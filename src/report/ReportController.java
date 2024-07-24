package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import login.LoginUI;
import room.RoomUI;
import staff.StaffUI;

public class ReportController implements Initializable {

	@FXML
	private TableView<Report> tblReport;

	@FXML
	private TableColumn<Report, String> colBookineID;

	@FXML
	private TableColumn<Report, Integer> colRoomID;

	@FXML
	private TableColumn<Report, Integer> colGuestID;

	@FXML
	private TableColumn<Report, String> colName;

	@FXML
	private TableColumn<Report, String> colNRC;

	@FXML
	private TableColumn<Report, String> colPhone;

	@FXML
	private TableColumn<Report, String> colCity;

	@FXML
	private TableColumn<Report, LocalDate> colCKin;

	@FXML
	private TableColumn<Report, LocalDate> colCKout;

	@FXML
	private TableColumn<Report, String> colRoomCapacity;

	@FXML
	private TableColumn<Report, String> colRoomType;

	@FXML
	private TableColumn<Report, Double> colNumberOfDay;

	@FXML
	private TableColumn<Report, Double> colTotalPrice;

	@FXML
	private Hyperlink LogoutLink;

	@FXML
	private Button btnCheckIn;

	@FXML
	private Button btnCheckOut;

	@FXML
	private Button btnGuest;

	@FXML
	private Button btnLogo;

	@FXML
	private Button exportReport;

	@FXML
	private Button btnReport;

	@FXML
	private Button btnRooms;

	@FXML
	private DatePicker dpEndDate;

	@FXML
	private DatePicker dpStartDate;

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
	private MenuItem m1;

	@FXML
	private MenuItem m2;

	@FXML
	private MenuItem m3;

	@FXML
	private MenuItem m4;

	@FXML
	private MenuItem m5;

	@FXML
	private MenuItem m6;

	@FXML
	private MenuItem allCapacity;

	@FXML
	private MenuItem allType;


	@FXML
	private MenuButton mbRoomCapacity;

	@FXML
	private MenuButton mbRoomType;

	@FXML
	private Label timeLabel;

	@FXML
	private TextField txfEroom;

	@FXML
	private TextField txfNroom;

	@FXML
	private TextField txfNumberOfBook;

	@FXML
	private TextField txfTotalIncome;

	@FXML
	private TextField txfVIProom;

	@FXML
	void btnAddStaff(MouseEvent event) {
		// Check if the logged-in user is an admin
		if (!isAdminUser()) {
			// Show an alert indicating that non-admin users cannot access this feature
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
		lblUsername.setText(loggedInUsername);


		allType.setOnAction(event -> {
			mbRoomType.setText("All");
			filterReport();

		});


		m1.setOnAction(event -> {
			mbRoomType.setText("ECONOMY");
			filterReport();

		});

		m2.setOnAction(event -> {
			mbRoomType.setText("NORMAL");
			filterReport();

		});

		m3.setOnAction(event -> {
			mbRoomType.setText("VIP");
			filterReport();

		});

		allCapacity.setOnAction(event -> {
			mbRoomCapacity.setText("All");
			filterReport();

		});


		m4.setOnAction(event -> {
			mbRoomCapacity.setText("Single");
			filterReport();

		});

		m5.setOnAction(event -> {
			mbRoomCapacity.setText("Double");
			filterReport();

		});

		m6.setOnAction(event -> {
			mbRoomCapacity.setText("Triple");
			filterReport();

		});




		// Set default start date to 16/4/2024
		dpStartDate.setValue(LocalDate.now().withDayOfMonth(1));
		dpStartDate.setEditable(false);

		// Set end date to current date
		dpEndDate.setValue(LocalDate.now());
		dpEndDate.setEditable(false);
		txfTotalIncome.setEditable(false);

		// Set default room type and room capacity
		mbRoomType.setText("All");
		mbRoomCapacity.setText("All");

		// Set up table columns
		colBookineID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
		colRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		colGuestID.setCellValueFactory(new PropertyValueFactory<>("guestID"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colNRC.setCellValueFactory(new PropertyValueFactory<>("nrc"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
		colCKin.setCellValueFactory(new PropertyValueFactory<>("ckinDate"));
		colCKout.setCellValueFactory(new PropertyValueFactory<>("ckoutDate"));
		colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomtype"));
		colRoomCapacity.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
		colNumberOfDay.setCellValueFactory(new PropertyValueFactory<>("numberofDay"));
		colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalprice"));

		// Add event handlers for date pickers
		dpStartDate.setOnAction(event -> {
			filterReport();
		});

		dpEndDate.setOnAction(event -> {
			filterReport();
		});


		// Initially populate the table without any filters
		filterReport();

	}


	private void filterReport() {
		System.out.println("Filtering report...");
		System.out.println(dpStartDate.getValue().toString());
		System.out.println(dpEndDate.getValue().toString());
		System.out.println(mbRoomCapacity.getText());
		System.out.println(mbRoomType.getText());
		System.out.println(calculateTotalPrice());
		System.out.println(calculateTotalNumberOfEconomyRooms());
		try {
			// Clear the table
			tblReport.getItems().clear();

			// Establish database connection (Assuming you have a method to get the connection)
			Connection conn = Database.DBConnection.getConnection();

			// Prepare the SQL statement with placeholders
			String sql = "SELECT b.bookingID, b.roomID, r.roomType, r.roomcapacity, b.guestID, g.name, g.nrc, g.phone, g.city, b.checkinDate, b.checkoutDate, b.numberOfDay, b.totalPrice " +
					"FROM booking b " +
					"JOIN guest g ON b.guestID = g.guestID " +
					"JOIN room r ON b.roomID = r.roomID " +
					"WHERE b.checkinDate >= ? " +
					"AND b.checkoutDate <= ? ";

			// Append room type and room capacity conditions if not "All"
			if (!mbRoomType.getText().equals("All")) {
				sql += "AND r.roomType = ? ";
			} else {
				// If room type is "All", add conditions for all room types
				sql += "AND r.roomType IN ('ECONOMY', 'NORMAL', 'VIP') ";
			}
			if (!mbRoomCapacity.getText().equals("All")) {
				sql += "AND r.roomcapacity = ? ";
			} else {
				// If room type is "All", add conditions for all room types
				sql += "AND r.roomCapacity IN ('Single', 'Double', 'Triple') ";
			}

			// Create the prepared statement
			PreparedStatement psmt = conn.prepareStatement(sql);

			// Set the parameters based on the conditions in your SQL query
			psmt.setString(1, dpStartDate.getValue().toString()); // Start date
			psmt.setString(2, dpEndDate.getValue().toString());   // End date
			int paramIndex = 3;
			if (!mbRoomType.getText().equals("All")) {
				psmt.setString(paramIndex++, mbRoomType.getText());
			}
			if (!mbRoomCapacity.getText().equals("All")) {
				psmt.setString(paramIndex, mbRoomCapacity.getText());
			}

			// Execute the query
			ResultSet rs = psmt.executeQuery();


			// Populate the table view with the retrieved data
			while (rs.next()) {
				Report report = new Report(
						rs.getInt("guestID"),
						rs.getInt("roomID"),
						rs.getString("bookingID"),
						rs.getString("name"),
						rs.getString("nrc"),
						rs.getString("phone"),
						rs.getString("city"),
						rs.getDate("checkinDate").toLocalDate(),
						rs.getDate("checkoutDate").toLocalDate(),
						rs.getString("roomType"),
						rs.getString("roomcapacity"),
						rs.getInt("numberOfDay"),
						rs.getDouble("totalPrice")
						);
				tblReport.getItems().add(report);
			}
			calculateTotalPrice();
			txfTotalIncome.setText(calculateTotalPrice()+"");

			int totalNumberOfRows = calculateTotalNumberOfRows();
			int totalNumberOfVIPRooms = calculateTotalNumberOfVIPRooms();
			int totalNumberOfNormalRooms = calculateTotalNumberOfNormalRooms();
			int totalNumberOfEconomyRooms = calculateTotalNumberOfEconomyRooms();

			txfNumberOfBook.setText(String.valueOf(totalNumberOfRows));
			txfVIProom.setText(String.valueOf(totalNumberOfVIPRooms));
			txfNroom.setText(String.valueOf(totalNumberOfNormalRooms));
			txfEroom.setText(String.valueOf(totalNumberOfEconomyRooms));
			
			txfNumberOfBook.setEditable(false);
			txfVIProom.setEditable(false);
			txfNroom.setEditable(false);
			txfEroom.setEditable(false);


			// Close the connections
			rs.close();
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// Display an error message if an exception occurs
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Failed to fetch data from the database!" + e.getMessage());
			alert.showAndWait();
		}
	}


	@FXML
    void exportReportAction(ActionEvent event) {
        // Display a confirmation dialog before exporting
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Export Report");
        confirmationAlert.setHeaderText("Are you sure you want to export the report?");
        confirmationAlert.setContentText("Click OK to export the report as CSV.");

        // Create a TextArea to show the CSV preview
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        // Set the CSV preview content
        textArea.setText(getCSVPreview());

        // Set the dialog content
        GridPane previewPane = new GridPane();
        previewPane.setMaxWidth(Double.MAX_VALUE);
        previewPane.add(textArea, 0, 0);

        // Set the dialog's expandable content
        confirmationAlert.getDialogPane().setExpandableContent(previewPane);

        // Show the confirmation dialog and wait for user response
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        // If the user confirms the export, proceed to export the report
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Export the report as CSV
            exportReportAsCSV(tblReport.getItems());
        }
    }

    private String getCSVPreview() {
        // Define the CSV file header
        String csvHeader = "Booking ID,Room ID,Guest ID,Name,NRC,Phone,City,Check-in Date,Check-out Date,Room Type,Room Capacity,Number of Days,Total Price\n";

        // Create a StringBuilder to construct the CSV preview content
        StringBuilder csvPreview = new StringBuilder();
        csvPreview.append(csvHeader);

        // Append sample report data to the CSV preview content
        // Here you can append some sample data to give the user an idea of the CSV format
        // For example:
      
        return csvPreview.toString();
    }

    private void exportReportAsCSV(List<Report> reportData) {
        // Create a FileChooser to allow the user to choose where to save the CSV file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("hotel_report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".csv");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());

        // If the user cancels the file chooser dialog, return
        if (file == null) {
            return;
        }

        // Initialize variables for totals
        int totalBookings = reportData.size();
        int totalRooms = 0;
        int totalDaysStayed = 0;
        double totalPriceSum = 0.0;

        // Write the report data to the CSV file
        try (FileWriter writer = new FileWriter(file)) {
        	writer.write("Start Date:," + dpStartDate.getValue().toString() + "\n");
            writer.write("End Date:," + dpEndDate.getValue().toString() + "\n");
            // Write the CSV header
            writer.write("Booking ID,Room ID,Guest ID,Name,NRC,Phone,City,Check-in Date,Check-out Date,Room Type,Room Capacity,Number of Days,Total Price\n");

            // Write each report entry to the CSV file
            for (Report report : reportData) {
                writer.write(report.getBookingID() + "," +
                        report.getRoomID() + "," +
                        report.getGuestID() + "," +
                        report.getName() + "," +
                        report.getNrc() + "," +
                        report.getPhone() + "," +
                        report.getCity() + "," +
                        report.getCkinDate() + "," +
                        report.getCkoutDate() + "," +
                        report.getRoomtype() + "," +
                        report.getRoomCapacity() + "," +
                        report.getNumberofDay() + "," +
                        report.getTotalprice() + "\n");

                // Accumulate totals
                totalRooms++;
                totalDaysStayed += report.getNumberofDay();
                totalPriceSum += report.getTotalprice();
            }

            // Write the sum of total price and other information at the end of the CSV file
            writer.write("\n");
            writer.write("Total Bookings:," + totalBookings + "\n");
            writer.write("Total Rooms:," + totalRooms + "\n");
            writer.write("Total Number of Days Stayed:," + totalDaysStayed + "\n");
            writer.write("Total Price Sum:," + totalPriceSum + "\n");

            // Show an alert indicating successful export
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Export Successful");
            successAlert.setHeaderText(null);
            successAlert.setContentText("The report has been successfully exported as CSV.");
            successAlert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an alert if an error occurs during export
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Export Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("An error occurred while exporting the report: " + e.getMessage());
            errorAlert.showAndWait();
        }
    }


	@FXML
	void dpEndDate(ActionEvent event) {

	}

	@FXML
	void dpStartDate(ActionEvent event) {

	}

	@FXML
	void goHome(ActionEvent event) {

	}
	@FXML
	void mbRoomCapacityAction(MouseEvent event) {
		filterReport();
	}


	@FXML
	void mbRoomTypeAction(MouseEvent event) {
		filterReport();
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
	void allType(ActionEvent event) {
		mbRoomCapacity.setText("All"); 
		mbRoomCapacity.getItems().get(0).fire();
	}


	@FXML
	void m1(ActionEvent event) {
		mbRoomCapacity.setText("Single"); // Set the text to "Single"
		mbRoomCapacity.getItems().get(0).fire(); 

	}

	@FXML
	void m2(ActionEvent event) {
		mbRoomCapacity.setText("Double"); // Set the text to "Double"
		mbRoomCapacity.getItems().get(0).fire(); 
	}

	@FXML
	void m3(ActionEvent event) {
		mbRoomCapacity.setText("Triple"); // Set the text to "Triple"
		// Set the first item as selected
		mbRoomCapacity.getItems().get(0).fire(); // This triggers the action of the first item
	}

	@FXML
	void allCapacityAction(ActionEvent event) {
		mbRoomType.setText("All"); 
		mbRoomType.getItems().get(0).fire(); // This triggers the action of the first item
	}


	@FXML
	void m4(ActionEvent event) {
		mbRoomType.setText("ECONOMY"); // Set the text to "Economy"
		// Set the first item as selected
		mbRoomType.getItems().get(0).fire(); // This triggers the action of the first item
	}

	@FXML
	void m5(ActionEvent event) {
		mbRoomType.setText("NORMAL"); // Set the text to "Normal"
		// Set the first item as selected
		mbRoomType.getItems().get(0).fire(); // This triggers the action of the first item
	}

	@FXML
	void m6(ActionEvent event) {
		mbRoomType.setText("VIP"); 
		mbRoomCapacity.getItems().get(0).fire(); 
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

	private double calculateTotalPrice() {
		double totalPrice = 0.0;
		for (Report report : tblReport.getItems()) {
			totalPrice += report.getTotalprice();
		}
		return totalPrice;

	}
	private int calculateTotalNumberOfRows() {
		return tblReport.getItems().size();
	}

	private int calculateTotalNumberOfVIPRooms() {
		int totalNumberOfVIPRooms = 0;
		for (Report report : tblReport.getItems()) {
			if (report.getRoomtype().equals("VIP")) {
				totalNumberOfVIPRooms++;
			}
		}
		return totalNumberOfVIPRooms;
	}

	private int calculateTotalNumberOfNormalRooms() {
		int totalNumberOfNormalRooms = 0;
		for (Report report : tblReport.getItems()) {
			if (report.getRoomtype().equals("NORMAL")) {
				totalNumberOfNormalRooms++;
			}
		}
		return totalNumberOfNormalRooms;
	}

	private int calculateTotalNumberOfEconomyRooms() {
		int totalNumberOfEconomyRooms = 0;
		for (Report report : tblReport.getItems()) {
			if (report.getRoomtype().equals("ECONOMY")) {
				totalNumberOfEconomyRooms++;
			}
		}
		return totalNumberOfEconomyRooms;
	}




}
