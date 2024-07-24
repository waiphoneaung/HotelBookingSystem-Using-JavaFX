package checkIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import login.LoginUI;

public class CheckInUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override

	public void start(Stage rt) throws Exception {
		LoginUI.mainStage = rt;

		Parent root = FXMLLoader.load(getClass().getResource("checkin.fxml"));
		Scene sc = new Scene(root);
		// sc.getStylesheets().add("/style/project.css");
		rt.getIcons().add(new Image(getClass().getResourceAsStream("../img/biglogo1.png")));
		rt.setScene(sc);
		rt.setTitle("CheckIn");
		rt.setResizable(true);
		// rt.setMaximized(true);
		rt.show();
	}

}
