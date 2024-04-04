package ee.ut.graafika;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		
		String[] buttonLabels = {
			"7", "8", "9", "/",
			"4", "5", "6", "*",
			"1", "2", "3", "-",
			"0", ".", "=", "+"
		};
		
		for (int i = 0; i < buttonLabels.length; i++) {
			Button button = new Button(buttonLabels[i]);
			button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			GridPane.setConstraints(button, i % 4, 1 + i / 4);
			
			button.setOnAction(event -> {
				Button source = (Button) event.getSource();
				String buttonText = source.getText();
				if (buttonText.equals("=")) {
					// arvutamine
				} else {
					display.setText(buttonText);
				}
			});
			
			gridPane.getChildren().add(button);
		}
		
		gridPane.getChildren().add(display);
		
		Scene scene = new Scene(gridPane, 300, 300);
		
		peaLava.setScene(scene);
		peaLava.setTitle("Kalkulaator");
		peaLava.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
