package ee.ut.graafika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EstonianFlagApp extends Application {

    private Canvas lõuend;
    private GraphicsContext kontekst;

    @Override
    public void start(Stage peaLava) {
        lõuend = new Canvas(400, 300);
        kontekst = lõuend.getGraphicsContext2D();

        Pane juur = new Pane(lõuend);
        Scene stseen = new Scene(juur, 400, 300);

        lõuend.widthProperty().bind(peaLava.widthProperty());
        lõuend.heightProperty().bind(peaLava.heightProperty());

        lõuend.widthProperty().addListener(jälgitav -> joonistaLipp());
        lõuend.heightProperty().addListener(jälgitav -> joonistaLipp());

        joonistaLipp();

        peaLava.setTitle("Lipp");
        peaLava.setScene(stseen);
        peaLava.show();
    }

    private void joonistaLipp() {
        double laius = lõuend.getWidth();
        double kõrgus = lõuend.getHeight();
        double triibuKõrgus = kõrgus / 3;

        kontekst.clearRect(0, 0, laius, kõrgus);

        kontekst.setFill(Color.BLUE);
        kontekst.fillRect(0, 0, laius, triibuKõrgus);

        kontekst.setFill(Color.BLACK);
        kontekst.fillRect(0, triibuKõrgus, laius, triibuKõrgus);

        kontekst.setFill(Color.WHITE);
        kontekst.fillRect(0, 2 * triibuKõrgus, laius, triibuKõrgus);
    }

    public static void main(String[] argumendid) {
        launch(argumendid);
    }
}