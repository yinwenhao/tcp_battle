package when_how.hero.netty;

public class MySimpleEncrypt {

	/** 密钥 */
	public static final byte[] key = "iSG^t!bO#8)3>jm3-4=hR+OB463M6H".getBytes();
	
	public static void encode(byte[] contentBytes) {
		int dataLength = contentBytes.length;
		int keyIndex = 0;
		for (int i = 0; i < dataLength; i++) {
			byte tkValue = key[keyIndex];
			keyIndex = ++keyIndex % key.length;

			contentBytes[i] = (byte) (contentBytes[i] ^ tkValue);
		}
	}
	
	public static void decode(byte[] contentBytes) {
		int dataLength = contentBytes.length;
		int keyIndex = 0;
		for (int i = 0; i < dataLength; i++) {
			byte tkValue = key[keyIndex];
			keyIndex = ++keyIndex % key.length;
			
			contentBytes[i] = (byte) (contentBytes[i] ^ tkValue);
		}
	}

}
