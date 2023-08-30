package com.example.to_dolist;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
public class AddTaskFragment extends BottomSheetDialogFragment {
private EditText title,description;
Button add;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title=view.findViewById(R.id.taskTitle);
        description=view.findViewById(R.id.description);
        add=view.findViewById(R.id.add);

            add.setOnClickListener(view1 -> {
            String taskTitle =title.getText().toString();
            String des=description.getText().toString();
            if(taskTitle.length()>0) {
                TaskDatabase taskDatabase = TaskDatabase.getInstance(getContext());
                taskDatabase.taskDao().insetTask(new Task(taskTitle, des, 0));
                MainActivity.tasksList.add(new Task(taskTitle, des, 0));
                 MainActivity.adapter.setData(MainActivity.tasksList);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getContext(),"Enter title for task", Toast.LENGTH_LONG).show();
            }
        });



    }
}