package com.example.encryptionalgorithms;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SHA256Activity extends AppCompatActivity {

    private EditText inputTextSHA;
    private EditText textToVerifySHA;
    private TextView hashResultTextSHA;
    private TextView verificationResultTextSHA;
    private String calculatedHash = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sha256);

        inputTextSHA = findViewById(R.id.inputTextSHA);
        textToVerifySHA = findViewById(R.id.textToVerifySHA);
        Button calculateHashButtonSHA = findViewById(R.id.calculateHashButtonSHA);
        Button verifyButtonSHA = findViewById(R.id.verifyButtonSHA);
        hashResultTextSHA = findViewById(R.id.hashResultTextSHA);
        verificationResultTextSHA = findViewById(R.id.verificationResultTextSHA);
        Button backButtonSHA = findViewById(R.id.backButtonSHA);

        backButtonSHA.setOnClickListener(v -> finish());

        calculateHashButtonSHA.setOnClickListener(v -> {
            calculatedHash = calculateSHA256(inputTextSHA.getText().toString());
            hashResultTextSHA.setText("Хеш (SHA-256):\n" + calculatedHash);
        });

        verifyButtonSHA.setOnClickListener(v -> {
            if (calculatedHash.isEmpty()) {
                verificationResultTextSHA.setText("Сначала вычислите хеш");
                verificationResultTextSHA.setTextColor(Color.BLACK);
                return;
            }
            String hashToVerify = calculateSHA256(textToVerifySHA.getText().toString());
            if (calculatedHash.equals(hashToVerify)) {
                verificationResultTextSHA.setText("Хеши СОВПАДАЮТ");
                verificationResultTextSHA.setTextColor(Color.parseColor("#008000")); // Зеленый
            } else {
                verificationResultTextSHA.setText("Хеши НЕ СОВПАДАЮТ");
                verificationResultTextSHA.setTextColor(Color.RED);
            }
        });
    }

    private String calculateSHA256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}