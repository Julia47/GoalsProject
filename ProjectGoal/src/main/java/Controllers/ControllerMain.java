package Controllers;

import Entity.User;
import Entity.UserHolder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    public Pane calendarPane;
    public ImageView closeBtn;
    public ImageView minBtn;

    public void userBtnClicked() {

        menuPane.setStyle("-fx-background-color: #351c4d; -fx-border-color: #b6f2e1; -fx-border-width: 0.4;");
        borderPane.setStyle("-fx-border-color: #b6f2e1; -fx-background-color: #351c4d;; -fx-border-width: 0.4;");
        up1Pane.setStyle("-fx-background-color:  #351c4d;");
        up2Pane.setStyle("-fx-background-color:  #351c4d;");
        up3Pane.setStyle("-fx-background-color:  #351c4d;");

        mainBorderPane.setCenter(setInfoToUserFields());
    }

    public Parent setInfoToUserFields(){
        User user = UserHolder.getInstance();
        Parent root;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("UserForms/UserForm1.fxml")));
            Label nameLabel = (Label) root.lookup("#nameData");
            if (nameLabel!=null) nameLabel.setText(user.getUsername());
            Label emailLabel = (Label) root.lookup("#emailData");
            if (emailLabel!=null) emailLabel.setText(user.getE_mail());
            Label passLabel = (Label) root.lookup("#passData");
            if (passLabel!=null) passLabel.setText(user.getPassw());
            return root;
        } catch (Exception ignored) {
        }
        return null;
    }

    public void starBtnClicked() {
        menuPane.setStyle("-fx-background-color: #050828; -fx-border-color: #b6f2e1; -fx-border-width: 0.4;");
        borderPane.setStyle("-fx-border-color: #b6f2e1; -fx-background-color: #050828; -fx-border-width: 0.4;");
        up1Pane.setStyle("-fx-background-color: #050828;");
        up2Pane.setStyle("-fx-background-color: #050828;");
        up3Pane.setStyle("-fx-background-color: #050828;");

        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
        } catch (Exception ignored) {
        }

        mainBorderPane.setCenter(root);

    }

    public void calendarBtnClicked() {
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

    public void closeBtnClicked(MouseEvent mouseEvent) {
        Stage stages = (Stage) maxBtn.getScene().getWindow();
        stages.close();
    }

    public void minBtnClicked(MouseEvent mouseEvent) {
        Stage stages = (Stage) maxBtn.getScene().getWindow();
        stages.setMaximized(false);
    }

}


