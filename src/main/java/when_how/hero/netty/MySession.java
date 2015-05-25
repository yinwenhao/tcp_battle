package when_how.hero.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import when_how.hero.common.json.MyResponse;
import when_how.hero.common.util.GZipUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MySession {
	
	private Map<String, Object> session = new HashMap<String, Object>();
	private ChannelHandlerContext ctx;
	private int sessionId;
	private long sessionTime;
	
	public boolean sendResponse(short jiekouId, MyResponse response) throws Exception {
		if (!ctx.channel().isActive()) {
			return false;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] reponseContentBytes1 = objectMapper.writeValueAsString(response).getBytes(MyTcpConstants.charset);
		byte[] reponseContentBytes2 = GZipUtils.compress(reponseContentBytes1);
		byte[] reponseContentBytes;
		byte responseType;
		if (reponseContentBytes1.length <= reponseContentBytes2.length) {
			reponseContentBytes = reponseContentBytes1;
			responseType = MyTcpConstants.type_string;
		} else {
			reponseContentBytes = reponseContentBytes2;
			responseType = MyTcpConstants.type_gzip;
		}
		MySimpleEncrypt.encode(reponseContentBytes);
		final ByteBuf responseByteBuf = ctx.alloc().buffer(32);
		responseByteBuf.writeInt(sessionId);
		responseByteBuf.writeShort(jiekouId);
		responseByteBuf.writeByte(responseType);
		responseByteBuf.writeBytes(reponseContentBytes);
		ctx.write(responseByteBuf);
		return true;
	}
	
	public boolean containsKey(String key) {
		return session.containsKey(key);
	}
	
	public void clear() {
		session.clear();
	}
	
	public Object get(String key) {
		return session.get(key);
	}
	
	public void put(String key, Object value) {
		session.put(key, value);
	}
	
	public String getString(String key) {
		return (String) session.get(key);
	}
	
	public Integer getInt(String key) {
		return (Integer) session.get(key);
	}
	
	public Long getLong(String key) {
		return (Long) session.get(key);
	}
	
	public Short getShort(String key) {
		return (Short) session.get(key);
	}
	
	public Double getDouble(String key) {
		return (Double) session.get(key);
	}
	
	public Float getFloat(String key) {
		return (Float) session.get(key);
	}
	
	public Character getChar(String key) {
		return (Character) session.get(key);
	}
	
	public Date getDate(String key) {
		return (Date) session.get(key);
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public long getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(long sessionTime) {
		this.sessionTime = sessionTime;
	}

}
