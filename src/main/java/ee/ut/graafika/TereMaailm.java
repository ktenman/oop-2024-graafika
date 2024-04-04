package ee.ut.graafika;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TereMaailm extends Application {
	@Override
	public void start(Stage peaLava) {
		TextField nimi = new TextField();
		nimi.setText("Sisesta nimi");
		nimi.setLayoutX(10);
		nimi.setLayoutY(10);
		
		Button nupp = new Button();
		nupp.setLayoutX(160);
		nupp.setLayoutY(10);
		nupp.setText("Tervita!");
		
		Label tervitusTekst = new Label();
		tervitusTekst.setLayoutX(10);
		tervitusTekst.setLayoutY(40);
		
		nupp.setOnAction(event -> tervitusTekst.setText("Tere, " + nimi.getText() + "!"));
		
		Group group = new Group();
		group.getChildren().addAll(nimi, nupp, tervitusTekst);
		
		peaLava.setTitle("Tere Maailm");
		peaLava.setScene(new Scene(group, 240, 70));
		peaLava.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
