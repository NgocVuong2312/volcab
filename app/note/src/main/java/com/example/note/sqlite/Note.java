package com.example.note.sqlite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int Noteid;
    @ColumnInfo
    public String content;
    @ColumnInfo
    public String date;
    @ColumnInfo
    public String month;
    @ColumnInfo
    public String year;
    public Note(String content, String date, String month, String year) {
        this.content = content;
        this.date = date;
        this.month = month;
        this.year = year;
    }
}
