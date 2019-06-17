package com.cym.sunflower.data;

import java.util.Calendar;

import androidx.room.TypeConverter;

/**
 * Type converters to allow Room to reference complex data types.
 */
public class Converters {
    @TypeConverter
    public long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalender(long value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}
