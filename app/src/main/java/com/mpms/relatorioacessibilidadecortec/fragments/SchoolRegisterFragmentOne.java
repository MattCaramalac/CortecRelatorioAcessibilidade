package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class SchoolRegisterFragmentOne extends Fragment {

    TextInputLayout schoolNameField, addressStreetField, addressComplementField, addressNumberField, addressNeighborhoodField,
            addressCityField, principalNameField, contactPhoneOneField, contactPhoneTwoField, responsibleField, inspectionTeamField;
    TextInputEditText schoolNameValue, addressStreetValue, addressComplementValue, addressNumberValue, addressNeighborhoodValue,
            addressCityValue, principalNameValue, contactPhoneOneValue, contactPhoneTwoValue, responsibleValue, inspectionTeamValue;

    String schoolName, addressStreet, addressComplement, addressNumber, addressNeighborhood, addressCity, principalName,
            contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam;

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    static Bundle bundleFragOne = new Bundle();

    public SchoolRegisterFragmentOne() {
        // Required empty public constructor
    }

    public static SchoolRegisterFragmentOne newInstance(Bundle bundle) {
        SchoolRegisterFragmentOne fragmentOne = new SchoolRegisterFragmentOne();
        fragmentOne.setArguments(bundle);
        SchoolRegisterActivity.provideSchoolID(bundle, bundleFragOne);
        return fragmentOne;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_register_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSchoolFragmentOne(view);

        if (bundleFragOne.getInt(SchoolRegisterActivity.SCHOOL_ID) > 0)
            modelEntry.getEntry(bundleFragOne.getInt(SchoolRegisterActivity.SCHOOL_ID)).observe(getViewLifecycleOwner(), this::gatherSchoolDataFragOne);
        else {
            modelEntry.getLastEntry().observe(getViewLifecycleOwner(), lastEntry -> {
                if (bundleFragOne.getBoolean(SchoolRegisterActivity.OPEN_FRAG_TWO)) {
                    bundleFragOne.putInt(SchoolRegisterActivity.SCHOOL_ID, lastEntry.getCadID());
                    modelFragments.setDataFromFragToActivity(bundleFragOne);
                    bundleFragOne.putBoolean(SchoolRegisterActivity.OPEN_FRAG_TWO, false);
                }
            });
        }

        modelFragments.getSaveUpdateSchoolReg().observe(getViewLifecycleOwner(), saveUpdate -> {
            if (regOneHasNoEmptyFields() && saveUpdate != null) {
                regOneDataHandling(saveUpdate);
            }
        });
    }

    private void instantiateSchoolFragmentOne(View view) {
//        TextInputLayout
        schoolNameField = view.findViewById(R.id.school_name_field);
        addressStreetField = view.findViewById(R.id.school_address_field);
        addressComplementField = view.findViewById(R.id.address_complement_field);
        addressNumberField = view.findViewById(R.id.address_number_field);
        addressNeighborhoodField = view.findViewById(R.id.address_neighborhood_field);
        addressCityField = view.findViewById(R.id.address_city_field);
        principalNameField = view.findViewById(R.id.name_principal_field);
        contactPhoneOneField = view.findViewById(R.id.first_telephone_number_field);
        contactPhoneTwoField = view.findViewById(R.id.second_telephone_number_field);
        responsibleField = view.findViewById(R.id.name_responsible_field);
        inspectionTeamField = view.findViewById(R.id.name_team_components_field);
//        TextInputEditText
        schoolNameValue = view.findViewById(R.id.school_name_value);
        addressStreetValue = view.findViewById(R.id.school_address_value);
        addressComplementValue = view.findViewById(R.id.address_complement_value);
        addressNumberValue = view.findViewById(R.id.address_number_value);
        addressNeighborhoodValue = view.findViewById(R.id.address_neighborhood_value);
        addressCityValue = view.findViewById(R.id.address_city_value);
        principalNameValue = view.findViewById(R.id.name_principal_value);
        contactPhoneOneValue = view.findViewById(R.id.first_telephone_number_value);
        contactPhoneTwoValue = view.findViewById(R.id.second_telephone_number_value);
        responsibleValue = view.findViewById(R.id.name_responsible_value);
        inspectionTeamValue = view.findViewById(R.id.name_team_components_value);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        MaskWatcher
//        TODO - verificar possibilidade de usar classe de máscaras (para adicionar parênteses)
        contactPhoneOneValue.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
        contactPhoneTwoValue.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
    }

    private void regOneDataHandling(Bundle bundle) {
        if (bundle.getBoolean(SchoolRegisterActivity.SAVE_CLOSE)) {
            SchoolEntry school = newEntry();
            ViewModelEntry.insertSchool(school);
            bundleFragOne.putBoolean(SchoolRegisterActivity.SAVE_CLOSE, false);
            bundleFragOne.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, true);
            modelFragments.setDataFromFragToActivity(bundleFragOne);
            bundleFragOne.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, false);
        } else if (bundle.getBoolean(SchoolRegisterActivity.SAVE_CONTINUE)) {
            SchoolEntry school = newEntry();
            ViewModelEntry.insertSchool(school);
            bundleFragOne.putBoolean(SchoolRegisterActivity.SAVE_CONTINUE, false);
            bundleFragOne.putBoolean(SchoolRegisterActivity.OPEN_FRAG_TWO, true);
        } else if (bundle.getBoolean(SchoolRegisterActivity.UPDATE_CLOSE)) {
            SchoolRegisterOne school = updateRegisterOne(bundleFragOne);
            ViewModelEntry.updateSchoolRegOne(school);
            bundleFragOne.putBoolean(SchoolRegisterActivity.UPDATE_CLOSE, false);
            bundleFragOne.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, true);
            modelFragments.setDataFromFragToActivity(bundleFragOne);
            bundleFragOne.putBoolean(SchoolRegisterActivity.CLOSE_FRAGMENT, false);
        } else if (bundle.getBoolean(SchoolRegisterActivity.UPDATE_CONTINUE)) {
            SchoolRegisterOne school = updateRegisterOne(bundleFragOne);
            ViewModelEntry.updateSchoolRegOne(school);
            bundleFragOne.putBoolean(SchoolRegisterActivity.UPDATE_CONTINUE, false);
            bundleFragOne.putBoolean(SchoolRegisterActivity.OPEN_FRAG_TWO, true);
            modelFragments.setDataFromFragToActivity(bundleFragOne);
            bundleFragOne.putBoolean(SchoolRegisterActivity.OPEN_FRAG_TWO, false);
        }
    }

    private void gatherSchoolDataFragOne(SchoolEntry school) {
        schoolNameValue.setText(school.getSchoolAddress());
        addressStreetValue.setText(school.getSchoolAddress());
        addressComplementValue.setText(school.getAddressComplement());
        addressNumberValue.setText(school.getAddressNumber());
        addressNeighborhoodValue.setText(school.getAddressNeighborhood());
        addressCityValue.setText(school.getNameCity());
        principalNameValue.setText(school.getNameDirector());
        contactPhoneOneValue.setText(school.getContactPhone1());
        contactPhoneTwoValue.setText(school.getContactPhone2());
        responsibleValue.setText(school.getNameResponsibleVisit());
        inspectionTeamValue.setText(school.getNameInspectionTeam());
    }

    private boolean regOneHasNoEmptyFields() {
        clearRegOneEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(schoolNameValue.getText())) {
            i++;
            schoolNameField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(addressStreetValue.getText())) {
            i++;
            addressStreetField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(addressNumberValue.getText())) {
            i++;
            addressNumberField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(addressNeighborhoodValue.getText())) {
            i++;
            addressNeighborhoodField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(addressCityValue.getText())) {
            i++;
            addressCityField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(principalNameValue.getText())) {
            i++;
            principalNameField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(contactPhoneOneValue.getText())) {
            i++;
            contactPhoneOneField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(responsibleValue.getText())) {
            i++;
            responsibleField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(inspectionTeamValue.getText())) {
            i++;
            inspectionTeamField.setError(getString(R.string.blank_field_error));
        }
        return i == 0;
    }

    private void clearRegOneEmptyFieldsErrors() {
        schoolNameField.setErrorEnabled(false);
        addressStreetField.setErrorEnabled(false);
        addressNumberField.setErrorEnabled(false);
        addressNeighborhoodField.setErrorEnabled(false);
        addressCityField.setErrorEnabled(false);
        principalNameField.setErrorEnabled(false);
        contactPhoneOneField.setErrorEnabled(false);
        responsibleField.setErrorEnabled(false);
        inspectionTeamField.setErrorEnabled(false);
    }

    private SchoolEntry newEntry() {
        schoolName = String.valueOf(schoolNameValue.getText());
        addressStreet = String.valueOf(addressStreetValue.getText());
        addressComplement = String.valueOf(addressComplementValue.getText());
        addressNumber = String.valueOf(addressNumberValue.getText());
        addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        addressCity = String.valueOf(addressCityValue.getText());
        principalName = String.valueOf(principalNameValue.getText());
        contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        responsible = String.valueOf(responsibleValue.getText());
        inspectionTeam = String.valueOf(inspectionTeamValue.getText());

        return new SchoolEntry(schoolName, addressStreet, addressComplement, addressNumber, addressNeighborhood, addressCity, principalName,
                contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null);
    }

    private SchoolRegisterOne updateRegisterOne(Bundle bundle) {
        schoolName = String.valueOf(schoolNameValue.getText());
        addressStreet = String.valueOf(addressStreetValue.getText());
        addressComplement = String.valueOf(addressComplementValue.getText());
        addressNumber = String.valueOf(addressNumberValue.getText());
        addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        addressCity = String.valueOf(addressCityValue.getText());
        principalName = String.valueOf(principalNameValue.getText());
        contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        responsible = String.valueOf(responsibleValue.getText());
        inspectionTeam = String.valueOf(inspectionTeamValue.getText());

        return new SchoolRegisterOne(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID), schoolName, addressStreet, addressComplement, addressNumber,
                addressNeighborhood, addressCity, principalName, contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam);
    }


}