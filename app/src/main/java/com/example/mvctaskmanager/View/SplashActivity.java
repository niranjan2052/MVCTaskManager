package com.example.mvctaskmanager.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvctaskmanager.MainActivity;
import com.example.mvctaskmanager.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent iHome = new Intent(SplashActivity.this, MainActivity.class);
        Intent iLogin = new Intent(SplashActivity.this, LoginActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                String checkbox = preferences.getString("remember", "");

                if (checkbox.equals("true")) {
//            Toast.makeText(LoginActivity.this, "Welcome to Home Page", Toast.LENGTH_SHORT).show();
                    startActivity(iHome);
                    finish();
                } else {
//            Toast.makeText(LoginActivity.this, "Please! Log In", Toast.LENGTH_SHORT).show();
                    startActivity(iLogin);
                    finish();
                }
            }
        }, 2000);
    }
}