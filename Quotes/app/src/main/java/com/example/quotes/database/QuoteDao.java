package com.example.quotes.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quotes.Quote;

import java.util.List;

@Dao
public interface QuoteDao {
    @Insert
    void insertFavQuote(Quote quote);

@Query("select * from Quote")
    List<Quote>getAllFavQuote();

}
