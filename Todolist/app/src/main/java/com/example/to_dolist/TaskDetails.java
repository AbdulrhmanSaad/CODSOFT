package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TaskDetails extends AppCompatActivity {
    EditText editTitle,editDes;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        editTitle=findViewById(R.id.editTitle);
        editDes=findViewById(R.id.editDesc);
        save=findViewById(R.id.save);

        Intent intent=getIntent();
        int id=intent.getIntExtra("id",-1);
        int stat=intent.getIntExtra("stat",-1);

        String title;
        String des;
        if(stat==0) {
           title = MainActivity.tasksList.get(id).getTitle();
             des = MainActivity.tasksList.get(id).getDescription();
        }else{
             title = MainActivity.completedTask.get(id).getTitle();
             des = MainActivity.completedTask.get(id).getDescription();
        }
        editTitle.setText(title);
        editDes.setText(des);

        save.setOnClickListener(view->{
           String titleAfterEdit=editTitle.getText().toString();
           String desAfterEdit=editDes.getText().toString();
           TaskDatabase taskDatabase=TaskDatabase.getInstance(this);
           if(stat==0) {
               taskDatabase.taskDao().updateTask(MainActivity.tasksList.get(id).getId()
                       , titleAfterEdit
                       , desAfterEdit
                       , MainActivity.tasksList.get(id).getStatus());

               MainActivity.tasksList.set(id, new Task(titleAfterEdit
                       , desAfterEdit
                       , MainActivity.tasksList.get(id).getStatus()));
               MainActivity.adapter.notifyDataSetChanged();

           }
           else if(stat==1){
               taskDatabase.taskDao().updateTask(MainActivity.completedTask.get(id).getId()
                       , titleAfterEdit
                       , desAfterEdit
                       , MainActivity.completedTask.get(id).getStatus());

               MainActivity.completedTask.set(id, new Task(titleAfterEdit
                       , desAfterEdit
                       , MainActivity.completedTask.get(id).getStatus()));
               MainActivity.tasksAdapterComplete.notifyDataSetChanged();
           }
            Intent intent1=new Intent(TaskDetails.this,MainActivity.class);
           startActivity(intent1);

        });
    }
}