package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.BoxBarParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;


public class RestBoxRightBarFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout aField, bField, cField, dField, diamField, distField, obsField;
    TextInputEditText aValue, bValue, cValue, dValue, diamValue, distValue, obsValue;
    ImageButton imgButton;
    ViewModelEntry modelEntry;

    static int boxID;

    Bundle imgBundle = new Bundle();

    public RestBoxRightBarFragment() {
        // Required empty public constructor
    }

    public static RestBoxRightBarFragment newInstance(int box) {
        boxID = box;
        return new RestBoxRightBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_box_l_shape_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instLBarViews(view);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_3, this, (key, bundle) -> {
            if (checkEmptyFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                createLShapeParcel(bundle);
            } else {
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                bundle.putParcelable(CHILD_PARCEL, null);
            }
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_3, bundle);
        });


        if (boxID > 0)
            modelEntry.getCommonBoxData(boxID).observe(getViewLifecycleOwner(), this::loadLBarData);
    }

    private void instLBarViews(View v) {
//        TextInputLayout
        aField = v.findViewById(R.id.box_bar_measure_a_field);
        bField = v.findViewById(R.id.box_bar_measure_b_field);
        cField = v.findViewById(R.id.box_bar_measure_c_field);
        dField = v.findViewById(R.id.box_bar_measure_d_field);
        diamField = v.findViewById(R.id.box_bar_diam_field);
        distField = v.findViewById(R.id.box_bar_dist_field);
        obsField = v.findViewById(R.id.box_bar_obs_field);
//        TextInputEditText
        aValue = v.findViewById(R.id.box_bar_measure_a_value);
        bValue = v.findViewById(R.id.box_bar_measure_b_value);
        cValue = v.findViewById(R.id.box_bar_measure_c_value);
        dValue = v.findViewById(R.id.box_bar_measure_d_value);
        diamValue = v.findViewById(R.id.box_bar_diam_value);
        distValue = v.findViewById(R.id.box_bar_dist_value);
        obsValue = v.findViewById(R.id.box_bar_obs_value);
        allowObsScroll(obsValue);
//        ImageButton
        imgButton = v.findViewById(R.id.box_bar_image_3);
        Glide.with(this).load(R.drawable.boxbarl).centerCrop().into(imgButton);
        imgButton.setOnClickListener(this::imgExpandClick);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void createLShapeParcel(Bundle bundle) {
        double rightA, rightB, rightC, rightD, rightDist, rightDiam;
        String rightObs = null;

        rightA = Double.parseDouble(String.valueOf(aValue.getText()));
        rightB = Double.parseDouble(String.valueOf(bValue.getText()));
        rightC = Double.parseDouble(String.valueOf(cValue.getText()));
        rightD = Double.parseDouble(String.valueOf(dValue.getText()));
        rightDist = Double.parseDouble(String.valueOf(distValue.getText()));
        rightDiam = Double.parseDouble(String.valueOf(diamValue.getText()));
        if (!TextUtils.isEmpty(obsValue.getText()))
            rightObs = String.valueOf(obsValue.getText());


        BoxBarParcel parcel = new BoxBarParcel(null, null, null, null, null,
                null, null, rightA, rightB, rightC, rightD, rightDiam, rightDist, rightObs);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    private void loadLBarData(RestBoxEntry entry) {

        if (entry.getComBoxRightShapeBarA() != null)
            aValue.setText(String.valueOf(entry.getComBoxRightShapeBarA()));
        if (entry.getComBoxRightShapeBarB() != null)
            bValue.setText(String.valueOf(entry.getComBoxRightShapeBarB()));
        if (entry.getComBoxRightShapeBarC() != null)
            cValue.setText(String.valueOf(entry.getComBoxRightShapeBarC()));
        if (entry.getComBoxRightShapeBarD() != null)
            dValue.setText(String.valueOf(entry.getComBoxRightShapeBarD()));
        if (entry.getComBoxRightShapeBarDiam() != null)
            diamValue.setText(String.valueOf(entry.getComBoxRightShapeBarDiam()));
        if (entry.getComBoxRightShapeBarDist() != null)
            distValue.setText(String.valueOf(entry.getComBoxRightShapeBarDist()));
        if (entry.getComBoxRightBarObs() != null)
            obsValue.setText(String.valueOf(entry.getComBoxRightBarObs()));

    }

    private void imgExpandClick(View view) {
        imgBundle.putInt(IMAGE_ID, R.drawable.boxbarl);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private boolean checkEmptyFields() {
        clearBoxBarErrors();
        int i = 0;

        if (TextUtils.isEmpty(aValue.getText())) {
            i++;
            aField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(bValue.getText())) {
            i++;
            bField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(cValue.getText())) {
            i++;
            cField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(dValue.getText())) {
            i++;
            dField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(diamValue.getText())) {
            i++;
            diamField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(distValue.getText())) {
            i++;
            distField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }

    private void clearBoxBarErrors() {
        aField.setErrorEnabled(false);
        bField.setErrorEnabled(false);
        cField.setErrorEnabled(false);
        dField.setErrorEnabled(false);
        diamField.setErrorEnabled(false);
        distField.setErrorEnabled(false);
    }

}