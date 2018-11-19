package com.saifi369.olschoolnotes.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {


    @TypeConverter
    public static Long toTimestamp(Date date){
        return date==null? null : date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp==null? null : new Date(timestamp);
    }

}
