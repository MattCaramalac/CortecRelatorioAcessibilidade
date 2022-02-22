package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

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
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.GateObsBarrierFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.GateObsDoorFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class GateObsFragment extends Fragment {

    public static final String GATE_OBS_SAVE_ATTEMPT = "GATE_OBS_SAVE_ATTEMPT";
    public static final String GATE_OBS_CHILD_FRAG_DATA = "GATE_OBS_CHILD_FRAG_DATA";
    public static final String GATE_OBS_EMPTY_CHECK = "GATE_OBS_EMPTY_CHECK";
    public static final String GATE_OBS_ID = "GATE_OBS_ID";

    TextInputLayout referencePointField, obsField;
    TextInputEditText referencePointValue, obsValue;
    RadioGroup gateObsTypeRadio;
    Button saveGateObs, cancelGateObs;
    TextView gateObsTypeError;

    ViewModelEntry modelEntry;

    FragmentManager manager;

    Fragment gateChildFrag;

    Bundle obsBundle = new Bundle();

    String referencePoint, obstacleObs;
    Integer accessibleEntrance, accessType;
    Double entranceGateWidth, gateBarrierHeight, gateBarrierWidth;

    public GateObsFragment() {
        // Required empty public constructor
    }

    public static GateObsFragment newInstance() {
        return new GateObsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            obsBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
            obsBundle.putInt(GATE_OBS_ID, this.getArguments().getInt(GATE_OBS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate_obstacle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateGateObsViews(view);
        allowGateObsScroll();

        gateObsTypeRadio.setOnCheckedChangeListener(((group, checkedId) -> {
            int index = getCheckedRadio(group);
            switch (index) {
                case 0:
                    gateChildFrag = new GateObsBarrierFragment();
                    break;
                case 1:
                    gateChildFrag = new GateObsDoorFragment();
                    break;
                default:
                    return;
            }
            gateChildFrag.setArguments(obsBundle);
            getChildFragmentManager().beginTransaction().
                    replace(R.id.gate_obs_type_child_fragment, gateChildFrag).commit();
        }));

        if (obsBundle.getInt(GATE_OBS_ID) != 0)
            modelEntry.getOneGateObsEntry(obsBundle.getInt(GATE_OBS_ID)).observe(getViewLifecycleOwner(), this::loadGateObsData);

        getChildFragmentManager().setFragmentResultListener(GATE_OBS_CHILD_FRAG_DATA, this, (key, bundle) -> {
            if (checkEmptyFields() && bundle.getInt(GATE_OBS_EMPTY_CHECK) != 1) {
                GateObsEntry newObs = newObstacle(obsBundle);
                if (obsBundle.getInt(GATE_OBS_ID) == 0) {
                    ViewModelEntry.insertGateObs(newObs);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else if (obsBundle.getInt(GATE_OBS_ID) > 0) {
                    newObs.setGateObsID(obsBundle.getInt(GATE_OBS_ID));
                    ViewModelEntry.updateGateObs(newObs);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                }
                obsBundle.putInt(GATE_OBS_ID, 0);
                removeObsFragment();
                clearGateObsFields();
            }
        });

        saveGateObs.setOnClickListener(v -> {
            if (getCheckedRadio(gateObsTypeRadio) > -1)
                getChildFragmentManager().setFragmentResult(GATE_OBS_SAVE_ATTEMPT, obsBundle);
            else
                checkEmptyFields();
        });

        cancelGateObs.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    private void instantiateGateObsViews(View view) {
//        TextInputLayout
        referencePointField = view.findViewById(R.id.gate_obstacle_location_field);
        obsField = view.findViewById(R.id.gate_obstacle_obs_field);
//        TextInputEditText
        referencePointValue = view.findViewById(R.id.gate_obstacle_location_value);
        obsValue = view.findViewById(R.id.gate_obstacle_obs_value);
//        RadioGroup
        gateObsTypeRadio = view.findViewById(R.id.gate_obstacle_type_radio);
//        MaterialButton
        saveGateObs = view.findViewById(R.id.save_gate_obstacle);
        cancelGateObs = view.findViewById(R.id.cancel_gate_obstacle);
//        TextView
        gateObsTypeError = view.findViewById(R.id.gate_obstacle_type_error);
//        FragmentManager
        manager = getChildFragmentManager();
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowGateObsScroll() {
        obsValue.setOnTouchListener(this::scrollingField);
    }

    public GateObsEntry newObstacle(Bundle bundle) {
        referencePoint = null;
        accessType = null;
        gateBarrierHeight = null;
        gateBarrierWidth = null;
        entranceGateWidth = null;
        obstacleObs = null;

        if (referencePointValue.getText() != null)
            referencePoint = Objects.requireNonNull(referencePointValue.getText()).toString();

        accessType = getCheckedRadio(gateObsTypeRadio);
            if (accessType == 0) {
                gateBarrierHeight = bundle.getDouble(GateObsBarrierFragment.OBS_BARRIER_HEIGHT);
                gateBarrierWidth = bundle.getDouble(GateObsBarrierFragment.OBS_BARRIER_WIDTH);

            } else if (accessType == 1)
                entranceGateWidth = bundle.getDouble(GateObsDoorFragment.OBS_GATE_WIDTH);

        if (!TextUtils.isEmpty(obsValue.getText()))
            obstacleObs = Objects.requireNonNull(obsValue.getText()).toString();

        return new GateObsEntry(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID), referencePoint, accessibleEntrance, accessType,
                entranceGateWidth, gateBarrierHeight, gateBarrierWidth, obstacleObs);

    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyFields() {
        clearErrorsGateObs();
        int i = 0;
        if (TextUtils.isEmpty(referencePointValue.getText())) {
            referencePointField.setError(getString(R.string.blank_field_error));
            i++;
        }
        if (gateObsTypeRadio.getCheckedRadioButtonId() == -1) {
            gateObsTypeError.setVisibility(View.VISIBLE);
            i++;
        }
        return i == 0;
    }

    public void clearErrorsGateObs() {
        referencePointField.setErrorEnabled(false);
        gateObsTypeError.setVisibility(View.GONE);
    }

    public void clearGateObsFields() {
        referencePointValue.setText(null);
        obsValue.setText(null);
        gateObsTypeRadio.clearCheck();
        removeObsFragment();
    }

    public void removeObsFragment() {
        Fragment gateObstacle = manager.findFragmentById(R.id.gate_obs_type_child_fragment);
        if (gateObstacle != null)
            manager.beginTransaction().remove(gateObstacle).commit();
    }

    public void loadGateObsData(GateObsEntry gateObs){
        referencePointValue.setText(gateObs.getAccessRefPoint());
        gateObsTypeRadio.check(gateObsTypeRadio.getChildAt(gateObs.getAccessType()).getId());
        if (gateObs.getGateObstacleObs() != null)
            obsValue.setText(gateObs.getGateObstacleObs());
    }
}