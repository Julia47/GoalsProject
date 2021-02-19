package Entity;

public class Task {
    private String id;
    private String id_goals;
    private String name_task;
    private String time_start;
    private String time_end;
    private Integer amount;
    private String weekday;

    public Task(String id, String id_goals, String name_task, String time_start, String time_end, Integer amount, String weekday) {
        this.id = id;
        this.id_goals = id_goals;
        this.name_task = name_task;
        this.time_start = time_start;
        this.time_end = time_end;
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

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
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
