package com.giu7.bandb.utils;

import android.arch.persistence.room.TypeConverter;

import java.time.LocalDateTime;

public class LocalDateTimeConverter {

    @TypeConverter
    public static LocalDateTime toDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            return LocalDateTime.parse(dateString);
        }
    }

    @TypeConverter
    public static String toString(LocalDateTime date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }
}
