package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEquipEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;


public class PoolEquipFragment extends Fragment implements ScrollEditText, TagInterface {

    TextInputLayout locationField, fieldA, fieldB, fieldC, fieldD, fieldE, photoField, obsField;
    TextInputEditText locationValue, valueA, valueB, valueC, valueD, valueE, photoValue, obsValue;
    MaterialButton saveEquip, returnList;
    ImageButton img1, img2;

    ViewModelEntry modelEntry;
    Bundle transfBundle, imgBundle;


    public PoolEquipFragment() {
        // Required empty public constructor
    }

    public static PoolEquipFragment newInstance() {
        return new PoolEquipFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            transfBundle = new Bundle(this.getArguments());
        else
            transfBundle = new Bundle();

        imgBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pool_transf_equip, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateView(view);

        if (transfBundle.getInt(PTRANSF_ID) > 0)
            modelEntry.getOnePoolEquip(transfBundle.getInt(PTRANSF_ID)).observe(getViewLifecycleOwner(), this::loadPoolEquipData);
    }

    private void instantiateView(View view) {
//        TextInputLayout
        locationField = view.findViewById(R.id.pool_transf_location_field);
        fieldA = view.findViewById(R.id.pool_transf_measure_A_field);
        fieldB = view.findViewById(R.id.pool_transf_measure_B_field);
        fieldC = view.findViewById(R.id.pool_transf_measure_C_field);
        fieldD = view.findViewById(R.id.pool_transf_measure_D_field);
        fieldE = view.findViewById(R.id.pool_transf_measure_E_field);
        photoField = view.findViewById(R.id.pool_transf_obs_field);
        obsField = view.findViewById(R.id.pool_transf_photo_field);
//        TextInputEditText
        locationValue = view.findViewById(R.id.pool_transf_location_value);
        valueA = view.findViewById(R.id.pool_transf_measure_A_value);
        valueB = view.findViewById(R.id.pool_transf_measure_B_value);
        valueC = view.findViewById(R.id.pool_transf_measure_C_value);
        valueD = view.findViewById(R.id.pool_transf_measure_D_value);
        valueE = view.findViewById(R.id.pool_transf_measure_E_value);
        photoValue = view.findViewById(R.id.pool_transf_obs_value);
        obsValue = view.findViewById(R.id.pool_transf_photo_value);
//        MaterialButton
        saveEquip = view.findViewById(R.id.save_pool_transf);
        returnList = view.findViewById(R.id.return_transf_list);
        saveEquip.setOnClickListener(this::buttonClickListener);
        returnList.setOnClickListener(this::buttonClickListener);
//        ImageButton
        img1 = view.findViewById(R.id.pool_transf_img_1);
        img2 = view.findViewById(R.id.pool_transf_img_2);
        Glide.with(this).load(R.drawable.transfequip1).centerCrop().into(img1);
        Glide.with(this).load(R.drawable.transfequip2).centerCrop().into(img2);
        img1.setOnClickListener(this::imgExpandClick);
        img2.setOnClickListener(this::imgExpandClick);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void loadPoolEquipData(PoolEquipEntry equip) {
        if (equip.getTransfLocation() != null)
            locationValue.setText(equip.getTransfLocation());
        if (equip.getTransfMeasureA() != null)
            valueA.setText(String.valueOf(equip.getTransfMeasureA()));
        if (equip.getTransfMeasureB() != null)
            valueB.setText(String.valueOf(equip.getTransfMeasureB()));
        if (equip.getTransfMeasureC() != null)
            valueC.setText(String.valueOf(equip.getTransfMeasureC()));
        if (equip.getTransfMeasureD() != null)
            valueD.setText(String.valueOf(equip.getTransfMeasureD()));
        if (equip.getTransfMeasureE() != null)
            valueE.setText(String.valueOf(equip.getTransfMeasureE()));
        if (equip.getTransfObs() != null)
            obsValue.setText(equip.getTransfObs());
        if (equip.getTransfPhoto() != null)
            photoValue.setText(equip.getTransfPhoto());
    }

    private void imgExpandClick(View view) {
        if (view == img1)
            imgBundle.putInt(IMAGE_ID, R.drawable.transfequip1);
        else if (view == img2)
            imgBundle.putInt(IMAGE_ID, R.drawable.transfequip2);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private void buttonClickListener(View view) {
        if (view == saveEquip && checkEmptyFields()) {
            PoolEquipEntry equip = newEquip(transfBundle);
            if (transfBundle.getInt(PTRANSF_ID) > 0) {
                equip.setPoolEquipID(transfBundle.getInt(PTRANSF_ID));
                modelEntry.updatePoolEquip(equip);
                Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            } else if (transfBundle.getInt(PTRANSF_ID) == 0) {
                modelEntry.insertPoolEquip(equip);
                Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                clearRegisterScreen();
            } else {
                transfBundle.putInt(PTRANSF_ID, 0);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            }
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private boolean checkEmptyFields() {
        clearErrorMessages();
        int i = 0;
        if (TextUtils.isEmpty(locationValue.getText())) {
            i++;
            locationField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueA.getText())) {
            i++;
            fieldA.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueB.getText())) {
            i++;
            fieldB.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueC.getText())) {
            i++;
            fieldC.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueD.getText())) {
            i++;
            fieldD.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(valueE.getText())) {
            i++;
            fieldE.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }

    private void clearErrorMessages() {
        locationField.setErrorEnabled(false);
        fieldA.setErrorEnabled(false);
        fieldB.setErrorEnabled(false);
        fieldC.setErrorEnabled(false);
        fieldD.setErrorEnabled(false);
        fieldE.setErrorEnabled(false);
    }

    private void clearRegisterScreen() {
        valueA.setText(null);
        valueB.setText(null);
        valueC.setText(null);
        valueD.setText(null);
        valueE.setText(null);
        obsValue.setText(null);
        photoValue.setText(null);
    }

    private PoolEquipEntry newEquip(Bundle bundle) {
        String local, obs = null, photo = null;
        double a, b, c, d, e;

        local = String.valueOf(locationValue.getText());
        a = Double.parseDouble(String.valueOf(valueA.getText()));
        b = Double.parseDouble(String.valueOf(valueB.getText()));
        c = Double.parseDouble(String.valueOf(valueC.getText()));
        d = Double.parseDouble(String.valueOf(valueD.getText()));
        e = Double.parseDouble(String.valueOf(valueE.getText()));

        if (!TextUtils.isEmpty(obsValue.getText()))
            obs = String.valueOf(obsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new PoolEquipEntry(bundle.getInt(POOL_ID), local, a, b, c, d, e, photo, obs);
    }
}