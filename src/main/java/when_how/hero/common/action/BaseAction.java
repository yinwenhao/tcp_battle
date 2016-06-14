package when_how.hero.common.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import when_how.hero.common.json.MyResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author when_how
 * 
 */

public class BaseAction {

	/** 给客户端的string响应结果 */
	private String stringResponse;

	/** 给客户端的响应结果 */
	private MyResponse response;

	private long uid;

	private InputStream inputStream;
	private InputStream stringInputStream;

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		setInputStream(new ByteArrayInputStream(objectMapper
				.writeValueAsString(response).getBytes()));
		return inputStream;
	}

	public void setResponse(MyResponse response) {
		this.response = response;
	}

	public MyResponse getResponse() {
		return response;
	}

	public InputStream getStringInputStream() {
		setStringInputStream(new ByteArrayInputStream(stringResponse.getBytes()));
		return stringInputStream;
	}

	public void setStringInputStream(InputStream stringInputStream) {
		this.stringInputStream = stringInputStream;
	}

	public String getStringResponse() {
		return stringResponse;
	}

	public void setStringResponse(String stringResponse) {
		this.stringResponse = stringResponse;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}
