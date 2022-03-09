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
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessSocialFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessVehicleFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.GateObsListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PayPhoneListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ExternalAccessFragment extends Fragment {

    public static final String EXT_ACCESS_SAVE_ATTEMPT = "EXT_ACCESS_SAVE_ATTEMPT";
    public static final String EXT_ACCESS_ID = "EXT_ACCESS_ID";
    public static final String EXT_ARRAY = "EXT_ARRAY";
    public static final String EXT_SCREEN = "EXT_SCREEN";
    public static final String CHILD_FRAG_DATA = "CHILD_FRAG_DATA";
    public static final String FRAG_DATA = "FRAG_DATA";

    RadioGroup entranceTypeRadio;
    TextInputLayout entranceLocationField, externalAccessObsField;
    TextInputEditText entranceLocationValue, externalAccessObsValue;
    Button saveExternalAccess, cancelExternalAccess;
    TextView accessTypeError;
    Fragment accessType;

    Bundle extBundle = new Bundle();
    Bundle fragComm = new Bundle();

    Fragment substitutionFrag;

    ArrayList<String> fragData = new ArrayList<>();

    ArrayList<String> extFrag = new ArrayList<>(Arrays.
            asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null));

    int existingEntry = 0;

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
        modelFragments.setExtAccessLoadInfo(extBundle);

        if (extBundle.getInt(EXT_ACCESS_ID) > 0) {
            modelEntry.getOneExternalAccess(extBundle.getInt(EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), this::gatherExtAccessInfo);
            existingEntry++;
        }

//        TODO - Resolver o carregamento incorreto de dados após salvar novo registro que clicou nos botões
        modelFragments.getExtAccessLoadInfo().observe(getViewLifecycleOwner(), tempDataBundle -> {
            if (tempDataBundle != null && tempDataBundle.getInt(EXT_ACCESS_ID) != 0) {
                extBundle.putInt(EXT_ACCESS_ID, tempDataBundle.getInt(EXT_ACCESS_ID));
                modelEntry.getOneExternalAccess(tempDataBundle.getInt(EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), this::gatherExtAccessInfo);
            }
        });

        getChildFragmentManager().setFragmentResultListener(FRAG_DATA, this, (key, bundle) -> {
            fragData = bundle.getStringArrayList(CHILD_FRAG_DATA);
            checkEmptyFields(fragData);
            ExternalAccess extAccess = newExtAccess(extBundle, fragData);
            if (extBundle.getInt(EXT_ACCESS_ID) != 0) {
                if (!Objects.equals(fragData.get(18), "false")) {
                    extAccess.setExternalAccessID(extBundle.getInt(EXT_ACCESS_ID));
                    ViewModelEntry.updateExternalAccess(extAccess);
                    clearExtAccessFields();
                    modelFragments.setExtAccessLoadInfo(null);
                    extBundle.putInt(EXT_ACCESS_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    if (existingEntry > 0)
                        requireActivity().getSupportFragmentManager().popBackStackImmediate();
                }
            } else {
                if (!Objects.equals(fragData.get(18), "false")) {
                    ViewModelEntry.insertExternalAccess(extAccess);
                    clearExtAccessFields();
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    modelFragments.setExtAccessLoadInfo(null);
                } else {
                    extFrag.set(18, null);
                }
            }
        });

        getChildFragmentManager().setFragmentResultListener(ExtAccessSocialFragment.TEMP_SOCIAL_FRAG, this, (key, bundle) -> {
            ArrayList<String> tempSocialDataArray = bundle.getStringArrayList(ExtAccessSocialFragment.TEMP_FRAG_DATA);
            if (extBundle.getInt(EXT_ACCESS_ID) == 0) {
                ExternalAccess tempAccess = newExtAccess(extBundle, tempSocialDataArray);
                ViewModelEntry.insertExternalAccess(tempAccess);
                clearExtAccessFields();
            }
            modelFragments.setExtAccessLoadInfo(bundle);
            if (tempSocialDataArray.get(19).equals("1")) {
                substitutionFrag = new GateObsListFragment();
            } else if (tempSocialDataArray.get(19).equals("2"))
                substitutionFrag = new PayPhoneListFragment();
            else
                return;
            substitutionFrag.setArguments(extBundle);
            getParentFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, substitutionFrag).
                    addToBackStack(EXT_SCREEN).commit();
        });

        cancelExternalAccess.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveExternalAccess.setOnClickListener(v -> {
            fragComm.putStringArrayList(EXT_ARRAY, extFrag);
            getChildFragmentManager().setFragmentResult(EXT_ACCESS_SAVE_ATTEMPT, fragComm);
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

    private void gatherExtAccessInfo(ExternalAccess extAccess) {
        entranceLocationValue.setText(extAccess.getAccessLocation());
        entranceTypeRadio.check(entranceTypeRadio.getChildAt(extAccess.getEntranceType()).getId());
        externalAccessObsValue.setText(extAccess.getExtAccessObs());
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

    private void checkEmptyFields(ArrayList<String> array) {
        clearExternalAccessErrors();
        if (TextUtils.isEmpty(entranceLocationValue.getText())) {
            entranceLocationField.setError(getText(R.string.blank_field_error));
            array.set(18, "false");
        }
        if (entranceTypeRadio.getCheckedRadioButtonId() == -1) {
            accessTypeError.setVisibility(View.VISIBLE);
            array.set(18, "false");
        }
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
        Integer entranceType, hasSIA = null, gateHasTracks = null, gateHasTrackRamp = null, trackRampQnt = null, gateSillType = null,
                gateHasObstacles = null, gateHasPayphone = null, gateHasIntercom = null, gateHasSoundSign = null;
        Double gateWidth = null, gateTrackHeight = null, trackRamp1 = null, trackRamp2 = null, trackRamp3 = null, trackRamp4 = null,
                sillInclinationHeight = null, sillStepHeight = null, sillSlopeAngle = null, sillSlopeWidth = null, intercomHeight = null;

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
        if (arrayList.get(20) != null && !TextUtils.equals(arrayList.get(20), ""))
            trackRampQnt = Integer.valueOf(arrayList.get(20));
        if (arrayList.get(21) != null && !TextUtils.equals(arrayList.get(21), ""))
            trackRamp1 = Double.valueOf(arrayList.get(21));
        if (arrayList.get(22) != null && !TextUtils.equals(arrayList.get(22), ""))
            trackRamp2 = Double.valueOf(arrayList.get(22));
        if (arrayList.get(23) != null && !TextUtils.equals(arrayList.get(23), ""))
            trackRamp3 = Double.valueOf(arrayList.get(23));
        if (arrayList.get(24) != null && !TextUtils.equals(arrayList.get(24), ""))
            trackRamp4 = Double.valueOf(arrayList.get(24));
        if (arrayList.get(7) != null)
            gateSillType = Integer.valueOf(arrayList.get(7));
        if (arrayList.get(8) != null)
            sillInclinationHeight = Double.valueOf(arrayList.get(8));
        if (arrayList.get(9) != null)
            sillStepHeight = Double.valueOf(arrayList.get(9));
        if (arrayList.get(10) != null)
            sillSlopeAngle = Double.valueOf(arrayList.get(10));
        if (arrayList.get(11) != null)
            sillSlopeWidth = Double.valueOf(arrayList.get(11));
        gateSillObs = arrayList.get(12);
        if (arrayList.get(13) != null)
            gateHasObstacles = Integer.valueOf(arrayList.get(13));
        if (arrayList.get(14) != null)
            gateHasPayphone = Integer.valueOf(arrayList.get(14));
        if (arrayList.get(15) != null)
            gateHasIntercom = Integer.valueOf(arrayList.get(15));
        if (arrayList.get(16) != null)
            intercomHeight = Double.valueOf(arrayList.get(16));
        if (arrayList.get(17) != null)
            gateHasSoundSign = Integer.valueOf(arrayList.get(17));

        return new ExternalAccess(bundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER), location, entranceType, extAccessObs, hasSIA, obsSIA, floorType, gateWidth,
                gateHasTracks, gateTrackHeight, gateHasTrackRamp, trackRampQnt, trackRamp1, trackRamp2, trackRamp3, trackRamp4, gateSillType, sillInclinationHeight,
                sillStepHeight, sillSlopeAngle, sillSlopeWidth, gateSillObs, gateHasObstacles, gateHasPayphone, gateHasIntercom, intercomHeight, gateHasSoundSign);
    }

}
