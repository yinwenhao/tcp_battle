package when_how.hero.common;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2062609475802878224L;

	private int errNo;

	public MyException(int errNo) {
		this.errNo = errNo;
	}

	public int getErrNo() {
		return errNo;
	}

	public void setErrNo(int errNo) {
		this.errNo = errNo;
	}

}
