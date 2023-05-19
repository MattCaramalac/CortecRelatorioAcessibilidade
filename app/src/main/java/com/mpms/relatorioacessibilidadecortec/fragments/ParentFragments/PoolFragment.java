package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntryOne;
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PoolFragment extends Fragment implements RadioGroupInterface, TagInterface, ScrollEditText {

    TextInputLayout poolLocalField, poolAreaObsField, waterFlowObsField, accessFloorObsField, showerObsField, fenceHeightField, fenceGapField, gateLockHeightField, gateObsField,
            photoField;
    TextInputEditText poolLocalValue, poolAreaObsValue, waterFlowObsValue, accessFloorObsValue, showerObsValue, fenceHeightValue, fenceGapValue, gateLockHeightValue, gateObsValue,
            photoValue;
    RadioGroup aroundPoolRadio, waterFlowRadio, accessFloorRadio, showerRadio, accessShowerRadio, fenceRadio, gateLockRadio, antiRustRadio, seeFenceRadio;
    TextView aroundPoolError, waterFlowError, accessFloorError, showerError, accessShowerHeader, accessShowerError, fenceError, gateLockHeader, gateLockError, antiRustHeader,
            antiRustError, seeFenceHeader, seeFenceError;
    MaterialButton proceedPool, returnList;
    ViewModelEntry modelEntry;
    InspectionViewModel dataView;
    Bundle poolBundle;

    ArrayList<TextInputEditText> poolObsArray = new ArrayList<>();

    public PoolFragment() {
        // Required empty public constructor
    }

    public static PoolFragment newInstance() {
        return new PoolFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            poolBundle = new Bundle(this.getArguments());
        else
            poolBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (poolBundle.getBoolean(RECENT_ENTRY))
                    cancelClick();
                else {
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
                setEnabled(true);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pool, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiatePoolViews(view);

        if (poolBundle.getInt(POOL_ID) > 0)
            modelEntry.getPool(poolBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), this::loadPoolOneData);

        returnList.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        proceedPool.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                if (poolBundle.getInt(POOL_ID) == 0) {
                    PoolEntry newPool = createPool(poolBundle);
                    modelEntry.insertPool(newPool);
                    modelEntry.getLastPoolEntry().observe(getViewLifecycleOwner(), pool -> {
                        poolBundle.putInt(POOL_ID, pool.getPoolID());
                        callNextPoolFragment(poolBundle);
                    });
                } else if (poolBundle.getInt(POOL_ID) > 0) {
                    PoolEntryOne upPool = updatePool(poolBundle);
                    modelEntry.updatePoolOne(upPool);
                    callNextPoolFragment(poolBundle);
                } else {
                    poolBundle.putInt(POOL_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });
    }

    private void instantiatePoolViews(View view) {
//        TextInputLayout
        poolLocalField = view.findViewById(R.id.location_pool_field);
        poolAreaObsField = view.findViewById(R.id.allow_pool_circulation_obs_field);
        waterFlowObsField = view.findViewById(R.id.water_flow_allowed_obs_field);
        accessFloorObsField = view.findViewById(R.id.pool_accessible_floor_obs_field);
        showerObsField = view.findViewById(R.id.accessible_hygiene_obs_field);
        fenceHeightField = view.findViewById(R.id.pool_fence_height_field);
        fenceGapField = view.findViewById(R.id.pool_fence_gap_field);
        gateLockHeightField = view.findViewById(R.id.gate_activation_height_field);
        gateObsField = view.findViewById(R.id.isolation_obs_field);
        photoField = view.findViewById(R.id.pool_photo_field_1);
//        TextInputEditText
        poolLocalValue = view.findViewById(R.id.location_pool_value);
        poolAreaObsValue = view.findViewById(R.id.allow_pool_circulation_obs_value);
        waterFlowObsValue = view.findViewById(R.id.water_flow_allowed_obs_value);
        accessFloorObsValue = view.findViewById(R.id.pool_accessible_floor_obs_value);
        showerObsValue = view.findViewById(R.id.accessible_hygiene_obs_value);
        fenceHeightValue = view.findViewById(R.id.pool_fence_height_value);
        fenceGapValue = view.findViewById(R.id.pool_fence_gap_value);
        gateLockHeightValue = view.findViewById(R.id.gate_activation_height_value);
        gateObsValue = view.findViewById(R.id.isolation_obs_value);
        photoValue = view.findViewById(R.id.pool_photo_value_1);

        poolObsArray.add(poolAreaObsValue);
        poolObsArray.add(waterFlowObsValue);
        poolObsArray.add(accessFloorObsValue);
        poolObsArray.add(showerObsValue);
        poolObsArray.add(gateObsValue);
        allowObsScroll(poolObsArray);

//        RadioGroup
        aroundPoolRadio = view.findViewById(R.id.allow_pool_circulation_radio);
        waterFlowRadio = view.findViewById(R.id.water_flow_allowed_radio);
        accessFloorRadio = view.findViewById(R.id.pool_accessible_floor_radio);
        showerRadio = view.findViewById(R.id.pool_hygiene_radio);
        accessShowerRadio = view.findViewById(R.id.accessible_hygiene_radio);
        fenceRadio = view.findViewById(R.id.pool_has_isolation_radio);
        gateLockRadio = view.findViewById(R.id.isolation_has_gate_radio);
        antiRustRadio = view.findViewById(R.id.isolation_anti_rust_radio);
        seeFenceRadio = view.findViewById(R.id.isolation_see_through_radio);
        showerRadio.setOnCheckedChangeListener(this::radioListener);
        fenceRadio.setOnCheckedChangeListener(this::radioListener);
        gateLockRadio.setOnCheckedChangeListener(this::radioListener);
//        TextView
        aroundPoolError = view.findViewById(R.id.allow_pool_circulation_error);
        waterFlowError = view.findViewById(R.id.water_flow_allowed_error);
        accessFloorError = view.findViewById(R.id.pool_accessible_floor_error);
        showerError = view.findViewById(R.id.pool_hygiene_error);
        accessShowerHeader = view.findViewById(R.id.accessible_hygiene_header);
        accessShowerError = view.findViewById(R.id.accessible_hygiene_error);
        fenceError = view.findViewById(R.id.pool_has_isolation_error);
        gateLockHeader = view.findViewById(R.id.isolation_has_gate_header);
        gateLockError = view.findViewById(R.id.isolation_has_gate_error);
        antiRustHeader = view.findViewById(R.id.isolation_anti_rust_header);
        antiRustError = view.findViewById(R.id.isolation_anti_rust_error);
        seeFenceHeader = view.findViewById(R.id.isolation_see_through_header);
        seeFenceError = view.findViewById(R.id.isolation_see_through_error);
//        MaterialButton
        proceedPool = view.findViewById(R.id.next_pool_screen);
        returnList = view.findViewById(R.id.cancel_pool);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        dataView = new ViewModelProvider(requireActivity()).get(InspectionViewModel.class);
        dataView.setVisible(false);
    }

    private void loadPoolOneData(PoolEntry pool) {
        if (pool.getPoolLocation() != null)
            poolLocalValue.setText(pool.getPoolLocation());
        if (pool.getAllowPoolAccess() != null)
            checkRadioGroup(aroundPoolRadio, pool.getAllowPoolAccess());
        if (pool.getPoolAccessObs() != null)
            poolAreaObsValue.setText(pool.getPoolAccessObs());
        if (pool.getAllowWaterFlow() != null)
            checkRadioGroup(waterFlowRadio, pool.getAllowWaterFlow());
        if (pool.getWaterFlowObs() != null)
            waterFlowObsValue.setText(pool.getWaterFlowObs());
        if (pool.getFloorAccessible() != null)
            checkRadioGroup(accessFloorRadio, pool.getFloorAccessible());
        if (pool.getFloorAccessObs() != null)
            accessFloorObsValue.setText(pool.getFloorAccessObs());
        if (pool.getPoolHasShower() != null)
            checkRadioGroup(showerRadio, pool.getPoolHasShower());
        if (pool.getHasAccessShower() != null)
            checkRadioGroup(accessShowerRadio, pool.getHasAccessShower());
        if (pool.getShowerObs() != null)
            showerObsValue.setText(pool.getShowerObs());
        if (pool.getPoolHasFence() != null)
            checkRadioGroup(fenceRadio, pool.getPoolHasFence());
        if (pool.getFenceHeight() != null)
            fenceHeightValue.setText(String.valueOf(pool.getFenceHeight()));
        if (pool.getFenceGapWidth() != null)
            fenceGapValue.setText(String.valueOf(pool.getFenceGapWidth()));
        if (pool.getFenceHasAutoGate() != null)
            checkRadioGroup(gateLockRadio, pool.getFenceHasAutoGate());
        if (pool.getAutoGateHandleHeight() != null)
            gateLockHeightValue.setText(String.valueOf(pool.getAutoGateHandleHeight()));
        if (pool.getGateHasAntiRust() != null)
            checkRadioGroup(antiRustRadio, pool.getGateHasAntiRust());
        if (pool.getGateAllowVision() != null)
            checkRadioGroup(seeFenceRadio, pool.getGateAllowVision());
        if (pool.getGateObs() != null)
            gateObsValue.setText(pool.getGateObs());
        if (pool.getPoolPhoto() != null)
            photoValue.setText(pool.getPoolPhoto());
    }

    private PoolEntry createPool(Bundle bundle) {
        String location, aroundObs = null, waterFlowObs = null, accessFloorObs = null, showerObs = null, gateObs = null, photo = null;
        int aroundPool, waterFlow, accessFloor, hasShower, hasFence;
        Integer accessShower = null, hasGate = null, antiRust = null, seeThrough = null;
        Double fenceHeight = null, lockHeight = null, gateGap = null;

        location = String.valueOf(poolLocalValue.getText());
        aroundPool = indexRadio(aroundPoolRadio);
        if (!TextUtils.isEmpty(poolAreaObsValue.getText()))
            aroundObs = String.valueOf(poolAreaObsValue.getText());
        waterFlow = indexRadio(waterFlowRadio);
        if (!TextUtils.isEmpty(waterFlowObsValue.getText()))
            waterFlowObs = String.valueOf(waterFlowObsValue.getText());
        accessFloor = indexRadio(accessFloorRadio);
        if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
            accessFloorObs = String.valueOf(accessFloorObsValue.getText());
        hasShower = indexRadio(showerRadio);
        if (hasShower == 1)
            accessShower = indexRadio(accessShowerRadio);
        if (!TextUtils.isEmpty(showerObsValue.getText()))
            showerObs = String.valueOf(showerObsValue.getText());
        hasFence = indexRadio(fenceRadio);
        if (hasFence == 1) {
            if (!TextUtils.isEmpty(fenceHeightValue.getText()))
                fenceHeight = Double.valueOf(String.valueOf(showerObsValue.getText()));
            if (!TextUtils.isEmpty(fenceGapValue.getText()))
                gateGap = Double.valueOf(String.valueOf(fenceGapValue.getText()));
            hasGate = indexRadio(gateLockRadio);
            if (hasGate == 1) {
                if (!TextUtils.isEmpty(gateLockHeightValue.getText()))
                    lockHeight = Double.valueOf(String.valueOf(gateLockHeightValue.getText()));
            }
            antiRust = indexRadio(antiRustRadio);
            seeThrough = indexRadio(seeFenceRadio);
        }
        if (!TextUtils.isEmpty(gateObsValue.getText()))
            gateObs = String.valueOf(gateObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new PoolEntry(bundle.getInt(BLOCK_ID), location, aroundPool, aroundObs, waterFlow, waterFlowObs, accessFloor, accessFloorObs, hasShower, accessShower, showerObs,
                hasFence, fenceHeight, gateGap, hasGate, lockHeight, antiRust, seeThrough, gateObs, photo, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null);
    }

    private PoolEntryOne updatePool(Bundle bundle) {
        String location, aroundObs = null, waterFlowObs = null, accessFloorObs = null, showerObs = null, gateObs = null, photo = null;
        int aroundPool, waterFlow, accessFloor, hasShower, hasFence;
        Integer accessShower = null, hasGate = null, antiRust = null, seeThrough = null;
        Double fenceHeight = null, lockHeight = null, gateGap = null;

        location = String.valueOf(poolLocalValue.getText());
        aroundPool = indexRadio(aroundPoolRadio);
        if (!TextUtils.isEmpty(poolAreaObsValue.getText()))
            aroundObs = String.valueOf(poolAreaObsValue.getText());
        waterFlow = indexRadio(waterFlowRadio);
        if (!TextUtils.isEmpty(waterFlowObsValue.getText()))
            waterFlowObs = String.valueOf(waterFlowObsValue.getText());
        accessFloor = indexRadio(accessFloorRadio);
        if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
            accessFloorObs = String.valueOf(accessFloorObsValue.getText());
        hasShower = indexRadio(showerRadio);
        if (hasShower == 1)
            accessShower = indexRadio(accessShowerRadio);
        if (!TextUtils.isEmpty(showerObsValue.getText()))
            showerObs = String.valueOf(showerObsValue.getText());
        hasFence = indexRadio(fenceRadio);
        if (hasFence == 1) {
            if (!TextUtils.isEmpty(fenceHeightValue.getText()))
                fenceHeight = Double.valueOf(String.valueOf(showerObsValue.getText()));
            if (!TextUtils.isEmpty(fenceGapValue.getText()))
                gateGap = Double.valueOf(String.valueOf(fenceGapValue.getText()));
            hasGate = indexRadio(gateLockRadio);
            if (hasGate == 1) {
                if (!TextUtils.isEmpty(gateLockHeightValue.getText()))
                    lockHeight = Double.valueOf(String.valueOf(gateLockHeightValue.getText()));
            }
            antiRust = indexRadio(antiRustRadio);
            seeThrough = indexRadio(seeFenceRadio);
        }
        if (!TextUtils.isEmpty(gateObsValue.getText()))
            gateObs = String.valueOf(gateObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new PoolEntryOne(bundle.getInt(POOL_ID), location, aroundPool, aroundObs, waterFlow, waterFlowObs, accessFloor, accessFloorObs, hasShower, accessShower, showerObs,
                hasFence, fenceHeight, gateGap, hasGate, lockHeight, antiRust, seeThrough, gateObs, photo);
    }

    private void clearErrors() {
        poolLocalField.setErrorEnabled(false);
        accessFloorObsField.setErrorEnabled(false);
        fenceHeightField.setErrorEnabled(false);
        fenceGapField.setErrorEnabled(false);
        gateLockHeightField.setErrorEnabled(false);

        aroundPoolError.setVisibility(View.GONE);
        waterFlowError.setVisibility(View.GONE);
        accessFloorError.setVisibility(View.GONE);
        showerError.setVisibility(View.GONE);
        accessShowerError.setVisibility(View.GONE);
        fenceError.setVisibility(View.GONE);
        gateLockError.setVisibility(View.GONE);
        antiRustError.setVisibility(View.GONE);
        seeFenceError.setVisibility(View.GONE);
    }

    private boolean checkEmptyFields() {
        int i = 0;
        clearErrors();
        if (TextUtils.isEmpty(poolLocalValue.getText())) {
            i++;
            poolLocalField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(aroundPoolRadio) == -1) {
            i++;
            aroundPoolError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(waterFlowRadio) == -1) {
            i++;
            waterFlowError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(accessFloorRadio) == -1) {
            i++;
            accessFloorError.setVisibility(View.VISIBLE);
        } else if (indexRadio(accessFloorRadio) == 0 && TextUtils.isEmpty(accessFloorObsValue.getText())) {
            i++;
            accessFloorObsField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(showerRadio) == -1) {
            i++;
            showerError.setVisibility(View.VISIBLE);
        } else if (indexRadio(showerRadio) == 1) {
            if (indexRadio(accessShowerRadio) == -1) {
                i++;
                accessShowerError.setVisibility(View.VISIBLE);
            }
        }

        if (indexRadio(fenceRadio) == -1) {
            i++;
            fenceError.setVisibility(View.VISIBLE);
        } else if (indexRadio(fenceRadio) == 1) {
            if (TextUtils.isEmpty(fenceHeightValue.getText())) {
                i++;
                fenceHeightField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(fenceGapValue.getText())) {
                i++;
                fenceGapField.setError(getString(R.string.req_field_error));
            }
            if (indexRadio(gateLockRadio) == -1) {
                i++;
                gateLockError.setVisibility(View.VISIBLE);
            } else if (indexRadio(gateLockRadio) == 1) {
                if (TextUtils.isEmpty(gateLockHeightValue.getText())) {
                    i++;
                    gateLockHeightField.setError(getString(R.string.req_field_error));
                }
            }
            if (indexRadio(antiRustRadio) == -1) {
                i++;
                antiRustError.setVisibility(View.VISIBLE);
            }
            if (indexRadio(seeFenceRadio) == -1) {
                i++;
                seeFenceError.setVisibility(View.VISIBLE);
            }
        }
        return i == 0;
    }

    private void callNextPoolFragment(Bundle bundle) {
        bundle.putBoolean(RECENT_ENTRY, true);
        PoolTwoFragment nextPool = new PoolTwoFragment();
        nextPool.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, nextPool).addToBackStack(null).commit();
    }

    private void cancelClick() {
        if (poolBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteSidewalk(poolBundle.getInt(POOL_ID));
                poolBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(POOL_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(POOL_LIST, 0);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == showerRadio) {
            if (index == 1) {
                accessShowerHeader.setVisibility(View.VISIBLE);
                accessShowerRadio.setVisibility(View.VISIBLE);
            } else {
                accessShowerRadio.clearCheck();
                accessShowerRadio.setVisibility(View.GONE);
                accessShowerHeader.setVisibility(View.GONE);
            }
        } else if (radio == fenceRadio) {
            if (index == 1) {
                fenceHeightField.setVisibility(View.VISIBLE);
                fenceGapField.setVisibility(View.VISIBLE);
                gateLockHeader.setVisibility(View.VISIBLE);
                gateLockRadio.setVisibility(View.VISIBLE);
                antiRustHeader.setVisibility(View.VISIBLE);
                antiRustRadio.setVisibility(View.VISIBLE);
                seeFenceHeader.setVisibility(View.VISIBLE);
                seeFenceRadio.setVisibility(View.VISIBLE);
            } else {
                fenceHeightValue.setText(null);
                fenceHeightField.setVisibility(View.GONE);
                fenceGapValue.setText(null);
                fenceGapField.setVisibility(View.GONE);
                gateLockHeader.setVisibility(View.GONE);
                gateLockRadio.clearCheck();
                gateLockRadio.setVisibility(View.GONE);
                gateLockRadio.clearCheck();
                antiRustHeader.setVisibility(View.GONE);
                antiRustRadio.clearCheck();
                antiRustRadio.setVisibility(View.GONE);
                seeFenceHeader.setVisibility(View.GONE);
                seeFenceRadio.clearCheck();
                seeFenceRadio.setVisibility(View.GONE);
            }

        } else if (radio == gateLockRadio) {
            if (index == 1)
                gateLockHeightField.setVisibility(View.VISIBLE);
            else {
                gateLockHeightValue.setText(null);
                gateLockHeightField.setVisibility(View.GONE);
            }
        }
    }
}