package Main;

import Controllers.Controller;
import Entity.Goal;
import Entity.Task;
import Entity.User;
import Entity.UserHolder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void insertGoal(String name_goal, String category, String date) {
        String sql = "INSERT INTO goals(name_goal, category, date, id_user) VALUES(?,?,?,?)";
        User user = UserHolder.getInstance();

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name_goal);
            pstmt.setString(2, String.valueOf(category));
            pstmt.setString(3, String.valueOf(date));
            pstmt.setString(4, user.getId_user());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTasks(String name_tasks, Integer amount, Integer hours, Integer minutes, String weekday){
        String sql = "INSERT INTO tasks (id_goals, name_task, time, amount, weekday, done) VALUES(?,?,?,?,?,?)";
        String hour = hours.toString();
        String min = minutes.toString();
        if (hours<10){
            hour = "0" + hour;
        }
        if (minutes<10){
            min = "0" + min;
        }
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Controller.id_goals);
            pstmt.setString(2, name_tasks);
            pstmt.setString(3, hour+":"+min);
            pstmt.setInt(4, amount);
            pstmt.setString(5, weekday);
            pstmt.setString(6, "no");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTask(Integer amount, String id, String done){
        String sql = "UPDATE tasks SET amount = \""+amount+ "\" , done = \""+ done + "\" WHERE id="+id+";";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            // conn.close();
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

    public void deleteGoal(String id) {
        String sql = "DELETE FROM \"goals\" WHERE id=\"" + id + "\";";
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            // conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String id) {
        String sql = "DELETE FROM \"tasks\" WHERE id=\"" + id + "\";";
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
        String id_user = user.getId_user();
        //String id_user = "38";
        String sql = "SELECT * FROM \"goals\" WHERE id_user=" + id_user + ";";
        List<Goal> goals = new ArrayList<>();
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                goals.add(new Goal(rs.getString("name_goal"), rs.getString("category"), rs.getString("date"), rs.getString("id"), rs.getString("id_user")));
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
                tasks.add(new Task(rs.getString("id"),  rs.getString("id_goals"), rs.getString("name_task"), rs.getString("time"), rs.getInt("amount"), rs.getString("weekday"), rs.getString("done")));
                //goals.add(rs.getString("goals"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public Boolean selectUser(String email, String pass) {

        String sql = "SELECT * FROM \"user\" WHERE email=\"" + email + "\" AND pass=\"" + pass + "\";";
        User user = UserHolder.getInstance();
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setE_mail(rs.getString("email"));
                user.setId_user(rs.getString("id"));
                user.setPassw(rs.getString("pass"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (user.getId_user()!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean searchEmail(String email) {

        String sql = "SELECT email FROM \"user\" WHERE email=\"" + email +  "\";";
        String str = "" ;
        try{
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                str = rs.getString("email");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!str.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
