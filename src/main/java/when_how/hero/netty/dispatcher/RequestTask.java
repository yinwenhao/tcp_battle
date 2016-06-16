package when_how.hero.netty.dispatcher;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.battle.Manager;
import when_how.hero.common.json.MyLoginSuccessResponse;
import when_how.hero.common.json.MyResponse;
import when_how.hero.constants.MyErrorNo;

public class RequestTask implements Runnable {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private String actionClassBean;

	private String method;

	private Map<String, Object> param;

	private ChannelHandlerContext ctx;

	public RequestTask(String actionClassBean, String method,
			Map<String, Object> param, ChannelHandlerContext ctx) {
		this.actionClassBean = actionClassBean;
		this.method = method;
		this.param = param;
		this.ctx = ctx;
	}

	@Override
	public void run() {
		try {
			if (!param.containsKey("sss")) {
				ctx.writeAndFlush(new MyResponse(MyErrorNo.wrongParam));
				return;
			}
			Long uid = Manager.getUidByCtx(ctx);
			if (uid != null) {
				param.put("uid", uid);
			} else {
				if (MyDispatcher.isNeedLogin(actionClassBean, method)) {
					ctx.writeAndFlush(new MyResponse(MyErrorNo.needLogin));
					ctx.close();
					return;
				}
			}
			int sequence = (int) param.get("sss");
			if (sequence <= Manager.getsequenceByCtx(ctx)
					&& MyDispatcher.isNeedLogin(actionClassBean, method)) {
				ctx.writeAndFlush(new MyResponse(
						MyErrorNo.requestOutOfDate));
				return;
			}
			synchronized (ctx) {
				if (sequence <= Manager.getsequenceByCtx(ctx)
						&& MyDispatcher.isNeedLogin(actionClassBean, method)) {
					ctx.writeAndFlush(new MyResponse(
							MyErrorNo.requestOutOfDate));
					return;
				}
				Manager.putSequence(ctx, sequence);
				param.remove("sss");
				MyResponse response = MyDispatcher.getResult(actionClassBean,
						method, param);
				if (response instanceof MyLoginSuccessResponse) {
					// 登陆成功
					long userId = ((MyLoginSuccessResponse) response).getUid();
					// 挤掉原来的连接
					ChannelHandlerContext ctxOld = Manager
							.removeCtxByUid(userId);
					if (ctxOld != null && ctxOld != ctx) {
						// 如果是同一个连接，就不关闭了。。。
						ctxOld.close(); // TODO: 通知客户端，该账号在其他地方登陆了
					}
					Manager.putCtxAndUid(ctx, userId);
				}
				if (response != null) {
					response.setSss(sequence);
					ctx.writeAndFlush(response);
				}
			}
		} catch (Exception e) {
			log.error(actionClassBean + "." + method + "(" + param + ")", e);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug(actionClassBean + "." + method + "(" + param + ")");
			}
		}
	}

}
