package checkOut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ReceiptUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage st) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("receipt.fxml"));
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.getIcons().add(new Image(getClass().getResourceAsStream("../img/biglogo1.png")));
		st.setTitle("Receipt");
		st.setResizable(false);
		//st.setMaximized(true);
		st.show();		
	}

}
