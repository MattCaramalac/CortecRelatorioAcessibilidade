package com.mpms.relatorioacessibilidadecortec;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

    TextInputLayout schoolField;
    TextInputLayout cityField;
    TextInputLayout responsibleField;
    TextInputLayout totStudentsField;
    TextInputLayout totStudentsPcd;
    TextInputLayout totWorkersField;
    TextInputLayout totWorkersPcd;
    TextInputLayout totWorkersLibras;
    TextInputLayout dateField;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_register);

        chosenDate = findViewById(R.id.date_inspection_value);
        nameSchool = findViewById(R.id.name_school_text);
        nameCity = findViewById(R.id.name_city_text);
        nameResponsible = findViewById(R.id.name_responsible_text);
        totalStudents = findViewById(R.id.total_students_text);
        totalStudentsPcd = findViewById(R.id.students_pcd_text);
        totalWorkers = findViewById(R.id.total_workers_text);
        totalWorkersPcd = findViewById(R.id.workers_pcd_text);
        totalWorkersLibras = findViewById(R.id.workers_libras_text);

        schoolField = findViewById(R.id.name_school);
        cityField = findViewById(R.id.name_city_school);
        responsibleField = findViewById(R.id.name_responsible);
        totStudentsField = findViewById(R.id.total_students);
        totStudentsPcd = findViewById(R.id.total_students_pcd);
        totWorkersField = findViewById(R.id.total_workers);
        totWorkersPcd = findViewById(R.id.total_workers_pcd);
        totWorkersLibras = findViewById(R.id.total_workers_libras);
        dateField = findViewById(R.id.date_inspection);

        saveButton = findViewById(R.id.saveButton);

        chosenDate.setOnClickListener(v -> showDatePicker());

        saveButton.setOnClickListener(v -> {
            int correctEntry = verifyErrors();
            if (correctEntry == 0)
                finish();
        });
    }

    private void showDatePicker (){
        MaterialDatePicker<Long> datePickerDialog = MaterialDatePicker.Builder
                .datePicker().setTitleText("Data de Inspeção").setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
        datePickerDialog.show(getSupportFragmentManager(), "DATE_PICKER");

        datePickerDialog.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener)
                selection -> chosenDate.setText(datePickerDialog.getHeaderText()));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public int verifyErrors() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(nameSchool.getText())) {
            schoolField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(nameCity.getText())) {
            cityField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(nameResponsible.getText())) {
            responsibleField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(chosenDate.getText())) {
            dateField.setError(getString(R.string.blank_field_error));
            i++;
        }

        if (TextUtils.isEmpty(totalStudents.getText())) {
            totStudentsField.setError(getString(R.string.blank_field_error));
            i++;
        } else if (!TextUtils.isDigitsOnly(totalStudents.getText().toString())) {
            totStudentsField.setError(getString(R.string.wrong_input_error));
            i++;
        }

        if (!TextUtils.isDigitsOnly(totalStudentsPcd.getText().toString())) {
            totStudentsPcd.setError(getString(R.string.wrong_input_error));
            i++;
        }

        if (TextUtils.isEmpty(totalWorkers.getText())) {
            totWorkersField.setError(getString(R.string.blank_field_error));
            i++;
        } else if (!TextUtils.isDigitsOnly(totalWorkers.getText().toString())) {
            totStudentsField.setError(getString(R.string.wrong_input_error));
            i++;
        }

        if (!TextUtils.isDigitsOnly(totalWorkersPcd.getText().toString())) {
            totWorkersPcd.setError(getString(R.string.wrong_input_error));
            i++;
        }

        if (!TextUtils.isDigitsOnly(totalWorkersLibras.getText().toString())) {
            totWorkersLibras.setError(getString(R.string.wrong_input_error));
            i++;
        }

        return i;
    }

    public void clearErrors() {

        schoolField.setError(null);
        cityField.setError(null);
        responsibleField.setError(null);
        totStudentsField.setError(null);
        totStudentsPcd.setError(null);
        totWorkersField.setError(null);
        totWorkersPcd.setError(null);
        totWorkersLibras.setError(null);
        dateField.setError(null);
    }
}
