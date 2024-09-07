package com.example.mvctaskmanager.Controller;

import com.example.mvctaskmanager.Model.Beans.User;
import com.example.mvctaskmanager.View.ILoginView;

public class LoginController implements ILoginController {

    ILoginView loginView;

    public LoginController(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void OnLogin(String username, String password) {
        User user = new User(username, password);
        int logincode = user.isValid();

        if (logincode == 0) {
            loginView.OnLoginError("Please! Enter Username");
        } else if (logincode == 1) {
            loginView.OnLoginError("Please! Enter a password");
        } else {
            loginView.OnLoginSuccess("Login Success");
        }

    }
}
