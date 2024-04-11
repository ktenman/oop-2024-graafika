package ee.ut.graafika;

import java.util.Set;

public interface KalkulaatoriKonstandid {
	String PEALKIRI = "Kalkulaator";
	String BACKSPACE = "←";
	String CE = "CE";
	String C = "C";
	String PUNKT = ".";
	String PLMI = "±";
	String VÕRDUS = "=";
	String RUUTJUUR = "√";
	String PÖÖRDARV = "1/x";
	String PROTSENT = "%";
	String VIGA_SISEND = "Sisendi viga";
	String VIGA_NEG_ARV = "Negatiivne arv";
	String VIGA_JAGAMINE_0 = "Jagamine 0-ga";
	
	double SCENE_WIDTH = 320;
	double SCENE_HEIGHT = 480;
	double VAHE = 10;
	double VÄIKSE_NUPU_LAIUS = 50;
	
	Set<String> NUMBRID = Set.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
	Set<String> PUNASED_OP = Set.of("C", "CE", "←", "±", "1/x", "√", "=", "%");
	Set<String> VAIKE_NUPUD = Set.of("C", "CE", "←");
	
	String[] ESIMESE_REA_NUPUD = {BACKSPACE, CE, C};
	String[] NUPU_SILDID = {"7", "8", "9", "/", "√", "4", "5", "6", "*", "1/x", "1", "2", "3", "-", "%", "0", ".", "=", "+"};
	
	String TEXTFIELD_STYLE = "-fx-border-color: black; -fx-border-width: 1px;";
}
