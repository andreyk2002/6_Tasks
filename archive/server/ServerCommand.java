package by.epam_tc.sockets.archive.server;

public enum ServerCommand {
	VIEW_PERSONS, // all users
	QUIT,
	
	BROWSE_ALL,
	BROWSE_BY_NAME, // clients and admins
	FIND, 
	
	SAVE,
	MODIFY, // admins only
	CREATE
}
