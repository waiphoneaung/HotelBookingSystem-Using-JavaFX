package staff;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaffUI extends Application{
	Label lblid = new Label("ID: ");
	Label lbluserName = new Label("User Name:");
	Label lblpassword = new Label("Password:");
	 Label lblis_admin = new Label("Position:");
	    ComboBox<String> cmbPosition = new ComboBox<>();
	
	TextField txfid = new TextField();
	TextField txfuserName = new TextField();
	TextField txfpassword = new TextField();
	
	Button btnAdd = new Button("Add");
	Button btnDelete = new Button("Delete");
	Button btnReset = new Button("Reset");

	
	VBox vbox = new VBox(20, new HBox(75, lblid, txfid),
							new HBox (10, lbluserName, txfuserName),
							new HBox(20, lblpassword, txfpassword),
							 new HBox(30, lblis_admin, cmbPosition),
							new HBox(20, btnAdd, btnDelete, btnReset));
						
	
	TableView<Staff> tbStaff = new TableView<Staff>();
	TableColumn<Staff, Integer> c1 = new TableColumn<>("ID");
	TableColumn<Staff, String> c2 = new TableColumn<>("Name");
	TableColumn<Staff, String> c4 = new TableColumn<>("Position");
	
	HBox root = new HBox(20, vbox, tbStaff);
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage st) throws Exception {
		root.setStyle("-fx-background-color: khaki");
		root.setPrefSize(900, 600);
		
		vbox.setStyle("-fx-padding: 30;" + "-fx-font-size: 18;");
		btnDelete.setStyle("-fx-background-color:#ed2207;" + "-fx-border-width: 3;" + 
						   "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);");
		btnAdd.setStyle("-fx-background-color:#3deb07;" +  "-fx-border-width: 3;" + 
				"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);");
		btnReset.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);");
		
		st.getIcons().add(new Image(getClass().getResourceAsStream("../img/biglogo1.png")));
		st.setResizable(false);
		
		tbStaff.getColumns().addAll(c1, c2, c4);
		c1.setCellValueFactory(new PropertyValueFactory<>("id"));
		c1.setPrefWidth(150);
		c2.setCellValueFactory(new PropertyValueFactory<>("Name"));
		c2.setPrefWidth(150);
		c4.setCellValueFactory(new PropertyValueFactory<>("Position"));
		c4.setPrefWidth(150);

		cmbPosition.getItems().addAll("Admin","Staff");
		cmbPosition.getSelectionModel().selectFirst();
		
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.setTitle("Staff Management");
		st.show();
		
		txfid.setEditable(false);
		txfuserName.requestFocus();
		txfid.setText(StaffManager.getNewID());
		
		tbStaff.getItems().addAll(StaffManager.getAllStaff());
		System.out.println(StaffManager.getAllStaff());
		
		tbStaff.setOnMouseClicked(e->{
			if(e.getClickCount()>1) {
				update = tbStaff.getSelectionModel().getSelectedItem();
				if(update != null) {
					txfid.setText(update.getId());
					txfid.setEditable(false);
					txfuserName.setText(update.getName());
					txfpassword.setText(update.getPassword());
					cmbPosition.getSelectionModel().getSelectedItem();
					txfuserName.requestFocus();
					btnAdd.setText("Update");
				}
			}
		});
		
		btnAdd.setOnAction(e-> btnAction());
		btnReset.setOnAction(e-> reset());
		btnDelete.setOnAction(e-> deleteStaff());
	}
	Staff update = null;
	
	private void reset() {
		txfuserName.clear();
		txfpassword.clear();
		cmbPosition.getSelectionModel().selectFirst();;
		txfuserName.requestFocus();
		btnAdd.setText("Add");
		txfid.setText(StaffManager.getNewID());
		txfid.setEditable(false);
		update = null;
	}
	
	private void addStaff() {
		 String id = txfid.getText();
	        String name = txfuserName.getText();
	        String password = txfpassword.getText();
	        String position;
	        if ("Admin".equals(cmbPosition.getValue())) {
	            position = "1";
	        } else if ("Staff".equals(cmbPosition.getValue())) {
	            position = "0";
	        } else {
	            position = null; // Handle other cases or provide a default value
	        }
	       // Staff s = new Staff(id, name, password, position);

	        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to add?", ButtonType.YES, ButtonType.NO);
	        confirmationAlert.setHeaderText(null);
	        confirmationAlert.setContentText("This action will add a new staff member. Do you want to continue?");
	        confirmationAlert.showAndWait();
	        
	        if (confirmationAlert.getResult() == ButtonType.YES) {
	            Staff s = new Staff(id, name, password, position);
	            
	            if (StaffManager.addNewStaff(s)) {
	                new Alert(AlertType.INFORMATION, "Successfully added the staff.", ButtonType.OK).show();
	                tbStaff.getItems().add(s);
	                reset();
	            } else {
	                new Alert(AlertType.ERROR, "Failed to add new staff member. Try Again :(", ButtonType.OK).show();
	            }
	        }
	    }
	
	private void updateStaff() {
        update.setId(txfid.getText());
        update.setName(txfuserName.getText());
        update.setPassword(txfpassword.getText());
     // Set position based on combo box value
        String position;
        if ("Admin".equals(cmbPosition.getValue())) {
            position = "1";
        } else if ("Staff".equals(cmbPosition.getValue())) {
            position = "0";
        } else {
            position = null; // Handle other cases or provide a default value
        }
        update.setPosition(position);
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to update?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("This action will update the staff member. Do you want to continue?");
        confirmationAlert.showAndWait();
        
        if (confirmationAlert.getResult() == ButtonType.YES) {
            if (StaffManager.updateStaff(update)) {
                new Alert(AlertType.CONFIRMATION, "Update successful", ButtonType.OK).show();
                tbStaff.refresh();
                reset();
            } else {
                new Alert(AlertType.ERROR, "Update failed", ButtonType.OK).show();
            }
        }    
	}
	
	private void deleteStaff() {
		Staff todelete = tbStaff.getSelectionModel().getSelectedItem();
		if(todelete == null) return;
		Alert a = new Alert(AlertType.WARNING, "Are you sure to delete " +txfuserName.getText(), ButtonType.YES, ButtonType.NO);
		ButtonType bt = a.showAndWait().get();
		if(bt == ButtonType.YES) {
			if(StaffManager.deleteStaff(todelete)) {
				new Alert(AlertType.INFORMATION, "Success to delete", ButtonType.OK).show();
				tbStaff.getItems().remove(todelete);
				reset();
			}else {
				new Alert(AlertType.ERROR, "Fail", ButtonType.OK).show();
			}
			
		}
	}

	
	private void btnAction() {
		if(update == null) {
			addStaff();
		}else {
			updateStaff();
		}
	}
	
}
