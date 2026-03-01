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

public class DESActivity extends AppCompatActivity {

    private static final String TAG = "DESActivity";
    private EditText inputTextDES;
    private EditText keyTextDES;
    private TextView encryptedResultTextDES;
    private TextView decryptedResultTextDES;
    private String encryptedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_desactivity);

        inputTextDES = findViewById(R.id.inputTextDES);
        keyTextDES = findViewById(R.id.keyTextDES);
        Button encryptButtonDES = findViewById(R.id.encryptButtonDES);
        Button decryptButtonDES = findViewById(R.id.decryptButtonDES);
        encryptedResultTextDES = findViewById(R.id.encryptedResultTextDES);
        decryptedResultTextDES = findViewById(R.id.decryptedResultTextDES);
        Button backButtonDES = findViewById(R.id.backButtonDES);

        backButtonDES.setOnClickListener(v -> finish());

        encryptButtonDES.setOnClickListener(v -> {
            if (keyTextDES.getText().toString().isEmpty() || inputTextDES.getText().toString().isEmpty()){
                Toast.makeText(this, "Пожалуйста, введите текст и ключ", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                encryptedData = encryptDES(inputTextDES.getText().toString(), keyTextDES.getText().toString());
                encryptedResultTextDES.setText("Зашифрованный текст (Base64):\n" + encryptedData);
                decryptedResultTextDES.setText("Расшифрованный текст:");
            } catch (Exception e) {
                Log.e(TAG, "Ошибка шифрования!", e);
                Toast.makeText(this, "Ошибка шифрования!", Toast.LENGTH_SHORT).show();
            }
        });

        decryptButtonDES.setOnClickListener(v -> {
            if (encryptedData == null || encryptedData.isEmpty()) {
                Toast.makeText(this, "Сначала зашифруйте текст", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                String decryptedText = decryptDES(encryptedData, keyTextDES.getText().toString());
                decryptedResultTextDES.setText("Расшифрованный текст: " + decryptedText);
            } catch (Exception e) {
                Log.e(TAG, "Ошибка расшифровки!", e);
                Toast.makeText(this, "Ошибка расшифровки!", Toast.LENGTH_SHORT).show();
            }
        });

        backButtonDES.setOnClickListener(v -> finish());
    }

    private String encryptDES(String text, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    private String decryptDES(String encryptedText, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedBytes = Base64.decode(encryptedText, Base64.DEFAULT);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}