package me.alfredengstrand.realliferoleplay.economy;

import java.util.UUID;

public class Account {

	private UUID uuid;
	private float balance;

	public Account(UUID uuid, float balance) {
		this.setUuid(uuid);
		this.setBalance(balance);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void addBalance(float amount) {
		this.balance += amount;
	}

	public void removeBalance(float amount) {
		this.balance -= amount;
	}

	public void reset() {
		this.balance = 0;
	}
}
