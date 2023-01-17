package com.example.dailydroppings.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dailydroppings.Event;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface EventDao {


    @Query("SELECT * FROM event WHERE date = :selectedDate")
    public List<Event> getAll(LocalDate selectedDate);

    @Insert
    void insertUser(Event... events);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM event")
    public void nukeTable();
}
