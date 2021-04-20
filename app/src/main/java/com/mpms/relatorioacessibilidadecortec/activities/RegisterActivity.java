package com.mpms.relatorioacessibilidadecortec.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private int cadID = 0;

//    private ViewModelEntry viewModelEntry;
//    Como insert é um método static, não precisa ser criado um objeto ViewModelEntry para acessar
    TextInputEditText nameSchool, addressSchool, addressComplement, addressNumber, addressNeighborhood, nameCity, nameDirector, contactPhone1,
        contactPhone2, nameResponsibleVisit, nameInspectionTeamMembers, morningStartTime, morningEndTime, afternoonStartTime, afternoonEndTime,
        eveningStartTime, eveningEndTime, maternalFirstGrade, maternalLastGrade, preschoolFirstGrade, preschoolLastGrade, elementaryFirstGrade,
        elementaryLastGrade, middleFirstGrade, middleLastGrade, highFirstGrade, highLastGrade, ejaFirstGrade, ejaLastGrade, youngestStudentAge,
        oldestStudentAge, totalStudents, totalStudentsPcd, studentsPcdDescription, totalWorkers, totalWorkersPcd, workersPcdDescription,
        totalWorkersLibras, dateInspectionText;

    TextInputLayout schoolField, addressField, addressComplementField, addressNumberField, addressNeighborhoodField, cityField, directorField,
            contactPhone1Field, contactPhone2Field, nameResponsibleVisitField, nameInspectionTeamMembersField, morningStartTimeField, morningEndTimeField,
            afternoonStartTimeField, afternoonEndTimeField, eveningStartTimeField, eveningEndTimeField, maternalFirstGradeField, maternalLastGradeField,
            preschoolFirstGradeField, preschoolLastGradeField, elementaryFirstGradeField, elementaryLastGradeField, middleFirstGradeField,
            middleLastGradeField, highFirstGradeField, highLastGradeField, ejaFirstGradeField, ejaLastGradeField, youngestStudentAgeField,
            oldestStudentAgeField, totStudentsField, totStudentsPcd, studentsPcdDescriptionField, totWorkersField, totWorkersPcd,
            workersPcdDescriptionField, totWorkersLibras, dateField;

    Button saveCloseButton, saveContinueButton, updateEntryButton;

    CheckBox hasMorningClasses, hasAfternoonClasses, hasEveningClasses, hasMaternal, hasPreschool, hasElementary, hasMiddle, hasHigh, hasEJA;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_register);

        //Só podem ser iniciados DENTRO do onCreate, caso contrário não foi selecionada ainda a View
        //E acaba causando um apontamento para algo nulo (já que não tem View selecioada para ser achada)
        nameSchool = findViewById(R.id.name_school_text);
        addressSchool = findViewById(R.id.school_address_text);
        addressComplement = findViewById(R.id.complement_text);
        addressNumber = findViewById(R.id.numberAddress_text);
        addressNeighborhood = findViewById(R.id.neighborhood_text);
        nameCity = findViewById(R.id.name_city_text);
        nameDirector = findViewById(R.id.name_principal_text);
        contactPhone1 = findViewById(R.id.contact_phone1_text);
        contactPhone2 = findViewById(R.id.contact_phone2_text);
        nameResponsibleVisit = findViewById(R.id.name_responsible_text);
        nameInspectionTeamMembers = findViewById(R.id.name_team_visit_text);
        morningStartTime = findViewById(R.id.morning_start_hour);
        morningEndTime = findViewById(R.id.morning_end_hour);
        afternoonStartTime = findViewById(R.id.afternoon_start_hour);
        afternoonEndTime = findViewById(R.id.afternoon_end_hour);
        eveningStartTime = findViewById(R.id.evening_start_hour);
        eveningEndTime = findViewById(R.id.evening_end_hour);
        maternalFirstGrade = findViewById(R.id.maternal_start_text);
        maternalLastGrade = findViewById(R.id.maternal_end_text);
        preschoolFirstGrade = findViewById(R.id.preschool_start_text);
        preschoolLastGrade = findViewById(R.id.preschool_end_text);
        elementaryFirstGrade = findViewById(R.id.elementary_start_text);
        elementaryLastGrade = findViewById(R.id.elementary_end_text);
        middleFirstGrade = findViewById(R.id.middleschool_start_text);
        middleLastGrade = findViewById(R.id.middleschool_end_text);
        highFirstGrade = findViewById(R.id.highschool_start_text);
        highLastGrade = findViewById(R.id.highschool_end_text);
        ejaFirstGrade = findViewById(R.id.eja_start_text);
        ejaLastGrade = findViewById(R.id.eja_end_text);
        youngestStudentAge = findViewById(R.id.starting_age_text);
        oldestStudentAge = findViewById(R.id.last_age_text);
        totalStudents = findViewById(R.id.total_students_text);
        totalStudentsPcd = findViewById(R.id.students_pcd_text);
        studentsPcdDescription = findViewById(R.id.description_pcd_students_text);
        totalWorkers = findViewById(R.id.total_workers_text);
        totalWorkersPcd = findViewById(R.id.workers_pcd_text);
        workersPcdDescription = findViewById(R.id.description_pcd_workers_text);
        totalWorkersLibras = findViewById(R.id.workers_libras_text);
        dateInspectionText = findViewById(R.id.date_inspection_value);

        schoolField = findViewById(R.id.name_school);
        cityField = findViewById(R.id.name_city);
        directorField = findViewById(R.id.name_principal);
        totStudentsField = findViewById(R.id.total_students);
        totStudentsPcd = findViewById(R.id.total_students_pcd);
        totWorkersField = findViewById(R.id.total_workers);
        totWorkersPcd = findViewById(R.id.total_workers_pcd);
        totWorkersLibras = findViewById(R.id.total_workers_libras);
        dateField = findViewById(R.id.date_inspection);
        addressField = findViewById(R.id.school_adress);
        addressComplementField = findViewById(R.id.complementAddress);
        addressNumberField = findViewById(R.id.numberAddress);
        addressNeighborhoodField = findViewById(R.id.neighborhood);
        contactPhone1Field = findViewById(R.id.contact_phone1);
        contactPhone2Field = findViewById(R.id.contact_phone2);
        nameResponsibleVisitField = findViewById(R.id.name_responsible);
        nameInspectionTeamMembersField = findViewById(R.id.name_team_visit);
        youngestStudentAgeField = findViewById(R.id.starting_age);
        oldestStudentAgeField = findViewById(R.id.last_age);
        studentsPcdDescriptionField = findViewById(R.id.description_pcd_students);
        workersPcdDescriptionField = findViewById(R.id.description_pcd_workers);
        morningStartTimeField = findViewById(R.id.morning_start);
        morningEndTimeField = findViewById(R.id.morning_end);
        afternoonStartTimeField = findViewById(R.id.afternoon_start);
        afternoonEndTimeField = findViewById(R.id.afternoon_end);
        eveningStartTimeField = findViewById(R.id.evening_start);
        eveningEndTimeField = findViewById(R.id.evening_end);
        maternalFirstGradeField = findViewById(R.id.maternal_start);
        maternalLastGradeField = findViewById(R.id.maternal_end);
        preschoolFirstGradeField = findViewById(R.id.preschool_start);
        preschoolLastGradeField = findViewById(R.id.preschool_end);
        elementaryFirstGradeField = findViewById(R.id.elementary_start);
        elementaryLastGradeField = findViewById(R.id.elementary_end);
        middleFirstGradeField = findViewById(R.id.middleschool_start);
        middleLastGradeField = findViewById(R.id.middleschool_end);
        highFirstGradeField = findViewById(R.id.highschool_start);
        highLastGradeField = findViewById(R.id.highschool_end);
        ejaFirstGradeField = findViewById(R.id.eja_start);
        ejaLastGradeField = findViewById(R.id.eja_end);
        dateField = findViewById(R.id.date_inspection);

        hasMorningClasses = findViewById(R.id.checkbox_morning);
        hasAfternoonClasses = findViewById(R.id.checkbox_afternoon);
        hasEveningClasses = findViewById(R.id.checkbox_evening);
        hasMaternal = findViewById(R.id.checkbox_school1);
        hasPreschool = findViewById(R.id.checkbox_school2);
        hasElementary = findViewById(R.id.checkbox_school3);
        hasMiddle = findViewById(R.id.checkbox_school4);
        hasHigh = findViewById(R.id.checkbox_school5);
        hasEJA = findViewById(R.id.checkbox_school6);

        saveCloseButton = findViewById(R.id.saveCloseButton);
        saveContinueButton = findViewById(R.id.saveContinueButton);
        updateEntryButton = findViewById(R.id.updateButton);

        //Usar mesmo Activity para cadastro e update. Preenche os campos com as informações da DB

        morningStartTimeField.setEnabled(false);
        morningEndTimeField.setEnabled(false);
        afternoonStartTimeField.setEnabled(false);
        afternoonEndTimeField.setEnabled(false);
        eveningStartTimeField.setEnabled(false);
        eveningEndTimeField.setEnabled(false);
        maternalFirstGradeField.setEnabled(false);
        maternalLastGradeField.setEnabled(false);
        preschoolFirstGradeField.setEnabled(false);
        preschoolLastGradeField.setEnabled(false);
        elementaryFirstGradeField.setEnabled(false);
        elementaryLastGradeField.setEnabled(false);
        middleFirstGradeField.setEnabled(false);
        middleLastGradeField.setEnabled(false);
        highFirstGradeField.setEnabled(false);
        highLastGradeField.setEnabled(false);
        ejaFirstGradeField.setEnabled(false);
        ejaLastGradeField.setEnabled(false);

        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST)) {
            saveCloseButton.setVisibility(View.GONE);
            saveContinueButton.setVisibility(View.GONE);

            cadID = getIntent().getIntExtra(MainActivity.UPDATE_REQUEST, 0);
            ViewModelEntry gatherInfo = new ViewModelEntry(RegisterActivity.this.getApplication());
            gatherInfo.getEntry(cadID).observe(this, this::gatherEntry);
        } else {
            updateEntryButton.setVisibility(View.GONE);
        }

        checkboxListener();

        dateInspectionText.setOnClickListener(v -> showDatePicker());

        saveCloseButton.setOnClickListener(v -> {
            if (verifyErrors()) {
                SchoolEntry newEntry = createEntry();
                ViewModelEntry.insert(newEntry);
                finish();
            }
        });

        saveContinueButton.setOnClickListener(v -> Toast.makeText(this, "Função a ser Implementada", Toast.LENGTH_LONG).show());

        updateEntryButton.setOnClickListener( v -> {
            if (verifyErrors()) {
                SchoolEntry updateEntry = createEntry();
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

    public void checkboxListener() {

        hasMorningClasses.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                morningStartTimeField.setEnabled(true);
                morningEndTimeField.setEnabled(true);
            } else {
                morningStartTimeField.setEnabled(false);
                morningEndTimeField.setEnabled(false);
            }

        });

        hasAfternoonClasses.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                afternoonStartTimeField.setEnabled(true);
                afternoonEndTimeField.setEnabled(true);
            } else {
                afternoonStartTimeField.setEnabled(false);
                afternoonEndTimeField.setEnabled(false);
            }

        });

        hasEveningClasses.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                eveningStartTimeField.setEnabled(true);
                eveningEndTimeField.setEnabled(true);
            } else {
                eveningStartTimeField.setEnabled(false);
                eveningEndTimeField.setEnabled(false);
            }

        });

        hasMaternal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                maternalFirstGradeField.setEnabled(true);
                maternalLastGradeField.setEnabled(true);
            } else {
                maternalFirstGradeField.setEnabled(false);
                maternalLastGradeField.setEnabled(false);
            }

        });

        hasPreschool.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                preschoolFirstGradeField.setEnabled(true);
                preschoolLastGradeField.setEnabled(true);
            } else {
                preschoolFirstGradeField.setEnabled(false);
                preschoolLastGradeField.setEnabled(false);
            }

        });

        hasElementary.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                elementaryFirstGradeField.setEnabled(true);
                elementaryLastGradeField.setEnabled(true);
            } else {
                elementaryFirstGradeField.setEnabled(false);
                elementaryLastGradeField.setEnabled(false);
            }

        });

        hasMiddle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                middleFirstGradeField.setEnabled(true);
                middleLastGradeField.setEnabled(true);
            } else {
                middleFirstGradeField.setEnabled(false);
                middleLastGradeField.setEnabled(false);
            }

        });

        hasHigh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                highFirstGradeField.setEnabled(true);
                highLastGradeField.setEnabled(true);
            } else {
                highFirstGradeField.setEnabled(false);
                highLastGradeField.setEnabled(false);
            }

        });

        hasEJA.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                ejaFirstGradeField.setEnabled(true);
                ejaLastGradeField.setEnabled(true);
            } else {
                ejaFirstGradeField.setEnabled(false);
                ejaLastGradeField.setEnabled(false);
            }

        });
    }

    public Integer checkValueCheckbox (CheckBox checkBox) {
        if (checkBox.isChecked())
            return 1;
        else
            return 0;
    }

//TODO - Dar Update nos métodos seguintes para acrescentar todos os novos campos da Register Activity (socorr)
    public boolean verifyErrors() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(nameSchool.getText())) {
            schoolField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(addressSchool.getText())) {
            addressField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(addressNumber.getText())) {
            addressNumberField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(addressNeighborhood.getText())) {
            addressNeighborhoodField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(nameCity.getText())) {
            cityField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(nameDirector.getText())) {
            directorField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(contactPhone1.getText())) {
            contactPhone1Field.setError(getString(R.string.blank_field_error));
            i++;
//            TODO - Inserir método para verificar se o número inserido é válido
        }
        if (TextUtils.isEmpty(nameResponsibleVisit.getText())) {
            nameResponsibleVisitField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(nameInspectionTeamMembers.getText())) {
            nameInspectionTeamMembersField.setError(getString(R.string.blank_field_error));
            i++;
        }
//        TODO - Inserir Método para verificação dos Campos dependentes de Checkbox
        if (TextUtils.isEmpty(totalStudents.getText())) {
            totStudentsField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (!TextUtils.isEmpty(totalStudentsPcd.getText()) && !TextUtils.equals(totalStudentsPcd.getText().toString(), "0")) {
            if (TextUtils.isEmpty(studentsPcdDescription.getText())) {
                studentsPcdDescriptionField.setError(getString(R.string.blank_field_error));
                i++;
            }
        }
        if (TextUtils.isEmpty(totalWorkers.getText())) {
            totWorkersField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (!TextUtils.isEmpty(totalWorkersPcd.getText()) && !TextUtils.equals(totalWorkersPcd.getText().toString(), "0")) {
            if (TextUtils.isEmpty(workersPcdDescription.getText())) {
                workersPcdDescriptionField.setError(getString(R.string.blank_field_error));
                i++;
            }
        }
        return i <= 0;
    }

    public void clearErrors() {
        schoolField.setErrorEnabled(false);
        addressField.setErrorEnabled(false);
        addressNumberField.setErrorEnabled(false);
        addressNeighborhoodField.setErrorEnabled(false);
        cityField.setErrorEnabled(false);
        directorField.setErrorEnabled(false);
        contactPhone1Field.setErrorEnabled(false);
        nameResponsibleVisitField.setErrorEnabled(false);
        nameInspectionTeamMembersField.setErrorEnabled(false);
        totStudentsField.setErrorEnabled(false);
        totStudentsPcd.setErrorEnabled(false);
        studentsPcdDescriptionField.setErrorEnabled(false);
        totWorkersField.setErrorEnabled(false);
        workersPcdDescriptionField.setErrorEnabled(false);
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

    public SchoolEntry createEntry() {
        return new SchoolEntry(nameSchool.getText().toString(), addressSchool.getText().toString(),
                addressComplement.getText().toString(), addressNumber.getText().toString(), addressNeighborhood.getText().toString(),
                nameCity.getText().toString(), nameDirector.getText().toString(), contactPhone1.getText().toString(),
                contactPhone2.getText().toString(), nameResponsibleVisit.getText().toString(), nameInspectionTeamMembers.getText().toString(),
                checkValueCheckbox(hasMorningClasses),morningStartTime.getText().toString(), morningEndTime.getText().toString(),
                checkValueCheckbox(hasAfternoonClasses), afternoonStartTime.getText().toString(), afternoonEndTime.getText().toString(),
                checkValueCheckbox(hasEveningClasses), eveningStartTime.getText().toString(), eveningEndTime.getText().toString(),
                checkValueCheckbox(hasMaternal), maternalFirstGrade.getText().toString(), maternalLastGrade.getText().toString(),
                checkValueCheckbox(hasPreschool), preschoolFirstGrade.getText().toString(), preschoolLastGrade.getText().toString(),
                checkValueCheckbox(hasElementary), elementaryFirstGrade.getText().toString(), elementaryLastGrade.getText().toString(),
                checkValueCheckbox(hasMiddle), middleFirstGrade.getText().toString(), middleLastGrade.getText().toString(),
                checkValueCheckbox(hasHigh), highFirstGrade.getText().toString(), highLastGrade.getText().toString(),
                checkValueCheckbox(hasEJA), ejaFirstGrade.getText().toString(), ejaLastGrade.getText().toString(),
                Integer.parseInt(youngestStudentAge.getText().toString()), Integer.parseInt(oldestStudentAge.getText().toString()),
                Integer.parseInt(totalStudents.getText().toString()), Integer.parseInt(totalStudentsPcd.getText().toString()),
                studentsPcdDescription.getText().toString(), Integer.parseInt(totalWorkers.getText().toString()),
                Integer.parseInt(totalWorkersPcd.getText().toString()), workersPcdDescription.getText().toString(),
                Integer.parseInt(totalWorkersLibras.getText().toString()), stringToDate(dateInspectionText.getText().toString()));
    }

    @SuppressLint("SetTextI18n")
    public void gatherEntry(SchoolEntry schoolEntry) {
        nameSchool.setText(schoolEntry.getSchoolName());
        addressSchool.setText(schoolEntry.getSchoolAddress());
        addressComplement.setText(schoolEntry.getAddressComplement());
        addressNumber.setText(schoolEntry.getAddressNumber());
        addressNeighborhood.setText(schoolEntry.getAddressNeighborhood());
        nameCity.setText(schoolEntry.getNameCity());
        nameDirector.setText(schoolEntry.getNameDirector());
        contactPhone1.setText(schoolEntry.getContactPhone1());
        contactPhone2.setText(schoolEntry.getContactPhone2());
        nameResponsibleVisit.setText(schoolEntry.getNameResponsibleVisit());
        nameInspectionTeamMembers.setText(schoolEntry.getNameInspectionTeam());
//        hasMorningClasses
        morningStartTime.setText(schoolEntry.getMorningStart());
        morningEndTime.setText(schoolEntry.getMorningEnd());
//        hasAfternoonClasses
        afternoonStartTime.setText(schoolEntry.getAfternoonStart());
        afternoonEndTime.setText(schoolEntry.getAfternoonEnd());
//        hasEveningClasses
        eveningStartTime.setText(schoolEntry.getEveningStart());
        eveningEndTime.setText(schoolEntry.getEveningEnd());
//        hasMaternal
        maternalFirstGrade.setText(schoolEntry.getMaternalFirstGrade());
        maternalLastGrade.setText(schoolEntry.getMaternalLastGrade());
//        hasPre
        preschoolFirstGrade.setText(schoolEntry.getPreschoolFirstGrade());
        preschoolLastGrade.setText(schoolEntry.getPreschoolLastGrade());
//        hasElem
        elementaryFirstGrade.setText(schoolEntry.getElementaryFirstGrade());
        elementaryLastGrade.setText(schoolEntry.getElementaryLastGrade());
//        hasMiddle
        middleFirstGrade.setText(schoolEntry.getMiddleFirstGrade());
        middleLastGrade.setText(schoolEntry.getMiddleLastGrade());
//        hasHigh
        highFirstGrade.setText(schoolEntry.getHighFirstGrade());
        highLastGrade.setText(schoolEntry.getHighLastGrade());
//        hasEJA
        ejaFirstGrade.setText(schoolEntry.getEjaFirstGrade());
        ejaLastGrade.setText(schoolEntry.getEjaLastGrade());
        youngestStudentAge.setText(Integer.toString(schoolEntry.getYoungestStudent()));
        oldestStudentAge.setText(Integer.toString(schoolEntry.getOldestStudent()));
        totalStudents.setText(Integer.toString(schoolEntry.getNumberStudents()));
        totalStudentsPcd.setText(Integer.toString(schoolEntry.getNumberStudentsPcd()));
        studentsPcdDescription.setText(schoolEntry.getStudentsPcdDescription());
        totalWorkers.setText(Integer.toString(schoolEntry.getNumberWorkers()));
        totalWorkersPcd.setText(Integer.toString(schoolEntry.getNumberWorkersPcd()));
        workersPcdDescription.setText(schoolEntry.getWorkersPcdDescription());
        totalWorkersLibras.setText(Integer.toString(schoolEntry.getNumberWorkersLibras()));
        dateInspectionText.setText(dateDatabaseToRegister(schoolEntry.getDateInspection()));
    }
}
