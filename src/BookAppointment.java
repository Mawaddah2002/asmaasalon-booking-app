package com.example.projectapplications;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_appointment);

        Button registerButton = findViewById(R.id.button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookAppointment.this, "Thank you!!", Toast.LENGTH_SHORT).show();
            }
        });

        try {
            FileInputStream fileInputStream = openFileInput("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder data = new StringBuilder();
            String line;
            String lastFourLines = "";

            // Read the file line by line
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append("\n");

                // Store the last four lines
                lastFourLines = lastFourLines.concat(line).concat("\n");
                if (lastFourLines.split("\n").length > 4) {
                    lastFourLines = lastFourLines.substring(lastFourLines.indexOf("\n") + 1);
                }
            }

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

            // Display only the last two lines or the latest data
            TextView dataTextView = findViewById(R.id.userInput);
            dataTextView.setText(lastFourLines);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(BookAppointment.this, "Error!!", Toast.LENGTH_SHORT).show();
        }
    }
}
