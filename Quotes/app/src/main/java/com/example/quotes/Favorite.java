package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quotes.database.QuoteDatabase;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {
 ArrayList<Quote>quote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        QuoteDatabase quoteDatabase= QuoteDatabase.getInstance(this);
        quote=new ArrayList<>();
        quote.addAll((quoteDatabase.quoteDao().getAllFavQuote()));
        RecyclerView favList=findViewById(R.id.favList);
        FavAdapter favAdapter=new FavAdapter(quote);
        favList.setLayoutManager(new LinearLayoutManager(this));
        favList.setAdapter(favAdapter);
    }
}