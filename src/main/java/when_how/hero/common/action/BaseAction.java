package when_how.hero.common.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import when_how.hero.common.json.MyResponse;
import when_how.hero.netty.MySession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author when_how
 * 
 */

public class BaseAction extends ActionSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1060001L;

	private long chatTime;

	/** 给客户端的string响应结果 */
	private String stringResponse;

	/** 给客户端的响应结果 */
	private MyResponse response;

	private InputStream inputStream;
	private InputStream stringInputStream;
	private InputStream inputStreamException;

	public static final String[] keys = new String[] { "playerDataChange",
			"chats", "bobao", "fcmTips" };

	/** mySession */
	private MySession session = new MySession();

	public MySession getSession() {
		return session;
	}

	public void setSession(MySession session) {
		this.session = session;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() throws JsonProcessingException {
//		PlayerDto playerDto = (PlayerDto) getSession().get(MyConstants.SESSION_KEY_PLAYER);
//		if (playerDto != null) {
//			// 处理标志
//			long mark = PlayerDataInterceptor.getMarkByPlayerIdAndLegionId(
//					playerDto.getPlayerId(), playerDto.getLegion());
//			JSONObject playerDataChange = new JSONObject();
//		}

		// TODO: 只是看看，用于调试
		// System.out.println("chatTime:" + chatTime + " " +
		// response.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		setInputStream(new ByteArrayInputStream(objectMapper.writeValueAsString(response).getBytes()));

		return inputStream;
	}

	public void setResponse(MyResponse response) {
		this.response = response;
	}

	public MyResponse getResponse() {
		return response;
	}

	public InputStream getInputStreamException() throws JsonProcessingException {
		response = new MyResponse();
		List<String> errorMsgs = (List<String>) this.getActionMessages();
		if (errorMsgs != null && errorMsgs.size() > 0) {
//			response.put(MyJSONBuilder.STATE, errorMsgs.get(0));
		}
		ObjectMapper objectMapper = new ObjectMapper();
		setInputStreamException(new ByteArrayInputStream(objectMapper.writeValueAsString(response).getBytes()));
		// TODO: 只是看看，用于调试
		// System.out.println(inputStreamException.toString());
		return inputStreamException;
	}

	public void setInputStreamException(InputStream inputStreamException) {
		this.inputStreamException = inputStreamException;
	}

	public long getChatTime() {
		return chatTime;
	}

	public void setChatTime(long chatTime) {
		this.chatTime = chatTime;
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

}
