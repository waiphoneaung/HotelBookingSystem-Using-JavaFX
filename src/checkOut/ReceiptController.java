package checkOut;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReceiptController implements Initializable {

	@FXML
	private TableView<CheckOut> bill_tableAmount;

	@FXML
	private TableColumn<CheckOut, Integer> b1;

	@FXML
	private TableColumn<CheckOut, String> b2;

	@FXML
	private TableColumn<CheckOut, String> b3;

	@FXML
	private TableColumn<CheckOut, Double> b4;

	@FXML
	private TableColumn<CheckOut, Double> b5;

	@FXML
	private Label bill_CheckInDate;

	@FXML
	private Label bill_CheckOutDate;

	@FXML
	private Label bill_bookingID;

	@FXML
	private Label bill_email;

	@FXML
	private Label bill_name;

	@FXML
	private Label bill_phone;

	@FXML
	private Button bill_print;

	@FXML
	private Label bill_receipt_date;

	@FXML
	private AnchorPane pnlR;

	@FXML
	private Label bill_total;

	@FXML
	void Action_Print(ActionEvent event) {
		// Create a PrinterJob
		PrinterJob printerJob = PrinterJob.createPrinterJob();

		if (printerJob != null && printerJob.showPrintDialog(bill_print.getScene().getWindow())) {
			// Print the receipt
			boolean success = printerJob.printPage(pnlR);

			if (success) {
				printerJob.endJob();
			} else {
				// Print failed
				displayAlert(AlertType.ERROR, "Printing failed", "Failed to print the receipt.");
			}
		} else {
			// Print dialog canceled or failed to create PrinterJob
			displayAlert(AlertType.ERROR, "Print dialog canceled",
					"Print dialog canceled or failed to create PrinterJob.");
		}
	}

	private void displayAlert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Set current date and time to bill_receipt_date label
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		bill_receipt_date.setText(formattedDateTime);

	}

	public void initData(CheckOut selectedCheckOut) {
		bill_name.setText(selectedCheckOut.getName());
		// bill_phone.setText(selectedCheckOut.getPhoneNumber());
		// Update other labels with the corresponding data from selectedCheckOut
		bill_bookingID.setText(selectedCheckOut.getBookingID() + "");
		bill_CheckInDate.setText(selectedCheckOut.getCheckInDate());
		bill_CheckOutDate.setText(selectedCheckOut.getCheckOutDate());
		bill_total.setText(selectedCheckOut.getTotalPrice() + "");
		bill_email.setText(selectedCheckOut.getEmail());
		bill_phone.setText(selectedCheckOut.getPhone());

		int roomID = selectedCheckOut.getRoomID();
		String roomCapacity = selectedCheckOut.getRoomCapacity();
		String numberOfDay = selectedCheckOut.getNumberOfDay();
		double pricePerNight = selectedCheckOut.getPricePerNight();
		double totalPrice = selectedCheckOut.getTotalPrice();

		b1.setCellValueFactory(new PropertyValueFactory<>("roomID"));
		b2.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
		b3.setCellValueFactory(new PropertyValueFactory<>("numberOfDay"));
		b4.setCellValueFactory(new PropertyValueFactory<>("PricePerNight"));
		b5.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		// Clear existing items in the TableView
		bill_tableAmount.getItems().clear();

		// Add new item to the TableView
		bill_tableAmount.getItems().add(selectedCheckOut);
	}

}
