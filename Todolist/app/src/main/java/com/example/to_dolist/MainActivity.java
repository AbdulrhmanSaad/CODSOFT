package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
     RecyclerView tasks,completed;
    public static TasksAdapter adapter;
    public static ArrayList<Task>tasksList;
    public static ArrayList<Task>completedTask;
    public static TasksAdapter tasksAdapterComplete;
    public  static TextView tasksNumber;
     OnItemClick onItemClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TaskDatabase taskDatabase=TaskDatabase.getInstance(this);
        tasksList=new ArrayList<>();
        completedTask=new ArrayList<>();
        tasksNumber=findViewById(R.id.tasksNumber);
        tasksList.addAll(taskDatabase.taskDao().getTasks(0));
        completedTask.addAll(taskDatabase.taskDao().getTasks(1));
        if(tasksList.size()==0)
            tasksNumber.setText("Add your tasks.");
        else
            tasksNumber.setText((tasksList.size())+" Tasks are pending.");

        Collections.reverse(tasksList);
        Collections.reverse(completedTask);
        onItemClick= (pos, stat) -> {
            Intent intent=new Intent(MainActivity.this,TaskDetails.class);
            intent.putExtra("id",pos);
            intent.putExtra("stat",stat);
            startActivity(intent);
        };
        adapter=new TasksAdapter(this,tasksList,onItemClick);
        tasksAdapterComplete=new TasksAdapter(this,completedTask,onItemClick);
        tasks=findViewById(R.id.task);
        tasks.setLayoutManager(new LinearLayoutManager(this));
        tasks.setAdapter(adapter);
        completed=findViewById(R.id.completed);
        completed.setLayoutManager(new LinearLayoutManager(this));
        completed.setAdapter(tasksAdapterComplete);


        FloatingActionButton add=findViewById(R.id.addTask);
        add.setOnClickListener(view-> {
            AddTaskFragment addTaskFragment=new AddTaskFragment();
            addTaskFragment.show(getSupportFragmentManager(),addTaskFragment.getTag());
        });
    }
}