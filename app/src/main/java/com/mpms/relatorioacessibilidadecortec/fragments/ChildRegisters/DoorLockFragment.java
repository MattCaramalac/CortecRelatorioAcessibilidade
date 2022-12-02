package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

public class DoorLockFragment extends Fragment implements TagInterface {

    TextInputLayout lockDescField, lockHeightField, lockObsField;
    TextInputEditText lockDescValue, lockHeightValue, lockObsValue;
    MaterialButton saveLock, cancelLock;
    MultiLineRadioGroup lockType;
    TextView lockTypeError;

    Bundle lockBundle = new Bundle();

    ViewModelEntry modelEntry;

    public DoorLockFragment() {
        // Required empty public constructor
    }

    public static DoorLockFragment newInstance() {
        return new DoorLockFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lockBundle.putInt(LOCK_ID, this.getArguments().getInt(LOCK_ID));
            lockBundle.putInt(DOOR_ID, this.getArguments().getInt(DOOR_ID));
            lockBundle.putInt(EXT_ACCESS_ID, this.getArguments().getInt(EXT_ACCESS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_door_lock, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateLockViews(view);

        if (lockBundle.getInt(LOCK_ID) > 0)
            modelEntry.getOneDoorLock(lockBundle.getInt(LOCK_ID)).observe(getViewLifecycleOwner(), this::loadDoorLockData);


        saveLock.setOnClickListener(v -> {
            if (doorLockNoEmptyFields()) {
                DoorLockEntry newLock = newDoorLock(lockBundle);
                if (lockBundle.getInt(LOCK_ID) > 0) {
                    newLock.setLockID(lockBundle.getInt(LOCK_ID));
                    ViewModelEntry.updateDoorLock(newLock);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (lockBundle.getInt(LOCK_ID) == 0) {
                    ViewModelEntry.insertDoorLock(newLock);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearDoorLockFields();
                } else {
                    lockBundle.putInt(LOCK_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelLock.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateLockViews(View view) {
//        TextInputLayout
        lockDescField = view.findViewById(R.id.door_lock_description_field);
        lockHeightField = view.findViewById(R.id.door_lock_height_field);
        lockObsField = view.findViewById(R.id.door_lock_obs_field);
//        TextInputEditText
        lockDescValue = view.findViewById(R.id.door_lock_description_value);
        lockHeightValue = view.findViewById(R.id.door_lock_height_value);
        lockObsValue = view.findViewById(R.id.door_lock_obs_value);
//        MaterialButton
        saveLock = view.findViewById(R.id.save_lock);
        cancelLock = view.findViewById(R.id.cancel_lock);
//        MultilineRadioGroup
        lockType = view.findViewById(R.id.door_lock_radio);
//        TextView
        lockTypeError = view.findViewById(R.id.door_lock_type_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listener
        lockType.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (v, r) -> doorLockMultiRadio(lockType));
    }

    private void doorLockMultiRadio(MultiLineRadioGroup multi) {
        if (multi.getCheckedRadioButtonIndex() == 2) {
            lockDescField.setVisibility(View.VISIBLE);
        } else {
            lockDescValue.setText(null);
            lockDescField.setVisibility(View.GONE);
        }
    }

    private void loadDoorLockData(DoorLockEntry lock) {
        lockType.checkAt(lock.getLockType());
        if (lock.getLockType() == 2)
            lockDescValue.setText(lock.getLockDesc());
        lockHeightValue.setText(String.valueOf(lock.getLockHeight()));
        if (lock.getLockObs() != null)
            lockObsValue.setText(lock.getLockObs());
    }

    private boolean doorLockNoEmptyFields() {
        clearDoorLockEmptyErrors();
        int i = 0;
        if (lockType.getCheckedRadioButtonIndex() == -1) {
            i++;
            lockTypeError.setVisibility(View.VISIBLE);
        } else if (lockType.getCheckedRadioButtonIndex() == 2) {
            if (TextUtils.isEmpty(lockDescValue.getText())) {
                i++;
                lockDescField.setError(getString(R.string.req_field_error));
            }
        }
        if (TextUtils.isEmpty(lockHeightValue.getText())) {
            i++;
            lockHeightField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }

    private void clearDoorLockEmptyErrors() {
        lockTypeError.setVisibility(View.GONE);
        lockDescField.setErrorEnabled(false);
        lockHeightField.setErrorEnabled(false);
    }

    private void clearDoorLockFields() {
        lockType.clearCheck();
        lockDescValue.setText(null);
        lockDescField.setVisibility(View.GONE);
        lockHeightValue.setText(null);
        lockObsValue.setText(null);
    }

    private DoorLockEntry newDoorLock(Bundle bundle) {
        Integer doorID = null, extAccessID = null;
        int lType;
        String lDesc = null, lObs = null;
        double lHeight;

        if (bundle.getInt(DOOR_ID) > 0)
            doorID = bundle.getInt(DOOR_ID);
        else if (bundle.getInt(AMBIENT_ID) > 0)
            extAccessID = bundle.getInt(AMBIENT_ID);

        lType = lockType.getCheckedRadioButtonIndex();
        if (lType == 2)
            lDesc = String.valueOf(lockDescValue.getText());
        lHeight = Double.parseDouble(String.valueOf(lockHeightValue.getText()));
        if (lockObsValue.getText() != null)
            lObs = String.valueOf(lockObsValue.getText());

        return new DoorLockEntry(doorID, extAccessID, lType, lDesc, lHeight, lObs);
    }
}