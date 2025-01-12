package com.example.projectapplications;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText, phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.name_edittext);
        phoneEditText = findViewById(R.id.phone_edittext);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFile();
            }
        });
    }

    private void saveDataToFile() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (!name.isEmpty() && !phone.isEmpty()) {
            if(phone.length() != 10) {
                Toast.makeText(RegisterActivity.this, "Please enter a correct phone number", Toast.LENGTH_SHORT).show();
            } else {

                try {
                    FileOutputStream fileOutputStream = openFileOutput("data.txt", Context.MODE_APPEND);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                    outputStreamWriter.write("Name: " + name + "\n");
                    outputStreamWriter.write("Phone: " + phone + "\n");

                    outputStreamWriter.close();
                    fileOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Error registration", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                startActivity(intent);
            }

        } else {
            Toast.makeText(RegisterActivity.this, "Please enter both name and phone number", Toast.LENGTH_SHORT).show();
        }
    }
}
