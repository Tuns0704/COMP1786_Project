package com.example.comp1786_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    // Init input variable
    EditText name_input,
            destination_input,
            description_input;
    RadioGroup require_assessment_input;
    Button datePickerBtn, addTrip_btn;
    private final DatePicker _datePicker = new DatePicker(this);
    private String riskAssessment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Date picker class custom
        datePickerBtn = findViewById(R.id.date_picker);
        _datePicker.initDatePicker(datePickerBtn);
        _datePicker.button.setText(_datePicker.getTodayDate());

        //findViewById
        name_input = findViewById(R.id.name_input);
        destination_input = findViewById(R.id.destination_input);
        require_assessment_input = findViewById(R.id.require_assessment_input);
        description_input = findViewById(R.id.description_input);
        addTrip_btn = findViewById(R.id.addTrip_btn);

        //check value from radio group
        require_assessment_input.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.Risks:
                    riskAssessment = "Yes";
                    break;
                case R.id.NoRisks:
                    riskAssessment = "No";
                    break;
            }
        });

        //add trip when user touch add button
        addTrip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(AddActivity.this);
                if(name_input.getText().toString().length() == 0){
                    name_input.setError("Name is required!");
                }else if(destination_input.getText().toString().length() == 0){
                    destination_input.setError("Destination is required!");
                }else {
                    db.addTrip(
                        name_input.getText().toString().trim(),
                        destination_input.getText().toString().trim(),
                        datePickerBtn.getText().toString().trim(),
                        riskAssessment.trim(),
                        description_input.getText().toString().trim()
                    );
                    startActivity(new Intent(AddActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });
    }

    //    //Open DatePicker Dialog
    public void openDatePicker(View view) {
        _datePicker.openDatePicker(view);
    }
}