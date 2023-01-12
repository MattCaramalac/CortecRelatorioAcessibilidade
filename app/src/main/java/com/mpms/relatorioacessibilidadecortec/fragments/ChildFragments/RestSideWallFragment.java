package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;


public class RestSideWallFragment extends Fragment implements TagInterface {

    ImageButton horBar, horDistBar, vertBar;
    TextInputLayout measureFieldD, measureFieldE, measureFieldF, measureFieldG, measureFieldH, measureFieldI, measureFieldJ, horSectionField,
            vertSectionField, horBarDistField, vertBarDistField;
    TextInputEditText measureValueD, measureValueE, measureValueF, measureValueG, measureValueH, measureValueI, measureValueJ, horSectionValue,
            vertSectionValue, horBarDistValue, vertBarDistValue;
    RadioGroup hasHorBar, hasVertBar;
    TextView hBarError, vBarError;

    Bundle imgData;
    ViewModelEntry modelEntry;

    static int restID;

    public RestSideWallFragment() {
        // Required empty public constructor
    }

    public static RestSideWallFragment newInstance(int i) {
        restID = i;
        return new RestSideWallFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgData = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_side_wall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSideWallViews(view);

        modelEntry.getOneRestroomEntry(restID).observe(getViewLifecycleOwner(), this::loadSideWallData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (checkSideWallFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                gatherData(bundle);
            } else {
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            }
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });
    }

    public void instantiateSideWallViews(View view) {
//        ImageButton
        horBar = view.findViewById(R.id.hor_side_bar_img);
        horDistBar = view.findViewById(R.id.side_wall_bar_dist_img);
        vertBar = view.findViewById(R.id.rest_vert_bar_img);
//        TextInputLayout
        measureFieldD = view.findViewById(R.id.bar_measureD_field);
        measureFieldE = view.findViewById(R.id.bar_measureE_field);
        measureFieldF = view.findViewById(R.id.bar_measureF_field);
        measureFieldG = view.findViewById(R.id.bar_measureG_field);
        measureFieldH = view.findViewById(R.id.bar_measureH_field);
        measureFieldI = view.findViewById(R.id.bar_measureI_field);
        measureFieldJ = view.findViewById(R.id.bar_measureJ_field);
        horSectionField = view.findViewById(R.id.side_hor_bar_section_field);
        vertSectionField = view.findViewById(R.id.vert_bar_section_field);
        horBarDistField = view.findViewById(R.id.ext_face_side_bar_dist_field);
        vertBarDistField = view.findViewById(R.id.ext_face_vert_bar_dist_field);
//        TextInputEditText
        measureValueD = view.findViewById(R.id.bar_measureD_value);
        measureValueE = view.findViewById(R.id.bar_measureE_value);
        measureValueF = view.findViewById(R.id.bar_measureF_value);
        measureValueG = view.findViewById(R.id.bar_measureG_value);
        measureValueH = view.findViewById(R.id.bar_measureH_value);
        measureValueI = view.findViewById(R.id.bar_measureI_value);
        measureValueJ = view.findViewById(R.id.bar_measureJ_value);
        horSectionValue = view.findViewById(R.id.side_hor_bar_section_value);
        vertSectionValue = view.findViewById(R.id.vert_bar_section_value);
        horBarDistValue = view.findViewById(R.id.ext_face_side_bar_dist_value);
        vertBarDistValue = view.findViewById(R.id.ext_face_vert_bar_dist_value);
//        RadioGroup
        hasHorBar = view.findViewById(R.id.side_hor_bar_radio);
        hasVertBar = view.findViewById(R.id.vert_bar_radio);
//        TextView
        hBarError = view.findViewById(R.id.side_hor_bar_error);
        vBarError = view.findViewById(R.id.vert_bar_error);
//        Images
        Glide.with(this).load(R.drawable.wallbar).centerCrop().into(horBar);
        Glide.with(this).load(R.drawable.disthorwall).centerCrop().into(horDistBar);
        Glide.with(this).load(R.drawable.vertbar).centerCrop().into(vertBar);
//        Listener
        hasHorBar.setOnCheckedChangeListener(this::barRadioListener);
        hasVertBar.setOnCheckedChangeListener(this::barRadioListener);
        horBar.setOnClickListener(this::imgButtonView);
        horDistBar.setOnClickListener(this::imgButtonView);
        vertBar.setOnClickListener(this::imgButtonView);
//        ModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        Log.i("DATA_LISTENER", "onViewCreatedChild: modelEntry =" + modelEntry);
    }

    public void imgButtonView(View view) {
        if (view == horBar)
            imgData.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.wallbar);
        else if (view == horDistBar)
            imgData.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.disthorwall);
        else if (view == vertBar)
            imgData.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.vertbar);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgData);
    }

    private void barRadioListener(RadioGroup radio, int check) {
        int index = getCheckedRadioIndex(radio);

        if (radio == hasHorBar) {
            if (index == 1) {
                horBar.setVisibility(View.VISIBLE);
                horDistBar.setVisibility(View.VISIBLE);
                measureFieldD.setVisibility(View.VISIBLE);
                measureFieldE.setVisibility(View.VISIBLE);
                measureFieldF.setVisibility(View.VISIBLE);
                measureFieldG.setVisibility(View.VISIBLE);
                horSectionField.setVisibility(View.VISIBLE);
                horBarDistField.setVisibility(View.VISIBLE);
            } else {
                measureValueD.setText(null);
                measureValueE.setText(null);
                measureValueF.setText(null);
                measureValueG.setText(null);
                horSectionValue.setText(null);
                horBarDistValue.setText(null);
                horBar.setVisibility(View.GONE);
                horDistBar.setVisibility(View.GONE);
                measureFieldD.setVisibility(View.GONE);
                measureFieldE.setVisibility(View.GONE);
                measureFieldF.setVisibility(View.GONE);
                measureFieldG.setVisibility(View.GONE);
                horSectionField.setVisibility(View.GONE);
                horBarDistField.setVisibility(View.GONE);
            }
        } else if (radio == hasVertBar) {
            if (index == 1) {
                vertBar.setVisibility(View.VISIBLE);
                measureFieldJ.setVisibility(View.VISIBLE);
                measureFieldH.setVisibility(View.VISIBLE);
                measureFieldI.setVisibility(View.VISIBLE);
                vertSectionField.setVisibility(View.VISIBLE);
                vertBarDistField.setVisibility(View.VISIBLE);
            } else {
                measureValueJ.setText(null);
                measureValueH.setText(null);
                measureValueI.setText(null);
                vertSectionValue.setText(null);
                vertBarDistValue.setText(null);
                vertBar.setVisibility(View.GONE);
                measureFieldJ.setVisibility(View.GONE);
                measureFieldH.setVisibility(View.GONE);
                measureFieldI.setVisibility(View.GONE);
                vertSectionField.setVisibility(View.GONE);
                vertBarDistField.setVisibility(View.GONE);
            }
        }
    }

    private void loadSideWallData(RestroomEntry rest) {
        if (rest.getHasHorBar() != null)
            hasHorBar.check(hasHorBar.getChildAt(rest.getHasHorBar()).getId());
        if (rest.getHorBarD() != null)
            measureValueD.setText(String.valueOf(rest.getHorBarD()));
        if (rest.getHorBarE() != null)
            measureValueE.setText(String.valueOf(rest.getHorBarE()));
        if (rest.getHorBarF() != null)
            measureValueF.setText(String.valueOf(rest.getHorBarF()));
        if (rest.getHorBarDistG() != null)
            measureValueG.setText(String.valueOf(rest.getHorBarDistG()));
        if (rest.getHasVertBar() != null)
            hasVertBar.check(hasVertBar.getChildAt(rest.getHasVertBar()).getId());
        if (rest.getVertBarJ() != null)
            measureValueJ.setText(String.valueOf(rest.getVertBarJ()));
        if (rest.getVertBarH() != null)
            measureValueH.setText(String.valueOf(rest.getVertBarH()));
        if (rest.getVertBarI() != null)
            measureValueI.setText(String.valueOf(rest.getVertBarI()));

        if (rest.getVertBarDist() != null)
            vertBarDistValue.setText(String.valueOf(rest.getVertBarDist()));
        if (rest.getHorBarDist() != null)
            horBarDistValue.setText(String.valueOf(rest.getHorBarDist()));
        if (rest.getVertBarSect() != null)
            vertSectionValue.setText(String.valueOf(rest.getVertBarSect()));
        if (rest.getHorBarSect() != null)
            horSectionValue.setText(String.valueOf(rest.getHorBarSect()));
    }


    private int getCheckedRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkSideWallFields() {
        clearSideWallErrors();
        int i = 0;
        if (getCheckedRadioIndex(hasHorBar) == -1) {
            i++;
            hBarError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadioIndex(hasHorBar) == 1) {
            if (TextUtils.isEmpty(measureValueD.getText())) {
                i++;
                measureFieldD.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueE.getText())) {
                i++;
                measureFieldE.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueF.getText())) {
                i++;
                measureFieldF.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueG.getText())) {
                i++;
                measureFieldG.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horSectionValue.getText())) {
                i++;
                horSectionField.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horBarDistValue.getText())) {
                i++;
                horBarDistField.setError(getText(R.string.req_field_error));
            }
        }
        if (getCheckedRadioIndex(hasVertBar) == -1) {
            i++;
            vBarError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadioIndex(hasVertBar) == 1) {
            if (TextUtils.isEmpty(measureValueJ.getText())) {
                i++;
                measureFieldJ.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueH.getText())) {
                i++;
                measureFieldH.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueI.getText())) {
                i++;
                measureFieldI.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(vertSectionValue.getText())) {
                i++;
                vertSectionField.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(vertBarDistValue.getText())) {
                i++;
                vertBarDistField.setError(getText(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    private void clearSideWallErrors() {
        hBarError.setVisibility(View.GONE);
        vBarError.setVisibility(View.GONE);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
        measureFieldF.setErrorEnabled(false);
        measureFieldG.setErrorEnabled(false);
        measureFieldH.setErrorEnabled(false);
        measureFieldI.setErrorEnabled(false);
        measureFieldJ.setErrorEnabled(false);
        vertSectionField.setErrorEnabled(false);
        vertBarDistField.setErrorEnabled(false);
        horSectionField.setErrorEnabled(false);
        horBarDistField.setErrorEnabled(false);
    }

    private void gatherData(Bundle bundle) {
        bundle.putInt(HAS_HOR, getCheckedRadioIndex(hasHorBar));
        if (getCheckedRadioIndex(hasHorBar) == 1) {
            bundle.putDouble(SIZE_D, Double.parseDouble(String.valueOf(measureValueD.getText())));
            bundle.putDouble(SIZE_E, Double.parseDouble(String.valueOf(measureValueE.getText())));
            bundle.putDouble(SIZE_F, Double.parseDouble(String.valueOf(measureValueF.getText())));
            bundle.putDouble(SIZE_G, Double.parseDouble(String.valueOf(measureValueG.getText())));
            bundle.putDouble(DIAM_A, Double.parseDouble(String.valueOf(horSectionValue.getText())));
            bundle.putDouble(DIST_A, Double.parseDouble(String.valueOf(horBarDistValue.getText())));
        }
        bundle.putInt(HAS_VERT, getCheckedRadioIndex(hasVertBar));
        if (getCheckedRadioIndex(hasVertBar) == 1) {
            bundle.putDouble(SIZE_H, Double.parseDouble(String.valueOf(measureValueH.getText())));
            bundle.putDouble(SIZE_I, Double.parseDouble(String.valueOf(measureValueI.getText())));
            bundle.putDouble(SIZE_J, Double.parseDouble(String.valueOf(measureValueJ.getText())));
            bundle.putDouble(DIAM_B, Double.parseDouble(String.valueOf(vertSectionValue.getText())));
            bundle.putDouble(DIST_B, Double.parseDouble(String.valueOf(vertBarDistValue.getText())));
        }
    }
}