package com.mpms.relatorioacessibilidadecortec;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import java.time.*;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public LocalDate chosenDate = null;
    public String chosenDateOldVersion = null;

    TextInputEditText dateInspectionText = findViewById(R.id.date_inspection_value);
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

        dateInspectionText.setOnClickListener(v -> showDatePicker());

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

        datePickerDialog.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                dateInspectionText.setText(dateToString(selection));
            }
        });
//        datePickerDialog.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener)
//                selection -> chosenDate.setText(datePickerDialog.getHeaderText()));
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
        if (TextUtils.isEmpty(dateInspectionText.getText())) {
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

    public String dateToString(Long dateLong) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chosenDate = Instant.ofEpochMilli(dateLong).atZone(ZoneId.systemDefault()).toLocalDate();
            return chosenDate.getDayOfMonth() +"/"+ chosenDate.getMonthValue() +"/"+ chosenDate.getYear();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dateLong);
            chosenDateOldVersion = calendar.YEAR +"-"+ lessThanTen((calendar.MONTH + 1))  +"-"+ lessThanTen(calendar.DAY_OF_MONTH);
            return calendar.DAY_OF_MONTH + "/" + calendar.MONTH + 1 + "/" + calendar.YEAR;
        }
    }

    public String lessThanTen(int number){
        if (number < 10)
            return "0"+number;
        else
            return Integer.toString(number);
    }
}
