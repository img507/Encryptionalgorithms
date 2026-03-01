package com.example.encryptionalgorithms;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AESActivity extends AppCompatActivity {

    private static final String TAG = "AESActivity";
    private EditText inputText, keyText;
    private TextView encryptedResultText, decryptedResultText;
    private String encryptedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aesactivity);

        inputText = findViewById(R.id.inputTextAES);
        keyText = findViewById(R.id.keyTextAES);
        Button encryptButton = findViewById(R.id.encryptButtonAES);
        Button decryptButton = findViewById(R.id.decryptButtonAES);
        encryptedResultText = findViewById(R.id.encryptedResultTextAES);
        decryptedResultText = findViewById(R.id.decryptedResultTextAES);
        Button backButtonAES = findViewById(R.id.backButtonAES);

        backButtonAES.setOnClickListener(v -> finish());

        encryptButton.setOnClickListener(v -> {
            if (keyText.getText().toString().isEmpty() || inputText.getText().toString().isEmpty()){
                Toast.makeText(this, "Пожалуйста, введите текст и ключ", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                encryptedData = encryptAES(inputText.getText().toString(), keyText.getText().toString());
                encryptedResultText.setText("Зашифрованный текст (Base64):\n" + encryptedData);
                decryptedResultText.setText("Расшифрованный текст:");
            } catch (Exception e) {
                Log.e(TAG, "Ошибка шифрования!", e);
                Toast.makeText(this, "Ошибка шифрования!", Toast.LENGTH_SHORT).show();
            }
        });

        decryptButton.setOnClickListener(v -> {
            if (encryptedData == null || encryptedData.isEmpty()) {
                Toast.makeText(this, "Сначала зашифруйте текст", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                String decryptedText = decryptAES(encryptedData, keyText.getText().toString());
                decryptedResultText.setText("Расшифрованный текст: " + decryptedText);
            } catch (Exception e) {
                Log.e(TAG, "Ошибка расшифровки! (проверьте ключ)", e);
                Toast.makeText(this, "Ошибка расшифровки! (проверьте ключ)", Toast.LENGTH_SHORT).show();
            }
        });

        backButtonAES.setOnClickListener(v -> finish());
    }

    private String encryptAES(String text, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    private String decryptAES(String encryptedText, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedBytes = Base64.decode(encryptedText, Base64.DEFAULT);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}