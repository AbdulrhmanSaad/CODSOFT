package com.example.quotes.retrofit;

import com.example.quotes.Quote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApi {
    @GET("random")
    Call<Quote>getRandomQuote();

}
