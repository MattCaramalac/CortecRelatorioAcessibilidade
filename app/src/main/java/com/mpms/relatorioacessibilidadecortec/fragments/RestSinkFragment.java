package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkLeftHorBarFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkLeftVertBarFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkRightHorBarFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkRightVertBarFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class RestSinkFragment extends Fragment implements TagInterface, ScrollEditText {

    ImageButton sink1, sink2, sink3, sink4, sink5, sinkApprox, sinkMirror;
    RadioGroup sinkRadio1, sinkRadio2, hasBarRadio, leftSinkRadio, rightSinkRadio, mirrorRadio;
    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, mirrorFieldA, mirrorFieldB, sinkObsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, mirrorValueA, mirrorValueB, sinkObsValue;
    TextView sinkError, barError, leftHeader, rightHeader, leftError, rightError, mirrorError, sinkTypeWarning;
    FrameLayout leftFrag, rightFrag;
    MaterialButton returnAccess, saveSink;
    Fragment lFrag, rFrag;

    Bundle sinkBundle, imgBundle, childBundle;
    ViewModelEntry modelEntry;

    boolean leftData = true, rightData = true, check1 = true, check2 = true;

    public RestSinkFragment() {
        // Required empty public constructor
    }

    public static RestSinkFragment newInstance() {
        return new RestSinkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            sinkBundle = new Bundle(this.getArguments());
        }
        imgBundle = new Bundle();
        childBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_sink, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSinkViews(view);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            leftData = bundle.getBoolean(CHILD_DATA_COMPLETE);
            if (getCheckRadio(rightSinkRadio) > 0)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_2, bundle);
            else {
                if (checkEmptyFields())
                    saveSinkData(bundle);
                else
                    Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
                leftData = true;
                rightData = true;
            }

        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_2, this, (key, bundle) -> {
            rightData = bundle.getBoolean(CHILD_DATA_COMPLETE_2);
            if (checkEmptyFields())
                saveSinkData(bundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            leftData = true;
            rightData = true;
        });

        modelEntry.getRestSinkData(sinkBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadSinkData);

    }

    private void instantiateSinkViews(View view) {
//        ImageButtons
        sink1 = view.findViewById(R.id.sink_type_1);
        sink2 = view.findViewById(R.id.sink_type_2);
        sink3 = view.findViewById(R.id.sink_type_3);
        sink4 = view.findViewById(R.id.sink_type_4);
        sink5 = view.findViewById(R.id.sink_type_5);
        sinkApprox = view.findViewById(R.id.sink_approx_img);
        sinkMirror = view.findViewById(R.id.sink_mirror_img);
        Glide.with(this).load(R.drawable.sinkhorbar).centerCrop().into(sink1);
        Glide.with(this).load(R.drawable.sinkvertbar).centerCrop().into(sink2);
        Glide.with(this).load(R.drawable.sinkhorvertbar).centerCrop().into(sink3);
        Glide.with(this).load(R.drawable.sinkcorner).centerCrop().into(sink4);
        Glide.with(this).load(R.drawable.sinksemifit).centerCrop().into(sink5);
        Glide.with(this).load(R.drawable.sinkapprox).centerCrop().into(sinkApprox);
        Glide.with(this).load(R.drawable.sinkmirror).centerCrop().into(sinkMirror);
//        RadioGroup
        sinkRadio1 = view.findViewById(R.id.sink_type_radio1);
        sinkRadio2 = view.findViewById(R.id.sink_type_radio2);
        hasBarRadio = view.findViewById(R.id.sink_has_bar_radio);
        leftSinkRadio = view.findViewById(R.id.left_bar_radio);
        rightSinkRadio = view.findViewById(R.id.right_bar_radio);
        mirrorRadio = view.findViewById(R.id.sink_mirror_radio);
//        TextInputLayout
        measureFieldA = view.findViewById(R.id.sink_approx_measureA_field);
        measureFieldB = view.findViewById(R.id.sink_approx_measureB_field);
        measureFieldC = view.findViewById(R.id.sink_approx_measureC_field);
        measureFieldD = view.findViewById(R.id.sink_approx_measureD_field);
        measureFieldE = view.findViewById(R.id.sink_approx_measureE_field);
        mirrorFieldA = view.findViewById(R.id.sink_mirror_measureA_field);
        mirrorFieldB = view.findViewById(R.id.sink_mirror_measureB_field);
        sinkObsField = view.findViewById(R.id.sink_obs_field);
//        TextInputEditText
        measureValueA = view.findViewById(R.id.sink_approx_measureA_value);
        measureValueB = view.findViewById(R.id.sink_approx_measureB_value);
        measureValueC = view.findViewById(R.id.sink_approx_measureC_value);
        measureValueD = view.findViewById(R.id.sink_approx_measureD_value);
        measureValueE = view.findViewById(R.id.sink_approx_measureE_value);
        mirrorValueA = view.findViewById(R.id.sink_mirror_measureA_value);
        mirrorValueB = view.findViewById(R.id.sink_mirror_measureB_value);
        sinkObsValue = view.findViewById(R.id.sink_obs_value);
//        TextView
        sinkError = view.findViewById(R.id.sink_type_error);
        barError = view.findViewById(R.id.sink_has_bar_error);
        leftHeader = view.findViewById(R.id.sink_left_bar_header);
        rightHeader = view.findViewById(R.id.sink_right_bar_header);
        leftError = view.findViewById(R.id.left_bar_error);
        rightError = view.findViewById(R.id.right_bar_error);
        mirrorError = view.findViewById(R.id.sink_mirror_error);
        sinkTypeWarning = view.findViewById(R.id.select_sink_type_warning);
//        FrameLayout
        leftFrag = view.findViewById(R.id.left_bar_fragment);
        rightFrag = view.findViewById(R.id.right_bar_fragment);
//        MaterialButton
        returnAccess = view.findViewById(R.id.return_t_access);
        saveSink = view.findViewById(R.id.save_sink);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        sink1.setOnClickListener(this::imgExpandClick);
        sink2.setOnClickListener(this::imgExpandClick);
        sink3.setOnClickListener(this::imgExpandClick);
        sink4.setOnClickListener(this::imgExpandClick);
        sink5.setOnClickListener(this::imgExpandClick);
        sinkApprox.setOnClickListener(this::imgExpandClick);
        sinkMirror.setOnClickListener(this::imgExpandClick);
        sinkRadio1.setOnCheckedChangeListener(this::radioListener);
        sinkRadio2.setOnCheckedChangeListener(this::radioListener);
        hasBarRadio.setOnCheckedChangeListener(this::radioListener);
        leftSinkRadio.setOnCheckedChangeListener(this::radioListener);
        rightSinkRadio.setOnCheckedChangeListener(this::radioListener);
        mirrorRadio.setOnCheckedChangeListener(this::radioListener);


        saveSink.setOnClickListener(this::saveClick);
        returnAccess.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        allowObsScroll(sinkObsValue);
    }

    private void imgExpandClick(View view) {
        if (view == sink1)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkhorbar);
        else if (view == sink2)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkvertbar);
        else if (view == sink3)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkhorvertbar);
        else if (view == sink4)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkcorner);
        else if (view == sink5)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinksemifit);
        else if (view == sinkApprox)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkapprox);
        else if (view == sinkMirror)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sinkmirror);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);

    }

    private int getCheckRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void radioListener(RadioGroup radio, int check) {
        int radioIndex = getCheckRadio(radio);

        if (radio == sinkRadio1 && check1) {
            check2 = false;
            sinkRadio2.clearCheck();
            childBundle.putInt(SINK_TYPE, radioIndex);
            childBundle.putInt(REST_ID, sinkBundle.getInt(REST_ID));
            leftSinkRadio.clearCheck();
            rightSinkRadio.clearCheck();
            hasBarRadio.clearCheck();
            check2 = true;
            if (getCheckRadio(hasBarRadio) == 1) {
                sinkTypeWarning.setVisibility(View.GONE);
                leftHeader.setVisibility(View.VISIBLE);
                rightHeader.setVisibility(View.VISIBLE);
                leftSinkRadio.setVisibility(View.VISIBLE);
                rightSinkRadio.setVisibility(View.VISIBLE);
            }
        } else if (radio == sinkRadio2 && check2) {
            check1 = false;
            sinkRadio1.clearCheck();
            childBundle.putInt(SINK_TYPE, (radioIndex == 0) ? 3 : 4);
            childBundle.putInt(REST_ID, sinkBundle.getInt(REST_ID));
            leftSinkRadio.clearCheck();
            rightSinkRadio.clearCheck();
            hasBarRadio.clearCheck();
            check1 = true;
            if (getCheckRadio(hasBarRadio) == 1) {
                sinkTypeWarning.setVisibility(View.GONE);
                leftHeader.setVisibility(View.VISIBLE);
                rightHeader.setVisibility(View.VISIBLE);
                leftSinkRadio.setVisibility(View.VISIBLE);
                rightSinkRadio.setVisibility(View.VISIBLE);
            }
        } else if (radio == hasBarRadio) {
            if (radioIndex == 1 && (getCheckRadio(sinkRadio1) != -1 || getCheckRadio(sinkRadio2) != -1)) {
                sinkTypeWarning.setVisibility(View.GONE);
                leftHeader.setVisibility(View.VISIBLE);
                rightHeader.setVisibility(View.VISIBLE);
                leftSinkRadio.setVisibility(View.VISIBLE);
                rightSinkRadio.setVisibility(View.VISIBLE);
            } else if (radioIndex == 1) {
                sinkTypeWarning.setVisibility(View.VISIBLE);
            } else {
                sinkTypeWarning.setVisibility(View.VISIBLE);
                leftSinkRadio.clearCheck();
                rightSinkRadio.clearCheck();
                leftHeader.setVisibility(View.GONE);
                rightHeader.setVisibility(View.GONE);
                leftSinkRadio.setVisibility(View.GONE);
                rightSinkRadio.setVisibility(View.GONE);
                removeChildFragments();
            }
        } else if (radio == leftSinkRadio) {
            if (radioIndex == 1) {
                if (getCheckRadio(sinkRadio1) == 0)
                    lFrag = SinkLeftHorBarFragment.newInstance(childBundle);
                else
                    lFrag = SinkLeftVertBarFragment.newInstance(childBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.left_bar_fragment, lFrag).commit();
            } else {
                if (lFrag != null)
                    getChildFragmentManager().beginTransaction().remove(lFrag).commit();
            }
        } else if (radio == rightSinkRadio) {
            if (radioIndex == 1) {
                if (getCheckRadio(sinkRadio1) == 0 || getCheckRadio(sinkRadio1) == 2)
                    rFrag = SinkRightHorBarFragment.newInstance(childBundle);
                else
                    rFrag = SinkRightVertBarFragment.newInstance(childBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.right_bar_fragment, rFrag).commit();
            } else {
                if (rFrag != null)
                    getChildFragmentManager().beginTransaction().remove(rFrag).commit();
            }
        } else if (radio == mirrorRadio) {
            if (radioIndex == 1) {
                sinkMirror.setVisibility(View.VISIBLE);
                mirrorFieldA.setVisibility(View.VISIBLE);
                mirrorFieldB.setVisibility(View.VISIBLE);
            } else {
                mirrorValueA.setText(null);
                mirrorValueB.setText(null);
                sinkMirror.setVisibility(View.GONE);
                mirrorFieldA.setVisibility(View.GONE);
                mirrorFieldB.setVisibility(View.GONE);
            }
        }
    }

    private void removeChildFragments() {
        if (lFrag != null)
            getChildFragmentManager().beginTransaction().remove(lFrag).commit();
        if (rFrag != null)
            getChildFragmentManager().beginTransaction().remove(rFrag).commit();
    }

    private boolean checkEmptyFields() {
        clearFieldErrors();
        int i = 0;
        if (getCheckRadio(sinkRadio1) == -1 && getCheckRadio(sinkRadio2) == -1) {
            i++;
            sinkError.setVisibility(View.VISIBLE);
        }
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
        if (TextUtils.isEmpty(measureValueD.getText())) {
            i++;
            measureFieldD.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(measureValueE.getText())) {
            i++;
            measureFieldE.setError(getText(R.string.req_field_error));
        }
        if (getCheckRadio(hasBarRadio) == -1) {
            i++;
            barError.setVisibility(View.VISIBLE);
        } else if (getCheckRadio(hasBarRadio) == 1) {
            if (getCheckRadio(leftSinkRadio) == -1) {
                i++;
                leftError.setVisibility(View.VISIBLE);
            }
            if (getCheckRadio(rightSinkRadio) == -1) {
                i++;
                rightError.setVisibility(View.VISIBLE);
            }
        }
        if (getCheckRadio(mirrorRadio) == -1) {
            i++;
            mirrorError.setVisibility(View.VISIBLE);
        } else if (getCheckRadio(mirrorRadio) == 1) {
            if (TextUtils.isEmpty(mirrorValueA.getText())) {
                i++;
                mirrorFieldA.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(mirrorValueB.getText())) {
                i++;
                mirrorFieldB.setError(getText(R.string.req_field_error));
            }
        }
        if (!leftData || !rightData)
            i++;

        return i == 0;
    }

    private void clearFieldErrors() {
        sinkError.setVisibility(View.GONE);
        leftError.setVisibility(View.GONE);
        rightError.setVisibility(View.GONE);
        mirrorError.setVisibility(View.GONE);
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
        mirrorFieldA.setErrorEnabled(false);
        mirrorFieldB.setErrorEnabled(false);
    }

    private RestSinkUpdate sinkUpdate(Bundle bundle) {
        int sinkType = 0, hasSinkBar, hasMirror;
        Integer hasLeft = null, hasRight = null;
        double approxA, approxB, approxC, approxD, approxE;
        Double leftHorA = null, leftHorB = null, leftHorC = null, leftHorD = null, leftVertA = null, leftVertB = null, leftVertC = null,
                leftVertD = null, leftVertE = null, leftBarDiam = null, leftBarDist = null, rightHorA = null, rightHorB = null, rightHorC = null,
                rightHorD = null, rightVertA = null, rightBarDiam = null, rightBarDist = null, mirrorLow = null, mirrorHigh = null;
        String sinkObs;

        if (getCheckRadio(sinkRadio1) > -1)
            sinkType = getCheckRadio(sinkRadio1);
        else {
            if (getCheckRadio(sinkRadio2) == 0)
                sinkType = 3;
            else if (getCheckRadio(sinkRadio2) == 1)
                sinkType = 4;
        }
        approxA = Double.parseDouble(String.valueOf(measureValueA.getText()));
        approxB = Double.parseDouble(String.valueOf(measureValueB.getText()));
        approxC = Double.parseDouble(String.valueOf(measureValueC.getText()));
        approxD = Double.parseDouble(String.valueOf(measureValueD.getText()));
        approxE = Double.parseDouble(String.valueOf(measureValueE.getText()));

        hasSinkBar = getCheckRadio(hasBarRadio);
        if (hasSinkBar == 1) {
            hasLeft = getCheckRadio(leftSinkRadio);
            if (hasLeft == 1) {
                if (sinkType == 0) {
                    leftHorA = bundle.getDouble(LEFT_A);
                    leftHorB = bundle.getDouble(LEFT_B);
                    leftHorC = bundle.getDouble(LEFT_C);
                    leftHorD = bundle.getDouble(LEFT_D);
                } else {
                    leftVertA = bundle.getDouble(LEFT_A);
                    leftVertB = bundle.getDouble(LEFT_B);
                    leftVertC = bundle.getDouble(LEFT_C);
                    leftVertD = bundle.getDouble(LEFT_D);
                    leftVertE = bundle.getDouble(LEFT_E);
                }
                leftBarDiam = bundle.getDouble(DIAM_A);
                leftBarDist = bundle.getDouble(DIST_A);
            }
            hasRight = getCheckRadio(rightSinkRadio);
            if (hasRight == 1) {
                if (sinkType == 0 || sinkType == 2) {
                    rightHorA = bundle.getDouble(RIGHT_A);
                    rightHorB = bundle.getDouble(RIGHT_B);
                    rightHorC = bundle.getDouble(RIGHT_C);
                    rightHorD = bundle.getDouble(RIGHT_D);
                } else
                    rightVertA = bundle.getDouble(RIGHT_A);
                rightBarDiam = bundle.getDouble(DIAM_B);
                rightBarDist = bundle.getDouble(DIST_B);
            }
        }


        hasMirror = getCheckRadio(mirrorRadio);
        if (hasMirror == 1) {
            mirrorLow = Double.parseDouble(String.valueOf(mirrorValueA.getText()));
            mirrorHigh = Double.parseDouble(String.valueOf(mirrorValueB.getText()));
        }
        sinkObs = String.valueOf(sinkObsValue.getText());

        return new RestSinkUpdate(sinkBundle.getInt(REST_ID), sinkType, approxA, approxB, approxC, approxD, approxE, hasSinkBar, hasLeft, leftHorA,
                leftHorB, leftHorC, leftHorD, leftVertA, leftVertB, leftVertC, leftVertD, leftVertE, leftBarDiam, leftBarDist, hasRight,
                rightHorA, rightHorB, rightHorC, rightHorD, rightVertA, rightBarDiam, rightBarDist, hasMirror, mirrorLow, mirrorHigh, sinkObs);
    }

    private void saveSinkData(Bundle bundle) {
        RestSinkUpdate sinkData = sinkUpdate(bundle);
        ViewModelEntry.updateRestSinkData(sinkData);
        callUrinalFragment(sinkBundle);
    }

    private void callUrinalFragment(Bundle bundle) {
        RestUrinalFragment uFrag = RestUrinalFragment.newInstance();
        uFrag.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, uFrag).addToBackStack(null).commit();
    }

    private void saveClick(View view) {
        if (getCheckRadio(hasBarRadio) == 1) {
            if (getCheckRadio(leftSinkRadio) > 0) {
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, childBundle);
            } else if (getCheckRadio(rightSinkRadio) > 0) {
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_2, childBundle);
            } else {
                checkEmptyFields();
                toastMessage();
            }

        } else {
            if (checkEmptyFields()) {
                saveSinkData(sinkBundle);
            } else
                toastMessage();
        }
    }

    private void toastMessage() {
        Toast.makeText(getContext(), getText(R.string.blank_fields_message), Toast.LENGTH_SHORT).show();
    }

    private void loadSinkData(RestroomEntry rest) {
        if (rest.getSinkType() != null) {
            switch (rest.getSinkType()) {
                case 4:
                    sinkRadio2.check(sinkRadio2.getChildAt(1).getId());
                    break;
                case 3:
                    sinkRadio2.check(sinkRadio2.getChildAt(0).getId());
                    break;
                default:
                    sinkRadio1.check(sinkRadio1.getChildAt(rest.getSinkType()).getId());
                    break;
            }
        }
        if (rest.getApproxMeasureA() != null)
            measureValueA.setText(String.valueOf(rest.getApproxMeasureA()));
        if (rest.getApproxMeasureB() != null)
            measureValueB.setText(String.valueOf(rest.getApproxMeasureB()));
        if (rest.getApproxMeasureC() != null)
            measureValueC.setText(String.valueOf(rest.getApproxMeasureC()));
        if (rest.getApproxMeasureD() != null)
            measureValueD.setText(String.valueOf(rest.getApproxMeasureD()));
        if (rest.getApproxMeasureE() != null)
            measureValueE.setText(String.valueOf(rest.getApproxMeasureE()));

        if (rest.getHasSinkBar() != null)
            hasBarRadio.check(hasBarRadio.getChildAt(rest.getHasSinkBar()).getId());

        if (rest.getHasLeftBar() != null) {
            leftSinkRadio.check(leftSinkRadio.getChildAt(rest.getHasLeftBar()).getId());
            if (rest.getHasLeftBar() == 1)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, sinkBundle);
        }
        if (rest.getHasRightBar() != null) {
            rightSinkRadio.check(rightSinkRadio.getChildAt(rest.getHasRightBar()).getId());
            if (rest.getHasRightBar() == 1)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA_2, sinkBundle);
        }

        if (rest.getSinkHasMirror() != null)
            mirrorRadio.check(mirrorRadio.getChildAt(rest.getSinkHasMirror()).getId());
        if (rest.getSiMirrorLow() != null)
            mirrorValueA.setText(String.valueOf(rest.getSiMirrorLow()));
        if (rest.getSiMirrorHigh() != null)
            mirrorValueB.setText(String.valueOf(rest.getSiMirrorHigh()));
        if (rest.getSinkObs() != null)
            sinkObsValue.setText(rest.getSinkObs());


    }


}