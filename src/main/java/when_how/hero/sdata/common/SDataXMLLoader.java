package when_how.hero.sdata.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;


public class SDataXMLLoader {

	/** loader */
	private static SDataXMLLoader loader;

	/** XML 文档 */
	private Document doc;

	public static SDataXMLLoader getInstance(String path) {
		if (loader == null) {
			loader = new SDataXMLLoader(path);
		}
		return loader;
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 */
	private SDataXMLLoader(String path) {
		File file = getFile(path);
		// 解析文件
		DocumentBuilder db;
		try {
			// 初始化工厂
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			dbf.setNamespaceAware(false);

			db = dbf.newDocumentBuilder();

			db.setErrorHandler(new ErrorHandler() {
				public void error(SAXParseException exception)
						throws SAXException {
					throw exception;
				}

				public void fatalError(SAXParseException exception)
						throws SAXException {
					throw exception;
				}

				public void warning(SAXParseException exception) {
				}
			});

			// 解析文件
			ZipFile zf = null;
			try {
				zf = new ZipFile(file);
				ZipEntry ze = null;
				Enumeration<?> entryEnum = zf.entries();
				while (entryEnum != null && entryEnum.hasMoreElements()) {
					ze = (ZipEntry) entryEnum.nextElement();
					/*
					 * InputStream ip = zf.getInputStream(ze); BufferedReader br
					 * = new BufferedReader(new InputStreamReader(ip)); String
					 * str; while((str = br.readLine())!=null){
					 * //数据表字段超长，导致下面一行代码报错，如Unicode:ox12 ,
					 * 可用UE十六进制编辑模式查得非法字符所在表。 }
					 */
					doc = db.parse(zf.getInputStream(ze));
				}

			} catch (Exception e) {
				final String s = "Caught exception while loading file ";
				throw new RuntimeException(s, e);
			} finally {
				if (zf != null) {
					try {
						zf.close();
					} catch (IOException e) {
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error loading configuration file ", e);
		}
	}

	/**
	 * 获取实体类
	 * 
	 * @param clazz
	 * @return 2010-7-12 上午09:55:11
	 */
	public List<Object[]> getModels(Class<?> clazz) {
		return loadModelsFromFile(clazz);
	}

	/**
	 * 查找指定表名队员的列
	 * 
	 * @param tableName
	 * @return
	 */
	private NodeList loadConfigurationFile(String tableName) {
		Element rootElement = doc.getDocumentElement();
		NodeList children = rootElement.getChildNodes();

		return parseNode(children, tableName);
	}

	/**
	 * 从文件中获取实体类
	 * 
	 * @param clazz
	 * @return
	 */
	private List<Object[]> loadModelsFromFile(Class<?> clazz) {
		NodeList nodeList = loadConfigurationFile(clazz.getAnnotation(
				SdataTable.class).table());
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				Element e = (Element) node;
				Object[] obj = loadModelsFromXML(clazz, e.getChildNodes());
				list.add(obj);
			}
		}
		return list;

	}

	/**
	 * 从XML中获取实体
	 * 
	 * @param clazz
	 * @param nodeList
	 * @return
	 */
	private Object[] loadModelsFromXML(Class<?> clazz, NodeList nodeList) {
		try {
			// 构造实例
			Object obj = clazz.newInstance();

			// 获取所有属性
			Field[] identifierColumns = clazz.getDeclaredFields();

			// 主键
			Object key = null;

			for (Field f : identifierColumns) {
				if (f.getAnnotation(SdataColumn.class) == null) {
					continue;
				}
				Node node = null;
				for (int i = 0; i < nodeList.getLength(); i++) {
					node = nodeList.item(i);
					if (node instanceof Element) {
						Element element = (Element) node;
						// 列名
						String fieldName = element.getAttribute("name")
								.toUpperCase();
						if (fieldName.equalsIgnoreCase(f.getName())) {
							break;
						}
					}
					node = null;
				}

				// 处理一般属性列
				Class<?> fc = f.getType();
				String setter = "set"
						+ f.getName().substring(0, 1).toUpperCase()
						+ f.getName().substring(1);
				Method m = clazz.getMethod(setter, new Class<?>[] { fc });
				Object value = getValue(fc, node.getNodeValue());
				m.invoke(obj, value);
				if (f.getAnnotation(SdataColumn.class).type()
						.equalsIgnoreCase("id")) {
					// 处理主键列
					if (key == null) {
						key = value;
					} else {
						key = key.toString() + ":" + value.toString();
					}
				}
			}
			return new Object[] { key, obj };
		} catch (Exception e) {
			throw new RuntimeException("unable parse xml", e);
		}
	}

	/**
	 * 获取真实类型的Value
	 * 
	 * @param type
	 * @param value
	 * @return 2010-7-12 下午12:50:15
	 */
	private Object getValue(Class<?> type, String value) {
		if (type.isAssignableFrom(int.class)
				|| type.isAssignableFrom(Integer.class)) {
			return Integer.parseInt(value);
		} else if (type.isAssignableFrom(float.class)
				|| type.isAssignableFrom(Float.class)) {
			return Float.parseFloat(value);
		} else if (type.isAssignableFrom(long.class)
				|| type.isAssignableFrom(Long.class)) {
			return Long.parseLong(value);
		} else if (type.isAssignableFrom(double.class)
				|| type.isAssignableFrom(Double.class)) {
			return Double.parseDouble(value);
		} else if (type.isAssignableFrom(String.class)) {
			return String.valueOf(value);
		} else {
			return value;
		}
	}

	/**
	 * 解析节点
	 * 
	 * @param nodeList
	 * @param tableName
	 * @return
	 */
	private NodeList parseNode(NodeList nodeList, String tableName) {
		int childSize = nodeList.getLength();

		for (int i = 0; i < childSize; i++) {
			Node childNode = nodeList.item(i);
			if (childNode instanceof Element) {
				Element child = (Element) childNode;

				final String nodeName = child.getNodeName();
				if (nodeName.equalsIgnoreCase("mysql")) {
					return parseNode(child.getChildNodes(), tableName);
				} else if (nodeName.equalsIgnoreCase("database")) {
					return parseNode(child.getChildNodes(), tableName);
				} else if (nodeName.equalsIgnoreCase("table")) {
					String name = child.getAttribute("name");
					if (tableName.equalsIgnoreCase(name)) {
						return child.getChildNodes();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 通过网络获取文件
	 * 
	 * @param urlpath
	 * @return
	 */
	private File getFile(String urlpath) {
		URL url;
		try {
			url = new URL(urlpath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			File file = null;
			FileOutputStream fos = null;
			try {
				byte[] buff = new byte[1024];
				file = File.createTempFile("sxfe", ".tmp");

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
				return file;
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
