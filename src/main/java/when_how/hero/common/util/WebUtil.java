package when_how.hero.common.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WebUtil {

	/**
	 * 发送请求到指定URL，POST方式
	 * 
	 * @param requestURL
	 * @param paramMap
	 * @return 2010-6-11 下午05:36:54
	 */
	public static String sendPostRequest(String requestURL,
			Map<String, Object> paramMap) {
		URL url = null;
		HttpURLConnection connection = null;
		BufferedInputStream bis = null;
		try {
			url = new URL(requestURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			// 设置连接超时时间: 60s
			connection.setConnectTimeout(1000 * 5);
			// 设置读取超时时间: 60s
			connection.setReadTimeout(1000 * 5);

			// 发送POST数据
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			String param = getParam(paramMap);
			out.write(param);
			out.flush();
			out.close();

			int code = connection.getResponseCode();
			// log.info("HttpURLConnection code: "+code);
			if (code == HttpURLConnection.HTTP_OK) {
				bis = new BufferedInputStream(connection.getInputStream());
				int length = -1;
				byte[] buff = new byte[1024];
				StringBuilder builder = new StringBuilder("");
				while ((length = bis.read(buff)) != -1) {
					builder.append(new String(buff, 0, length));
				}
				// log.info("HttpURLConnection content: " + builder.toString());
				return builder.toString();
			} else {
				return "";
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	/**
	 * 发送请求到指定URL，GET方式
	 * 
	 * @param requestURL
	 * @param paramMap
	 * @return 2013-6-11 下午05:36:54
	 */
	public static String sendGetRequest(String requestURL,
			Map<String, Object> paramMap) {
		URL url = null;
		HttpURLConnection connection = null;
		BufferedInputStream bis = null;
		try {
			requestURL = getURL(requestURL, paramMap);
			url = new URL(requestURL);

			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(false);
			// 设置连接超时时间: 15s
			connection.setConnectTimeout(1000 * 15);
			// 设置读取超时时间: 15s
			connection.setReadTimeout(1000 * 15);

			int code = connection.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				bis = new BufferedInputStream(connection.getInputStream());
				int length = -1;
				byte[] buff = new byte[1024];
				StringBuilder builder = new StringBuilder("");
				while ((length = bis.read(buff)) != -1) {
					builder.append(new String(buff, 0, length));
				}
				return builder.toString();
			} else {
				return "";
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	/**
	 * 发送请求到指定URL，GET方式
	 * 
	 * @param requestURL
	 * @param paramMap
	 * @return 2010-6-11 下午05:36:54
	 */
	public static String sendSSLGetRequest(String requestURL,
			Map<String, Object> paramMap) {
		URL url = null;
		HttpsURLConnection connection = null;
		BufferedInputStream bis = null;
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());

			requestURL = getURL(requestURL, paramMap);
			url = new URL(requestURL);

			connection = (HttpsURLConnection) url.openConnection();
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			connection.setDoOutput(false);
			connection.connect();

			int code = connection.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				bis = new BufferedInputStream(connection.getInputStream());
				int length = -1;
				byte[] buff = new byte[1024];
				StringBuilder builder = new StringBuilder("");
				while ((length = bis.read(buff)) != -1) {
					builder.append(new String(buff, 0, length));
				}
				return builder.toString();
			} else {
				return "";
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	/**
	 * 获取参数
	 * 
	 * @param paramMap
	 *            参数Map
	 * @return 2010-6-11 下午05:37:09
	 * @throws UnsupportedEncodingException
	 */
	public static String getURL(String url, Map<String, Object> paramMap)
			throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		builder.append(url);
		Set<Entry<String, Object>> entrySet = paramMap.entrySet();
		int index = 0;
		for (Entry<String, Object> entry : entrySet) {
			if (index != 0) {
				builder.append("&");
			} else {
				builder.append("?");
			}
			builder.append(entry.getKey())
					.append("=")
					.append(URLEncoder.encode(entry.getValue().toString(),
							"UTF-8"));
			index++;
		}
		return builder.toString();
	}

	/**
	 * 获取URI编码方式
	 * 
	 * @param defaultEncode
	 * @return 2010-6-11 下午05:37:09
	 * @throws UnsupportedEncodingException
	 */
	private static String getParam(Map<String, Object> paramMap)
			throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		Set<Entry<String, Object>> entrySet = paramMap.entrySet();
		int index = 0;
		for (Entry<String, Object> entry : entrySet) {
			if (index != 0) {
				builder.append("&");
			}
			builder.append(entry.getKey())
					.append("=")
					.append(URLEncoder.encode((String) entry.getValue(),
							"UTF-8"));
			index++;
		}
		return builder.toString();
	}

	/**
	 * SSL验证器
	 * 
	 */
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	/**
	 * SSL验证器
	 * 
	 */
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}
