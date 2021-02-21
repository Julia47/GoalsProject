package Tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class ConvertorDate {

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public String getMyDate(String myDate, String requiredFormat, String myFormat) {
        DateFormat dateFormat = new SimpleDateFormat(requiredFormat);
        Date date = null;
        String returnValue = "";
        try {
            date = new SimpleDateFormat(myFormat, Locale.ENGLISH).parse(myDate);
            returnValue = dateFormat.format(date);
        } catch (ParseException e) {
            returnValue = myDate;
        }
        return returnValue;
    }


}
