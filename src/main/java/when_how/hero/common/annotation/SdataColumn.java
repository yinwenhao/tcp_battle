package when_how.hero.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface SdataColumn {

	/**
	 * 视图类型：id表示主键或组合主键的一个，field表示普通属性
	 * @return
	 */
	public String type() default "field";
}
