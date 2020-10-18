package basePackage;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection=ConnectionManager.connectionManager.getConnection();

            CLI cli=new CLI(connection);
            cli.showOptions();

            connection.close();

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
