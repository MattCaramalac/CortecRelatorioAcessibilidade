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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.HorSinkBarParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SinkParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.VertSinkBarParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkHorLeftBarFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkHorRightBarFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkVertFrontalBarFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SinkVertSideBarFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class RestSinkFragment extends Fragment implements TagInterface, ScrollEditText {

    ImageButton sink1, sink2, sink3, sink4, sink5, sinkApprox, sinkMirror;
    RadioGroup sinkRadio1, sinkRadio2, hasBarRadio, frontLeftHorSinkRadio, sideRightVertSinkRadio, mirrorRadio;
    TextInputLayout measureFieldA, measureFieldB, measureFieldC, measureFieldD, measureFieldE, mirrorFieldA, mirrorFieldB, sinkObsField;
    TextInputEditText measureValueA, measureValueB, measureValueC, measureValueD, measureValueE, mirrorValueA, mirrorValueB, sinkObsValue;
    TextView sinkError, barError, frontLeftHorHeader, sideRightVertHeader, frontLeftHorError, sideRightVertError, mirrorError, sinkTypeWarning;
    FrameLayout leftFrag, rightFrag;
    Fragment frontLeftHorFrag, sideRightVertFrag;

    Bundle sinkBundle, imgBundle, childBundle;
    ViewModelEntry modelEntry;

    boolean leftData = true, rightData = true, check1 = true, check2 = true;
    int sinkType;

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

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, getViewLifecycleOwner(), (key, bundle) -> {
            if (getCheckRadio(hasBarRadio) == 1) {
                if (getCheckRadio(frontLeftHorSinkRadio) > 0) {
                    getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_2, childBundle);
                } else if (getCheckRadio(sideRightVertSinkRadio) > 0) {
                    getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_3, childBundle);
                } else {
                    checkEmptyFields();
                    Toast.makeText(getContext(), "Cadastre pelo menos uma das barras de apoio do lavatório ou mude a opção selecionada", Toast.LENGTH_LONG).show();
                }
            } else {
                if (checkEmptyFields()) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    sendSinkData(bundle);
                } else {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                    getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
                }
            }
        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_2, getViewLifecycleOwner(), (key, bundle) -> {
            leftData = bundle.getBoolean(CHILD_DATA_COMPLETE_2);
            if (getCheckRadio(sideRightVertSinkRadio) > 0)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA_3, bundle);
            else {
                if (checkEmptyFields()) {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                    sendSinkData(bundle);
                }
                else {
                    bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                    getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
                }
                leftData = true;
                rightData = true;
            }

        });

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER_3, getViewLifecycleOwner(), (key, bundle) -> {
            rightData = bundle.getBoolean(CHILD_DATA_COMPLETE_3);
            if (checkEmptyFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                sendSinkData(bundle);
            }
            else {
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
                getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
            }
            leftData = true;
            rightData = true;
        });

        if (sinkBundle.getInt(BOX_ID) > 0)
            modelEntry.getBoxSinkData(sinkBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxSinkData);
        else if (sinkBundle.getInt(REST_ID) > 0)
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
        frontLeftHorSinkRadio = view.findViewById(R.id.front_left_bar_radio);
        sideRightVertSinkRadio = view.findViewById(R.id.side_right_bar_radio);
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
        frontLeftHorHeader = view.findViewById(R.id.sink_front_left_bar_header);
        sideRightVertHeader = view.findViewById(R.id.sink_side_right_bar_header);
        frontLeftHorError = view.findViewById(R.id.front_left_bar_error);
        sideRightVertError = view.findViewById(R.id.side_right_bar_error);
        mirrorError = view.findViewById(R.id.sink_mirror_error);
        sinkTypeWarning = view.findViewById(R.id.select_sink_type_warning);
//        FrameLayout
        leftFrag = view.findViewById(R.id.front_left_bar_fragment);
        rightFrag = view.findViewById(R.id.side_right_bar_fragment);
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
        frontLeftHorSinkRadio.setOnCheckedChangeListener(this::radioListener);
        sideRightVertSinkRadio.setOnCheckedChangeListener(this::radioListener);
        mirrorRadio.setOnCheckedChangeListener(this::radioListener);

        allowObsScroll(sinkObsValue);
    }

    private void imgExpandClick(View view) {
        if (view == sink1)
            imgBundle.putInt(IMAGE_ID, R.drawable.sinkhorbar);
        else if (view == sink2)
            imgBundle.putInt(IMAGE_ID, R.drawable.sinkvertbar);
        else if (view == sink3)
            imgBundle.putInt(IMAGE_ID, R.drawable.sinkhorvertbar);
        else if (view == sink4)
            imgBundle.putInt(IMAGE_ID, R.drawable.sinkcorner);
        else if (view == sink5)
            imgBundle.putInt(IMAGE_ID, R.drawable.sinksemifit);
        else if (view == sinkApprox)
            imgBundle.putInt(IMAGE_ID, R.drawable.sinkapprox);
        else if (view == sinkMirror)
            imgBundle.putInt(IMAGE_ID, R.drawable.sinkmirror);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);

    }

    private int getCheckRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private String[] setSinkBarRadioText(int sinkType) {
        String[] headers = new String[2];
        switch (sinkType) {
            case 0:
                headers[0] = "O lavatório possui barra de apoio à esquerda?";
                headers[1] = "O lavatório possui barra de apoio à direita?";
                break;
            case 2:
                headers[0] = "O lavatório possui barra de apoio horizontal?";
                headers[1] = "O lavatório possui barra de apoio vertical?";
                break;
            default:
                headers[0] = "O lavatório possui barra de apoio frontal?";
                headers[1] = "O lavatório possui barra de apoio lateral?";
                break;
        }
        return headers;
    }

    private void radioListener(RadioGroup radio, int check) {
        int radioIndex = getCheckRadio(radio);

        if (radio == sinkRadio1 && check1) {
            check2 = false;
            sinkRadio2.clearCheck();
            sinkType = radioIndex;
            frontLeftHorSinkRadio.clearCheck();
            sideRightVertSinkRadio.clearCheck();
            hasBarRadio.clearCheck();
            check2 = true;
            if (getCheckRadio(hasBarRadio) == 1) {
                sinkTypeWarning.setVisibility(View.GONE);
                String[] headerText = setSinkBarRadioText(sinkType);
                frontLeftHorHeader.setVisibility(View.VISIBLE);
                frontLeftHorHeader.setText(headerText[0]);
                sideRightVertHeader.setVisibility(View.VISIBLE);
                sideRightVertHeader.setText(headerText[1]);
                frontLeftHorSinkRadio.setVisibility(View.VISIBLE);
                sideRightVertSinkRadio.setVisibility(View.VISIBLE);
            }
        } else if (radio == sinkRadio2 && check2) {
            check1 = false;
            sinkRadio1.clearCheck();
            sinkType = (radioIndex == 0) ? 3 : 4;
            frontLeftHorSinkRadio.clearCheck();
            sideRightVertSinkRadio.clearCheck();
            hasBarRadio.clearCheck();
            check1 = true;
            if (getCheckRadio(hasBarRadio) == 1) {
                sinkTypeWarning.setVisibility(View.GONE);
                String[] headerText = setSinkBarRadioText(sinkType);
                frontLeftHorHeader.setVisibility(View.VISIBLE);
                frontLeftHorHeader.setText(headerText[0]);
                sideRightVertHeader.setVisibility(View.VISIBLE);
                sideRightVertHeader.setText(headerText[1]);
                frontLeftHorSinkRadio.setVisibility(View.VISIBLE);
                sideRightVertSinkRadio.setVisibility(View.VISIBLE);
            }
        } else if (radio == hasBarRadio) {
            if (radioIndex == 1 && (getCheckRadio(sinkRadio1) != -1 || getCheckRadio(sinkRadio2) != -1)) {
                sinkTypeWarning.setVisibility(View.GONE);
                String[] headerText = setSinkBarRadioText(sinkType);
                frontLeftHorHeader.setVisibility(View.VISIBLE);
                frontLeftHorHeader.setText(headerText[0]);
                sideRightVertHeader.setVisibility(View.VISIBLE);
                sideRightVertHeader.setText(headerText[1]);
                frontLeftHorSinkRadio.setVisibility(View.VISIBLE);
                sideRightVertSinkRadio.setVisibility(View.VISIBLE);
            } else if (radioIndex == 1 && getCheckRadio(sinkRadio1) == -1 && getCheckRadio(sinkRadio2) == -1) {
                sinkTypeWarning.setVisibility(View.VISIBLE);
            } else {
                sinkTypeWarning.setVisibility(View.GONE);
                frontLeftHorSinkRadio.clearCheck();
                sideRightVertSinkRadio.clearCheck();
                frontLeftHorHeader.setVisibility(View.GONE);
                sideRightVertHeader.setVisibility(View.GONE);
                frontLeftHorSinkRadio.setVisibility(View.GONE);
                sideRightVertSinkRadio.setVisibility(View.GONE);
                removeChildFragments();
            }
        } else if (radio == frontLeftHorSinkRadio) {
            if (radioIndex == 1) {
                if (sinkType == 0 || sinkType == 2) {
                    frontLeftHorFrag = new SinkHorLeftBarFragment();
                    frontLeftHorFrag.setArguments(sinkBundle);
                } else
                    frontLeftHorFrag = SinkVertFrontalBarFragment.newInstance(sinkBundle, sinkType);
                getChildFragmentManager().beginTransaction().replace(R.id.front_left_bar_fragment, frontLeftHorFrag).commit();
            } else {
                if (frontLeftHorFrag != null)
                    getChildFragmentManager().beginTransaction().remove(frontLeftHorFrag).commit();
            }
        } else if (radio == sideRightVertSinkRadio) {
            if (radioIndex == 1) {
                if (sinkType == 0) {
                    sideRightVertFrag = new SinkHorRightBarFragment();
                    sideRightVertFrag.setArguments(sinkBundle);
                }
                else
                    sideRightVertFrag = SinkVertSideBarFragment.newInstance(sinkBundle, sinkType);
                getChildFragmentManager().beginTransaction().replace(R.id.side_right_bar_fragment, sideRightVertFrag).commit();
            } else {
                if (sideRightVertFrag != null)
                    getChildFragmentManager().beginTransaction().remove(sideRightVertFrag).commit();
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
        if (frontLeftHorFrag != null)
            getChildFragmentManager().beginTransaction().remove(frontLeftHorFrag).commit();
        if (sideRightVertFrag != null)
            getChildFragmentManager().beginTransaction().remove(sideRightVertFrag).commit();
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
            if (getCheckRadio(frontLeftHorSinkRadio) == -1) {
                i++;
                frontLeftHorError.setVisibility(View.VISIBLE);
            }
            if (getCheckRadio(sideRightVertSinkRadio) == -1) {
                i++;
                sideRightVertError.setVisibility(View.VISIBLE);
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
        frontLeftHorError.setVisibility(View.GONE);
        sideRightVertError.setVisibility(View.GONE);
        mirrorError.setVisibility(View.GONE);
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
        measureFieldC.setErrorEnabled(false);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
        mirrorFieldA.setErrorEnabled(false);
        mirrorFieldB.setErrorEnabled(false);
    }

    private SinkParcel sinkUpdate(Bundle bundle) {
        int sinkType = 0, hasSinkBar, hasMirror;
        Integer hasLeftFrontHor = null, hasSideRightVert = null;
        double approxA, approxB, approxC, approxD, approxE;
        Double leftFrontHorA = null, leftFrontHorB = null, leftFrontHorC = null, leftFrontHorD = null, leftFrontHorDiam = null,
                leftFrontHorDist = null, rightSideVertA = null, rightSideVertB = null, rightSideVertC = null, rightSideVertD = null, rightSideVertE = null,
                rightSideVertDiam = null, rightSideVertDist = null, mirrorLow = null, mirrorHigh = null;
        String leftFrontHorObs = null, rightSideVertObs = null, sinkObs = null;

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
            hasLeftFrontHor = getCheckRadio(frontLeftHorSinkRadio);
            if (hasLeftFrontHor == 1) {
                if (sinkType == 0 || sinkType == 2) {
                    HorSinkBarParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_2));

                    leftFrontHorA = parcel.getHorMeasureA();
                    leftFrontHorB = parcel.getHorMeasureB();
                    leftFrontHorC = parcel.getHorMeasureC();
                    leftFrontHorD = parcel.getHorMeasureD();
                    leftFrontHorDiam = parcel.getHorDiam();
                    leftFrontHorObs = parcel.getHorObs();

                } else {
                    VertSinkBarParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_2));

                    leftFrontHorA = parcel.getMeasureA();
                    leftFrontHorB = parcel.getMeasureB();
                    leftFrontHorC = parcel.getMeasureC();
                    leftFrontHorDiam = parcel.getDiam();
                    leftFrontHorDist = parcel.getDiam();
                    leftFrontHorObs = parcel.getObs();
                }
            }
            hasSideRightVert = getCheckRadio(sideRightVertSinkRadio);
            if (hasSideRightVert == 1) {
                if (sinkType == 0) {
                    HorSinkBarParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_3));

                    rightSideVertA = parcel.getHorMeasureA();
                    rightSideVertB = parcel.getHorMeasureB();
                    rightSideVertC = parcel.getHorMeasureC();
                    rightSideVertD = parcel.getHorMeasureD();
                    rightSideVertDiam = parcel.getHorDiam();
                    rightSideVertObs = parcel.getHorObs();

                } else {
                    VertSinkBarParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL_3));

                    rightSideVertA = parcel.getMeasureA();
                    rightSideVertB = parcel.getMeasureB();
                    rightSideVertC = parcel.getMeasureC();
                    rightSideVertD = parcel.getMeasureD();
                    rightSideVertE = parcel.getMeasureE();
                    rightSideVertDiam = parcel.getDiam();
                    rightSideVertDist = parcel.getDist();
                    rightSideVertObs = parcel.getObs();
                }
            }
        }


        hasMirror = getCheckRadio(mirrorRadio);
        if (hasMirror == 1) {
            mirrorLow = Double.parseDouble(String.valueOf(mirrorValueA.getText()));
            mirrorHigh = Double.parseDouble(String.valueOf(mirrorValueB.getText()));
        }
        if (!TextUtils.isEmpty(sinkObsValue.getText()))
            sinkObs = String.valueOf(sinkObsValue.getText());

        return new SinkParcel(sinkType, approxA, approxB, approxC, approxD, approxE, hasSinkBar, hasLeftFrontHor, leftFrontHorA,
                leftFrontHorB, leftFrontHorC, leftFrontHorD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasSideRightVert, rightSideVertA, rightSideVertB,
                rightSideVertC, rightSideVertD, rightSideVertE, rightSideVertDiam, rightSideVertDist, rightSideVertObs, hasMirror, mirrorLow, mirrorHigh, sinkObs);
    }

    private void sendSinkData(Bundle bundle) {
        SinkParcel sinkData = sinkUpdate(bundle);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(sinkData));
        getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
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

        if (rest.getHasLeftFrontHorBar() != null)
            frontLeftHorSinkRadio.check(frontLeftHorSinkRadio.getChildAt(rest.getHasLeftFrontHorBar()).getId());

        if (rest.getHasRightSideVertBar() != null)
            sideRightVertSinkRadio.check(sideRightVertSinkRadio.getChildAt(rest.getHasRightSideVertBar()).getId());

        if (rest.getSinkHasMirror() != null)
            mirrorRadio.check(mirrorRadio.getChildAt(rest.getSinkHasMirror()).getId());
        if (rest.getSinkMirrorLow() != null)
            mirrorValueA.setText(String.valueOf(rest.getSinkMirrorLow()));
        if (rest.getSinkMirrorHigh() != null)
            mirrorValueB.setText(String.valueOf(rest.getSinkMirrorHigh()));
        if (rest.getSinkObs() != null)
            sinkObsValue.setText(rest.getSinkObs());
    }

    private void loadBoxSinkData(RestBoxEntry rest) {
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

        if (rest.getHasLeftFrontHorBar() != null)
            frontLeftHorSinkRadio.check(frontLeftHorSinkRadio.getChildAt(rest.getHasLeftFrontHorBar()).getId());

        if (rest.getHasRightSideVertBar() != null)
            sideRightVertSinkRadio.check(sideRightVertSinkRadio.getChildAt(rest.getHasRightSideVertBar()).getId());

        if (rest.getSinkHasMirror() != null)
            mirrorRadio.check(mirrorRadio.getChildAt(rest.getSinkHasMirror()).getId());
        if (rest.getSinkMirrorLow() != null)
            mirrorValueA.setText(String.valueOf(rest.getSinkMirrorLow()));
        if (rest.getSinkMirrorHigh() != null)
            mirrorValueB.setText(String.valueOf(rest.getSinkMirrorHigh()));
        if (rest.getSinkObs() != null)
            sinkObsValue.setText(rest.getSinkObs());
    }

}