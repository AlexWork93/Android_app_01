package com.example.app_01;

import static com.example.app_01.R.*;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ImageView mainImage;
    TextView mainText;
    RadioButton maleRadio;
    RadioButton femaleRadio;
    RadioGroup measurementRadioGroup;
    RadioButton centimetersRadio;
    RadioButton feet_inchesRadio;
    EditText ageInput;
    EditText feetInput;
    EditText inchesInput;
    EditText centimetersInput;
    EditText weightInput;
    Button calculateButton;
    TextView messageOutput;
    TextView bmiOutput;

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
        measurementRadioGroup = findViewById(id.radio_group_measurement);
        centimetersRadio = findViewById(id.radio_button_centimeters);
        feet_inchesRadio = findViewById(id.radio_button_inches);
        ageInput = findViewById(id.edit_text_age_input);
        feetInput = findViewById(id.edit_text_feet_input);
        inchesInput = findViewById(id.edit_text_inches_input);
        centimetersInput = findViewById(id.edit_text_centimeters);
        weightInput = findViewById(id.edit_text_weight_input);
        calculateButton = findViewById(id.button_calculate);
        messageOutput = findViewById(id.text_view_message_output);
        bmiOutput = findViewById(id.text_view_bmi_output);
    }

    private void setupClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = getUserData();
                if (userData == null) {
                    showToastWithText(0);
                } else {
                    showToastWithText(calculateBMI(getUserData()));
                }
            }
        });
        measurementRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                centimeters_inches_visibility();
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

    private void showToastWithText(double bmi) {
        String message = "";
        if (bmi == 0) {
            message += "wrong input";
        } else if (bmi < 18.5)
            message += " you are underweight";
        else if (bmi > 25) {
            message += " you are overweight";
        } else
            message += " you are healthy";
        if (message.contains("wrong input")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            bmiOutput.setText(String.valueOf(bmi));
            messageOutput.setText(message);
        }
    }

    private UserData getUserData() {
        int age = 0;
        int feet = 0;
        int inches = 0;
        int centimeters = 0;
        double weight = 0;
        try {
            age = Integer.parseInt(ageInput.getText().toString());
        } catch (Exception e) {
            return null;
        }
        if (centimetersRadio.isChecked()) {
            try {
                centimeters = Integer.parseInt(centimetersInput.getText().toString());
            } catch (Exception e) {
                return null;
            }
        } else {
            try {
                feet = Integer.parseInt(feetInput.getText().toString());
            } catch (Exception e) {
                return null;
            }
            try {
                inches = Integer.parseInt(inchesInput.getText().toString());
            } catch (Exception e) {
                return null;
            }
        }
        try {
            weight = Double.parseDouble(weightInput.getText().toString());
        } catch (Exception e) {
            return null;
        }

        return new UserData(age, feet, inches, centimeters, weight);
    }

    private double calculateBMI(UserData userData) {
        int totalInches = 0;
        double totalMeters = 0;
        if (centimetersRadio.isChecked()) {
            totalMeters = (double) userData.getCentimeters() / 100;
        } else {
            totalInches = userData.getFeet() * 12 + userData.getInches();
            totalMeters = totalInches * 0.0254;
        }
        double resultBMI = Math.round((userData.getWeight() / Math.pow(totalMeters, 2)) * 100.0) / 100.0;

        return resultBMI;
    }
}

