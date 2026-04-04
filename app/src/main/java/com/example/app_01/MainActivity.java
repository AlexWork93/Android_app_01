package com.example.app_01;

import static com.example.app_01.R.*;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
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
    EditText ageInput;
    EditText feetInput;
    EditText inchesInput;
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

    }

    private void findViews() {
        mainImage = findViewById(id.image_view_top_image);
        mainText = findViewById(id.text_view_name);
        maleRadio = findViewById(id.radio_button_male);
        femaleRadio = findViewById(id.radio_button_female);
        ageInput = findViewById(id.edit_text_age_input);
        feetInput = findViewById(id.edit_text_feet_input);
        inchesInput = findViewById(id.edit_text_inches_input);
        weightInput = findViewById(id.edit_text_weight_input);
        calculateButton = findViewById(id.button_calculate);
        indexOutput = findViewById(id.text_view_bmi_output);
        messageOutput = findViewById(id.text_view_message_output);
    }

    private void setupClickListener(){
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastWithText(getUserData());
            }
        });
    }

    private void showToastWithText(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getUserData(){
        String age = ageInput.getText().toString();
        String feet = feetInput.getText().toString();
        String inches = inchesInput.getText().toString();
        String weight = weightInput.getText().toString();

        return age + ' ' + feet + ' ' + inches + ' ' + weight;
    }
}