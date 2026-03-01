package com.example.encryptionalgorithms;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CaesarCipherActivity extends AppCompatActivity {

    private EditText inputText;
    private EditText keyText;
    private TextView resultText;
    private String encryptedText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_caesar_cipher);
        setTitle("Шифр Цезаря");

        inputText = findViewById(R.id.inputText);
        keyText = findViewById(R.id.keyText);
        Button encryptButton = findViewById(R.id.encryptButton);
        Button decryptButton = findViewById(R.id.decryptButton);
        resultText = findViewById(R.id.resultText);
        Button backButton = findViewById(R.id.backButton);

        encryptButton.setOnClickListener(v -> {
            if (isInputInvalid()) return;

            String originalText = inputText.getText().toString();
            int shift = Integer.parseInt(keyText.getText().toString());

            encryptedText = cipher(originalText, shift);
            resultText.setText("Зашифрованный текст: " + encryptedText);
        });

        decryptButton.setOnClickListener(v -> {
            if (isInputInvalid()) return;

            if (encryptedText.isEmpty()) {
                Toast.makeText(this, "Сначала зашифруйте текст", Toast.LENGTH_SHORT).show();
                return;
            }

            int shift = Integer.parseInt(keyText.getText().toString());

            String decryptedText = cipher(encryptedText, -shift);
            resultText.setText("Расшифрованный текст: " + decryptedText);
        });

        backButton.setOnClickListener(v -> finish());
    }

    private boolean isInputInvalid() {
        if (inputText.getText().toString().isEmpty() || keyText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите текст и ключ", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private String cipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        int effectiveShift = shift % 26;

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition + effectiveShift + 26) % 26;
                char newCharacter = (char) (base + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}