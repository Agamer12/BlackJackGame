module com.ari.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.ari.game to javafx.fxml;
    exports com.ari.game;
}