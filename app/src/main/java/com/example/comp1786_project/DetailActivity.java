package com.example.comp1786_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    EditText name_input, destination_input, description_input;
    Button datePicker_btn, edit_btn, seeAllExpense_btn;
    RadioGroup require_assessment_input;
    RadioButton risks_update, noRisks_update;
    String id, name, destination, date, requireAssessment, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Get Id from View
        name_input = findViewById(R.id.name_input_detail);
        destination_input = findViewById(R.id.destination_input_detail);
        datePicker_btn = findViewById(R.id.date_picker_detail);
        require_assessment_input = findViewById(R.id.require_assessment_input_detail);
        description_input = findViewById(R.id.description_input_detail);
        risks_update = findViewById(R.id.Risks_detail);
        noRisks_update = findViewById(R.id.NoRisks_detail);

        edit_btn = findViewById(R.id.edit_btn);
        seeAllExpense_btn = findViewById(R.id.see_all_expenses_btn);

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, EditActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("destination", getIntent().getStringExtra("destination"));
                intent.putExtra("date", getIntent().getStringExtra("date"));
                intent.putExtra("requireAssessment", getIntent().getStringExtra("requireAssessment"));
                intent.putExtra("description", getIntent().getStringExtra("description"));
                startActivity(intent);
            }
        });

        seeAllExpense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ExpensesMainActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });
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
            destination_input.setText(destination);
            datePicker_btn.setText(date);
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
}