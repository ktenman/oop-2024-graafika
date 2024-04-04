module ee.ut.oop2024graafika {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	
	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires net.synedra.validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires eu.hansolo.tilesfx;
	requires com.almasb.fxgl.all;
	
	opens ee.ut.graafika to javafx.fxml;
	exports ee.ut.graafika;
}
