package com.example.clock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextClock clock;
    FloatingActionButton list;
    ConstraintLayout manhinh;

    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        anhxa();
        clock.setFormat24Hour("HH:mm:ss");


    }
    private void anhxa(){
        manhinh=findViewById(R.id.main);
        clock=findViewById(R.id.mainclock);
        list=findViewById(R.id.listbutton);
    }
}