package ee.ut.graafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HiireVajutus extends Application {

    Label koordinaadid;

    @Override
    public void start(Stage peaLava) {

        Pane juur = new Pane();
        Scene scene = new Scene(juur, 800, 600);

        scene.setOnMousePressed(sündmus -> {
//1.
            juur.getChildren().clear();
//2.
         //   if (koordinaadid != null) {
            //    koordinaadid.setVisible(false);
         //   }

            double x = sündmus.getX();
            double y = sündmus.getY();

            System.out.println(x + " " + y);

            koordinaadid = new Label(x + " " + y);

            koordinaadid.setLayoutX(x);
            koordinaadid.setLayoutY(y);

        //    koordinaadid.setVisible(true);

            juur.getChildren().add(koordinaadid);
        });

        peaLava.setScene(scene);
        peaLava.setTitle("Hiire Vajutus");
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
