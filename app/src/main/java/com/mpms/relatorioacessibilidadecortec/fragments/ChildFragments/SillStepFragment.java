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
import com.mpms.relatorioacessibilidadecortec.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.PlaygroundFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.ArrayList;
import java.util.Objects;


public class SillStepFragment extends Fragment {

    public static final String STEP_HEIGHT = "STEP_HEIGHT";

    TextInputLayout stepHeightField;
    TextInputEditText stepHeightValue;

    ViewModelDialog modelDialog;

    ViewModelEntry modelEntry;

    ViewModelFragments modelFragments;

    ArrayList<String> childData = new ArrayList<>();

    Bundle sillStepBundle = new Bundle();
    Bundle extAccessData = new Bundle();
    Bundle playData = new Bundle();

    public SillStepFragment() {
        // Required empty public constructor
    }

    public static SillStepFragment newInstance() {
        return new SillStepFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSillStepViews(view);

        modelDialog.getRestDoorBundle().observe(getViewLifecycleOwner(), this::gatherStepDataDialog);

        modelDialog.getSaveDoorAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelDialog.getSaveDoorAttempt().getValue(), 1)) {
                if (checkEmptySillStepField()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(STEP_HEIGHT, Double.parseDouble(Objects.requireNonNull(stepHeightValue.getText()).toString()));
                    modelDialog.setDoorInfo(bundle);
                    clearStepField();
                }
                modelDialog.setSaveDoorAttempt(0);
            }
        });

        if (extAccessData != null && extAccessData.getInt(ExternalAccessFragment.EXT_ACCESS_ID) != 0) {
            modelEntry.getOneExternalAccess(extAccessData.getInt(ExternalAccessFragment.EXT_ACCESS_ID))
                    .observe(getViewLifecycleOwner(), this::gatherStepExtAccData);
        }

        if (playData != null && playData.getInt(PlaygroundFragment.PLAY_ID) != 0) {
            modelEntry.getOnePlayground(playData.getInt(PlaygroundFragment.PLAY_ID))
                    .observe(getViewLifecycleOwner(), this::gatherStepPlayData);
        }

        getParentFragmentManager().setFragmentResultListener(ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT, this, (key, bundle) -> {
            childData = bundle.getStringArrayList(ExternalAccessFragment.EXT_ARRAY);
            if (checkEmptySillStepField()) {
                childData.set(18, null);
            } else
                childData.set(18, "false");
            sillStepBundle.putStringArrayList(ExternalAccessFragment.EXT_ARRAY, childData);
            getParentFragmentManager().setFragmentResult(ExternalAccessFragment.FRAG_DATA, sillStepBundle);
        });

        getParentFragmentManager().setFragmentResultListener(ExtAccessSocialFragment.TEMP_SOCIAL_FRAG, this, (key, bundle) -> {
            ArrayList<String> tempData = bundle.getStringArrayList(ExtAccessSocialFragment.TEMP_FRAG_DATA);
            gatherTempData(tempData);
            bundle.putStringArrayList(ExtAccessSocialFragment.TEMP_FRAG_DATA, tempData);
            getParentFragmentManager().setFragmentResult(ExtAccessSocialFragment.CHILD_TEMP_DATA, bundle);
        });

        getParentFragmentManager().setFragmentResultListener(PlaygroundFragment.PLAY_SAVE_ATTEMPT, this, (key, bundle) -> {
            checkEmptyPlaySillStep(bundle);
            getParentFragmentManager().setFragmentResult(PlaygroundFragment.PLAY_SILL_DATA, bundle);
        });
    }

    private void instantiateSillStepViews(View view) {
//        TextInputLayout
        stepHeightField = view.findViewById(R.id.sill_step_height_field);
//        TextInputEditText
        stepHeightValue = view.findViewById(R.id.sill_step_height_value);
        //        TODO - Retirar esse model dialog quando remover o DoorDialog
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        //        Bundle
        extAccessData = modelFragments.getExtAccessLoadInfo().getValue();
        playData = modelFragments.getPlaygroundLoadInfo().getValue();
    }

    private boolean checkEmptySillStepField() {
        clearEmptyErrorStepField();
        int error = 0;
        if (TextUtils.isEmpty(stepHeightValue.getText())) {
            stepHeightField.setError(getString(R.string.blank_field_error));
            error++;
        } else
            childData.set(9, String.valueOf(stepHeightValue.getText()));

        return error == 0;
    }

    private void checkEmptyPlaySillStep(Bundle bundle) {
        clearEmptyErrorStepField();
        if (TextUtils.isEmpty(stepHeightValue.getText())) {
            stepHeightField.setError(getString(R.string.blank_field_error));
            bundle.putBoolean(PlaygroundFragment.ALLOW_PLAY_REGISTER, false);
        } else {
            bundle.putDouble(STEP_HEIGHT, Double.parseDouble(String.valueOf(stepHeightValue.getText())));
            bundle.putBoolean(PlaygroundFragment.ALLOW_PLAY_REGISTER, true);
        }
    }

    private void gatherTempData(ArrayList<String> arrayList) {
        if (!TextUtils.isEmpty(stepHeightValue.getText()))
            arrayList.set(9, String.valueOf(stepHeightValue.getText()));
    }

    private void clearEmptyErrorStepField() {
        stepHeightField.setErrorEnabled(false);
    }

    private void clearStepField() {
        stepHeightValue.setText(null);
    }

    private void gatherStepDataDialog(Bundle bundle) {
        stepHeightValue.setText(String.valueOf(bundle.getDouble(STEP_HEIGHT)));
    }

    private void gatherStepExtAccData(ExternalAccess access) {
        if (access.getSillStepHeight() != null)
            stepHeightValue.setText(String.valueOf(access.getSillStepHeight()));
    }

    private void gatherStepPlayData(PlaygroundEntry playEntry) {
        if (playEntry.getStepSillHeight() != null)
            stepHeightValue.setText(String.valueOf(playEntry.getStepSillHeight()));
    }
}