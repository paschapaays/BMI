package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etAge, etHeight, etWeight;
    private RadioGroup rgGender;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        etAge = findViewById(R.id.etAge);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        rgGender = findViewById(R.id.rgGender);
        tvResult = findViewById(R.id.tvResult);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        // Event klik tombol hitung
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Ambil nilai input
        String ageStr = etAge.getText().toString();
        String heightStr = etHeight.getText().toString();
        String weightStr = etWeight.getText().toString();

        // Periksa apakah semua input diisi
        if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() || rgGender.getCheckedRadioButtonId() == -1) {
            tvResult.setText("Please enter all fields.");
            return;
        }

        int age = Integer.parseInt(ageStr);

        // Jika usia di bawah 18 tahun, tampilkan pesan dan tidak melanjutkan
        if (age < 18) {
            tvResult.setText("BMI calculation is only available for individuals aged 18 and above.");
            return;
        }

        // Konversi tinggi dari cm ke meter dan hitung BMI
        double height = Double.parseDouble(heightStr) / 100;
        double weight = Double.parseDouble(weightStr);
        double bmi = weight / (height * height);

        // Tentukan kategori BMI
        String bmiCategory = getBMICategory(bmi);

        // Dapatkan jenis kelamin
        String gender = ((RadioButton) findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();

        // Tampilkan hasil
        tvResult.setText(String.format("Gender: %s\nAge: %d\nBMI: %.2f\nCategory: %s", gender, age, bmi, bmiCategory));
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
