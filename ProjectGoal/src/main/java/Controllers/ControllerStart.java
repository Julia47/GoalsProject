package Controllers;

import Entity.User;
import Entity.UserHolder;
import Main.GlobalRepo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ControllerStart {

    public TextField createMail;
    public TextField createName;
    public PasswordField createPassw;
    public Button createBtn;
    public Label labelEmpty;
    public Label invMailLabel;
    public Button signInBtn;
    public TextField emailField;
    public PasswordField passField;
    public Label invalidLabel;

    @FXML
    private Label registerLabel;

    @FXML
    private void registerLabelClicked() throws IOException {

        Stage stage = (Stage) registerLabel.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("StartForms/Register.fxml")));
        stage.setTitle("Register");
        stage.setScene(new Scene(root, 600, 800));
        stage.show();
    }

    @FXML
    private void registerLabelMoved() {
        registerLabel.setTextFill(Paint.valueOf("#b6f2e1"));
    }

    @FXML
    private void registerLabelExited() {
        registerLabel.setTextFill(Paint.valueOf("#888888"));
    }

    @FXML
    private void createClicked() throws IOException {

        createBtn.setStyle("-fx-background-color: #34273B; -fx-border-width: 3px; -fx-border-color: #b6f2e1;");

        String username = createName.getText();
        String e_mail = createMail.getText();
        String passw = createPassw.getText();
        String empty = "";


        if (username.equals(empty) || e_mail.equals(empty) || passw.equals(empty) || searchMailBool(e_mail)) {
            if(username.equals(empty) || e_mail.equals(empty) || passw.equals(empty) ){
                invMailLabel.setTextFill(Paint.valueOf("#34273B"));
            }
            else {
                searchMail(e_mail);
                invMailLabel.setTextFill(Paint.valueOf("#888888"));
            }
            labelEmpty.setTextFill(Paint.valueOf("#888888"));
        }

        else {
            createUser(username, e_mail, passw);
            Stage stages = (Stage) createBtn.getScene().getWindow();
            stages.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("StartForms/SignIn.fxml")));
            stages.setTitle("SignIn");
            stages.setWidth(900);
            stages.setHeight(770);
            stages.setScene(new Scene(root, 900, 770));
            stages.show();
        }

    }

    public void createUser(String username, String e_mail, String passw){

            User user = UserHolder.getInstance();
            user.setUsername(username);
            user.setE_mail(e_mail);
            user.setPassw(passw);

            GlobalRepo connect = new GlobalRepo();
            connect.insertUser(user.getUsername(), user.getPassw(), user.getE_mail());
            connect.selectMail();

    }

    public void signInBtnClicked(MouseEvent mouseEvent) throws IOException {
        signInBtn.setStyle("-fx-background-color: #34273B; -fx-border-width: 3px; -fx-border-color: #b6f2e1;");
        GlobalRepo connect = new GlobalRepo();
        String mail = emailField.getText();
        String pass = passField.getText();
        User user = UserHolder.getInstance();
        user.setE_mail(mail);
        user.setPassw(pass);
        user.setUsername(connect.searchName(mail));
        if (searchPassBool(pass, mail)){
            System.out.println("Enter");

            Stage stages = (Stage) signInBtn.getScene().getWindow();
            stages.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("StartForms/MainForm.fxml")));
            stages.setTitle("MainFrame");
            stages.setWidth(900);
            stages.setHeight(770);
            stages.setScene(new Scene(root, 900, 770));
            stages.show();

        }
        else{
            invalidLabel.setTextFill(Paint.valueOf("#888888"));
        }
    }

    public Boolean searchMailBool(String e_mail) {
        GlobalRepo connect = new GlobalRepo();
        List<String> mail;
        boolean b = false;
        mail = connect.selectMail();
        for (String s : mail) {
            if (s.equals(e_mail)) {
                b = true;
                break;
                }
            }
        return b;
    }

    public String searchMail(String email){
        GlobalRepo connect = new GlobalRepo();
        List<String> mail;
        String str = "";
        mail = connect.selectMail();
        for(String m: mail){
            if (m.equals(email)) {
                str = m;
            }
        }
        return str;
    }

    public Boolean searchPassBool(String pass, String mail){
        GlobalRepo connect = new GlobalRepo();
        String password;
        boolean t = false;
        password = connect.selectPass(mail);
            if (password.equals(pass)) {
                t = true;
            }
        return t;
    }

    public void createExited(MouseEvent mouseEvent) {
        createBtn.setStyle("-fx-background-color: #34273B; -fx-border-width: 1px; -fx-border-color: #b6f2e1;");
    }

    public void createMoved(MouseEvent mouseEvent) {
        createBtn.setStyle("-fx-background-color: #34273B; -fx-border-width: 3px; -fx-border-color: #b6f2e1;");
    }

    public void signInBtnExited(MouseEvent mouseEvent) {
        signInBtn.setStyle("-fx-background-color: #34273B; -fx-border-width: 1px; -fx-border-color: #b6f2e1;");
    }

    public void signInBtnMoved(MouseEvent mouseEvent) {
        signInBtn.setStyle("-fx-background-color: #34273B; -fx-border-width: 3px; -fx-border-color: #b6f2e1;");
    }
}


