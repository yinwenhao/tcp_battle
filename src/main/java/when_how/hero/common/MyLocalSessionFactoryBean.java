package when_how.hero.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.catalina.loader.WebappClassLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class MyLocalSessionFactoryBean extends LocalSessionFactoryBean{

	@Override
	public void setMappingJarLocations(Resource[] mappingJarLocations) {
		WebappClassLoader loader = (WebappClassLoader) Thread.currentThread().getContextClassLoader();
		if (mappingJarLocations != null) {			
			Resource[] self = new Resource[mappingJarLocations.length];
			int index = 0;
			for (Resource res : mappingJarLocations) {
				FileOutputStream fos = null;
				try {
					byte[] buff = new byte[1024];
					InputStream is = loader.getResourceAsStream(res.getFilename());
					File file = File.createTempFile("abc", ".jar");
					
					fos = new FileOutputStream(file);
					while (true) {
						int length = is.read(buff, 0, buff.length);
						if (-1 != length) {
							fos.write(buff, 0, length);
						} else {
							break;
						}
					}
					fos.flush();
					fos.close();
					
					self[index++] = new FileSystemResource(file);
				} catch (IOException e) {
				} finally {
					if (fos != null) {
						try {
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			super.setMappingJarLocations(self);
			
		}
	}	
	
}
