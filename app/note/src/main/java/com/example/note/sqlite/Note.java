package com.example.note.sqlite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey
    public int Noteid;
    @ColumnInfo
    public String content;
}
