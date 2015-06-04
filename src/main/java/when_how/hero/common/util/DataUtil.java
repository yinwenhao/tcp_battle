package when_how.hero.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 工具类,用于填充查询结果
 * 2009-8-27 下午09:20:51
 */
public class DataUtil {
	
	/**
	 * 只用于简单对象
	 * @param target
	 * @param value
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void fillObject(Object target, Object value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 获取所有属性
		Class<? extends Object> clazz = target.getClass();
		Class<? extends Object> clazz2 = value.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			Class<?> fc = f.getType();
			String setter = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
			String getter = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
			Method m = clazz.getMethod(setter, new Class<?>[]{fc});
			Method m2 = clazz2.getMethod(getter);
			m.invoke(target, m2.invoke(value));
		}
	}
	
	/**
	 * 将查询结果填充到指定类中
	 * @param target
	 * @param property
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * 2009-8-27 下午09:21:12
	 */
	public static void setObject(Object target, String property, Object value) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
		IllegalAccessException, InvocationTargetException, InstantiationException
	{
		Object t2 = target;
		setObjectConversion(t2, value, property);
	}

	/**
	 * 将查询结果填充到指定类中
	 * @param target
	 * @param value
	 * @param property
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * 2009-8-27 下午09:21:47
	 */
	public static void setObjectConversion(Object target, Object value, String property)
		throws IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
	{
		String setterName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
		if(value instanceof BigDecimal){
			BigDecimal dec = (BigDecimal)value;

			String getterName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
			Method mGet = target.getClass().getMethod(getterName);
			Class<?> t = mGet.getReturnType();
			Method m = target.getClass().getMethod(setterName, t);

			if(t == Integer.class || t == int.class){
				m.invoke(target, dec.intValue());
			}
			else if(t == BigDecimal.class){
				m.invoke(target, dec);
			}
			else if(t == Long.class || t == long.class){
				m.invoke(target, dec.longValue());
			}
			else if(t == BigInteger.class){
				m.invoke(target, dec.toBigInteger());
			}
			else{
				m.invoke(target, dec.toString());
			}
		}
		else{
			Method[] methods = target.getClass().getMethods();
			for(Method m : methods){
				if(m.getName().equals(setterName)){
					if(value instanceof Timestamp){
						Timestamp t = (Timestamp)value;
						Class<?>[] types = m.getParameterTypes();
						if(types.length>0 && java.util.Date.class.equals(types[0])){
							java.util.Date newValue = new java.util.Date(t.getTime());
							m.invoke(target, newValue);
							break;
						}
					}
					m.invoke(target, value);
					break;
				}
			}
		}
	}
	
	/**
	 * 深度拷贝
	 * @param srcObj
	 * @return
	 * 2010-4-22 下午03:30:24
	 */
	public static Object depthClone(Object srcObj) {
		Object cloneObj = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(out);
			oo.writeObject(srcObj);

			ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(in);
			cloneObj = oi.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cloneObj;
	} 
}
