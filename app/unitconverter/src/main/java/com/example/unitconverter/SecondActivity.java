package com.example.unitconverter;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        anhxa();
        double mile=getIntent().getDoubleExtra("mile",0);
        textView.setText(String.valueOf(mile)+ " Mile");

    }
    void anhxa(){
        textView=findViewById(R.id.textView);
    }
}