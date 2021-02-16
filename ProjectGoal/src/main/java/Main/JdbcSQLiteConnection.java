package Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSQLiteConnection {

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
