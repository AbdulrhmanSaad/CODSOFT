package com.example.to_dolist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TaskDao {
    @Insert
    void insetTask(Task task);
@Query("Select * from  tasks where status=:status")
    List<Task>getTasks(int status);
@Query("delete from tasks where id=:id")
void deleteTask(int id);
@Query("update tasks set title=:title ,description=:des,status=:status where id=:id")
void updateTask(int id,String title,String des,int status);



}
