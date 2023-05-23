package project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DbHelper {
    private static Connection conn = null;

    public static Connection getConnection() throws Exception {
        if ( conn == null || conn.isClosed() ) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1242140733ma");
        }

        return conn;
    }

    public static void closeConnection() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static void migrate() throws Exception {
        String driverType = AppConfig.get().getDriverType();
        String autoIncFunc = driverType.equals("mysql") ? "AUTO_INCREMENT" : "AUTOINCREMENT";

        ArrayList<String> queries = new ArrayList<>();
        queries.add(
                String.format("CREATE TABLE IF NOT EXISTS users (id INTEGER NOT NULL PRIMARY KEY %s, name VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, salt VARCHAR(255) NOT NULL, role CHAR(1) NOT NULL, active BIT NOT NULL DEFAULT 1, createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)", autoIncFunc)
        );
        queries.add(
                String.format("CREATE TABLE IF NOT EXISTS team (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,name VARCHAR(30) NOT NULL,coach VARCHAR(30) NOT NULL,logo TEXT NULL,nr_players INTEGER NULL,createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)", autoIncFunc)
        );
        queries.add(
                String.format("CREATE TABLE IF NOT EXISTS game (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,ekipiI VARCHAR(30) NOT NULL,ekipiII VARCHAR(30) NOT NULL,hour VARCHAR(30) NULL,result VARCHAR(10) NULL,playsAt VARCHAR(50) NULL,createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)", autoIncFunc)
        );
        queries.add(
                String.format("CREATE TABLE IF NOT EXISTS standings (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, ekipi VARCHAR(30) NOT NULL, w INTEGER NOT NULL,  l INTEGER NOT NULL, streak VARCHAR(10) NOT NULL, last_10 VARCHAR(10) NOT NULL, win REAL NOT NULL)", autoIncFunc)
        );
        Connection conn = getConnection();
        for (String sql : queries) {
            conn.createStatement().executeUpdate(sql);
        }
    }
}
