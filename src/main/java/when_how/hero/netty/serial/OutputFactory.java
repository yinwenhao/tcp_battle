package when_how.hero.netty.serial;

import java.io.IOException;

public interface OutputFactory {

	public byte[] output(Object data) throws IOException;
	
}
