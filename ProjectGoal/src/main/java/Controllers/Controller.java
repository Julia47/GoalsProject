package Controllers;

import Entity.Goal;
import Entity.Task;
import Main.GlobalRepo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable  {
    public BorderPane goalBorderPane;
    public Pane goalPane;
    public VBox vbox;

    public static String id_goals;

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

            Pane pane = new Pane();
            pane.setPrefWidth(400);
            pane.setPrefHeight(40);
            pane.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
            pane.getChildren().add(labelGoal);


            addTaskBtn.setText("+");
            addTaskBtn.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 1px; -fx-border-color:  #b6f2e1");
            addTaskBtn.setTextFill(Color.web("#b6f2e1"));
            addTaskBtn.setPrefWidth(40);
            addTaskBtn.setPrefHeight(40);

            addTaskBtn.setOnAction((event) -> {
                Parent root = null;
                try {
                    root= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GoalForms/GoalForm3.fxml")));
                } catch (Exception ignored) {
                }
                id_goals = goals.getId();
                goalBorderPane.setCenter(root);
                System.out.println("Button Action");
            });

           // Image imageDecline = new Image(getClass().getResourceAsStream("not.png"));

            hBox.getChildren().addAll(pane, addTaskBtn);
            vbox.getChildren().add(hBox);

            List<Task> tasks;
            tasks = connect.selectTasks(goals.getId());
            for (Task task : tasks){
                Label labelTask = new Label();
                labelTask.setText("     - "+task.getName_task());
                labelTask.setTextFill(Color.web("#b6f2e1"));
                labelTask.setFont(new Font("System", 18));
                labelTask.setPadding(new Insets(8,8,8,8));

                Pane pane2 = new Pane();
                pane2.setPrefWidth(440);
                pane2.setPrefHeight(40);
                pane2.setStyle("-fx-background-color: #0b253a;; -fx-border-width: 0.4px; -fx-border-color:  #b6f2e1");
                pane2.getChildren().add(labelTask);
                HBox hBox2 = new HBox();
                hBox2.getChildren().add(pane2);
                vbox.getChildren().add(hBox2);
            }
        }
    }
}
