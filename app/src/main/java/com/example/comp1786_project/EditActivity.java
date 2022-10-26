package com.example.comp1786_project;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class EditActivity extends AppCompatActivity {

    EditText name_input,
            destination_input,
            description_input;
    Button update_btn,
            delete_btn,
            datePickerBtn;
    RadioGroup require_assessment_input;
    RadioButton risks_update, noRisks_update;
    String id,
            name,
            destination,
            date,
            requireAssessment,
            description;
    private final DatePicker _datePicker = new DatePicker(this);

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Date Picker class custom
        datePickerBtn = findViewById(R.id.date_picker_update);
        _datePicker.initDatePicker(datePickerBtn);

        //Get Id from View
        name_input = findViewById(R.id.name_input_update);
        destination_input = findViewById(R.id.destination_input_update);
        require_assessment_input = findViewById(R.id.require_assessment_input_update);
        description_input = findViewById(R.id.description_input_update);
        risks_update = findViewById(R.id.risks_update);
        noRisks_update = findViewById(R.id.noRisks_update);
        update_btn = findViewById(R.id.edit_btn);
        delete_btn = findViewById(R.id.see_all_expenses_btn);

        //Get value from Radio Button
        require_assessment_input.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.risks_update:
                    requireAssessment = "Yes";
                    break;
                case R.id.noRisks_update:
                    requireAssessment = "No";
                    break;
            }
        });

        //Update Trip
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(EditActivity.this);
                if(name_input.getText().toString().length() == 0){
                    name_input.setError("Name is required!");
                }else if(destination_input.getText().toString().length() == 0){
                    destination_input.setError("Destination is required!");
                }else {
                    name = name_input.getText().toString().trim();
                    destination = destination_input.getText().toString().trim();
                    date = datePickerBtn.getText().toString().trim();
                    requireAssessment = requireAssessment.trim();
                    description = description_input.getText().toString().trim();

                    db.updateTripData(id, name, destination, date, requireAssessment, description);
                    startActivity(new Intent(EditActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }
            }
        });

        //Delete Trip

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        //Get and Set Data to input
        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("destination") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("requireAssessment") && getIntent().hasExtra("description")){

            //Get Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            requireAssessment = getIntent().getStringExtra("requireAssessment");
            description = getIntent().getStringExtra("description");

            //Set Intent Data
            name_input.setText(name);
            datePickerBtn.setText(date);
            destination_input.setText(destination);
            description_input.setText(description);

            //Set Intent Data Radio Button to Checked
            if(Objects.equals(requireAssessment, "Yes")){
                risks_update.setChecked(true);
            }else{
                noRisks_update.setChecked(true);
            }
        }else{
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }
    }

    //Open DatePicker Dialog
    public void openDatePicker(View view) {
        _datePicker.openDatePicker(view);
    }

    //Confirm Delete Dialog
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Do you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper db = new DatabaseHelper(EditActivity.this);
                db.deleteOneRowTrip(id);
                startActivity(new Intent(EditActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
