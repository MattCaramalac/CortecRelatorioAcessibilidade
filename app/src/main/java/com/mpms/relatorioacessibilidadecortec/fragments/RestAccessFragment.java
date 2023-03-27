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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxAccOneUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RestAccessFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout hangerHeightField, hangerObsField, objHoldHeightField, objHoldObsField, soapHoldHeightField, soapHoldObsField, towelHoldHeightField, towelHoldObsField,
            photoField;
    TextInputEditText hangerHeightValue, hangerObsValue, objHoldHeightValue, objHoldObsValue, soapHoldHeightValue, soapHoldObsValue, towelHoldHeightValue, towelHoldObsValue,
            photoValue;
    RadioGroup hangRadio, objHoldRadio, objHoldStatRadio, soapRadio, towelRadio;
    TextView hangError, objHoldError, objHoldStatHeader, objHoldStatError, soapError, towelError, accessHeader, hangHeader, objHeader, soapHeader, towelHeader;
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

        if (rAccessBundle.getInt(BOX_ID) > 0)
            modelEntry.getBoxAccessData(rAccessBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadRestBoxAccOne);
        else if (rAccessBundle.getInt(REST_ID) > 0)
            modelEntry.getRestAccessData(rAccessBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadAccessData);

        saveToilAccess.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                if (rAccessBundle.getBoolean(FROM_BOX)) {
                    RestBoxAccOneUpdate access = boxAccOneUpdate(rAccessBundle);
                    modelEntry.updateBoxAccOne(access);
                } else if (rAccessBundle.getInt(REST_ID) > 0) {
                    RestAccessUpdate access = accUpdate(rAccessBundle);
                    ViewModelEntry.updateRestAccessData(access);
                }
                callAccessTwoFragment(rAccessBundle);
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });


        returnToilView.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void loadAccessData(RestroomEntry rest) {
        if (rest.getHasHanger() != null)
            checkRadioGroup(hangRadio, rest.getHasHanger());
        if (rest.getHangerHeight() != null)
            hangerHeightValue.setText(String.valueOf(rest.getHangerHeight()));
        if (rest.getHangerObs() != null)
            hangerObsValue.setText(rest.getHangerObs());

        if (rest.getHasObjHold() != null)
            checkRadioGroup(objHoldRadio, rest.getHasObjHold());
        if (rest.getObjHoldCorrect() != null)
            checkRadioGroup(objHoldStatRadio, rest.getObjHoldCorrect());
        if (rest.getObjHoldHeight() != null)
            objHoldHeightValue.setText(String.valueOf(rest.getObjHoldHeight()));
        if (rest.getObjHoldObs() != null)
            objHoldObsValue.setText(rest.getObjHoldObs());

        if (rest.getHasSoapHold() != null)
            checkRadioGroup(soapRadio, rest.getHasSoapHold());
        if (rest.getSoapHoldHeight() != null)
            soapHoldHeightValue.setText(String.valueOf(rest.getSoapHoldHeight()));
        if (rest.getSoapHoldObs() != null)
            soapHoldObsValue.setText(rest.getSoapHoldObs());

        if (rest.getHasTowelHold() != null)
            checkRadioGroup(towelRadio, rest.getHasTowelHold());
        if (rest.getTowelHoldHeight() != null)
            towelHoldHeightValue.setText(String.valueOf(rest.getTowelHoldHeight()));
        if (rest.getTowelHoldObs() != null)
            towelHoldObsValue.setText(rest.getTowelHoldObs());
        if (rest.getRestFirstPhoto() != null)
            photoValue.setText(rest.getRestFirstPhoto());
    }

    private void loadRestBoxAccOne(RestBoxEntry rest) {
        if (rest.getHasHanger() != null)
            checkRadioGroup(hangRadio, rest.getHasHanger());
        if (rest.getHangerHeight() != null)
            hangerHeightValue.setText(String.valueOf(rest.getHangerHeight()));
        if (rest.getHangerObs() != null)
            hangerObsValue.setText(rest.getHangerObs());

        if (rest.getHasObjHold() != null)
            checkRadioGroup(objHoldRadio, rest.getHasObjHold());
        if (rest.getObjHoldCorrect() != null)
            checkRadioGroup(objHoldStatRadio, rest.getObjHoldCorrect());
        if (rest.getObjHoldHeight() != null)
            objHoldHeightValue.setText(String.valueOf(rest.getObjHoldHeight()));
        if (rest.getObjHoldObs() != null)
            objHoldObsValue.setText(rest.getObjHoldObs());

        if (rest.getHasSoapHold() != null)
            checkRadioGroup(soapRadio, rest.getHasSoapHold());
        if (rest.getSoapHoldHeight() != null)
            soapHoldHeightValue.setText(String.valueOf(rest.getSoapHoldHeight()));
        if (rest.getSoapHoldObs() != null)
            soapHoldObsValue.setText(rest.getSoapHoldObs());

        if (rest.getHasTowelHold() != null)
            checkRadioGroup(towelRadio, rest.getHasTowelHold());
        if (rest.getTowelHoldHeight() != null)
            towelHoldHeightValue.setText(String.valueOf(rest.getTowelHoldHeight()));
        if (rest.getTowelHoldObs() != null)
            towelHoldObsValue.setText(rest.getTowelHoldObs());
        if (rest.getBoxUpperPhoto() != null)
            photoValue.setText(rest.getBoxUpperPhoto());
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
        photoField = view.findViewById(R.id.access_one_photo_field);
//        TextInputEditText
        hangerHeightValue = view.findViewById(R.id.hanger_height_value);
        hangerObsValue = view.findViewById(R.id.hanger_obs_value);
        objHoldHeightValue = view.findViewById(R.id.obj_holder_height_value);
        objHoldObsValue = view.findViewById(R.id.obj_holder_obs_value);
        soapHoldHeightValue = view.findViewById(R.id.soap_height_value);
        soapHoldObsValue = view.findViewById(R.id.soap_obs_value);
        towelHoldHeightValue = view.findViewById(R.id.towel_height_value);
        towelHoldObsValue = view.findViewById(R.id.towel_obs_value);
        photoValue = view.findViewById(R.id.access_one_photo_value);
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
        accessHeader = view.findViewById(R.id.access_info_header);
        hangHeader = view.findViewById(R.id.hanger_header);
        objHeader = view.findViewById(R.id.obj_holder_header);
        soapHeader = view.findViewById(R.id.soap_holder_header);
        towelHeader = view.findViewById(R.id.towel_holder_header);

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

        if (rAccessBundle.getBoolean(FROM_BOX)) {
            accessHeader.setText(R.string.header_box_accessories);
            hangHeader.setText(R.string.header_box_hanger);
            objHeader.setText(R.string.header_box_obj_holder);
            soapHeader.setText(R.string.header_box_soap_holder);
            towelHeader.setText(R.string.header_box_towel_holder);
        }

    }

    @Override
    public void radioListener(RadioGroup radio, int check) {
        int index = indexRadio(radio);
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
        if (indexRadio(hangRadio) == -1) {
            hangError.setVisibility(View.VISIBLE);
            i++;
        } else if (indexRadio(hangRadio) == 1) {
            if (TextUtils.isEmpty(hangerHeightValue.getText())) {
                i++;
                hangerHeightField.setError(getText(R.string.req_field_error));
            }
        }
        if (indexRadio(objHoldRadio) == -1) {
            objHoldError.setVisibility(View.VISIBLE);
            i++;
        } else if (indexRadio(objHoldRadio) == 1) {
            if (indexRadio(objHoldStatRadio) == -1) {
                objHoldStatError.setVisibility(View.VISIBLE);
                i++;
            }
            if (TextUtils.isEmpty(objHoldHeightValue.getText())) {
                i++;
                objHoldHeightField.setError(getText(R.string.req_field_error));
            }
        }
        if (indexRadio(soapRadio) == -1) {
            i++;
            soapError.setVisibility(View.VISIBLE);
        } else if (indexRadio(soapRadio) == 1) {
            if (TextUtils.isEmpty(soapHoldHeightValue.getText())) {
                i++;
                soapHoldHeightField.setError(getText(R.string.req_field_error));
            }
        }
        if (indexRadio(towelRadio) == -1) {
            i++;
            towelError.setVisibility(View.VISIBLE);
        } else if (indexRadio(towelRadio) == 1) {
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
        towelError.setVisibility(View.GONE);
        hangerHeightField.setErrorEnabled(false);
        objHoldHeightField.setErrorEnabled(false);
        soapHoldHeightField.setErrorEnabled(false);
        towelHoldHeightField.setErrorEnabled(false);

    }

    private RestAccessUpdate accUpdate(Bundle bundle) {
        int hasHanger, hasObjHold, hasSoapHold, hasTowelHold;
        Integer objHoldOK = null;
        Double hangerHeight = null, objHoldHeight = null, soapHoldHeight = null, towelHoldHeight = null;
        String hangerObs = null, objHoldObs = null, soapObs = null, towelObs = null, photo = null;

        hasHanger = indexRadio(hangRadio);
        if (hasHanger == 1)
            hangerHeight = Double.parseDouble(String.valueOf(hangerHeightValue.getText()));
        if (!TextUtils.isEmpty(hangerObsValue.getText()))
            hangerObs = String.valueOf(hangerObsValue.getText());

        hasObjHold = indexRadio(objHoldRadio);
        if (hasObjHold == 1) {
            objHoldOK = indexRadio(objHoldStatRadio);
            objHoldHeight = Double.parseDouble(String.valueOf(objHoldHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(objHoldObsValue.getText()))
            objHoldObs = String.valueOf(objHoldObsValue.getText());

        hasSoapHold = indexRadio(soapRadio);
        if (hasSoapHold == 1)
            soapHoldHeight = Double.parseDouble(String.valueOf(soapHoldHeightValue.getText()));
        if (!TextUtils.isEmpty(soapHoldObsValue.getText()))
            soapObs = String.valueOf(soapHoldObsValue.getText());

        hasTowelHold = indexRadio(towelRadio);
        if (hasTowelHold == 1)
            towelHoldHeight = Double.parseDouble(String.valueOf(towelHoldHeightValue.getText()));
        if (!TextUtils.isEmpty(towelHoldObsValue.getText()))
            towelObs = String.valueOf(towelHoldObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new RestAccessUpdate(bundle.getInt(REST_ID), hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldOK, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight,
                soapObs, hasTowelHold, towelHoldHeight, towelObs, photo);
    }

    private RestBoxAccOneUpdate boxAccOneUpdate(Bundle bundle) {
        int hasHanger, hasObjHold, hasSoapHold, hasTowelHold;
        Integer objHoldOK = null;
        Double hangerHeight = null, objHoldHeight = null, soapHoldHeight = null, towelHoldHeight = null;
        String hangerObs = null, objHoldObs = null, soapObs = null, towelObs = null, photo = null;

        hasHanger = indexRadio(hangRadio);
        if (hasHanger == 1)
            hangerHeight = Double.parseDouble(String.valueOf(hangerHeightValue.getText()));
        if (!TextUtils.isEmpty(hangerObsValue.getText()))
            hangerObs = String.valueOf(hangerObsValue.getText());

        hasObjHold = indexRadio(objHoldRadio);
        if (hasObjHold == 1) {
            objHoldOK = indexRadio(objHoldStatRadio);
            objHoldHeight = Double.parseDouble(String.valueOf(objHoldHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(objHoldObsValue.getText()))
            objHoldObs = String.valueOf(objHoldObsValue.getText());

        hasSoapHold = indexRadio(soapRadio);
        if (hasSoapHold == 1)
            soapHoldHeight = Double.parseDouble(String.valueOf(soapHoldHeightValue.getText()));
        if (!TextUtils.isEmpty(soapHoldObsValue.getText()))
            soapObs = String.valueOf(soapHoldObsValue.getText());

        hasTowelHold = indexRadio(towelRadio);
        if (hasTowelHold == 1)
            towelHoldHeight = Double.parseDouble(String.valueOf(towelHoldHeightValue.getText()));
        if (!TextUtils.isEmpty(towelHoldObsValue.getText()))
            towelObs = String.valueOf(towelHoldObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new RestBoxAccOneUpdate(bundle.getInt(BOX_ID), hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldOK, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight,
                soapObs, hasTowelHold, towelHoldHeight, towelObs, photo);
    }

    private void addEditTextFields() {
        accessObsArray.add(hangerObsValue);
        accessObsArray.add(objHoldObsValue);
        accessObsArray.add(soapHoldObsValue);
        accessObsArray.add(towelHoldObsValue);
    }

}