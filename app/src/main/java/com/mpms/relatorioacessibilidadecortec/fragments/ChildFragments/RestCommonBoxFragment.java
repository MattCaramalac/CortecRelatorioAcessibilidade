package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
import com.mpms.relatorioacessibilidadecortec.data.parcels.CommonBoxParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class RestCommonBoxFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout boxDoorWidthField, boxDiamField, doorDistField, boxWidthField, boxObsField;
    TextInputEditText boxDoorWidthValue, boxDiamValue, doorDistValue, boxWidthValue, boxObsValue;
    RadioGroup hasBarRadio, leftBarRadio, rightBarRadio;
    TextView hasBarError, leftBarHeader, leftBarQuestion, leftBarError, rightBarHeader, rightBarQuestion, rightBarError;
    ImageButton imgButton;
    ViewModelEntry modelEntry;
    FrameLayout leftBarLayout, rightBarLayout;

    Bundle comBundle, imgBundle = new Bundle();

    BoxBarParcel left = null, right = null;

    public RestCommonBoxFragment() {
        // Required empty public constructor
    }


    public static RestCommonBoxFragment newInstance() {
        return new RestCommonBoxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            comBundle = this.getArguments();
        else
            comBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_common_box, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instComBoxViews(view);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (getCheckRadio(leftBarRadio) == 0 || getCheckRadio(leftBarRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_2, bundle);
            else if (getCheckRadio(rightBarRadio) == 0 || getCheckRadio(rightBarRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_3, bundle);
            else {
                if (checkEmptyComBoxField()) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    createComBoxParcel(bundle, left, right);
                } else
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);

                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            }
        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_2, this, (key,bundle) -> {
            if (bundle.getParcelable(CHILD_PARCEL) != null)
                left = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            if (getCheckRadio(rightBarRadio) == 0 || getCheckRadio(rightBarRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_3, bundle);
            else {
                if (checkEmptyComBoxField()) {
                    createComBoxParcel(bundle, left, right);
                    if (!checkEmptyComBoxField() || !bundle.getBoolean(CHILD_DATA_COMPLETE))
                        bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                } else
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);

                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            }
        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_3, this, (key,bundle) -> {
            if (bundle.getParcelable(CHILD_PARCEL) != null)
                right = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            if (getCheckRadio(rightBarRadio) == 0 || getCheckRadio(rightBarRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_3, bundle);
            else {
                if (checkEmptyComBoxField()) {
                    createComBoxParcel(bundle, left, right);
                    if (!checkEmptyComBoxField() || !bundle.getBoolean(CHILD_DATA_COMPLETE))
                        bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                } else
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);

                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            }
        });

        if (comBundle.getInt(BOX_ID) > 0)
        modelEntry.getOneBoxEntry(comBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadComBoxData);
    }

    private void instComBoxViews(View view) {
//        TextInputLayout
        boxDoorWidthField = view.findViewById(R.id.box_door_width_field);
        boxDiamField = view.findViewById(R.id.free_space_diam_field);
        doorDistField = view.findViewById(R.id.box_toilet_door_dist_field);
        boxWidthField = view.findViewById(R.id.box_width_field);
        boxObsField = view.findViewById(R.id.common_box_obs_field);
//        TextInputEditText
        boxDoorWidthValue = view.findViewById(R.id.box_door_width_value);
        boxDiamValue = view.findViewById(R.id.free_space_diam_value);
        doorDistValue = view.findViewById(R.id.box_toilet_door_dist_value);
        boxWidthValue = view.findViewById(R.id.box_width_value);
        boxObsValue = view.findViewById(R.id.common_box_obs_value);
//        RadioGroup
        hasBarRadio = view.findViewById(R.id.box_support_bar_radio);
        leftBarRadio = view.findViewById(R.id.box_left_bar_type_radio);
        rightBarRadio = view.findViewById(R.id.box_right_bar_type_radio);
        hasBarRadio.setOnCheckedChangeListener(this::radioListener);
        leftBarRadio.setOnCheckedChangeListener(this::radioListener);
        rightBarRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        hasBarError = view.findViewById(R.id.box_support_bar_error);
        leftBarHeader = view.findViewById(R.id.left_sup_bar_header);
        leftBarQuestion = view.findViewById(R.id.box_left_bar_type_header);
        leftBarError = view.findViewById(R.id.box_left_bar_type_error);
        rightBarHeader = view.findViewById(R.id.right_sup_bar_header);
        rightBarQuestion = view.findViewById(R.id.box_right_bar_type_header);
        rightBarError = view.findViewById(R.id.box_right_bar_type_error);
//        FrameLayout
        leftBarLayout = view.findViewById(R.id.left_bar_frame);
        rightBarLayout = view.findViewById(R.id.right_bar_frame);
//        ImageButton
        imgButton = view.findViewById(R.id.box_bar_image_1);
        Glide.with(this).load(R.drawable.boxsupview).centerCrop().into(imgButton);
        imgButton.setOnClickListener(this::imgExpandClick);
//        ViewModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void loadComBoxData(RestBoxEntry entry) {
        if (entry.getTypeBox() == 0) {
            if (entry.getComBoxDoorWidth() != null)
                boxDoorWidthValue.setText(String.valueOf(entry.getComBoxDoorWidth()));
            if (entry.getComBoxFreeDiam() != null)
                boxDiamValue.setText(String.valueOf(entry.getComBoxFreeDiam()));
            if (entry.getComBoxHasBars() != null && entry.getComBoxHasBars() > -1) {
                hasBarRadio.check(hasBarRadio.getChildAt(entry.getComBoxHasBars()).getId());
                if (entry.getComBoxHasBars() == 1) {
                    if (entry.getComBoxToiletDoorDist() != null)
                        doorDistValue.setText(String.valueOf(entry.getComBoxToiletDoorDist()));
                    if (entry.getComBoxWidth() != null)
                        boxWidthValue.setText(String.valueOf(entry.getComBoxWidth()));
                    if (entry.getComBoxHasLeftBar() != null && entry.getComBoxHasLeftBar() > -1)
                        leftBarRadio.check(leftBarRadio.getChildAt(entry.getComBoxHasLeftBar()).getId());
                    if (entry.getComBoxHasRightBar() != null && entry.getComBoxHasRightBar() > -1)
                        rightBarRadio.check(rightBarRadio.getChildAt(entry.getComBoxHasRightBar()).getId());
                }
            }
            if (entry.getComBoxObs() != null)
                boxObsValue.setText(entry.getComBoxObs());
        }
    }

    private int getCheckRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void createComBoxParcel(Bundle bundle, BoxBarParcel left, BoxBarParcel right) {
        double doorWidth, boxFreeDiam;
        int hasBars;
        Double doorDist = null, boxWidth = null;
        Integer hasLeft = null, hasRight = null;
        String obs = null;

        doorWidth = Double.parseDouble(String.valueOf(boxDoorWidthValue.getText()));
        boxFreeDiam = Double.parseDouble(String.valueOf(boxDiamValue.getText()));
        hasBars = getCheckRadio(hasBarRadio);
        if (hasBars == 1) {
            doorDist = Double.parseDouble(String.valueOf(doorDistValue.getText()));
            boxWidth = Double.parseDouble(String.valueOf(boxWidthValue.getText()));
            hasLeft = getCheckRadio(leftBarRadio);
            hasRight = getCheckRadio(rightBarRadio);

        }
        if (!TextUtils.isEmpty(boxObsValue.getText()))
            obs = String.valueOf(boxObsValue.getText());

        CommonBoxParcel parcel = new CommonBoxParcel(doorWidth, boxFreeDiam, hasBars, doorDist, boxWidth, hasLeft, left, hasRight, right, obs);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    private boolean checkEmptyComBoxField() {
        int i = 0;
        if (TextUtils.isEmpty(boxDoorWidthValue.getText())) {
            i++;
            boxDoorWidthField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(boxDiamValue.getText())) {
            i++;
            boxDiamField.setError(getString(R.string.req_field_error));
        }
        if (getCheckRadio(hasBarRadio) == -1) {
            i++;
            hasBarError.setVisibility(View.VISIBLE);
        } else if (getCheckRadio(hasBarRadio) == 1) {
            if (TextUtils.isEmpty(boxWidthValue.getText())) {
                i++;
                boxWidthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(doorDistValue.getText())) {
                i++;
                doorDistField.setError(getString(R.string.req_field_error));
            }
            if (getCheckRadio(leftBarRadio) == -1) {
                i++;
                leftBarError.setVisibility(View.VISIBLE);
            }
            if (getCheckRadio(rightBarRadio) == -1) {
                i++;
                rightBarError.setVisibility(View.VISIBLE);
            }
        }

        return i == 0;
    }

    private void imgExpandClick(View view) {
        imgBundle.putInt(IMAGE_ID, R.drawable.boxsupview);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private void radioListener(RadioGroup radio, int checkedID) {
        int index = getCheckRadio(radio);
        int boxID = comBundle.getInt(BOX_ID);
        if (radio == hasBarRadio) {
            if (index == 1) {
                imgButton.setVisibility(View.VISIBLE);
                doorDistField.setVisibility(View.VISIBLE);
                boxWidthField.setVisibility(View.VISIBLE);
                leftBarHeader.setVisibility(View.VISIBLE);
                leftBarQuestion.setVisibility(View.VISIBLE);
                leftBarRadio.setVisibility(View.VISIBLE);
                rightBarHeader.setVisibility(View.VISIBLE);
                rightBarQuestion.setVisibility(View.VISIBLE);
                rightBarRadio.setVisibility(View.VISIBLE);
                leftBarLayout.setVisibility(View.VISIBLE);
                rightBarLayout.setVisibility(View.VISIBLE);
            } else {
                doorDistValue.setText(null);
                boxWidthValue.setText(null);
                leftBarRadio.clearCheck();
                rightBarRadio.clearCheck();
                removeSillFragments(R.id.left_bar_frame);
                removeSillFragments(R.id.right_bar_frame);
                imgButton.setVisibility(View.GONE);
                doorDistField.setVisibility(View.GONE);
                boxWidthField.setVisibility(View.GONE);
                leftBarHeader.setVisibility(View.GONE);
                leftBarQuestion.setVisibility(View.GONE);
                leftBarRadio.setVisibility(View.GONE);
                leftBarError.setVisibility(View.GONE);
                rightBarHeader.setVisibility(View.GONE);
                rightBarQuestion.setVisibility(View.GONE);
                rightBarRadio.setVisibility(View.GONE);
                rightBarRadio.setVisibility(View.GONE);
                leftBarLayout.setVisibility(View.GONE);
                rightBarLayout.setVisibility(View.GONE);
            }
        } else {
            int container;
            boolean fromLeft;
            if (radio == leftBarRadio) {
                container = R.id.left_bar_frame;
                fromLeft = true;
            } else {
                container = R.id.right_bar_frame;
                fromLeft = false;
            }
            switch (index) {
                case 0:
                    getChildFragmentManager().beginTransaction().replace(container, RestBoxLShapeBarFragment.newInstance(boxID, fromLeft)).commit();
                    break;
                case 1:
                    getChildFragmentManager().beginTransaction().replace(container,RestBoxTwoBarFragment.newInstance(boxID, fromLeft)).commit();
                    break;
                default:
                    removeSillFragments(container);
            }
        }
    }

    private void removeSillFragments(int container) {
        Fragment fragment = getChildFragmentManager().findFragmentById(container);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }
}