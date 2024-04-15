package checkIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import login.LoginUI;
	
public class CheckInUI extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	
	public void start(Stage st) throws Exception {
		LoginUI. mainStage = st;

		
		Parent root = FXMLLoader.load(getClass().getResource("checkin.fxml"));
		Scene sc = new Scene(root);
		sc.getStylesheets().add("/style/project.css");
		st.getIcons().add(new Image(getClass().getResourceAsStream("../img/biglogo1.png")));
		st.setScene(sc);
		st.setTitle("CheckIn"); 
		st.setResizable(false);
		st.setMaximized(true);
		st.show();
	}
	

}
