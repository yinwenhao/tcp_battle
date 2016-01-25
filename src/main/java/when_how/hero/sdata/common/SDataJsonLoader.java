package when_how.hero.sdata.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import when_how.hero.common.annotation.SdataTable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SDataJsonLoader {

	private static ObjectMapper mapper = new ObjectMapper();

	public static <T> List<T> getModels(Class<T> clazz) throws IOException {
		List<T> result = new ArrayList<T>();
		String fileName = "sdata/"
				+ clazz.getAnnotation(SdataTable.class).table() + ".json";
		try (InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
				BufferedReader bufferReader = new BufferedReader(
						new InputStreamReader(in))) {
			for (String line = bufferReader.readLine(); line != null; line = bufferReader
					.readLine()) {
				result.add(mapper.readValue(line, clazz));
			}
		}
		return result;
	}

}
