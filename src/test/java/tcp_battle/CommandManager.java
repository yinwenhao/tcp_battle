package tcp_battle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import when_how.hero.request.Request;
import io.netty.channel.ChannelHandlerContext;

public class CommandManager implements Runnable {

	private ChannelHandlerContext ctx;
	
	private int sequence = 0;

	public CommandManager(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}
	
	private int addSequenceAndGet() {
		return ++sequence;
	}

	@Override
	public void run() {
		System.out
				.println("input command. Example:"
						+ "\r\nlogin login token=String:12345678901234567890123456789012999"
						+ "\r\ncard changeCardsInHand changeIndexString=String:0,1 turn=int:0"
						+ "\r\ncard useCard i=int:0 location=int:0"
						+ "\r\nturn endTurn turn=int:1");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			boolean done = false;
			while (!done) {
				System.out.print("> ");

				String command = in.readLine().trim();

				String[] parts = command.split("\\s");
				if (parts.length == 0) {
					continue;
				}

				if (parts[0].equals("q") || parts[0].equals("quit")) {
					done = true;
					break;
				}

				Request request = new Request();
				request.setBean(parts[0]);
				request.setMethod(parts[1]);
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 2; i < parts.length; i++) {
					String[] ss = parts[i].split("=");
					String[] sss = ss[1].split(":");
					Object p = null;
					switch (sss[0]) {
					case "int":
					case "Integer":
						p = Integer.valueOf(sss[1]);
						break;
					case "long":
					case "Long":
						p = Long.valueOf(sss[1]);
						break;
					case "short":
					case "Short":
						p = Short.valueOf(sss[1]);
						break;
					case "double":
					case "Double":
						p = Double.valueOf(sss[1]);
						break;
					case "String":
						p = sss[1];
						break;
					}
					map.put(ss[0], p);
				}
				map.put("sss", addSequenceAndGet());
				request.setParam(map);
				ctx.writeAndFlush(request);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
