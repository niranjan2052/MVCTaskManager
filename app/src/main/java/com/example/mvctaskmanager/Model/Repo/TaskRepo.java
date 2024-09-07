package com.example.mvctaskmanager.Model.Repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvctaskmanager.Model.Beans.Task;
import com.example.mvctaskmanager.Model.Dao.TaskDao;
import com.example.mvctaskmanager.Model.Database.TaskDatabase;

import java.util.List;

public class TaskRepo {
    private TaskDao taskDao;
    private LiveData<List<Task>> taskList;

    public TaskRepo(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.taskDao();
        taskList = taskDao.getAllData();
    }

    public void insertData(Task task) {
        new InsertTask(taskDao).execute(task);
    }

    public void updateData(Task task) {
        new UpdateTask(taskDao).execute(task);
    }

    public void deleteData(Task task) {
        new DeleteTask(taskDao).execute(task);
    }

    public LiveData<List<Task>> getAllData() {
        return taskList;
    }

    private static class InsertTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        public InsertTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        public UpdateTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        public DeleteTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }
}
