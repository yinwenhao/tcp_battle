/*
 * Copyright 2013 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package when_how.hero.http;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.COOKIE;
import static io.netty.handler.codec.http.HttpHeaders.Names.SET_COOKIE;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import flex.messaging.util.URLDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;

import when_how.hero.common.json.MyResponse;
import when_how.hero.netty.MyDispatcher;
import when_how.hero.netty.MySession;
import when_how.hero.netty.MySessionManager;
import when_how.hero.netty.MyTcpConstants;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpHelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, JsonParseException, JsonMappingException, IOException {
//    	DefaultFullHttpRequest r = (DefaultFullHttpRequest)msg;
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;

            if (HttpHeaders.is100ContinueExpected(req)) {
                ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
            }
            
            int sessionId = 0;
            String value = req.headers().get(COOKIE);
            Set<Cookie> cookies;
            if (value == null) {
                cookies = Collections.emptySet();
            } else {
                cookies = ServerCookieDecoder.STRICT.decode(value);
            }
            for (Cookie cookie : cookies) {
            	if (cookie.name().equalsIgnoreCase("JSESSIONID")) {
            		sessionId = Integer.valueOf(cookie.value());
            	}
            }
            SocketAddress remoteAddress = ctx.channel().remoteAddress();
            sessionId = MySessionManager.checkAndGetSessionId(sessionId, remoteAddress.hashCode());
    		MySession session = MySessionManager.getSession(sessionId);
    		String uri = req.getUri(); // TODO: /favicon.ico
            String[] uu = uri.split("\\?");
            short jiekouId = Short.valueOf(uu[0].substring(1));
            MyResponse response =MyDispatcher.getResult(jiekouId, URLDecoder.decode(uu[1]), session);
            ObjectMapper objectMapper = new ObjectMapper();
			ByteBuf responseByteBuf = Unpooled.copiedBuffer(objectMapper.writeValueAsString(response), Charset.forName(MyTcpConstants.charset));
            
            boolean keepAlive = HttpHeaders.isKeepAlive(req);
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, responseByteBuf);// Unpooled.wrappedBuffer(CONTENT)
            httpResponse.headers().set(CONTENT_TYPE, "text/plain");
            httpResponse.headers().set(CONTENT_LENGTH, httpResponse.content().readableBytes());
//            response.headers().add(SET_COOKIE, ServerCookieEncoder.encode(cookie));
            httpResponse.headers().set(SET_COOKIE, ServerCookieEncoder.STRICT.encode("JSESSIONID", String.valueOf(session.getSessionId())));
            if (!keepAlive) {
                ctx.write(httpResponse).addListener(ChannelFutureListener.CLOSE);
            } else {
                httpResponse.headers().set(CONNECTION, Values.KEEP_ALIVE);
                ctx.write(httpResponse);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	if (! (cause instanceof IOException)) {
    		cause.printStackTrace();
    	}
        ctx.close();
    }
}
