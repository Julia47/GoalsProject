package Entity;

import java.time.LocalDate;
import java.util.Date;

public class Goal {
    private String name_goal;
    private String category;
    private Date date_goal;
    private String id;
    private String id_user;

    public Goal(String name_goal, String category, Date date_goal, String id, String id_user) {
        this.name_goal = name_goal;
        this.category = category;
        this.date_goal = date_goal;
        this.id = id;
        this.id_user = id_user;
    }

    public String getName_goal() {
        return name_goal;
    }

    public void setName_goal(String name_goal) {
        this.name_goal = name_goal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate_goal() {
        return date_goal;
    }

    public void setDate_goal(Date date_goal) {
        this.date_goal = date_goal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
