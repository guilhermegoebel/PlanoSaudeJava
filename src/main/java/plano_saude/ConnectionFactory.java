package plano_saude;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionFactory {

        public Connection getConnection(){
                try{
                        return DriverManager
                                .getConnection("jdbc:mysql://localhost:3306/plano_saude?user=root&password=root");
                }catch(SQLException e){
                        throw new RuntimeException(e);
                }
        }
}
