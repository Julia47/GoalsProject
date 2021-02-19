package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFX extends Application {

        public static void main(String[] args) {
        Application.launch(args);
        }

        @Override
        public void start(Stage stage) throws Exception {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("StartForms/SignIn.fxml")));
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.setTitle("Sign In");
            stage.setWidth(600);
            stage.setHeight(800);

            stage.show();
        }
}
