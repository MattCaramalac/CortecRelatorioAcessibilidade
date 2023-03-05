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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.VertSinkBarParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class SinkVertFrontalBarFragment extends Fragment implements TagInterface {

    ImageButton vert1, vert2;
    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, diamField, distField, obsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, diamValue, distValue, obsValue;

    ViewModelEntry modelEntry;
    Bundle imgBundle;

    static Bundle vertBarBundle;

    static int sinkType;
    int firstImg, secondImg;

    public SinkVertFrontalBarFragment() {
        // Required empty public constructor
    }

    public static SinkVertFrontalBarFragment newInstance(Bundle bundle, int type) {
        SinkVertFrontalBarFragment fragment = new SinkVertFrontalBarFragment();
        vertBarBundle = bundle;
        sinkType = type;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgBundle = new Bundle();
        if (sinkType == 3)
            firstImg = R.drawable.sinkcorner2;
        else
            firstImg = R.drawable.sinkvertbar;

        secondImg = R.drawable.sinkvertbar2simp;

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
            if (checkEmptyFields(bundle))
                createVertBarParcel(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER_2, bundle);
        });

        if (vertBarBundle.getBoolean(FROM_BOX))
            modelEntry.getBoxSinkData(vertBarBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxLeftVertBarData);
        else
            modelEntry.getRestSinkData(vertBarBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadLeftVertBarData);

    }

    private void createVertBarParcel(Bundle bundle) {
        double measureA, measureB, measureC, diam, dist;
        Double measureD = null, measureE = null;
        String obs = null;

        measureA = Double.parseDouble(String.valueOf(measureValueA.getText()));
        measureB = Double.parseDouble(String.valueOf(measureValueB.getText()));
        measureC = Double.parseDouble(String.valueOf(measureValueC.getText()));
        diam = Double.parseDouble(String.valueOf(diamValue.getText()));
        dist = Double.parseDouble(String.valueOf(distValue.getText()));
        if (!TextUtils.isEmpty(obsValue.getText()))
            obs = String.valueOf(obsValue.getText());

        VertSinkBarParcel parcel = new VertSinkBarParcel(true, measureA, measureB, measureC, measureD, measureE, diam, dist, obs);
        bundle.putParcelable(CHILD_PARCEL_2, Parcels.wrap(parcel));
    }

    private void loadLeftVertBarData(RestroomEntry entry) {
        if (entry.getLeftFrontHorMeasureA() != null)
            measureValueA.setText(String.valueOf(entry.getLeftFrontHorMeasureA()));
        if (entry.getLeftFrontHorMeasureB() != null)
            measureValueB.setText(String.valueOf(entry.getLeftFrontHorMeasureB()));
        if (entry.getLeftFrontHorMeasureC() != null)
            measureValueC.setText(String.valueOf(entry.getLeftFrontHorMeasureC()));
        if (entry.getLeftFrontHorDiam() != null)
            diamValue.setText(String.valueOf(entry.getLeftFrontHorDiam()));
        if (entry.getLeftFrontHorDist() != null)
            distValue.setText(String.valueOf(entry.getLeftFrontHorDist()));
        if (entry.getLeftFrontHorObs() != null)
            obsValue.setText(entry.getLeftFrontHorObs());
    }

    private void loadBoxLeftVertBarData(RestBoxEntry entry) {
        if (entry.getLeftFrontHorMeasureA() != null)
            measureValueA.setText(String.valueOf(entry.getLeftFrontHorMeasureA()));
        if (entry.getLeftFrontHorMeasureB() != null)
            measureValueB.setText(String.valueOf(entry.getLeftFrontHorMeasureB()));
        if (entry.getLeftFrontHorMeasureC() != null)
            measureValueC.setText(String.valueOf(entry.getLeftFrontHorMeasureC()));
        if (entry.getLeftFrontHorDiam() != null)
            diamValue.setText(String.valueOf(entry.getLeftFrontHorDiam()));
        if (entry.getLeftFrontHorDist() != null)
            distValue.setText(String.valueOf(entry.getLeftFrontHorDist()));
        if (entry.getLeftFrontHorObs() != null)
            obsValue.setText(entry.getLeftFrontHorObs());
    }

    private void instantiateVertViews(View view) {
//        ImageButton
        vert1 = view.findViewById(R.id.vert_bar_img);
        vert2 = view.findViewById(R.id.vert_bar_img2);
        Glide.with(this).load(firstImg).centerCrop().into(vert1);
        Glide.with(this).load(secondImg).centerCrop().into(vert2);
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.sink_vert_measureA_field);
        measureFieldB = view.findViewById(R.id.sink_vert_measureB_field);
        measureFieldC = view.findViewById(R.id.sink_vert_measureC_field);
        measureFieldD = view.findViewById(R.id.sink_vert_measureD_field);
        measureFieldE = view.findViewById(R.id.sink_vert_measureE_field);
        diamField = view.findViewById(R.id.sink_vert_diam_field);
        distField = view.findViewById(R.id.dist_vert_bar_field);
        obsField = view.findViewById(R.id.vert_bar_obs_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.sink_vert_measureA_value);
        measureValueB = view.findViewById(R.id.sink_vert_measureB_value);
        measureValueC = view.findViewById(R.id.sink_vert_measureC_value);
        measureValueD = view.findViewById(R.id.sink_vert_measureD_value);
        measureValueE = view.findViewById(R.id.sink_vert_measureE_value);
        diamValue = view.findViewById(R.id.sink_vert_diam_value);
        distValue = view.findViewById(R.id.dist_vert_bar_value);
        obsValue = view.findViewById(R.id.vert_bar_obs_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        vert1.setOnClickListener(this::imgExpandClick);
        vert2.setOnClickListener(this::imgExpandClick);

    }

    private void imgExpandClick(View view) {
        if (view == vert1)
            imgBundle.putInt(IMAGE_ID, firstImg);
        if (view == vert2)
            imgBundle.putInt(IMAGE_ID, secondImg);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private boolean checkEmptyFields(Bundle bundle) {
        clearEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            i++;
            measureFieldA.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(measureValueB.getText())) {
            i++;
            measureFieldB.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(measureValueC.getText())) {
            i++;
            measureFieldC.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(diamValue.getText())) {
            i++;
            diamField.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(distValue.getText())) {
            i++;
            distField.setError(getText(R.string.req_field_error));
        }
        bundle.putBoolean(CHILD_DATA_COMPLETE_2, i == 0);

        return i == 0;
    }

    private void clearEmptyFieldsErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        diamField.setErrorEnabled(false);
        distField.setErrorEnabled(false);
    }


}