package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

public class DoorFragment extends Fragment implements TagInterface {

    TextInputLayout doorLocaleField, doorWidthField, handleHeightField, handleObsField, tactileSignObsField, doorSillObsField, doorObsField;
    TextInputEditText doorLocaleValue, doorWidthValue, handleHeightValue, handleObsValue, tactileSignObsValue, doorSillObsValue, doorObsValue;
    TextView handleTypeError, doorLockError, tactileSignError, addDoorLockHeader, doorSillError;
    RadioGroup doorHandleTypeRadio, hasDoorLockRadio, tactileSignRadio;
    MultiLineRadioGroup doorSillRadio;
    MaterialButton addDoorLockButton, saveDoorButton, cancelDoorButton;

    Bundle doorBundle = new Bundle();

    ViewModelEntry modelEntry;

    int newEntry = 1, recentEntry = 0;

    public DoorFragment() {
        // Required empty public constructor
    }

    public static DoorFragment newInstance() {
        return new DoorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            doorBundle = new Bundle(this.getArguments());
        else
            doorBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_door, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateDoorViews(view);

        if (doorBundle.getInt(DOOR_ID) > 0) {
            newEntry = 0;
            modelEntry.getSpecificDoor(doorBundle.getInt(DOOR_ID)).observe(getViewLifecycleOwner(), this::loadDoorData);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(ADD_ITEM_REQUEST)) {
                saveUpdateDoorEntry(bundle);
                openDoorLockListFragment(bundle);
            } else {
                if (checkDoorNoEmptyFields() && bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                    saveUpdateDoorEntry(bundle);
                    if (newEntry == 1 || (newEntry == 0 && recentEntry == 1)) {
                        clearDoorFields(bundle);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStackImmediate();
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        RoomsRegisterFragment.roomModelFragments.getNewChildRegID().observe(getViewLifecycleOwner(), newDoorID -> {
            if (newDoorID != null && newDoorID > 0) {
                if (newEntry == 1) {
                    newEntry = 0;
                    recentEntry = 1;
                } else {
                    newEntry = 0;
                }
                doorBundle.putInt(DOOR_ID, newDoorID);
                if (!modelEntry.getSpecificDoor(doorBundle.getInt(DOOR_ID)).hasActiveObservers())
                    modelEntry.getSpecificDoor(doorBundle.getInt(DOOR_ID)).observe(getViewLifecycleOwner(), this::loadDoorData);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RoomsRegisterFragment.roomModelFragments.setNewChildRegID(null);
    }

    private void instantiateDoorViews(View view) {
//        TextInputLayout
        doorLocaleField = view.findViewById(R.id.door_placement_field);
        doorWidthField = view.findViewById(R.id.door_width_field);
        handleHeightField = view.findViewById(R.id.door_handle_height_field);
        handleObsField = view.findViewById(R.id.door_handle_obs_field);
        tactileSignObsField = view.findViewById(R.id.tactile_sign_obs_field);
        doorSillObsField = view.findViewById(R.id.door_sill_obs_field);
        doorObsField = view.findViewById(R.id.door_obs_field);
//        TextInputEditText
        doorLocaleValue = view.findViewById(R.id.door_placement_value);
        doorWidthValue = view.findViewById(R.id.door_width_value);
        handleHeightValue = view.findViewById(R.id.door_handle_height_value);
        handleObsValue = view.findViewById(R.id.door_handle_obs_value);
        tactileSignObsValue = view.findViewById(R.id.tactile_sign_obs_value);
        doorSillObsValue = view.findViewById(R.id.door_sill_obs_value);
        doorObsValue = view.findViewById(R.id.door_obs_value);
//        TextView
        handleTypeError = view.findViewById(R.id.door_handle_type_error);
        doorLockError = view.findViewById(R.id.door_lock_error);
        tactileSignError = view.findViewById(R.id.door_tactile_sign_error);
        addDoorLockHeader = view.findViewById(R.id.label_door_lock_register);
        doorSillError = view.findViewById(R.id.door_sill_type_error);
//        RadioGroup
        doorHandleTypeRadio = view.findViewById(R.id.door_handle_type_radio);
        hasDoorLockRadio = view.findViewById(R.id.door_lock_radio);
        tactileSignRadio = view.findViewById(R.id.door_has_tactile_sign_radio);
//        MultilineRadioGroup
        doorSillRadio = view.findViewById(R.id.door_sill_radio);
//        MaterialButton
        addDoorLockButton = view.findViewById(R.id.add_door_lock_button);
        saveDoorButton = view.findViewById(R.id.save_door);
        cancelDoorButton = view.findViewById(R.id.cancel_door);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Radio Listeners
        hasDoorLockRadio.setOnCheckedChangeListener(this::doorLockRadioListener);
        doorSillRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (v, r) -> doorMultiRadioListener(doorSillRadio));
//        OnClickListeners
        saveDoorButton.setOnClickListener(v -> doorButtonClickListener(doorBundle, v));
        addDoorLockButton.setOnClickListener(v -> doorButtonClickListener(doorBundle, v));
        cancelDoorButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void doorMultiRadioListener(MultiLineRadioGroup multi) {
        int index = multi.getCheckedRadioButtonIndex();
        switch (index) {
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillInclinationFragment()).commit();
                break;
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillStepFragment()).commit();
                break;
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillSlopeFragment()).commit();
                break;
            default:
                removeSillFragments();
                break;
        }
    }

    private void doorLockRadioListener(RadioGroup radio, int checkID) {
        int index = radio.indexOfChild(radio.findViewById(checkID));
        if (index == 1) {
            addDoorLockHeader.setVisibility(View.VISIBLE);
            addDoorLockButton.setVisibility(View.VISIBLE);
        } else {
            addDoorLockHeader.setVisibility(View.GONE);
            addDoorLockButton.setVisibility(View.GONE);
        }
    }

    private void doorButtonClickListener(Bundle bundle, View view) {
        if (view == addDoorLockButton) {
            if (doorSillRadio.getCheckedRadioButtonIndex() > 0) {
                bundle.putBoolean(ADD_ITEM_REQUEST, true);
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
            } else {
                saveUpdateDoorEntry(bundle);
                openDoorLockListFragment(bundle);
            }
        } else {
            bundle.putBoolean(ADD_ITEM_REQUEST, false);
            if (doorSillRadio.getCheckedRadioButtonIndex() > 0) {
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
            } else {
                if (checkDoorNoEmptyFields()) {
                    saveUpdateDoorEntry(bundle);
                    if (newEntry == 1 || (newEntry == 0 && recentEntry == 1)) {
                        clearDoorFields(bundle);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.ROOM_LIST, 0);
                    }
                }
            }
        }
    }

    private void clearDoorFields(Bundle bundle) {
        doorLocaleValue.setText(null);
        doorWidthValue.setText(null);
        handleHeightValue.setText(null);
        handleObsValue.setText(null);
        tactileSignObsValue.setText(null);
        doorSillObsValue.setText(null);
        doorObsValue.setText(null);
        doorHandleTypeRadio.clearCheck();
        hasDoorLockRadio.clearCheck();
        tactileSignRadio.clearCheck();
        doorSillRadio.clearCheck();
        removeSillFragments();
        resetDoorVariables(bundle);
    }

    private void resetDoorVariables(Bundle bundle) {
        newEntry = 1;
        recentEntry = 0;
        int roomID = bundle.getInt(AMBIENT_ID);
        bundle.clear();
        bundle.putInt(AMBIENT_ID, roomID);
    }

    private void saveUpdateDoorEntry(Bundle bundle) {
        DoorEntry newDoor = newDoorEntry(doorBundle);
        if (newEntry == 1) {
            ViewModelEntry.insertDoorEntry(newDoor);
        } else if (newEntry == 0) {
            newDoor.setDoorID(bundle.getInt(DOOR_ID));
            ViewModelEntry.updateDoor(newDoor);
        }
    }

    private void openDoorLockListFragment(Bundle bundle) {
        DoorLockListFragment lockList = DoorLockListFragment.newInstance();
        lockList.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, lockList).addToBackStack(null).commit();
    }

    private int getDoorRadioCheck(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkDoorNoEmptyFields() {
        clearDoorEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(doorLocaleValue.getText())) {
            i++;
            doorLocaleField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(doorWidthValue.getText())) {
            i++;
            doorWidthField.setError(getString(R.string.blank_field_error));
        }
        if (getDoorRadioCheck(doorHandleTypeRadio) == -1) {
            i++;
            handleTypeError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(handleHeightValue.getText())) {
            i++;
            handleHeightField.setError(getString(R.string.blank_field_error));
        }
        if (getDoorRadioCheck(hasDoorLockRadio) == -1) {
            i++;
            doorLockError.setVisibility(View.VISIBLE);
        }
        if (getDoorRadioCheck(tactileSignRadio) == -1) {
            i++;
            tactileSignError.setVisibility(View.VISIBLE);
        }
        if (doorSillRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            doorSillError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void clearDoorEmptyFieldsErrors() {
        doorLocaleField.setErrorEnabled(false);
        doorWidthField.setErrorEnabled(false);
        handleHeightField.setErrorEnabled(false);
        handleTypeError.setVisibility(View.GONE);
        doorLockError.setVisibility(View.GONE);
        tactileSignError.setVisibility(View.GONE);
        doorSillError.setVisibility(View.GONE);
    }


    private void loadDoorData(DoorEntry doorEntry) {
        if (doorEntry.getDoorLocation() != null)
            doorLocaleValue.setText(doorEntry.getDoorLocation());
        if (doorEntry.getDoorWidth() != null)
            doorWidthValue.setText(String.valueOf(doorEntry.getDoorWidth()));
        if (doorEntry.getDoorHandleType() != null && doorEntry.getDoorHandleType() > -1)
            doorHandleTypeRadio.check(doorHandleTypeRadio.getChildAt(doorEntry.getDoorHandleType()).getId());
        if (doorEntry.getDoorHandleHeight() != null)
            handleHeightValue.setText(String.valueOf(doorEntry.getDoorHandleHeight()));
        if (doorEntry.getDoorHandleObs() != null)
            handleObsValue.setText(doorEntry.getDoorHandleObs());
        if (doorEntry.getDoorHasLocks() != null && doorEntry.getDoorHasLocks() > -1)
            hasDoorLockRadio.check(hasDoorLockRadio.getChildAt(doorEntry.getDoorHasLocks()).getId());
        if (doorEntry.getDoorHasTactileSign() != null && doorEntry.getDoorHasTactileSign() > -1)
            tactileSignRadio.check(tactileSignRadio.getChildAt(doorEntry.getDoorHasTactileSign()).getId());
        if (doorEntry.getDoorTactileSignObs() != null)
            tactileSignObsValue.setText(doorEntry.getDoorTactileSignObs());
        if (doorEntry.getDoorSillType() != null && doorEntry.getDoorSillType() > -1) {
            doorSillRadio.checkAt(doorEntry.getDoorSillType());
            if (doorEntry.getDoorSillType() > 0)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, doorBundle);
        }
        if (doorEntry.getDoorSillObs() != null)
            doorSillObsValue.setText(doorEntry.getDoorSillObs());
        if (doorEntry.getDoorObs() != null)
            doorObsValue.setText(doorEntry.getDoorObs());

    }

    private DoorEntry newDoorEntry(Bundle bundle) {
        String doorLocale = null, handleObs = null, tactileSignObs = null, doorSillObs = null, doorObs = null;
        Integer handleType = null, doorHasLocks = null, hasTactileSign = null, doorSillType = null, sillSlopeQnt = null;
        Double doorWidth = null, handleHeight = null, sillInclinationHeight = null, sillStepHeight = null, slopeAngle1 = null, slopeAngle2 = null, slopeAngle3 = null,
                slopeAngle4 = null, sillSlopeWidth = null;

        if (!TextUtils.isEmpty(doorLocaleValue.getText()))
            doorLocale = String.valueOf(doorLocaleValue.getText());
        if (!TextUtils.isEmpty(doorWidthValue.getText()))
            doorWidth = Double.parseDouble(String.valueOf(doorWidthValue.getText()));
        if (getDoorRadioCheck(doorHandleTypeRadio) > -1)
            handleType = getDoorRadioCheck(doorHandleTypeRadio);
        if (!TextUtils.isEmpty(handleHeightValue.getText()))
            handleHeight = Double.parseDouble(String.valueOf(handleHeightValue.getText()));
        if (!TextUtils.isEmpty(handleObsValue.getText()))
            handleObs = String.valueOf(handleObsValue.getText());
        if (getDoorRadioCheck(hasDoorLockRadio) > -1)
            doorHasLocks = getDoorRadioCheck(hasDoorLockRadio);
        if (getDoorRadioCheck(tactileSignRadio) > -1)
            hasTactileSign = getDoorRadioCheck(hasDoorLockRadio);
        if (!TextUtils.isEmpty(tactileSignObsValue.getText()))
            tactileSignObs = String.valueOf(tactileSignObsValue.getText());
        if (doorSillRadio.getCheckedRadioButtonIndex() > -1) {
            doorSillType = doorSillRadio.getCheckedRadioButtonIndex();
            if (doorSillType == 1) {
                sillInclinationHeight = bundle.getDouble(SillInclinationFragment.HEIGHT_INCLINED_SILL);
            } else if (doorSillType == 2) {
                sillStepHeight = bundle.getDouble(SillStepFragment.STEP_HEIGHT);
            } else if (doorSillType == 3) {
                sillSlopeWidth = bundle.getDouble(SillSlopeFragment.SLOPE_WIDTH);
                sillSlopeQnt = bundle.getInt(SillSlopeFragment.SLOPE_QNT);
                switch (sillSlopeQnt) {
                    case 4:
                        slopeAngle4 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_4);
                    case 3:
                        slopeAngle3 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_3);
                    case 2:
                        slopeAngle2 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_2);
                    case 1:
                        slopeAngle1 = bundle.getDouble(SillSlopeFragment.SLOPE_ANGLE_1);
                        break;
                    default:
                        break;
                }
            }
        }

        if (!TextUtils.isEmpty(doorSillObsValue.getText()))
            doorSillObs = String.valueOf(doorSillObsValue.getText());
        if (!TextUtils.isEmpty(doorObsValue.getText()))
            doorObs = String.valueOf(doorObsValue.getText());

        return new DoorEntry(bundle.getInt(AMBIENT_ID), doorLocale, doorWidth, handleType, handleHeight, handleObs, doorHasLocks, hasTactileSign, tactileSignObs, doorSillType,
                sillInclinationHeight, sillStepHeight, sillSlopeQnt, slopeAngle1, slopeAngle2, slopeAngle3, slopeAngle4, sillSlopeWidth, doorSillObs, doorObs);

    }

    private void removeSillFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.door_sill_fragment);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }
}