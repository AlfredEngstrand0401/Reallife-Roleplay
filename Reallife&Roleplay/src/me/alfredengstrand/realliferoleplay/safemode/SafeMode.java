package me.alfredengstrand.realliferoleplay.safemode;

public class SafeMode {
	
	private static boolean active;

	public static boolean isActive() {
		return active;
	}

	public static void setActive(boolean active) {
		SafeMode.active = active;
	}

}
