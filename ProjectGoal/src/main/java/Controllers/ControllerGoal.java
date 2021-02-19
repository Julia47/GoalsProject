package Controllers;

import Main.GlobalRepo;
import Tools.ConvertorColor;
import Tools.ConvertorDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import java.util.Date;
import java.util.Objects;

public class ControllerGoal {

    public Pane addGoalPane;
    public BorderPane goalBorderPane;
    public Button canselDataBtn;
    public Button addTaskBtn;
    public BorderPane goalBorderPane2;
    public BorderPane goalBorderPane3;
    public TextField goalTextField;
    public DatePicker dateGoal;
    public ChoiceBox chooseCategory;
    public Label labelEmpty;
    public TextField taskTextField;
    public Button canselTaskBtn;
    public Button addDataBtn;
    public ColorPicker chooseColor;
    public Spinner<Integer> spinnerMin;
    public Spinner<Integer> spinnerHour;
    public Spinner<Integer> amountReminding;

    // checkBox.setStyle("selected-box-color: lime; box-color: red; mark-color: blue;");
    
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
        ConvertorColor convertorColor = new ConvertorColor();
        //Date date = convertorDate.convertToDateViaInstant(dateGoal.getValue());
        //Date date = convertorDate.convertToDateViaSqlDate(dateGoal.getValue());

        String name_goal = goalTextField.getText();
       // String category = (String) chooseCategory.getSelectionModel().getSelectedItem();

        String color = convertorColor.toHexString(chooseColor.getValue());
        System.out.println(color);
        System.out.println(dateGoal.getValue());

        GlobalRepo connect = new GlobalRepo();
        if (color != null && !name_goal.isEmpty() && dateGoal.getValue() != null){
            Date date = convertorDate.convertToDateViaInstant(dateGoal.getValue());
            connect.insertGoal(name_goal, color, date);
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
        Integer amount = amountReminding.getValue();
        System.out.println(amount);

        GlobalRepo connect = new GlobalRepo();
        if (!name_task.isEmpty() && amount!=0){
            connect.insertTasks(name_task, amount);
        }
        else {
            labelEmpty.setTextFill(Paint.valueOf("#888888"));
        }
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane3.setCenter(root);
    }

    public void canselTaskClicked(MouseEvent mouseEvent) {
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane3.setCenter(root);
    }

    public void canselDataExited(MouseEvent mouseEvent) {
        canselDataBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void addDataExited(MouseEvent mouseEvent) {
        addDataBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void canselDataMoved(MouseEvent mouseEvent) {
        canselDataBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void addDataMoved(MouseEvent mouseEvent) {
        addDataBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void canselTaskExited(MouseEvent mouseEvent) {
        canselTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void canselTaskMoved(MouseEvent mouseEvent) {
        canselTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void addTaskExited(MouseEvent mouseEvent) {
        addTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

    public void addTaskMoved(MouseEvent mouseEvent) {
        addTaskBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }
}
