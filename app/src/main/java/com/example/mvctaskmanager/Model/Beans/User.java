package com.example.mvctaskmanager.Model.Beans;

import android.text.TextUtils;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User implements IUser {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String email;
    private String password;
    private boolean isActive;

    @Ignore
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Ignore
    public User(int id, String username, String email, String password, boolean isActive) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    public User(String username, String email, String password, boolean isActive) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int isValid() {
        if (TextUtils.isEmpty(getUsername())) {
            return 0;
        } else if (TextUtils.isEmpty(getPassword())) {
            return 1;
        } else {
            return -1;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
