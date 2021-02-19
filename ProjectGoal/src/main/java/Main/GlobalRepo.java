package Main;

import Controllers.Controller;
import Entity.Goal;
import Entity.Task;
import Entity.User;
import Entity.UserHolder;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GlobalRepo {

    private Connection connect() {

        String dbURL = "jdbc:sqlite:/home/julie47/JUST_DO/repo/GoalsProject/sqliteDB/goal.db";

        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public void insertUser(String username, String password, String mail) {
        String sql = "INSERT INTO user(username, pass, email) VALUES(?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, mail);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //ADD id_user
    public void insertGoal(String name_goal, String category, Date date_goal) {
        String sql = "INSERT INTO goal(name_goal, category, date_goal) VALUES(?,?,?)";
        String id_user;
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name_goal);
            pstmt.setString(2, category);
            pstmt.setString(3, String.valueOf(date_goal));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTasks(String name_tasks){
        String sql = "INSERT INTO tasks (id_goals, name_task, time_start, time_end, amount, weekday) VALUES(?,?,?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Controller.id_goals);
            pstmt.setString(2, name_tasks);
            pstmt.setString(3, null);
            pstmt.setString(4, null);
            pstmt.setString(5, null);
            pstmt.setString(6, null);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(String email) {
        String sql = "DELETE FROM \"user\" WHERE email=\"" + email + "\";";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
           // conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Goal> selectGoals() {

        User user = UserHolder.getInstance();
        //String id_user = user.getId_user();
        String id_user = "38";
        String sql = "SELECT * FROM \"goals\" WHERE id_user=" + id_user + ";";
        List<Goal> goals = new ArrayList<>();
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                goals.add(new Goal(rs.getString("name_goal"), rs.getString("category"), null, rs.getString("id"), rs.getString("id_user")));
                //goals.add(rs.getString("goals"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return goals;
    }

    public List<Task> selectTasks(String id_goals) {

        String sql = "SELECT * FROM \"tasks\" WHERE id_goals=" + id_goals + ";";
        List<Task> tasks = new ArrayList<>();
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tasks.add(new Task(rs.getString("id"),  rs.getString("id_goals"), rs.getString("name_task"), null, null, null, null));
                //goals.add(rs.getString("goals"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public List<String> selectMail() {

        String sql = "SELECT email FROM \"user\"";
        List<String> mail = new ArrayList<>();
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    mail.add(rs.getString("email"));
                }
            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mail;
    }

    public String searchName(String mail){
        String sql = "SELECT username FROM \"user\" WHERE email=\""+mail+"\";";
        String name = "";
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("username");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }

    public String selectPass(String mail) {

        String sql = "SELECT pass FROM \"user\" WHERE email=\""+mail+"\";";
        String pass = "";
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                pass = rs.getString("pass");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pass;
    }
}
