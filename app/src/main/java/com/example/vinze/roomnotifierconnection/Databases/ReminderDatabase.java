package com.example.vinze.roomnotifierconnection.Databases;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.vinze.roomnotifierconnection.DAOs.ReminderDAO;
import com.example.vinze.roomnotifierconnection.Entities.Reminder;

@Database(entities = {Reminder.class}, version = 9)
public abstract class ReminderDatabase extends RoomDatabase {

    private static ReminderDatabase instance;

    public abstract ReminderDAO reminderDAO();

    public static synchronized ReminderDatabase getInstance(Context context){
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ReminderDatabase.class, "reminderDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ReminderDAO reminderDAO;

        private PopulateDbAsyncTask(ReminderDatabase db) {
            reminderDAO = db.reminderDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
