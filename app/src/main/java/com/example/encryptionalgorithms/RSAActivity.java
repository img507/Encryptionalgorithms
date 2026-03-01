package com.example.encryptionalgorithms;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RSAActivity extends AppCompatActivity {
    private static final String TAG = "RSAActivity";
    private EditText inputTextRSA;
    private TextView encryptedResultTextRSA, decryptedResultTextRSA, publicKeyTextRSA, privateKeyTextRSA;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private byte[] encryptedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rsaactivity);

        inputTextRSA = findViewById(R.id.inputTextRSA);
        encryptedResultTextRSA = findViewById(R.id.encryptedResultTextRSA);
        decryptedResultTextRSA = findViewById(R.id.decryptedResultTextRSA);
        publicKeyTextRSA = findViewById(R.id.publicKeyTextRSA);
        privateKeyTextRSA = findViewById(R.id.privateKeyTextRSA);
        Button generateKeysButtonRSA = findViewById(R.id.generateKeysButtonRSA);
        Button encryptButtonRSA = findViewById(R.id.encryptButtonRSA);
        Button decryptButtonRSA = findViewById(R.id.decryptButtonRSA);
        Button backButtonRSA = findViewById(R.id.backButtonRSA);

        backButtonRSA.setOnClickListener(v -> finish());

        generateKeysButtonRSA.setOnClickListener(v -> {
            try {
                KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
                keyGen.initialize(2048);
                KeyPair keyPair = keyGen.generateKeyPair();
                publicKey = keyPair.getPublic();
                privateKey = keyPair.getPrivate();

                publicKeyTextRSA.setText("Открытый ключ:\n" + Base64.encodeToString(publicKey.getEncoded(), Base64.NO_WRAP));
                privateKeyTextRSA.setText("Закрытый ключ:\n" + Base64.encodeToString(privateKey.getEncoded(), Base64.NO_WRAP));
                Toast.makeText(this, "Ключи сгенерированы", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "Ключи сгенерированы", e);
            }
        });

        encryptButtonRSA.setOnClickListener(v -> {
            if (publicKey == null) {
                Toast.makeText(this, "Сначала сгенерируйте ключи", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                encryptedData = cipher.doFinal(inputTextRSA.getText().toString().getBytes("UTF-8"));
                encryptedResultTextRSA.setText("Зашифрованный текст:\n" + Base64.encodeToString(encryptedData, Base64.DEFAULT));
                decryptedResultTextRSA.setText("Расшифрованный текст:");
            } catch (Exception e) {
                Log.e(TAG, "Ошибка шифрования", e);
                Toast.makeText(this, "Ошибка шифрования", Toast.LENGTH_SHORT).show();
            }
        });

        decryptButtonRSA.setOnClickListener(v -> {
            if (privateKey == null || encryptedData == null) {
                Toast.makeText(this, "Сначала сгенерируйте ключи и зашифруйте текст", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] decryptedBytes = cipher.doFinal(encryptedData);
                decryptedResultTextRSA.setText("Расшифрованный текст: " + new String(decryptedBytes, "UTF-8"));
            } catch (Exception e) {
                Log.e(TAG, "Ошибка расшифровки", e);
                Toast.makeText(this, "Ошибка расшифровки", Toast.LENGTH_SHORT).show();
            }
        });
    }
}