package Controllers;

import Entity.User;
import Entity.UserHolder;
import Main.GlobalRepo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import java.util.Objects;

public class ControllerUser {

    public Pane userPane;
    public Label emailData;
    public Label nameData;
    public Button changeDataBtn;
    public Label passData;
    public Pane changeUserPane;
    public Button canselDataBtn;
    public TextField changeName;
    public TextField changeMail;
    public PasswordField changePassw;
    public BorderPane userBorderPane;
    public Button changeDataBtn2;
    public BorderPane userBorderPane2;
    public Label invMailLabel;
    public Label labelEmpty;

    public void changeDataClicked(MouseEvent mouseEvent) {
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("UserForms/UserForm2.fxml")));
        } catch (Exception ignored) {
        }
        userBorderPane.setCenter(root);
    }

    public void changeDataClicked2(MouseEvent mouseEvent) {
        String username = changeName.getText();
        String e_mail = changeMail.getText();
        String passw = changePassw.getText();
        String empty = "";
        ControllerStart controllerStart = new ControllerStart();
        GlobalRepo connect = new GlobalRepo();
        
        if (username.equals(empty) || e_mail.equals(empty) || passw.equals(empty) || connect.searchEmail(e_mail)) {
            if(username.equals(empty) || e_mail.equals(empty) || passw.equals(empty) ){
                invMailLabel.setTextFill(Paint.valueOf("#34273B"));
            }
            else {
                invMailLabel.setTextFill(Paint.valueOf("#888888"));
            }
            labelEmpty.setTextFill(Paint.valueOf("#888888"));
        }

        else {
            User user = UserHolder.getInstance();
            connect.deleteUser(user.getE_mail());
            controllerStart.createUser(username, e_mail, passw);
            ControllerMain controllerMain = new ControllerMain();
            userBorderPane2.setCenter(controllerMain.setInfoToUserFields());
        }
    }

    public void canselDataClicked(MouseEvent mouseEvent) {
        ControllerMain controllerMain = new ControllerMain();
        userBorderPane2.setCenter(controllerMain.setInfoToUserFields());
    }

    public void changeDataMoved(MouseEvent mouseEvent) {
        changeDataBtn.setStyle("-fx-background-color: #361040; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void changeDataExited(MouseEvent mouseEvent) {
        changeDataBtn.setStyle("-fx-background-color: #361040; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void changeDataExited2(MouseEvent mouseEvent) {
        changeDataBtn2.setStyle("-fx-background-color: #361040; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void canselDataExited(MouseEvent mouseEvent) {
        canselDataBtn.setStyle("-fx-background-color: #361040; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void canselDataMoved(MouseEvent mouseEvent) {
        canselDataBtn.setStyle("-fx-background-color: #361040; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void changeDataMoved2(MouseEvent mouseEvent) {
        changeDataBtn2.setStyle("-fx-background-color: #361040; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void passDataExited(MouseEvent mouseEvent) {
        User user = UserHolder.getInstance();
        String str = "";
        for (int i=0; i<user.getPassw().length(); i++){
            str = str + "*";
        }
        passData.setText(str);
    }

    public void passDataMoved(MouseEvent mouseEvent) {
        User user = UserHolder.getInstance();
        passData.setText(user.getPassw());
    }
}
