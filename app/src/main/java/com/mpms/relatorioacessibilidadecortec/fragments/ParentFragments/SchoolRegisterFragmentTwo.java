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
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterTwo;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.MaskWatcher;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class SchoolRegisterFragmentTwo extends Fragment implements ScrollEditText, TagInterface {

    TextInputLayout morningStartField, morningEndField, afternoonStartField, afternoonEndField, eveningStartField, eveningEndField, workHoursObsField,
            preStartField, preEndField, elementaryStartField, elementaryEndField, middleStartField, middleEndField, highStartField, highEndField, ejaStartField,
            ejaEndField, servicesObsField;
    TextInputEditText morningStartValue, morningEndValue, afternoonStartValue, afternoonEndValue, eveningStartValue, eveningEndValue, workHoursObsValue,
            preStartValue, preEndValue, elementaryStartValue, elementaryEndValue, middleStartValue, middleEndValue, highStartValue, highEndValue, ejaStartValue,
            ejaEndValue, servicesObsValue;
    MaterialCheckBox morningCheck, afternoonCheck, eveningCheck, preSchoolCheck, elementaryCheck, middleCheck, highCheck, ejaCheck;
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
        bundleFragTwo.putInt(SCHOOL_ID, bundle.getInt(SCHOOL_ID));
        bundleFragTwo.putBoolean(DATA_COMPLETE, bundle.getBoolean(DATA_COMPLETE));
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

        if (bundleFragTwo.getInt(SCHOOL_ID) > 0)
            modelEntry.getEntry(bundleFragTwo.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), this::loadSchoolDataTwo);

        modelFragments.getSaveUpdateSchoolReg().observe(getViewLifecycleOwner(), clickButton -> {
            if (clickButton != null) {
                if (checkEmptyFieldsFragTwo(bundleFragTwo)) {
                    SchoolRegisterTwo updateFragTwo = updateRegisterTwo(bundleFragTwo);
                    ViewModelEntry.updateSchoolRegTwo(updateFragTwo);
                    if (clickButton.getBoolean(UPDATE_CONTINUE)) {
                        bundleFragTwo.putBoolean(OPEN_FRAG_THREE, true);
                        modelFragments.setDataFromFragToActivity(bundleFragTwo);
                        bundleFragTwo.putBoolean(OPEN_FRAG_THREE, false);
                    } else if (clickButton.getBoolean(UPDATE_CLOSE)) {
                        bundleFragTwo.putBoolean(UPDATE_CLOSE, false);
                        bundleFragTwo.putBoolean(CLOSE_FRAGMENT, true);
                        modelFragments.setDataFromFragToActivity(bundleFragTwo);
                        bundleFragTwo.putBoolean(CLOSE_FRAGMENT, false);
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
        preStartField = v.findViewById(R.id.child_education_first_level_field);
        preEndField = v.findViewById(R.id.child_education_last_level_field);
        elementaryStartField = v.findViewById(R.id.elementary_first_level_field);
        elementaryEndField = v.findViewById(R.id.elementary_last_level_field);
        middleStartField = v.findViewById(R.id.middle_school_first_level_field);
        middleEndField = v.findViewById(R.id.middle_school_last_level_field);
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
        preStartValue = v.findViewById(R.id.child_education_first_level_value);
        preEndValue = v.findViewById(R.id.child_education_last_level_value);
        elementaryStartValue = v.findViewById(R.id.elementary_first_level_value);
        elementaryEndValue = v.findViewById(R.id.elementary_last_level_value);
        middleStartValue = v.findViewById(R.id.middle_school_first_level_value);
        middleEndValue = v.findViewById(R.id.middle_school_last_level_value);
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
        preSchoolCheck = v.findViewById(R.id.checkbox_child_education_classes);
        elementaryCheck = v.findViewById(R.id.checkbox_elementary_classes);
        middleCheck = v.findViewById(R.id.checkbox_middle_school_classes);
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
        checkBoxListener();
    }

    private boolean checkEmptyFieldsFragTwo(Bundle bundle) {
        clearEmptyFieldsErrorFragTwo();
        int i = 0;
        if (!morningCheck.isChecked() && !afternoonCheck.isChecked() && !eveningCheck.isChecked())
            bundle.putBoolean(DATA_COMPLETE, false);
        else {
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
        }

//        TODO - Check Funciona mas deixa impossível digitar o texto se colocar msg erro, refazer isso (como mudar a bendita cor do outline???????)
        if (!preSchoolCheck.isChecked() && !elementaryCheck.isChecked() && !middleCheck.isChecked() && !highCheck.isChecked() && !ejaCheck.isChecked())
            bundle.putBoolean(DATA_COMPLETE, false);
        else {
            if (elementaryCheck.isChecked()) {
                if (TextUtils.isEmpty(elementaryStartValue.getText())) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(elementaryEndValue.getText())) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                } else if (Integer.parseInt(String.valueOf(elementaryEndValue.getText())) > 5) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                }
            }

            if (middleCheck.isChecked()) {
                if (TextUtils.isEmpty(middleStartValue.getText())) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                } else if (Integer.parseInt(String.valueOf(middleStartValue.getText())) < 6) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(middleEndValue.getText())) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                } else if (Integer.parseInt(String.valueOf(middleEndValue.getText())) > 9) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                }
            }
            if (highCheck.isChecked()) {
                if (TextUtils.isEmpty(highStartValue.getText())) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                } else if (Integer.parseInt(String.valueOf(highStartValue.getText())) < 1) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(highEndValue.getText())) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                } else if (Integer.parseInt(String.valueOf(highEndValue.getText())) > 3) {
                    i++;
                    servicesError.setVisibility(View.VISIBLE);
                }
            }
            if (ejaCheck.isChecked() &&
                    (TextUtils.isEmpty(ejaStartValue.getText()) || TextUtils.isEmpty(ejaEndValue.getText()))) {
                i++;
                servicesError.setVisibility(View.VISIBLE);
            }
        }

        return i == 0;
    }

    private void clearEmptyFieldsErrorFragTwo() {
        workingHoursError.setVisibility(View.GONE);
        servicesError.setVisibility(View.GONE);
    }

    private void loadSchoolDataTwo(SchoolEntry school) {
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
        if (school.getWorkingHoursObs() != null)
            workHoursObsValue.setText(school.getWorkingHoursObs());

        if (school.getHasPreschool() != null && school.getHasPreschool() == 1) {
            preSchoolCheck.setChecked(true);
            preStartField.setEnabled(true);
            preEndField.setEnabled(true);
            preStartValue.setText(school.getPreschoolFirstGrade());
            preEndValue.setText(school.getPreschoolLastGrade());
        }
        if (school.getHasElementary() != null && school.getHasElementary() == 1) {
            elementaryCheck.setChecked(true);
            elementaryStartField.setEnabled(true);
            elementaryEndField.setEnabled(true);
            elementaryStartValue.setText(school.getElementaryFirstGrade());
            elementaryEndValue.setText(school.getElementaryLastGrade());
        }
        if (school.getHasMiddleSchool() != null && school.getHasMiddleSchool() == 1) {
            middleCheck.setChecked(true);
            middleStartField.setEnabled(true);
            middleEndField.setEnabled(true);
            middleStartValue.setText(school.getMiddleFirstGrade());
            middleEndValue.setText(school.getMiddleLastGrade());
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
        if (school.getServicesObs() != null)
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
        preSchoolCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                preStartField.setEnabled(true);
                preEndField.setEnabled(true);
            } else {
                preStartValue.setText(null);
                preEndValue.setText(null);
                preStartField.setEnabled(false);
                preEndField.setEnabled(false);
            }
        });
        elementaryCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                elementaryStartField.setEnabled(true);
                elementaryEndField.setEnabled(true);
            } else {
                elementaryStartValue.setText(null);
                elementaryEndValue.setText(null);
                elementaryStartField.setEnabled(false);
                elementaryEndField.setEnabled(false);
            }
        });
        middleCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                middleStartField.setEnabled(true);
                middleEndField.setEnabled(true);
            } else {
                middleStartValue.setText(null);
                middleEndValue.setText(null);
                middleStartField.setEnabled(false);
                middleEndField.setEnabled(false);
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
                workingHoursObs = null, preSchoolStart = null, preSchoolEnd = null, elementaryStart = null, elementaryEnd = null,
                middleStart = null, middleEnd = null, highStart = null, highEnd = null, ejaStart = null, ejaEnd = null, servicesObs = null;
        Integer hasMorning = null, hasAfternoon = null, hasEvening = null, hasPreSchool = null, hasElementary = null, hasMiddle = null, hasHigh = null, hasEja = null;

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
        if (preSchoolCheck.isChecked()) {
            hasPreSchool = 1;
            preSchoolStart = String.valueOf(preStartValue.getText());
            preSchoolEnd = String.valueOf(preEndValue.getText());
        }
        if (elementaryCheck.isChecked()) {
            hasElementary = 1;
            elementaryStart = String.valueOf(elementaryStartValue.getText());
            elementaryEnd = String.valueOf(elementaryEndValue.getText());
        }
        if (middleCheck.isChecked()) {
            hasMiddle = 1;
            middleStart = String.valueOf(middleStartValue.getText());
            middleEnd = String.valueOf(middleEndValue.getText());
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

        return new SchoolRegisterTwo(bundle.getInt(SCHOOL_ID), hasMorning, morningStart, morningEnd, hasAfternoon, afternoonStart, afternoonEnd,
                hasEvening, eveningStart, eveningEnd, workingHoursObs, hasPreSchool, preSchoolStart, preSchoolEnd, hasElementary, elementaryStart, elementaryEnd,
                hasMiddle, middleStart, middleEnd, hasHigh, highStart, highEnd, hasEja, ejaStart, ejaEnd, servicesObs);
    }
}