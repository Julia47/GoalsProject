package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {


        public static void main(String[] args) {

        Application.launch(args);
    }


        @Override
        public void start(Stage stage) throws Exception {

            String name = "startForms/SignIn.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(name));
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.setTitle("Hello JavaFX");
            stage.setWidth(600);
            stage.setHeight(800);

            stage.show();
        }

    }
