package com.example.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button button;

    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("KTQD", " call onCreate: ");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("KTQD", " call onStart: ");
        anhxa();
        button.setOnClickListener(view -> {
            double km=Double.parseDouble(edittext.getText().toString());
            double mile=km*0.621371;
            Intent intent=new Intent(this,SecondActivity.class);
            intent.putExtra("mile",mile);
            startActivity(intent);
        });
    }


    void anhxa(){
        button=findViewById(R.id.button);
        edittext=findViewById(R.id.edtitext);
    }
}