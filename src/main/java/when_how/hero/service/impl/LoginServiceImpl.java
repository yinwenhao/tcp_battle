package when_how.hero.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import when_how.hero.battle.data.Battle;
import when_how.hero.battle.init.BattleInit;
import when_how.hero.common.UidToken;
import when_how.hero.common.json.MyLoginSuccessResponse;
import when_how.hero.common.json.MyResponse;
import when_how.hero.constants.MyErrorNo;
import when_how.hero.constants.RedisKey;
import when_how.hero.dao.mapper.TestMapper;
import when_how.hero.dao.mapper.UserDeckMapper;
import when_how.hero.dto.BattleInitData;
import when_how.hero.netty.serial.impl.JsonAutoCloseOutput;
import when_how.hero.remotememory.RemoteMemory;
import when_how.hero.service.LoginService;

/**
 * @author when_how
 * 
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDeckMapper userDeckMapper;
	@Autowired
	private TestMapper testMapper;

	@Autowired
	private BattleInit battleInit;

	@Autowired
	private RemoteMemory redisRemoteMemory;

	@Override
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public MyResponse login(String token) throws Exception {
//		try {
//			testMapper.insertTest2(281475076710965L, "3bebe554fha24", 234354325, 1);
//		}catch (Exception e) {
//			log.error("test error!!!!!!!", e);
//		}
		// long start = System.currentTimeMillis();
		// testMapper.test();
		// log.debug("第一次：" + (System.currentTimeMillis() - start));
		// start = System.currentTimeMillis();
		// testMapper.test();
		// log.debug("第二次：" + (System.currentTimeMillis() - start));

		// List<UserDeck> rr = userDeckMapper.getUserDeck(1);
		// rr.get(0).setDeckName("haha2");
		// userDeckMapper.updateUserDeck(rr.get(0));

		// List<UserDeck> rr2 = userDeckMapper.getUserDeck(1);
		// if (rr2.get(0).getDeckName().equals("haha2")) {
		// log.debug("~~~~~~~~~~~~~~~~~~~~~");
		// }

		// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		// rr.get(0).setDeckName("haha4");
		// userDeckMapper.updateUserDeck(rr.get(0));

		long uid = UidToken.getUidFromToken(token);
		String currentToken = redisRemoteMemory.getString(RedisKey.token, uid);
		if (!token.equals(currentToken)) {
			return new MyResponse(MyErrorNo.needLogin);
		}
		String battleInitDataString = redisRemoteMemory.getString(
				RedisKey.battleInitData, uid);
		BattleInitData battleInitData = JsonAutoCloseOutput.MAPPER.readValue(
				battleInitDataString, BattleInitData.class);
		Battle battle = battleInit.init(battleInitData);

		// 把目前的战场数据发给客户端
		return new MyLoginSuccessResponse(uid, battle);
	}

}
