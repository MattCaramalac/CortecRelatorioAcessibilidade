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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class SinkLeftVertBarFragment extends Fragment implements TagInterface {

    ImageButton vert1, vert2;
    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, diamField, distField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, diamValue, distValue;

    ViewModelEntry modelEntry;
    Bundle imgBundle;

    static int sinkType, restID;
    int imgID;

    public SinkLeftVertBarFragment() {
        // Required empty public constructor
    }

    public static SinkLeftVertBarFragment newInstance(Bundle bundle) {
        SinkLeftVertBarFragment fragment = new SinkLeftVertBarFragment();
        sinkType = bundle.getInt(SINK_TYPE);
        restID = bundle.getInt(REST_ID);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgBundle = new Bundle();
        if (sinkType == 3)
            imgID = R.drawable.sinkcorner2;
        else
            imgID = R.drawable.sinkvertbar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sink_vert_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateVertViews(view);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            checkEmptyFields(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });

        modelEntry.getRestSinkData(restID).observe(getViewLifecycleOwner(), this::loadLeftVertBarData);

    }

    private void loadLeftVertBarData(RestroomEntry entry) {
        if (entry.getLeftVertMeasureA() != null)
            measureValueA.setText(String.valueOf(entry.getLeftVertMeasureA()));
        if (entry.getLeftVertMeasureB() != null)
            measureValueB.setText(String.valueOf(entry.getLeftVertMeasureB()));
        if (entry.getLeftVertMeasureC() != null)
            measureValueC.setText(String.valueOf(entry.getLeftVertMeasureC()));
        if (entry.getLeftVertMeasureD() != null)
            measureValueD.setText(String.valueOf(entry.getLeftVertMeasureD()));
        if (entry.getLeftVertMeasureE() != null)
            measureValueE.setText(String.valueOf(entry.getLeftVertMeasureE()));
        if (entry.getLeftBarDiam() != null)
            diamValue.setText(String.valueOf(entry.getLeftBarDiam()));
        if (entry.getLeftBarDist() != null)
            distValue.setText(String.valueOf(entry.getLeftBarDist()));
    }

    private void instantiateVertViews(View view) {
//        ImageButton
        vert1 = view.findViewById(R.id.vert_bar_img);
        vert2 = view.findViewById(R.id.vert_bar_img2);
        Glide.with(this).load(imgID).centerCrop().into(vert1);
        Glide.with(this).load(R.drawable.sinkvertbar2).centerCrop().into(vert2);
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.sink_vert_measureA_field);
        measureFieldB = view.findViewById(R.id.sink_vert_measureB_field);
        measureFieldC = view.findViewById(R.id.sink_vert_measureC_field);
        measureFieldD = view.findViewById(R.id.sink_vert_measureD_field);
        measureFieldE = view.findViewById(R.id.sink_vert_measureE_field);
        diamField = view.findViewById(R.id.sink_vert_diam_field);
        distField = view.findViewById(R.id.dist_vert_bar_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.sink_vert_measureA_value);
        measureValueB = view.findViewById(R.id.sink_vert_measureB_value);
        measureValueC = view.findViewById(R.id.sink_vert_measureC_value);
        measureValueD = view.findViewById(R.id.sink_vert_measureD_value);
        measureValueE = view.findViewById(R.id.sink_vert_measureE_value);
        diamValue = view.findViewById(R.id.sink_vert_diam_value);
        distValue = view.findViewById(R.id.dist_vert_bar_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        vert1.setOnClickListener(this::imgExpandClick);
        vert2.setOnClickListener(this::imgExpandClick);
//        Show Views
        vert2.setVisibility(View.VISIBLE);
        measureFieldB.setVisibility(View.VISIBLE);
        measureFieldC.setVisibility(View.VISIBLE);
        measureFieldD.setVisibility(View.VISIBLE);
        measureFieldE.setVisibility(View.VISIBLE);
    }

    private void imgExpandClick(View view) {
        if (view == vert1)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, imgID);
        if (view == vert2)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkvertbar2);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private void checkEmptyFields(Bundle bundle) {
        clearEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(LEFT_A, Double.parseDouble(String.valueOf(measureValueA.getText())));
        if (TextUtils.isEmpty(measureValueB.getText())) {
            i++;
            measureFieldB.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(LEFT_B, Double.parseDouble(String.valueOf(measureValueB.getText())));
        if (TextUtils.isEmpty(measureValueC.getText())) {
            i++;
            measureFieldC.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(LEFT_C, Double.parseDouble(String.valueOf(measureValueC.getText())));
        if (TextUtils.isEmpty(measureValueD.getText())) {
            i++;
            measureFieldD.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(LEFT_D, Double.parseDouble(String.valueOf(measureValueD.getText())));
        if (TextUtils.isEmpty(measureValueE.getText())) {
            i++;
            measureFieldE.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(LEFT_E, Double.parseDouble(String.valueOf(measureValueE.getText())));
        if (TextUtils.isEmpty(diamValue.getText())) {
            i++;
            diamField.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(DIAM_A, Double.parseDouble(String.valueOf(diamValue.getText())));
        if (TextUtils.isEmpty(distValue.getText())) {
            i++;
            distField.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(DIST_A, Double.parseDouble(String.valueOf(distValue.getText())));

        bundle.putBoolean(CHILD_DATA_COMPLETE, i == 0);
    }

    private void clearEmptyFieldsErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
        diamField.setErrorEnabled(false);
        distField.setErrorEnabled(false);
    }


}