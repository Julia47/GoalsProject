package Tools;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ConvertDay {
    public String getConvertWeekdayNow(){
        DayOfWeek real_day = DayOfWeek.from( LocalDate.now());
        String day = "";
        switch (real_day.toString()) {
            case  ("MONDAY"):
                day = "Mon";
                break;
            case ("TUESDAY"):
                day = "Tue";
                break;
            case ("WEDNESDAY"):
                day = "Wed";
                break;
            case ("THURSDAY"):
                day = "Thu";
                break;
            case ("FRIDAY"):
                day = "Fri";
                break;
            case ("SATURDAY"):
                day = "Sat";
                break;
            case ("SUNDAY"):
                day = "Sun";
                break;
            default:
                System.out.println("Error");
                break;
        }
        return day;
    }
}
