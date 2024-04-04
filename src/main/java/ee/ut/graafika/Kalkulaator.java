package ee.ut.graafika;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Kalkulaator extends Application {
	@Override
	public void start(Stage peaLava) {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		
		TextField display = new TextField();
		display.setEditable(false);
		display.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(display, 0, 0, 4, 1);
		
		peaLava.setTitle("Kalkulaator");
		peaLava.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
