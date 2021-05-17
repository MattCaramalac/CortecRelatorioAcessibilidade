package com.mpms.relatorioacessibilidadecortec.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

//      TODO - Estudar para usar Fragments no lugar de Activities - Garante melhor Design em Tablets + diminui gasto de memória
public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private static final int MIN_NUMBER_LENGTH = 10;
    public static final String MEMORIAL_ITEM_ENTRY = "MEMORIAL_ITEM_ENTRY";
    public LocalDate chosenDate;
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
    TextView timeScheduleError, schoolServicesError, agesError;
    Button saveCloseButton, saveContinueButton, updateEntryButton, updateContinueButton;
    CheckBox hasMorningClasses, hasAfternoonClasses, hasEveningClasses, hasMaternal, hasPreschool, hasElementary, hasMiddle, hasHigh, hasEJA;
    private int cadID = -1;
    private int lastCadID;
    private SchoolEntry lastEntry;

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_register);
        Locale aLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        Locale.setDefault(aLocale);

        //Só podem ser iniciados DENTRO do onCreate, caso contrário não foi selecionada ainda a View
        //E acaba causando um apontamento para algo nulo (já que não tem View selecioada para ser achada)

        timeScheduleError = findViewById(R.id.time_schedule_error);
        agesError = findViewById(R.id.min_max_ages_error);
        schoolServicesError = findViewById(R.id.services_error);

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
        updateContinueButton = findViewById(R.id.updateContinueButton);

        //Usar mesmo Activity para cadastro e update. Preenche os campos com as informações da DB

        disableAllCheckboxFields();

        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST)) {
            saveCloseButton.setVisibility(View.GONE);
            saveContinueButton.setVisibility(View.GONE);

            cadID = getIntent().getIntExtra(MainActivity.UPDATE_REQUEST, 0);
            ViewModelEntry gatherInfo = new ViewModelEntry(RegisterActivity.this.getApplication());
            gatherInfo.getEntry(cadID).observe(this, this::gatherEntry);
        } else {
            updateEntryButton.setVisibility(View.GONE);
            updateContinueButton.setVisibility(View.GONE);
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

        saveContinueButton.setOnClickListener(v -> {
            if (verifyErrors()) {
                SchoolEntry newEntry = createEntry();
                ViewModelEntry recentEntry = new ViewModelEntry(RegisterActivity.this.getApplication());
//              Crie o observador COM TODOS OS MÉTODOS NECESSÁRIOS E SÓ ENTÃO faça a inserção
                recentEntry.getLastEntry().observe(this, lastEntry -> {
                    lastCadID = lastEntry.getCadID();
//                  LiveData PRECISA ser observado para poder obter os dados
                    Toast.makeText(this, "lastCadID = " + lastCadID, Toast.LENGTH_LONG).show();
                    Intent itemInspectionIntent = new Intent(RegisterActivity.this, InspectionActivity.class);
                    itemInspectionIntent.putExtra(MEMORIAL_ITEM_ENTRY, lastCadID);
                    startActivity(itemInspectionIntent);
                });
//              Isto garante que, ao ser inserido, o observador será ativado e receberá os dados solicitados
                ViewModelEntry.insert(newEntry);
            }
        });

        updateEntryButton.setOnClickListener(v -> {
            if (verifyErrors()) {
                SchoolEntry updateEntry = createEntry();
                updateEntry.setCadID(cadID);
                ViewModelEntry.update(updateEntry);
                finish();
            }
        });

        updateContinueButton.setOnClickListener(v -> {
            if (verifyErrors()) {
                SchoolEntry updateEntry = createEntry();
                updateEntry.setCadID(cadID);
                ViewModelEntry.update(updateEntry);
                Intent itemInspectionIntent = new Intent(RegisterActivity.this, InspectionActivity.class);
                itemInspectionIntent.putExtra(MEMORIAL_ITEM_ENTRY, cadID);
                startActivity(itemInspectionIntent);
                releaseInstance();
            }

        });
    }

    //  TODO - Refazer a forma de captar e armazenar datas para manter integridade
    private void showDatePicker() {
        MaterialDatePicker<Long> datePickerDialog = MaterialDatePicker.Builder
                .datePicker().setTitleText("Data de Inspeção").setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
        datePickerDialog.show(getSupportFragmentManager(), "DATE_PICKER");

        datePickerDialog.addOnPositiveButtonClickListener(selection -> dateInspectionText.setText(dateToString(selection)));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public void checkboxListener() {

        hasMorningClasses.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                morningStartTimeField.setEnabled(true);
                morningEndTimeField.setEnabled(true);
            } else {
                morningStartTime.setText(null);
                morningEndTime.setText(null);
                morningStartTimeField.setEnabled(false);
                morningEndTimeField.setEnabled(false);
            }

        });

        hasAfternoonClasses.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                afternoonStartTimeField.setEnabled(true);
                afternoonEndTimeField.setEnabled(true);
            } else {
                afternoonStartTime.setText(null);
                afternoonEndTime.setText(null);
                afternoonStartTimeField.setEnabled(false);
                afternoonEndTimeField.setEnabled(false);
            }

        });

        hasEveningClasses.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                eveningStartTimeField.setEnabled(true);
                eveningEndTimeField.setEnabled(true);
            } else {
                eveningStartTime.setText(null);
                eveningEndTime.setText(null);
                eveningStartTimeField.setEnabled(false);
                eveningEndTimeField.setEnabled(false);
            }

        });

        hasMaternal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                maternalFirstGradeField.setEnabled(true);
                maternalLastGradeField.setEnabled(true);
            } else {
                maternalFirstGrade.setText(null);
                maternalLastGrade.setText(null);
                maternalFirstGradeField.setEnabled(false);
                maternalLastGradeField.setEnabled(false);
            }

        });

        hasPreschool.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                preschoolFirstGradeField.setEnabled(true);
                preschoolLastGradeField.setEnabled(true);
            } else {
                preschoolFirstGrade.setText(null);
                preschoolLastGrade.setText(null);
                preschoolFirstGradeField.setEnabled(false);
                preschoolLastGradeField.setEnabled(false);
            }

        });

        hasElementary.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                elementaryFirstGradeField.setEnabled(true);
                elementaryLastGradeField.setEnabled(true);
            } else {
                elementaryFirstGrade.setText(null);
                elementaryLastGrade.setText(null);
                elementaryFirstGradeField.setEnabled(false);
                elementaryLastGradeField.setEnabled(false);
            }

        });

        hasMiddle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                middleFirstGradeField.setEnabled(true);
                middleLastGradeField.setEnabled(true);
            } else {
                middleFirstGrade.setText(null);
                middleLastGrade.setText(null);
                middleFirstGradeField.setEnabled(false);
                middleLastGradeField.setEnabled(false);
            }

        });

        hasHigh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                highFirstGradeField.setEnabled(true);
                highLastGradeField.setEnabled(true);
            } else {
                highFirstGrade.setText(null);
                highLastGrade.setText(null);
                highFirstGradeField.setEnabled(false);
                highLastGradeField.setEnabled(false);
            }

        });

        hasEJA.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                ejaFirstGradeField.setEnabled(true);
                ejaLastGradeField.setEnabled(true);
            } else {
                ejaFirstGrade.setText(null);
                ejaLastGrade.setText(null);
                ejaFirstGradeField.setEnabled(false);
                ejaLastGradeField.setEnabled(false);
            }

        });
    }

    public Integer checkValueCheckbox(CheckBox checkBox) {
        if (checkBox.isChecked())
            return 1;
        else
            return 0;
    }

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
        } else if (Objects.requireNonNull(contactPhone1.getText()).toString().length() < MIN_NUMBER_LENGTH) {
            contactPhone1Field.setError(getString(R.string.invalid_input_error));
            i++;
        }
        if (!TextUtils.isEmpty(contactPhone2.getText()) && Objects.requireNonNull(contactPhone2.getText()).toString().length() < MIN_NUMBER_LENGTH) {
            contactPhone2Field.setError(getString(R.string.invalid_input_error));
            i++;
        }
        if (TextUtils.isEmpty(nameResponsibleVisit.getText())) {
            nameResponsibleVisitField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(nameInspectionTeamMembers.getText())) {
            nameInspectionTeamMembersField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (hasMorningClasses.isChecked()) {
            if (TextUtils.isEmpty(morningStartTime.getText()) || TextUtils.isEmpty(morningEndTime.getText())) {
                timeScheduleError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasAfternoonClasses.isChecked()) {
            if (TextUtils.isEmpty(afternoonStartTime.getText()) || TextUtils.isEmpty(afternoonEndTime.getText())) {
                timeScheduleError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasEveningClasses.isChecked()) {
            if (TextUtils.isEmpty(eveningStartTime.getText()) || TextUtils.isEmpty(eveningEndTime.getText())) {
                timeScheduleError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasMaternal.isChecked()) {
            if (TextUtils.isEmpty(maternalFirstGrade.getText()) || TextUtils.isEmpty(maternalLastGrade.getText())) {
                schoolServicesError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasPreschool.isChecked()) {
            if (TextUtils.isEmpty(preschoolFirstGrade.getText()) || TextUtils.isEmpty(preschoolLastGrade.getText())) {
                schoolServicesError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasElementary.isChecked()) {
            if (TextUtils.isEmpty(elementaryFirstGrade.getText()) || TextUtils.isEmpty(elementaryLastGrade.getText())) {
                schoolServicesError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasMiddle.isChecked()) {
            if (TextUtils.isEmpty(middleFirstGrade.getText()) || TextUtils.isEmpty(middleLastGrade.getText())) {
                schoolServicesError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasHigh.isChecked()) {
            if (TextUtils.isEmpty(highFirstGrade.getText()) || TextUtils.isEmpty(highLastGrade.getText())) {
                schoolServicesError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (hasEJA.isChecked()) {
            if (TextUtils.isEmpty(ejaFirstGrade.getText()) || TextUtils.isEmpty(ejaLastGrade.getText())) {
                schoolServicesError.setVisibility(View.VISIBLE);
                i++;
            }
        }
        if (TextUtils.isEmpty(youngestStudentAge.getText())) {
            agesError.setVisibility(View.VISIBLE);
            i++;
        }
        if (TextUtils.isEmpty(oldestStudentAge.getText())) {
            agesError.setVisibility(View.VISIBLE);
            i++;
        }
        if (TextUtils.isEmpty(totalStudents.getText())) {
            totStudentsField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(totalStudentsPcd.getText())) {
            totStudentsPcd.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (!TextUtils.isEmpty(totalStudentsPcd.getText()) && !TextUtils.equals(Objects.requireNonNull(totalStudentsPcd.getText()).toString(), "0")) {
            if (TextUtils.isEmpty(studentsPcdDescription.getText())) {
                studentsPcdDescriptionField.setError(getString(R.string.blank_field_error));
                i++;
            }
        }
        if (TextUtils.isEmpty(totalWorkers.getText())) {
            totWorkersField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (TextUtils.isEmpty(totalWorkersPcd.getText())) {
            totWorkersPcd.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (!TextUtils.isEmpty(totalWorkersPcd.getText()) && !TextUtils.equals(Objects.requireNonNull(totalWorkersPcd.getText()).toString(), "0")) {
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
        timeScheduleError.setVisibility(View.GONE);
        schoolServicesError.setVisibility(View.GONE);
        agesError.setVisibility(View.GONE);

    }

    public String dateToString(Long dateLong) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chosenDate = Instant.ofEpochMilli(dateLong).atZone(ZoneId.of("Z")).toLocalDate();
            return lessThanTen(chosenDate.getDayOfMonth()) + "/" + lessThanTen(chosenDate.getMonthValue()) + "/" + chosenDate.getYear();
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

    public String dateDatabaseToRegister(String date) {
        String[] dividedString = date.split("-");
        return dividedString[2] + "/" + dividedString[1] + "/" + dividedString[0];
    }

    public String lessThanTen(int number) {
        if (number < 10)
            return "0" + number;
        else
            return Integer.toString(number);
    }

    public SchoolEntry createEntry() {
        return new SchoolEntry(Objects.requireNonNull(nameSchool.getText()).toString(),
                Objects.requireNonNull(addressSchool.getText()).toString(),
                Objects.requireNonNull(addressComplement.getText()).toString(),
                Objects.requireNonNull(addressNumber.getText()).toString(),
                Objects.requireNonNull(addressNeighborhood.getText()).toString(),
                Objects.requireNonNull(nameCity.getText()).toString(),
                Objects.requireNonNull(nameDirector.getText()).toString(),
                Objects.requireNonNull(contactPhone1.getText()).toString(),
                Objects.requireNonNull(contactPhone2.getText()).toString(),
                Objects.requireNonNull(nameResponsibleVisit.getText()).toString(),
                Objects.requireNonNull(nameInspectionTeamMembers.getText()).toString(),
                checkValueCheckbox(hasMorningClasses), Objects.requireNonNull(morningStartTime.getText()).toString(),
                Objects.requireNonNull(morningEndTime.getText()).toString(),
                checkValueCheckbox(hasAfternoonClasses), Objects.requireNonNull(afternoonStartTime.getText()).toString(),
                Objects.requireNonNull(afternoonEndTime.getText()).toString(),
                checkValueCheckbox(hasEveningClasses), Objects.requireNonNull(eveningStartTime.getText()).toString(),
                Objects.requireNonNull(eveningEndTime.getText()).toString(),
                checkValueCheckbox(hasMaternal), Objects.requireNonNull(maternalFirstGrade.getText()).toString(),
                Objects.requireNonNull(maternalLastGrade.getText()).toString(),
                checkValueCheckbox(hasPreschool), Objects.requireNonNull(preschoolFirstGrade.getText()).toString(),
                Objects.requireNonNull(preschoolLastGrade.getText()).toString(),
                checkValueCheckbox(hasElementary), Objects.requireNonNull(elementaryFirstGrade.getText()).toString(),
                Objects.requireNonNull(elementaryLastGrade.getText()).toString(),
                checkValueCheckbox(hasMiddle), Objects.requireNonNull(middleFirstGrade.getText()).toString(),
                Objects.requireNonNull(middleLastGrade.getText()).toString(),
                checkValueCheckbox(hasHigh), Objects.requireNonNull(highFirstGrade.getText()).toString(),
                Objects.requireNonNull(highLastGrade.getText()).toString(),
                checkValueCheckbox(hasEJA), Objects.requireNonNull(ejaFirstGrade.getText()).toString(),
                Objects.requireNonNull(ejaLastGrade.getText()).toString(),
                Integer.parseInt(Objects.requireNonNull(youngestStudentAge.getText()).toString()),
                Integer.parseInt(Objects.requireNonNull(oldestStudentAge.getText()).toString()),
                Integer.parseInt(Objects.requireNonNull(totalStudents.getText()).toString()),
                Integer.parseInt(Objects.requireNonNull(totalStudentsPcd.getText()).toString()),
                Objects.requireNonNull(studentsPcdDescription.getText()).toString(),
                Integer.parseInt(Objects.requireNonNull(totalWorkers.getText()).toString()),
                Integer.parseInt(Objects.requireNonNull(totalWorkersPcd.getText()).toString()),
                Objects.requireNonNull(workersPcdDescription.getText()).toString(),
                Integer.parseInt(Objects.requireNonNull(totalWorkersLibras.getText()).toString()),
                stringToDate(Objects.requireNonNull(dateInspectionText.getText()).toString()));
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
        if (schoolEntry.getHasMorningClasses() == 1) {
            hasMorningClasses.setChecked(true);
            morningStartTimeField.setEnabled(true);
            morningEndTimeField.setEnabled(true);
            morningStartTime.setText(schoolEntry.getMorningStart());
            morningEndTime.setText(schoolEntry.getMorningEnd());
        }
        if (schoolEntry.getHasAfternoonClasses() == 1) {
            hasAfternoonClasses.setChecked(true);
            afternoonStartTimeField.setEnabled(true);
            afternoonEndTimeField.setEnabled(true);
            afternoonStartTime.setText(schoolEntry.getAfternoonStart());
            afternoonEndTime.setText(schoolEntry.getAfternoonEnd());
        }
        if (schoolEntry.getHasEveningClasses() == 1) {
            hasEveningClasses.setChecked(true);
            eveningStartTimeField.setEnabled(true);
            eveningEndTimeField.setEnabled(true);
            eveningStartTime.setText(schoolEntry.getEveningStart());
            eveningEndTime.setText(schoolEntry.getEveningEnd());
        }
        if (schoolEntry.getHasMaternal() == 1) {
            hasMaternal.setChecked(true);
            maternalFirstGradeField.setEnabled(true);
            maternalLastGradeField.setEnabled(true);
            maternalFirstGrade.setText(schoolEntry.getMaternalFirstGrade());
            maternalLastGrade.setText(schoolEntry.getMaternalLastGrade());
        }
        if (schoolEntry.getHasPreschool() == 1) {
            hasPreschool.setChecked(true);
            preschoolFirstGradeField.setEnabled(true);
            preschoolLastGradeField.setEnabled(true);
            preschoolFirstGrade.setText(schoolEntry.getPreschoolFirstGrade());
            preschoolLastGrade.setText(schoolEntry.getPreschoolLastGrade());
        }
        if (schoolEntry.getHasElementarySchool() == 1) {
            hasElementary.setChecked(true);
            elementaryFirstGradeField.setEnabled(true);
            elementaryLastGradeField.setEnabled(true);
            elementaryFirstGrade.setText(schoolEntry.getElementaryFirstGrade());
            elementaryLastGrade.setText(schoolEntry.getElementaryLastGrade());
        }
        if (schoolEntry.getHasMiddleSchool() == 1) {
            hasMiddle.setChecked(true);
            middleFirstGradeField.setEnabled(true);
            middleLastGradeField.setEnabled(true);
            middleFirstGrade.setText(schoolEntry.getMiddleFirstGrade());
            middleLastGrade.setText(schoolEntry.getMiddleLastGrade());
        }
        if (schoolEntry.getHasHighSchool() == 1) {
            hasHigh.setChecked(true);
            highFirstGradeField.setEnabled(true);
            highLastGradeField.setEnabled(true);
            highFirstGrade.setText(schoolEntry.getHighFirstGrade());
            highLastGrade.setText(schoolEntry.getHighLastGrade());
        }
        if (schoolEntry.getHasEja() == 1) {
            hasEJA.setChecked(true);
            ejaFirstGradeField.setEnabled(true);
            ejaLastGradeField.setEnabled(true);
            ejaFirstGrade.setText(schoolEntry.getEjaFirstGrade());
            ejaLastGrade.setText(schoolEntry.getEjaLastGrade());
        }
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

    public void disableAllCheckboxFields() {
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
    }
}
