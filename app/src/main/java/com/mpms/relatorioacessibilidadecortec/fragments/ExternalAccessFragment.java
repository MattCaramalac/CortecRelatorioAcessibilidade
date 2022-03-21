package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessSocialFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessVehicleFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.GateObsListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PayPhoneListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.ArrayList;
import java.util.Arrays;

public class ExternalAccessFragment extends Fragment {

    public static final String EXT_ACCESS_SAVE_ATTEMPT = "EXT_ACCESS_SAVE_ATTEMPT";
    public static final String EXT_ACCESS_ID = "EXT_ACCESS_ID";
    public static final String EXT_ARRAY = "EXT_ARRAY";
    public static final String CHILD_BUTTON_PRESS = "CHILD_BUTTON_PRESS";
    public static final String CHILD_FRAG_DATA = "CHILD_FRAG_DATA";
    public static final String FRAG_DATA = "FRAG_DATA";
    private static final String EXT_SCREEN = "EXT_SCREEN";

    RadioGroup entranceTypeRadio;
    TextInputLayout entranceLocationField, externalAccessObsField;
    TextInputEditText entranceLocationValue, externalAccessObsValue;
    Button saveExternalAccess, cancelExternalAccess;
    TextView accessTypeError;
    Fragment accessType;

    Bundle extBundle = new Bundle();

    ArrayList<String> extFrag = new ArrayList<>(Arrays.
            asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null));

    int newEntry = 1, recentEntry = 0;

    private ViewModelEntry modelEntry;

    private ViewModelFragments modelFragments;

    public ExternalAccessFragment() {
        // Required empty public constructor
    }

    public static ExternalAccessFragment newInstance() {
        return new ExternalAccessFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            extBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
            extBundle.putInt(EXT_ACCESS_ID, this.getArguments().getInt(EXT_ACCESS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_external_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateExternalAccessViews(view);
        allowExternalObsScroll();

        if (extBundle.getInt(EXT_ACCESS_ID) > 0) {
            modelEntry.getOneExternalAccess(extBundle.getInt(EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), this::loadExtAccessInfo);
            newEntry = 0;
        }

        getChildFragmentManager().setFragmentResultListener(InspectionActivity.CHILD_DATA_LISTENER, this, (key, bundle) -> {
            ArrayList<String> childDataArray = bundle.getStringArrayList(CHILD_FRAG_DATA);
            ExternalAccess extAccess = newExtAccess(extBundle, childDataArray);
            if (bundle.getInt(CHILD_BUTTON_PRESS, 0) == 0) {
                if (bundle.getInt(EXT_ACCESS_ID) == 0) {
                    if (checkEmptyFields() && bundle.getBoolean(RoomsRegisterFragment.CHILD_DATA_COMPLETE)) {
                        ViewModelEntry.insertExternalAccess(extAccess);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                        clearExtAccessFields();
                    }
                } else if (bundle.getInt(EXT_ACCESS_ID) > 0) {
                    if (checkEmptyFields() && bundle.getBoolean(RoomsRegisterFragment.CHILD_DATA_COMPLETE)) {
                        extAccess.setExternalAccessID(bundle.getInt(EXT_ACCESS_ID));
                        ViewModelEntry.updateExternalAccess(extAccess);
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                } else {
                    bundle.putInt(EXT_ACCESS_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            } else {
                if (bundle.getInt(EXT_ACCESS_ID) == 0) {
                    ViewModelEntry.insertExternalAccess(extAccess);
                } else if (bundle.getInt(EXT_ACCESS_ID) > 0) {
                    extAccess.setExternalAccessID(bundle.getInt(EXT_ACCESS_ID));
                    ViewModelEntry.updateExternalAccess(extAccess);
                } else {
                    bundle.putInt(EXT_ACCESS_ID, 0);
//                Toast erro
                    return;
                }
                Fragment frag;
                if (bundle.getInt(CHILD_BUTTON_PRESS) == 1)
                    frag = new GateObsListFragment();
                else if (bundle.getInt(CHILD_BUTTON_PRESS) == 2)
                    frag = new PayPhoneListFragment();
                else {
//                    Toast Erro
                    return;
                }
                frag.setArguments(extBundle);
                getParentFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, frag).
                        addToBackStack(EXT_SCREEN).commit();
            }
        });

        cancelExternalAccess.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveExternalAccess.setOnClickListener(v -> {
            extBundle.putStringArrayList(EXT_ARRAY, extFrag);
            getChildFragmentManager().setFragmentResult(EXT_ACCESS_SAVE_ATTEMPT, extBundle);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        modelFragments.getExtAccessLoadInfo().observe(getViewLifecycleOwner(), newAccessID -> {
            if (newAccessID != null && newAccessID > 0) {
                newEntry = 0;
                recentEntry = 1;
                extBundle.putInt(EXT_ACCESS_ID, newAccessID);
                if (!modelEntry.getOneExternalAccess(extBundle.getInt(EXT_ACCESS_ID)).hasActiveObservers())
                    modelEntry.getOneExternalAccess(extBundle.getInt(EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), this::loadExtAccessInfo);
            }
        });
    }

    private void instantiateExternalAccessViews(View view) {
//        TextInputLayout
        entranceLocationField = view.findViewById(R.id.entrance_location_field);
        externalAccessObsField = view.findViewById(R.id.external_access_obs_field);
//        TextInputEditText
        entranceLocationValue = view.findViewById(R.id.entrance_location_value);
        externalAccessObsValue = view.findViewById(R.id.external_access_obs_value);
//        RadioGroup
        entranceTypeRadio = view.findViewById(R.id.external_access_type_radio);
//        TextView
        accessTypeError = view.findViewById(R.id.external_access_type_error);
//        MaterialButton
        saveExternalAccess = view.findViewById(R.id.save_ext_access);
        cancelExternalAccess = view.findViewById(R.id.cancel_ext_access);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);

        entranceTypeRadio.setOnCheckedChangeListener(this::radioGroupActivation);
    }

    private void loadExtAccessInfo(ExternalAccess extAccess) {
        entranceLocationValue.setText(extAccess.getAccessLocation());
        entranceTypeRadio.check(entranceTypeRadio.getChildAt(extAccess.getEntranceType()).getId());
        externalAccessObsValue.setText(extAccess.getExtAccessObs());

        getChildFragmentManager().setFragmentResult(InspectionActivity.LOAD_CHILD_DATA, extBundle);
    }

    private int getRadioCheckIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void radioGroupActivation(RadioGroup radioGroup, int checkedID) {
        int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedID));
        if (index == 1)
            accessType = new ExtAccessVehicleFragment();
        else
            accessType = new ExtAccessSocialFragment();
        accessType.setArguments(extBundle);
        getChildFragmentManager().beginTransaction().replace(R.id.external_access_layout, accessType).commit();
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowExternalObsScroll() {
        externalAccessObsValue.setOnTouchListener(this::scrollingField);
    }

    private boolean checkEmptyFields() {
        clearExternalAccessErrors();
        int i = 0;
        if (TextUtils.isEmpty(entranceLocationValue.getText())) {
            entranceLocationField.setError(getText(R.string.blank_field_error));
            i++;
        }
        if (entranceTypeRadio.getCheckedRadioButtonId() == -1) {
            accessTypeError.setVisibility(View.VISIBLE);
            i++;
        }
        return i == 0;
    }

    private void clearExternalAccessErrors() {
        entranceLocationField.setErrorEnabled(false);
        accessTypeError.setVisibility(View.GONE);
    }

    private void clearExtAccessFields() {
        entranceLocationValue.setText(null);
        entranceTypeRadio.clearCheck();
        externalAccessObsValue.setText(null);
        getChildFragmentManager().beginTransaction().remove(accessType).commit();
        accessType = null;
    }

    private ExternalAccess newExtAccess(Bundle bundle, ArrayList<String> arrayList) {
        String location, extAccessObs, obsSIA, floorType, gateSillObs;
        Integer entranceType = null, hasSIA = null, gateHasTracks = null, gateHasTrackRamp = null, trackRampQnt = null, gateSillType = null,
                gateHasObstacles = null, gateHasPayphone = null, gateHasIntercom = null, gateHasSoundSign = null, slopeMeasureQnt = null;
        Double gateWidth = null, gateTrackHeight = null, trackRamp1 = null, trackRamp2 = null, trackRamp3 = null, trackRamp4 = null,
                sillInclinationHeight = null, sillStepHeight = null, sillSlopeAngle = null, sillSlopeAngle2 = null, sillSlopeAngle3 = null,
                sillSlopeAngle4 = null, sillSlopeWidth = null, intercomHeight = null;

        location = String.valueOf(entranceLocationValue.getText());
        extAccessObs = String.valueOf(externalAccessObsValue.getText());
        entranceType = getRadioCheckIndex(entranceTypeRadio);
        if (arrayList.get(0) != null)
            hasSIA = Integer.valueOf(arrayList.get(0));
        obsSIA = arrayList.get(1);
        floorType = arrayList.get(2);
        if (arrayList.get(3) != null)
            gateWidth = Double.valueOf(arrayList.get(3));
        if (arrayList.get(4) != null)
            gateHasTracks = Integer.valueOf(arrayList.get(4));
        if (arrayList.get(5) != null)
            gateTrackHeight = Double.valueOf(arrayList.get(5));
        if (arrayList.get(6) != null)
            gateHasTrackRamp = Integer.valueOf(arrayList.get(6));
        if (arrayList.get(7) != null && !TextUtils.equals(arrayList.get(20), ""))
            trackRampQnt = Integer.valueOf(arrayList.get(20));
        if (arrayList.get(8) != null && !TextUtils.equals(arrayList.get(21), ""))
            trackRamp1 = Double.valueOf(arrayList.get(21));
        if (arrayList.get(9) != null && !TextUtils.equals(arrayList.get(22), ""))
            trackRamp2 = Double.valueOf(arrayList.get(22));
        if (arrayList.get(10) != null && !TextUtils.equals(arrayList.get(23), ""))
            trackRamp3 = Double.valueOf(arrayList.get(23));
        if (arrayList.get(11) != null && !TextUtils.equals(arrayList.get(24), ""))
            trackRamp4 = Double.valueOf(arrayList.get(24));
        if (arrayList.get(12) != null)
            gateSillType = Integer.valueOf(arrayList.get(7));

        if (gateSillType != null) {
            switch (gateSillType) {
                case 3:
                    slopeMeasureQnt = bundle.getInt(SillSlopeFragment.SLOPE_QNT);
                    switch (slopeMeasureQnt) {
                        case 4:
                            sillSlopeAngle4 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_4);
                        case 3:
                            sillSlopeAngle3 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_3);
                        case 2:
                            sillSlopeAngle2 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_2);
                        case 1:
                            sillSlopeAngle = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_1);
                            sillSlopeWidth = bundle.getDouble(SillSlopeFragment.SLOPE_WIDTH);
                        default:
                            break;
                    }

                    break;
                case 2:
                    sillStepHeight = bundle.getDouble(SillStepFragment.STEP_HEIGHT);
                    break;
                case 1:
                    sillInclinationHeight = bundle.getDouble(SillInclinationFragment.HEIGHT_INCLINED_SILL);
                    break;
                default:
                    break;
            }
        }

        gateSillObs = arrayList.get(13);
        if (arrayList.get(14) != null)
            gateHasObstacles = Integer.valueOf(arrayList.get(13));
        if (arrayList.get(15) != null)
            gateHasPayphone = Integer.valueOf(arrayList.get(14));
        if (arrayList.get(16) != null)
            gateHasIntercom = Integer.valueOf(arrayList.get(15));
        if (arrayList.get(17) != null)
            intercomHeight = Double.valueOf(arrayList.get(16));
        if (arrayList.get(18) != null)
            gateHasSoundSign = Integer.valueOf(arrayList.get(17));

        return new ExternalAccess(bundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER), location, entranceType, extAccessObs, hasSIA, obsSIA, floorType, gateWidth,
                gateHasTracks, gateTrackHeight, gateHasTrackRamp, trackRampQnt, trackRamp1, trackRamp2, trackRamp3, trackRamp4, gateSillType, sillInclinationHeight,
                sillStepHeight, sillSlopeAngle, sillSlopeWidth, gateSillObs, gateHasObstacles, gateHasPayphone, gateHasIntercom, intercomHeight, gateHasSoundSign, slopeMeasureQnt,
                sillSlopeAngle2, sillSlopeAngle3, sillSlopeAngle4);
    }

}
