
package NCafeManagement;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    private Connection connection = null;
    public Connection setConnection() {
        try{
            String url = "jdbc:mysql://localhost:3306/warned_db";
            String user = "root";
            String pass = "";
            String db = "warned_db";
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, pass);
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
        }return connection;
    }
}