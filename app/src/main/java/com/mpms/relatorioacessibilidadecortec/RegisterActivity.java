package com.mpms.relatorioacessibilidadecortec;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    TextInputEditText chosenDate;
    TextInputEditText nameSchool;
    TextInputEditText nameCity;
    TextInputEditText nameResponsible;
    TextInputEditText totalStudents;
    TextInputEditText totalStudentsPcd;
    TextInputEditText totalWorkers;
    TextInputEditText totalWorkersPcd;
    TextInputEditText totalWorkersLibras;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_register);

        chosenDate = findViewById(R.id.date_inspection_value);;
        nameSchool = findViewById(R.id.name_school_text);
        nameCity = findViewById(R.id.name_city_text);
        nameResponsible = findViewById(R.id.name_responsible_text);
        totalStudents = findViewById(R.id.total_students_text);
        totalStudentsPcd = findViewById(R.id.students_pcd_text);
        totalWorkers = findViewById(R.id.total_workers_text);
        totalWorkersPcd = findViewById(R.id.workers_pcd_text);
        totalWorkersLibras = findViewById(R.id.workers_libras_text);

        saveButton = findViewById(R.id.saveButton);

        chosenDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void showDatePicker (){
        MaterialDatePicker datePickerDialog = MaterialDatePicker.Builder
                .datePicker().setTitleText("Data de Inspeção").setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
        datePickerDialog.show(getSupportFragmentManager(), "DATE_PICKER");

        datePickerDialog.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                chosenDate.setText(datePickerDialog.getHeaderText());
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
