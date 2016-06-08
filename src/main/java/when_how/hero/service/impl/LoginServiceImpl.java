package when_how.hero.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import when_how.hero.battle.data.Battle;
import when_how.hero.battle.init.BattleInit;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.UidToken;
import when_how.hero.common.json.MyLoginSuccessResponse;
import when_how.hero.common.json.MyResponse;
import when_how.hero.dao.entity.UserDeck;
import when_how.hero.dao.mapper.UserDeckMapper;
import when_how.hero.dto.BattleInitData;
import when_how.hero.netty.serial.impl.JsonAutoCloseOutput;
import when_how.hero.service.LoginService;

/**
 * @author when_how
 * 
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private UserDeckMapper userDeckMapper;

	@Autowired
	private BattleInit battleInit;

	//rollbackFor=Exception.class, 
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public MyResponse login(String token) throws Exception {
		List<UserDeck> rr = userDeckMapper.getUserDeck(1);
//		rr.get(0).setDeckName("haha2");
//		userDeckMapper.updateUserDeck(rr.get(0));
		
		List<UserDeck> rr2 = userDeckMapper.getUserDeck(1);
		if (rr2.get(0).getDeckName().equals("haha2")) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		}
		
//		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		
		rr.get(0).setDeckName("haha4");
		userDeckMapper.updateUserDeck(rr.get(0));
		
		long uid = UidToken.getUidFromToken(token);
		String currentToken = redisTemplate.opsForValue().get("token." + uid);
		log.debug("currentToken: " + currentToken);
		if (!token.equals(currentToken)) {
			return new MyResponse(MyErrorMessage.needLogin);
		}
		String battleInitDataString = redisTemplate.opsForValue().get(
				"battle.init.data." + uid);
		log.debug("battleInitDataString: " + battleInitDataString);
		BattleInitData battleInitData = JsonAutoCloseOutput.MAPPER.readValue(
				battleInitDataString, BattleInitData.class);
		Battle battle = battleInit.init(battleInitData);

		// 把目前的战场数据发给客户端
		return new MyLoginSuccessResponse(uid, battle);
	}

}
