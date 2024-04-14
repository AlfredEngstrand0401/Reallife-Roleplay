package me.alfredengstrand.realliferoleplay.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

public class MySQL {

	private String host = "localhost";
	private String port = "3306";
	private String database = "economy";
	private String username = "root";
	private String password = "";

	private Connection connection;

	public boolean isConnected() {
		return (connection == null ? false : true);
	}

	public void connect() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false",
				username, password);
		this.createTable();
		System.out.println("Sucessfully connected to the database!");
	}

	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
				System.out.println("the connection has been closed!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createTable() {
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS balance (NAME VARCHAR(100), UUID VARCHAR(100), POINTS INT(100), PRIMARY KEY(UUID))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createPlayer(Player player) {
		try {
			UUID uuid = player.getUniqueId();
			PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM balance WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet results = ps.executeQuery();
			results.next();
			if(!exists(uuid)) {
				PreparedStatement ps2 = this.connection.prepareStatement("INSERT IGNORE INTO balance (NAME, UUID) VALUE(?,?)");
				ps2.setString(1, player.getName());
				ps2.setString(2, uuid.toString());
				ps2.executeUpdate();
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean exists(UUID uuid) {
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM balance WHERE UUID=?");
			ps.setString(1, uuid.toString());
			ResultSet results = ps.executeQuery();
			if (results.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateBalance(UUID uuid, int balance) {
		try {
			PreparedStatement ps = this.connection.prepareStatement("UPDATE balance SET POINTS=? WHERE UUID=?");
			ps.setInt(1, balance);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getBalance(UUID uuid) {
	    try {
	        PreparedStatement ps = this.connection.prepareStatement("SELECT POINTS FROM balance WHERE UUID=?");
	        ps.setString(1, uuid.toString());
	        ResultSet results = ps.executeQuery();
	        int points = 0;
	        if(results.next()) {
	            points = results.getInt("POINTS");
	            return points;
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

}
