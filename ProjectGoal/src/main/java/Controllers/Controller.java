package Controllers;

import Entity.Goal;
import Entity.Task;
import Main.GlobalRepo;
import Tools.ConvertDay;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable  {
    public BorderPane goalBorderPane;
    public Pane goalPane;
    public VBox vbox;

    public static String id_goals;
    public Button addGoalBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GlobalRepo connect = new GlobalRepo();
        List<Goal> us_goals;
        us_goals = connect.selectGoals();

        for (Goal goals : us_goals) {
            Label labelGoal = new Label();
            labelGoal.setText("  " + goals.getName_goal());
            labelGoal.setTextFill(Color.web("d1d1d1"));
            labelGoal.setFont(new Font("System", 22));
            labelGoal.setPadding(new Insets(4,3,5,3));
            HBox hBox = new HBox();
            Button addTaskBtn = new Button();
            Button deleteGoal = new Button();

            Pane pane = new Pane();
            pane.setPrefWidth(360);
            pane.setPrefHeight(40);
            pane.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
            pane.getChildren().add(labelGoal);

            Pane datePane = new Pane();
            datePane.setPrefWidth(120);
            datePane.setPrefHeight(40);
            datePane.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");

            Label labelDate = new Label();
            labelDate.setText("  " + goals.getDate_goal());
            labelDate.setTextFill(Color.web("d1d1d1"));
            labelDate.setFont(new Font("System", 16));
            labelDate.setPadding(new Insets(4,3,5,3));

            datePane.getChildren().add(labelDate);

            addTaskBtn.setText("+");
            addTaskBtn.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
            addTaskBtn.setTextFill(Color.web("#b6f2e1"));
            addTaskBtn.setPrefWidth(40);
            addTaskBtn.setPrefHeight(40);
            deleteGoal.setText("-");
            deleteGoal.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
            deleteGoal.setTextFill(Color.web("#b6f2e1"));
            deleteGoal.setPrefWidth(40);
            deleteGoal.setPrefHeight(40);

            addTaskBtn.setOnAction((event) -> {
                Parent root = null;
                try {
                    root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm3.fxml")));
                } catch (Exception ignored) {
                }
                id_goals = goals.getId();
                goalBorderPane.setCenter(root);
            });

            deleteGoal.setOnAction((event) -> {
                connect.deleteGoal(goals.getId());

                Parent root = null;
                try {
                    root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
                } catch (Exception ignored) {
                }
                goalBorderPane.setCenter(root);
            });

           // Image imageDecline = new Image(getClass().getResourceAsStream("not.png"));

            hBox.getChildren().addAll(addTaskBtn, pane, datePane, deleteGoal );
            vbox.getChildren().add(hBox);

            List<Task> tasks;
            tasks = connect.selectTasks(goals.getId());
            for (Task task : tasks){

                ConvertDay convertDay = new ConvertDay();
                String day_now = convertDay.getConvertWeekdayNow();
                boolean find_day = task.getWeekday().contains(day_now);

                Label labelTask = new Label();
                labelTask.setText("     - "+task.getName_task());
                labelTask.setTextFill(Color.web("#b6f2e1"));
                labelTask.setFont(new Font("System", 18));
                labelTask.setPadding(new Insets(8,8,8,8));

                Pane pane2 = new Pane();
                pane2.setPrefWidth(300);
                pane2.setPrefHeight(40);
                pane2.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                if (find_day){
                    pane2.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                }
                else {
                    pane2.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                }

                Label labelAmount = new Label();
                labelAmount.setText(task.getAmount().toString());
                labelAmount.setTextFill(Color.web("#b6f2e1"));
                labelAmount.setFont(new Font("System", 20));
                labelAmount.setPadding(new Insets(8,8,8,13));

                Pane colorpane = new Pane();
                colorpane.setPrefWidth(40);
                colorpane.setPrefHeight(40);
                colorpane.setStyle("-fx-background-color:" + goals.getCategory() + ";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                colorpane.getChildren().add(labelAmount);

                Label labelWeekday = new Label();
                labelWeekday.setText(task.getWeekday());
                labelWeekday.setTextFill(Color.web("#b6f2e1"));
                labelWeekday.setFont(new Font("System", 10));
                labelWeekday.setPadding(new Insets(8,8,8,8));

                Pane weekdayPane = new Pane();
                weekdayPane.setPrefWidth(120);
                weekdayPane.setPrefHeight(40);
                if (find_day){
                    weekdayPane.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                }
                else {
                    weekdayPane.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                }
                weekdayPane.getChildren().add(labelWeekday);

                Label labelTime = new Label();
                labelTime.setText(task.getTime());
                labelTime.setTextFill(Color.web("#b6f2e1"));
                labelTime.setFont(new Font("System", 17));
                labelTime.setPadding(new Insets(8,8,8,8));

                Pane timePane = new Pane();
                timePane.setPrefWidth(60);
                timePane.setPrefHeight(40);
                if (find_day){
                    timePane.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                }
                else {
                    timePane.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                }
                timePane.getChildren().add(labelTime);

                Button deleteTask = new Button();
                deleteTask.setText("-");
                deleteTask.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
                deleteTask.setTextFill(Color.web("#b6f2e1"));
                deleteTask.setPrefWidth(40);
                deleteTask.setPrefHeight(40);

                deleteTask.setOnAction((event) -> {
                    connect.deleteTask(task.getId());

                    Parent root = null;
                    try {
                        root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm1.fxml")));
                    } catch (Exception ignored) {
                    }
                    goalBorderPane.setCenter(root);
                });

                pane2.getChildren().add(labelTask);
                HBox hBox2 = new HBox();
                hBox2.getChildren().addAll( colorpane, pane2, timePane, weekdayPane, deleteTask);
                vbox.getChildren().add(hBox2);
            }
        }
    }



    public void addGoalClicked(MouseEvent mouseEvent) {
        Parent root = null;
        try {
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm2.fxml")));
        } catch (Exception ignored) {
        }
        goalBorderPane.setCenter(root);
    }

    public void addGoalMoved(MouseEvent mouseEvent) {
        addGoalBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 3px; -fx-border-color:  #b6f2e1");
    }

    public void addGoalExited(MouseEvent mouseEvent) {
        addGoalBtn.setStyle("-fx-background-color: #0b253a; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
    }

}
