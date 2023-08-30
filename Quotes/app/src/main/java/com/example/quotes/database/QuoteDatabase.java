package com.example.quotes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quotes.Quote;

@Database(entities = Quote.class, version = 1)
public abstract class QuoteDatabase extends RoomDatabase {
    private static QuoteDatabase instance;

    public abstract QuoteDao quoteDao();

    public static synchronized QuoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, QuoteDatabase.class, "Quote")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
