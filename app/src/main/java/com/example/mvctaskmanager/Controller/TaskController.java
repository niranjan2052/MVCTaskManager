package com.example.mvctaskmanager.Controller;

import android.app.Application;
import android.widget.Toast;

import com.example.mvctaskmanager.Model.Beans.Task;

public class TaskController implements ITaskController{

    Application application;
    TaskController(Application application){
        this.application = application;
    }
    @Override
    public void OnAddTask(Task task) {
        Toast.makeText(application, "Add Task Button Clicked", Toast.LENGTH_SHORT).show();
    }
}
