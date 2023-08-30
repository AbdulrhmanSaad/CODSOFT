package com.example.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    ArrayList<Quote> quotes;

    public FavAdapter(ArrayList<Quote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.bind(quotes.get(position));
    }

    @Override
    public int getItemCount() {
        return quotes == null ? 0 : quotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView quoteContent,quoteAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quoteContent=itemView.findViewById(R.id.quote);
            quoteAuthor=itemView.findViewById(R.id.author);
        }

        public void bind(Quote quote) {
            quoteContent.setText(quote.getContent());
            quoteAuthor.setText(quote.getAuthor());
        }
    }
}
