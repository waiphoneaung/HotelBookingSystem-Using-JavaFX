package login;

import checkIn.CheckInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginUI extends Application{
	public static Stage mainStage;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage st) throws Exception {
		LoginUI.mainStage = st;
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

		Scene sc = new Scene(root);
		st.setScene(sc);
		st.getIcons().add(new Image(getClass().getResourceAsStream("../img/biglogo1.png")));
		st.setResizable(false);
		st.setTitle("Login");
		st.show();
		
	    CheckInController.setLoginUI(this);

	}

}
