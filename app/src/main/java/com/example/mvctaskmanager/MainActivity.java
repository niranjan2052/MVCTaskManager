package com.example.mvctaskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvctaskmanager.Controller.RecyclerTaskAdapter;
import com.example.mvctaskmanager.Model.Beans.Task;
import com.example.mvctaskmanager.Model.Database.TaskDatabase;
import com.example.mvctaskmanager.Model.Repo.TaskRepo;
import com.example.mvctaskmanager.View.LoginActivity;
import com.example.mvctaskmanager.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> arrayTaskList = new ArrayList<>();
    RecyclerTaskAdapter adapter;
    ActivityMainBinding binding;
    View emptyStateLayout;

    Toolbar toolbar;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Toolbar Integration
        toolbar = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        // Recycler View and EmptyState layout
        recyclerView = findViewById(R.id.recyclerTaskList);
        emptyStateLayout = findViewById(R.id.empty_state_layout);

        //View Integration
        ImageView menuBtn = findViewById(R.id.menu_icon);
        TextView toolBarTitle = findViewById(R.id.toolbar_title);
        FloatingActionButton btnOpenAddTaskDialogBox = findViewById(R.id.addTaskDialogBox);

        //attaching layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {
                    item.setChecked(true);
//                    Toast.makeText(MainActivity.this, "Home Page Selected", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    return true;
                } else if (itemId == R.id.menu_logout) {
//                    item.setChecked(true);
//                    Toast.makeText(MainActivity.this, "LogOut Selected", Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    drawerLayout.closeDrawers();
                    Intent iLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(iLogin);
                    finish();
                    return true;
                } else if (itemId == R.id.menu_exit) {
                    finishAffinity();
                    return true;
                }
                return false;
            }
        });

        menuBtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });
        //Code to add new data to Task

        TaskRepo taskRepo = new TaskRepo(this.getApplication());
        List<Task> a = new ArrayList<>();
        a.add(new Task("Task1","Description","SEP 10 2025",false));
        arrayTaskList.addAll(a);
        adapter = new RecyclerTaskAdapter(this, arrayTaskList);
        recyclerView.setAdapter(adapter);
        checkIfEmpty(arrayTaskList);
    }

    public void checkIfEmpty(ArrayList<Task> items) {
        if (items.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyStateLayout.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyStateLayout.setVisibility(View.GONE);
        }
    }
}