package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class ControllerGoal {

    public Pane addGoalPane;
    public Pane goalPane;
    public Button addGoalBtn;
    public BorderPane goalBorderPane;

    public void addGoalBtnClicked(MouseEvent mouseEvent) {
        addGoalBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/AddGoalForm.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane.setCenter(root);
    }
}
