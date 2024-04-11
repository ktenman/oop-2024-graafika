package ee.ut.graafika;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Kalkulaator extends Application implements KalkulaatoriKonstandid, Operatsioonid {
	
	private TextField kuvamine;
	private BigDecimal arv1 = BigDecimal.ZERO;
	private String toiming = "";
	private boolean uusSisestus = false;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage peaLava) {
		VBox põhjaPaigutus = new VBox();
		põhjaPaigutus.setSpacing(VAHE);
		põhjaPaigutus.setPadding(new Insets(VAHE, VAHE, VAHE, VAHE));
		
		kuvamine = new TextField();
		kuvamine.setEditable(false);
		kuvamine.setStyle(TEXTFIELD_STYLE);
		kuvamine.setMaxWidth(Double.MAX_VALUE);
		kuvamine.setAlignment(Pos.TOP_RIGHT);
		
		GridPane võrguPaigutus = new GridPane();
		võrguPaigutus.setHgap(VAHE);
		võrguPaigutus.setVgap(VAHE);
		
		HBox esimeneRida = new HBox();
		esimeneRida.setSpacing(VAHE);
		
		for (String nupuSilt : ESIMESE_REA_NUPUD) {
			Button nupp = new Button(nupuSilt);
			nupp.setTextFill(PUNASED_OP.contains(nupuSilt) ? Color.RED : Color.BLUE);
			nupp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			if (VAIKE_NUPUD.contains(nupuSilt)) {
				nupp.setMaxWidth(VÄIKSE_NUPU_LAIUS);
			}
			nupp.setOnAction(e -> lisaEsimeseReaNupuleTegevus(nupuSilt));
			esimeneRida.getChildren().add(nupp);
		}
		
		for (int i = 0; i < NUPU_SILDID.length; i++) {
			Button nupp = new Button(NUPU_SILDID[i]);
			nupp.setTextFill(PUNASED_OP.contains(NUPU_SILDID[i]) ?
					Color.RED : Color.BLUE);
			nupp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			GridPane.setConstraints(nupp, i % 5, i / 5);
			nupp.setOnAction(e -> lisaKalkulaatoriNupuleTegevus(nupp.getText()));
			võrguPaigutus.getChildren().add(nupp);
		}
		
		põhjaPaigutus.getChildren().addAll(kuvamine, esimeneRida, võrguPaigutus);
		Scene stseen = new Scene(põhjaPaigutus, SCENE_WIDTH, SCENE_HEIGHT);
		peaLava.setScene(stseen);
		peaLava.setTitle(PEALKIRI);
		peaLava.setResizable(false);
		peaLava.show();
	}
	
	private void lisaEsimeseReaNupuleTegevus(String nupuSilt) {
		switch (nupuSilt) {
			case BACKSPACE -> {
				String tekst = kuvamine.getText();
				if (!tekst.isEmpty()) {
					kuvamine.setText(tekst.substring(0, tekst.length() - 1));
				}
			}
			case CE -> kuvamine.setText("");
			case C -> {
				arv1 = BigDecimal.ZERO;
				kuvamine.setText("");
				toiming = "";
			}
		}
	}
	
	private void lisaKalkulaatoriNupuleTegevus(String nupuTekst) {
		try {
			if (NUMBRID.contains(nupuTekst)) {
				if (uusSisestus) {
					kuvamine.setText("");
					uusSisestus = false;
				}
				kuvamine.appendText(nupuTekst);
				return;
			}
			switch (nupuTekst) {
				case PUNKT -> {
					if (!kuvamine.getText().contains(PUNKT)) {
						kuvamine.appendText(PUNKT);
					}
				}
				case PLMI -> {
					if (!kuvamine.getText().isEmpty()) {
						BigDecimal arv = new BigDecimal(kuvamine.getText()).negate();
						kuvamine.setText(arv.toString());
					}
				}
				case RUUTJUUR, PROTSENT, PÖÖRDARV -> arvuta(nupuTekst);
				default -> {
					if (toiming.isEmpty()) {
						arv1 = new BigDecimal(kuvamine.getText());
						toiming = nupuTekst;
						uusSisestus = true;
					} else if (nupuTekst.equals(VÕRDUS)) {
						arvuta(toiming);
						toiming = "";
					}
				}
			}
			
		} catch (NumberFormatException ex) {
			kuvamine.setText(VIGA_SISEND);
			uusSisestus = true;
		}
	}
	
	private void arvuta(String operatsioon) {
		BigDecimal arv = new BigDecimal(kuvamine.getText());
		switch (operatsioon) {
			case RUUTJUUR -> {
				if (arv.compareTo(BigDecimal.ZERO) >= 0) {
					kuvamine.setText(String.valueOf(Math.sqrt(arv.doubleValue())));
				} else {
					kuvamine.setText(VIGA_NEG_ARV);
					uusSisestus = true;
				}
			}
			case PÖÖRDARV -> {
				if (arv.compareTo(BigDecimal.ZERO) != 0) {
					kuvamine.setText(BigDecimal.ONE.divide(arv, 10, RoundingMode.HALF_UP).toString());
				} else {
					kuvamine.setText(VIGA_JAGAMINE_0);
					uusSisestus = true;
				}
			}
			case PROTSENT -> {
				arv1 = arv1.multiply(arv).divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
				kuvamine.setText(arv1.toString());
				uusSisestus = true;
			}
			default -> {
				BigDecimal arv2 = new BigDecimal(kuvamine.getText());
				arv1 = arvuta(arv1, arv2, operatsioon);
				kuvamine.setText(arv1.toString());
				uusSisestus = true;
			}
		}
	}
}
