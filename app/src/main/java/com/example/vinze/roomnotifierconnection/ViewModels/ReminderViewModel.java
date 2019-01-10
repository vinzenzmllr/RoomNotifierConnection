package com.example.vinze.roomnotifierconnection.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.vinze.roomnotifierconnection.Entities.Reminder;
import com.example.vinze.roomnotifierconnection.Repositories.ReminderRepository;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {

    private ReminderRepository repository;
    private LiveData<List<Reminder>> allReminders;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        repository = new ReminderRepository(application);
        allReminders = repository.getAllReminders();
    }

    public long insert(Reminder reminder) {
        repository.insert(reminder);
        return reminder.getId();

    }

    public void update(Reminder reminder) {
        repository.update(reminder);
    }

    public void delete(Reminder reminder) {
        repository.delete(reminder);
    }

    public void deleteAllReminders() {
        repository.deleteAllReminders();
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }
}
