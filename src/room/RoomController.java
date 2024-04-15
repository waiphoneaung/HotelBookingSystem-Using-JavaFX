package room;

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
import guest.GuestUI;
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
    private TableColumn<Room, String> c5;

    @FXML
    private Label lblUsername;

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
		
		List<Room> Rooms = getAllRoom();

        for (int i = 0; i < Rooms.size(); i++) {
            System.out.println("aa" + Rooms.get(i));
        }
        c1.setCellValueFactory(new PropertyValueFactory("roomID"));
        c2.setCellValueFactory(new PropertyValueFactory("roomType"));
        c3.setCellValueFactory(new PropertyValueFactory("roomCapacity"));
        c4.setCellValueFactory(new PropertyValueFactory("Is_Empty"));
        c5.setCellValueFactory(new PropertyValueFactory("pricePerNight"));
        
        try {
            tblRoom.getItems().addAll(Rooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
		

}
	public static List<Room> getAllRoom(){
		String sql = "SELECT * FROM room";
		ArrayList<Room> list = new ArrayList<>();
		try(Connection con = Database.DBConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);) {
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				Room s = new Room(rs.getInt("roomID"), rs.getString("roomType"), rs.getString("roomCapacity"), rs.getBoolean("is_empty"), rs.getDouble("pricePerNight"));
				list.add(s);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}return list;
}
}

