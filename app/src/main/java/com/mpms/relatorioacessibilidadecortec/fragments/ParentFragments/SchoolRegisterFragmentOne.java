package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.apache.commons.validator.routines.EmailValidator;

public class SchoolRegisterFragmentOne extends Fragment implements ScrollEditText, TagInterface {

    TextInputLayout schoolNameField, addressStreetField, addressComplementField, addressNumberField, addressNeighborhoodField,
            addressCityField, schoolDistrictField, contactPhoneOneField, contactPhoneTwoField, contactNameOneField, contactNameTwoField,
            respNameField1, respNameField2, respJobField1, respJobField2, inspectionTeamField, emailField;
    TextInputEditText schoolNameValue, addressStreetValue, addressComplementValue, addressNumberValue, addressNeighborhoodValue,
            addressCityValue, schoolDistrictValue, contactPhoneOneValue, contactPhoneTwoValue, contactNameOneValue, contactNameTwoValue,
            respNameValue1, respNameValue2, respJobValue1, respJobValue2, inspectionTeamValue, emailValue;

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
        bundleFragOne = bundle;
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

        if (bundleFragOne.getInt(SCHOOL_ID) > 0)
            modelEntry.getEntry(bundleFragOne.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), this::loadSchoolDataOne);
        else {
            modelEntry.getLastEntry().observe(getViewLifecycleOwner(), lastEntry -> {
                if (bundleFragOne.getBoolean(OPEN_FRAG_TWO)) {
                    bundleFragOne.putInt(SCHOOL_ID, lastEntry.getCadID());
                    modelFragments.setDataFromFragToActivity(bundleFragOne);
                    bundleFragOne.putBoolean(OPEN_FRAG_TWO, false);
                }
            });
        }

        modelFragments.getSaveUpdateSchoolReg().observe(getViewLifecycleOwner(), saveUpdate -> {
            if (regOneHasNoEmptyFields(bundleFragOne) && saveUpdate != null) {
                regOneDataHandling(saveUpdate);
            } else if (!regOneHasNoEmptyFields(bundleFragOne))
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
        respNameField1 = view.findViewById(R.id.first_responsible_field);
        respJobField1 = view.findViewById(R.id.first_job_description_field);
        respNameField2 = view.findViewById(R.id.second_responsible_field);
        respJobField2 = view.findViewById(R.id.second_job_description_field);
        inspectionTeamField = view.findViewById(R.id.name_team_components_field);
        contactNameOneField = view.findViewById(R.id.first_telephone_name_field);
        contactNameTwoField = view.findViewById(R.id.second_telephone_name_field);
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
        contactNameOneValue = view.findViewById(R.id.first_telephone_name_value);
        contactNameTwoValue = view.findViewById(R.id.second_telephone_name_value);
        respNameValue1 = view.findViewById(R.id.first_responsible_value);
        respJobValue1 = view.findViewById(R.id.first_job_description_value);
        respNameValue2 = view.findViewById(R.id.second_responsible_value);
        respJobValue2 = view.findViewById(R.id.second_job_description_value);
        inspectionTeamValue = view.findViewById(R.id.name_team_components_value);
        emailValue = view.findViewById(R.id.email_value);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        MaskWatcher
//        TODO - verificar possibilidade de usar classe de máscaras (para adicionar parênteses)
        contactPhoneOneValue.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));
        contactPhoneTwoValue.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));

        allowObsScroll(inspectionTeamValue);

        bundleFragOne.putBoolean(DATA_COMPLETE, true);
    }

    private void regOneDataHandling(Bundle bundle) {
        if (bundle.getBoolean(SAVE_CLOSE)) {
            SchoolEntry school = newEntry();
            ViewModelEntry.insertSchool(school);
            bundleFragOne.putBoolean(SAVE_CLOSE, false);
            bundleFragOne.putBoolean(CLOSE_FRAGMENT, true);
            modelFragments.setDataFromFragToActivity(bundleFragOne);
            bundleFragOne.putBoolean(CLOSE_FRAGMENT, false);
        } else if (bundle.getBoolean(SAVE_CONTINUE)) {
            SchoolEntry school = newEntry();
            ViewModelEntry.insertSchool(school);
            bundleFragOne.putBoolean(SAVE_CONTINUE, false);
            bundleFragOne.putBoolean(OPEN_FRAG_TWO, true);
        } else if (bundle.getBoolean(UPDATE_CLOSE)) {
            SchoolRegisterOne school = updateRegisterOne(bundleFragOne);
            ViewModelEntry.updateSchoolRegOne(school);
            bundleFragOne.putBoolean(UPDATE_CLOSE, false);
            bundleFragOne.putBoolean(CLOSE_FRAGMENT, true);
            modelFragments.setDataFromFragToActivity(bundleFragOne);
            bundleFragOne.putBoolean(CLOSE_FRAGMENT, false);
        } else if (bundle.getBoolean(UPDATE_CONTINUE)) {
            SchoolRegisterOne school = updateRegisterOne(bundleFragOne);
            ViewModelEntry.updateSchoolRegOne(school);
            bundleFragOne.putBoolean(UPDATE_CONTINUE, false);
            bundleFragOne.putBoolean(OPEN_FRAG_TWO, true);
            modelFragments.setDataFromFragToActivity(bundleFragOne);
            bundleFragOne.putBoolean(OPEN_FRAG_TWO, false);
        }
    }

    private void loadSchoolDataOne(SchoolEntry school) {
        if (school.getSchoolName() != null)
            schoolNameValue.setText(school.getSchoolName());
        if (school.getSchoolAddress() != null)
            addressStreetValue.setText(school.getSchoolAddress());
        if (school.getAddressComplement() != null)
            addressComplementValue.setText(school.getAddressComplement());
        if (school.getAddressNumber() != null)
            addressNumberValue.setText(school.getAddressNumber());
        if (school.getAddressNeighborhood() != null)
            addressNeighborhoodValue.setText(school.getAddressNeighborhood());
        if (school.getNameCity() != null)
            addressCityValue.setText(school.getNameCity());
        if (school.getNameDistrict() != null)
            schoolDistrictValue.setText(school.getNameDistrict());
        if (school.getContactPhone1() != null)
            contactPhoneOneValue.setText(school.getContactPhone1());
        if (school.getContactName1() != null)
            contactNameOneValue.setText(school.getContactName1());
        if (school.getContactPhone2() != null)
            contactPhoneTwoValue.setText(school.getContactPhone2());
        if (school.getContactName1() != null)
            contactNameTwoValue.setText(school.getContactName2());
        if (school.getRespName1() != null)
            respNameValue1.setText(school.getRespName1());
        if (school.getRespJob1() != null)
            respJobValue1.setText(school.getRespJob1());
        if (school.getRespName2() != null)
            respNameValue2.setText(school.getRespName2());
        if (school.getRespJob2() != null)
            respJobValue2.setText(school.getRespJob2());
        if (school.getNameInspectionTeam() != null)
            inspectionTeamValue.setText(school.getNameInspectionTeam());
        if (school.getEmailAddress() != null)
            emailValue.setText(school.getEmailAddress());
    }

    private boolean regOneHasNoEmptyFields(Bundle bundle) {
        clearRegOneEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(schoolNameValue.getText())) {
            i++;
            schoolNameField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(addressStreetValue.getText())) {
            i++;
            addressStreetField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(addressNumberValue.getText())) {
            i++;
            addressNumberField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(addressNeighborhoodValue.getText())) {
            i++;
            addressNeighborhoodField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(addressCityValue.getText())) {
            i++;
            addressCityField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(contactPhoneOneValue.getText()))
            bundle.putBoolean(DATA_COMPLETE, false);
        else if (!Patterns.PHONE.matcher(String.valueOf(contactPhoneOneValue.getText())).matches()) {
            i++;
            contactPhoneOneField.setError("Telefone Inválido");
        }
        else if (TextUtils.isEmpty(contactNameOneValue.getText())) {
            i++;
            contactNameOneField.setError(getString(R.string.req_field_error));
        }
        if (!TextUtils.isEmpty(contactPhoneTwoValue.getText()) && !Patterns.PHONE.matcher(String.valueOf(contactPhoneTwoValue.getText())).matches()) {
            i++;
            contactPhoneTwoField.setError("Telefone Inválido");
        }
        if (!TextUtils.isEmpty(contactPhoneTwoValue.getText()) && TextUtils.isEmpty(contactNameTwoValue.getText())) {
            i++;
            contactNameTwoField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(respNameValue1.getText()))
            bundle.putBoolean(DATA_COMPLETE, false);
        else if (TextUtils.isEmpty(respJobValue1.getText())) {
            i++;
            respJobField1.setError(getString(R.string.req_field_error));
        }
        if (!TextUtils.isEmpty(respNameValue2.getText()) && TextUtils.isEmpty(respJobValue2.getText())) {
            i++;
            respJobField2.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(inspectionTeamValue.getText())) {
            i++;
            inspectionTeamField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(emailValue.getText())) {
            i++;
            emailField.setError(getString(R.string.req_field_error));
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
        addressComplementField.setErrorEnabled(false);
        addressNumberField.setErrorEnabled(false);
        addressNeighborhoodField.setErrorEnabled(false);
        addressCityField.setErrorEnabled(false);
        schoolDistrictField.setErrorEnabled(false);
        contactPhoneOneField.setErrorEnabled(false);
        contactPhoneTwoField.setErrorEnabled(false);
        contactNameOneField.setErrorEnabled(false);
        contactNameTwoField.setErrorEnabled(false);
        respNameField1.setErrorEnabled(false);
        respNameField2.setErrorEnabled(false);
        respJobField1.setErrorEnabled(false);
        respJobField2.setErrorEnabled(false);
        inspectionTeamField.setErrorEnabled(false);
        emailField.setErrorEnabled(false);
    }

    private SchoolEntry newEntry() {
        String schoolName, addressStreet, addressComplement = null, addressNumber, addressNeighborhood, addressCity, districtName = null,
                contactPhoneOne = null, contactPhoneTwo = null, respName1 = null, respName2 = null, respJob1 = null, respJob2 = null,
                inspectionTeam, email, contactNameOne = null, contactNameTwo = null;

        schoolName = String.valueOf(schoolNameValue.getText());
        addressStreet = String.valueOf(addressStreetValue.getText());
        if (!TextUtils.isEmpty(addressComplementValue.getText()))
            addressComplement = String.valueOf(addressComplementValue.getText());
        addressNumber = String.valueOf(addressNumberValue.getText());
        addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        addressCity = String.valueOf(addressCityValue.getText());
        if (!TextUtils.isEmpty(schoolDistrictValue.getText()))
            districtName = String.valueOf(schoolDistrictValue.getText());
        if (!TextUtils.isEmpty(contactPhoneOneValue.getText()))
            contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        if (!TextUtils.isEmpty(contactNameOneValue.getText()))
            contactNameOne = String.valueOf(contactNameOneValue.getText());
        if (!TextUtils.isEmpty(contactPhoneTwoValue.getText()))
            contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        if (!TextUtils.isEmpty(contactNameTwoValue.getText()))
            contactNameTwo = String.valueOf(contactNameTwoValue.getText());

        if (!TextUtils.isEmpty(respNameValue1.getText()))
            respName1 = String.valueOf(respNameValue1.getText());
        if (!TextUtils.isEmpty(respJobValue1.getText()))
            respJob1 = String.valueOf(respJobValue1.getText());
        if (!TextUtils.isEmpty(respNameValue2.getText()))
            respName2 = String.valueOf(respNameValue2.getText());
        if (!TextUtils.isEmpty(respJobValue2.getText()))
            respJob2 = String.valueOf(respJobValue2.getText());

        inspectionTeam = String.valueOf(inspectionTeamValue.getText());
        email = String.valueOf(emailValue.getText());

        return new SchoolEntry(schoolName, addressStreet, addressComplement, addressNumber, addressNeighborhood, addressCity, districtName, contactPhoneOne, contactNameOne,
                contactPhoneTwo, contactNameTwo, respName1, respJob1, respName2, respJob2, inspectionTeam, email, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, 0, 0);
    }

    private SchoolRegisterOne updateRegisterOne(Bundle bundle) {
        String schoolName, addressStreet, addressComplement = null, addressNumber, addressNeighborhood, addressCity, districtName = null,
                contactPhoneOne = null, contactPhoneTwo = null, respName1 = null, respName2 = null, respJob1 = null, respJob2 = null,
                inspectionTeam, email, contactNameOne = null, contactNameTwo = null;

        schoolName = String.valueOf(schoolNameValue.getText());
        addressStreet = String.valueOf(addressStreetValue.getText());
        if (!TextUtils.isEmpty(addressComplementValue.getText()))
            addressComplement = String.valueOf(addressComplementValue.getText());
        addressNumber = String.valueOf(addressNumberValue.getText());
        addressNeighborhood = String.valueOf(addressNeighborhoodValue.getText());
        addressCity = String.valueOf(addressCityValue.getText());
        if (!TextUtils.isEmpty(schoolDistrictValue.getText()))
            districtName = String.valueOf(schoolDistrictValue.getText());
        if (!TextUtils.isEmpty(contactPhoneOneValue.getText()))
            contactPhoneOne = String.valueOf(contactPhoneOneValue.getText());
        if (!TextUtils.isEmpty(contactNameOneValue.getText()))
            contactNameOne = String.valueOf(contactNameOneValue.getText());
        if (!TextUtils.isEmpty(contactPhoneTwoValue.getText()))
            contactPhoneTwo = String.valueOf(contactPhoneTwoValue.getText());
        if (!TextUtils.isEmpty(contactNameTwoValue.getText()))
            contactNameTwo = String.valueOf(contactNameTwoValue.getText());

        if (!TextUtils.isEmpty(respNameValue1.getText()))
            respName1 = String.valueOf(respNameValue1.getText());
        if (!TextUtils.isEmpty(respJobValue1.getText()))
            respJob1 = String.valueOf(respJobValue1.getText());
        if (!TextUtils.isEmpty(respNameValue2.getText()))
            respName2 = String.valueOf(respNameValue2.getText());
        if (!TextUtils.isEmpty(respJobValue2.getText()))
            respJob2 = String.valueOf(respJobValue2.getText());

        inspectionTeam = String.valueOf(inspectionTeamValue.getText());
        email = String.valueOf(emailValue.getText());


        return new SchoolRegisterOne(bundle.getInt(SCHOOL_ID), schoolName, addressStreet, addressComplement, addressNumber, addressNeighborhood, addressCity, districtName,
                contactPhoneOne, contactNameOne, contactPhoneTwo, contactNameTwo, respName1, respJob1, respName2, respJob2, inspectionTeam, email);
    }


}