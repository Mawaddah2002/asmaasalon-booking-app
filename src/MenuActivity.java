package com.example.projectapplications;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    CheckBox servicesCheckbox1, servicesCheckbox2, salonSpecialistsCheckbox1, salonSpecialistsCheckbox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        servicesCheckbox1 = findViewById(R.id.checkBox);
        servicesCheckbox2 = findViewById(R.id.checkBox2);
        salonSpecialistsCheckbox1 = findViewById(R.id.checkBox3);
        salonSpecialistsCheckbox2 = findViewById(R.id.checkBox4);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFile();
            }
        });
    }

    private void saveDataToFile() {
        String servicesSelected1 = "";
        String servicesSelected2 = "";

        String salonSpecialistsSelected1 = "";
        String salonSpecialistsSelected2 = "";

        if ((servicesCheckbox1.isChecked() || servicesCheckbox2.isChecked()) && (salonSpecialistsCheckbox1.isChecked() ^ salonSpecialistsCheckbox2.isChecked())) {

            try {
                FileOutputStream fileOutputStream = openFileOutput("data.txt", Context.MODE_APPEND);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                if (servicesCheckbox1.isChecked() && servicesCheckbox2.isChecked() && salonSpecialistsCheckbox1.isChecked()) {
                    servicesSelected1 += "Hair Styling, ";
                    servicesSelected2 += "Hair Coloring ";
                    salonSpecialistsSelected1 += "Sarah Mohammed ";
                } else if (servicesCheckbox1.isChecked() && servicesCheckbox2.isChecked() && salonSpecialistsCheckbox2.isChecked()) {
                    servicesSelected1 += "Hair Styling, ";
                    servicesSelected2 += "Hair Coloring ";
                    salonSpecialistsSelected2 += "Nora Ali ";
                } else if (servicesCheckbox1.isChecked() && salonSpecialistsCheckbox1.isChecked()) {
                    servicesSelected1 += "Hair Styling ";
                    salonSpecialistsSelected1 += "Sarah Mohammed ";
                } else if (servicesCheckbox1.isChecked() && salonSpecialistsCheckbox2.isChecked()) {
                    servicesSelected1 += "Hair Styling ";
                    salonSpecialistsSelected2 += "Nora Ali ";
                } else if (servicesCheckbox2.isChecked() && salonSpecialistsCheckbox1.isChecked()) {
                    servicesSelected2 += "Hair Coloring ";
                    salonSpecialistsSelected1 += "Sarah Mohammed ";
                } else if (servicesCheckbox2.isChecked() && salonSpecialistsCheckbox2.isChecked()) {
                    servicesSelected2 += "Hair Coloring ";
                    salonSpecialistsSelected2 += "Nora Ali ";
                }

                String data = "Service: " + servicesSelected1 + servicesSelected2 + "\nSpecialist: " + salonSpecialistsSelected1 + salonSpecialistsSelected2 + "\n";
                outputStreamWriter.write( data + "\n");

                outputStreamWriter.close();
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MenuActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(MenuActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuActivity.this, BookAppointment.class);
            startActivity(intent);

        } else {
            Toast.makeText(MenuActivity.this, "Please select at least one service and exactly one salon specialist", Toast.LENGTH_SHORT).show();
        }
    }
}
