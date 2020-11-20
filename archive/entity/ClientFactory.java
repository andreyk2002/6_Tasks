package by.epam_tc.sockets.archive.entity;

public class ClientFactory {
	private static ClientFactory instance = null;
	
	private ClientFactory(){
		
	}
	
	public static ClientFactory getInstance() {
		if(instance == null) {
			instance = new ClientFactory();
		}
		return instance;
	}
	
	public User getAdmin(String name) {
		return new Admin(name);
	}
	
	public User getClient(String name) {
		return new User(name);
	}
	
	public User getGuest(String name) {
		return new Guest(name);
	}
}
