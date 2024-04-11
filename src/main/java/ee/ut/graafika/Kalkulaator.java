package ee.ut.graafika;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Kalkulaator extends Application implements KalkulaatoriKonstandid, Operatsioonid {
	
	private TextField kuvamine = new TextField();
	
	@Override
	public void start(Stage peaLava) {
		VBox põhjaPaigutus = new VBox();
		põhjaPaigutus.setPadding(new Insets(VAHE, VAHE, VAHE, VAHE));
		põhjaPaigutus.setSpacing(VAHE);
		
		kuvamine = new TextField();
		kuvamine.setEditable(false);
		kuvamine.setStyle(TEXTFIELD_STYLE);
		kuvamine.setMaxWidth(Double.MAX_VALUE);
		kuvamine.setAlignment(Pos.TOP_RIGHT);
		
		GridPane.setConstraints(kuvamine, 0, 0, 4, 1);
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(VAHE);
		gridPane.setVgap(VAHE);
		gridPane.setPadding(new Insets(VAHE, VAHE, VAHE, VAHE));
		
		
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
					kuvamine.setText(buttonText);
				}
			});
			
			gridPane.getChildren().add(button);
		}
		
		põhjaPaigutus.getChildren().addAll(kuvamine, gridPane);
		
		Scene scene = new Scene(põhjaPaigutus, 300, 300);
		
		peaLava.setScene(scene);
		peaLava.setTitle(PEALKIRI);
		peaLava.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
