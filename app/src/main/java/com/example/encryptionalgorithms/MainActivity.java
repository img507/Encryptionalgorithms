package com.example.encryptionalgorithms;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Button btnCaesar = findViewById(R.id.btnCaesar);
        Button btnAES = findViewById(R.id.btnAES);
        Button btnDES = findViewById(R.id.btnDES);
        Button btnRSA = findViewById(R.id.btnRSA);
        Button btnSHA256 = findViewById(R.id.btnSHA256);


        btnCaesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaesarCipherActivity.class);
                startActivity(intent);
            }
        });

        btnAES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AESActivity.class);
                startActivity(intent);
            }
        });

        btnDES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DESActivity.class);
                startActivity(intent);
            }
        });

        btnRSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RSAActivity.class);
                startActivity(intent);
            }
        });

        btnSHA256.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SHA256Activity.class);
                startActivity(intent);
            }
        });
    }
}