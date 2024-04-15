package login;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import checkIn.CheckInUI;
import guest.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txfPassword;

    @FXML
    private TextField txfUsername;

    @FXML
    void LoginAction(ActionEvent event) {
    	String username = txfUsername.getText();
    	String password = txfPassword.getText();
    	
    	String sql = "SELECT * FROM staff WHERE username = ? AND password = ?";
    	
    	try(Connection con = Database.DBConnection.getConnection();
    			PreparedStatement psmt = con.prepareStatement(sql);) {
    		psmt.setString(1, username);
            psmt.setString(2, password);
    			ResultSet rs = psmt.executeQuery();
    			
    			if (rs.next()) {
                    System.out.println("You can enter");
                    boolean isAdmin = rs.getBoolean("is_admin");
                    if (isAdmin) {
                    	try {
                			CheckInUI g = new CheckInUI();
                			g.start(LoginUI.mainStage);
                		} catch (Exception e) {
                			e.printStackTrace();
                		}
                    } else {
                        System.out.println("You are normal");
                    }
                } else {
                    // Invalid username or password
                    showAlert("Invalid Credentials", "The username or password is incorrect.");
                }
    	
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred while trying to log in.");
            }
    
    }
    
    private void openCheckInUI(boolean isAdmin) {
        try {
            Stage stage = (Stage) txfUsername.getScene().getWindow();
            stage.close();

            CheckInUI checkInOpen = new CheckInUI(); // Pass isAdmin parameter to CheckInUI constructor
            checkInOpen.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void showAlert(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	
		
	}

}
