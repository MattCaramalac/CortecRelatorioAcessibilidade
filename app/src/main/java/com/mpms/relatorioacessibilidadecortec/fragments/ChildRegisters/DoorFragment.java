package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.InclinationParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SlopeParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.StepParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestToiletFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestUpViewFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestUrinalFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DoorFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout doorLocaleField, doorWidthField1, doorWidthField2, pictObsField, doorOpObsField, handleHeightField, handleObsField, horHandleHeightField,
            horHandleLengthField, horHandleFrameDistField, horHandleDiamField, horHandleDoorDistField, horHandleObsField, winInfHeightField, winSupHeightField,
            winWidthField, winObsField, tactSignHeightField, tactSignInclField, tactSignObsField, doorSillObsField, doorObsField, photoField;
    TextInputEditText doorLocaleValue, doorWidthValue1, doorWidthValue2, pictObsValue, doorOpObsValue, handleHeightValue, handleObsValue, horHandleHeightValue,
            horHandleLengthValue, horHandleFrameDistValue, horHandleDiamValue, horHandleDoorDistValue, horHandleObsValue, winInfHeightValue, winSupHeightValue,
            winWidthValue, winObsValue, tactSignHeightValue, tactSignInclValue, tactSignObsValue, doorSillObsValue, doorObsValue, photoValue;
    TextView doorTypeHeader, doorTypeError, hasPictHeader, hasPictError, opDirHeader, opDirError, handleTypeError, doorLockError, horHandleError, doorWinHeader, doorWinError,
            tacSignError, addDoorLockHeader, doorSillError, labelDoorLock;
    RadioGroup doorTypeRadio, hasPictRadio, doorOpRadio, doorHandleTypeRadio, hasDoorLockRadio, hasHorHandleRadio, hasWindowRadio, tactSignRadio, doorSillRadio;
    MultiLineRadioGroup doorSillMultiRadio;
    MaterialButton addDoorLockButton, saveDoorButton, cancelDoorButton;

    Bundle doorBundle = new Bundle();

    ArrayList<TextInputEditText> eText = new ArrayList<>();

    ViewModelEntry modelEntry;

    int newEntry = 1, recentEntry = 0, doorID = 0;

    public DoorFragment() {
        // Required empty public constructor
    }

    public static DoorFragment newInstance() {
        return new DoorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            doorBundle = new Bundle(this.getArguments());
            doorBundle.putBoolean(RECENT_ENTRY, false);
        } else
            doorBundle = new Bundle();

        if (!doorBundle.getBoolean(FROM_REST)) {
            requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    if (doorBundle.getBoolean(RECENT_ENTRY))
                        cancelClick();
                    else {
                        setEnabled(false);
                        requireActivity().onBackPressed();
                    }
                    setEnabled(true);
                }
            });
        }
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
        } else if (doorBundle.getInt(BOX_ID) > 0) {
            modelEntry.getAccBoxDoor(doorBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), door -> {
                if (door != null) {
                    newEntry = 0;
                    doorID = door.getDoorID();
                    loadDoorData(door);
                }
            });
        } else if (doorBundle.getInt(REST_ID) > 0) {
            modelEntry.getRestDoor(doorBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), door -> {
                if (door != null) {
                    newEntry = 0;
                    doorID = door.getDoorID();
                    loadDoorData(door);
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!doorBundle.getBoolean(FROM_REST) && !doorBundle.getBoolean(FROM_BOX)) {
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
                    } else
                        Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!doorBundle.getBoolean(FROM_REST) && !doorBundle.getBoolean(FROM_BOX)) {
            RoomsRegisterFragment.roomModelFragments.getNewChildRegID().observe(getViewLifecycleOwner(), newDoorID -> {
                if (newDoorID != null && newDoorID > 0) {
                    if (newEntry == 1)
                        recentEntry = 1;
                    newEntry = 0;
                    doorBundle.putInt(DOOR_ID, newDoorID);
                    if (!modelEntry.getSpecificDoor(doorBundle.getInt(DOOR_ID)).hasActiveObservers())
                        modelEntry.getSpecificDoor(doorBundle.getInt(DOOR_ID)).observe(getViewLifecycleOwner(), this::loadDoorData);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!doorBundle.getBoolean(FROM_REST) && !doorBundle.getBoolean(FROM_BOX))
            RoomsRegisterFragment.roomModelFragments.setNewChildRegID(null);
    }

    private void instantiateDoorViews(View view) {
//        TextInputLayout
        doorLocaleField = view.findViewById(R.id.door_placement_field);
        doorWidthField1 = view.findViewById(R.id.door_width_field_1);
        doorWidthField2 = view.findViewById(R.id.door_width_field_2);
        pictObsField = view.findViewById(R.id.door_sia_obs_field);
        doorOpObsField = view.findViewById(R.id.door_opening_direction_obs_field);
        handleHeightField = view.findViewById(R.id.door_handle_height_field);
        handleObsField = view.findViewById(R.id.door_handle_obs_field);
        horHandleHeightField = view.findViewById(R.id.door_horizontal_handle_height_field);
        horHandleLengthField = view.findViewById(R.id.door_horizontal_handle_length_field);
        horHandleFrameDistField = view.findViewById(R.id.door_hor_handle_frame_dist_field);
        horHandleDiamField = view.findViewById(R.id.door_hor_handle_diam_field);
        horHandleDoorDistField = view.findViewById(R.id.door_hor_handle_door_dist_field);
        horHandleObsField = view.findViewById(R.id.door_horizontal_handle_obs_field);
        winInfHeightField = view.findViewById(R.id.door_window_inf_height_field);
        winSupHeightField = view.findViewById(R.id.door_window_sup_height_field);
        winWidthField = view.findViewById(R.id.door_window_width_field);
        winObsField = view.findViewById(R.id.door_window_obs_field);
        tactSignHeightField = view.findViewById(R.id.door_tact_sign_height_field);
        tactSignInclField = view.findViewById(R.id.door_tact_sign_incl_field);
        tactSignObsField = view.findViewById(R.id.tactile_sign_obs_field);
        doorSillObsField = view.findViewById(R.id.door_sill_obs_field);
        doorObsField = view.findViewById(R.id.door_obs_field);
        photoField = view.findViewById(R.id.door_photos_field);
//        TextInputEditText
        doorLocaleValue = view.findViewById(R.id.door_placement_value);
        doorWidthValue1 = view.findViewById(R.id.door_width_value_1);
        doorWidthValue2 = view.findViewById(R.id.door_width_value_2);
        pictObsValue = view.findViewById(R.id.door_sia_obs_value);
        doorOpObsValue = view.findViewById(R.id.door_opening_direction_obs_value);
        handleHeightValue = view.findViewById(R.id.door_handle_height_value);
        handleObsValue = view.findViewById(R.id.door_handle_obs_value);
        horHandleHeightValue = view.findViewById(R.id.door_horizontal_handle_height_value);
        horHandleLengthValue = view.findViewById(R.id.door_horizontal_handle_length_value);
        horHandleFrameDistValue = view.findViewById(R.id.door_hor_handle_frame_dist_value);
        horHandleDiamValue = view.findViewById(R.id.door_hor_handle_diam_value);
        horHandleDoorDistValue = view.findViewById(R.id.door_hor_handle_door_dist_value);
        horHandleObsValue = view.findViewById(R.id.door_horizontal_handle_obs_value);
        winInfHeightValue = view.findViewById(R.id.door_window_inf_height_value);
        winSupHeightValue = view.findViewById(R.id.door_window_sup_height_value);
        winWidthValue = view.findViewById(R.id.door_window_width_value);
        winObsValue = view.findViewById(R.id.door_window_obs_value);
        tactSignHeightValue = view.findViewById(R.id.door_tact_sign_height_value);
        tactSignInclValue = view.findViewById(R.id.door_tact_sign_incl_value);
        tactSignObsValue = view.findViewById(R.id.tactile_sign_obs_value);
        doorSillObsValue = view.findViewById(R.id.door_sill_obs_value);
        doorObsValue = view.findViewById(R.id.door_obs_value);
        photoValue = view.findViewById(R.id.door_photos_value);
//        TextView
        doorTypeHeader = view.findViewById(R.id.header_door_type);
        doorTypeError = view.findViewById(R.id.door_type_error);
        hasPictHeader = view.findViewById(R.id.door_sia_header);
        hasPictError = view.findViewById(R.id.door_sia_error);
        opDirHeader = view.findViewById(R.id.door_opening_direction_header);
        opDirError = view.findViewById(R.id.door_opening_direction_error);
        horHandleError = view.findViewById(R.id.door_horizontal_handle_error);
        doorWinHeader = view.findViewById(R.id.label_door_has_window);
        doorWinError = view.findViewById(R.id.door_has_window_error);
        handleTypeError = view.findViewById(R.id.door_handle_type_error);
        doorLockError = view.findViewById(R.id.door_lock_error);
        tacSignError = view.findViewById(R.id.door_tactile_sign_error);
        addDoorLockHeader = view.findViewById(R.id.label_door_lock_register);
        doorSillError = view.findViewById(R.id.door_sill_type_error);
        labelDoorLock = view.findViewById(R.id.label_door_locks);
//        RadioGroup
        doorTypeRadio = view.findViewById(R.id.door_type_radio);
        hasPictRadio = view.findViewById(R.id.door_sia_radio);
        doorOpRadio = view.findViewById(R.id.door_opening_direction_radio);
        hasHorHandleRadio = view.findViewById(R.id.door_horizontal_handle_radio);
        hasWindowRadio = view.findViewById(R.id.door_has_window_radio);
        doorSillRadio = view.findViewById(R.id.door_sill_radio);
        doorHandleTypeRadio = view.findViewById(R.id.door_handle_type_radio);
        hasDoorLockRadio = view.findViewById(R.id.door_lock_radio);
        tactSignRadio = view.findViewById(R.id.door_has_tactile_sign_radio);
//        MultilineRadioGroup
        doorSillMultiRadio = view.findViewById(R.id.door_sill_multiradio);
//        MaterialButton
        addDoorLockButton = view.findViewById(R.id.add_door_lock_button);
        saveDoorButton = view.findViewById(R.id.save_door);
        cancelDoorButton = view.findViewById(R.id.cancel_door);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Radio Listeners
        doorTypeRadio.setOnCheckedChangeListener(this::doorRadioListener);
        hasHorHandleRadio.setOnCheckedChangeListener(this::doorRadioListener);
        hasWindowRadio.setOnCheckedChangeListener(this::doorRadioListener);
        tactSignRadio.setOnCheckedChangeListener(this::doorRadioListener);
        hasDoorLockRadio.setOnCheckedChangeListener(this::doorRadioListener);
        doorSillMultiRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (v, r) -> doorMultiRadioListener(doorSillMultiRadio));
//        OnClickListeners
        saveDoorButton.setOnClickListener(v -> doorButtonClickListener(doorBundle, v));
        cancelDoorButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        editTextFields();
        allowObsScroll(eText);
        if (doorBundle.getBoolean(FROM_REST) || doorBundle.getBoolean(FROM_BOX)) {
            doorLocaleField.setVisibility(View.GONE);
            doorTypeHeader.setVisibility(View.GONE);
            doorTypeRadio.setVisibility(View.GONE);
            labelDoorLock.setVisibility(View.GONE);
            addDoorLockHeader.setVisibility(View.GONE);
            hasDoorLockRadio.setVisibility(View.GONE);
            doorWinHeader.setVisibility(View.GONE);
            hasWindowRadio.setVisibility(View.GONE);
            winInfHeightField.setVisibility(View.GONE);
            winSupHeightField.setVisibility(View.GONE);
            winWidthField.setVisibility(View.GONE);
            winObsField.setVisibility(View.GONE);
            doorSillMultiRadio.setVisibility(View.GONE);
            saveDoorButton.setText(R.string.label_button_proceed_register);
            cancelDoorButton.setText(R.string.label_back_button);
        } else {
            hasPictHeader.setVisibility(View.GONE);
            hasPictRadio.setVisibility(View.GONE);
            pictObsField.setVisibility(View.GONE);
            opDirHeader.setVisibility(View.GONE);
            doorOpRadio.setVisibility(View.GONE);
            doorOpObsField.setVisibility(View.GONE);
            doorSillRadio.setVisibility(View.GONE);
            addDoorLockButton.setOnClickListener(v -> doorButtonClickListener(doorBundle, v));

        }
    }

    private void cancelClick() {
        if (doorBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteDoor(doorBundle.getInt(DOOR_ID));
                doorBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
    }

    private void editTextFields() {
        eText.add(handleObsValue);
        eText.add(tactSignObsValue);
        eText.add(doorSillObsValue);
        eText.add(doorObsValue);
        eText.add(pictObsValue);
        eText.add(doorOpObsValue);
        eText.add(horHandleObsValue);
        eText.add(winObsValue);
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

    private void doorRadioListener(RadioGroup radio, int checkID) {
        int index = radio.indexOfChild(radio.findViewById(checkID));
        if (radio == hasDoorLockRadio) {
            if (index == 1) {
                addDoorLockHeader.setVisibility(View.VISIBLE);
                addDoorLockButton.setVisibility(View.VISIBLE);
            } else {
                addDoorLockHeader.setVisibility(View.GONE);
                addDoorLockButton.setVisibility(View.GONE);
            }
        } else if (radio == doorTypeRadio) {
            if (index == 1)
                doorWidthField2.setVisibility(View.VISIBLE);
            else {
                doorWidthValue2.setText(null);
                doorWidthField2.setVisibility(View.GONE);
            }
        } else if (radio == hasHorHandleRadio) {
            if (index == 1) {
                horHandleHeightField.setVisibility(View.VISIBLE);
                horHandleLengthField.setVisibility(View.VISIBLE);
                horHandleDoorDistField.setVisibility(View.VISIBLE);
                horHandleDiamField.setVisibility(View.VISIBLE);
                horHandleFrameDistField.setVisibility(View.VISIBLE);
            } else {
                horHandleHeightValue.setText(null);
                horHandleHeightField.setVisibility(View.GONE);
                horHandleLengthValue.setText(null);
                horHandleLengthField.setVisibility(View.GONE);
                horHandleDoorDistValue.setText(null);
                horHandleDoorDistField.setVisibility(View.GONE);
                horHandleDiamValue.setText(null);
                horHandleDiamField.setVisibility(View.GONE);
                horHandleFrameDistValue.setText(null);
                horHandleFrameDistField.setVisibility(View.GONE);
            }
        } else if (radio == hasWindowRadio) {
            if (index == 1) {
                winInfHeightField.setVisibility(View.VISIBLE);
                winSupHeightField.setVisibility(View.VISIBLE);
                winWidthField.setVisibility(View.VISIBLE);
            } else {
                winInfHeightValue.setText(null);
                winSupHeightValue.setText(null);
                winWidthValue.setText(null);
                winInfHeightField.setVisibility(View.GONE);
                winSupHeightField.setVisibility(View.GONE);
                winWidthField.setVisibility(View.GONE);
            }
        } else if (radio == tactSignRadio) {
            if (index == 1) {
                tactSignHeightField.setVisibility(View.VISIBLE);
                tactSignInclField.setVisibility(View.VISIBLE);
            } else {
                tactSignHeightValue.setText(null);
                tactSignInclValue.setText(null);
                tactSignHeightField.setVisibility(View.GONE);
                tactSignInclField.setVisibility(View.GONE);
            }
        }
    }

    private void doorButtonClickListener(Bundle bundle, View view) {
        if (view == addDoorLockButton) {
            bundle.putBoolean(RECENT_ENTRY, true);
            if (doorSillMultiRadio.getCheckedRadioButtonIndex() > 0) {
                bundle.putBoolean(ADD_ITEM_REQUEST, true);
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
            } else {
                saveUpdateDoorEntry(bundle);
                openDoorLockListFragment(bundle);
            }
        } else {
            if (!doorBundle.getBoolean(FROM_REST) && !doorBundle.getBoolean(FROM_BOX)) {
                bundle.putBoolean(ADD_ITEM_REQUEST, false);
                if (doorSillMultiRadio.getCheckedRadioButtonIndex() > 0) {
                    getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
                } else {
                    if (checkDoorNoEmptyFields()) {
                        saveUpdateDoorEntry(bundle);
                        if (newEntry == 1 || (newEntry == 0 && recentEntry == 1)) {
                            clearDoorFields(bundle);
                            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                            requireActivity().getSupportFragmentManager().popBackStack(ROOM_OBJ_LIST, 0);
                        }
                    } else
                        Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
                }
            } else {
                if (checkDoorNoEmptyFields()) {
                    saveUpdateDoorEntry(doorBundle);
                    Fragment fragment;
                    if (doorBundle.getBoolean(FROM_COLLECTIVE))
                        fragment = RestUrinalFragment.newInstance();
                    else if (doorBundle.getBoolean(FROM_BOX)) {
                        fragment = RestToiletFragment.newInstance();
                    } else
                        fragment = RestUpViewFragment.newInstance();
                    fragment.setArguments(doorBundle);
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
                }
            }
        }
    }

    private void clearDoorFields(Bundle bundle) {
        doorLocaleValue.setText(null);
        doorTypeRadio.clearCheck();
        doorWidthValue1.setText(null);
        doorWidthValue2.setText(null);
        doorWidthField2.setVisibility(View.GONE);
        doorHandleTypeRadio.clearCheck();
        handleHeightValue.setText(null);
        handleObsValue.setText(null);
        hasDoorLockRadio.clearCheck();
        hasHorHandleRadio.clearCheck();
        horHandleHeightValue.setText(null);
        horHandleHeightField.setVisibility(View.GONE);
        horHandleLengthValue.setText(null);
        horHandleLengthField.setVisibility(View.GONE);
        horHandleFrameDistValue.setText(null);
        horHandleFrameDistField.setVisibility(View.GONE);
        horHandleDiamValue.setText(null);
        horHandleDiamField.setVisibility(View.GONE);
        horHandleDoorDistValue.setText(null);
        horHandleDoorDistField.setVisibility(View.GONE);
        horHandleObsValue.setText(null);
        hasWindowRadio.clearCheck();
        winInfHeightValue.setText(null);
        winInfHeightField.setVisibility(View.GONE);
        winSupHeightValue.setText(null);
        winSupHeightField.setVisibility(View.GONE);
        winWidthValue.setText(null);
        winWidthField.setVisibility(View.GONE);
        winObsValue.setText(null);
        tactSignRadio.clearCheck();
        tactSignHeightValue.setText(null);
        tactSignHeightField.setVisibility(View.GONE);
        tactSignInclValue.setText(null);
        tactSignInclField.setVisibility(View.GONE);
        tactSignObsValue.setText(null);
        doorSillObsValue.setText(null);
        doorObsValue.setText(null);
        doorSillMultiRadio.clearCheck();
        photoValue.setText(null);
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
        } else {
            if (!doorBundle.getBoolean(FROM_REST) && !doorBundle.getBoolean(FROM_BOX))
                newDoor.setDoorID(bundle.getInt(DOOR_ID));
            else
                newDoor.setDoorID(doorID);
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

        if (TextUtils.isEmpty(doorWidthValue1.getText())) {
            i++;
            doorWidthField1.setError(getString(R.string.req_field_error));
        }
        if (getDoorRadioCheck(doorHandleTypeRadio) == -1) {
            i++;
            handleTypeError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(handleHeightValue.getText())) {
            i++;
            handleHeightField.setError(getString(R.string.req_field_error));
        }

        if (getDoorRadioCheck(hasHorHandleRadio) == -1) {
            i++;
            horHandleError.setVisibility(View.VISIBLE);
        } else if (getDoorRadioCheck(hasHorHandleRadio) == 1) {
            if (TextUtils.isEmpty(horHandleHeightValue.getText())) {
                i++;
                horHandleHeightField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horHandleLengthValue.getText())) {
                i++;
                horHandleLengthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horHandleFrameDistValue.getText())) {
                i++;
                horHandleFrameDistField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horHandleDiamValue.getText())) {
                i++;
                horHandleDiamField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horHandleDoorDistValue.getText())) {
                i++;
                horHandleDoorDistField.setError(getString(R.string.req_field_error));
            }
        }

        if (getDoorRadioCheck(tactSignRadio) == -1) {
            i++;
            tacSignError.setVisibility(View.VISIBLE);
        } else if (getDoorRadioCheck(tactSignRadio) == 1) {
            if (TextUtils.isEmpty(tactSignHeightValue.getText())) {
                i++;
                tactSignHeightField.setError(getString(R.string.req_field_error));
            }
        }

        if (doorBundle.getBoolean(FROM_REST) || doorBundle.getBoolean(FROM_BOX)) {
            if (getDoorRadioCheck(hasPictRadio) == -1) {
                i++;
                hasPictError.setVisibility(View.VISIBLE);
            }
            if (getDoorRadioCheck(doorOpRadio) == -1) {
                i++;
                opDirError.setVisibility(View.VISIBLE);
            }
            if (getDoorRadioCheck(doorSillRadio) == -1) {
                i++;
                doorSillError.setVisibility(View.VISIBLE);
            }
        } else {
            if (TextUtils.isEmpty(doorLocaleValue.getText())) {
                i++;
                doorLocaleField.setError(getString(R.string.req_field_error));
            }
            if (getDoorRadioCheck(doorTypeRadio) == -1) {
                i++;
                doorTypeError.setVisibility(View.VISIBLE);
            } else if (getDoorRadioCheck(doorTypeRadio) == 1 && TextUtils.isEmpty(doorWidthValue2.getText())) {
                i++;
                doorWidthField2.setError(getString(R.string.req_field_error));
            }
            if (getDoorRadioCheck(hasDoorLockRadio) == -1) {
                i++;
                doorLockError.setVisibility(View.VISIBLE);
            }
            if (getDoorRadioCheck(hasWindowRadio) == -1) {
                i++;
                doorWinError.setVisibility(View.VISIBLE);
            } else if (getDoorRadioCheck(hasWindowRadio) == 1) {
                if (TextUtils.isEmpty(winInfHeightValue.getText())) {
                    i++;
                    winInfHeightField.setError(getString(R.string.req_field_error));
                }
                if (TextUtils.isEmpty(winSupHeightValue.getText())) {
                    i++;
                    winSupHeightField.setError(getString(R.string.req_field_error));
                }
                if (TextUtils.isEmpty(winWidthValue.getText())) {
                    i++;
                    winWidthField.setError(getString(R.string.req_field_error));
                }
            }
            if (doorSillMultiRadio.getCheckedRadioButtonIndex() == -1) {
                i++;
                doorSillError.setVisibility(View.VISIBLE);
            }
        }

        return i == 0;
    }

    private void clearDoorEmptyFieldsErrors() {
        doorLocaleField.setErrorEnabled(false);
        doorWidthField1.setErrorEnabled(false);
        doorWidthField2.setErrorEnabled(false);
        handleHeightField.setErrorEnabled(false);
        horHandleHeightField.setErrorEnabled(false);
        horHandleLengthField.setErrorEnabled(false);
        horHandleFrameDistField.setErrorEnabled(false);
        horHandleDiamField.setErrorEnabled(false);
        horHandleDoorDistField.setErrorEnabled(false);
        winInfHeightField.setErrorEnabled(false);
        winSupHeightField.setErrorEnabled(false);
        winWidthField.setErrorEnabled(false);
        tactSignHeightField.setErrorEnabled(false);
        doorTypeError.setVisibility(View.GONE);
        hasPictError.setVisibility(View.GONE);
        opDirError.setVisibility(View.GONE);
        horHandleError.setVisibility(View.GONE);
        doorWinError.setVisibility(View.GONE);
        handleTypeError.setVisibility(View.GONE);
        doorLockError.setVisibility(View.GONE);
        tacSignError.setVisibility(View.GONE);
        doorSillError.setVisibility(View.GONE);
    }

    private void loadDoorData(DoorEntry door) {
        if (door.getDoorLocation() != null)
            doorLocaleValue.setText(door.getDoorLocation());
        if (door.getDoorType() != null)
            doorTypeRadio.check(doorTypeRadio.getChildAt(door.getDoorType()).getId());
        if (door.getDoorWidth1() != null)
            doorWidthValue1.setText(String.valueOf(door.getDoorWidth1()));
        if (door.getDoorWidth2() != null)
            doorWidthValue2.setText(String.valueOf(door.getDoorWidth2()));
        if (door.getDoorHasPict() != null && door.getDoorHasPict() > -1)
            hasPictRadio.check(hasPictRadio.getChildAt(door.getDoorHasPict()).getId());
        if (door.getDoorPictObs() != null)
            pictObsValue.setText(door.getDoorPictObs());
        if (door.getOpDirection() != null && door.getOpDirection() > -1)
            doorOpRadio.check(doorOpRadio.getChildAt(door.getOpDirection()).getId());
        if (door.getOpDirectionObs() != null)
            doorOpObsValue.setText(door.getOpDirectionObs());
        if (door.getDoorHandleType() != null && door.getDoorHandleType() > -1)
            doorHandleTypeRadio.check(doorHandleTypeRadio.getChildAt(door.getDoorHandleType()).getId());
        if (door.getDoorHandleHeight() != null)
            handleHeightValue.setText(String.valueOf(door.getDoorHandleHeight()));
        if (door.getDoorHandleObs() != null)
            handleObsValue.setText(door.getDoorHandleObs());
        if (door.getDoorHasLocks() != null && door.getDoorHasLocks() > -1)
            hasDoorLockRadio.check(hasDoorLockRadio.getChildAt(door.getDoorHasLocks()).getId());
        if (door.getDoorHasHorBar() != null && door.getDoorHasHorBar() > -1) {
            hasHorHandleRadio.check(hasHorHandleRadio.getChildAt(door.getDoorHasHorBar()).getId());
            if (door.getDoorHasHorBar() == 1) {
                if (door.getHorBarHeight() != null)
                    horHandleHeightValue.setText(String.valueOf(door.getHorBarHeight()));
                if (door.getHorBarLength() != null)
                    horHandleLengthValue.setText(String.valueOf(door.getHorBarLength()));
                if (door.getHorBarFrameDist() != null)
                    horHandleFrameDistValue.setText(String.valueOf(door.getHorBarFrameDist()));
                if (door.getHorBarDiam() != null)
                    horHandleDiamValue.setText(String.valueOf(door.getHorBarDiam()));
                if (door.getHorBarDoorDist() != null)
                    horHandleDoorDistValue.setText(String.valueOf(door.getHorBarDoorDist()));

            }
        }
        if (door.getHorBarObs() != null)
            horHandleObsValue.setText(door.getHorBarObs());
        if (door.getDoorHasWindow() != null && door.getDoorHasWindow() > -1) {
            hasWindowRadio.check(hasWindowRadio.getChildAt(door.getDoorHasWindow()).getId());
            if (door.getDoorHasWindow() > 1) {
                if (door.getDoorWinInfHeight() != null)
                    winInfHeightValue.setText(String.valueOf(door.getDoorWinInfHeight()));
                if (door.getDoorWinSupHeight() != null)
                    winSupHeightValue.setText(String.valueOf(door.getDoorWinSupHeight()));
                if (door.getDoorWinWidth() != null)
                    winWidthValue.setText(String.valueOf(door.getDoorWinWidth()));
            }
        }
        if (door.getDoorWinObs() != null)
            winObsValue.setText(door.getDoorWinObs());

        if (door.getDoorHasTactSign() != null && door.getDoorHasTactSign() > -1) {
            tactSignRadio.check(tactSignRadio.getChildAt(door.getDoorHasTactSign()).getId());
            if (door.getDoorHasTactSign() == 1) {
                if (door.getTactSignHeight() != null)
                    tactSignHeightValue.setText(String.valueOf(door.getTactSignHeight()));
                if (door.getTactSignIncl() != null)
                    tactSignInclValue.setText(String.valueOf(door.getTactSignIncl()));
            }
        }
        if (door.getTactSignObs() != null)
            tactSignObsValue.setText(door.getTactSignObs());

        if ((doorBundle.getBoolean(FROM_REST) || doorBundle.getBoolean(FROM_BOX)) && door.getDoorSillType() != null && door.getDoorSillType() > -1) {
            doorSillRadio.check(doorSillRadio.getChildAt(door.getDoorSillType()).getId());
        } else if (door.getDoorSillType() != null && door.getDoorSillType() > -1) {
            doorSillMultiRadio.checkAt(door.getDoorSillType());
            if (door.getDoorSillType() > 0)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, doorBundle);
        }
        if (door.getDoorSillObs() != null)
            doorSillObsValue.setText(door.getDoorSillObs());
        if (door.getDoorObs() != null)
            doorObsValue.setText(door.getDoorObs());
        if(door.getDoorPhotos() != null)
            photoValue.setText(door.getDoorPhotos());

    }

    private DoorEntry newDoorEntry(Bundle bundle) {
        int doorType = 0;
        String doorLocale = null, handleObs = null, pictObs = null, opDirObs = null, horBarObs = null, winObs = null, tactileSignObs = null, doorSillObs = null,
                doorObs = null, photos = null;
        Integer handleType = null, hasPict = null, opDir = null, doorHasLocks = null, hasHorBar = null, hasWindow = null, hasTactileSign = null,
                doorSillType = null, inclQnt = null, sillSlopeQnt = null, ambientID = null, restID = null, boxID = null, hasSillIncl = null;
        Double doorWidth1 = null, doorWidth2 = null, handleHeight = null, horBarHeight = null, horBarLength = null, horBarDiam = null, horBarFrameDist = null,
                horBarDoorDist = null, winInfHeight = null, winSupHeight = null, winWidth = null, tactHeight = null, tactIncl = null, inclHeight = null,
                stepHeight = null, slopeAngle1 = null, slopeAngle2 = null, slopeAngle3 = null, slopeAngle4 = null, slopeWidth = null, slopeHeight = null,
                inclAngle4 = null, inclAngle3 = null, inclAngle2 = null, inclAngle1 = null;

        if (bundle.getInt(AMBIENT_ID) != 0)
            ambientID = bundle.getInt(AMBIENT_ID);
        else if ((bundle.getInt(BOX_ID) != 0))
            boxID = bundle.getInt(BOX_ID);
        else if (bundle.getInt(REST_ID) != 0)
            restID = bundle.getInt(REST_ID);

        if (!TextUtils.isEmpty(doorWidthValue1.getText()))
            doorWidth1 = Double.parseDouble(String.valueOf(doorWidthValue1.getText()));

        if (getDoorRadioCheck(doorHandleTypeRadio) > -1)
            handleType = getDoorRadioCheck(doorHandleTypeRadio);
        if (!TextUtils.isEmpty(handleHeightValue.getText()))
            handleHeight = Double.parseDouble(String.valueOf(handleHeightValue.getText()));
        if (!TextUtils.isEmpty(handleObsValue.getText()))
            handleObs = String.valueOf(handleObsValue.getText());

        if (getDoorRadioCheck(hasHorHandleRadio) != -1) {
            hasHorBar = getDoorRadioCheck(hasHorHandleRadio);
            if (hasHorBar == 1) {
                if (!TextUtils.isEmpty(horHandleHeightValue.getText()))
                    horBarHeight = Double.parseDouble(String.valueOf(horHandleHeightValue.getText()));
                if (!TextUtils.isEmpty(horHandleLengthValue.getText()))
                    horBarLength = Double.parseDouble(String.valueOf(horHandleLengthValue.getText()));
                if (!TextUtils.isEmpty(horHandleFrameDistValue.getText()))
                    horBarFrameDist = Double.parseDouble(String.valueOf(horHandleFrameDistValue.getText()));
                if (!TextUtils.isEmpty(horHandleDiamValue.getText()))
                    horBarDiam = Double.parseDouble(String.valueOf(horHandleDiamValue.getText()));
                if (!TextUtils.isEmpty(horHandleDoorDistValue.getText()))
                    horBarDoorDist = Double.parseDouble(String.valueOf(horHandleDoorDistValue.getText()));
            }
        }
        if (!TextUtils.isEmpty(horHandleObsValue.getText()))
            horBarObs = String.valueOf(horHandleObsValue.getText());

        if (getDoorRadioCheck(tactSignRadio) != -1) {
            hasTactileSign = getDoorRadioCheck(tactSignRadio);
            if (hasTactileSign == 1)
                if (!TextUtils.isEmpty(tactSignHeightValue.getText()))
                    tactHeight = Double.parseDouble(String.valueOf(tactSignHeightValue.getText()));
            if (!TextUtils.isEmpty(tactSignInclValue.getText()))
                tactIncl = Double.parseDouble(String.valueOf(tactSignInclValue.getText()));
        }
        if (!TextUtils.isEmpty(tactSignObsValue.getText()))
            tactileSignObs = String.valueOf(tactSignObsValue.getText());

        if (!TextUtils.isEmpty(doorSillObsValue.getText()))
            doorSillObs = String.valueOf(doorSillObsValue.getText());
        if (!TextUtils.isEmpty(doorObsValue.getText()))
            doorObs = String.valueOf(doorObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photos = String.valueOf(photoValue.getText());

        if (doorBundle.getBoolean(FROM_REST) || doorBundle.getBoolean(FROM_BOX)) {
            if (getDoorRadioCheck(hasPictRadio) != -1)
                hasPict = getDoorRadioCheck(hasPictRadio);
            if (!TextUtils.isEmpty(pictObsValue.getText()))
                pictObs = String.valueOf(pictObsValue.getText());

            if (getDoorRadioCheck(doorOpRadio) != -1)
                opDir = getDoorRadioCheck(doorOpRadio);
            if (!TextUtils.isEmpty(doorOpObsValue.getText()))
                opDirObs = String.valueOf(doorOpObsValue.getText());

            if (getDoorRadioCheck(doorSillRadio) != -1) {
                doorSillType = getDoorRadioCheck(doorSillRadio);
            }
        } else {
            if (!TextUtils.isEmpty(doorLocaleValue.getText())) {
                doorLocale = String.valueOf(doorLocaleValue.getText());
            }
            if (getDoorRadioCheck(doorTypeRadio) != -1) {
                doorType = getDoorRadioCheck(doorTypeRadio);
                if (doorType == 1 && !TextUtils.isEmpty(doorWidthValue2.getText()))
                    doorWidth2 = Double.parseDouble(String.valueOf(doorWidthValue2.getText()));
            }
            if (getDoorRadioCheck(hasDoorLockRadio) > -1)
                doorHasLocks = getDoorRadioCheck(hasDoorLockRadio);
            if (getDoorRadioCheck(hasWindowRadio) != -1) {
                hasWindow = getDoorRadioCheck(hasWindowRadio);
                if (hasWindow == 1) {
                    if (!TextUtils.isEmpty(winInfHeightValue.getText()))
                        winInfHeight = Double.parseDouble(String.valueOf(winInfHeightValue.getText()));
                    if (!TextUtils.isEmpty(winSupHeightValue.getText()))
                        winSupHeight = Double.parseDouble(String.valueOf(winSupHeightValue.getText()));
                    if (!TextUtils.isEmpty(winWidthValue.getText()))
                        winWidth = Double.parseDouble(String.valueOf(winWidthValue.getText()));
                }
            }
            if (!TextUtils.isEmpty(winObsValue.getText()))
                winObs = String.valueOf(winObsValue.getText());

            if (doorSillMultiRadio.getCheckedRadioButtonIndex() > -1) {
                doorSillType = doorSillMultiRadio.getCheckedRadioButtonIndex();
                if (doorSillType == 1) {
                    InclinationParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
                    inclHeight = parcel.getInclHeight();
                    hasSillIncl = parcel.getHasInclSlope();
                    if (hasSillIncl == 1) {
                        inclQnt = parcel.getInclQnt();
                        if (parcel.getInclMeasure4() != null)
                            inclAngle4 = parcel.getInclMeasure4();
                        if (parcel.getInclMeasure3() != null)
                            inclAngle3 = parcel.getInclMeasure3();
                        if (parcel.getInclMeasure2() != null)
                            inclAngle2 = parcel.getInclMeasure2();
                        if (parcel.getInclMeasure1() != null)
                            inclAngle1 = parcel.getInclMeasure1();
                    }
                } else if (doorSillType == 2) {
                    StepParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
                    stepHeight = parcel.getStepHeight();
                } else if (doorSillType == 3) {
                    SlopeParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
                    slopeHeight = parcel.getSillSlopeHeight();
                    slopeWidth = parcel.getSillSlopeWidth();
                    sillSlopeQnt = parcel.getSillSlopeQnt();
                    if (parcel.getSillSlopeAngle4() != null)
                        inclAngle4 = parcel.getSillSlopeAngle4();
                    if (parcel.getSillSlopeAngle3() != null)
                        inclAngle3 = parcel.getSillSlopeAngle3();
                    if (parcel.getSillSlopeAngle2() != null)
                        inclAngle2 = parcel.getSillSlopeAngle2();
                    if (parcel.getSillSlopeAngle1() != null)
                        inclAngle1 = parcel.getSillSlopeAngle1();
                }
            }
        }

        return new DoorEntry(ambientID, restID, boxID, doorLocale, doorType, doorWidth1, doorWidth2, hasPict, pictObs, opDir, opDirObs, handleType, handleHeight,
                handleObs, doorHasLocks, hasHorBar, horBarHeight, horBarLength, horBarFrameDist, horBarDiam, horBarDoorDist, horBarObs, hasWindow, winInfHeight,
                winSupHeight, winWidth, winObs, hasTactileSign, tactHeight, tactIncl, tactileSignObs, doorSillType, inclHeight, inclQnt, inclAngle1,
                inclAngle2, inclAngle3, inclAngle4, stepHeight, sillSlopeQnt, slopeAngle1, slopeAngle2, slopeAngle3, slopeAngle4, slopeWidth, slopeHeight,
                doorSillObs, doorObs, hasSillIncl, photos);

    }

    private void removeSillFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.door_sill_fragment);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }
}