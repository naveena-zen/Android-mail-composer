package com.example.emailapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText emailAddress, subject, message;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAddress = findViewById(R.id.emailAddress);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(v -> {

            String email = emailAddress.getText().toString().trim();
            String subj = subject.getText().toString().trim();
            String msg = message.getText().toString().trim();

            if (email.isEmpty() || subj.isEmpty() || msg.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");

            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subj);
            emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

            try {
                startActivity(Intent.createChooser(emailIntent, "Choose Email App"));
                Toast.makeText(this, "Sent Mail Successfully", Toast.LENGTH_SHORT).show();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
            }

        });
    }
}