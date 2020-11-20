package by.epam_tc.sockets.archive.exception;

public class AccessDeniedException extends Exception {

	private static final long serialVersionUID = -2759729410460863271L;
	
	public AccessDeniedException() {
		
	}
	
	public AccessDeniedException(String msg) {
		super(msg);
	}

}
