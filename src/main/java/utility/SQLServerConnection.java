package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    public static Connection connectToSQLDb() {
        Connection connection = null;
        try {
            // Connection parameters
            String url = Constants.SQLDB_CONNECTION_STRING;
            String user =Constants.SQLDB_USERNAME ;
            String password = Constants.SQLDB_PASSWORD;

            // Load the SQLServer JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Connect to the database
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException e) {
            Log.error("Failure in connecting to SQL DB due to: "+e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                // Handle closing error
                e.printStackTrace();
            }
        }
    }

}

