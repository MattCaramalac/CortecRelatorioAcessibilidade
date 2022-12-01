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

public class SinkRightVertBarFragment extends Fragment implements TagInterface {

    ImageButton vert1;
    TextInputLayout measureFieldA, diamField, distField;
    TextInputEditText measureValueA, diamValue, distValue;

    ViewModelEntry modelEntry;
    Bundle imgBundle;

    static int sinkType, restID;
    int imgID;

    public SinkRightVertBarFragment() {
        // Required empty public constructor
    }

    public static SinkRightVertBarFragment newInstance(Bundle bundle) {
        SinkRightVertBarFragment fragment = new SinkRightVertBarFragment();
        sinkType = bundle.getInt(SINK_TYPE);
        restID = bundle.getInt(REST_ID);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgBundle = new Bundle();
        if (sinkType == 3)
            imgID = R.drawable.sinkcorner3;
        else
            imgID = R.drawable.sinkvertbar3;
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

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_2, this, (key, bundle) -> {
            checkEmptyFields(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_2, bundle);
        });

        modelEntry.getRestSinkData(restID).observe(getViewLifecycleOwner(), this::loadRightVertBarData);
    }

    private void loadRightVertBarData(RestroomEntry entry) {
        if (entry.getRightVertMeasureA() != null)
            measureValueA.setText(String.valueOf(entry.getRightVertMeasureA()));
        if (entry.getLeftBarDiam() != null)
            diamValue.setText(String.valueOf(entry.getLeftBarDiam()));
        if (entry.getLeftBarDist() != null)
            distValue.setText(String.valueOf(entry.getLeftBarDist()));
    }

    private void instantiateVertViews(View view) {
//        ImageButton
        vert1 = view.findViewById(R.id.vert_bar_img);
        Glide.with(this).load(imgID).centerCrop().into(vert1);
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.sink_vert_measureA_field);
        diamField = view.findViewById(R.id.sink_vert_diam_field);
        distField = view.findViewById(R.id.dist_vert_bar_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.sink_vert_measureA_value);
        diamValue = view.findViewById(R.id.sink_vert_diam_value);
        distValue = view.findViewById(R.id.dist_vert_bar_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        vert1.setOnClickListener(this::imgExpandClick);
    }

    private void imgExpandClick(View view) {
        if (view == vert1)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, imgID);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private void checkEmptyFields(Bundle bundle) {
        clearEmptyFieldsErrors();
        int i = 0;

        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(RIGHT_A, Double.parseDouble(String.valueOf(measureValueA.getText())));
        if (TextUtils.isEmpty(diamValue.getText())) {
            i++;
            diamField.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(DIAM_B, Double.parseDouble(String.valueOf(diamValue.getText())));
        if (TextUtils.isEmpty(distValue.getText())) {
            i++;
            distField.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(DIST_B, Double.parseDouble(String.valueOf(distValue.getText())));

        bundle.putBoolean(CHILD_DATA_COMPLETE_2, i == 0);

    }

    private void clearEmptyFieldsErrors() {
        measureFieldA.setErrorEnabled(false);
        diamField.setErrorEnabled(false);
        distField.setErrorEnabled(false);
    }
}