import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {
	public static Connection getConnection() {         
        Connection connection = null;         
		 
        try {             
            // Load the database driver.             
            Class.forName("oracle.jdbc.OracleDriver"); 
 
            // Set database connection parameters.  
            String url = ("jdbc:oracle:thin:@X.X.X.X:1521/testdev2" ); 
            String username = ("X");  
            String password = ("Y");             
             
            // Create the database connection.             
            connection = DriverManager.getConnection(url, username, password);                                                     
        }  
        catch (Exception ex) {              
            ex.printStackTrace();           
        } 
        return connection; 
    } 
}
