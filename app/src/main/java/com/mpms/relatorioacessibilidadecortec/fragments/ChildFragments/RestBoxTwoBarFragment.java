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


public class RestBoxTwoBarFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout aVertField, bVertField, cVertField, diamVertField, distVertField, obsVertField, aHorField, bHorField, cHorField, diamHorField, distHorField, obsHorField;
    TextInputEditText aVertValue, bVertValue, cVertValue, diamVertValue, distVertValue, obsVertValue, aHorValue, bHorValue, cHorValue, diamHorValue, distHorValue, obsHorValue;
    ImageButton imgButton1, imgButton2;
    ViewModelEntry modelEntry;

    static int boxID;
    static boolean isLeft;

    Bundle imgBundle = new Bundle();

    public RestBoxTwoBarFragment() {
        // Required empty public constructor
    }

    public static RestBoxTwoBarFragment newInstance(int box, boolean isLeftBar) {
        boxID = box;
        isLeft = isLeftBar;
        return new RestBoxTwoBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_box_two_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instTwoBarsViews(view);

        if (isLeft) {
            getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_2, this, (key, bundle) -> {
                if (checkEmptyFields()) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    createTwoBarParcel(bundle);
                } else {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                    bundle.putParcelable(CHILD_PARCEL, null);
                }
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_2, bundle);
            });
        } else {
            getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_3, this, (key, bundle) -> {
                if (checkEmptyFields()) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    createTwoBarParcel(bundle);
                } else {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                    bundle.putParcelable(CHILD_PARCEL, null);
                }
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_3, bundle);
            });
        }

        if (boxID > 0)
            modelEntry.getCommonBoxData(boxID).observe(getViewLifecycleOwner(), this::loadTwoBarsData);
    }

    private void instTwoBarsViews(View v) {
        //        TextInputLayout
        aHorField = v.findViewById(R.id.box_hor_bar_measure_a_field);
        bHorField = v.findViewById(R.id.box_hor_bar_measure_b_field);
        cHorField = v.findViewById(R.id.box_hor_bar_measure_c_field);
        diamHorField = v.findViewById(R.id.box_hor_bar_diam_field);
        distHorField = v.findViewById(R.id.box_hor_bar_dist_field);
        obsHorField = v.findViewById(R.id.box_hor_bar_obs_field);
        aVertField = v.findViewById(R.id.box_vert_bar_measure_a_field);
        bVertField = v.findViewById(R.id.box_vert_bar_measure_b_field);
        cVertField = v.findViewById(R.id.box_vert_bar_measure_c_field);
        diamVertField = v.findViewById(R.id.box_vert_bar_diam_field);
        distVertField = v.findViewById(R.id.box_vert_bar_dist_field);
        obsVertField = v.findViewById(R.id.box_vert_bar_obs_field);
//        TextInputEditText
        aHorValue = v.findViewById(R.id.box_hor_bar_measure_a_value);
        bHorValue = v.findViewById(R.id.box_hor_bar_measure_b_value);
        cHorValue = v.findViewById(R.id.box_hor_bar_measure_c_value);
        diamHorValue = v.findViewById(R.id.box_hor_bar_diam_value);
        distHorValue = v.findViewById(R.id.box_hor_bar_dist_value);
        obsHorValue = v.findViewById(R.id.box_hor_bar_obs_value);
        aVertValue = v.findViewById(R.id.box_vert_bar_measure_a_value);
        bVertValue = v.findViewById(R.id.box_vert_bar_measure_b_value);
        cVertValue = v.findViewById(R.id.box_vert_bar_measure_c_value);
        diamVertValue = v.findViewById(R.id.box_vert_bar_diam_value);
        distVertValue = v.findViewById(R.id.box_vert_bar_dist_value);
        obsVertValue = v.findViewById(R.id.box_vert_bar_obs_value);
        allowObsScroll(obsVertValue);
        allowObsScroll(obsHorValue);
//        ImageButton
        imgButton1 = v.findViewById(R.id.box_hor_bar_image);
        imgButton2 = v.findViewById(R.id.box_vert_bar_image);
        Glide.with(this).load(R.drawable.boxbarhor).centerCrop().into(imgButton1);
        Glide.with(this).load(R.drawable.boxbarvert).centerCrop().into(imgButton2);
        imgButton1.setOnClickListener(this::imgExpandClick);
        imgButton2.setOnClickListener(this::imgExpandClick);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void imgExpandClick(View view) {
        if (view == imgButton1)
            imgBundle.putInt(IMAGE_ID, R.drawable.boxbarhor);
        else
            imgBundle.putInt(IMAGE_ID, R.drawable.boxbarvert);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private boolean checkEmptyFields() {
        clearBoxBarErrors();
        int i = 0;

        if (TextUtils.isEmpty(aHorValue.getText())) {
            i++;
            aHorField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(bHorValue.getText())) {
            i++;
            bHorField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(cHorValue.getText())) {
            i++;
            cHorField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(diamHorValue.getText())) {
            i++;
            diamHorField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(distHorValue.getText())) {
            i++;
            distHorField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(aVertValue.getText())) {
            i++;
            aVertField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(bVertValue.getText())) {
            i++;
            bVertField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(cVertValue.getText())) {
            i++;
            cVertField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(diamVertValue.getText())) {
            i++;
            diamVertField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(distVertValue.getText())) {
            i++;
            distVertField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }

    private void clearBoxBarErrors() {
        aHorField.setErrorEnabled(false);
        bHorField.setErrorEnabled(false);
        cHorField.setErrorEnabled(false);
        diamHorField.setErrorEnabled(false);
        distHorField.setErrorEnabled(false);
        aVertField.setErrorEnabled(false);
        bVertField.setErrorEnabled(false);
        cVertField.setErrorEnabled(false);
        diamVertField.setErrorEnabled(false);
        distVertField.setErrorEnabled(false);
    }

    private void loadTwoBarsData(RestBoxEntry entry) {
        if (entry.getComBoxHasLeftBar() == 0) {
            if (isLeft) {
                if (entry.getComBoxLeftShapeBarA() != null)
                    aHorValue.setText(String.valueOf(entry.getComBoxLeftShapeBarA()));
                if (entry.getComBoxLeftShapeBarB() != null)
                    bHorValue.setText(String.valueOf(entry.getComBoxLeftShapeBarB()));
                if (entry.getComBoxLeftShapeBarC() != null)
                    cHorValue.setText(String.valueOf(entry.getComBoxLeftShapeBarC()));
                if (entry.getComBoxLeftShapeBarDiam() != null)
                    diamHorValue.setText(String.valueOf(entry.getComBoxLeftShapeBarDiam()));
                if (entry.getComBoxLeftShapeBarDist() != null)
                    distHorValue.setText(String.valueOf(entry.getComBoxLeftShapeBarDist()));
                if (entry.getComBoxLeftVertObs() != null)
                    obsHorValue.setText(String.valueOf(entry.getComBoxLeftVertObs()));
                if (entry.getComBoxLeftVertBarA() != null)
                    aVertValue.setText(String.valueOf(entry.getComBoxLeftVertBarA()));
                if (entry.getComBoxLeftVertBarB() != null)
                    bVertValue.setText(String.valueOf(entry.getComBoxLeftVertBarB()));
                if (entry.getComBoxLeftVertBarC() != null)
                    cVertValue.setText(String.valueOf(entry.getComBoxLeftVertBarC()));
                if (entry.getComBoxLeftVertBarDiam() != null)
                    diamVertValue.setText(String.valueOf(entry.getComBoxLeftVertBarDiam()));
                if (entry.getComBoxLeftVertBarDist() != null)
                    distVertValue.setText(String.valueOf(entry.getComBoxLeftVertBarDist()));
                if (entry.getComBoxLeftVertObs() != null)
                    obsVertValue.setText(String.valueOf(entry.getComBoxLeftVertObs()));
            } else {
                if (entry.getComBoxRightShapeBarA() != null)
                    aHorValue.setText(String.valueOf(entry.getComBoxRightShapeBarA()));
                if (entry.getComBoxRightShapeBarB() != null)
                    bHorValue.setText(String.valueOf(entry.getComBoxRightShapeBarB()));
                if (entry.getComBoxRightShapeBarC() != null)
                    cHorValue.setText(String.valueOf(entry.getComBoxRightShapeBarC()));
                if (entry.getComBoxRightShapeBarDiam() != null)
                    diamHorValue.setText(String.valueOf(entry.getComBoxRightShapeBarDiam()));
                if (entry.getComBoxRightShapeBarDist() != null)
                    distHorValue.setText(String.valueOf(entry.getComBoxRightShapeBarDist()));
                if (entry.getComBoxRightVertObs() != null)
                    obsHorValue.setText(String.valueOf(entry.getComBoxRightVertObs()));
                if (entry.getComBoxRightVertBarA() != null)
                    aVertValue.setText(String.valueOf(entry.getComBoxRightVertBarA()));
                if (entry.getComBoxRightVertBarB() != null)
                    bVertValue.setText(String.valueOf(entry.getComBoxRightVertBarB()));
                if (entry.getComBoxRightVertBarC() != null)
                    cVertValue.setText(String.valueOf(entry.getComBoxRightVertBarC()));
                if (entry.getComBoxRightVertBarDiam() != null)
                    diamVertValue.setText(String.valueOf(entry.getComBoxRightVertBarDiam()));
                if (entry.getComBoxRightVertBarDist() != null)
                    distVertValue.setText(String.valueOf(entry.getComBoxRightVertBarDist()));
                if (entry.getComBoxRightVertObs() != null)
                    obsVertValue.setText(String.valueOf(entry.getComBoxRightVertObs()));
            }
        }
    }

    private void createTwoBarParcel(Bundle bundle) {
        Double leftHorA = null, leftHorB = null, leftHorC = null, leftHorDist = null, leftHorDiam = null, rightHorA = null, rightHorB = null, rightHorC = null,
                rightHorDist = null, rightHorDiam = null, leftVertA = null, leftVertB = null, leftVertC = null, leftVertD = null, leftVertDist = null, leftVertDiam = null,
                rightVertA = null, rightVertB = null, rightVertC = null, rightVertD = null, rightVertDist = null, rightVertDiam = null;
        String leftHorObs = null, leftVertObs = null, rightHorObs = null, rightVertObs = null;

        if (isLeft) {
            leftHorA = Double.parseDouble(String.valueOf(aHorValue.getText()));
            leftHorB = Double.parseDouble(String.valueOf(bHorValue.getText()));
            leftHorC = Double.parseDouble(String.valueOf(cHorValue.getText()));
            leftHorDist = Double.parseDouble(String.valueOf(distHorValue.getText()));
            leftHorDiam = Double.parseDouble(String.valueOf(diamHorValue.getText()));
            leftHorObs = String.valueOf(obsHorValue.getText());
            leftVertA = Double.parseDouble(String.valueOf(aVertValue.getText()));
            leftVertB = Double.parseDouble(String.valueOf(bVertValue.getText()));
            leftVertC = Double.parseDouble(String.valueOf(cVertValue.getText()));
            leftVertDist = Double.parseDouble(String.valueOf(distVertValue.getText()));
            leftVertDiam = Double.parseDouble(String.valueOf(diamVertValue.getText()));
            leftVertObs = String.valueOf(obsVertValue.getText());
        } else {
            rightHorA = Double.parseDouble(String.valueOf(aHorValue.getText()));
            rightHorB = Double.parseDouble(String.valueOf(bHorValue.getText()));
            rightHorC = Double.parseDouble(String.valueOf(cHorValue.getText()));
            rightHorDist = Double.parseDouble(String.valueOf(distHorValue.getText()));
            rightHorDiam = Double.parseDouble(String.valueOf(diamHorValue.getText()));
            rightHorObs = String.valueOf(obsHorValue.getText());
            rightVertA = Double.parseDouble(String.valueOf(aVertValue.getText()));
            rightVertB = Double.parseDouble(String.valueOf(bVertValue.getText()));
            rightVertC = Double.parseDouble(String.valueOf(cVertValue.getText()));
            rightVertDist = Double.parseDouble(String.valueOf(distVertValue.getText()));
            rightVertDiam = Double.parseDouble(String.valueOf(diamVertValue.getText()));
            rightVertObs = String.valueOf(obsVertValue.getText());
        }


        BoxBarParcel parcel = new BoxBarParcel(leftHorA, leftHorB, leftHorC, null, leftHorDiam, leftHorDist, leftHorObs, leftVertA, leftVertB, leftVertC,
                leftVertDiam, leftVertDist, leftVertObs, rightHorA, rightHorB, rightHorC, null, rightHorDiam, rightHorDist, rightHorObs, rightVertA, rightVertB,
                rightVertC, rightVertDiam, rightVertDist, rightVertObs);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

}