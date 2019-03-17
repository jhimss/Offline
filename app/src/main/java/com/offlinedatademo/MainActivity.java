package com.offlinedatademo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.offlinedatademo.room.DatabaseClient;
import com.offlinedatademo.room.Task;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTasks();
        
    }

    private void getTasks() {

        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(MainActivity.this)
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @SuppressLint("SetTextI18n")
            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);


                Toast.makeText(MainActivity.this, ""+tasks.size(), Toast.LENGTH_SHORT).show();

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
