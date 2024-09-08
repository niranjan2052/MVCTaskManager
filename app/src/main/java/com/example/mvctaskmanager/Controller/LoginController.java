package com.example.mvctaskmanager.Controller;

import android.app.Application;
import android.content.Context;

import com.example.mvctaskmanager.Model.Beans.User;
import com.example.mvctaskmanager.Model.Dao.UserDao;
import com.example.mvctaskmanager.Model.Database.TaskDatabase;
import com.example.mvctaskmanager.Model.Repo.UserRepo;
import com.example.mvctaskmanager.View.ILoginView;
import com.example.mvctaskmanager.View.LoginActivity;

public class LoginController implements ILoginController {

    ILoginView loginView;
    UserDao userDao;

    public LoginController(ILoginView loginView, Application application) {
        this.loginView = loginView;
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        userDao = taskDatabase.userDao();
    }

    @Override
    public void OnLogin(String username, String password) {
//        User user = new User(username, password);
        if (username.isEmpty()) {
            loginView.OnLoginError("Please! Enter Username");
        } else if (password.isEmpty()) {
            loginView.OnLoginError("Please! Enter a password");
        } else {
            if (userDao.login(username, password)) {
                loginView.OnLoginSuccess("Login Success");
            } else {
                loginView.OnLoginError("Incorrect Username and Password");
            }
        }

    }
}
