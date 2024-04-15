package checkOut;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import login.LoginUI;
import report.ReportUI;
import room.RoomUI;

public class CheckoutController implements Initializable {
	
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
    
    
    /*
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
        
        List<CheckOut> checkOuts = getCheckOutGuest(roomId);
        if (checkOuts.isEmpty()) {
            new Alert(AlertType.INFORMATION, "No check-out details found for this room", ButtonType.OK).show();
        } else {
            // Clear existing items in the TableView
            CheckOutTable.getItems().clear();
            
            // Initialize TableView columns
            co1.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
            co2.setCellValueFactory(new PropertyValueFactory<>("roomID"));
            co3.setCellValueFactory(new PropertyValueFactory<>("name"));
            co4.setCellValueFactory(new PropertyValueFactory<>("nrc"));
            co5.setCellValueFactory(new PropertyValueFactory<>("CheckInDate"));
            co6.setCellValueFactory(new PropertyValueFactory<>("CheckOutDate"));
            co7.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
            
            // Add new items to the TableView
            try {
                CheckOutTable.getItems().addAll(checkOuts);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.println(checkOuts);
        }
        
        // Get the selected item from the table
        CheckOut selectedCheckOut = CheckOutTable.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedCheckOut == null) {
            new Alert(AlertType.ERROR, "Please select a row to check out", ButtonType.OK).show();
            return;
        }

        // Show confirmation dialog
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Are you sure you want to check out?");
        confirmAlert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Perform check-out process

            // Delete the booking record associated with the room
            if (!deleteBookingRecord(selectedCheckOut.getRoomID())) {
                new Alert(AlertType.ERROR, "Failed to delete booking record", ButtonType.OK).show();
                return;
            }

            // Update the room status
            if (!updateRoomStatus(selectedCheckOut.getRoomID())) {
                new Alert(AlertType.ERROR, "Failed to update room status", ButtonType.OK).show();
                return;
            }

            // Display a success message
            new Alert(AlertType.INFORMATION, "Checkout process completed successfully", ButtonType.OK).show();

            // Remove the selected item from the table
            CheckOutTable.getItems().remove(selectedCheckOut);
        }
        

        // Update the room status
        if (!updateRoomStatus(roomId)) {
            new Alert(AlertType.ERROR, "Failed to update room status", ButtonType.OK).show();
            return;
        }

        // Display a success message
        new Alert(AlertType.INFORMATION, "Checkout process completed successfully", ButtonType.OK).show();
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
        co7.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));

        // Add new items to the TableView
        CheckOutTable.getItems().addAll(checkOuts);

        // Show confirmation dialog
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Are you sure you want to check out?");
        confirmAlert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Perform check-out process

            // Delete the booking record associated with the room
            if (!deleteBookingRecord(roomId)) {
                new Alert(AlertType.ERROR, "Failed to delete booking record", ButtonType.OK).show();
                return;
            }

            // Update the room status
            if (!updateRoomStatus(roomId)) {
                new Alert(AlertType.ERROR, "Failed to update room status", ButtonType.OK).show();
                return;
            }

            // Display a success message
            new Alert(AlertType.INFORMATION, "Checkout process completed successfully", ButtonType.OK).show();

            // Clear the TableView
            CheckOutTable.getItems().clear();
        } else {
            // Display a message indicating that the checkout process was cancelled
            new Alert(AlertType.INFORMATION, "Checkout process cancelled", ButtonType.OK).show();
        }
    }

*/
  /*  
    @FXML
    void ActionCheckOut(ActionEvent event) {
        // Get the room number entered by the user
        String roomNumber = SearchCKRoom.getText().trim();
        
       // CheckOut selectedCheckOut = CheckOutTable.getSelectionModel().getSelectedItem();
       

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
        co7.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));

        // Add new items to the TableView
        CheckOutTable.getItems().addAll(checkOuts);
       
        CheckOut toDelete = CheckOutTable.getSelectionModel().getSelectedItem();
        CheckOutTable.setOnMouseClicked(e->{
        	if(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
        		 if(toDelete != null) {
        	        	//return ;
        	        
        	        	 // Show confirmation dialog
        	            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        	            confirmAlert.setTitle("Confirmation");
        	            confirmAlert.setHeaderText("Are you sure you want to check out?");
        	            confirmAlert.setContentText("This action cannot be undone.");

        	            Optional<ButtonType> result = confirmAlert.showAndWait();
        	            
        	            if (result.isPresent() && result.get() == ButtonType.OK) {
        	               // if(selectedCheckOut = CheckOutTable.getSelectionModel().getSelectedItem(){
        	            	// Perform check-out process

        	                // Get the selected item from the table
        	               // CheckOut selectedCheckOut = CheckOutTable.getSelectionModel().getSelectedItem();

        	                // Check if an item is selected
//        	                if (selectedCheckOut == null) {
//        	                    new Alert(AlertType.ERROR, "Please select a row to check out", ButtonType.OK).show();
//        	                    return;
//        	                }

        	                // Delete the booking record associated with the room
        	                if (!deleteBookingRecord(roomId)) {
        	                    new Alert(AlertType.ERROR, "Failed to delete booking record", ButtonType.OK).show();
        	                    return;
        	                }

        	                // Delete the guest record associated with the selected booking
        	                if (!deleteGuestRecord(roomId)) {
        	                    new Alert(AlertType.ERROR, "Failed to delete guest record", ButtonType.OK).show();
        	                    return;
        	                }

        	                // Update the room status
        	                if (!updateRoomStatus(roomId)) {
        	                    new Alert(AlertType.ERROR, "Failed to update room status", ButtonType.OK).show();
        	                    return;
        	                }
        	             // Add the data to the report table
        	              //  addDataToReport(selectedCheckOut);

        	                // Display a success message
        	                new Alert(AlertType.INFORMATION, "Checkout process completed successfully", ButtonType.OK).show();

        	                // Clear the TableView
        	                CheckOutTable.getItems().clear();
        	            } else {
        	                // Display a message indicating that the checkout process was cancelled
        	                new Alert(AlertType.INFORMATION, "Checkout process cancelled", ButtonType.OK).show();
        	            }	
        	        }	
        	}
        });
       
       
    }

    */
    
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
        co7.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));

        // Add new items to the TableView
        CheckOutTable.getItems().addAll(checkOuts);

        // Handle double-click event to initiate checkout process
        CheckOutTable.setOnMouseClicked(e-> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                // Get the selected item from the table
                CheckOut selectedCheckOut = CheckOutTable.getSelectionModel().getSelectedItem();

                // Check if an item is selected
                if (selectedCheckOut == null) {
                    new Alert(AlertType.ERROR, "Please select a row to check out", ButtonType.OK).show();
                    return;
                }

                // Show confirmation dialog
                Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
                confirmAlert.setTitle("Confirmation");
                confirmAlert.setHeaderText("Are you sure you want to check out?");
                confirmAlert.setContentText("This action cannot be undone.");

                Optional<ButtonType> result = confirmAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Perform checkout process
                    if (deleteBookingRecord(selectedCheckOut.getRoomID()) && deleteGuestRecord(selectedCheckOut.getRoomID()) && updateRoomStatus(selectedCheckOut.getRoomID())) {
                        // Add data to the report table
                        addDataToReport(selectedCheckOut);

                        // Display success message
                        new Alert(AlertType.INFORMATION, "Checkout process completed successfully", ButtonType.OK).show();

                        // Clear the TableView
                        CheckOutTable.getItems().clear();
                    } else {
                        // Display error message if checkout process fails
                        new Alert(AlertType.ERROR, "Failed to complete checkout process", ButtonType.OK).show();
                    }
                }
            }
        });
    }

  
    @FXML
    void ActionShowCheckOutRoom(ActionEvent event) {
    	//System.out.println(getCheckOutGuest());
    	
    }


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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//----------------Date and Time ---------
		
				LocalDateTime now = LocalDateTime.now();

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String formattedDateTime = now.format(formatter);
				timeLabel.setText(formattedDateTime);
				
				
				
				
		        
		        // Fetch and display CheckOutGuest
		 //    fetchAndDisplayCheckOutGuest();
		        
			}
	 // Method to fetch CheckOutGuest from the database
 /*   private void fetchAndDisplayCheckOutGuest() {
        List<CheckOut> CheckOuts = getCheckOutGuest();
        try {
            CheckOutTable.getItems().addAll(CheckOuts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
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

 // Method to delete the guest record associated with the given booking ID
    private boolean deleteGuestRecord(int roomId) {
        String sql = "DELETE FROM booking WHERE bookingID = ?";
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
    
    // Method to update the room status to reflect that it is empty after checkout
    public boolean updateRoomStatus(int roomId) {
        String sql = "UPDATE room SET is_empty = true WHERE roomID = ?";
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
        String sql = "SELECT b.bookingID, b.roomID, g.name, g.nrc, b.checkinDate, b.checkoutDate, b.totalPrice " +
                "FROM booking b JOIN guest g ON b.guestID = g.guestID WHERE b.roomID = ?;";
        List<CheckOut> list = new ArrayList<>();
        try (Connection con = Database.DBConnection.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setInt(1, roomId);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                CheckOut s = new CheckOut(rs.getInt("bookingID"), rs.getInt("roomID"), rs.getString("name"),
                        rs.getString("nrc"), rs.getString("checkinDate"), rs.getString("checkoutDate"),
                        rs.getDouble("totalPrice"));
                list.add(s);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private void addDataToReport(CheckOut selectedCheckOut) {
        String sql = "INSERT INTO report (bookingID, roomID, name, nrc, CheckInDate, CheckOutDate, pricePerNight) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Database.DBConnection.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setInt(1, selectedCheckOut.getBookingID());
            psmt.setInt(2, selectedCheckOut.getRoomID());
            psmt.setString(3, selectedCheckOut.getName());
            psmt.setString(4, selectedCheckOut.getNrc());
//            psmt.setString(5, selectedCheckOut.getPhone());
//            psmt.setString(6, selectedCheckOut.getEmail());
//            psmt.setString(7, selectedCheckOut.getAddress());
//            psmt.setString(8, selectedCheckOut.getCity());
            psmt.setString(5, selectedCheckOut.getCheckInDate());
            psmt.setString(6, selectedCheckOut.getCheckOutDate());
            psmt.setDouble(7, selectedCheckOut.getPricePerNight());
            
            
            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data added to report successfully.");
            } else {
                System.out.println("Failed to add data to report.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}