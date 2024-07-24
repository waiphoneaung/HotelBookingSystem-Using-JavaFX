package CHART;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import Database.DBConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RP extends Application {
	BorderPane root = new BorderPane();
	Button btnGet = new Button("Get");
	CategoryAxis x = new CategoryAxis();
	NumberAxis y = new NumberAxis();
	BarChart<String, Number> chart = new BarChart<>(x, y);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public static Map<String, Integer> getNoOfDaysRented(){
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();
		String sql = "SELECT roomtype, SUM(numberofday) renteddays FROM `room` r JOIN booking b ON r.roomID=b.roomID \r\n"
				+ "GROUP BY roomtype";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql);){
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				data.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	public static Map<String, Integer> getNoOfDaysRentedByCapacity(){
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();
		String sql = "SELECT roomtype, COUNT(*) renteddays FROM `room` r JOIN booking b ON r.roomID=b.roomID \r\n"
				+ "GROUP BY roomtype";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql);){
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				data.put(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	@Override
	public void start(Stage st) throws Exception {
		chart.setAnimated(false);
		chart.setTitle("Room Summary");
		// TODO Auto-generated method stub
		root.setBottom(btnGet);
		root.setCenter(chart);
		root.setPrefSize(300, 400);
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.show();
		btnGet.setOnAction(e->showData());
	}
	private void showData() {
		Map<String, Integer> data = getNoOfDaysRented();
		XYChart.Series<String, Number> s1 = new XYChart.Series<String, Number>();
		s1.setName("No of Days");
		for (String k : data.keySet()) {
			s1.getData().add(new XYChart.Data<String, Number>(k, data.get(k)));
		}
		chart.getData().add(s1);
		
		data = getNoOfDaysRentedByCapacity();
		XYChart.Series<String, Number> s2 = new XYChart.Series<String, Number>();
		s2.setName("Book Count");
		for (String k : data.keySet()) {
			s2.getData().add(new XYChart.Data<String, Number>(k, data.get(k)));
		}
		chart.getData().add(s2);
	}
}
