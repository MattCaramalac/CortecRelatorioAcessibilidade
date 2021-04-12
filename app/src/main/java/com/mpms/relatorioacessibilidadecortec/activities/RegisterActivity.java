package com.mpms.relatorioacessibilidadecortec.activities;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.time.*;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

//      TODO - Estudar para usar Fragments no lugar de Activities - Garante melhor Design em Tablets + diminui gasto de memória
public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public LocalDate chosenDate;
//    public String chosenDateOldVersion;
    private int cadID = 0;

//    private ViewModelEntry viewModelEntry;
//    Como insert é um método static, não precisa ser criado um objeto ViewModelEntry para acessar

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

    Button saveCloseButton;
    Button saveContinueButton;
    Button updateEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_register);

        //Só podem ser iniciados DENTRO do onCreate, caso contrário não foi selecionada ainda a View
        //E acaba causando um apontamento para algo nulo (já que não tem View selecioada para ser achada)

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

        saveCloseButton = findViewById(R.id.saveCloseButton);
        saveContinueButton = findViewById(R.id.saveContinueButton);
        updateEntryButton = findViewById(R.id.updateButton);

        //Usar mesmo Activity para cadastro e update. Preenche os campos com as informações da DB
        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST)) {
            saveCloseButton.setVisibility(View.GONE);
            saveContinueButton.setVisibility(View.GONE);

            cadID = getIntent().getIntExtra(MainActivity.UPDATE_REQUEST, 0);
            ViewModelEntry gatherInfo = new ViewModelEntry(RegisterActivity.this.getApplication());
            gatherInfo.getEntry(cadID).observe(this, schoolEntry -> {
                nameSchool.setText(schoolEntry.getSchoolName());
                nameResponsible.setText(schoolEntry.getNameDirector());
                nameCity.setText(schoolEntry.getNameCity());
                totalStudents.setText(Integer.toString(schoolEntry.getNumberStudents()));
                totalStudentsPcd.setText(Integer.toString(schoolEntry.getNumberStudentsPcd()));
                totalWorkers.setText(Integer.toString(schoolEntry.getNumberWorkers()));
                totalWorkersPcd.setText(Integer.toString(schoolEntry.getNumberWorkersPcd()));
                totalWorkersLibras.setText(Integer.toString(schoolEntry.getNumberWorkersLibras()));
                dateInspectionText.setText(dateDatabaseToRegister(schoolEntry.getDateInspection()));
            });
        } else {
            updateEntryButton.setVisibility(View.GONE);
        }

        dateInspectionText.setOnClickListener(v -> showDatePicker());

        saveCloseButton.setOnClickListener(v -> {
            if (verifyErrors()) {
                setEmptyAmountToZero();
                  SchoolEntry newEntry = new SchoolEntry(nameSchool.getText().toString(), nameResponsible.getText().toString(),
                            nameCity.getText().toString(), stringToDate(dateInspectionText.getText().toString()),
                            Integer.parseInt(totalStudents.getText().toString()), Integer.parseInt(totalStudentsPcd.getText().toString()),
                            Integer.parseInt(totalWorkers.getText().toString()), Integer.parseInt(totalWorkersLibras.getText().toString()),
                            Integer.parseInt(totalWorkersPcd.getText().toString()));
                ViewModelEntry.insert(newEntry);
                finish();

            }
        });

        saveContinueButton.setOnClickListener(v -> {
            Toast.makeText(this, "Função a ser Implementada", Toast.LENGTH_LONG).show();
        });

        updateEntryButton.setOnClickListener( v -> {
            if (verifyErrors()) {
                setEmptyAmountToZero();
                SchoolEntry updateEntry = new SchoolEntry(nameSchool.getText().toString(), nameResponsible.getText().toString(),
                            nameCity.getText().toString(), stringToDate(dateInspectionText.getText().toString()),
                            Integer.parseInt(totalStudents.getText().toString()),
                            Integer.parseInt(totalStudentsPcd.getText().toString()), Integer.parseInt(totalWorkers.getText().toString()),
                            Integer.parseInt(totalWorkersLibras.getText().toString()), Integer.parseInt(totalWorkersPcd.getText().toString()));
                updateEntry.setCadID(cadID);
                ViewModelEntry.update(updateEntry);
                finish();
            }

        });
    }

//  TODO - Refazer a forma de captar e armazenar datas para manter integridade
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

    public boolean verifyErrors() {
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
//        if (TextUtils.isEmpty(dateInspectionText.getText())) {
//            dateField.setError(getString(R.string.blank_field_error));
//            i++;
//        }

        if (TextUtils.isEmpty(totalStudents.getText())) {
            totStudentsField.setError(getString(R.string.blank_field_error));
            i++;
        }

        if (TextUtils.isEmpty(totalWorkers.getText())) {
            totWorkersField.setError(getString(R.string.blank_field_error));
            i++;
        }

        return i <= 0;
    }

    public void clearErrors() {
        schoolField.setErrorEnabled(false);
        cityField.setErrorEnabled(false);
        responsibleField.setErrorEnabled(false);
        totStudentsField.setErrorEnabled(false);
        totStudentsPcd.setErrorEnabled(false);
        totWorkersField.setErrorEnabled(false);
        totWorkersPcd.setErrorEnabled(false);
        totWorkersLibras.setErrorEnabled(false);
        dateField.setErrorEnabled(false);
    }

    public String dateToString(Long dateLong) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chosenDate = Instant.ofEpochMilli(dateLong).atZone(ZoneId.of("Z")).toLocalDate();
            return lessThanTen(chosenDate.getDayOfMonth()) +"/"+ lessThanTen(chosenDate.getMonthValue()) +"/"+ chosenDate.getYear();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dateLong);
            return lessThanTen(calendar.DAY_OF_MONTH) + "/" + lessThanTen((calendar.MONTH + 1)) + "/" + calendar.YEAR;
        }
    }

    public String stringToDate(String date) {
        String[] dividedString = date.split("/");
        return dividedString[2] + "-" + dividedString[1] + "-" + dividedString[0];
    }

    public String dateDatabaseToRegister (String date) {
        String[] dividedString = date.split("-");
        return dividedString[2] + "/" + dividedString[1] + "/" + dividedString[0];
    }

    public String lessThanTen(int number){
        if (number < 10)
            return "0"+number;
        else
            return Integer.toString(number);
    }

    public void setEmptyAmountToZero () {
        Log.d("TAG", "setEmptyAmountToZero: " + totalStudentsPcd.getText().toString());
        if (TextUtils.isEmpty(dateInspectionText.getText()))
            dateInspectionText.setText("");
        if (TextUtils.isEmpty(totalStudentsPcd.getText()))
            totalStudentsPcd.setText("0");
        if (TextUtils.isEmpty(totalWorkersPcd.getText()))
            totalWorkersPcd.setText("0");
        if (TextUtils.isEmpty(totalWorkersLibras.getText()))
            totalWorkersLibras.setText("0");
    }
}
