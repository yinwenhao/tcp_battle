package when_how.hero.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import when_how.hero.battle.data.Battle;
import when_how.hero.battle.init.BattleInit;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.UidToken;
import when_how.hero.common.json.MyLoginSuccessResponse;
import when_how.hero.common.json.MyResponse;
import when_how.hero.dao.mapper.UserMapper;
import when_how.hero.dto.BattleInitData;
import when_how.hero.dto.own.OwnBattleData;
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
	private UserMapper userMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private BattleInit battleInit;
	
	@Override
	public MyResponse login(String token) throws Exception {
		long uid = UidToken.getUidFromToken(token);
		String currentToken = redisTemplate.opsForValue().get("token."+uid);
		log.debug("currentToken: "+currentToken);
		if (!token.equals(currentToken)) {
			return new MyResponse(MyErrorMessage.needLogin);
		}
		String battleInitDataString = redisTemplate.opsForValue().get("battle.init.data." + uid);
		log.debug("battleInitDataString: "+battleInitDataString);
		BattleInitData battleInitData = JsonAutoCloseOutput.MAPPER.readValue(battleInitDataString, BattleInitData.class);
		Battle battle = battleInit.init(battleInitData);

		// 把目前的战场数据发给客户端
		OwnBattleData obd = new OwnBattleData(battle, uid);
		MyResponse result = new MyLoginSuccessResponse();
		result.setData(obd);
		return result;
	}

}
