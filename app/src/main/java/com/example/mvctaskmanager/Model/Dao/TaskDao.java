package com.example.mvctaskmanager.Model.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvctaskmanager.Model.Beans.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    public void insert(Task task);

    @Update
    public void update(Task task);

    @Delete
    public void delete(Task task);

    @Query("SELECT * FROM task_table")
    public LiveData<List<Task>> getAllData();
}
