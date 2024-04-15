package checkOut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import login.LoginUI;

public class CheckoutUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("checkout.fxml"));
		LoginUI. mainStage = st;
		Scene sc = new Scene(root);
		sc.getStylesheets().add("/style/project.css");
		st.setScene(sc);
		st.getIcons().add(new Image(getClass().getResourceAsStream("../img/biglogo1.png")));
		st.setTitle("Check Out");
		st.setResizable(false);
		st.setMaximized(true);
		st.show();
		
	}

}
