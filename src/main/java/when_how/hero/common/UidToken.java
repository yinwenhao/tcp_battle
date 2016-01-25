package when_how.hero.common;

import java.io.IOException;
import java.util.UUID;

import when_how.hero.dto.BattleInitData;
import when_how.hero.netty.serial.impl.JsonAutoCloseOutput;

public class UidToken {
	
//	private static Map
	
	public static long getUidFromToken(String token) {
		return Long.valueOf(token.substring(32));
	}
	
	public static void main(String[] args) {
		String a = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(a);
		System.out.println(getUidFromToken(a+"1234567890"));
		
		BattleInitData battleInitData = new BattleInitData();
		battleInitData.setUid(new long[] {999,998});
		battleInitData.setHeroId(new int[] {1,2});
		battleInitData.setCards(new int[][] {{1,2,3},{1,2,3}});
		try {
			JsonAutoCloseOutput.MAPPER.writeValue(System.out, battleInitData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
