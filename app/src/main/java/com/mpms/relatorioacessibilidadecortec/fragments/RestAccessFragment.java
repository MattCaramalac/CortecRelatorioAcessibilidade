package com.mpms.relatorioacessibilidadecortec.fragments;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RestAccessFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout hangerHeightField, hangerObsField, objHoldHeightField, objHoldObsField, soapHoldHeightField, soapHoldObsField, towelHoldHeightField, towelHoldObsField;
    TextInputEditText hangerHeightValue, hangerObsValue, objHoldHeightValue, objHoldObsValue, soapHoldHeightValue, soapHoldObsValue, towelHoldHeightValue, towelHoldObsValue;
    RadioGroup hangRadio, objHoldRadio, objHoldStatRadio, soapRadio, towelRadio;
    TextView hangError, objHoldError, objHoldStatHeader, objHoldStatError, soapError, towelError;
    MaterialButton returnToilView, saveToilAccess;

    ViewModelEntry modelEntry;
    Bundle rAccessBundle;
    ArrayList<TextInputEditText> accessObsArray = new ArrayList<>();


    public RestAccessFragment() {
        // Required empty public constructor
    }

    public static RestAccessFragment newInstance() {
        return new RestAccessFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.getArguments() != null)
            rAccessBundle = new Bundle(this.getArguments());
        else
            rAccessBundle = new Bundle();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_acessories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instRestAccessViews(view);

        modelEntry.getRestAccessData(rAccessBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadAccessData);

        saveToilAccess.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                RestAccessUpdate access = accUpdate(rAccessBundle);
                ViewModelEntry.updateRestAccessData(access);
                callAccessTwoFragment(rAccessBundle);
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });


        returnToilView.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void loadAccessData (RestroomEntry rest) {
        if (rest.getHasHanger() != null)
            hangRadio.check(hangRadio.getChildAt(rest.getHasHanger()).getId());
        if (rest.getHangerHeight() != null)
            hangerHeightValue.setText(String.valueOf(rest.getHangerHeight()));
        if (rest.getHangerObs() != null)
            hangerObsValue.setText(rest.getHangerObs());

        if (rest.getHasObjHold() != null)
            objHoldRadio.check(objHoldRadio.getChildAt(rest.getHasObjHold()).getId());
        if (rest.getObjHoldCorrect() != null)
            objHoldStatRadio.check(objHoldStatRadio.getChildAt(rest.getObjHoldCorrect()).getId());
        if (rest.getObjHoldHeight() != null)
            objHoldHeightValue.setText(String.valueOf(rest.getObjHoldHeight()));
        if (rest.getObjHoldObs() != null)
            objHoldObsValue.setText(rest.getObjHoldObs());

        if (rest.getHasSoapHold() != null)
            soapRadio.check(soapRadio.getChildAt(rest.getHasSoapHold()).getId());
        if (rest.getSoapHoldHeight() != null)
            soapHoldHeightValue.setText(String.valueOf(rest.getSoapHoldHeight()));
        if (rest.getSoapHoldObs() != null)
            soapHoldObsValue.setText(rest.getSoapHoldObs());

        if (rest.getHasTowelHold() != null)
            towelRadio.check(towelRadio.getChildAt(rest.getHasSoapHold()).getId());
        if (rest.getTowelHoldHeight() != null)
            towelHoldHeightValue.setText(String.valueOf(rest.getTowelHoldHeight()));
        if (rest.getTowelHoldObs() != null)
            towelHoldObsValue.setText(rest.getTowelHoldObs());
    }

    private void callAccessTwoFragment(Bundle bundle) {
        RestAccessTwoFragment accessTwo = RestAccessTwoFragment.newInstance();
        accessTwo.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, accessTwo).addToBackStack(null).commit();
    }

    private void instRestAccessViews(View view) {
//        TextInputLayout
        hangerHeightField = view.findViewById(R.id.hanger_height_field);
        hangerObsField = view.findViewById(R.id.hanger_obs_field);
        objHoldHeightField = view.findViewById(R.id.obj_holder_height_field);
        objHoldObsField = view.findViewById(R.id.obj_holder_obs_field);
        soapHoldHeightField = view.findViewById(R.id.soap_height_field);
        soapHoldObsField = view.findViewById(R.id.soap_obs_field);
        towelHoldHeightField = view.findViewById(R.id.towel_height_field);
        towelHoldObsField = view.findViewById(R.id.towel_obs_field);

//        TextInputEditText
        hangerHeightValue = view.findViewById(R.id.hanger_height_value);
        hangerObsValue = view.findViewById(R.id.hanger_obs_value);
        objHoldHeightValue = view.findViewById(R.id.obj_holder_height_value);
        objHoldObsValue = view.findViewById(R.id.obj_holder_obs_value);
        soapHoldHeightValue = view.findViewById(R.id.soap_height_value);
        soapHoldObsValue = view.findViewById(R.id.soap_obs_value);
        towelHoldHeightValue = view.findViewById(R.id.towel_height_value);
        towelHoldObsValue = view.findViewById(R.id.towel_obs_value);

//        RadioGroup
        hangRadio = view.findViewById(R.id.hanger_radio);
        objHoldRadio = view.findViewById(R.id.obj_holder_radio);
        objHoldStatRadio = view.findViewById(R.id.obj_hold_status_radio);
        soapRadio = view.findViewById(R.id.soap_radio);
        towelRadio = view.findViewById(R.id.towel_radio);
//        TextView
        hangError = view.findViewById(R.id.hanger_error);
        objHoldError = view.findViewById(R.id.obj_holder_error);
        objHoldStatHeader = view.findViewById(R.id.obj_hold_status_header);
        objHoldStatError = view.findViewById(R.id.obj_hold_status_error);
        soapError = view.findViewById(R.id.soap_error);
        towelError = view.findViewById(R.id.towel_error);

//        MaterialButton
        returnToilView = view.findViewById(R.id.return_access_one);
        saveToilAccess = view.findViewById(R.id.save_t_access);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        hangRadio.setOnCheckedChangeListener(this::radioListener);
        objHoldRadio.setOnCheckedChangeListener(this::radioListener);
        soapRadio.setOnCheckedChangeListener(this::radioListener);
        towelRadio.setOnCheckedChangeListener(this::radioListener);

        addEditTextFields();
        allowObsScroll(accessObsArray);

    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void radioListener(RadioGroup radio, int check) {
        int index = getCheckedRadio(radio);
        if (radio == hangRadio) {
            if (index == 0) {
                hangerHeightValue.setText(null);
                hangerHeightField.setVisibility(View.GONE);
            } else
                hangerHeightField.setVisibility(View.VISIBLE);
        } else if (radio == objHoldRadio) {
            if (index == 0) {
                objHoldStatRadio.clearCheck();
                objHoldHeightValue.setText(null);
                objHoldStatHeader.setVisibility(View.GONE);
                objHoldStatRadio.setVisibility(View.GONE);
                objHoldStatError.setVisibility(View.GONE);
                objHoldHeightField.setVisibility(View.GONE);
            } else {
                objHoldStatHeader.setVisibility(View.VISIBLE);
                objHoldStatRadio.setVisibility(View.VISIBLE);
                objHoldHeightField.setVisibility(View.VISIBLE);
            }
        } else if (radio == towelRadio) {
            if (index == 1)
                towelHoldHeightField.setVisibility(View.VISIBLE);
            else {
                towelHoldHeightValue.setText(null);
                towelHoldHeightField.setVisibility(View.GONE);
            }
        } else if (radio == soapRadio) {
            if (index == 1)
                soapHoldHeightField.setVisibility(View.VISIBLE);
            else {
                soapHoldHeightValue.setText(null);
                soapHoldHeightField.setVisibility(View.GONE);
            }
        }
    }



    private boolean checkEmptyFields() {
        clearEmptyFieldErrors();
        int i = 0;
        if (getCheckedRadio(hangRadio) == -1) {
            hangError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadio(hangRadio) == 1) {
            if (TextUtils.isEmpty(hangerHeightValue.getText())) {
                i++;
                hangerHeightField.setError(getText(R.string.req_field_error));
            }
        }
        if (getCheckedRadio(objHoldRadio) == -1) {
            objHoldError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadio(objHoldRadio) == 1) {
            if (getCheckedRadio(objHoldStatRadio) == -1) {
                objHoldStatError.setVisibility(View.VISIBLE);
                i++;
            }
            if (TextUtils.isEmpty(objHoldHeightValue.getText())) {
                i++;
                objHoldHeightField.setError(getText(R.string.req_field_error));
            }
        }
        if (getCheckedRadio(soapRadio) == -1) {
            i++;
            soapError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(soapRadio) == 1) {
            if (TextUtils.isEmpty(soapHoldHeightValue.getText())) {
                i++;
                soapHoldHeightField.setError(getText(R.string.req_field_error));
            }
        }
        if (getCheckedRadio(towelRadio) == -1) {
            i++;
            towelError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(towelRadio) == 1) {
            if (TextUtils.isEmpty(towelHoldHeightValue.getText())) {
                i++;
                towelHoldHeightField.setError(getText(R.string.req_field_error));
            }
        }


        return i == 0;
    }

    private void clearEmptyFieldErrors() {
        hangError.setVisibility(View.GONE);
        objHoldError.setVisibility(View.GONE);
        objHoldStatError.setVisibility(View.GONE);
        soapError.setVisibility(View.GONE);
        towelError.setVisibility(View.GONE);;
        hangerHeightField.setErrorEnabled(false);
        objHoldHeightField.setErrorEnabled(false);
        soapHoldHeightField.setErrorEnabled(false);
        towelHoldHeightField.setErrorEnabled(false);

    }

    private RestAccessUpdate accUpdate(Bundle bundle) {
        int hasHanger, hasObjHold, hasSoapHold, hasTowelHold, hasWallMirror;
        Integer objHoldOK = null;
        Double hangerHeight = null, objHoldHeight = null, soapHoldHeight = null, towelHoldHeight = null, mirrorA = null, mirrorB = null;
        String hangerObs, objHoldObs, soapObs, towelObs, mirrorObs;

        hasHanger = getCheckedRadio(hangRadio);
        if (hasHanger == 1)
            hangerHeight = Double.parseDouble(String.valueOf(hangerHeightValue.getText()));
        hangerObs = String.valueOf(hangerObsValue.getText());

        hasObjHold = getCheckedRadio(objHoldRadio);
        if (hasObjHold == 1) {
            objHoldOK = getCheckedRadio(objHoldStatRadio);
            objHoldHeight = Double.parseDouble(String.valueOf(objHoldHeightValue.getText()));
        }
        objHoldObs = String.valueOf(objHoldObsValue.getText());

        hasSoapHold = getCheckedRadio(soapRadio);
        if (hasSoapHold == 1)
            soapHoldHeight = Double.parseDouble(String.valueOf(soapHoldHeightValue.getText()));
        soapObs = String.valueOf(soapHoldObsValue.getText());

        hasTowelHold = getCheckedRadio(towelRadio);
        if (hasTowelHold == 1)
            towelHoldHeight = Double.parseDouble(String.valueOf(towelHoldHeightValue.getText()));
        towelObs = String.valueOf(towelHoldObsValue.getText());

        return new RestAccessUpdate(bundle.getInt(REST_ID),hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldOK, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight,
                soapObs, hasTowelHold, towelHoldHeight, towelObs);
    }

    private void addEditTextFields() {
        accessObsArray.add(hangerObsValue);
        accessObsArray.add(objHoldObsValue);
        accessObsArray.add(soapHoldObsValue);
        accessObsArray.add(towelHoldObsValue);
    }

}