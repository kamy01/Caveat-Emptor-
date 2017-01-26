package ro.fortech.caveatEmptor.exceptions;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764980094866259633L;

	private String message;

	public UserException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
