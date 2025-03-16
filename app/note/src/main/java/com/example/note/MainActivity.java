package com.example.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendar;
    private EditText editText;
    private Button button;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private Map<String, List<String>> notesMap;
    private List<String> notes;

    private static final String SHARED_PREFS = "persist_data";
    private static final String SHARED_PREFS_KEY = "notes_map";
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendarView);
        editText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);
        list = findViewById(R.id.list);

        notesMap = new HashMap<>();
        loadData();

        selectedDate = getCurrentDate();
        notes = notesMap.getOrDefault(selectedDate, new ArrayList<>());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        list.setAdapter(adapter);

        calendar.setOnDateChangeListener((view, year, month, day) -> {
            selectedDate = String.format("%02d_%02d_%04d", day, month + 1, year);
            loadNotesForDate();
        });

        button.setOnClickListener(view -> {
            String noteText = editText.getText().toString().trim();
            if (!noteText.isEmpty()) {
                notes.add(noteText);
                adapter.notifyDataSetChanged();
                editText.setText("");
                saveData();
            }
        });

        list.setOnItemClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete this note?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        notes.remove(position);
                        adapter.notifyDataSetChanged();
                        saveData();
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        });
    }

    private void saveData() {
        notesMap.put(selectedDate, notes);

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(notesMap);
        editor.putString(SHARED_PREFS_KEY, jsonString);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString(SHARED_PREFS_KEY, "");

        if (!json.isEmpty()) {
            Type type = new TypeToken<HashMap<String, List<String>>>() {}.getType();
            notesMap = gson.fromJson(json, type);
        }
    }

    private void loadNotesForDate() {
        notes.clear();
        notes.addAll(notesMap.getOrDefault(selectedDate, new ArrayList<>()));
        adapter.notifyDataSetChanged();
    }

    private String getCurrentDate() {
        return String.format("%02d_%02d_%04d", 1, 1, 2023); // Default to Jan 1, 2023
    }
}
