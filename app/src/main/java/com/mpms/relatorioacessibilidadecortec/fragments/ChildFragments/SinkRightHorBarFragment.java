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

public class SinkRightHorBarFragment extends Fragment implements TagInterface {

    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, diamField, distField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, diamValue, distValue;
    ImageButton horImage, horImage2;

    Bundle imgBundle;

    ViewModelEntry modelEntry;

    static int restID;

    public SinkRightHorBarFragment() {
        // Required empty public constructor
    }

    public static SinkRightHorBarFragment newInstance(Bundle bundle) {
        restID = bundle.getInt(REST_ID);
        return new SinkRightHorBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sink_hor_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA_2, this, (key, bundle) -> {
            checkEmptyField(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_2, bundle);
        });

        modelEntry.getRestSinkData(restID).observe(getViewLifecycleOwner(), this::loadRightHorBarData);
    }


    private void loadRightHorBarData(RestroomEntry entry) {
        if (entry.getRightHorMeasureA() != null)
            measureValueA.setText(String.valueOf(entry.getRightHorMeasureA()));
        if (entry.getRightHorMeasureB() != null)
            measureValueB.setText(String.valueOf(entry.getRightHorMeasureB()));
        if (entry.getRightHorMeasureC() != null)
            measureValueC.setText(String.valueOf(entry.getRightHorMeasureC()));
        if (entry.getRightHorMeasureD() != null)
            measureValueD.setText(String.valueOf(entry.getRightHorMeasureD()));
        if (entry.getRightBarDiam() != null)
            diamValue.setText(String.valueOf(entry.getRightBarDiam()));
        if (entry.getRightBarDist() != null)
            distValue.setText(String.valueOf(entry.getRightBarDist()));
    }

    private void instantiateViews(View view) {
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.sink_hor_measureA_field);
        measureFieldB = view.findViewById(R.id.sink_hor_measureB_field);
        measureFieldC = view.findViewById(R.id.sink_hor_measureC_field);
        measureFieldD = view.findViewById(R.id.sink_hor_measureD_field);
        diamField = view.findViewById(R.id.sink_hor_diam_field);
        distField = view.findViewById(R.id.dist_hor_bar_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.sink_hor_measureA_value);
        measureValueB = view.findViewById(R.id.sink_hor_measureB_value);
        measureValueC = view.findViewById(R.id.sink_hor_measureC_value);
        measureValueD = view.findViewById(R.id.sink_hor_measureD_value);
        diamValue = view.findViewById(R.id.sink_hor_diam_value);
        distValue = view.findViewById(R.id.dist_hor_bar_value);
//        ImageButton
        horImage = view.findViewById(R.id.hor_bar_img);
        horImage2 = view.findViewById(R.id.hor_bar_img2);
        Glide.with(this).load(R.drawable.sinkhorbar).centerCrop().into(horImage);
        Glide.with(this).load(R.drawable.sinkhorbar2).centerCrop().into(horImage2);
//        Listener
        horImage.setOnClickListener(this::imgExpandClick);
        horImage2.setOnClickListener(this::imgExpandClick);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void imgExpandClick(View view) {
        if (view == horImage)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkhorbar);
        if (view == horImage)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkhorbar2);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private void checkEmptyField(Bundle bundle) {
        clearEmptyFieldError();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(RIGHT_A, Double.parseDouble(String.valueOf(measureValueA.getText())));
        if (TextUtils.isEmpty(measureValueB.getText())) {
            i++;
            measureFieldB.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(RIGHT_B, Double.parseDouble(String.valueOf(measureValueB.getText())));
        if (TextUtils.isEmpty(measureValueC.getText())) {
            i++;
            measureFieldC.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(RIGHT_C, Double.parseDouble(String.valueOf(measureValueC.getText())));
        if (TextUtils.isEmpty(measureValueD.getText())) {
            i++;
            measureFieldD.setError(getText(R.string.req_field_error));
        } else
            bundle.putDouble(RIGHT_D, Double.parseDouble(String.valueOf(measureValueD.getText())));
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

    private void clearEmptyFieldError() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        diamField.setErrorEnabled(false);
        distField.setErrorEnabled(false);
    }
}
