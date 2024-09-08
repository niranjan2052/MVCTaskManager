package com.example.mvctaskmanager.Model.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvctaskmanager.Model.Beans.Task;
import com.example.mvctaskmanager.Model.Beans.User;
import com.example.mvctaskmanager.Model.Dao.TaskDao;
import com.example.mvctaskmanager.Model.Dao.UserDao;

@Database(entities = {User.class, Task.class}, version = 2)
public abstract class TaskDatabase extends RoomDatabase {

    // It will contain the instance of database if the database instance is already created.
    private static TaskDatabase instance;

    public abstract TaskDao taskDao();

    public abstract UserDao userDao();

    //The method is synchronized so that it don't interrupt the main thread
    public static synchronized TaskDatabase getInstance(Context context) {
        //check if there is already an instance of the database if not i.e. instance==null then created instance
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "task_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
