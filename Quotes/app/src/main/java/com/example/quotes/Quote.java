package com.example.quotes;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "Quote")
public class Quote {
    public int getQuoteId() {
        return quoteId;
    }

    @PrimaryKey(autoGenerate = true)
    private int quoteId;
    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("tags")
    @Expose
    @Ignore
    private List<String> tags;
    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


    public List<String> getTags() {
        return tags;
    }

}
