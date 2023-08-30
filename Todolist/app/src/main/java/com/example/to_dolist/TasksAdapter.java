package com.example.to_dolist;

import android.app.AlertDialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    ArrayList<Task>tasks;
    Context context;
    OnItemClick onItemClick;

    public TasksAdapter( Context context,ArrayList<Task> tasks,OnItemClick onItemClick) {
        this.tasks = tasks;
        this.context=context;
        this.onItemClick=onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos =position;
        SpannableString spannableString = new SpannableString(tasks.get(pos).getTitle());
        spannableString.setSpan(new StrikethroughSpan()
                , 0
                ,tasks.get(pos).getTitle().length()
                , SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);


        holder.checkBox.setText(tasks.get(position).getStatus()
                ==0?tasks.get(position).getTitle()
                :spannableString);
       holder.checkBox.setChecked(isChecked(tasks.get(position).getStatus()));
       holder.checkBox.setOnClickListener(view -> {
           int stat=tasks.get(pos).getStatus();
           if(stat==0) {
               tasks.get(pos).setStatus(1);
               holder.checkBox.setChecked(true);
               Toast.makeText(context,"Done", Toast.LENGTH_SHORT).show();
               Task t = new Task(tasks.get(pos).getTitle(), tasks.get(pos).getDescription()
                       , tasks.get(pos).getStatus());
               TaskDatabase taskDatabase = TaskDatabase.getInstance(holder.itemView.getContext());
               taskDatabase.taskDao().updateTask(tasks.get(pos).getId()
                       , tasks.get(pos).getTitle()
                       , tasks.get(pos).getDescription()
                       , tasks.get(pos).getStatus());
               tasks.remove(pos);
               MainActivity.completedTask.add(t);
           }else{
               tasks.get(pos).setStatus(0);
               holder.checkBox.setChecked(false);
               Task t = new Task(tasks.get(pos).getTitle(), tasks.get(pos).getDescription()
                       , tasks.get(pos).getStatus());
               TaskDatabase taskDatabase = TaskDatabase.getInstance(holder.itemView.getContext());
               taskDatabase.taskDao().updateTask(tasks.get(pos).getId()
                       , tasks.get(pos).getTitle()
                       , tasks.get(pos).getDescription()
                       , tasks.get(pos).getStatus());
               tasks.remove(pos);
               MainActivity.tasksList.add(t);
           }
           MainActivity.tasksNumber.setText(MainActivity.tasksList.size()==0?
                   "Add your tasks."
                   :""+(MainActivity.tasksList.size())+" Tasks are pending.");
           MainActivity.adapter.notifyDataSetChanged();
           MainActivity.tasksAdapterComplete.notifyDataSetChanged();

       });

        holder.itemView.setOnClickListener(view -> onItemClick.onItemClick(pos,tasks.get(pos).getStatus()));



        holder.itemView.setOnLongClickListener(view -> {
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Delete item")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        TaskDatabase taskDatabase=TaskDatabase
                                .getInstance(holder.itemView.getContext());
                        taskDatabase.taskDao().deleteTask(tasks.get(pos).getId());
                        int stat=tasks.get(pos).getStatus();
                        if(stat==1){
                            MainActivity.completedTask.remove(pos);
                        }else {
                            MainActivity.tasksList.remove(pos);
                        }
                        MainActivity.tasksNumber.setText(MainActivity.tasksList.size()==0?
                                "Add your tasks."
                                :""+(MainActivity.tasksList.size())+" Tasks are pending.");
                        MainActivity.tasksAdapterComplete.notifyDataSetChanged();
                        MainActivity.adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No",null)
                    .show();
            return true;
        });
    }
    public boolean isChecked(int n){
        return n!=0;
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkBox);
        }
    }
    public void setData(ArrayList<Task> data) {
        if (tasks != null) {
            tasks.clear();
            tasks.addAll(data);
            Collections.reverse(tasks);
            notifyDataSetChanged();
        }
    }
}
