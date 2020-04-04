package ro.pao.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class UtilDate {

    public static Date onlyDate(Date date) {
        if (date == null) {
            return null;
        }
        return DateUtils.truncate(date, Calendar.DATE);
    }

}
