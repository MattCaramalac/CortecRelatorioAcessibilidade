package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.LibraryFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SecretariatFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.BlackboardListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.CounterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.FreeSpaceListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.SwitchListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.TableListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.WindowListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class RoomsRegisterFragment extends Fragment implements TagInterface {

    TextView roomIdentifier, vertSignError, looseCarpetError, accessFloorError, blackboardHeader, counterHeader, soundError, phoneError, biometryError,
            soundHeader, phoneHeader, biometryHeader;
    TextInputLayout roomLocaleField, roomDescField, vertSignObsField, looseCarpetObsField, accessFloorObsField, roomObsField,
            soundSignalHeightField, soundSignalObsField, phoneHeightField, phoneObsField, biometryHeightField, biometryObsField;
    TextInputEditText roomLocaleValue, roomDescValue, vertSignObsValue, looseCarpetObsValue, accessFloorObsValue, roomObsValue,
            soundSignalHeightValue, soundSignalObsValue, phoneHeightValue, phoneObsValue, biometryHeightValue, biometryObsValue;
    MaterialButton addDoor, addSwitch, addWindow, addTable, addFreeSpace, addBlackboard, addCounter, addRamp, addStairs, cancelRegister, saveRegister;
    RadioGroup hasVertSingRadio, hasLooseCarpetRadio, hasAccessFloorRadio, soundSignalRadio, phoneRadio, biometryRadio;
    FrameLayout childFrag;

    ArrayList<TextInputEditText> roomScrollArray = new ArrayList<>();
    Bundle roomBundle;

    ViewModelEntry modelEntry;
    public static ViewModelFragments roomModelFragments;
    FragmentManager manager;

    int newEntry = 1, recentEntry = 0, buttonPressed = 0;


    public RoomsRegisterFragment() {
        // Required empty public constructor
    }

    public static RoomsRegisterFragment newInstance() {
        return new RoomsRegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            roomBundle = new Bundle(this.getArguments());
        else
            roomBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (roomBundle.getBoolean(RECENT_ENTRY))
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
        return inflater.inflate(R.layout.fragment_rooms_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateRoomViews(view);

        if (roomBundle.getInt(AMBIENT_ID) > 0) {
            newEntry = 0;
            modelEntry.getRoomEntry(roomBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadRoomData);
        }

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(ADD_ITEM_REQUEST)) {
                saveUpdateRoomEntry(bundle);
                childFragCaller(buttonPressed, bundle);
            } else {
                if (roomNoEmptyFields(bundle) && bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                    saveUpdateRoomEntry(bundle);
                    if (newEntry == 1 || (newEntry == 0 && recentEntry == 1)) {
                        clearRoomFields();
                        resetVariables(bundle);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();

                    }
                    requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        roomModelFragments.getNewRoomID().observe(getViewLifecycleOwner(), newRoomID -> {
            if (newRoomID != null && newRoomID > 0) {
                if (newEntry == 1) {
                    newEntry = 0;
                    recentEntry = 1;
                } else {
                    newEntry = 0;
                }
                roomBundle.putInt(AMBIENT_ID, newRoomID);
                if (!modelEntry.getRoomEntry(roomBundle.getInt(AMBIENT_ID)).hasActiveObservers())
                    modelEntry.getRoomEntry(newRoomID).observe(getViewLifecycleOwner(), this::loadRoomData);
                roomModelFragments.setNewRoomID(null);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        roomModelFragments.setNewRoomID(null);
    }

    private void instantiateRoomViews(View view) {
//        TextView
        roomIdentifier = view.findViewById(R.id.room_register_header);
        roomIdentifier.setText(RoomRegisterListFragment.roomHeader(roomBundle));
        vertSignError = view.findViewById(R.id.visual_sign_error);
        looseCarpetError = view.findViewById(R.id.carpet_error);
        accessFloorError = view.findViewById(R.id.room_accessible_floor_error);
        blackboardHeader = view.findViewById(R.id.label_blackboard_register);
        counterHeader = view.findViewById(R.id.label_counter_register);
        soundHeader = view.findViewById(R.id.has_bell_activation_header);
        phoneHeader = view.findViewById(R.id.locale_has_intercom_header);
        biometryHeader = view.findViewById(R.id.locale_has_biometry_header);
        soundError = view.findViewById(R.id.has_bell_activation_error);
        phoneError = view.findViewById(R.id.locale_has_intercom_error);
        biometryError = view.findViewById(R.id.locale_has_biometry_error);
//        TextInputLayout
        roomLocaleField = view.findViewById(R.id.room_location_field);
        roomDescField = view.findViewById(R.id.room_description_field);
        if (roomBundle.getInt(ROOM_TYPE) == 12)
            roomDescField.setVisibility(View.VISIBLE);
        vertSignObsField = view.findViewById(R.id.visual_sign_obs_field);
        looseCarpetObsField = view.findViewById(R.id.carpet_obs_field);
        accessFloorObsField = view.findViewById(R.id.room_access_floor_obs_field);
        roomObsField = view.findViewById(R.id.room_obs_field);
        soundSignalHeightField = view.findViewById(R.id.bell_activation_height_field);
        soundSignalObsField = view.findViewById(R.id.bell_activation_obs_field);
        phoneHeightField = view.findViewById(R.id.intercom_height_field);
        phoneObsField = view.findViewById(R.id.locale_has_intercom_obs_field);
        biometryHeightField = view.findViewById(R.id.biometry_height_field);
        biometryObsField = view.findViewById(R.id.locale_has_biometry_obs_field);
//        TextInputLayout
        roomLocaleValue = view.findViewById(R.id.room_location_value);
        roomDescValue = view.findViewById(R.id.room_description_value);
        vertSignObsValue = view.findViewById(R.id.visual_sign_obs_value);
        looseCarpetObsValue = view.findViewById(R.id.carpet_obs_value);
        accessFloorObsValue = view.findViewById(R.id.room_access_floor_obs_value);
        roomObsValue = view.findViewById(R.id.room_obs_value);
        soundSignalHeightValue = view.findViewById(R.id.bell_activation_height_value);
        soundSignalObsValue = view.findViewById(R.id.bell_activation_obs_value);
        phoneHeightValue = view.findViewById(R.id.intercom_height_value);
        phoneObsValue = view.findViewById(R.id.locale_has_intercom_obs_value);
        biometryHeightValue = view.findViewById(R.id.biometry_height_value);
        biometryObsValue = view.findViewById(R.id.locale_has_biometry_obs_value);
//        MaterialButton
        addDoor = view.findViewById(R.id.room_add_door_button);
        addSwitch = view.findViewById(R.id.room_add_switch_button);
        addWindow = view.findViewById(R.id.room_add_window_button);
        addTable = view.findViewById(R.id.room_add_tables_button);
        addFreeSpace = view.findViewById(R.id.room_add_free_space_button);
        addBlackboard = view.findViewById(R.id.room_add_blackboard_button);
        addCounter = view.findViewById(R.id.room_add_counter_button);
        addRamp = view.findViewById(R.id.room_add_ramps_button);
        addStairs = view.findViewById(R.id.room_add_stairs_button);
        cancelRegister = view.findViewById(R.id.cancel_room);
        saveRegister = view.findViewById(R.id.save_room);
//        RadioGroup
        hasVertSingRadio = view.findViewById(R.id.room_has_visual_sign_radio);
        hasLooseCarpetRadio = view.findViewById(R.id.room_has_carpet_radio);
        hasAccessFloorRadio = view.findViewById(R.id.room_accessible_floor_radio);
        soundSignalRadio = view.findViewById(R.id.has_bell_activation_radio);
        phoneRadio = view.findViewById(R.id.locale_has_intercom_radio);
        biometryRadio = view.findViewById(R.id.locale_has_biometry_radio);
//        FrameLayout
        childFrag = view.findViewById(R.id.room_child_fragment);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        roomModelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        FragmentManager
        manager = requireActivity().getSupportFragmentManager();
//        Listeners
        hasVertSingRadio.setOnCheckedChangeListener(this::roomRadioGroupListener);
        hasLooseCarpetRadio.setOnCheckedChangeListener(this::roomRadioGroupListener);
        hasAccessFloorRadio.setOnCheckedChangeListener(this::roomRadioGroupListener);
        soundSignalRadio.setOnCheckedChangeListener(this::roomRadioGroupListener);
        phoneRadio.setOnCheckedChangeListener(this::roomRadioGroupListener);
        biometryRadio.setOnCheckedChangeListener(this::roomRadioGroupListener);
        addDoor.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addSwitch.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addWindow.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addTable.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addFreeSpace.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addBlackboard.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addCounter.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addRamp.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addStairs.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        saveRegister.setOnClickListener(v -> buttonClickedListener(roomBundle, view));
        cancelRegister.setOnClickListener(v -> cancelClick());
        allowRoomObsScroll();
        setChildFragView(roomBundle);

    }

    private void cancelClick() {
        if (roomBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteRoom(roomBundle.getInt(AMBIENT_ID));
                roomBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
    }

    private void roomRadioGroupListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasVertSingRadio) {
            if (index == 1) {
                vertSignObsField.setVisibility(View.VISIBLE);
            } else {
                vertSignObsValue.setText(null);
                vertSignObsField.setVisibility(View.GONE);
            }
        } else if (radio == hasLooseCarpetRadio) {
            if (index == 1) {
                looseCarpetObsField.setVisibility(View.VISIBLE);
            } else {
                looseCarpetObsValue.setText(null);
                looseCarpetObsField.setVisibility(View.GONE);
            }
        } else if (radio == hasAccessFloorRadio) {
            if (index == 0) {
                accessFloorObsField.setVisibility(View.VISIBLE);
            } else {
                accessFloorObsValue.setText(null);
                accessFloorObsField.setVisibility(View.GONE);
            }
        } else if (radio == soundSignalRadio) {
            if (index == 1) {
                soundSignalHeightField.setVisibility(View.VISIBLE);
                soundSignalObsField.setVisibility(View.VISIBLE);
            } else {
                soundSignalHeightValue.setText(null);
                soundSignalObsValue.setText(null);
                soundSignalHeightField.setVisibility(View.GONE);
                soundSignalObsField.setVisibility(View.GONE);
            }
        } else if (radio == phoneRadio) {
            if (index == 1) {
                phoneHeightField.setVisibility(View.VISIBLE);
                phoneObsField.setVisibility(View.VISIBLE);
            } else {
                phoneHeightValue.setText(null);
                phoneObsValue.setText(null);
                phoneHeightField.setVisibility(View.GONE);
                phoneObsField.setVisibility(View.GONE);
            }
        } else {
            if (index == 1) {
                biometryHeightField.setVisibility(View.VISIBLE);
                biometryObsField.setVisibility(View.VISIBLE);
            } else {
                biometryHeightValue.setText(null);
                biometryObsValue.setText(null);
                biometryHeightField.setVisibility(View.GONE);
                biometryObsField.setVisibility(View.GONE);
            }
        }

    }

    private void setChildFragView(Bundle bundle) {
        switch (bundle.getInt(ROOM_TYPE)) {
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new LibraryFragment()).commit();
                break;
            case 3:
            case 4:
            case 9:
                soundHeader.setVisibility(View.VISIBLE);
                phoneHeader.setVisibility(View.VISIBLE);
                biometryHeader.setVisibility(View.VISIBLE);
                soundSignalRadio.setVisibility(View.VISIBLE);
                phoneRadio.setVisibility(View.VISIBLE);
                biometryRadio.setVisibility(View.VISIBLE);
                break;
            case 5:
                counterHeader.setVisibility(View.VISIBLE);
                addCounter.setVisibility(View.VISIBLE);
                break;
            case 6:
                blackboardHeader.setVisibility(View.VISIBLE);
                addBlackboard.setVisibility(View.VISIBLE);
                break;
            case 11:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new SecretariatFragment()).commit();
            case 12:
                counterHeader.setVisibility(View.VISIBLE);
                addCounter.setVisibility(View.VISIBLE);
                blackboardHeader.setVisibility(View.VISIBLE);
                addBlackboard.setVisibility(View.VISIBLE);
                soundHeader.setVisibility(View.VISIBLE);
                phoneHeader.setVisibility(View.VISIBLE);
                biometryHeader.setVisibility(View.VISIBLE);
                soundSignalRadio.setVisibility(View.VISIBLE);
                phoneRadio.setVisibility(View.VISIBLE);
                biometryRadio.setVisibility(View.VISIBLE);
            default:
                break;
        }
    }

    private int getCheckedRoomRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void addObsFieldsToArray() {
        roomScrollArray.add(roomDescValue);
        roomScrollArray.add(vertSignObsValue);
        roomScrollArray.add(looseCarpetObsValue);
        roomScrollArray.add(accessFloorObsValue);
        roomScrollArray.add(roomObsValue);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowRoomObsScroll() {
        addObsFieldsToArray();
        for (TextInputEditText scroll : roomScrollArray) {
            scroll.setOnTouchListener(this::scrollingField);
        }
    }

    private void saveUpdateRoomEntry(Bundle bundle) {
        RoomEntry newRoom = newRoomEntry(bundle);
        if (newEntry == 1) {
            ViewModelEntry.insertRoomEntry(newRoom);
        } else if (newEntry == 0) {
            newRoom.setRoomID(bundle.getInt(AMBIENT_ID));
            ViewModelEntry.updateRoom(newRoom);
        }
    }

    private void buttonClickedListener(Bundle bundle, View view) {
        switch (view.getId()) {
            case (R.id.room_add_door_button):
                buttonPressed = 1;
                break;
            case (R.id.room_add_switch_button):
                buttonPressed = 2;
                break;
            case (R.id.room_add_window_button):
                buttonPressed = 3;
                break;
            case (R.id.room_add_tables_button):
                buttonPressed = 4;
                break;
            case (R.id.room_add_free_space_button):
                buttonPressed = 5;
                break;
            case (R.id.room_add_blackboard_button):
                buttonPressed = 6;
                break;
            case (R.id.room_add_counter_button):
                buttonPressed = 7;
                break;
            case (R.id.room_add_stairs_button):
                buttonPressed = 8;
                break;
            case (R.id.room_add_ramps_button):
                buttonPressed = 9;
                break;
            default:
                buttonPressed = 0;
                break;
        }

        if (buttonPressed > 0) {
            roomBundle.putBoolean(RECENT_ENTRY, true);
            if (bundle.getInt(ROOM_TYPE) == 2 || bundle.getInt(ROOM_TYPE) == 11) {
                bundle.putBoolean(ADD_ITEM_REQUEST, true);
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
            } else {
                saveUpdateRoomEntry(bundle);
                childFragCaller(buttonPressed, bundle);
            }
        } else {
            if (bundle.getInt(ROOM_TYPE) == 2 || bundle.getInt(ROOM_TYPE) == 11) {
                bundle.putBoolean(ADD_ITEM_REQUEST, false);
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
            } else {
                if (roomNoEmptyFields(bundle)) {
                    saveUpdateRoomEntry(bundle);
                    if (newEntry == 1 || (newEntry == 0 && recentEntry == 1)) {
                        clearRoomFields();
                        resetVariables(bundle);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    }
                    requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
                }
            }
        }
    }

    private void resetVariables(Bundle bundle) {
        bundle.putInt(AMBIENT_ID, 0);
        bundle.putBoolean(ADD_ITEM_REQUEST, false);
        newEntry = 1;
        recentEntry = 0;
        buttonPressed = 0;
    }

//    TODO - Testar salas com esses adendos

    private void clearRoomFields() {
        roomLocaleValue.setText(null);
        roomDescValue.setText(null);
        hasVertSingRadio.clearCheck();
        hasLooseCarpetRadio.clearCheck();
        hasAccessFloorRadio.clearCheck();
        soundSignalRadio.clearCheck();
        phoneRadio.clearCheck();
        biometryRadio.clearCheck();
    }

    private void clearRoomNoEmptyFieldsErrors() {
        roomLocaleField.setErrorEnabled(false);
        roomDescField.setErrorEnabled(false);
        soundSignalHeightField.setErrorEnabled(false);
        phoneHeightField.setErrorEnabled(false);
        biometryHeightField.setErrorEnabled(false);
        vertSignError.setVisibility(View.GONE);
        looseCarpetError.setVisibility(View.GONE);
        accessFloorError.setVisibility(View.GONE);
        soundError.setVisibility(View.GONE);
        phoneError.setVisibility(View.GONE);
        biometryError.setVisibility(View.GONE);
    }

    private boolean roomNoEmptyFields(Bundle bundle) {
        clearRoomNoEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(roomLocaleValue.getText())) {
            i++;
            roomLocaleField.setError(getString(R.string.req_field_error));
        }
        if (bundle.getInt(ROOM_TYPE) == 12) {
            if (TextUtils.isEmpty(roomDescValue.getText())) {
                i++;
                roomDescField.setError(getString(R.string.req_field_error));
            }
        }
        if (getCheckedRoomRadioIndex(hasVertSingRadio) == -1) {
            i++;
            vertSignError.setVisibility(View.VISIBLE);
        }
        if (getCheckedRoomRadioIndex(hasLooseCarpetRadio) == -1) {
            i++;
            looseCarpetError.setVisibility(View.VISIBLE);
        }
        if (getCheckedRoomRadioIndex(hasAccessFloorRadio) == -1) {
            i++;
            accessFloorError.setVisibility(View.VISIBLE);
        }

        if (bundle.getInt(ROOM_TYPE) == 3 || bundle.getInt(ROOM_TYPE) == 4 || bundle.getInt(ROOM_TYPE) == 9 ||
                bundle.getInt(ROOM_TYPE) == 11 || bundle.getInt(ROOM_TYPE) == 12) {
            if (getCheckedRoomRadioIndex(soundSignalRadio) == -1) {
                i++;
                soundError.setVisibility(View.VISIBLE);
            } else if (getCheckedRoomRadioIndex(soundSignalRadio) == 1) {
                if (TextUtils.isEmpty(soundSignalHeightValue.getText())) {
                    i++;
                    soundSignalHeightField.setError(getString(R.string.req_field_error));
                }
            }
            if (getCheckedRoomRadioIndex(phoneRadio) == -1) {
                i++;
                phoneError.setVisibility(View.VISIBLE);
            } else if (getCheckedRoomRadioIndex(phoneRadio) == 1) {
                if (TextUtils.isEmpty(phoneHeightValue.getText())) {
                    i++;
                    phoneHeightField.setError(getString(R.string.req_field_error));
                }
            }
            if (getCheckedRoomRadioIndex(biometryRadio) == -1) {
                i++;
                biometryError.setVisibility(View.VISIBLE);
            } else if (getCheckedRoomRadioIndex(biometryRadio) == 1) {
                if (TextUtils.isEmpty(biometryHeightValue.getText())) {
                    i++;
                    biometryHeightField.setError(getString(R.string.req_field_error));
                }
            }
        }

        return i == 0;
    }

    private void childFragCaller(int i, Bundle bundle) {
        Fragment fragment = null;
        switch (i) {
            case 1:
                fragment = DoorListFragment.newInstance();
                break;
            case 2:
                fragment = SwitchListFragment.newInstance();
                break;
            case 3:
                fragment = WindowListFragment.newInstance();
                break;
            case 4:
                fragment = TableListFragment.newInstance();
                break;
            case 5:
                fragment = FreeSpaceListFragment.newInstance();
                break;
            case 6:
                fragment = BlackboardListFragment.newInstance();
                break;
            case 7:
                fragment = CounterListFragment.newInstance();
                break;
            case 8:
                bundle.putInt(RAMP_OR_STAIRS, 1);
                bundle.putInt(AMBIENT_TYPE, 4);
                bundle.putBoolean(FROM_ROOMS, true);
                fragment = RampStairsListFragment.newInstance();
                break;
            case 9:
                bundle.putInt(RAMP_OR_STAIRS, 2);
                bundle.putInt(AMBIENT_TYPE, 4);
                bundle.putBoolean(FROM_ROOMS, true);
                fragment = RampStairsListFragment.newInstance();
                break;
        }
        if (fragment != null) {
            fragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
        } else
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
    }

    private RoomEntry newRoomEntry(Bundle bundle) {
        String roomLocale, roomDescription = null, vertSignObs = null, looseCarpetObs = null, accessFloorObs = null, roomObs = null, secPcrSpaceObs = null,
                soundObs = null, intPhoneObs = null, bioClockObs = null;
        int hasVertSing, hasLooseCarpet, accessFloor;
        Integer libDistShelves = null, libPcrManeuver = null, libAccessPC = null, secHasFixedSeat = null, secHasPcrSpace = null, hasBell = null,
                hasIntPhone = null, hasBioClock = null;
        Double secPcrWidth = null, secPcrDepth = null, soundHeight = null, intPhoneHeight = null, bioClockHeight = null;

        roomLocale = String.valueOf(roomLocaleValue.getText());
        if (bundle.getInt(ROOM_TYPE) == 12) {
            if (!TextUtils.isEmpty(roomDescValue.getText()))
                roomDescription = String.valueOf(roomDescValue.getText());
        }
        hasVertSing = getCheckedRoomRadioIndex(hasVertSingRadio);
        if (!TextUtils.isEmpty(vertSignObsValue.getText()))
            vertSignObs = String.valueOf(vertSignObsValue.getText());
        hasLooseCarpet = getCheckedRoomRadioIndex(hasLooseCarpetRadio);
        if (!TextUtils.isEmpty(looseCarpetObsValue.getText()))
            looseCarpetObs = String.valueOf(looseCarpetObsValue.getText());
        accessFloor = getCheckedRoomRadioIndex(hasAccessFloorRadio);
        if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
            accessFloorObs = String.valueOf(accessFloorObsValue.getText());

        if (bundle.getInt(ROOM_TYPE) == 2) {
            libDistShelves = bundle.getInt(LibraryFragment.DISTANCE_SHELVES);
            libPcrManeuver = bundle.getInt(LibraryFragment.MANEUVER_PCR);
            libAccessPC = bundle.getInt(LibraryFragment.COMPUTER_ACCESSIBLE);
        } else if (bundle.getInt(ROOM_TYPE) == 11) {
            secHasFixedSeat = bundle.getInt(SecretariatFragment.HAS_FIXED_SEATS);
            if (secHasFixedSeat == 1) {
                secHasPcrSpace = bundle.getInt(SecretariatFragment.HAS_PCR_SPACE);
                if (secHasPcrSpace == 1) {
                    secPcrWidth = bundle.getDouble(SecretariatFragment.PCR_WIDTH);
                    secPcrDepth = bundle.getDouble(SecretariatFragment.PCR_DEPTH);
                    secPcrSpaceObs = bundle.getString(SecretariatFragment.PCR_OBS);
                }
            }
        }

        if (bundle.getInt(ROOM_TYPE) == 3 || bundle.getInt(ROOM_TYPE) == 4 || bundle.getInt(ROOM_TYPE) == 9 ||
                bundle.getInt(ROOM_TYPE) == 11 || bundle.getInt(ROOM_TYPE) == 12) {
            hasBell = getCheckedRoomRadioIndex(soundSignalRadio);
            if (hasBell == 1) {
                if (!TextUtils.isEmpty(soundSignalHeightValue.getText()))
                    soundHeight = Double.parseDouble(String.valueOf(soundSignalHeightValue.getText()));
                if (!TextUtils.isEmpty(soundSignalObsValue.getText()))
                    soundObs = String.valueOf(soundSignalObsValue.getText());
            }
            hasIntPhone = getCheckedRoomRadioIndex(phoneRadio);
            if (hasIntPhone == 1) {
                if (!TextUtils.isEmpty(phoneHeightValue.getText()))
                    intPhoneHeight = Double.parseDouble(String.valueOf(phoneHeightValue.getText()));
                if (!TextUtils.isEmpty(phoneObsValue.getText()))
                    intPhoneObs = String.valueOf(phoneObsValue.getText());
            }

            hasBioClock = getCheckedRoomRadioIndex(biometryRadio);
            if (hasBioClock == 1) {
                if (!TextUtils.isEmpty(biometryHeightValue.getText()))
                    bioClockHeight = Double.parseDouble(String.valueOf(biometryHeightValue.getText()));
                if (!TextUtils.isEmpty(biometryObsValue.getText()))
                    bioClockObs = String.valueOf(biometryObsValue.getText());
            }

        }

        if (!TextUtils.isEmpty(roomObsValue.getText()))
            roomObs = String.valueOf(roomObsValue.getText());

        return new RoomEntry(bundle.getInt(BLOCK_ID), bundle.getInt(ROOM_TYPE),
                roomLocale, roomDescription, hasVertSing, vertSignObs, hasLooseCarpet, looseCarpetObs, accessFloor, accessFloorObs, libDistShelves,
                libPcrManeuver, libAccessPC, secHasFixedSeat, secHasPcrSpace, secPcrWidth, secPcrDepth, secPcrSpaceObs, roomObs, null, null,
                hasBell, soundHeight, soundObs, hasIntPhone, intPhoneHeight, intPhoneObs, hasBioClock,bioClockHeight, bioClockObs);
    }

    private void loadRoomData(RoomEntry roomEntry) {
        roomLocaleValue.setText(roomEntry.getRoomLocation());
        if (roomEntry.getRoomDescription() != null)
            roomDescValue.setText(roomEntry.getRoomDescription());
        hasVertSingRadio.check(hasVertSingRadio.getChildAt(roomEntry.getRoomHasVertSing()).getId());
        if (roomEntry.getRoomVertSignObs() != null)
            vertSignObsValue.setText(roomEntry.getRoomVertSignObs());
        hasVertSingRadio.check(hasVertSingRadio.getChildAt(roomEntry.getRoomHasVertSing()).getId());
        if (roomEntry.getRoomVertSignObs() != null)
            vertSignObsValue.setText(roomEntry.getRoomVertSignObs());
        hasLooseCarpetRadio.check(hasLooseCarpetRadio.getChildAt(roomEntry.getRoomHasLooseCarpet()).getId());
        if (roomEntry.getLooseCarpetObs() != null)
            looseCarpetObsValue.setText(roomEntry.getLooseCarpetObs());
        if (roomEntry.getRoomAccessFloor() != null)
            hasAccessFloorRadio.check(hasAccessFloorRadio.getChildAt(roomEntry.getRoomAccessFloor()).getId());
        if (roomEntry.getAccessFloorObs() != null)
            accessFloorObsValue.setText(roomEntry.getAccessFloorObs());
        if (roomEntry.getRoomObs() != null)
            roomObsValue.setText(roomEntry.getRoomObs());

        if (roomEntry.getHasBellControl() != null)
            soundSignalRadio.check(soundSignalRadio.getChildAt(roomEntry.getHasBellControl()).getId());
        if (roomEntry.getBellControlHeight() != null)
            soundSignalHeightValue.setText(String.valueOf(roomEntry.getBellControlHeight()));
        if (roomEntry.getBellControlObs() != null)
            soundSignalObsValue.setText(roomEntry.getBellControlObs());

        if (roomEntry.getHasInternalPhone() != null)
            phoneRadio.check(phoneRadio.getChildAt(roomEntry.getHasInternalPhone()).getId());
        if (roomEntry.getInternalPhoneHeight() != null)
            phoneHeightValue.setText(String.valueOf(roomEntry.getInternalPhoneHeight()));
        if (roomEntry.getInternalPhoneObs() != null)
            phoneObsValue.setText(roomEntry.getInternalPhoneObs());

        if (roomEntry.getHasBiometricClock() != null)
            biometryRadio.check(biometryRadio.getChildAt(roomEntry.getHasBiometricClock()).getId());
        if (roomEntry.getBiometricClockHeight() != null)
            biometryHeightValue.setText(String.valueOf(roomEntry.getBiometricClockHeight()));
        if (roomEntry.getBiometricClockObs() != null)
            biometryObsValue.setText(roomEntry.getBiometricClockObs());

        if (roomEntry.getRoomType() == 2 || roomEntry.getRoomType() == 11)
            getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, roomBundle);
    }
}