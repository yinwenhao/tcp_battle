package when_how.hero.hibernate.hql;

import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * HQL工厂类
 * @author when_how
 *
 * 2011-2-24 下午04:30:48
 */
public class HqlFactory implements InitializingBean{
	/** log */
	private static final Logger log = LoggerFactory.getLogger(HqlFactory.class);
	
	// 存储ID-SQL
	private Map<String, String> dic = new HashMap<String, String>();
	
	// 配置文件资源
	private Resource[] resources;
	
	// Schema资源
	private Resource schemaResource;
	
	/**
	 * 设置配置文件资源
	 * @param resources
	 */
	public void setResources(Resource[] resources){
		this.resources = resources;
	}
	
	/**
	 * 是指Schema资源
	 * @param resource
	 */
	public void setSchemaResource(Resource resource){
		this.schemaResource = resource;
	}
	
	/**
	 * 返回指定SQL语句
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return dic.get(key) == null ? key : dic.get(key);
	}
	
	/**
	 * 加载配置文件
	 */
	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {		
		JAXBContext jaxbContext = JAXBContext.newInstance("com.when_how.hibernate.hql");
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new StreamSource(schemaResource.getInputStream()));
		unmarshaller.setSchema(schema);

		for (Resource res : resources) {
			JAXBElement<Hqls> element = (JAXBElement<Hqls>) unmarshaller.unmarshal(res.getInputStream());
			Hqls hqls = element.getValue();
			for (Hql hql : hqls.getHql()) {
				if (dic.containsKey(hql.getId())) {
					//throw new InternalException("定义了重复的SQL:" + sql.getId());
				} else {
					dic.put(hql.getId(), hql.getValue());
					log.info("HQL语句加载完毕:" + hql.getId());
				}
			}
		}
	}
}
