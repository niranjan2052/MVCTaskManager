package com.example.mvctaskmanager.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvctaskmanager.Controller.ILoginController;
import com.example.mvctaskmanager.Controller.LoginController;
import com.example.mvctaskmanager.MainActivity;
import com.example.mvctaskmanager.Model.Beans.User;
import com.example.mvctaskmanager.Model.Repo.UserRepo;
import com.example.mvctaskmanager.R;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    EditText edtUsername;
    EditText edtPassword;
    CheckBox chkRemember;
    Button btnLogin;
    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Code for inserting data inside UserRepo
//        UserRepo userRepo = new UserRepo(this.getApplication());
//        userRepo.insertData(new User("admin","admin@gmail.com","admin",true));

        loginController = new LoginController(LoginActivity.this, this.getApplication());
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById((R.id.loginBtn));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginController.OnLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });

        chkRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                } else {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }
        });
    }


    @Override
    public void OnLoginSuccess(String message) {
        Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(iHome);
        finish();
    }

    @Override
    public void OnLoginError(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}