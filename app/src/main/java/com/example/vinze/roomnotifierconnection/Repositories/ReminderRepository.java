package com.example.vinze.roomnotifierconnection.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.vinze.roomnotifierconnection.DAOs.ReminderDAO;
import com.example.vinze.roomnotifierconnection.Entities.Reminder;
import com.example.vinze.roomnotifierconnection.Databases.ReminderDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ReminderRepository {

    private ReminderDAO reminderDAO;
    private LiveData<List<Reminder>> allReminders;

    public ReminderRepository(Application application){
        ReminderDatabase reminderDatabase = ReminderDatabase.getInstance(application);
        reminderDAO = reminderDatabase.reminderDAO();
        allReminders = reminderDAO.getAllReminders();
    }

    public long insert(Reminder reminder) {
        try {
            new InsertReminderAsyncTask(reminderDAO).execute(reminder).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return reminder.getId();
    }

    public void update(Reminder reminder) {
        new UpdateReminderAsyncTask(reminderDAO).execute(reminder);
    }

    public void delete(Reminder reminder) {
        new DeleteReminderAsyncTask(reminderDAO).execute(reminder);
    }

    public void deleteAllReminders() {
        new DeleteAllRemindersAsyncTask(reminderDAO).execute();
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    private class InsertReminderAsyncTask extends AsyncTask<Reminder, Void, Void> {

        private ReminderDAO reminderDAO;
        private Reminder reminder;
        private long insertId = -1;

        InsertReminderAsyncTask(ReminderDAO reminderDAO) {
            this.reminderDAO = reminderDAO;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {
            //reminderDAO.insert(reminders[0]);
            this.reminder = reminders[0];
            this.insertId = reminderDAO.insert(reminder);
            reminder.setId(this.insertId);
            return null;
        }

    }



    private static class UpdateReminderAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDAO reminderDAO;

        private UpdateReminderAsyncTask(ReminderDAO reminderDAO) {
            this.reminderDAO = reminderDAO;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDAO.update(reminders[0]);
            return null;
        }
    }

    private static class DeleteReminderAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDAO reminderDAO;

        private DeleteReminderAsyncTask(ReminderDAO reminderDAO) {
            this.reminderDAO = reminderDAO;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDAO.delete(reminders[0]);
            return null;
        }
    }


    private static class DeleteAllRemindersAsyncTask extends AsyncTask<Void, Void, Void> {
        private ReminderDAO reminderDAO;

        private DeleteAllRemindersAsyncTask(ReminderDAO reminderDAO) {
            this.reminderDAO = reminderDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            reminderDAO.deleteAllReminders();
            return null;
        }


    }
}
