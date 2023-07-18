package JdbcConn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcConn {
    public  static Connection connection;
    public static Statement statement;

    static String url="jdbc:mysql://localhost:3306/fonebook_management";
    static String password= "1234567890";
    static String userName="root";


 public static void openConn(){
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection(url,userName,password);
     } catch (Exception e) {
         System.out.println("database connection failed");
     }
 }

}
