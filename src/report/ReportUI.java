package report;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import login.LoginUI;

public class ReportUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage st) throws Exception {
		LoginUI. mainStage = st;
		
		Parent root = FXMLLoader.load(getClass().getResource("report.fxml"));
		
		Scene sc = new Scene(root);
		sc.getStylesheets().add("/style/project.css");
		st.setScene(sc);
		st.getIcons().add(new Image(getClass().getResourceAsStream("../img/biglogo1.png")));
		st.setTitle("Report");
		st.setResizable(false);
		//st.setMaximized(true);
		st.show();
		
		
	}

}
