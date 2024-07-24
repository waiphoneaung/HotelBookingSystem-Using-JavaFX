package room;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import checkIn.CheckInUI;
import checkOut.CheckoutUI;
import guest.GuestUI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.LoginUI;
import report.ReportUI;
import staff.StaffUI;


public class RoomController implements Initializable {

    @FXML
    private Hyperlink LogoutLink;

    @FXML
    private Button btnCancelBooking;

    @FXML
    private Button btnCheckIn;

    @FXML
    private Button btnCheckOut;

    @FXML
    private Button btnGuest;

    @FXML
    private Button btnLogo;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnRoomBooking;

    @FXML
    private Button btnRooms;
    
    @FXML
    private TableView<Room> tblRoom;
    
    @FXML
    private TableColumn<Room, Integer> c1;

    @FXML
    private TableColumn<Room, String> c2;

    @FXML
    private TableColumn<Room, String> c3;

    @FXML
    private TableColumn<Room, String> c4;


    @FXML
	private Label lblUsername;
	
	  private static String loggedInUsername;

	    public static void setLoggedInUsername(String username) {
	        loggedInUsername = username;
	    }
	    private String getLoggedInUsername() {
	        return loggedInUsername;
	    }

    @FXML
    private Hyperlink linkBack;

    @FXML
    private Label timeLabel;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void goHome(ActionEvent event) {

    }

    @FXML
    void goTO_check_in(ActionEvent event) {
    	try {
			CheckInUI g = new CheckInUI();
			g.start(LoginUI.mainStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void goTO_check_out(ActionEvent event) {
    	try {
    		CheckoutUI g = new CheckoutUI();
			g.start(LoginUI.mainStage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

 
    @FXML
    void goTo_room_booking(ActionEvent event) {

    }

    @FXML
    void guest(ActionEvent event) {
    	try {
			GuestUI g = new GuestUI();
			g.start(LoginUI.mainStage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    @FXML
    void logout(ActionEvent event) {
	    // Create a confirmation alert
	    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
	    confirmationAlert.setTitle("Confirm Logout");
	    confirmationAlert.setHeaderText("Are you sure you want to log out?");
	    confirmationAlert.setContentText("Logging out will close the current session.");

	    // Show the confirmation alert and wait for user response
	    Optional<ButtonType> result = confirmationAlert.showAndWait();

	    // If the user confirms the logout, proceed to log out
	    if (result.isPresent() && result.get() == ButtonType.OK) {
	        // Close the current stage (window)
	        Stage stage = (Stage) LogoutLink.getScene().getWindow();
	        stage.close();

	        // Open the login page
	        LoginUI login = new LoginUI();
	        try {
	            Stage loginStage = new Stage();
	            login.start(loginStage); // Start the login page
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	

    @FXML
    void report(ActionEvent event) {
    	try {
			ReportUI g = new ReportUI();
			g.start(LoginUI.mainStage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    @FXML
    void rooms(ActionEvent event) {
    	try {
			RoomUI g = new RoomUI();
			g.start(LoginUI.mainStage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    @FXML
	void btnAddStaff(MouseEvent event) {
	    if (!isAdminUser()) {
	        showAlert("Access Denied", "You do not have permission to access this feature.");
	    } else {
	        // Proceed to open the staff UI
	        StaffUI staffUI = new StaffUI();
	        try {
	            Stage stage = new Stage();
	            staffUI.start(stage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    // Print the user's role
        String loggedInUsername = getLoggedInUsername(); // Assuming you have a method to get the logged-in username
        String userRole = getUserRole(loggedInUsername); // Assuming you have a method to retrieve the user's role
        System.out.println("User role: " + userRole);
	}
	

	// Method to check if the logged-in user is an admin
	 private boolean isAdminUser() {
	        // Implement your logic here to determine if the logged-in user is an admin
	        String loggedInUsername = getLoggedInUsername(); // Assuming you have a method to get the logged-in username
	        String userRole = getUserRole(loggedInUsername); // Assuming you have a method to retrieve the user's role
	        return userRole != null && userRole.equalsIgnoreCase("admin");
	    }
	 


	// Method to retrieve the user's role from the database
	 private String getUserRole(String username) {
		    System.out.println("Getting role for username: " + username);
		    String sql = "SELECT is_admin FROM staff WHERE username = ?";
		    try (Connection con = Database.DBConnection.getConnection();
		         PreparedStatement psmt = con.prepareStatement(sql)) {
		        psmt.setString(1, username);
		        ResultSet rs = psmt.executeQuery();
		        if (rs.next()) {
		            int isAdmin = rs.getInt("is_admin");
		            return (isAdmin == 1) ? "admin" : "user";
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    System.out.println("User role is null");
		    return null; // Return null if the user does not exist or an error occurs
		}


	// Method to show an alert
	private void showAlert(String title, String message) {
	    Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	private void updateDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        // Update the date and time label
        timeLabel.setText(formattedDateTime);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		updateDateTime();
        Timeline timeline = new Timeline(
            new KeyFrame(javafx.util.Duration.seconds(1), e -> updateDateTime())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
		List<Room> Rooms = getAllRoom();

        for (int i = 0; i < Rooms.size(); i++) {
            System.out.println("Room " + Rooms.get(i));
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        c2.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        c3.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
        c4.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        
        try {
            tblRoom.getItems().addAll(Rooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
		lblUsername.setText(loggedInUsername);

}
	public static List<Room> getAllRoom(){
		String sql = "SELECT * FROM room";
		ArrayList<Room> list = new ArrayList<>();
		try(Connection con = Database.DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);) {
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				Room s = new Room(rs.getInt("roomID"), rs.getString("roomType"), rs.getString("roomCapacity"), rs.getDouble("pricePerNight"));
				list.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}return list;
}
}

