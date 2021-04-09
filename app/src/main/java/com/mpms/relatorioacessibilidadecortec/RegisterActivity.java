package com.mpms.relatorioacessibilidadecortec;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextInputEditText chosenDate = findViewById(R.id.date_inspection_value);
    TextInputEditText nameSchool = findViewById(R.id.name_school_text);
    TextInputEditText nameCity = findViewById(R.id.name_city_text);
    TextInputEditText nameResponsible = findViewById(R.id.name_responsible_text);
    TextInputEditText totalStudents = findViewById(R.id.total_students_text);
    TextInputEditText totalStudentsPcd = findViewById(R.id.students_pcd_text);
    TextInputEditText totalWorkers = findViewById(R.id.total_workers_text);
    TextInputEditText totalWorkersPcd = findViewById(R.id.workers_pcd_text);
    TextInputEditText totalWorkersLibras = findViewById(R.id.workers_libras_text);

    TextInputLayout schoolField = findViewById(R.id.name_school);
    TextInputLayout cityField = findViewById(R.id.name_city_school);
    TextInputLayout responsibleField = findViewById(R.id.name_responsible);
    TextInputLayout totStudentsField = findViewById(R.id.total_students);
    TextInputLayout totStudentsPcd = findViewById(R.id.total_students_pcd);
    TextInputLayout totWorkersField = findViewById(R.id.total_workers);
    TextInputLayout totWorkersPcd = findViewById(R.id.total_workers_pcd);
    TextInputLayout totWorkersLibras = findViewById(R.id.total_workers_libras);
    TextInputLayout dateField = findViewById(R.id.date_inspection);

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_register);

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
        }

        if (TextUtils.isEmpty(totalWorkers.getText())) {
            totWorkersField.setError(getString(R.string.blank_field_error));
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
