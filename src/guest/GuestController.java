package guest;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import checkIn.CheckInUI;
import checkOut.CheckoutUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import login.LoginUI;
import report.ReportUI;
import room.RoomUI;

public class GuestController implements Initializable {

    @FXML
    private Hyperlink LogoutLink;

    @FXML
    private TableColumn<Guest, String> address;

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
    private TableColumn<Guest, String> city;

    @FXML
    private TableColumn<Guest, String> email;

    @FXML
    private TableColumn<Guest, Integer> guestID;

    @FXML
    private Label lblUsername;

    @FXML
    private Hyperlink linkBack;

    @FXML
    private TableColumn<Guest, String> name;

    @FXML
    private TableColumn<Guest, String> nrc;

    @FXML
    private TableColumn<Guest, String> phone;

    @FXML
    private TableColumn<Guest, Integer> roomID;

    @FXML
    private TableView<Guest> tblGuest;

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
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        timeLabel.setText(formattedDateTime);
	
		
		List<Guest> Guests = getAllGuest();

        for (int i = 0; i < Guests.size(); i++) {
            System.out.println("aa" + Guests.get(i));
        }
        guestID.setCellValueFactory(new PropertyValueFactory <>("guestID"));
        roomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        nrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        
        try {
            tblGuest.getItems().addAll(Guests);
        } catch (Exception e) {
            e.printStackTrace();
        }
		

}
	public static List<Guest> getAllGuest(){
		String sql = "SELECT * FROM guest g WHERE guestID IN (SELECT guestID FROM booking b WHERE g.guestID = b.guestID AND  CURRENT_DATE() BETWEEN checkinDate AND checkoutDate)";
		ArrayList<Guest> list = new ArrayList<>();
		try(Connection con = Database.DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);) {
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				Guest s = new Guest(rs.getInt("GuestID"), rs.getInt("roomID"), rs.getString("name"), rs.getString("nrc"), rs.getString("phone"), rs.getString("email"),rs.getString("address"),rs.getString("city"));
				list.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}return list;
}
	}


