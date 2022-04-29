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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
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

import java.util.ArrayList;

public class RoomsRegisterFragment extends Fragment {

    public static final String ROOM_ID = "ROOM_ID";
    public static final String LOAD_FRAG_DATA = "LOAD_FRAG_DATA";
    public static final String CHILD_DATA_COMPLETE = "CHILD_DATA_COMPLETE";

    TextView roomIdentifier, vertSignError, looseCarpetError, accessFloorError, blackboardHeader, counterHeader;
    TextInputLayout roomLocaleField, roomDescField, vertSignObsField, looseCarpetObsField, accessFloorObsField, roomObsField;
    TextInputEditText roomLocaleValue, roomDescValue, vertSignObsValue, looseCarpetObsValue, accessFloorObsValue, roomObsValue;
    MaterialButton addDoor, addSwitch, addWindow, addTable, addFreeSpace, addBlackboard, addCounter, cancelRegister, saveRegister;
    RadioGroup hasVertSingRadio, hasLooseCarpetRadio, hasAccessFloorRadio;
    FrameLayout childFrag;

    ArrayList<TextInputEditText> roomScrollArray = new ArrayList<>();
    Bundle roomBundle = new Bundle();

    ViewModelEntry modelEntry;
    public static ViewModelFragments roomModelFragments;
    FragmentManager manager;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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
        if (this.getArguments() != null) {
            roomBundle.putInt(BlockRegisterActivity.BLOCK_ID, this.getArguments().getInt(BlockRegisterActivity.BLOCK_ID));
            roomBundle.putInt(RoomRegisterListFragment.ROOM_TYPE, this.getArguments().getInt(RoomRegisterListFragment.ROOM_TYPE));
            roomBundle.putInt(ROOM_ID, this.getArguments().getInt(ROOM_ID));
        }
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

        if (roomBundle.getInt(ROOM_ID) > 0) {
            newEntry = 0;
            modelEntry.getRoomEntry(roomBundle.getInt(ROOM_ID)).observe(getViewLifecycleOwner(), this::loadRoomData);
        }

        getChildFragmentManager().setFragmentResultListener(InspectionActivity.CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(InspectionActivity.ADD_ITEM_REQUEST)) {
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
                    requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.ROOM_LIST, 0);
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
                roomBundle.putInt(ROOM_ID, newRoomID);
                if (!modelEntry.getRoomEntry(roomBundle.getInt(ROOM_ID)).hasActiveObservers())
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
//        TextInputLayout
        roomLocaleField = view.findViewById(R.id.room_location_field);
        roomDescField = view.findViewById(R.id.room_description_field);
        if (roomBundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 16)
            roomDescField.setVisibility(View.VISIBLE);
        vertSignObsField = view.findViewById(R.id.visual_sign_obs_field);
        looseCarpetObsField = view.findViewById(R.id.carpet_obs_field);
        accessFloorObsField = view.findViewById(R.id.room_access_floor_obs_field);
        roomObsField = view.findViewById(R.id.room_obs_field);
//        TextInputLayout
        roomLocaleValue = view.findViewById(R.id.room_location_value);
        roomDescValue = view.findViewById(R.id.room_description_value);
        vertSignObsValue = view.findViewById(R.id.visual_sign_obs_value);
        looseCarpetObsValue = view.findViewById(R.id.carpet_obs_value);
        accessFloorObsValue = view.findViewById(R.id.room_access_floor_obs_value);
        roomObsValue = view.findViewById(R.id.room_obs_value);
//        MaterialButton
        addDoor = view.findViewById(R.id.room_add_door_button);
        addSwitch = view.findViewById(R.id.room_add_switch_button);
        addWindow = view.findViewById(R.id.room_add_window_button);
        addTable = view.findViewById(R.id.room_add_tables_button);
        addFreeSpace = view.findViewById(R.id.room_add_free_space_button);
        addBlackboard = view.findViewById(R.id.room_add_blackboard_button);
        addCounter = view.findViewById(R.id.room_add_counter_button);
        cancelRegister = view.findViewById(R.id.cancel_room);
        saveRegister = view.findViewById(R.id.save_room);
//        RadioGroup
        hasVertSingRadio = view.findViewById(R.id.room_has_visual_sign_radio);
        hasLooseCarpetRadio = view.findViewById(R.id.room_has_carpet_radio);
        hasAccessFloorRadio = view.findViewById(R.id.room_accessible_floor_radio);
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
        addDoor.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addSwitch.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addWindow.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addTable.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addFreeSpace.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addBlackboard.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addCounter.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        saveRegister.setOnClickListener(v -> buttonClickedListener(roomBundle, view));
        cancelRegister.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.ROOM_LIST, 0));
        allowRoomObsScroll();
        setChildFragView(roomBundle);

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
        }

    }

    private void setChildFragView(Bundle bundle) {
        switch (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE)) {
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new LibraryFragment()).commit();
                break;
            case 10:
                counterHeader.setVisibility(View.VISIBLE);
                addCounter.setVisibility(View.VISIBLE);
                break;
            case 11:
                blackboardHeader.setVisibility(View.VISIBLE);
                addBlackboard.setVisibility(View.VISIBLE);
                break;
            case 15:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new SecretariatFragment()).commit();
            case 16:
                counterHeader.setVisibility(View.VISIBLE);
                addCounter.setVisibility(View.VISIBLE);
                blackboardHeader.setVisibility(View.VISIBLE);
                addBlackboard.setVisibility(View.VISIBLE);
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
            newRoom.setRoomID(bundle.getInt(ROOM_ID));
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
            default:
                buttonPressed = 0;
                break;
        }

        if (buttonPressed > 0) {
            if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 2 || bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
                bundle.putBoolean(InspectionActivity.ADD_ITEM_REQUEST, true);
                getChildFragmentManager().setFragmentResult(InspectionActivity.GATHER_CHILD_DATA, bundle);
            } else {
                saveUpdateRoomEntry(bundle);
                childFragCaller(buttonPressed, bundle);
            }
        } else {
            if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 2 || bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
                bundle.putBoolean(InspectionActivity.ADD_ITEM_REQUEST, false);
                getChildFragmentManager().setFragmentResult(InspectionActivity.GATHER_CHILD_DATA, bundle);
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
                    requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.ROOM_LIST, 0);
                }
            }
        }
    }

    private void resetVariables(Bundle bundle) {
        bundle.putInt(ROOM_ID, 0);
        bundle.putBoolean(InspectionActivity.ADD_ITEM_REQUEST, false);
        newEntry = 1;
        recentEntry = 0;
        buttonPressed = 0;
    }

    private void clearRoomFields() {
        roomLocaleValue.setText(null);
        roomDescValue.setText(null);
        hasVertSingRadio.clearCheck();
        hasLooseCarpetRadio.clearCheck();
        hasAccessFloorRadio.clearCheck();
    }

    private void clearRoomNoEmptyFieldsErrors() {
        roomLocaleField.setErrorEnabled(false);
        roomDescField.setErrorEnabled(false);
        vertSignError.setVisibility(View.GONE);
        looseCarpetError.setVisibility(View.GONE);
        accessFloorError.setVisibility(View.GONE);
    }

    private boolean roomNoEmptyFields(Bundle bundle) {
        clearRoomNoEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(roomLocaleValue.getText())) {
            i++;
            roomLocaleField.setError(getString(R.string.blank_field_error));
        }
        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 12) {
            if (TextUtils.isEmpty(roomDescValue.getText())) {
                i++;
                roomDescField.setError(getString(R.string.blank_field_error));
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
        }
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
        } else
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
    }

    private RoomEntry newRoomEntry(Bundle bundle) {
        String roomLocale, roomDescription = null, vertSignObs = null, looseCarpetObs = null, accessFloorObs = null, roomObs = null, secPcrSpaceObs = null;
        int hasVertSing, hasLooseCarpet, accessFloor;
        Integer libDistShelves = null, libPcrManeuver = null, libAccessPC = null, secHasFixedSeat = null, secHasPcrSpace = null;
        Double secPcrWidth = null, secPcrDepth = null;

        roomLocale = String.valueOf(roomLocaleValue.getText());
        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 12) {
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

        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 3) {
            libDistShelves = bundle.getInt(LibraryFragment.DISTANCE_SHELVES);
            libPcrManeuver = bundle.getInt(LibraryFragment.MANEUVER_PCR);
            libAccessPC = bundle.getInt(LibraryFragment.COMPUTER_ACCESSIBLE);
        } else if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 15) {
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

        if (!TextUtils.isEmpty(roomObsValue.getText()))
            roomObs = String.valueOf(roomObsValue.getText());

        return new RoomEntry(bundle.getInt(BlockRegisterActivity.BLOCK_ID), bundle.getInt(RoomRegisterListFragment.ROOM_TYPE),
                roomLocale, roomDescription, hasVertSing, vertSignObs, hasLooseCarpet, looseCarpetObs, accessFloor, accessFloorObs, libDistShelves,
                libPcrManeuver, libAccessPC, secHasFixedSeat, secHasPcrSpace, secPcrWidth, secPcrDepth, secPcrSpaceObs, roomObs);
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

        if (roomEntry.getRoomType() == 3 || roomEntry.getRoomType() == 15)
            getChildFragmentManager().setFragmentResult(LOAD_FRAG_DATA, roomBundle);
    }
}