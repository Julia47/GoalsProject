package Entity;

public class Task {
    private String id;
    private String id_goals;
    private String name_task;
    private String time;
    private Integer amount;
    private String weekday;

    public Task(String id, String id_goals, String name_task, String time,  Integer amount, String weekday) {
        this.id = id;
        this.id_goals = id_goals;
        this.name_task = name_task;
        this.time=time;
        this.amount = amount;
        this.weekday = weekday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_goals() {
        return id_goals;
    }

    public void setId_goals(String id_goals) {
        this.id_goals = id_goals;
    }

    public String getName_task() {
        return name_task;
    }

    public void setName_task(String name_task) {
        this.name_task = name_task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time_start) {
        this.time = time;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
