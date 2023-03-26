package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestUrinalUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;


public class RestUrinalFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    ImageButton frontUrinal, frontUrinal2, sideUrinal;
    RadioGroup hasUrinal, accessRadio, urinalType;
    TextView hasUrinalError, accessHeader, accessError, typeHeader, typeError;
    TextInputLayout fieldA, fieldB, fieldC, fieldD, fieldE, fieldF, fieldG, fieldH, fieldI, fieldJ, fieldK, fieldL, fieldM, urinalObsField, photoField;
    TextInputEditText valueA, valueB, valueC, valueD, valueE, valueF, valueG, valueH, valueI, valueJ, valueK, valueL, valueM, urinalObsValue, photoValue;
    MaterialButton saveUrinal, returnSink;

    ViewModelEntry modelEntry;
    Bundle uBundle, imgBundle;

    int uType = 0;

    public RestUrinalFragment() {
        // Required empty public constructor
    }

    public static RestUrinalFragment newInstance() {
        return new RestUrinalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            uBundle = new Bundle(this.getArguments());
        else
            uBundle = new Bundle();
        imgBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_urinal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateUrinalViews(view);

        modelEntry.getRestUrinalData(uBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadUrinalData);

    }

    private void instantiateUrinalViews(View view) {
//        ImageButton
        frontUrinal = view.findViewById(R.id.urinal_front_img);
        frontUrinal2 = view.findViewById(R.id.urinal_front_img2);
        sideUrinal = view.findViewById(R.id.urinal_side_img);
//        RadioGroup
        hasUrinal = view.findViewById(R.id.has_urinal_radio);
        accessRadio = view.findViewById(R.id.has_access_urinal_radio);
        urinalType = view.findViewById(R.id.urinal_type_radio);
//        TextView
        hasUrinalError = view.findViewById(R.id.has_urinal_error);
        accessHeader = view.findViewById(R.id.has_access_urinal_header);
        accessError = view.findViewById(R.id.has_access_urinal_error);
        typeHeader = view.findViewById(R.id.urinal_type_header);
        typeError = view.findViewById(R.id.urinal_type_error);
//        TextInputLayout
        fieldA = view.findViewById(R.id.urinal_measureA_field);
        fieldB = view.findViewById(R.id.urinal_measureB_field);
        fieldC = view.findViewById(R.id.urinal_measureC_field);
        fieldD = view.findViewById(R.id.urinal_measureD_field);
        fieldE = view.findViewById(R.id.urinal_measureE_field);
        fieldF = view.findViewById(R.id.urinal_measureF_field);
        fieldG = view.findViewById(R.id.urinal_measureG_field);
        fieldH = view.findViewById(R.id.urinal_measureH_field);
        fieldI = view.findViewById(R.id.urinal_measureI_field);
        fieldJ = view.findViewById(R.id.urinal_measureJ_field);
        fieldK = view.findViewById(R.id.urinal_measureK_field);
        fieldL = view.findViewById(R.id.urinal_measureL_field);
        fieldM = view.findViewById(R.id.urinal_measureM_field);
        urinalObsField = view.findViewById(R.id.urinal_obs_field);
        photoField = view.findViewById(R.id.urinal_photo_field);
//        TextInputEditText
        valueA = view.findViewById(R.id.urinal_measureA_value);
        valueB = view.findViewById(R.id.urinal_measureB_value);
        valueC = view.findViewById(R.id.urinal_measureC_value);
        valueD = view.findViewById(R.id.urinal_measureD_value);
        valueE = view.findViewById(R.id.urinal_measureE_value);
        valueF = view.findViewById(R.id.urinal_measureF_value);
        valueG = view.findViewById(R.id.urinal_measureG_value);
        valueH = view.findViewById(R.id.urinal_measureH_value);
        valueI = view.findViewById(R.id.urinal_measureI_value);
        valueJ = view.findViewById(R.id.urinal_measureJ_value);
        valueK = view.findViewById(R.id.urinal_measureK_value);
        valueL = view.findViewById(R.id.urinal_measureL_value);
        valueM = view.findViewById(R.id.urinal_measureM_value);
        urinalObsValue = view.findViewById(R.id.urinal_obs_value);
        photoValue = view.findViewById(R.id.urinal_photo_value);
//        MaterialButton
        saveUrinal= view.findViewById(R.id.save_urinal);
        returnSink= view.findViewById(R.id.return_sink);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        frontUrinal.setOnClickListener(this::imgExpandClick);
        frontUrinal2.setOnClickListener(this::imgExpandClick);
        sideUrinal.setOnClickListener(this::imgExpandClick);
        hasUrinal.setOnCheckedChangeListener(this::radioListener);
        accessRadio.setOnCheckedChangeListener(this::radioListener);
        urinalType.setOnCheckedChangeListener(this::radioListener);
        saveUrinal.setOnClickListener(this::saveClick);
        returnSink.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        allowObsScroll(urinalObsValue);
    }

    private void saveClick(View view) {
        if (checkEmptyFields()) {
            RestUrinalUpdate urData = urUpdate(uBundle);
            ViewModelEntry.updateRestUrinalData(urData);
            Toast.makeText(getContext(), getText(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(REST_LIST, 0);
        } else
            Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
    }

    private RestUrinalUpdate urUpdate (Bundle bundle) {
        int hasUr;
        Integer accessUr = null, urType = null;
        Double measA = null, measB = null, measC = null, measD = null, measE = null, measF = null, measG = null, measH = null, measI = null, measJ = null,
                measK = null, measL = null, measM = null;
        String urObs = null, photo = null;

        hasUr = indexRadio(hasUrinal);
        if (hasUr == 1) {
            accessUr = indexRadio(accessRadio);
            if (accessUr == 1) {
                urType = indexRadio(urinalType);
                measA = Double.parseDouble(String.valueOf(valueA.getText()));
                measB = Double.parseDouble(String.valueOf(valueB.getText()));
                measC = Double.parseDouble(String.valueOf(valueC.getText()));
                measD = Double.parseDouble(String.valueOf(valueD.getText()));
                measE = Double.parseDouble(String.valueOf(valueE.getText()));
                measF = Double.parseDouble(String.valueOf(valueF.getText()));
                measG = Double.parseDouble(String.valueOf(valueG.getText()));
                measH = Double.parseDouble(String.valueOf(valueH.getText()));
                measI = Double.parseDouble(String.valueOf(valueI.getText()));
                measJ = Double.parseDouble(String.valueOf(valueJ.getText()));
                measK = Double.parseDouble(String.valueOf(valueK.getText()));
                if (urType == 0) {
                    measL = Double.parseDouble(String.valueOf(valueL.getText()));
                    measM = Double.parseDouble(String.valueOf(valueM.getText()));
                }
            }
        }
        if (!TextUtils.isEmpty(urinalObsValue.getText()))
            urObs = String.valueOf(urinalObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new RestUrinalUpdate(bundle.getInt(REST_ID), hasUr, accessUr, urType, measA, measB, measC, measD, measE, measF, measG, measH, measI, measJ, measK,
                measL, measM, urObs, photo);

    }

    private void imgExpandClick(View view) {
        if (view == frontUrinal && uType == 0)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.suspurinal1);
        if (view == frontUrinal && uType == 1)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.floorurinal);
        else if (view == sideUrinal)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sideurinal);
        else if (view == frontUrinal2)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.suspurinal2);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    @Override
    public void radioListener(RadioGroup radio, int check) {
        int index = indexRadio(radio);

        if (radio == hasUrinal) {
            if (index == 1) {
                accessHeader.setVisibility(View.VISIBLE);
                accessRadio.setVisibility(View.VISIBLE);
            } else {
                valueA.setText(null);
                valueB.setText(null);
                valueC.setText(null);
                valueD.setText(null);
                valueE.setText(null);
                valueF.setText(null);
                valueG.setText(null);
                valueH.setText(null);
                valueI.setText(null);
                valueJ.setText(null);
                valueK.setText(null);
                valueL.setText(null);
                valueM.setText(null);
                accessRadio.clearCheck();
                urinalType.clearCheck();
                fieldA.setVisibility(View.GONE);
                fieldB.setVisibility(View.GONE);
                fieldC.setVisibility(View.GONE);
                fieldD.setVisibility(View.GONE);
                fieldE.setVisibility(View.GONE);
                fieldF.setVisibility(View.GONE);
                fieldG.setVisibility(View.GONE);
                fieldH.setVisibility(View.GONE);
                fieldI.setVisibility(View.GONE);
                fieldJ.setVisibility(View.GONE);
                fieldK.setVisibility(View.GONE);
                fieldL.setVisibility(View.GONE);
                fieldM.setVisibility(View.GONE);
                hasUrinalError.setVisibility(View.GONE);
                accessHeader.setVisibility(View.GONE);
                accessError.setVisibility(View.GONE);
                typeHeader.setVisibility(View.GONE);
                typeError.setVisibility(View.GONE);
                accessRadio.setVisibility(View.GONE);
                urinalType.setVisibility(View.GONE);
                frontUrinal.setVisibility(View.GONE);
                frontUrinal2.setVisibility(View.GONE);
                sideUrinal.setVisibility(View.GONE);
            }
        } else if (radio == accessRadio) {
            if (index == 1) {
                typeHeader.setVisibility(View.VISIBLE);
                urinalType.setVisibility(View.VISIBLE);
            } else {
                valueA.setText(null);
                valueB.setText(null);
                valueC.setText(null);
                valueD.setText(null);
                valueE.setText(null);
                valueF.setText(null);
                valueG.setText(null);
                valueH.setText(null);
                valueI.setText(null);
                valueJ.setText(null);
                valueK.setText(null);
                valueL.setText(null);
                valueM.setText(null);
                urinalType.clearCheck();
                fieldA.setVisibility(View.GONE);
                fieldB.setVisibility(View.GONE);
                fieldC.setVisibility(View.GONE);
                fieldD.setVisibility(View.GONE);
                fieldE.setVisibility(View.GONE);
                fieldF.setVisibility(View.GONE);
                fieldG.setVisibility(View.GONE);
                fieldH.setVisibility(View.GONE);
                fieldI.setVisibility(View.GONE);
                fieldJ.setVisibility(View.GONE);
                fieldK.setVisibility(View.GONE);
                fieldL.setVisibility(View.GONE);
                fieldM.setVisibility(View.GONE);
                typeHeader.setVisibility(View.GONE);
                typeError.setVisibility(View.GONE);
                urinalType.setVisibility(View.GONE);
                frontUrinal.setVisibility(View.GONE);
                frontUrinal2.setVisibility(View.GONE);
                sideUrinal.setVisibility(View.GONE);
            }
        } else if (radio == urinalType) {
            fieldA.setVisibility(View.VISIBLE);
            fieldB.setVisibility(View.VISIBLE);
            fieldC.setVisibility(View.VISIBLE);
            fieldD.setVisibility(View.VISIBLE);
            fieldE.setVisibility(View.VISIBLE);
            fieldF.setVisibility(View.VISIBLE);
            fieldG.setVisibility(View.VISIBLE);
            fieldH.setVisibility(View.VISIBLE);
            fieldI.setVisibility(View.VISIBLE);
            fieldJ.setVisibility(View.VISIBLE);
            fieldK.setVisibility(View.VISIBLE);
            frontUrinal.setVisibility(View.VISIBLE);
            sideUrinal.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.sideurinal).centerCrop().into(sideUrinal);
            if (index == 0) {
                uType = 0;
                fieldL.setVisibility(View.VISIBLE);
                fieldM.setVisibility(View.VISIBLE);
                frontUrinal2.setVisibility(View.VISIBLE);
                Glide.with(this).load(R.drawable.suspurinal1).centerCrop().into(frontUrinal);
                Glide.with(this).load(R.drawable.suspurinal2).centerCrop().into(frontUrinal2);
            } else if (index == 1) {
                uType = 1;
                valueL.setText(null);
                valueM.setText(null);
                fieldL.setVisibility(View.GONE);
                fieldM.setVisibility(View.GONE);
                frontUrinal2.setVisibility(View.GONE);
                Glide.with(this).load(R.drawable.floorurinal).centerCrop().into(frontUrinal);
            }
        }
    }

    private boolean checkEmptyFields() {
        clearEmptyFieldError();
        int i = 0;
        if (indexRadio(hasUrinal) == -1) {
            i++;
            hasUrinalError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasUrinal) == 1) {
            if (indexRadio(accessRadio) == -1) {
                i++;
                accessError.setVisibility(View.VISIBLE);
            } else if (indexRadio(accessRadio) == 1) {
                if (indexRadio(urinalType) == -1) {
                    i++;
                    typeError.setVisibility(View.VISIBLE);
                } else {
                    if (TextUtils.isEmpty(valueA.getText())) {
                        i++;
                        fieldA.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueB.getText())) {
                        i++;
                        fieldB.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueC.getText())) {
                        i++;
                        fieldC.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueD.getText())) {
                        i++;
                        fieldD.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueE.getText())) {
                        i++;
                        fieldE.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueF.getText())) {
                        i++;
                        fieldF.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueG.getText())) {
                        i++;
                        fieldG.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueH.getText())) {
                        i++;
                        fieldH.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueI.getText())) {
                        i++;
                        fieldI.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueJ.getText())) {
                        i++;
                        fieldJ.setError(getText(R.string.req_field_error));
                    }
                    if (TextUtils.isEmpty(valueK.getText())) {
                        i++;
                        fieldK.setError(getText(R.string.req_field_error));
                    }
                    if (indexRadio(urinalType) == 0) {
                        if (TextUtils.isEmpty(valueL.getText())) {
                            i++;
                            fieldL.setError(getText(R.string.req_field_error));
                        }
                        if (TextUtils.isEmpty(valueM.getText())) {
                            i++;
                            fieldM.setError(getText(R.string.req_field_error));
                        }
                    }
                }
            }
        }
        return i == 0;
    }

    private void clearEmptyFieldError() {
        hasUrinalError.setVisibility(View.GONE);
        accessError.setVisibility(View.GONE);
        typeError.setVisibility(View.GONE);
        fieldA.setErrorEnabled(false);
        fieldB.setErrorEnabled(false);
        fieldC.setErrorEnabled(false);
        fieldD.setErrorEnabled(false);
        fieldE.setErrorEnabled(false);
        fieldF.setErrorEnabled(false);
        fieldG.setErrorEnabled(false);
        fieldH.setErrorEnabled(false);
        fieldI.setErrorEnabled(false);
        fieldJ.setErrorEnabled(false);
        fieldK.setErrorEnabled(false);
        fieldL.setErrorEnabled(false);
        fieldM.setErrorEnabled(false);
    }

    private void loadUrinalData(RestroomEntry rest) {
        if (rest.getHasUrinal() != null) {
            checkRadioGroup(hasUrinal, rest.getHasUrinal());
            if (rest.getHasUrinal() == 1) {
                if (rest.getHasAccessUrinal() != null) {
                    checkRadioGroup(accessRadio, rest.getHasAccessUrinal());
                    if (rest.getHasAccessUrinal() == 1) {
                        if (rest.getUrinalType() != null)
                            urinalType.check(urinalType.getChildAt(rest.getUrinalType()).getId());
                        if (rest.getUrMeasureA() != null)
                            valueA.setText(String.valueOf(rest.getUrMeasureA()));
                        if (rest.getUrMeasureB() != null)
                            valueB.setText(String.valueOf(rest.getUrMeasureB()));
                        if (rest.getUrMeasureC() != null)
                            valueC.setText(String.valueOf(rest.getUrMeasureC()));
                        if (rest.getUrMeasureD() != null)
                            valueD.setText(String.valueOf(rest.getUrMeasureD()));
                        if (rest.getUrMeasureE() != null)
                            valueE.setText(String.valueOf(rest.getUrMeasureE()));
                        if (rest.getUrMeasureF() != null)
                            valueF.setText(String.valueOf(rest.getUrMeasureF()));
                        if (rest.getUrMeasureG() != null)
                            valueG.setText(String.valueOf(rest.getUrMeasureG()));
                        if (rest.getUrMeasureH() != null)
                            valueH.setText(String.valueOf(rest.getUrMeasureH()));
                        if (rest.getUrMeasureI() != null)
                            valueI.setText(String.valueOf(rest.getUrMeasureI()));
                        if (rest.getUrMeasureJ() != null)
                            valueJ.setText(String.valueOf(rest.getUrMeasureJ()));
                        if (rest.getUrMeasureK() != null)
                            valueK.setText(String.valueOf(rest.getUrMeasureK()));
                        if (rest.getUrMeasureL() != null)
                            valueL.setText(String.valueOf(rest.getUrMeasureL()));
                        if (rest.getUrMeasureM() != null)
                            valueM.setText(String.valueOf(rest.getUrMeasureM()));
                    }
                }
            }
        }

        if (rest.getUrObs() != null)
            urinalObsValue.setText(rest.getUrObs());
        if (rest.getRestUrinalPhoto() != null)
            photoValue.setText(rest.getRestUrinalPhoto());
    }

}