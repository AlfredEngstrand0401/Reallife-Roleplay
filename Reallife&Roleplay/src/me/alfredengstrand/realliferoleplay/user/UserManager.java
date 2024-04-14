package me.alfredengstrand.realliferoleplay.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {

	public static List<User> users = new ArrayList<User>();

	public static void registerUser(User user) {
		users.add(user);
		System.out.println("Registered one new user: " + user.getUuid());
	}

	public static void unregisterUser(User user) {
		users.remove(user);
	}

	public static User getUser(UUID uuid) {
	    for (User currentUser : users) {
	        if (currentUser.getUuid().equals(uuid)) {
	            return currentUser;
	        }
	    }
	    return null;
	}
}
