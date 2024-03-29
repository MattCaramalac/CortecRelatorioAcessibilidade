package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterThree;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class SchoolRegisterFragmentThree extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout startAgeField, finalAgeField, totalStudentsField, totalStudentsPcdField, studentPcdDescriptionField,
            totalWorkersField, totalWorkersPcdField, workersPcdDescriptionField, totalWorkersLibrasField, workersLibrasDescriptionField,
            initialDateInspectionField, finalDateInspectionField, studentRegisterObsField;
    TextInputEditText startAgeValue, finalAgeValue, totalStudentsValue, totalStudentsPcdValue, studentPcdDescriptionValue,
            totalWorkersValue, totalWorkersPcdValue, workersPcdDescriptionValue, totalWorkersLibrasValue, workersLibrasDescriptionValue,
            initialDateInspectionValue, finalDateInspectionValue, studentRegisterObsValue;
    RadioGroup youngestMonthYearRadio, oldestMonthYearRadio, hasWorkersLibras;
    TextView studentsAgeError, librasWorkersError;
    MaterialDatePicker<Long> initialDate, finalDate;
    CalendarConstraints.Builder constraints;

    ArrayList<TextInputEditText> eText = new ArrayList<>();

    int defaultColor;
    int sentReport = 0;

    static Bundle bundleFragThree = new Bundle();

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    //    TODO - Colocar o Locale no App em vez de só neste fragmento. Verificar como.
    Locale locale = new Locale("pt");

    Configuration configuration = new Configuration();
    Date date;

    public static SchoolRegisterFragmentThree newInstance(Bundle bundle) {
        SchoolRegisterFragmentThree fragmentThree = new SchoolRegisterFragmentThree();
        fragmentThree.setArguments(bundle);
        bundleFragThree.putInt(SCHOOL_ID, bundle.getInt(SCHOOL_ID));
        bundleFragThree.putBoolean(DATA_COMPLETE, bundle.getBoolean(DATA_COMPLETE));
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


        if (bundleFragThree.getInt(SCHOOL_ID) > 0)
            modelEntry.getEntry(bundleFragThree.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), this::loadSchoolDataThree);

        modelFragments.getSaveUpdateSchoolReg().observe(getViewLifecycleOwner(), clickButton -> {
            if (clickButton != null) {
                if (checkEmptyFieldsFragThree(bundleFragThree)) {
                    SchoolRegisterThree updateFragThree = updateRegisterThree(bundleFragThree);
                    ViewModelEntry.updateSchoolRegThree(updateFragThree);
                    if (clickButton.getBoolean(UPDATE_CONTINUE)) {
                        bundleFragThree.putBoolean(NEXT_ACTIVITY, true);
                        modelFragments.setDataFromFragToActivity(bundleFragThree);
                        bundleFragThree.putBoolean(NEXT_ACTIVITY, false);
                    } else if (clickButton.getBoolean(UPDATE_CLOSE)) {
                        bundleFragThree.putBoolean(UPDATE_CLOSE, false);
                        bundleFragThree.putBoolean(CLOSE_FRAGMENT, true);
                        modelFragments.setDataFromFragToActivity(bundleFragThree);
                        bundleFragThree.putBoolean(CLOSE_FRAGMENT, false);
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
                    modelFragments.setSaveUpdateSchoolReg(null);
                }
            }
        });
    }

    private void instantiateSchoolFragThree(View v) {
//        Configuration - Set Locale by default, not by Fragment
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
        workersLibrasDescriptionField = v.findViewById(R.id.libras_workers_obs_field);
        initialDateInspectionField = v.findViewById(R.id.initial_date_inspection_field);
        finalDateInspectionField = v.findViewById(R.id.final_date_inspection_field);
        studentRegisterObsField = v.findViewById(R.id.students_register_obs_field);
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
        workersLibrasDescriptionValue = v.findViewById(R.id.libras_workers_obs_value);
        initialDateInspectionValue = v.findViewById(R.id.initial_date_inspection_value);
        finalDateInspectionValue = v.findViewById(R.id.final_date_inspection_value);
        studentRegisterObsValue = v.findViewById(R.id.students_register_obs_value);
//        RadioGroups
        youngestMonthYearRadio = v.findViewById(R.id.youngest_age_month_year_radio);
        oldestMonthYearRadio = v.findViewById(R.id.oldest_age_month_year_radio);
        hasWorkersLibras = v.findViewById(R.id.has_libras_workers_radio);
//        TextView
        studentsAgeError = v.findViewById(R.id.students_age_error_message);
        librasWorkersError = v.findViewById(R.id.libras_workers_error);
//        Default Color
        defaultColor = startAgeField.getBoxStrokeColor();
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Date ClickListeners
        initialDateInspectionValue.setOnClickListener(this::showDatePicker);
        finalDateInspectionValue.setOnClickListener(this::showDatePicker);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        hasWorkersLibras.setOnCheckedChangeListener(this::radioListener);

        addScrollFields();
        allowObsScroll(eText);
    }

    private void addScrollFields() {
        eText.add(workersPcdDescriptionValue);
        eText.add(studentPcdDescriptionValue);
        eText.add(workersLibrasDescriptionValue);
    }

    private boolean checkEmptyFieldsFragThree(Bundle bundle) {
        clearEmptyFieldsFragThreeErrors();
        int i = 0;
        if (TextUtils.isEmpty(startAgeValue.getText()))
            bundle.putBoolean(DATA_COMPLETE, false);
        else if (youngestMonthYearRadio.getCheckedRadioButtonId() == -1) {
            i++;
            studentsAgeError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(finalAgeValue.getText()))
            bundle.putBoolean(DATA_COMPLETE, false);
        else if (oldestMonthYearRadio.getCheckedRadioButtonId() == -1) {
            i++;
            studentsAgeError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(totalStudentsValue.getText()))
            bundle.putBoolean(DATA_COMPLETE, false);
        else if (TextUtils.isEmpty(totalStudentsPcdValue.getText())) {
            i++;
            totalStudentsPcdField.setError(getString(R.string.req_field_error));
        } else if (TextUtils.isEmpty(studentPcdDescriptionValue.getText()) &&
                !TextUtils.equals(String.valueOf(totalStudentsPcdValue.getText()), "0")) {
            i++;
            studentPcdDescriptionField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(totalWorkersValue.getText()))
            bundle.putBoolean(DATA_COMPLETE, false);
        else if (TextUtils.isEmpty(totalWorkersPcdValue.getText())) {
            i++;
            totalWorkersPcdField.setError(getString(R.string.req_field_error));
        } else if (TextUtils.isEmpty(workersPcdDescriptionValue.getText()) &&
                !TextUtils.equals(String.valueOf(totalWorkersPcdValue.getText()), "0")) {
            i++;
            studentPcdDescriptionField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(hasWorkersLibras) == -1)
            bundle.putBoolean(DATA_COMPLETE, false);
        else if (indexRadio(hasWorkersLibras) == 1) {
            if (TextUtils.isEmpty(totalWorkersLibrasValue.getText())) {
                i++;
                totalWorkersLibrasField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(workersLibrasDescriptionValue.getText())) {
                i++;
                workersLibrasDescriptionField.setError(getString(R.string.req_field_error));
            }
        }
        if (TextUtils.isEmpty(initialDateInspectionValue.getText()))
            bundle.putBoolean(DATA_COMPLETE, false);

        return i == 0;
    }

    private void clearEmptyFieldsFragThreeErrors() {
        startAgeField.setErrorEnabled(false);
        finalAgeField.setErrorEnabled(false);
        studentsAgeError.setVisibility(View.GONE);
        totalStudentsField.setErrorEnabled(false);
        totalStudentsPcdField.setErrorEnabled(false);
        studentPcdDescriptionField.setErrorEnabled(false);
        totalWorkersField.setErrorEnabled(false);
        totalWorkersPcdField.setErrorEnabled(false);
        workersPcdDescriptionField.setErrorEnabled(false);
        librasWorkersError.setVisibility(View.GONE);
        totalWorkersLibrasField.setErrorEnabled(false);
        workersLibrasDescriptionField.setErrorEnabled(false);
        initialDateInspectionField.setErrorEnabled(false);
        finalDateInspectionField.setErrorEnabled(false);

    }

    private void loadSchoolDataThree(SchoolEntry school) {
        if (school.getYoungestStudentAge() != null)
            startAgeValue.setText(String.valueOf(school.getYoungestStudentAge()));
        if (school.getMonthYearYoungest() != null)
            checkRadioGroup(youngestMonthYearRadio, school.getMonthYearYoungest());
        if (school.getOldestStudentAge() != null)
            finalAgeValue.setText(String.valueOf(school.getOldestStudentAge()));
        if (school.getMonthYearOldest() != null)
            checkRadioGroup(oldestMonthYearRadio, school.getMonthYearOldest());
        if (school.getNumberStudents() != null)
            totalStudentsValue.setText(String.valueOf(school.getNumberStudents()));
        if (school.getNumberStudentsPCD() != null)
            totalStudentsPcdValue.setText(String.valueOf(school.getNumberStudentsPCD()));
        studentPcdDescriptionValue.setText(school.getStudentsPCDDescription());
        if (school.getNumberWorkers() != null)
            totalWorkersValue.setText(String.valueOf(school.getNumberWorkers()));
        if (school.getNumberWorkersPCD() != null)
            totalWorkersPcdValue.setText(String.valueOf(school.getNumberWorkersPCD()));
        if (school.getWorkersPCDDescription() != null)
            workersPcdDescriptionValue.setText(school.getWorkersPCDDescription());
        if (school.getHasWorkersLibras() != null)
            checkRadioGroup(hasWorkersLibras, school.getHasWorkersLibras());
//            hasWorkersLibras.check(hasWorkersLibras.getChildAt().getId());
        if (school.getNumberWorkersLibras() != null)
            totalWorkersLibrasValue.setText(String.valueOf(school.getNumberWorkersLibras()));
        if (school.getWorkersLibrasDescriptions() != null)
            workersLibrasDescriptionValue.setText(school.getWorkersLibrasDescriptions());
        if (school.getInitialDateInspection() != null)
            initialDateInspectionValue.setText(school.getInitialDateInspection());
        if (school.getFinalDateInspection() != null)
            finalDateInspectionValue.setText(school.getFinalDateInspection());
        sentReport = school.getReportSent();
    }

    private void showDatePicker(View view) {
        TimeZone utc = TimeZone.getDefault();
        int offset = utc.getOffset(new Date().getTime()) * -1;
        //        MaterialDatePickers
        if (view == initialDateInspectionValue) {
            if (!TextUtils.isEmpty(finalDateInspectionValue.getText())) {
                long endInspection = stringToLongConverter(String.valueOf(finalDateInspectionValue.getText()));
                constraints = new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.before(endInspection));
                initialDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_start_inspection).
                        setCalendarConstraints(constraints.build()).build();
            } else {
                initialDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_start_inspection).build();
            }
            initialDate.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER");
            initialDate.addOnPositiveButtonClickListener(selection -> initialDateInspectionValue.setText(longToStringConverter(selection + offset)));
        } else if (view == finalDateInspectionValue) {
            if (!TextUtils.isEmpty(initialDateInspectionValue.getText())) {
                long startInspection = stringToLongConverter(String.valueOf(initialDateInspectionValue.getText()));
                constraints = new CalendarConstraints.Builder().setValidator(DateValidatorPointForward.from(startInspection));
                finalDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_end_inspection).
                        setCalendarConstraints(constraints.build()).build();
            } else {
                finalDate = MaterialDatePicker.Builder.datePicker().setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR).
                        setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText(R.string.date_picker_end_inspection).build();
            }
            finalDate.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER");
            finalDate.addOnPositiveButtonClickListener(selection -> finalDateInspectionValue.setText(longToStringConverter(selection + offset)));
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
            return date != null ? date.getTime() : 0;
        }
    }

    private SchoolRegisterThree updateRegisterThree(Bundle bundle) {
        Integer totalLibrasEmployees = null, youngestStudent = null, youngestMonthYear = null, oldestStudent = null, oldestMonthYear = null, totalStudents = null,
                totalPcdStudents = null, totalEmployees = null, totalPcdEmployees = null, hasLibrasEmployees = null;
        int update = 0;
        String studentsPcdDescription = null, employeesPcdDescription = null, librasDescriptions = null, initialDateInspection = null, finalDateInspection = null,
                registerObs = null;

        if (!TextUtils.isEmpty(startAgeValue.getText()))
            youngestStudent = Integer.parseInt(String.valueOf(startAgeValue.getText()));
        if (indexRadio(youngestMonthYearRadio) != -1)
            youngestMonthYear = indexRadio(youngestMonthYearRadio);
        if (!TextUtils.isEmpty(finalAgeValue.getText()))
            oldestStudent = Integer.parseInt(String.valueOf(finalAgeValue.getText()));
        if (indexRadio(oldestMonthYearRadio) != -1)
            oldestMonthYear = indexRadio(oldestMonthYearRadio);
        if (!TextUtils.isEmpty(totalStudentsValue.getText()))
            totalStudents = Integer.parseInt(String.valueOf(totalStudentsValue.getText()));
        if (!TextUtils.isEmpty(totalStudentsPcdValue.getText()))
            totalPcdStudents = Integer.parseInt(String.valueOf(totalStudentsPcdValue.getText()));
        if (!TextUtils.isEmpty(studentPcdDescriptionValue.getText()))
            studentsPcdDescription = String.valueOf(studentPcdDescriptionValue.getText());
        if (!TextUtils.isEmpty(totalWorkersValue.getText()))
            totalEmployees = Integer.parseInt(String.valueOf(totalWorkersValue.getText()));
        if (!TextUtils.isEmpty(totalWorkersPcdValue.getText()))
            totalPcdEmployees = Integer.parseInt(String.valueOf(totalWorkersPcdValue.getText()));
        if (!TextUtils.isEmpty(workersPcdDescriptionValue.getText()))
            employeesPcdDescription = String.valueOf(workersPcdDescriptionValue.getText());
        if (indexRadio(hasWorkersLibras) != -1) {
            hasLibrasEmployees = indexRadio(hasWorkersLibras);
            if (hasLibrasEmployees == 1) {
                totalLibrasEmployees = Integer.valueOf(String.valueOf(totalWorkersLibrasValue.getText()));
                librasDescriptions = String.valueOf(workersLibrasDescriptionValue.getText());
            }
        }

        if (!TextUtils.isEmpty(studentRegisterObsValue.getText()))
            registerObs = String.valueOf(studentRegisterObsValue.getText());
        if (!TextUtils.isEmpty(initialDateInspectionValue.getText()))
            initialDateInspection = String.valueOf(initialDateInspectionValue.getText());
        if (!TextUtils.isEmpty(finalDateInspectionValue.getText()))
            finalDateInspection = String.valueOf(finalDateInspectionValue.getText());
        if (bundle.getBoolean(DATA_COMPLETE))
            update = 1;


        return new SchoolRegisterThree(bundle.getInt(SCHOOL_ID), youngestStudent, youngestMonthYear, oldestStudent, oldestMonthYear, totalStudents, totalPcdStudents,
                studentsPcdDescription, totalEmployees, totalPcdEmployees, employeesPcdDescription, hasLibrasEmployees, totalLibrasEmployees, librasDescriptions,
                initialDateInspection, finalDateInspection, registerObs, update, sentReport);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        if (index == 1) {
            totalWorkersLibrasField.setVisibility(View.VISIBLE);
            workersLibrasDescriptionField.setVisibility(View.VISIBLE);
        } else {
            totalWorkersLibrasValue.setText(null);
            workersLibrasDescriptionValue.setText(null);
            totalWorkersLibrasField.setVisibility(View.GONE);
            workersLibrasDescriptionField.setVisibility(View.GONE);
        }
    }
}