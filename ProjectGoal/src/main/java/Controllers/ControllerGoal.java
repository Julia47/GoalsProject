package Controllers;

import Main.GlobalRepo;
import Tools.ConvertorColor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import java.util.Objects;

public class ControllerGoal {

    public Pane addGoalPane;
    public Button canselDataBtn;
    public Button addTaskBtn;
    public BorderPane goalBorderPane2;
    public BorderPane goalBorderPane3;
    public TextField goalTextField;
    public DatePicker dateGoal;
    public Label labelEmpty;
    public TextField taskTextField;
    public Button canselTaskBtn;
    public Button addDataBtn;
    public ColorPicker chooseColor;
    public Spinner<Integer> spinnerMin;
    public Spinner<Integer> spinnerHour;
    public Spinner<Integer> amountReminding;
    public CheckBox MonCheck;
    public CheckBox TueCheck;
    public CheckBox SatCheck;
    public CheckBox FriCheck;
    public CheckBox ThuCheck;
    public CheckBox WedCheck;
    public CheckBox SunCheck;
    public Label invalidDate;

    public void canselDataClicked() {
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane2.setCenter(root);
    }
    
    public void addDataClicked() {

        ConvertorColor convertorColor = new ConvertorColor();

        String name_goal = goalTextField.getText();

        String color = convertorColor.toHexString(chooseColor.getValue());
        System.out.println(color);
        System.out.println(dateGoal.getValue());
        String date = dateGoal.getValue().toString();
        int date_n = Integer.parseInt(parseDate(date)[0]);
        int date_m = Integer.parseInt(parseDate(date)[1]);
        int date_d = Integer.parseInt(parseDate(date)[2]);

        GlobalRepo connect = new GlobalRepo();
        if (color != null && !name_goal.isEmpty() && dateGoal.getValue() != null && date_n>=2021 ){

            connect.insertGoal(name_goal, color, date);
            Parent root = null;
            try {
                root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
            } catch (Exception ignored) {
            }
            goalBorderPane2.setCenter(root);
        }
        else{
            if (date_n<2021){
                invalidDate.setTextFill(Paint.valueOf("#888888"));
            }
            labelEmpty.setTextFill(Paint.valueOf("#888888"));
        }
    }

    public String[] parseDate(String date){
        String[] str;
        str = date.split("-");
        return str;
    }

    public String generateWeekday(){
        String weekday = "";
        System.out.println(MonCheck.isSelected());
        if(SunCheck.isSelected()){
            weekday = weekday + SunCheck.getText()+" ";
        }
        if(MonCheck.isSelected()){
            weekday = weekday + MonCheck.getText()+" ";
        }
        if(TueCheck.isSelected()){
            weekday = weekday + TueCheck.getText()+" ";
        }
        if(WedCheck.isSelected()){
            weekday = weekday + WedCheck.getText()+" ";
        }
        if(ThuCheck.isSelected()){
            weekday = weekday + ThuCheck.getText()+" ";
        }
        if(FriCheck.isSelected()){
            weekday = weekday + FriCheck.getText()+" ";
        }
        if(SatCheck.isSelected()){
            weekday = weekday + SatCheck.getText()+" ";
        }
        return weekday;
    }

    public void addTaskClicked(MouseEvent mouseEvent) {
        String name_task = taskTextField.getText();
        Integer amount = amountReminding.getValue();
        Integer hours = spinnerHour.getValue();
        Integer minutes = spinnerMin.getValue();
        String weekday = generateWeekday();

        GlobalRepo connect = new GlobalRepo();
        if (!name_task.isEmpty() && amount!=0 && !weekday.isEmpty() ){
            connect.insertTasks(name_task, amount, hours, minutes, weekday);
            Parent root = null;
            try {
                root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
            } catch (Exception ignored) {
            }
            goalBorderPane3.setCenter(root);
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
