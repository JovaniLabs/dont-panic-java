// import the necessary classes for SQL connections and handling user input
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Hack {
    public static void main(String[] args) throws Exception {
        //create a scanner object to take input from the user
        Scanner scanner = new Scanner(System.in);

        //ask user for new password
        System.out.println("Enter the new password: ");

        //read user input and store it in password
        String password = scanner.nextLine();

        //connection to SQLite database named 'dont-panic.db'
        //'DriverManager.getConnection' is used to connect to the database
        Connection sqliteConnection = DriverManager.getConnection("jdbc:sqlite:dont-panic.db");

        //updating the password for 'admin' user.Use '?' as a plceholder that
        //will be replaced with actual password provided by the user
        String query = """
            UPDATE "users"
            SET "password" = ?
            WHERE "username" = 'admin';
        """;

        //use PreparedStatement to execute SQL queries with parameter
        PreparedStatement sqliteStatement = sqliteConnection.prepareStatement(query);

        //set first parameter (index 1 ) of the SQL statement to user's password
        sqliteStatement.setString(1, password);

        //execute update query to change password in the database
        sqliteStatement.executeUpdate();

        //close the database connection after the operation is complete
        sqliteConnection.close();

        //close the scanner object to release the associated resources
        scanner.close();
    }
}

