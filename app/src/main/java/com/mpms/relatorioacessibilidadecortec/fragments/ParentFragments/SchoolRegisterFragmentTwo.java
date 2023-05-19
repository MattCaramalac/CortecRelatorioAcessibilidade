package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterTwo;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.MaskWatcher;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class SchoolRegisterFragmentTwo extends Fragment implements ScrollEditText, TagInterface {

    TextInputLayout morningStartField, morningEndField, afternoonStartField, afternoonEndField, eveningStartField,
            eveningEndField, workHoursObsField, maternalStartField, maternalEndField, preSchoolStartField, preSchoolEndField,
            elementaryMiddleStartField, elementaryMiddleEndField, highStartField, highEndField, ejaStartField,
            ejaEndField, servicesObsField;
    TextInputEditText morningStartValue, morningEndValue, afternoonStartValue, afternoonEndValue, eveningStartValue,
            eveningEndValue, workHoursObsValue, maternalStartValue, maternalEndValue, preSchoolStartValue, preSchoolEndValue,
            elementaryMiddleStartValue, elementaryMiddleEndValue, highStartValue, highEndValue, ejaStartValue,
            ejaEndValue, servicesObsValue;
    MaterialCheckBox morningCheck, afternoonCheck, eveningCheck, nurseryCheck, daycareCheck, maternalCheck, preSchoolCheck, elementaryMiddleCheck,
            highCheck, ejaCheck;
    TextView workingHoursError, servicesError;

    ArrayList<TextInputEditText> eText = new ArrayList<>();

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    static Bundle bundleFragTwo = new Bundle();

    public SchoolRegisterFragmentTwo() {
        // Required empty public constructor
    }

    public static SchoolRegisterFragmentTwo newInstance(Bundle bundle) {
        SchoolRegisterFragmentTwo fragmentTwo = new SchoolRegisterFragmentTwo();
        fragmentTwo.setArguments(bundle);
        SchoolRegisterActivity.provideSchoolID(bundle, bundleFragTwo);
        return fragmentTwo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_register_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSchoolFragmentTwo(view);
        checkBoxListener();

        if (bundleFragTwo.getInt(SchoolRegisterActivity.SCHOOL_ID) > 0)
            modelEntry.getEntry(bundleFragTwo.getInt(SchoolRegisterActivity.SCHOOL_ID)).observe(getViewLifecycleOwner(), this::gatherSchoolDataFragTwo);

        modelFragments.getSaveUpdateSchoolReg().observe(getViewLifecycleOwner(), clickButton -> {
            if (clickButton != null) {
                if (checkEmptyFieldsFragTwo()) {
                    SchoolRegisterTwo updateFragTwo = updateRegisterTwo(bundleFragTwo);
                    ViewModelEntry.updateSchoolRegTwo(updateFragTwo);
                    if (clickButton.getBoolean(SchoolRegisterActivity.UPDATE_CONTINUE)) {
                        bundleFragTwo.putBoolean(SchoolRegisterActivity.OPEN_FRAG_THREE, true);
                        modelFragments.setDataFromFragToActivity(bundleFragTwo);
                        bundleFragTwo.putBoolean(SchoolRegisterActivity.OPEN_FRAG_THREE, false);
                    } else if (clickButton.getBoolean(SchoolRegisterActivity.UPDATE_CLOSE)) {
                        bundleFragTwo.putBoolean(SchoolRegisterActivity.UPDATE_CLOSE, false);
                        bundleFragTwo.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, true);
                        modelFragments.setDataFromFragToActivity(bundleFragTwo);
                        bundleFragTwo.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, false);
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
                    modelFragments.setSaveUpdateSchoolReg(null);
                }
            }
        });
    }

    private void instantiateSchoolFragmentTwo(View v) {
//        TextInputLayout
        morningStartField = v.findViewById(R.id.morning_hours_start_field);
        morningEndField = v.findViewById(R.id.morning_hours_end_field);
        afternoonStartField = v.findViewById(R.id.afternoon_hours_start_field);
        afternoonEndField = v.findViewById(R.id.afternoon_hours_end_field);
        eveningStartField = v.findViewById(R.id.evening_hours_start_field);
        eveningEndField = v.findViewById(R.id.evening_hours_end_field);
        workHoursObsField = v.findViewById(R.id.working_hours_obs_field);
        maternalStartField = v.findViewById(R.id.maternal_first_level_field);
        maternalEndField = v.findViewById(R.id.maternal_last_level_field);
        preSchoolStartField = v.findViewById(R.id.pre_school_first_level_field);
        preSchoolEndField = v.findViewById(R.id.pre_school_last_level_field);
        elementaryMiddleStartField = v.findViewById(R.id.elem_middle_school_first_level_field);
        elementaryMiddleEndField = v.findViewById(R.id.elem_middle_school_last_level_field);
        highStartField = v.findViewById(R.id.high_school_first_level_field);
        highEndField = v.findViewById(R.id.high_school_last_level_field);
        ejaStartField = v.findViewById(R.id.EJA_first_level_field);
        ejaEndField = v.findViewById(R.id.EJA_last_level_field);
        servicesObsField = v.findViewById(R.id.school_services_obs_field);
//        TextInputEditText
        morningStartValue = v.findViewById(R.id.morning_hours_start_value);
        morningEndValue = v.findViewById(R.id.morning_hours_end_value);
        afternoonStartValue = v.findViewById(R.id.afternoon_hours_start_value);
        afternoonEndValue = v.findViewById(R.id.afternoon_hours_end_value);
        eveningStartValue = v.findViewById(R.id.evening_hours_start_value);
        eveningEndValue = v.findViewById(R.id.evening_hours_end_value);
        workHoursObsValue = v.findViewById(R.id.working_hours_obs_value);
        maternalStartValue = v.findViewById(R.id.maternal_first_level_value);
        maternalEndValue = v.findViewById(R.id.maternal_last_level_value);
        preSchoolStartValue = v.findViewById(R.id.pre_school_first_level_value);
        preSchoolEndValue = v.findViewById(R.id.pre_school_last_level_value);
        elementaryMiddleStartValue = v.findViewById(R.id.elem_middle_school_first_level_value);
        elementaryMiddleEndValue = v.findViewById(R.id.elem_middle_school_last_level_value);
        highStartValue = v.findViewById(R.id.high_school_first_level_value);
        highEndValue = v.findViewById(R.id.high_school_last_level_value);
        ejaStartValue = v.findViewById(R.id.EJA_first_level_value);
        ejaEndValue = v.findViewById(R.id.EJA_last_level_value);
        servicesObsValue = v.findViewById(R.id.school_services_obs_value);
//        DataMasks
        morningStartValue.addTextChangedListener(MaskWatcher.buildWorkingHours());
        morningEndValue.addTextChangedListener(MaskWatcher.buildWorkingHours());
        afternoonStartValue.addTextChangedListener(MaskWatcher.buildWorkingHours());
        afternoonEndValue.addTextChangedListener(MaskWatcher.buildWorkingHours());
        eveningStartValue.addTextChangedListener(MaskWatcher.buildWorkingHours());
        eveningEndValue.addTextChangedListener(MaskWatcher.buildWorkingHours());
//        MaterialCheckBox
        morningCheck = v.findViewById(R.id.checkbox_morning_hours);
        afternoonCheck = v.findViewById(R.id.checkbox_afternoon_hours);
        eveningCheck = v.findViewById(R.id.checkbox_evening_hours);
        nurseryCheck = v.findViewById(R.id.checkbox_nursery);
        daycareCheck = v.findViewById(R.id.checkbox_day_care);
        maternalCheck = v.findViewById(R.id.checkbox_maternal_classes);
        preSchoolCheck = v.findViewById(R.id.checkbox_pre_school_classes);
        elementaryMiddleCheck = v.findViewById(R.id.checkbox_elem_middle_school_classes);
        highCheck = v.findViewById(R.id.checkbox_high_school_classes);
        ejaCheck = v.findViewById(R.id.checkbox_EJA_classes);
//        TextView
        workingHoursError = v.findViewById(R.id.working_hours_error_message);
        servicesError = v.findViewById(R.id.school_services_error_message);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);

        eText.add(servicesObsValue);
        eText.add(workHoursObsValue);
        allowObsScroll(eText);
    }

    private boolean checkEmptyFieldsFragTwo() {
        clearEmptyFieldsErrorFragTwo();
        int i = 0;
        if (!morningCheck.isChecked() && !afternoonCheck.isChecked() && !eveningCheck.isChecked()) {
            i++;
            workingHoursError.setVisibility(View.VISIBLE);
        }
        if (morningCheck.isChecked() &&
                (TextUtils.isEmpty(morningStartValue.getText()) || TextUtils.isEmpty(morningEndValue.getText()))) {
            i++;
            workingHoursError.setVisibility(View.VISIBLE);
        }
        if (afternoonCheck.isChecked() &&
                (TextUtils.isEmpty(afternoonStartValue.getText()) || TextUtils.isEmpty(afternoonEndValue.getText()))) {
            i++;
            workingHoursError.setVisibility(View.VISIBLE);
        }
        if (eveningCheck.isChecked() &&
                (TextUtils.isEmpty(eveningStartValue.getText()) || TextUtils.isEmpty(eveningEndValue.getText()))) {
            i++;
            workingHoursError.setVisibility(View.VISIBLE);
        }
        if (!nurseryCheck.isChecked() && !daycareCheck.isChecked() && !maternalCheck.isChecked() && !preSchoolCheck.isChecked()
                && !elementaryMiddleCheck.isChecked() && !highCheck.isChecked() && !ejaCheck.isChecked()) {
            i++;
            servicesError.setVisibility(View.VISIBLE);
        }
        if (maternalCheck.isChecked() &&
                (TextUtils.isEmpty(maternalStartValue.getText()) ||TextUtils.isEmpty(maternalEndValue.getText()))) {
            i++;
            servicesError.setVisibility(View.VISIBLE);
        }
        if (preSchoolCheck.isChecked() &&
                (TextUtils.isEmpty(preSchoolStartValue.getText()) ||TextUtils.isEmpty(preSchoolEndValue.getText()))) {
            i++;
            servicesError.setVisibility(View.VISIBLE);
        }
        if (elementaryMiddleCheck.isChecked() &&
                (TextUtils.isEmpty(elementaryMiddleStartValue.getText()) ||TextUtils.isEmpty(elementaryMiddleEndValue.getText()))) {
            i++;
            servicesError.setVisibility(View.VISIBLE);
        }
        if (highCheck.isChecked() &&
                (TextUtils.isEmpty(highStartValue.getText()) ||TextUtils.isEmpty(highEndValue.getText()))) {
            i++;
            servicesError.setVisibility(View.VISIBLE);
        }
        if (ejaCheck.isChecked() &&
                (TextUtils.isEmpty(ejaStartValue.getText()) ||TextUtils.isEmpty(ejaEndValue.getText()))) {
            i++;
            servicesError.setVisibility(View.VISIBLE);
        }

        return i == 0;
    }

    private void clearEmptyFieldsErrorFragTwo() {
        workingHoursError.setVisibility(View.GONE);
        servicesError.setVisibility(View.GONE);
    }

    private void gatherSchoolDataFragTwo(SchoolEntry school) {
        if (school.getHasMorningClasses() != null && school.getHasMorningClasses() == 1) {
            morningCheck.setChecked(true);
            morningStartField.setEnabled(true);
            morningEndField.setEnabled(true);
            morningStartValue.setText(school.getMorningStart());
            morningEndValue.setText(school.getMorningEnd());
        }
        if (school.getHasAfternoonClasses() != null && school.getHasAfternoonClasses() == 1) {
            afternoonCheck.setChecked(true);
            afternoonStartField.setEnabled(true);
            afternoonEndField.setEnabled(true);
            afternoonStartValue.setText(school.getAfternoonStart());
            afternoonEndValue.setText(school.getAfternoonEnd());
        }
        if (school.getHasEveningClasses() != null && school.getHasEveningClasses() == 1) {
            eveningCheck.setChecked(true);
            eveningStartField.setEnabled(true);
            eveningEndField.setEnabled(true);
            eveningStartValue.setText(school.getEveningStart());
            eveningEndValue.setText(school.getEveningEnd());
        }
        workHoursObsValue.setText(school.getWorkingHoursObs());
        if (school.getHasNursery() != null && school.getHasNursery() == 1)
            nurseryCheck.setChecked(true);
        if (school.getHasDayCare() != null && school.getHasDayCare() == 1)
            daycareCheck.setChecked(true);
        if (school.getHasMaternal() != null && school.getHasMaternal() == 1) {
            maternalCheck.setChecked(true);
            maternalStartField.setEnabled(true);
            maternalEndField.setEnabled(true);
            maternalStartValue.setText(school.getMaternalFirstGrade());
            maternalEndValue.setText(school.getMaternalLastGrade());
        }
        if (school.getHasPreschool() != null && school.getHasPreschool() == 1) {
            preSchoolCheck.setChecked(true);
            preSchoolStartField.setEnabled(true);
            preSchoolEndField.setEnabled(true);
            preSchoolStartValue.setText(school.getPreschoolFirstGrade());
            preSchoolEndValue.setText(school.getPreschoolLastGrade());
        }
        if (school.getHasElementaryMiddle() != null && school.getHasElementaryMiddle() == 1) {
            elementaryMiddleCheck.setChecked(true);
            elementaryMiddleStartField.setEnabled(true);
            elementaryMiddleEndField.setEnabled(true);
            elementaryMiddleStartValue.setText(school.getElementaryMiddleFirstGrade());
            elementaryMiddleEndValue.setText(school.getElementaryMiddleLastGrade());
        }
        if (school.getHasHighSchool() != null && school.getHasHighSchool() == 1) {
            highCheck.setChecked(true);
            highStartField.setEnabled(true);
            highEndField.setEnabled(true);
            highStartValue.setText(school.getHighFirstGrade());
            highEndValue.setText(school.getHighLastGrade());
        }
        if (school.getHasEja() != null && school.getHasEja() == 1) {
            ejaCheck.setChecked(true);
            ejaStartField.setEnabled(true);
            ejaEndField.setEnabled(true);
            ejaStartValue.setText(school.getEjaFirstGrade());
            ejaEndValue.setText(school.getEjaLastGrade());
        }
        servicesObsValue.setText(school.getServicesObs());
    }

    private void checkBoxListener() {
        morningCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                morningStartField.setEnabled(true);
                morningEndField.setEnabled(true);
            } else {
                morningStartValue.setText(null);
                morningEndValue.setText(null);
                morningStartField.setEnabled(false);
                morningEndField.setEnabled(false);
            }
        });
        afternoonCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                afternoonStartField.setEnabled(true);
                afternoonEndField.setEnabled(true);
            } else {
                afternoonStartValue.setText(null);
                afternoonEndValue.setText(null);
                afternoonStartField.setEnabled(false);
                afternoonEndField.setEnabled(false);
            }
        });
        eveningCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                eveningStartField.setEnabled(true);
                eveningEndField.setEnabled(true);
            } else {
                eveningStartValue.setText(null);
                eveningEndValue.setText(null);
                eveningStartField.setEnabled(false);
                eveningEndField.setEnabled(false);
            }
        });
        maternalCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                maternalStartField.setEnabled(true);
                maternalEndField.setEnabled(true);
            } else {
                maternalStartValue.setText(null);
                maternalEndValue.setText(null);
                maternalStartField.setEnabled(false);
                maternalEndField.setEnabled(false);
            }
        });
        preSchoolCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                preSchoolStartField.setEnabled(true);
                preSchoolEndField.setEnabled(true);
            } else {
                preSchoolStartValue.setText(null);
                preSchoolEndValue.setText(null);
                preSchoolStartField.setEnabled(false);
                preSchoolEndField.setEnabled(false);
            }
        });
        elementaryMiddleCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                elementaryMiddleStartField.setEnabled(true);
                elementaryMiddleEndField.setEnabled(true);
            } else {
                elementaryMiddleStartValue.setText(null);
                elementaryMiddleEndValue.setText(null);
                elementaryMiddleStartField.setEnabled(false);
                elementaryMiddleEndField.setEnabled(false);
            }
        });
        highCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                highStartField.setEnabled(true);
                highEndField.setEnabled(true);
            } else {
                highStartValue.setText(null);
                highEndValue.setText(null);
                highStartField.setEnabled(false);
                highEndField.setEnabled(false);
            }
        });
        ejaCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                ejaStartField.setEnabled(true);
                ejaEndField.setEnabled(true);
            } else {
                ejaStartValue.setText(null);
                ejaEndValue.setText(null);
                ejaStartField.setEnabled(false);
                ejaEndField.setEnabled(false);
            }
        });
    }

    private SchoolRegisterTwo updateRegisterTwo(Bundle bundle) {

        String morningStart = null, morningEnd = null, afternoonStart = null, afternoonEnd = null, eveningStart = null, eveningEnd = null,
                workingHoursObs = null, maternalStart = null, maternalEnd = null, preSchoolStart = null, preSchoolEnd = null,
                elementaryMiddleStart = null, elementaryMiddleEnd = null, highStart = null, highEnd = null, ejaStart = null, ejaEnd = null, servicesObs = null;
        int hasMorning = 0, hasAfternoon = 0, hasEvening = 0, hasNursery = 0, hasDaycare = 0, hasMaternal = 0, hasPreSchool = 0,
                hasElementaryMiddle = 0, hasHigh = 0, hasEja = 0;

        if (morningCheck.isChecked()) {
            hasMorning = 1;
            morningStart = String.valueOf(morningStartValue.getText());
            morningEnd = String.valueOf(morningEndValue.getText());
        }
        if (afternoonCheck.isChecked()) {
            hasAfternoon = 1;
            afternoonStart = String.valueOf(afternoonStartValue.getText());
            afternoonEnd = String.valueOf(afternoonEndValue.getText());
        }
        if (eveningCheck.isChecked()) {
            hasEvening = 1;
            eveningStart = String.valueOf(eveningStartValue.getText());
            eveningEnd = String.valueOf(eveningEndValue.getText());
        }
        if (!TextUtils.isEmpty(workHoursObsValue.getText()))
            workingHoursObs = String.valueOf(workHoursObsValue.getText());
        if(nurseryCheck.isChecked())
            hasNursery = 1;
        if (daycareCheck.isChecked())
            hasDaycare = 1;
        if (maternalCheck.isChecked()) {
            hasMaternal = 1;
            maternalStart = String.valueOf(maternalStartValue.getText());
            maternalEnd = String.valueOf(maternalEndValue.getText());
        }
        if (preSchoolCheck.isChecked()) {
            hasPreSchool = 1;
            preSchoolStart = String.valueOf(preSchoolStartValue.getText());
            preSchoolEnd = String.valueOf(preSchoolEndValue.getText());
        }
        if (elementaryMiddleCheck.isChecked()) {
            hasElementaryMiddle = 1;
            elementaryMiddleStart = String.valueOf(elementaryMiddleStartValue.getText());
            elementaryMiddleEnd = String.valueOf(elementaryMiddleEndValue.getText());
        }
        if (highCheck.isChecked()) {
            hasHigh = 1;
            highStart = String.valueOf(highStartValue.getText());
            highEnd = String.valueOf(highEndValue.getText());
        }
        if (ejaCheck.isChecked()) {
            hasEja = 1;
            ejaStart = String.valueOf(ejaStartValue.getText());
            ejaEnd = String.valueOf(ejaEndValue.getText());
        }
        if (!TextUtils.isEmpty(servicesObsValue.getText()))
            servicesObs = String.valueOf(servicesObsValue.getText());

        return new SchoolRegisterTwo(bundle.getInt(SCHOOL_ID), hasMorning, morningStart, morningEnd,
                hasAfternoon, afternoonStart, afternoonEnd, hasEvening, eveningStart, eveningEnd, workingHoursObs, hasNursery,
                hasDaycare, hasMaternal, maternalStart, maternalEnd, hasPreSchool, preSchoolStart, preSchoolEnd, hasElementaryMiddle,
                elementaryMiddleStart, elementaryMiddleEnd, hasHigh, highStart, highEnd, hasEja, ejaStart, ejaEnd, servicesObs);
    }
}