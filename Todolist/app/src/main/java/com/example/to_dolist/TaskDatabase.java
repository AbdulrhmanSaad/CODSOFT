package com.example.to_dolist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Task.class,version = 2)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;
    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(Context context){
         if(instance==null){
             instance= Room.databaseBuilder(context,TaskDatabase.class,"tasks")
                     .fallbackToDestructiveMigration()
                     .allowMainThreadQueries()
                     .build();
         }
         return instance;
    }

}
