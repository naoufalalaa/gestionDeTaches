package metier;



import java.sql.Connection;
import java.sql.DriverManager;

public class SignletonConnectionDB_REMOTE {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11467548","sql11467548","vDclK7E1Ei");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static Connection getConnection() {
        return connection;
    }
}



