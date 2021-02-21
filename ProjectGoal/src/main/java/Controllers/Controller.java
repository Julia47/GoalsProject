package Controllers;

import Entity.Goal;
import Entity.Task;
import Main.GlobalRepo;
import Tools.ConvertDay;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable  {
    public BorderPane goalBorderPane;
    public Pane goalPane;
    public VBox vbox;

    public static String id_goals;
    public Button addGoalBtn;
    public Label dataTime;
    public VBox vboxTaskNow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GlobalRepo connect = new GlobalRepo();
        List<Goal> us_goals;
        us_goals = connect.selectGoals();
        initClock();

        for (int i=us_goals.size()-1; i>0; i--) {

            addElementsToGoals(us_goals.get(i));

            List<Task> tasks;
            tasks = connect.selectTasks(us_goals.get(i).getId());
            for (Task task : tasks){
                addElementsToTasks(task, us_goals.get(i));
            }
        }
    }

    public static int j = 0;
    public static int k = 0;

    public void addElementsToTasks(Task task, Goal goals){

        ConvertDay convertDay = new ConvertDay();
        GlobalRepo connect = new GlobalRepo();
        String day_now = convertDay.getConvertWeekdayNow();
        boolean find_day = task.getWeekday().contains(day_now);

        Label labelTask = new Label();
        labelTask.setText("     - "+task.getName_task());
        labelTask.setTextFill(Color.web("#b6f2e1"));
        labelTask.setFont(new Font("System", 18));
        labelTask.setPadding(new Insets(8,8,8,8));

        Pane pane2 = new Pane();
        pane2.setPrefWidth(520);
        pane2.setPrefHeight(40);
        pane2.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
        Pane paneNow = new Pane();
        if (find_day){
            paneNow.setPrefWidth(520);
            paneNow.setPrefHeight(40);
            paneNow.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
            Label labelTaskNow = new Label();
            labelTaskNow.setText("  "+task.getName_task());
            labelTaskNow.setTextFill(Color.web("#b6f2e1"));
            labelTaskNow.setFont(new Font("System", 18));
            labelTaskNow.setPadding(new Insets(8,8,8,8));
            paneNow.getChildren().add(labelTaskNow);
            pane2.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
        }
        else {
            pane2.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
        }
        pane2.getChildren().add(labelTask);

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
        Pane weekdayPaneNow = new Pane();
        weekdayPane.setPrefWidth(120);
        weekdayPane.setPrefHeight(40);
        if (find_day){
            Label dayNow = new Label();
            dayNow.setText(day_now);
            dayNow.setTextFill(Color.web("#b6f2e1"));
            dayNow.setFont(new Font("System", 10));
            dayNow.setPadding(new Insets(8,8,8,8));
            weekdayPaneNow.getChildren().add(dayNow);
            weekdayPaneNow.setPrefWidth(40);
            weekdayPaneNow.setPrefHeight(40);
            weekdayPaneNow.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
            weekdayPane.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
        }
        else {
            weekdayPane.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
        }
        weekdayPane.getChildren().add(labelWeekday);

        Label labelTime = new Label();
        labelTime.setText(task.getTime());
        labelTime.setTextFill(Color.web("#b6f2e1"));
        labelTime.setFont(new Font("System", 16));
        labelTime.setPadding(new Insets(8,8,8,8));

        Pane timePane = new Pane();
        Pane timePaneNow = new Pane();
        timePane.setPrefWidth(60);
        timePane.setPrefHeight(40);
        if (find_day){
            timePaneNow.setPrefWidth(60);
            timePaneNow.setPrefHeight(40);
            Label labelTimeNow = new Label();
            labelTimeNow.setText(task.getTime());
            labelTimeNow.setTextFill(Color.web("#b6f2e1"));
            labelTimeNow.setFont(new Font("System", 16));
            labelTimeNow.setPadding(new Insets(8,8,8,8));
            timePaneNow.getChildren().add(labelTimeNow);
            timePaneNow.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
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

        if (find_day ){

            HBox hBoxNow = new HBox();
            if (task.getDone().equals("yes")){
                k++;
                if(k==j){
                    Label doneNow = new Label();
                    doneNow.setText("Good Job) Today all tasks are done!!");
                    doneNow.setTextFill(Color.web("#b6f2e1"));
                    doneNow.setFont(new Font("System", 23));
                    doneNow.setPadding(new Insets(8,8,8,8));

                    Pane paneDone = new Pane();
                    paneDone.setPrefWidth(500);
                    paneDone.setPrefHeight(40);
                    paneDone.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                    paneDone.getChildren().add(doneNow);
                    hBoxNow.getChildren().add(paneDone);
                }
            }
            else {

                Label doneNow = new Label();
                doneNow.setText(" Done?");
                doneNow.setTextFill(Color.web("#b6f2e1"));
                doneNow.setFont(new Font("System", 16));
                doneNow.setPadding(new Insets(8,8,8,8));
                Pane paneDone = new Pane();
                paneDone.setPrefWidth(80);
                paneDone.setPrefHeight(40);
                paneDone.setStyle("-fx-background-color:" + goals.getCategory() +";; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                paneDone.getChildren().add(doneNow);

                Button YesTask = new Button();
                YesTask.setText("Y");
                YesTask.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
                YesTask.setTextFill(Color.web("#b6f2e1"));
                YesTask.setPrefWidth(40);
                YesTask.setPrefHeight(40);

                YesTask.setOnAction((event) -> {
                    hBoxNow.getChildren().clear();
                    connect.updateTask(task.getAmount()-1, task.getId(), "yes");
                });

                Button NoTask = new Button();
                NoTask.setText("No");
                NoTask.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
                NoTask.setTextFill(Color.web("#b6f2e1"));
                NoTask.setPrefWidth(40);
                NoTask.setPrefHeight(40);

                NoTask.setOnAction((event) -> {
                    hBoxNow.getChildren().clear();
                });

                hBoxNow.getChildren().addAll(weekdayPaneNow,timePaneNow, paneNow, paneDone, YesTask, NoTask);
            }

            j++;
            vboxTaskNow.getChildren().add(hBoxNow);
        }

        if (task.getAmount()<=0){
            connect.deleteTask(task.getId());
        }

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(colorpane, pane2, timePane, weekdayPane, deleteTask);
        vbox.getChildren().add(hBox2);
    }

    public void addElementsToGoals(Goal goals){

        GlobalRepo connect = new GlobalRepo();
        HBox hBox = new HBox();
        Button addTaskBtn = new Button();
        Button deleteGoal = new Button();

        Label labelGoal = new Label();
        labelGoal.setText("  " + goals.getName_goal());
        labelGoal.setTextFill(Color.web("d1d1d1"));
        labelGoal.setFont(new Font("System", 22));
        labelGoal.setPadding(new Insets(4,3,5,3));

        Pane pane = new Pane();
        pane.setPrefWidth(580);
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDateTime.now().format(formatter);
        String date_new = goals.getDate_goal();
        if(LocalDateTime.now().format(formatter).equals(goals.getDate_goal())){
            System.out.println(date);
            System.out.println(date_new);
            pane.setStyle("-fx-background-color: #050828;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
            datePane.setStyle("-fx-background-color: #050828;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
        }

        hBox.getChildren().addAll(addTaskBtn, pane, datePane, deleteGoal );
        vbox.getChildren().add(hBox);
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dataTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
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
