import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean registerUser(String username, String email, String password) {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Registration failed.");
            e.printStackTrace();
            return false;
        }
    }

    public String loginUser(String email, String password) {
        String query = "SELECT username FROM users WHERE email = ? AND password = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getString("username");
            }

        } catch (Exception e) {
            System.out.println("Login failed.");
            e.printStackTrace();
        }

        return null;
    }
}