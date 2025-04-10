package ee.ut.graafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HiireVajutus extends Application {

    private Label koordinaadid;

    @Override
    public void start(Stage peaLava) {
        Pane juur = new Pane();
        Scene scene = new Scene(juur, 400, 300);

        scene.setOnMousePressed(event -> {
            if (koordinaadid != null) {
                juur.getChildren().remove(koordinaadid);
            }

            double x = event.getX();
            double y = event.getY();

            koordinaadid = new Label(String.format("(%.1f, %.1f)", x, y));

            koordinaadid.setLayoutX(x);
            koordinaadid.setLayoutY(y);

            juur.getChildren().add(koordinaadid);
        });

        peaLava.setTitle("Hiire asukoht hetkel, kui vajutatakse hiire nupule");
        peaLava.setScene(scene);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}