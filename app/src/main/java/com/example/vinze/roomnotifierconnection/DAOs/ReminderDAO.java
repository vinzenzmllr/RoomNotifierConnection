package com.example.vinze.roomnotifierconnection.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.vinze.roomnotifierconnection.Entities.Reminder;

import java.util.List;

@Dao
public interface ReminderDAO {

    @Insert
    long insert(Reminder reminder);

    @Update
    void update(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

    @Query("DELETE FROM r_reminder")
    void deleteAllReminders();

    @Query("SELECT * FROM r_reminder")
    LiveData<List<Reminder>> getAllReminders();

}
