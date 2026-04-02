package com.example.app_01;

import static com.example.app_01.R.*;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
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
    TextView ageInput;
    TextView feetInput;
    TextView inchesInput;
    TextView weightInput;
    Button calculateButton;
    TextView indexOutput;
    TextView messageOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();


    }

    private void findViews() {
        mainImage = findViewById(id.image_view_top_image);
        mainText = findViewById(id.text_view_name);
        maleRadio = findViewById(id.radio_button_male);
        femaleRadio = findViewById(id.radio_button_female);
        ageInput = findViewById(id.text_view_age_input);
        feetInput = findViewById(id.text_view_feet_input);
        inchesInput = findViewById(id.text_view_inches_input);
        weightInput = findViewById(id.text_view_weight_input);
        calculateButton = findViewById(id.button_calculate);
        indexOutput = findViewById(id.text_view_bmi_output);
        messageOutput = findViewById(id.text_view_message_output);
    }
}