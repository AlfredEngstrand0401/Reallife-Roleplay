package me.alfredengstrand.realliferoleplay.notificationsystem;

public enum Notification {
	
	WARNING_BOMB_WAS_FOUND("");

	private final String message;

	Notification(String message) {
    	this.message = message;
    }

	public String getMessage() {
		return message;
	}

}
