package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import org.apache.commons.validator.routines.EmailValidator;

public class SchoolRegisterFragmentOne extends Fragment {

    TextInputLayout schoolNameField, addressStreetField, addressComplementField, addressNumberField, addressNeighborhoodField,
            addressCityField, schoolDistrictField, contactPhoneOneField, contactPhoneTwoField, responsibleField, inspectionTeamField,
            emailField;
    TextInputEditText schoolNameValue, addressStreetValue, addressComplementValue, addressNumberValue, addressNeighborhoodValue,
            addressCityValue, schoolDistrictValue, contactPhoneOneValue, contactPhoneTwoValue, responsibleValue, inspectionTeamValue,
            emailValue;

    String schoolName, addressStreet, addressComplement, addressNumber, addressNeighborhood, addressCity, districtName,
            contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam, email;

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    EmailValidator validator = EmailValidator.getInstance();

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
            } else if (!regOneHasNoEmptyFields())
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
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
        schoolDistrictField = view.findViewById(R.id.school_district_field);
        contactPhoneOneField = view.findViewById(R.id.first_telephone_number_field);
        contactPhoneTwoField = view.findViewById(R.id.second_telephone_number_field);
        responsibleField = view.findViewById(R.id.name_responsible_field);
        inspectionTeamField = view.findViewById(R.id.name_team_components_field);
        emailField = view.findViewById(R.id.email_field);
//        TextInputEditText
        schoolNameValue = view.findViewById(R.id.school_name_value);
        addressStreetValue = view.findViewById(R.id.school_address_value);
        addressComplementValue = view.findViewById(R.id.address_complement_value);
        addressNumberValue = view.findViewById(R.id.address_number_value);
        addressNeighborhoodValue = view.findViewById(R.id.address_neighborhood_value);
        addressCityValue = view.findViewById(R.id.address_city_value);
        schoolDistrictValue = view.findViewById(R.id.school_district_value);
        contactPhoneOneValue = view.findViewById(R.id.first_telephone_number_value);
        contactPhoneTwoValue = view.findViewById(R.id.second_telephone_number_value);
        responsibleValue = view.findViewById(R.id.name_responsible_value);
        inspectionTeamValue = view.findViewById(R.id.name_team_components_value);
        emailValue = view.findViewById(R.id.email_value);
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
        schoolNameValue.setText(school.getSchoolName());
        addressStreetValue.setText(school.getSchoolAddress());
        addressComplementValue.setText(school.getAddressComplement());
        addressNumberValue.setText(school.getAddressNumber());
        addressNeighborhoodValue.setText(school.getAddressNeighborhood());
        addressCityValue.setText(school.getNameCity());
        schoolDistrictValue.setText(school.getNameDistrict());
        contactPhoneOneValue.setText(school.getContactPhone1());
        contactPhoneTwoValue.setText(school.getContactPhone2());
        responsibleValue.setText(school.getNameResponsibleVisit());
        inspectionTeamValue.setText(school.getNameInspectionTeam());
        emailValue.setText(school.getEmailAddress());
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
        if (TextUtils.isEmpty(emailValue.getText())) {
            i++;
            emailField.setError(getString(R.string.blank_field_error));
        } else {
            String email = String.valueOf(emailValue.getText());
            if (!validator.isValid(email)) {
                i++;
                emailField.setError("Endereço de e-mail inválido");
            }
        }
        return i == 0;
    }

    private void clearRegOneEmptyFieldsErrors() {
        schoolNameField.setErrorEnabled(false);
        addressStreetField.setErrorEnabled(false);
        addressNumberField.setErrorEnabled(false);
        addressNeighborhoodField.setErrorEnabled(false);
        addressCityField.setErrorEnabled(false);
        schoolDistrictField.setErrorEnabled(false);
        contactPhoneOneField.setErrorEnabled(false);
        responsibleField.setErrorEnabled(false);
        inspectionTeamField.setErrorEnabled(false);
        emailField.setErrorEnabled(false);
    }

    private SchoolEntry newEntry() {
        schoolName = String.valueOf(schoolNameValue.getText());
        addressStreet = String.valueOf(addressStreetValue.getText());
        addressComplement = String.valueOf(addressComplementValue.getText());
        addressNumber = String.valueOf(addressNumberValue.getText());
        addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        addressCity = String.valueOf(addressCityValue.getText());
        districtName = String.valueOf(schoolDistrictValue.getText());
        contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        responsible = String.valueOf(responsibleValue.getText());
        inspectionTeam = String.valueOf(inspectionTeamValue.getText());
        email = String.valueOf(emailValue.getText());

        return new SchoolEntry(schoolName, addressStreet, addressComplement, addressNumber, addressNeighborhood, addressCity, districtName,
                contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, email);
    }

    private SchoolRegisterOne updateRegisterOne(Bundle bundle) {
        schoolName = String.valueOf(schoolNameValue.getText());
        addressStreet = String.valueOf(addressStreetValue.getText());
        addressComplement = String.valueOf(addressComplementValue.getText());
        addressNumber = String.valueOf(addressNumberValue.getText());
        addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        addressCity = String.valueOf(addressCityValue.getText());
        districtName = String.valueOf(schoolDistrictValue.getText());
        contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        responsible = String.valueOf(responsibleValue.getText());
        inspectionTeam = String.valueOf(inspectionTeamValue.getText());
        email = String.valueOf(emailValue.getText());


        return new SchoolRegisterOne(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID), schoolName, addressStreet, addressComplement, addressNumber,
                addressNeighborhood, addressCity, districtName, contactPhoneOne, contactPhoneTwo, responsible, inspectionTeam, email);
    }


}