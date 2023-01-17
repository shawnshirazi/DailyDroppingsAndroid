package com.example.dailydroppings.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.*

class DateTypeConverter {
//    @RequiresApi(Build.VERSION_CODES.O)
//    @TypeConverter
//    fun fromTimestamp(value: Long?): LocalDateTime? {
//        return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC) }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @TypeConverter
//    fun dateToTimestamp(date: LocalDateTime?): Long? {
//        return date?.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
//    }

//    @TypeConverter
//    fun fromTimestamp(value: Long?): Date? {
//        return if (value == null) null else Date(value)
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: Date?): Long? {
//        return date?.time
//    }


    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return if (value == null) null else LocalDate.now()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(localDate: LocalDate?): Long? {
        return localDate?.toEpochDay()
    }

//    @TypeConverter
//    fun toDate(timestamp: Long?): Date? {
//        return timestamp?.let { Date(it) }
//    }
//
//    @TypeConverter
//    fun toTimestamp(date: Date?): Long? {
//        return date?.time
//    }

}