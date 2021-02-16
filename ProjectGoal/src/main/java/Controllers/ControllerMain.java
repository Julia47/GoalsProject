package Controllers;

import Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class ControllerMain {


    public ImageView userBtn;
    public ImageView starBtn;
    public ImageView calendarBtn;
    public Pane menuPane;
    public Pane up3Pane;
    public Label up1Pane;
    public Pane up2Pane;
    public BorderPane borderPane;
    public ImageView maxBtn;
    public BorderPane mainBorderPane;
    @FXML
    public Label emailData = new Label();
    @FXML
    public Label nameData;
    @FXML
    public Label passData = new Label();
    public Button changeDataBtn;
    public TextField changeName;
    public TextField changeMail;
    public PasswordField changePassw;
    @FXML
    public Button canselDataBtn;
    public Pane calendarPane;
    public Pane userPane;
    public Pane changeUserPane;


    public void userBtnClicked() {

        menuPane.setStyle("-fx-background-color: #351c4d; -fx-border-color: #b6f2e1; -fx-border-width: 0.4;");
        borderPane.setStyle("-fx-border-color: #b6f2e1; -fx-background-color: #351c4d;; -fx-border-width: 0.4;");
        up1Pane.setStyle("-fx-background-color:  #351c4d;");
        up2Pane.setStyle("-fx-background-color:  #351c4d;");
        up3Pane.setStyle("-fx-background-color:  #351c4d;");

        User user = User.getInstance();
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("UserForms/UserForm1.fxml")));
            Label nameLabel = (Label) root.lookup("#nameData");
            if (nameLabel!=null) nameLabel.setText(user.getUsername());
            Label emailLabel = (Label) root.lookup("#emailData");
            if (emailLabel!=null) emailLabel.setText(user.getE_mail());
            Label passLabel = (Label) root.lookup("#passData");
            if (passLabel!=null) passLabel.setText(user.getPassw());
        } catch (Exception ignored) {
        }
        mainBorderPane.setCenter(root);

    }

    public void starBtnClicked() {
       // mainPane.setStyle("-fx-background-color: #0b253a; -fx-border-color: #b6f2e1; -fx-border-width: 0.4;");
        menuPane.setStyle("-fx-background-color: #050828; -fx-border-color: #b6f2e1; -fx-border-width: 0.4;");
        borderPane.setStyle("-fx-border-color: #b6f2e1; -fx-background-color: #050828; -fx-border-width: 0.4;");
        up1Pane.setStyle("-fx-background-color: #050828;");
        up2Pane.setStyle("-fx-background-color: #050828;");
        up3Pane.setStyle("-fx-background-color: #050828;");

        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm.fxml")));
        } catch (Exception ignored) {
        }
        mainBorderPane.setCenter(root);
    }

    public void calendarBtnClicked() {
       // mainPane.setStyle("-fx-background-color: #0a4445; -fx-border-color: #b6f2e1; -fx-border-width: 0.4;");
        menuPane.setStyle("-fx-background-color: #002c3e; -fx-border-color: #b6f2e1; -fx-border-width: 0.4;");
        borderPane.setStyle("-fx-border-color: #b6f2e1; -fx-background-color: #002c3e; -fx-border-width: 0.4;");
        up1Pane.setStyle("-fx-background-color: #002c3e;");
        up2Pane.setStyle("-fx-background-color: #002c3e;");
        up3Pane.setStyle("-fx-background-color: #002c3e;");

        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("CalendarForms/CalendarForm.fxml")));
        } catch (Exception ignored) {
        }
        mainBorderPane.setCenter(root);
    }

    public void maxBtnClicked() {
        Stage stages = (Stage) maxBtn.getScene().getWindow();
        stages.setMaximized(true);
    }

    public void changeDataClicked() {
        changeDataBtn.setStyle("-fx-background-color: #361040; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");

//        Parent root = null;
//        try {
//            root= FXMLLoader.load(getClass().getResource("UserForm2.fxml"));
//        } catch (Exception ignored) {
//        }
//        mainBorderPane.setCenter(root);
    }
}


