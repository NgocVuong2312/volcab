package com.example.note.sqlite;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.note.sqlite.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Note.class},version =1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase db;
    public abstract NoteDAO noteDAO();
    public static AppDatabase getDatabase(final Context context){
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "note_database").addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    Executors.newSingleThreadExecutor().execute(() -> {
                        List<Note> notes = loadQuestionsFromAsset(context);
                        getDatabase(context).noteDAO().insertall(notes);
                    });
                }
            }).build();
        }
        return db;
    }
    public static List<Note> loadQuestionsFromAsset(Context context) {
        try {
            InputStream is = context.getAssets().open("notes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Note>>() {
            }.getType();
            return gson.fromJson(json, listType);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

