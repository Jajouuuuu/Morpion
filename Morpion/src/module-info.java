module Morpion {
	requires jBCrypt;
	requires com.google.gson;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires junit;
	
    opens app to javafx.fxml;
    exports app;
    opens model to javafx.fxml;
    exports model;
    opens view to javafx.fxml;
    exports view;
    opens controler to javafx.fxml;
    exports controler;
}