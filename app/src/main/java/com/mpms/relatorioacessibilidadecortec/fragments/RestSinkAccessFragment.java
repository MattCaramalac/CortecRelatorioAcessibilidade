package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SinkParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class RestSinkAccessFragment extends Fragment implements TagInterface, ScrollEditText {

    RadioGroup accSinkRadio;
    TextView accSinkError;
    TextInputLayout accSinkObsField;
    TextInputEditText accSinkObsValue;
    MaterialButton saveSink, returnAccess;

    ViewModelEntry modelEntry;
    Bundle accSinkBundle;

    public RestSinkAccessFragment() {
        // Required empty public constructor
    }

    public static RestSinkAccessFragment newInstance() {
        return new RestSinkAccessFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            accSinkBundle = new Bundle(this.getArguments());
        else
            accSinkBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_sink_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instAccSinkViews(view);

        if (accSinkBundle.getBoolean(FROM_BOX))
            modelEntry.getBoxSinkData(accSinkBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxSinkData);
        else
            modelEntry.getRestSinkData(accSinkBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadRestSinkData);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE) && checkEmptyFields())
                saveSinkData(bundle);
            else
                toastMessage();
        });
    }

    private void loadRestSinkData(RestroomEntry entry) {
        if (entry.getHasSink() != null)
            accSinkRadio.check(accSinkRadio.getChildAt(entry.getHasSink()).getId());
    }

    private void loadBoxSinkData(RestBoxEntry entry) {
        if (entry.getHasSink() != null)
            accSinkRadio.check(accSinkRadio.getChildAt(entry.getHasSink()).getId());
    }

    private void instAccSinkViews(View view) {
//        RadioGroup
        accSinkRadio = view.findViewById(R.id.has_access_sink_radio);
//        TextView
        accSinkError = view.findViewById(R.id.has_access_sink_error);
//        TextInputLayout
        accSinkObsField = view.findViewById(R.id.not_access_sink_obs_field);
//        TextInputEditText
        accSinkObsValue = view.findViewById(R.id.not_access_sink_obs_value);
//        MaterialButton
        saveSink = view.findViewById(R.id.save_sink_button);
        returnAccess = view.findViewById(R.id.cancel_sink_button);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        allowObsScroll(accSinkObsValue);
        accSinkRadio.setOnCheckedChangeListener(this::radioListener);
        saveSink.setOnClickListener(this::saveClick);
        returnAccess.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void saveClick(View view) {
        if (checkEmptyFields()) {
            getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, accSinkBundle);
        } else
            toastMessage();
    }


    private boolean checkEmptyFields() {
        accSinkError.setVisibility(View.GONE);
        int i = 0;
        if (getCheckRadio(accSinkRadio) == -1) {
            i++;
            accSinkError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void toastMessage() {
        Toast.makeText(getContext(), getText(R.string.blank_fields_message), Toast.LENGTH_SHORT).show();
    }

    private void saveSinkData(Bundle bundle) {
        if (bundle.getBoolean(FROM_BOX)) {
            RestBoxSinkUpdate sinkUpdate = boxSinkUpdate(bundle);
            ViewModelEntry.updateBoxSink(sinkUpdate);
            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(BOX_LIST, 0);
        } else {
            RestSinkUpdate sinkData = sinkUpdate(bundle);
            ViewModelEntry.updateRestSinkData(sinkData);
            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(REST_LIST, 0);
        }
    }

    private RestBoxSinkUpdate boxSinkUpdate(Bundle bundle) {
        int hasSink;
        Integer sinkType = null, hasSinkBar = null, hasMirror = null, hasLeftFrontHor = null, hasSideRightVert = null;
        Double leftFrontHorA = null, leftFrontHorB = null, leftFrontHorC = null, leftFrontHorD = null, leftFrontHorDiam = null,
                leftFrontHorDist = null, rightSideVertA = null, rightSideVertB = null, rightSideVertC = null, rightSideVertD = null, rightSideVertE = null,
                rightSideVertDiam = null, rightSideVertDist = null, mirrorLow = null, mirrorHigh = null,
                approxA = null, approxB = null, approxC = null, approxD = null, approxE = null;
        String leftFrontHorObs = null, rightSideVertObs = null, sinkObs = null;

        hasSink = getCheckRadio(accSinkRadio);

        if (hasSink == 1) {

            SinkParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

            sinkType = parcel.getSinkType();

            approxA = parcel.getApproxMeasureA();
            approxB = parcel.getApproxMeasureB();
            approxC = parcel.getApproxMeasureC();
            approxD = parcel.getApproxMeasureD();
            approxE = parcel.getApproxMeasureE();

            hasSinkBar = parcel.getHasSinkBar();
            hasLeftFrontHor = parcel.getHasLeftFrontHorBar();

            leftFrontHorA = parcel.getLeftFrontHorMeasureA();
            leftFrontHorB = parcel.getLeftFrontHorMeasureB();
            leftFrontHorC = parcel.getLeftFrontHorMeasureC();
            leftFrontHorD = parcel.getLeftFrontHorMeasureD();
            leftFrontHorDiam = parcel.getLeftFrontHorDiam();
            leftFrontHorDist = parcel.getLeftFrontHorDist();
            leftFrontHorObs = parcel.getLeftFrontHorObs();

            hasSideRightVert = parcel.getHasRightSideVertBar();

            rightSideVertA = parcel.getRightSideVertMeasureA();
            rightSideVertB = parcel.getRightSideVertMeasureB();
            rightSideVertC = parcel.getRightSideVertMeasureC();
            rightSideVertD = parcel.getRightSideVertMeasureD();
            rightSideVertE = parcel.getRightSideVertMeasureE();
            rightSideVertDiam = parcel.getRightSideVertDiam();
            rightSideVertDist = parcel.getRightSideVertDist();
            rightSideVertObs = parcel.getRightSideVertObs();

            hasMirror = parcel.getSinkHasMirror();
            mirrorLow = parcel.getSinkMirrorLow();
            mirrorHigh = parcel.getSinkMirrorHigh();


            sinkObs = parcel.getSinkObs();
        }

        return new RestBoxSinkUpdate(bundle.getInt(BOX_ID), hasSink, sinkType, approxA, approxB, approxC, approxD, approxE, hasSinkBar, hasLeftFrontHor, leftFrontHorA,
                leftFrontHorB, leftFrontHorC, leftFrontHorD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasSideRightVert, rightSideVertA, rightSideVertB,
                rightSideVertC, rightSideVertD, rightSideVertE, rightSideVertDiam, rightSideVertDist, rightSideVertObs, hasMirror, mirrorLow, mirrorHigh, sinkObs);
    }

    private RestSinkUpdate sinkUpdate(Bundle bundle) {
        int hasSink;
        Integer sinkType = null, hasSinkBar = null, hasMirror = null, hasLeftFrontHor = null, hasSideRightVert = null;
        Double leftFrontHorA = null, leftFrontHorB = null, leftFrontHorC = null, leftFrontHorD = null, leftFrontHorDiam = null,
                leftFrontHorDist = null, rightSideVertA = null, rightSideVertB = null, rightSideVertC = null, rightSideVertD = null, rightSideVertE = null,
                rightSideVertDiam = null, rightSideVertDist = null, mirrorLow = null, mirrorHigh = null,
                approxA = null, approxB = null, approxC = null, approxD = null, approxE = null;
        String leftFrontHorObs = null, rightSideVertObs = null, sinkObs = null;

        hasSink = getCheckRadio(accSinkRadio);

        if (hasSink == 1) {

            SinkParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

            sinkType = parcel.getSinkType();

            approxA = parcel.getApproxMeasureA();
            approxB = parcel.getApproxMeasureB();
            approxC = parcel.getApproxMeasureC();
            approxD = parcel.getApproxMeasureD();
            approxE = parcel.getApproxMeasureE();

            hasSinkBar = parcel.getHasSinkBar();
            hasLeftFrontHor = parcel.getHasLeftFrontHorBar();

            leftFrontHorA = parcel.getLeftFrontHorMeasureA();
            leftFrontHorB = parcel.getLeftFrontHorMeasureB();
            leftFrontHorC = parcel.getLeftFrontHorMeasureC();
            leftFrontHorD = parcel.getLeftFrontHorMeasureD();
            leftFrontHorDiam = parcel.getLeftFrontHorDiam();
            leftFrontHorDist = parcel.getLeftFrontHorDist();
            leftFrontHorObs = parcel.getLeftFrontHorObs();

            hasSideRightVert = parcel.getHasRightSideVertBar();

            rightSideVertA = parcel.getRightSideVertMeasureA();
            rightSideVertB = parcel.getRightSideVertMeasureB();
            rightSideVertC = parcel.getRightSideVertMeasureC();
            rightSideVertD = parcel.getRightSideVertMeasureD();
            rightSideVertE = parcel.getRightSideVertMeasureE();
            rightSideVertDiam = parcel.getRightSideVertDiam();
            rightSideVertDist = parcel.getRightSideVertDist();
            rightSideVertObs = parcel.getRightSideVertObs();

            hasMirror = parcel.getSinkHasMirror();
            mirrorLow = parcel.getSinkMirrorLow();
            mirrorHigh = parcel.getSinkMirrorHigh();


            sinkObs = parcel.getSinkObs();
        }

        return new RestSinkUpdate(accSinkBundle.getInt(REST_ID), hasSink, sinkType, approxA, approxB, approxC, approxD, approxE, hasSinkBar, hasLeftFrontHor, leftFrontHorA,
                leftFrontHorB, leftFrontHorC, leftFrontHorD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasSideRightVert, rightSideVertA, rightSideVertB,
                rightSideVertC, rightSideVertD, rightSideVertE, rightSideVertDiam, rightSideVertDist, rightSideVertObs, hasMirror, mirrorLow, mirrorHigh, sinkObs);
    }

    private int getCheckRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void radioListener(RadioGroup radio, int checkedID) {
        int index = getCheckRadio(radio);

        if (index == 1) {
            accSinkObsValue.setText(null);
            accSinkObsField.setVisibility(View.GONE);
            Fragment fragment = new RestSinkFragment();
            fragment.setArguments(accSinkBundle);
            getChildFragmentManager().beginTransaction().replace(R.id.access_sink_frame, fragment).commit();
        } else {
            removeSillFragments();
            accSinkObsField.setVisibility(View.VISIBLE);
        }
    }

    private void removeSillFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.access_sink_frame);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }
}