package com.drugstore.server.db;

import com.drugstore.server.exception.DrugStoreRuntimeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	private static ConnectionManager INSTANCE;

	private String url = "jdbc:mysql://localhost:3306/drugstore";
	private String user = "root";
	private String pass = "root";

	private ConnectionManager() {
		registerDriver();
	}
/*
	public int authenticateUser(String user, String pass) {
		User user;
		try {
			PreparedStatement ps = conn
					.prepareStatement("select readonly * from users where username = \""
							+ user + "\" AND " + "password = \"" + pass + "\"");
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				user = new User(result.getString(1), result.getString(2));
			}
			result.close();
			ps.close();
		} catch (SQLException sqle) {
			// do stuff on fail
		}
		return user;
	}
*/
	public static synchronized ConnectionManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionManager();
		}

		return INSTANCE;
	}
	
	public Connection getConnection(){
		try {
			//Class.forName(JDBC_DRIVER).newInstance();
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DrugStoreRuntimeException("Can not connect to database", e);
		}

	}

    private void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
