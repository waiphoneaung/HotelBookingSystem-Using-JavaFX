package login;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import checkIn.CheckInController;
import checkIn.CheckInUI;
import checkOut.CheckOutController;
import guest.GuestController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import report.ReportController;
import room.RoomController;

public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txfPassword;

    @FXML
    private TextField txfUsername;

    // Declare loggedInUsername as static
    private static String loggedInUsername;

    // Getter for loggedInUsername
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
    


    @FXML
    void LoginAction(ActionEvent event) {
        String username = txfUsername.getText();
        String password = txfPassword.getText();

        // Hash the password before querying the database
        String hashedPassword = hashPassword(password);

        String sql = "SELECT * FROM staff WHERE username = ?";

        try (Connection con = Database.DBConnection.getConnection();
                PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                // User found, check password
                String storedPassword = rs.getString("password");
                if (checkPassword(hashedPassword, storedPassword)) {
                    System.out.println("Login successful!");
                    boolean isAdmin = rs.getBoolean("is_admin");
                    loggedInUsername = username;
                    openMainUI(isAdmin);
                } else {
                    showAlert("Login Failed", "Incorrect password.");
                }
            } else {
                // User not found
                showAlert("Login Failed", "User not found.");
            }

        } catch (SQLException e) {
            showAlert("Error", "An error occurred while trying to log in.");
            e.printStackTrace();
        }
    }

    private void openMainUI(boolean isAdmin) {
        // Set the logged-in username in the respective controllers
        CheckInController.setLoggedInUsername(loggedInUsername);
        CheckOutController.setLoggedInUsername(loggedInUsername);
        GuestController.setLoggedInUsername(loggedInUsername);
        RoomController.setLoggedInUsername(loggedInUsername);
        ReportController.setLoggedInUsername(loggedInUsername);
        try {
            CheckInUI g = new CheckInUI();
            g.start(LoginUI.mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkPassword(String hashedPassword, String storedPassword) {
        // Compare the hashed password with the stored password
        return hashedPassword.equals(storedPassword);
    }

    private void showAlert(String title, String message) {
        // Display an alert with the given title and message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 txfUsername.setOnAction(event -> LoginAction(event));
    	    
    	    // Add event listener to password field
    	    txfPassword.setOnAction(event -> LoginAction(event));
    }
}
