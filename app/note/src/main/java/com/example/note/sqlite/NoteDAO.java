package com.example.note.sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDAO{
    @Query("SELECT * FROM Note")
    List<Note> getAll();
    @Delete
    void delete(Note note);
    @Insert
    void insert(Note note);
    @Insert
    void insertall(List<Note> notes);
}
