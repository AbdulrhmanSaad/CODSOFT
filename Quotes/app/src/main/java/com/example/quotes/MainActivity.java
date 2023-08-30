package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotes.database.QuoteDatabase;
import com.example.quotes.retrofit.RetrofitQuote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
String q;
ImageView share,saved;
TextView quoteText,category,authorName;
Button favorite;
Quote quote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         quoteText=findViewById(R.id.test);
         share=findViewById(R.id.share);
         favorite=findViewById(R.id.fav);
         category=findViewById(R.id.cat);
         authorName=findViewById(R.id.authorname);
         saved=findViewById(R.id.saved);



        QuoteDatabase quoteDatabase=QuoteDatabase.getInstance(this);

        Call<Quote> quatecall= RetrofitQuote.getInstance().getRandomQuote();
        quatecall.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                quote= response.body();
                Log.d("+++",response.body().getTags().toString());
                quoteText.setText(quote.getContent());
                q=response.body().getContent();
                category.setText(response.body().getTags().get(0));
                authorName.setText(response.body().getAuthor());
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Toast.makeText(MainActivity.this
                        , "No Internet Connection"
                        , Toast.LENGTH_SHORT).show();
                quoteText.setText("Difficulties are things that show a person what they are.");
                category.setText("Motivation");
                authorName.setText("Epictetus");
            }
        });

        favorite.setOnClickListener(view -> {
            favorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_bookmark_24
                    ,0,0,0);
            if(quote!=null) {
                quoteDatabase.quoteDao().insertFavQuote(quote);
            }
            Toast.makeText(this, "Quote Saved", Toast.LENGTH_LONG).show();
        });
        saved.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Favorite.class);
            startActivity(intent);
        });


        share.setOnClickListener(view -> {
            shareQuote(q);
        });

    }

    private void shareQuote(String quote) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, quote);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}