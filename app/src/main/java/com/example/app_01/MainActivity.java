package com.example.app_01;

import static com.example.app_01.R.*;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ImageView mainImage;
    TextView mainText;
    RadioButton maleRadio;
    RadioButton femaleRadio;
    RadioButton centimetersRadio;
    RadioButton feet_inchesRadio;
    EditText ageInput;
    EditText feetInput;
    EditText inchesInput;
    EditText centimetersInput;
    EditText weightInput;
    Button calculateButton;
    TextView indexOutput;
    TextView messageOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupClickListener();
        maleRadio.setChecked(true);
        feet_inchesRadio.setChecked(true);
        centimeters_inches_visibility();

    }

    private void findViews() {
        mainImage = findViewById(id.image_view_top_image);
        mainText = findViewById(id.text_view_name);
        maleRadio = findViewById(id.radio_button_male);
        femaleRadio = findViewById(id.radio_button_female);
        centimetersRadio = findViewById(id.radio_button_centimeters);
        feet_inchesRadio = findViewById(id.radio_button_inches);
        ageInput = findViewById(id.edit_text_age_input);
        feetInput = findViewById(id.edit_text_feet_input);
        inchesInput = findViewById(id.edit_text_inches_input);
        centimetersInput = findViewById(id.edit_text_centimeters);
        weightInput = findViewById(id.edit_text_weight_input);
        calculateButton = findViewById(id.button_calculate);
        indexOutput = findViewById(id.text_view_bmi_output);
        messageOutput = findViewById(id.text_view_message_output);
    }

    private void setupClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastWithText(String.valueOf(calculateBMI(getUserData())));
            }
        });
        feet_inchesRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    centimeters_inches_visibility();
                }
            }
        });
        centimetersRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    centimeters_inches_visibility();
                }
            }
        });
    }

    private void centimeters_inches_visibility() {
        if (centimetersRadio.isChecked()) {
            feetInput.setVisibility(View.GONE);
            inchesInput.setVisibility(View.GONE);
            centimetersInput.setVisibility(View.VISIBLE);
        }

        if (feet_inchesRadio.isChecked()) {
            feetInput.setVisibility(View.VISIBLE);
            inchesInput.setVisibility(View.VISIBLE);
            centimetersInput.setVisibility(View.GONE);
        }
    }

    private void showToastWithText(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private UserData getUserData() {
        int age = Integer.parseInt(ageInput.getText().toString());
        int feet = Integer.parseInt(feetInput.getText().toString());
        int inches = Integer.parseInt(inchesInput.getText().toString());
        double weight = Double.parseDouble(weightInput.getText().toString());

        return new UserData(age, feet, inches, weight);
    }

    private double calculateBMI(UserData userData) {
        int totalInches = userData.getFeet() * 12 + userData.getInches();
        double totalMeters = totalInches * 0.0254;
        double resultBMI = Math.round((userData.getWeight() / Math.sqrt(totalMeters)) * 100.0) / 100.0;

        return resultBMI;
    }
}

