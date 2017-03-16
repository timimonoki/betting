package database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public class DBConnection {
    private static DBConnection istance = null;
    private Connection connection;

    private DBConnection() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("database.properties"));

            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String dbURL = props.getProperty("URL");

            connection = DriverManager.getConnection(dbURL, user, password);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (istance == null) {
            istance = new DBConnection();
        }
        return istance;
    }
}
