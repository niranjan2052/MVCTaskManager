package com.example.mvctaskmanager.Model.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvctaskmanager.Model.Beans.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void insert(User user);

    @Update
    public void update(User user);

    @Delete
    public void delete(User user);

    @Query("SELECT * FROM user_table")
    public LiveData<List<User>> getAllData();

    @Query("SELECT EXISTS (SELECT * from user_table where username=:userName)")
    boolean is_taken(String userName);

    @Query("SELECT EXISTS (SELECT * from user_table where username=:userName AND password=:password)")
    boolean login(String userName, String password);
}
