package fi.ukkosnetti.symprap.model;

public enum UserRole {
	
	ADMIN, TEEN, FOLLOWER;

	public static String[] stringValues() {
		return new String[] {ADMIN.toString(), TEEN.toString(), FOLLOWER.toString()};
	}
}
