package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

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
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.ArrayList;
import java.util.Objects;


public class SillSlopeFragment extends Fragment {

    public static final String SLOPE_INCLINATION = "SLOPE_INCLINATION";
    public static final String SLOPE_WIDTH = "SLOPE_WIDTH";

    TextInputLayout sillSlopeAngleField, sillSlopeWidthField;
    TextInputEditText sillSlopeAngleValue, sillSlopeWidthValue;

    ViewModelDialog modelDialog;

    ViewModelEntry modelEntry;

    ViewModelFragments modelFragments;

    Bundle sillSlopeBundle = new Bundle();
    Bundle extAccessDataInfo = new Bundle();

    ArrayList<String> childData = new ArrayList<>();

    public SillSlopeFragment() {
        // Required empty public constructor
    }


    public static SillSlopeFragment newInstance() {
        return new SillSlopeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_slope, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSillSlopeViews(view);

        modelDialog.getRestDoorBundle().observe(getViewLifecycleOwner(), this::gatherSlopeDataDialog);

        modelDialog.getSaveDoorAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelDialog.getSaveDoorAttempt().getValue(), 1)) {
                if (doesNotHaveEmptySlopeFields()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(SLOPE_INCLINATION, Double.parseDouble(Objects.requireNonNull(sillSlopeAngleValue.getText()).toString()));
                    bundle.putDouble(SLOPE_WIDTH, Double.parseDouble(Objects.requireNonNull(sillSlopeWidthValue.getText()).toString()));
                    modelDialog.setDoorInfo(bundle);
                    clearSlopeFields();
                }
                modelDialog.setSaveDoorAttempt(0);
            }
        });

        if (extAccessDataInfo != null && extAccessDataInfo.getInt(ExternalAccessFragment.EXT_ACCESS_ID) != 0) {
            modelEntry.getOneExternalAccess(extAccessDataInfo.getInt(ExternalAccessFragment.EXT_ACCESS_ID))
                    .observe(getViewLifecycleOwner(), this::gatherSlopeData);
        }

        getParentFragmentManager().setFragmentResultListener(ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT, this, (key, bundle) -> {
            childData = bundle.getStringArrayList(ExternalAccessFragment.EXT_ARRAY);
            if (doesNotHaveEmptySlopeFields())
                childData.set(18, null);
            else
                childData.set(18, "false");
            sillSlopeBundle.putStringArrayList(ExternalAccessFragment.EXT_ARRAY, childData);
            getParentFragmentManager().setFragmentResult(ExternalAccessFragment.FRAG_DATA, sillSlopeBundle);
        });

        getParentFragmentManager().setFragmentResultListener(ExtAccessSocialFragment.TEMP_SOCIAL_FRAG, this, (key,bundle) -> {
            ArrayList<String> tempData = bundle.getStringArrayList(ExtAccessSocialFragment.TEMP_FRAG_DATA);
            gatherTempData(tempData);
            bundle.putStringArrayList(ExtAccessSocialFragment.TEMP_FRAG_DATA, tempData);
            getParentFragmentManager().setFragmentResult(ExtAccessSocialFragment.CHILD_TEMP_DATA, bundle);
        });
    }

    private void instantiateSillSlopeViews (View view) {
//        TextInputLayout
        sillSlopeAngleField = view.findViewById(R.id.sill_slope_field);
        sillSlopeWidthField = view.findViewById(R.id.sill_slope_width_field);
//        TextInputEditText
        sillSlopeAngleValue = view.findViewById(R.id.sill_slope_value);
        sillSlopeWidthValue = view.findViewById(R.id.sill_slope_width_value);
        //        TODO - Retirar esse model dialog quando remover o DoorDialog
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        //        Bundle
        extAccessDataInfo = modelFragments.getExtAccessLoadInfo().getValue();
    }

    private boolean doesNotHaveEmptySlopeFields() {
        clearEmptyFieldErrors();
        int error = 0;
        if (TextUtils.isEmpty(sillSlopeAngleValue.getText())) {
            sillSlopeAngleField.setError(getString(R.string.blank_field_error));
            error++;
        } else
            childData.set(10, String.valueOf(sillSlopeAngleValue.getText()));
        if (TextUtils.isEmpty(sillSlopeWidthValue.getText())) {
            sillSlopeWidthField.setError(getString(R.string.blank_field_error));
            error++;
        } else
            childData.set(11, String.valueOf(sillSlopeWidthValue.getText()));
        return error == 0;
    }

    private void gatherTempData(ArrayList<String> arrayList) {
        if (!TextUtils.isEmpty(sillSlopeAngleValue.getText()))
            arrayList.set(10, String.valueOf(sillSlopeAngleValue.getText()));
        if (!TextUtils.isEmpty(sillSlopeWidthValue.getText()))
            arrayList.set(11, String.valueOf(sillSlopeWidthValue.getText()));
    }

    private void clearEmptyFieldErrors() {
        sillSlopeAngleField.setErrorEnabled(false);
        sillSlopeWidthField.setErrorEnabled(false);
    }

    private void clearSlopeFields() {
        sillSlopeWidthValue.setText(null);
        sillSlopeAngleValue.setText(null);
    }

    //    TODO - Retirar esse método de carregamento de dados assim que tirar o Dialog
    private void gatherSlopeDataDialog(Bundle bundle) {
        sillSlopeWidthValue.setText(String.valueOf(bundle.getDouble(SLOPE_WIDTH)));
        sillSlopeAngleValue.setText(String.valueOf(bundle.getDouble(SLOPE_INCLINATION)));
    }

    private void gatherSlopeData(ExternalAccess access) {
        sillSlopeWidthValue.setText(String.valueOf(access.getSillSlopeWidth()));
        sillSlopeAngleValue.setText(String.valueOf(access.getSillSlopeAngle()));
    }
}