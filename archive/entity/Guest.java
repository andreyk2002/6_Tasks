package by.epam_tc.sockets.archive.entity;

public class Guest extends User {

	private static final long serialVersionUID = 3142254032547264461L;
	
	public Guest() {
		super("Guest");
	}
	
	public Guest(String name) {
		super(name);
	}

}
