package checkIn;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import checkOut.CheckoutUI;
import guest.Guest;
import guest.GuestUI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginUI;
import report.ReportUI;
import room.Room;
import room.RoomUI;
import staff.StaffUI;

import java.sql.*;

public class CheckInController implements Initializable {

	@FXML
	private TextField AddressField;

	@FXML
	private DatePicker CheckInDatePicker;

	@FXML
	private DatePicker CheckOutDatePicker;

	@FXML
	private TextField CityField;

	@FXML
	private Button ClearButton;

	@FXML
	private RadioButton DoubleCapacity;

	@FXML
	private TextField EmailField;

	@FXML
	private Button FindBtn;

	@FXML
	private Hyperlink LogoutLink;

	@FXML
	private TextField NameField;

	@FXML
	private RadioButton NormalType;

	@FXML
	private TextField NrcField;

	@FXML
	private TextField NumberOfDay;

	@FXML
	private TextField PhoneField;

	@FXML
	private TextField RoomNumber;

	@FXML
	private RadioButton SingleCapacity;

	@FXML
	private Button SubmitButton;

	@FXML
	private RadioButton TripleCapacity;

	@FXML
	private RadioButton VipType;

	@FXML
	private Label address_error;

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
	private Label city_error;

	@FXML
	private RadioButton economyType;

	@FXML
	private Label name_error;

	@FXML
	private Label email_error;

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
	private Label nrc_error;

	@FXML
	private Label phone_error;

	@FXML
	private Label timeLabel;

	@FXML
	private TextField PaymentField;

	@FXML
	private TextField TotalField;

	@FXML
	private TextField PriceField;

	@FXML
	private Button Register;

	@FXML
	private Button Ck_btn_submit;

	@FXML
	private Button btnSearch;

	@FXML
	private AnchorPane RoomCheck;

	@FXML
	private AnchorPane CustomerInfo;

	@FXML
	private TextField CkCheckInDate;

	@FXML
	private TextField CkCheckOutDate;

	@FXML
	private TextField CkNumberOfDate;

	@FXML
	private TextField CkRoomCapacity;

	@FXML
	private TextField CkRoomNumber;

	@FXML
	private TextField CkRoomType;

	@FXML
	private Button Ck_btn_clear;

	@FXML
	private TableView<FreeRoomSearch> FreeRoomTable;

	@FXML
	private TableColumn<FreeRoomSearch, Integer> f1;

	@FXML
	private TableColumn<FreeRoomSearch, String> f2;

	@FXML
	private TableColumn<FreeRoomSearch, String> f3;

	@FXML
	private TableColumn<FreeRoomSearch, Double> f4;

	@FXML
	private ImageView imgAddStaff;

	FreeRoomSearch update = null;

	private Guest s;

	private Node btnAddStaff;

	private static LoginUI loginUI;

	public static void setLoginUI(LoginUI ui) {
		loginUI = ui;
	}

	@FXML
	void btnAddStaff(MouseEvent event) {
		if (!isAdminUser()) {
			showAlert("Access Denied", "You do not have permission to access this feature.");
		} else {
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
	void back(ActionEvent event) {
		RoomCheck.setVisible(true);
		CustomerInfo.setVisible(false);
	}

	@FXML
	void clearAction(ActionEvent event) {

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

		Optional<ButtonType> result = confirmationAlert.showAndWait();

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
	void FindAction(ActionEvent event) {

	}

	@FXML
	void NumberOfDayAction(ActionEvent event) {
		LocalDate checkInDate = CheckInDatePicker.getValue();
		String numOfDaysText = NumberOfDay.getText().trim();
		if (!numOfDaysText.isEmpty()) {
			try {
				long numOfDays = Long.parseLong(numOfDaysText);
				if (checkInDate != null) {
					LocalDate checkOutDate = checkInDate.plusDays(numOfDays);
					CheckOutDatePicker.setValue(checkOutDate);
				}
			} catch (NumberFormatException ex) {
				NumberOfDay.setText("");
			}

		}
	}

	@FXML
	void check_out_Action(ActionEvent event) {
		LocalDate checkInDate = CheckInDatePicker.getValue();
		LocalDate checkOutDate = CheckOutDatePicker.getValue();
		if (checkInDate != null && checkOutDate != null && checkOutDate.isAfter(checkInDate)) {
			long numOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
			NumberOfDay.setText(String.valueOf(numOfDays));
		}
	}

	@FXML
	void check_in_Action(ActionEvent event) {

		LocalDate checkInDate = CheckInDatePicker.getValue();
		LocalDate checkOutDate = CheckOutDatePicker.getValue();
		if (checkInDate != null && checkOutDate != null && checkOutDate.isAfter(checkInDate)) {
			long numOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
			NumberOfDay.setText(String.valueOf(numOfDays));
		}
	}

	/*
	 * @FXML void searchRooms(ActionEvent event) { if(!getRoomTypeValue().isEmpty()
	 * && !getRoomCapacityValue().isEmpty() && CheckInDatePicker.getValue() != null
	 * && CheckOutDatePicker.getValue() != null && NumberOfDay.getText() != null ) {
	 * FreeRoomTable.getItems().clear(); List<FreeRoomSearch> FreeRooms =
	 * RoomSearch(); System.out.println(FreeRooms.size());
	 * 
	 * for (int i = 0; i < FreeRooms.size(); i++) {
	 * System.out.println("Free Rooms: " +FreeRooms.get(i)); }
	 * FreeRoomTable.getItems().addAll(FreeRooms);
	 * 
	 * }else { new
	 * Alert(AlertType.ERROR,"Please Fill All The Fields To Search Free Room!"
	 * ,ButtonType.OK).show(); } }
	 */
	@FXML
	void searchRooms(ActionEvent event) {
		LocalDate checkInDate = CheckInDatePicker.getValue();
		LocalDate checkOutDate = CheckOutDatePicker.getValue();

		if (checkInDate == null || checkOutDate == null || NumberOfDay.getText().isEmpty()) {
			new Alert(AlertType.ERROR, "Please fill in all the fields to search for free rooms!", ButtonType.OK).show();
		} else if (checkInDate.isAfter(checkOutDate)) {
			new Alert(AlertType.ERROR, "Check-in date cannot be after check-out date!", ButtonType.OK).show();
		} else if (getRoomTypeValue().isEmpty() || getRoomCapacityValue().isEmpty()) {
			new Alert(AlertType.ERROR, "Please select room type and capacity to search for free rooms!", ButtonType.OK)
					.show();
		} else {
			FreeRoomTable.getItems().clear();
			List<FreeRoomSearch> freeRooms = RoomSearch();
			System.out.println(freeRooms.size());

			for (int i = 0; i < freeRooms.size(); i++) {
				System.out.println("Free Rooms: " + freeRooms.get(i));
			}
			FreeRoomTable.getItems().addAll(freeRooms);
		}
	}

	@FXML
	void RegisterAction(ActionEvent event) {

		if (!RoomNumber.getText().isEmpty() && CheckInDatePicker.getValue() != null) {
			SwitchCustomerInfo();
			getRoomData();
			double totalprice = Integer.parseInt(CkNumberOfDate.getText()) * Double.parseDouble(PriceField.getText());
			TotalField.setText(totalprice + "");
		} else {
			new Alert(AlertType.ERROR, "Please Choose Room Number!", ButtonType.OK).show();
		}

	}

	@FXML
	void Action_Ck_btn_submit(ActionEvent event) {
		if (!NameField.getText().isEmpty() && !PhoneField.getText().isEmpty() && !NrcField.getText().isEmpty()
				&& !EmailField.getText().isEmpty() && !AddressField.getText().isEmpty()
				&& !CityField.getText().isEmpty() && !CkRoomNumber.getText().isEmpty()) {

			if (!validateName()) {
				new Alert(AlertType.ERROR, "Please enter a valid name.").showAndWait();
				return;
			}
			if (!validatePhone()) {
				new Alert(AlertType.ERROR, "Please enter a valid phone number.").showAndWait();
				return;
			}
			if (!validateEmail()) {
				new Alert(AlertType.ERROR, "Please enter a valid email address.").showAndWait();
				return;
			}

			// Create a confirmation alert
			Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
			confirmationAlert.setTitle("Confirmation");
			confirmationAlert.setHeaderText("Are you sure you want to submit?");
			confirmationAlert.setContentText("Once submitted, the guest information will be saved.");

			Optional<ButtonType> result = confirmationAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				// Insert guest information
				int newGuestID = insertGuest();

				if (newGuestID != -1) {

					insertBooking(newGuestID);

					// Display success message
					new Alert(AlertType.INFORMATION, "Success").showAndWait();

					// Switch back to the RoomCheck pane
					RoomCheck.setVisible(true);
					CustomerInfo.setVisible(false);

					// Clear all text fields
					clearTextFields();
					FreeRoomTable.refresh();

				} else {
					// Display error message if guest insertion failed
					new Alert(AlertType.ERROR, "Failed to insert guest information").showAndWait();
				}
			}
		} else {
			new Alert(AlertType.ERROR, "Please fill all the field to Register").showAndWait();
		}
	}

	private void clearTextFields() {
		NameField.clear();
		PhoneField.clear();
		NrcField.clear();
		EmailField.clear();
		AddressField.clear();
		CityField.clear();
		RoomNumber.clear();
		// CheckInDatePicker.setValue(null);
		CheckOutDatePicker.setValue(null);
		NumberOfDay.clear();
		// PaymentField.clear();
		TotalField.clear();
		PriceField.clear();
		economyType.setSelected(true);
		SingleCapacity.setSelected(true);

		// Clear TableView
		FreeRoomTable.getItems().clear();

	}

	private int insertGuest() {
		String sql = "INSERT INTO guest(roomID,name, phone, nrc, address, email, city) VALUES (?,?,?,?,?,?,?)";
		try (Connection con = Database.DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			psmt.setInt(1, Integer.parseInt(CkRoomNumber.getText()));
			psmt.setString(2, NameField.getText());
			psmt.setString(3, PhoneField.getText());
			psmt.setString(4, NrcField.getText());
			psmt.setString(5, AddressField.getText());
			psmt.setString(6, EmailField.getText());
			psmt.setString(7, CityField.getText());
			psmt.executeUpdate();

			ResultSet generatedKeys = psmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Return -1 if insertion failed
	}

	private void insertBooking(int guestID) {
		String addBooking = "INSERT INTO booking (guestID, roomID, checkinDate, checkoutDate, numberOfDay, totalPrice) VALUES (?,?,?,?,?,?)";
		try (Connection con = Database.DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(addBooking)) {
			psmt.setInt(1, guestID);
			psmt.setInt(2, Integer.parseInt(CkRoomNumber.getText()));
			psmt.setString(3, CkCheckInDate.getText());
			psmt.setString(4, CkCheckOutDate.getText());
			psmt.setString(5, CkNumberOfDate.getText());
			psmt.setDouble(6, Double.parseDouble(TotalField.getText()));
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void Action_Ck_btn_clear(ActionEvent event) {
		NameField.clear();
		PhoneField.clear();
		NrcField.clear();
		EmailField.clear();
		AddressField.clear();
		CityField.clear();
	}

	// @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		FreeRoomTable.setOnMouseClicked(e -> {
			if (e.getClickCount() >= 1) {
				update = FreeRoomTable.getSelectionModel().getSelectedItem();
				if (update != null) {
					RoomNumber.setText(update.getRoomId() + "");
					PriceField.setText(update.getPricePerNight() + "");
				}
			}
		});

		// ----------------Date and Time ---------
		CheckInDatePicker.setValue(LocalDate.now());
		CheckInDatePicker.setEditable(false);
		CheckOutDatePicker.setEditable(false);
		RoomNumber.setEditable(false);
		CkRoomNumber.setEditable(false);
		CkRoomType.setEditable(false);
		CkRoomCapacity.setEditable(false);
		CkCheckInDate.setEditable(false);
		CkCheckOutDate.setEditable(false);
		CkNumberOfDate.setEditable(false);
		PriceField.setEditable(false);
		TotalField.setEditable(false);
		NumberOfDay.setEditable(false);

		CheckInDatePicker.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				setDisable(date.isBefore(LocalDate.now()));
			}
		});
		CheckOutDatePicker.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				setDisable(date.isBefore(LocalDate.now()));
			}
		});

		updateDateTime();
		Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), e -> updateDateTime()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		ToggleGroup RoomTypeToggleGroup = new ToggleGroup();
		economyType.setToggleGroup(RoomTypeToggleGroup);
		NormalType.setToggleGroup(RoomTypeToggleGroup);
		VipType.setToggleGroup(RoomTypeToggleGroup);
		economyType.setSelected(true);

		// Create a ToggleGroup for room capacities
		ToggleGroup roomCapacityToggleGroup = new ToggleGroup();
		SingleCapacity.setToggleGroup(roomCapacityToggleGroup);
		DoubleCapacity.setToggleGroup(roomCapacityToggleGroup);
		TripleCapacity.setToggleGroup(roomCapacityToggleGroup);
		SingleCapacity.setSelected(true);

		f1.setCellValueFactory(new PropertyValueFactory<>("roomId"));
		f2.setCellValueFactory(new PropertyValueFactory<>("roomType"));
		f3.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
		f4.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));

		lblUsername.setText(loggedInUsername);

		// CheckOutDatePicker.setOnAction(event -> searchRooms(event));

	}

	public void SwitchCustomerInfo() {
		RoomCheck.setVisible(false);
		CustomerInfo.setVisible(true);
	}

	private void updateDateTime() {
		// Get the current date and time
		LocalDateTime currentDateTime = LocalDateTime.now();
		// Format the current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		// Update the date and time label
		timeLabel.setText(formattedDateTime);

	} // -------------Input Error----------
	// ======== input error ========

	static boolean Handle_NameField = true;

	@FXML
	void Handle_NameField(KeyEvent event) {

		if (Handle_NameField) {
			if ((NameField.getText()).matches("[A-Za-z\\s]{2,}")) {
				name_error.setText("valid");
				name_error.setTextFill(Color.GREEN);
			} else {
				name_error.setText("Name must contain only letters");
				name_error.setTextFill(Color.RED);
				Handle_NameField = true;
			}
		}

	}

	private boolean validateName() {
		// Validate name format (allowing only letters and spaces)
		return NameField.getText().matches("[A-Za-z\\s]{2,}");
	}

	static boolean Handle_PhoneField = true;

	@FXML
	void Handle_PhoneField(KeyEvent event) {
		if (Handle_PhoneField) {
			if ((PhoneField.getText()).matches("[\\d\\s]+")) {
				phone_error.setText("valid");
				phone_error.setTextFill(Color.GREEN);
			} else {
				phone_error.setText("Phone number must be digits only");
				phone_error.setTextFill(Color.RED);
			}
		}
	}

	private boolean validatePhone() {
		// Validate phone format (allowing only digits and spaces)
		return PhoneField.getText().matches("[\\d\\s]+");
	}

	static boolean Handle_EmailField = true;

	@FXML
	void Handle_EmailField(KeyEvent event) {

		if (Handle_EmailField) {
			String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			if ((EmailField.getText()).matches(EMAIL_REGEX)) {
				email_error.setText("valid");
				email_error.setTextFill(Color.GREEN);
			} else {
				email_error.setText("Must be at this form : user@domain.com");
				email_error.setTextFill(Color.RED);
				Handle_EmailField = true;
			}
		}
	}

	private boolean validateEmail() {
		String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return EmailField.getText().matches(EMAIL_REGEX);
	}

	public static String newGuestID() {
		String sql = "SELECT MAX(guestID)+1 FROM guest";
		String newNo = "";
		try (Connection con = Database.DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql);) {
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				newNo = rs.getString(1);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newNo;
	}

	public String getRoomTypeValue() {
		String RoomType = null;
		if (economyType.isSelected()) {
			RoomType = "ECONOMY";
		} else if (NormalType.isSelected()) {
			RoomType = "NORMAL";
		} else if (VipType.isSelected()) {
			RoomType = "VIP";
		}
		return RoomType;
	}

	public String getRoomCapacityValue() {
		String RoomCapacity = null;
		if (SingleCapacity.isSelected()) {
			RoomCapacity = "Single";
		} else if (DoubleCapacity.isSelected()) {
			RoomCapacity = "Double";
		} else if (TripleCapacity.isSelected()) {
			RoomCapacity = "Triple";
		}
		return RoomCapacity;
	}

	public List<FreeRoomSearch> RoomSearch() {
		// !CheckInDatePicker.getValue().isBefore(LocalDate.now())
		String sql = "SELECT roomID, roomtype, roomCapacity, pricePerNight FROM room WHERE "
				+ " roomtype = ? AND roomCapacity = ? "
				+ "AND roomID NOT IN (SELECT roomID FROM booking WHERE checkinDate < ? AND checkoutDate > ?)";
		ArrayList<FreeRoomSearch> list = new ArrayList<>();
		try (Connection con = Database.DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql);) {
			psmt.setString(1, getRoomTypeValue());
			psmt.setString(2, getRoomCapacityValue());
			psmt.setString(3, CheckOutDatePicker.getValue().toString());
			psmt.setString(4, CheckInDatePicker.getValue().toString());
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				FreeRoomSearch s = new FreeRoomSearch(rs.getInt("roomId"), rs.getString("roomtype"),
						rs.getString("roomCapacity"), rs.getDouble("pricePerNight"));
				list.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void getRoomData() {

		CkRoomNumber.setText(RoomNumber.getText());
		CkRoomType.setText(getRoomTypeValue());
		CkRoomCapacity.setText(getRoomCapacityValue());
		CkCheckInDate.setText(CheckInDatePicker.getValue().toString());
		CkCheckOutDate.setText(CheckOutDatePicker.getValue().toString());
		LocalDate checkInDate = CheckInDatePicker.getValue();
		LocalDate checkOutDate = CheckOutDatePicker.getValue();
		if (checkInDate != null && checkOutDate != null && checkOutDate.isAfter(checkInDate)) {
			long numOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
			CkNumberOfDate.setText(String.valueOf(numOfDays));
		}
	}
}