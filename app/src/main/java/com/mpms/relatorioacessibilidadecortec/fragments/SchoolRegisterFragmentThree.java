package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterThree;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class SchoolRegisterFragmentThree extends Fragment {

    TextInputLayout startAgeField, finalAgeField, totalStudentsField, totalStudentsPcdField, studentPcdDescriptionField,
            totalWorkersField, totalWorkersPcdField, workersPcdDescriptionField, totalWorkersLibrasField, startInspectionDateField,
            endInspectionDateField;
    TextInputEditText startAgeValue, finalAgeValue, totalStudentsValue, totalStudentsPcdValue, studentPcdDescriptionValue,
            totalWorkersValue, totalWorkersPcdValue, workersPcdDescriptionValue, totalWorkersLibrasValue, startInspectionDateValue,
            endInspectionDateValue;
    MaterialDatePicker<Long> initialDate, finalDate;
    CalendarConstraints.Builder constraints;

    Integer youngestStudent, oldestStudent, totalStudents, totalPcdStudents, totalEmployees, totalPcdEmployees, librasEmployees;
    String studentsPcdDescription, employeesPcdDescription, startingDateInspection, endDateInspection;

    static Bundle bundleFragThree = new Bundle();

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    //    TODO - Colocar o Locale no App em vez de só neste fragmento. Verificar como.
    Locale locale = new Locale("BR");

    Configuration configuration = new Configuration();
    Date date;


    public static SchoolRegisterFragmentThree newInstance(Bundle bundle) {
        SchoolRegisterFragmentThree fragmentThree = new SchoolRegisterFragmentThree();
        fragmentThree.setArguments(bundle);
        SchoolRegisterActivity.provideSchoolID(bundle, bundleFragThree);
        return fragmentThree;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_register_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSchoolFragThree(view);
        allowSidewalkObsScroll();

        if (bundleFragThree.getInt(SchoolRegisterActivity.SCHOOL_ID) > 0)
            modelEntry.getEntry(bundleFragThree.getInt(SchoolRegisterActivity.SCHOOL_ID)).observe(getViewLifecycleOwner(), this::gatherSchoolDataFragThree);

        modelFragments.getSaveUpdateSchoolReg().observe(getViewLifecycleOwner(), clickButton -> {
            if (clickButton != null) {
                if (checkEmptyFieldsFragThree()) {
                    SchoolRegisterThree updateFragThree = updateRegisterThree(bundleFragThree);
                    ViewModelEntry.updateSchoolRegThree(updateFragThree);
                    if (clickButton.getBoolean(SchoolRegisterActivity.UPDATE_CONTINUE)) {
                        bundleFragThree.putBoolean(SchoolRegisterActivity.NEXT_ACTIVITY, true);
                        modelFragments.setDataFromFragToActivity(bundleFragThree);
                        bundleFragThree.putBoolean(SchoolRegisterActivity.NEXT_ACTIVITY, false);
                    } else if (clickButton.getBoolean(SchoolRegisterActivity.UPDATE_CLOSE)) {
                        bundleFragThree.putBoolean(SchoolRegisterActivity.UPDATE_CLOSE, false);
                        bundleFragThree.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, true);
                        modelFragments.setDataFromFragToActivity(bundleFragThree);
                        bundleFragThree.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, false);
                    }
                }
            }
        });
    }

    private boolean checkEmptyFieldsFragThree() {
        int i = 0;
        if (TextUtils.isEmpty(startAgeValue.getText())) {
            i++;
            startAgeField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(finalAgeValue.getText())) {
            i++;
            finalAgeField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(totalStudentsValue.getText())) {
            i++;
            totalStudentsField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(totalStudentsPcdValue.getText())) {
            i++;
            totalStudentsPcdField.setError(getString(R.string.blank_field_error));
        } else if (TextUtils.isEmpty(studentPcdDescriptionValue.getText()) &&
                !TextUtils.equals(String.valueOf(totalStudentsPcdValue.getText()), "0")) {
            i++;
            studentPcdDescriptionField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(totalWorkersValue.getText())) {
            i++;
            totalWorkersField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(totalWorkersPcdValue.getText())) {
            i++;
            totalWorkersPcdField.setError(getString(R.string.blank_field_error));
        } else if (TextUtils.isEmpty(workersPcdDescriptionValue.getText()) &&
                !TextUtils.equals(String.valueOf(totalWorkersPcdValue.getText()), "0")) {
            i++;
            studentPcdDescriptionField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(totalWorkersLibrasValue.getText())) {
            i++;
            totalWorkersLibrasField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(startInspectionDateValue.getText())) {
            i++;
            startInspectionDateField.setError(getString(R.string.blank_field_error));
        }

        return i == 0;
    }

    private void gatherSchoolDataFragThree(SchoolEntry school) {
        startAgeValue.setText(String.valueOf(school.getYoungestStudent()));
        finalAgeValue.setText(String.valueOf(school.getOldestStudent()));
        totalStudentsValue.setText(String.valueOf(school.getNumberStudents()));
        totalStudentsPcdValue.setText(String.valueOf(school.getNumberStudentsPcd()));
        studentPcdDescriptionValue.setText(school.getStudentsPcdDescription());
        totalWorkersValue.setText(String.valueOf(school.getNumberWorkers()));
        totalWorkersPcdValue.setText(String.valueOf(school.getNumberWorkersPcd()));
        workersPcdDescriptionValue.setText(school.getWorkersPcdDescription());
        totalWorkersLibrasValue.setText(String.valueOf(school.getNumberWorkersLibras()));
        startInspectionDateValue.setText(school.getDateInspection());
        endInspectionDateValue.setText(school.getDateInspectionEnd());
    }

    private void instantiateSchoolFragThree(View v) {
        configuration.setLocale(locale);
//        TextInputLayout
        startAgeField = v.findViewById(R.id.students_newest_age_field);
        finalAgeField = v.findViewById(R.id.students_oldest_age_field);
        totalStudentsField = v.findViewById(R.id.total_students_field);
        totalStudentsPcdField = v.findViewById(R.id.total_pcd_students_field);
        studentPcdDescriptionField = v.findViewById(R.id.description_pcd_students_field);
        totalWorkersField = v.findViewById(R.id.total_workers_field);
        totalWorkersPcdField = v.findViewById(R.id.total_pcd_workers_field);
        workersPcdDescriptionField = v.findViewById(R.id.description_pcd_workers_field);
        totalWorkersLibrasField = v.findViewById(R.id.total_workers_libras_field);
        startInspectionDateField = v.findViewById(R.id.initial_date_inspection_field);
        endInspectionDateField = v.findViewById(R.id.final_date_inspection_field);
//        TextInputEditText
        startAgeValue = v.findViewById(R.id.students_newest_age_value);
        finalAgeValue = v.findViewById(R.id.students_oldest_age_value);
        totalStudentsValue = v.findViewById(R.id.total_students_value);
        totalStudentsPcdValue = v.findViewById(R.id.total_pcd_students_value);
        studentPcdDescriptionValue = v.findViewById(R.id.description_pcd_students_value);
        totalWorkersValue = v.findViewById(R.id.total_workers_value);
        totalWorkersPcdValue = v.findViewById(R.id.total_pcd_workers_value);
        workersPcdDescriptionValue = v.findViewById(R.id.description_pcd_workers_value);
        totalWorkersLibrasValue = v.findViewById(R.id.total_workers_libras_value);
        startInspectionDateValue = v.findViewById(R.id.initial_date_inspection_value);
        endInspectionDateValue = v.findViewById(R.id.final_date_inspection_value);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Date ClickListeners
        startInspectionDateValue.setOnClickListener(this::showDatePicker);
        endInspectionDateValue.setOnClickListener(this::showDatePicker);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
    }

    private void showDatePicker(View view) {
        //        MaterialDatePickers
        if (view == startInspectionDateValue) {
            if (!TextUtils.isEmpty(endInspectionDateValue.getText())) {
                long endInspection = stringToLongConverter(String.valueOf(endInspectionDateValue.getText()));
                constraints = new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.before(endInspection));
                initialDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_start_inspection).
                        setCalendarConstraints(constraints.build()).build();
            } else {
                initialDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_start_inspection).build();
            }
            initialDate.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER");
            initialDate.addOnPositiveButtonClickListener(selection -> startInspectionDateValue.setText(longToStringConverter(selection)));
        } else if (view == endInspectionDateValue) {
            if (!TextUtils.isEmpty(startInspectionDateValue.getText())) {
                long startInspection = stringToLongConverter(String.valueOf(startInspectionDateValue.getText()));
                constraints = new CalendarConstraints.Builder().setValidator(DateValidatorPointForward.from(startInspection));
                finalDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_end_inspection).
                        setCalendarConstraints(constraints.build()).build();
            } else {
                finalDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_end_inspection).build();
            }
            finalDate.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER");
            finalDate.addOnPositiveButtonClickListener(selection -> endInspectionDateValue.setText(longToStringConverter(selection)));
        }
    }

    private String longToStringConverter(Long dateTime) {
        return DateFormat.getDateInstance(DateFormat.SHORT, locale).format(dateTime);
    }

    private long stringToLongConverter(String dateTime) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", locale);
            return LocalDate.parse(dateTime, formatter).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        } else {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", locale);
            try {
                date = format.parse(dateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date.getTime();
        }
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSidewalkObsScroll() {
        studentPcdDescriptionValue.setOnTouchListener(this::scrollingField);
        workersPcdDescriptionValue.setOnTouchListener(this::scrollingField);
    }

    private SchoolRegisterThree updateRegisterThree(Bundle bundle) {
        youngestStudent = Integer.valueOf(String.valueOf(startAgeValue.getText()));
        oldestStudent = Integer.valueOf(String.valueOf(finalAgeValue.getText()));
        totalStudents = Integer.valueOf(String.valueOf(totalStudentsValue.getText()));
        totalPcdStudents = Integer.valueOf(String.valueOf(totalStudentsPcdValue.getText()));
        totalEmployees = Integer.valueOf(String.valueOf(totalWorkersValue.getText()));
        totalPcdEmployees = Integer.valueOf(String.valueOf(totalWorkersPcdValue.getText()));
        librasEmployees = Integer.valueOf(String.valueOf(totalWorkersLibrasValue.getText()));
        studentsPcdDescription = String.valueOf(studentPcdDescriptionValue.getText());
        employeesPcdDescription = String.valueOf(workersPcdDescriptionValue.getText());
        startingDateInspection = String.valueOf(startInspectionDateValue.getText());
        endDateInspection = String.valueOf(endInspectionDateValue.getText());

        return new SchoolRegisterThree(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID), youngestStudent, oldestStudent, totalStudents,
                totalPcdStudents, studentsPcdDescription, totalEmployees, totalPcdEmployees, employeesPcdDescription, librasEmployees,
                startingDateInspection, endDateInspection);
    }
}