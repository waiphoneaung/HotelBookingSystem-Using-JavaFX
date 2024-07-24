package checkOut;

import java.io.IOException;
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

import guest.GuestUI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.LoginUI;
import report.ReportUI;
import room.RoomUI;
import staff.StaffUI;

public class CheckOutController implements Initializable {

	@FXML
	private TableView<CheckOut> CheckOutTable;
	@FXML
	private TableColumn<CheckOut, Integer> co1;

	@FXML
	private TableColumn<CheckOut, Integer> co2;

	@FXML
	private TableColumn<CheckOut, String> co3;

	@FXML
	private TableColumn<CheckOut, String> co4;

	@FXML
	private TableColumn<CheckOut, String> co5;

	@FXML
	private TableColumn<CheckOut, String> co6;

	@FXML
	private TableColumn<CheckOut, Double> co7;

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
	private Button cancelCheckOut;

	@FXML
	private Button checkOut;

	@FXML
	private TextField SearchCKRoom;

	@FXML
	void ActionCancelCheckOut(ActionEvent event) {
		CheckOutTable.getItems().clear();

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
		String loggedInUsername = getLoggedInUsername();
		String userRole = getUserRole(loggedInUsername);
		System.out.println("User role: " + userRole);
	}

	// Method to check if the logged-in user is an admin
	private boolean isAdminUser() {

		String loggedInUsername = getLoggedInUsername();
		String userRole = getUserRole(loggedInUsername);
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

	@FXML
	void ActionCheckOut(ActionEvent event) {
		// Get the room number entered by the user
		String roomNumber = SearchCKRoom.getText().trim();

		// Check if the room number is empty
		if (roomNumber.isEmpty()) {
			new Alert(AlertType.ERROR, "Please enter a room number to check out", ButtonType.OK).show();
			return;
		}

		// Check if the room number is a valid integer
		if (!isInteger(roomNumber)) {
			new Alert(AlertType.ERROR, "Invalid room number", ButtonType.OK).show();
			return;
		}

		int roomId = Integer.parseInt(roomNumber);

		// Get the list of check-out details for the given room
		List<CheckOut> checkOuts = getCheckOutGuest(roomId);

		// Check if there are no check-out details found for this room
		if (checkOuts.isEmpty()) {
			new Alert(AlertType.INFORMATION, "No check-out details found for this room", ButtonType.OK).show();
			return;
		}

		// Clear existing items in the TableView
		CheckOutTable.getItems().clear();

		// Initialize TableView columns
		co1.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
		co2.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		co3.setCellValueFactory(new PropertyValueFactory<>("name"));
		co4.setCellValueFactory(new PropertyValueFactory<>("nrc"));
		co5.setCellValueFactory(new PropertyValueFactory<>("CheckInDate"));
		co6.setCellValueFactory(new PropertyValueFactory<>("CheckOutDate"));
		co7.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		// Add new items to the TableView
		CheckOutTable.getItems().addAll(checkOuts);
		// Handle double-click event to initiate checkout process
		CheckOutTable.setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
				// Get the selected item from the table
				CheckOut selectedCheckOut = CheckOutTable.getSelectionModel().getSelectedItem();

				// Check if an item is selected
				if (selectedCheckOut == null) {
					new Alert(AlertType.ERROR, "Please select a row to view receipt", ButtonType.OK).show();
					return;
				}
				openReceipt(selectedCheckOut);
			}
		});
	}

	@FXML
	void ActionShowCheckOutRoom(ActionEvent event) {

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
		// ----------------Date and Time ---------

		updateDateTime();
		Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), e -> updateDateTime()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		lblUsername.setText(loggedInUsername);

		SearchCKRoom.setOnAction(event -> ActionCheckOut(event));

	}

	// Method to delete the booking record associated with the given room ID
	public boolean deleteBookingRecord(int roomId) {
		String sql = "DELETE FROM booking WHERE roomID = ?";
		try (Connection con = Database.DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql)) {
			psmt.setInt(1, roomId);
			int rowsAffected = psmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Utility method to check if a string is an integer
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static List<CheckOut> getCheckOutGuest(int roomId) {
		String sql = "SELECT b.bookingID, b.roomID, g.name, g.nrc, g.phone, g.email, b.checkinDate, b.checkoutDate, b.numberOfDay, r.pricePerNight, r.roomCapacity, b.totalPrice FROM booking b JOIN room r ON r.roomID = b.roomID JOIN guest g ON g.guestID = b.guestID WHERE b.roomID = ?;";
		List<CheckOut> list = new ArrayList<>();
		try (Connection con = Database.DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql)) {
			psmt.setInt(1, roomId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				CheckOut s = new CheckOut(rs.getInt("bookingID"), rs.getInt("roomID"), rs.getString("name"),
						rs.getString("nrc"), rs.getString("phone"), rs.getString("email"), rs.getString("checkinDate"),
						rs.getString("checkoutDate"), rs.getString("numberOfDay"), rs.getDouble("pricePerNight"),
						rs.getString("roomCapacity"), rs.getDouble("totalPrice"));
				list.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Method to open the receipt window with the selected CheckOut data
	private void openReceipt(CheckOut selectedCheckOut) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));
			Parent root = loader.load();

			// Access the controller and call a method to pass data
			ReceiptController controller = loader.getController();
			controller.initData(selectedCheckOut);

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}