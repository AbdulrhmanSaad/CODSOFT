package com.example.quotes.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitQuote {
    public static QuoteApi instance;
    public static QuoteApi getInstance(){
       if(instance==null){
           Retrofit retrofit=new Retrofit.Builder()
                   .baseUrl("https://api.quotable.io/")
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
           instance=retrofit.create(QuoteApi.class);
       }
       return instance;
    }
}
