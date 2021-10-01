package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
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
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.activities.NewSchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class SchoolRegisterFragmentOne extends Fragment {

    TextInputLayout schoolNameField, addressStreetField, addressComplementField, addressNumberField, addressNeighborhoodField,
            addressCityField, principalNameField, contactPhoneOneField, contactPhoneTwoField, responsibleField, inspectionTeamField;
    TextInputEditText schoolNameValue, addressStreetValue, addressComplementValue, addressNumberValue, addressNeighborhoodValue,
            addressCityValue, principalNameValue, contactPhoneOneValue, contactPhoneTwoValue, responsibleValue, inspectionTeamValue;

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    Bundle schoolRegOne = new Bundle();

    public SchoolRegisterFragmentOne() {
        // Required empty public constructor
    }

    public static SchoolRegisterFragmentOne newInstance(Bundle bundle) {
        SchoolRegisterFragmentOne fragmentOne = new SchoolRegisterFragmentOne();
        fragmentOne.fillDataFields(bundle);
        return fragmentOne;
    }

    public void fillDataFields(Bundle bundle) {
        if (bundle != null) {
            schoolRegOne.putInt(MainActivity.UPDATE_REQUEST, bundle.getInt(MainActivity.UPDATE_REQUEST));
        }

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

        if (schoolRegOne.getInt(MainActivity.UPDATE_REQUEST) != 0)
            modelEntry.getEntry(schoolRegOne.getInt(MainActivity.UPDATE_REQUEST)).observe(getViewLifecycleOwner(), this::gatherSchoolDataOne);

        modelFragments.getSaveUpdateSchoolReg().observe(getViewLifecycleOwner(), saveUpdate -> {
            if (regOneHasNoEmptyFields() && saveUpdate != null) {
                if (saveUpdate.getBoolean(NewSchoolRegisterActivity.SAVE_CLOSE)) {
                    SchoolEntry school = newEntry();
                    ViewModelEntry.insertSchool(school);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.SAVE_CLOSE, false);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.CLOSE_FRAGMENT, true);
                    modelFragments.setDataFromFragToActivity(schoolRegOne);
                } else if (saveUpdate.getBoolean(NewSchoolRegisterActivity.SAVE_CONTINUE)) {
                    SchoolEntry school = newEntry();
                    ViewModelEntry.insertSchool(school);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.SAVE_CONTINUE, false);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.OPEN_FRAG_TWO, true);
                    modelFragments.setDataFromFragToActivity(schoolRegOne);
                } else if (saveUpdate.getBoolean(NewSchoolRegisterActivity.UPDATE_CLOSE)) {
                    SchoolRegisterOne school = updateRegisterOne(schoolRegOne);
                    ViewModelEntry.updateSchoolRegOne(school);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.UPDATE_CLOSE, false);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.CLOSE_FRAGMENT, true);
                    modelFragments.setDataFromFragToActivity(schoolRegOne);
                } else if (saveUpdate.getBoolean(NewSchoolRegisterActivity.UPDATE_CONTINUE)) {
                    SchoolRegisterOne school = updateRegisterOne(schoolRegOne);
                    ViewModelEntry.updateSchoolRegOne(school);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.UPDATE_CONTINUE, false);
                    schoolRegOne.putBoolean(NewSchoolRegisterActivity.OPEN_FRAG_TWO, true);
                    modelFragments.setDataFromFragToActivity(schoolRegOne);
                }
            }
        });
    }

    private void instantiateSchoolFragmentOne(View view) {
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

        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
    }

    private void gatherSchoolDataOne(SchoolEntry school) {
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
        String schoolName = String.valueOf(schoolNameValue.getText());
        String addressStreet = String.valueOf(addressStreetValue.getText());
        String addressComplement = String.valueOf(addressComplementValue.getText());
        String addressNumber = String.valueOf(addressNumberValue.getText());
        String addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        String addressCity = String.valueOf(addressCityValue.getText());
        String principalName = String.valueOf(principalNameValue.getText());
        String contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        String contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        String responsible = String.valueOf(responsibleValue.getText());
        String inspectionTeam = String.valueOf(inspectionTeamValue.getText());

        return new SchoolEntry(schoolName, addressStreet, addressComplement, addressNumber, addressNeighborhood, addressCity, principalName,
                contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null);
    }

    private SchoolRegisterOne updateRegisterOne(Bundle bundle) {
        String schoolName = String.valueOf(schoolNameValue.getText());
        String addressStreet = String.valueOf(addressStreetValue.getText());
        String addressComplement = String.valueOf(addressComplementValue.getText());
        String addressNumber = String.valueOf(addressNumberValue.getText());
        String addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        String addressCity = String.valueOf(addressCityValue.getText());
        String principalName = String.valueOf(principalNameValue.getText());
        String contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        String contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        String responsible = String.valueOf(responsibleValue.getText());
        String inspectionTeam = String.valueOf(inspectionTeamValue.getText());

        return new SchoolRegisterOne(bundle.getInt(MainActivity.UPDATE_REQUEST), schoolName, addressStreet, addressComplement, addressNumber,
                addressNeighborhood, addressCity, principalName, contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam);
    }


}