package basePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private Connection connection;
    public static ConnectionManager connectionManager=new ConnectionManager();

    private ConnectionManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1234");

        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
