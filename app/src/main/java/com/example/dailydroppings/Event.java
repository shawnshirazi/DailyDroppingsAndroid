package com.example.dailydroppings;

import static com.example.dailydroppings.CalenderUtils.selectedDate;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


@Entity
public class Event {

    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(LocalDate date)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList)
        {

            if(event.getDate().equals(date))
                events.add(event);
//            if(event.getDate().equals(date))
//                events.add(event);
        }

        return events;
    }

//    public LocalDate date;
    public LocalDate getDate()
    {
        return date;
    }

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "date")
    public LocalDate date;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "poop_type")
    public String poopType;

    @ColumnInfo(name = "poop_color")
    public int poopColor;

    @ColumnInfo(name = "poop_duration")
    public String poopDuration;

    @ColumnInfo(name = "poop_feeling")
    public String poopFeeling;



    @RequiresApi(api = Build.VERSION_CODES.O)
    public Event(String name, String poopType, int poopColor, String poopDuration, String poopFeeling, LocalDate date) {
        this.poopType = poopType;
        this.poopColor = poopColor;
        this.poopDuration = poopDuration;
        this.poopFeeling = poopFeeling;
        this.date = date;
        this.name = name;
    }



    public String getPoopType() { return poopType; }

    public void setPoopType(String poopType) { this.poopType = poopType; }


    public int getPoopColor() { return poopColor; }

    public void setPoopColor(int poopColor) { this.poopColor = poopColor; }


    public String getPoopDuration() { return poopDuration; }

    public void setPoopDuration(String poopDuration) { this.poopDuration = poopDuration; }


    public String getPoopFeeling() { return poopFeeling; }

    public void setPoopFeeling(String feeling) { this.poopFeeling = feeling; }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

//    public LocalDate getDate()
//    {
//        return date;
//    }
//
//    public void setDate(LocalDate date) { this.date = date; }

//    public LocalTime getTime() { return time; }
//
//    public void setTime(LocalTime time) { this.time = time; }


}

