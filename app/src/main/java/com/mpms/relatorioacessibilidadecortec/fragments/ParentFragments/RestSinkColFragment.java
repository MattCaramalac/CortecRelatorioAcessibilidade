package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.CounterSinkParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SinkParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.RestColCounterSinkFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class RestSinkColFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    RadioGroup hasColSink, sinkTypeRadio;
    TextView colSinkError, sinkTypeHeader, sinkTypeError;
    TextInputLayout sinkObsField;
    TextInputEditText sinkObsValue;
    MaterialButton cancelColSink, saveColSink;

    Bundle colSinkBundle;
    ViewModelEntry modelEntry;

    public RestSinkColFragment() {
        // Required empty public constructor
    }

    public static RestSinkColFragment newInstance() {
        return new RestSinkColFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            colSinkBundle = new Bundle(this.getArguments());
        else
            colSinkBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_sink_col, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateColSinkView(view);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, getViewLifecycleOwner(), (key, bundle) -> {
            if (checkEmptyFields() && bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                RestSinkUpdate sUpdate = colSinkUpdate(bundle);
                ViewModelEntry.updateRestSinkData(sUpdate);
                RestUrinalFragment fragment = RestUrinalFragment.newInstance();
                fragment.setArguments(colSinkBundle);
                requireActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        modelEntry.getRestSinkData(colSinkBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadColSinkData);
    }

    private void loadColSinkData(RestroomEntry rest) {
        if (rest.getHasSink() != null) {
            checkRadioGroup(hasColSink, rest.getHasSink());
            if (rest.getHasSink() == 1 && rest.getSinkType() != null) {
                checkRadioGroup(sinkTypeRadio, rest.getSinkType());
            } else {
                if (rest.getSinkObs() != null)
                    sinkObsValue.setText(rest.getSinkObs());
            }
        }

    }

    private void instantiateColSinkView(View view) {
//        TextInputLayout
        sinkObsField = view.findViewById(R.id.not_col_sink_obs_field);
//        TextInputEditText
        sinkObsValue = view.findViewById(R.id.not_col_sink_obs_value);
//        TextView
        colSinkError = view.findViewById(R.id.has_col_sink_error);
        sinkTypeHeader = view.findViewById(R.id.has_col_sink_type_header);
        sinkTypeError = view.findViewById(R.id.has_col_sink_type_error);
//        RadioGroup
        hasColSink = view.findViewById(R.id.has_col_sink_radio);
        sinkTypeRadio = view.findViewById(R.id.has_col_sink_type_radio);
//        MaterialButton
        cancelColSink = view.findViewById(R.id.cancel_col_sink_button);
        saveColSink = view.findViewById(R.id.save_col_sink_button);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listener
        hasColSink.setOnCheckedChangeListener(this::radioListener);
        sinkTypeRadio.setOnCheckedChangeListener(this::radioListener);
        cancelColSink.setOnClickListener(this::buttonClick);
        saveColSink.setOnClickListener(this::buttonClick);
    }

    private void buttonClick(View view) {
        if (view == cancelColSink) {
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        } else {
            if (checkEmptyFields()) {
                if (indexRadio(hasColSink) == 0) {
                    RestSinkUpdate sUpdate = colSinkUpdate(colSinkBundle);
                    ViewModelEntry.updateRestSinkData(sUpdate);
                    RestUrinalFragment fragment = RestUrinalFragment.newInstance();
                    fragment.setArguments(colSinkBundle);
                    requireActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
                } else if (indexRadio(hasColSink) == 1) {
                    getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, colSinkBundle);
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void radioListener(RadioGroup radio, int checkID) {
        int index = radio.indexOfChild(radio.findViewById(checkID));

        if (radio == hasColSink) {
            if (index == 0) {
                sinkTypeRadio.clearCheck();
                removeSillFragments();
                sinkTypeRadio.setVisibility(View.GONE);
                sinkTypeHeader.setVisibility(View.GONE);
                sinkTypeError.setVisibility(View.GONE);
                sinkObsField.setVisibility(View.VISIBLE);
            } else if (index == 1) {
                sinkObsValue.setText(null);
                sinkObsField.setVisibility(View.GONE);
                sinkTypeHeader.setVisibility(View.VISIBLE);
                sinkTypeRadio.setVisibility(View.VISIBLE);
            }

        } else {
            Fragment fragment = null;
            if (index == 0) {
                fragment = RestSinkFragment.newInstance();
            } else if (index == 1) {
                fragment = RestColCounterSinkFragment.newInstance();
            }
            if (fragment != null) {
                fragment.setArguments(colSinkBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.col_sink_frame, fragment).commit();
            }
        }
    }

    private void removeSillFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.col_sink_frame);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }

    private boolean checkEmptyFields() {
        int i = 0;
        if (indexRadio(hasColSink) == -1) {
            i++;
            colSinkError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasColSink) == 1) {
            if (indexRadio(sinkTypeRadio) == -1) {
                i++;
                sinkTypeError.setVisibility(View.VISIBLE);
            }
        }

        return i == 0;
    }

    private RestSinkUpdate colSinkUpdate(Bundle bundle) {
        int hasSink;
        Integer sinkType = null, hasColumn = null, hasSinkBar = null, hasMirror = null, hasLeftFrontHor = null, hasSideRightVert = null, lowSink = null;
        Double leftFrontHorA = null, leftFrontHorB = null, leftFrontHorC = null, leftFrontHorD = null, leftFrontHorDiam = null,
                leftFrontHorDist = null, rightSideVertA = null, rightSideVertB = null, rightSideVertC = null, rightSideVertD = null, rightSideVertE = null,
                rightSideVertDiam = null, rightSideVertDist = null, mirrorLow = null, mirrorHigh = null,
                approxA = null, approxB = null, approxC = null, approxD = null, approxE = null;
        String leftFrontHorObs = null, rightSideVertObs = null, sinkObs, photo = null;

        hasSink = indexRadio(hasColSink);
        if (hasSink == 0) {
            sinkObs = String.valueOf(sinkObsValue.getText());
        }
        else {
            sinkType = indexRadio(sinkTypeRadio);

            if (sinkType == 0) {
                SinkParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                approxA = parcel.getApproxMeasureA();
                approxB = parcel.getApproxMeasureB();
                approxC = parcel.getApproxMeasureC();
                approxD = parcel.getApproxMeasureD();
                approxE = parcel.getApproxMeasureE();

                hasColumn = parcel.getHasColumn();
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
                photo = parcel.getSinkPhoto();
            } else {
                CounterSinkParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                lowSink = parcel.getHasLowerSink();
                approxB = parcel.getApproxMeasureB();
                approxC = parcel.getApproxMeasureC();
                hasSinkBar = parcel.getHasSinkBar();
                sinkObs = parcel.getSinkObs();
                photo = parcel.getPhoto();
            }
        }

        return new RestSinkUpdate(bundle.getInt(REST_ID), hasSink, sinkType, approxA, approxB, approxC, approxD, approxE, hasColumn, hasSinkBar, hasLeftFrontHor, leftFrontHorA,
                leftFrontHorB, leftFrontHorC, leftFrontHorD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasSideRightVert, rightSideVertA, rightSideVertB,
                rightSideVertC, rightSideVertD, rightSideVertE, rightSideVertDiam, rightSideVertDist, rightSideVertObs, hasMirror, mirrorLow, mirrorHigh, sinkObs, lowSink,
                photo);
    }
}