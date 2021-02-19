package Controllers;

import Entity.Goal;
import Main.GlobalRepo;
import Tools.ConvertorDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ControllerGoal {

    public Pane addGoalPane;
    public Pane goalPane;
    public Button addGoalBtn;
    public BorderPane goalBorderPane;
    public Button canselDataBtn;
    public Button addTaskBtn;
    public BorderPane goalBorderPane2;
    public BorderPane goalBorderPane3;
    public TextField goalTextField;
    public DatePicker dateGoal;
    public ChoiceBox chooseCategory;
    public ScrollPane scrollPaneMain;
    public Label labelEmpty;
    public ScrollPane mainScrollPane;
    public BorderPane borderPaneNew;
    public VBox vbox;
    public TextField taskTextField;
    public Button canselTaskBtn;
    // public VBox vbox;

    public void addGoalBtnClicked() {
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm2.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane.setCenter(root);
    }

    public void setInfoGoal(){
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane.setCenter(root);
    }

    public void canselDataClicked() {
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane2.setCenter(root);
    }
    
    public void addDataClicked() {
        ConvertorDate convertorDate = new ConvertorDate();
        Date date = convertorDate.convertToDateViaInstant(dateGoal.getValue());
        //Date date = convertorDate.convertToDateViaSqlDate(dateGoal.getValue());
        System.out.println(date);
        String name_goal = goalTextField.getText();
        String category = (String) chooseCategory.getSelectionModel().getSelectedItem();

        GlobalRepo connect = new GlobalRepo();
        if (!category.isEmpty() && !name_goal.isEmpty()){

            connect.insertGoal(name_goal, category, date);
            Parent root = null;
            try {
                root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
            } catch (Exception ignored) {
            }
            goalBorderPane2.setCenter(root);
        }
        else{
            labelEmpty.setTextFill(Paint.valueOf("#888888"));
        }
    }

    public void addTaskClicked(MouseEvent mouseEvent) {
        String name_task = taskTextField.getText();

        GlobalRepo connect = new GlobalRepo();
        if (!name_task.isEmpty()){
            connect.insertTasks(name_task);
        }
        else {
            labelEmpty.setTextFill(Paint.valueOf("#888888"));
        }
    }

    public void canselTaskClicked(MouseEvent mouseEvent) {
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane3.setCenter(root);
    }

    public void addGoalBtnExited(MouseEvent mouseEvent) {
        addGoalBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void addGoalBtnMoved(MouseEvent mouseEvent) {
        addGoalBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void canselDataExited(MouseEvent mouseEvent) {
        canselDataBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void addDataExited(MouseEvent mouseEvent) {
        addTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void canselDataMoved(MouseEvent mouseEvent) {
        canselDataBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void addDataMoved(MouseEvent mouseEvent) {
        addTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void canselTaskExited(MouseEvent mouseEvent) {
        canselTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void canselTaskMoved(MouseEvent mouseEvent) {
        canselTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }
}
