package com.mpms.relatorioacessibilidadecortec;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import java.time.*;
import java.time.zone.ZoneRules;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public LocalDate chosenDate = null;
    public String chosenDateOldVersion = null;

    private ViewModelEntry viewModelEntry;

    TextInputEditText dateInspectionText;
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

        viewModelEntry = new ViewModelProvider.AndroidViewModelFactory(RegisterActivity.this.getApplication()).create(ViewModelEntry.class);

        //Só podem ser iniciados DENTRO do onCreate, caso contrário não foi selecionada ainda a View
        //E acaba causando um apontamento para algo nulo (já que não tem View selecioada para ser achada

        dateInspectionText = findViewById(R.id.date_inspection_value);
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

        dateInspectionText.setOnClickListener(v -> showDatePicker());

        saveButton.setOnClickListener(v -> {
            int correctEntry = verifyErrors();
            if (correctEntry == 0) {
                SchoolEntry newEntry = new SchoolEntry(nameSchool.getText().toString(), nameResponsible.getText().toString(),
                        nameCity.getText().toString(), chosenDate.toString(), Integer.parseInt(totalStudents.getText().toString()),
                        Integer.parseInt(totalStudentsPcd.getText().toString()),Integer.parseInt(totalWorkers.getText().toString()),
                        Integer.parseInt(totalStudentsPcd.getText().toString()),Integer.parseInt(totalWorkersLibras.getText().toString()));
                viewModelEntry.insert(newEntry);
                finish();
            }
        });
    }

    private void showDatePicker (){
        MaterialDatePicker<Long> datePickerDialog = MaterialDatePicker.Builder
                .datePicker().setTitleText("Data de Inspeção").setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
        datePickerDialog.show(getSupportFragmentManager(), "DATE_PICKER");

        datePickerDialog.addOnPositiveButtonClickListener(selection -> dateInspectionText.setText(dateToString(selection)));
//          Sem expressão lambda fica assim:
//        datePickerDialog.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
//            @Override
//            public void onPositiveButtonClick(Long selection) {
//             dateInspectionText.setText(dateToString(selection));
//            }
//        });
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
//            systemDefault()
            chosenDate = Instant.ofEpochMilli(dateLong).atZone(ZoneId.of("Z")).toLocalDate();
            return lessThanTen(chosenDate.getDayOfMonth()) +"/"+ lessThanTen(chosenDate.getMonthValue()) +"/"+ chosenDate.getYear();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dateLong);
            chosenDateOldVersion = calendar.YEAR +"-"+ lessThanTen((calendar.MONTH + 1))  +"-"+ lessThanTen(calendar.DAY_OF_MONTH);
            return lessThanTen(calendar.DAY_OF_MONTH) + "/" + lessThanTen((calendar.MONTH + 1)) + "/" + calendar.YEAR;
        }
    }

    public String lessThanTen(int number){
        if (number < 10)
            return "0"+number;
        else
            return Integer.toString(number);
    }


}
