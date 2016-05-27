package tp.myapp.minibank.core.service;

//public class MyServiceException extends Exception {
//RuntimeException for EJB default rollback 
public class MyServiceException extends RuntimeException {	

	private static final long serialVersionUID = 1L;

	public MyServiceException() {
		
	}

	public MyServiceException(String msg) {
		super(msg);
		
	}

	public MyServiceException(Throwable ex) {
		super(ex);
		
	}

	public MyServiceException(String msg, Throwable ex) {
		super(msg, ex);
		
	}

}
