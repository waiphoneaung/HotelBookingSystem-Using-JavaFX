package report;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import login.LoginUI;
import room.RoomUI;

public class ReportController implements Initializable {

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

	}
		
	}
