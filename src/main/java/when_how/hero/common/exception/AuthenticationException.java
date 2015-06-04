package when_how.hero.common.exception;


public class AuthenticationException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 547698752364L;
	
	private String errorCode;
	
	private Object[] args;
	
	public AuthenticationException() {
		super();
	}
	
	public AuthenticationException(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public AuthenticationException(String errorMsg, Throwable throwable) {
		super(errorMsg, throwable);
		this.errorCode = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
