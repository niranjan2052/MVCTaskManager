package com.example.mvctaskmanager.Model.Repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvctaskmanager.Model.Beans.User;
import com.example.mvctaskmanager.Model.Dao.UserDao;
import com.example.mvctaskmanager.Model.Database.TaskDatabase;

import java.util.List;

public class UserRepo {

    private UserDao userDao;
    private LiveData<List<User>> userList;

    public UserRepo(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        userDao = taskDatabase.userDao();
        userList = userDao.getAllData();
    }

    public void insertData(User user) {
    }

    public void updateData(User user) {
    }

    public void deleteData(User user) {
    }

    public LiveData<List<User>> getAllData() {
        return userList;
    }

    private static class InsertUser extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public InsertUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUser extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public UpdateUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUser extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public DeleteUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
}
