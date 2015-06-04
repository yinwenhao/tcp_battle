package when_how.hero.common.interceptor;

import java.lang.reflect.Method;
import java.util.Collection;

import when_how.hero.common.action.BaseAction;
import when_how.hero.common.json.MyResponse;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Unchainable;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.CompoundRoot;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.reflection.ReflectionProvider;

/**
 * Chain拦截器
 * @author when_how
 *
 */
public class ChainInterceptor extends AbstractInterceptor {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 407729266017029279L;
	
	/** getResponse 方法 */
	private static final String METHOD_GETRESPONSE = "getResponse";

	/** 排除的参数 */
    protected Collection<String> excludes;
    
    /** 包含的参数 */
    protected Collection<String> includes;

    /** 反射提供商 */
    protected ReflectionProvider reflectionProvider;

    @Inject
    public void setReflectionProvider(ReflectionProvider prov) {
        this.reflectionProvider = prov;
    }

	@Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ValueStack stack = invocation.getStack();
        CompoundRoot root = stack.getRoot();

        if (root.size() >= 3) {
        	Object o = root.get(1);
            if (!(o instanceof Unchainable)) {    	
            	Object targetObj = invocation.getAction();
            	if (o instanceof ActionSupport && targetObj instanceof ActionSupport) {
            		// 拷贝异常信息
            		ActionSupport source = (ActionSupport) o;
            		ActionSupport target = (ActionSupport) targetObj;
            		
            		target.setActionErrors(source.getActionErrors());
            		target.setActionMessages(source.getActionMessages());
            		target.setFieldErrors(source.getFieldErrors());
            	}
            	
            	// 拷贝
				if (targetObj instanceof BaseAction) {
					BaseAction result = (BaseAction) targetObj;
					Method[] methods = o.getClass().getMethods();
					for (Method method : methods) {
						if (METHOD_GETRESPONSE.equals(method.getName())) {
							Object value = method.invoke(o, new Object[] {});
							result.setResponse((MyResponse) value);
						}
					}
				}
            }
        }
        return invocation.invoke();
    }

    /**
     * Gets list of parameter names to exclude
     *
     * @return the exclude list
     */
    public Collection<String> getExcludes() {
        return excludes;
    }

    /**
     * Sets the list of parameter names to exclude from copying (all others will be included).
     *
     * @param excludes the excludes list
     */
    public void setExcludes(Collection<String> excludes) {
        this.excludes = excludes;
    }

    /**
     * Gets list of parameter names to include
     *
     * @return the include list
     */
    public Collection<String> getIncludes() {
        return includes;
    }

    /**
     * Sets the list of parameter names to include when copying (all others will be excluded).
     *
     * @param includes the includes list
     */
    public void setIncludes(Collection<String> includes) {
        this.includes = includes;
    }
}
