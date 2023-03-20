package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.LibParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SecParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.LibraryFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SecretariatFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.BlackboardListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.CounterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.EquipmentListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.FreeSpaceListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.SwitchListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.TableListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.WindowListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

import java.util.ArrayList;

public class RoomsRegisterFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextView roomIdentifier, vertSignError, looseCarpetError, accessFloorError, counterHeader, phoneError, biometryError,
            phoneHeader, biometryHeader, workRoomHeader, workRoomError;
    TextInputLayout roomLocaleField, roomDescField, vertSignObsField, looseCarpetObsField, accessFloorObsField, roomObsField,
            phoneHeightField, phoneObsField, biometryHeightField, biometryObsField, roomPhotoField;
    TextInputEditText roomLocaleValue, roomDescValue, vertSignObsValue, looseCarpetObsValue, accessFloorObsValue, roomObsValue,
            phoneHeightValue, phoneObsValue, biometryHeightValue, biometryObsValue, roomPhotoValue;
    MaterialButton addDoor, addSwitch, addWindow, addTable, addFreeSpace, addBlackboard, addCounter, addRamp, addStairs, addFountain, cancelRegister, saveRegister, addEquip;
    RadioGroup hasVertSingRadio, hasLooseCarpetRadio, hasAccessFloorRadio, phoneRadio, biometryRadio, workRoomRadio;
    FrameLayout childFrag;
    ConstraintLayout roomConst;

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
                    } else
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack(ROOM_LIST, 0);
                } else
                    Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        roomModelFragments.getNewRoomID().observe(getViewLifecycleOwner(), newRoomID -> {
            if (newRoomID != null && newRoomID > 0) {
                if (newEntry == 1) {
                    recentEntry = 1;
                }
                newEntry = 0;
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
        roomIdentifier.setVisibility(View.GONE);
        vertSignError = view.findViewById(R.id.visual_sign_error);
        looseCarpetError = view.findViewById(R.id.carpet_error);
        accessFloorError = view.findViewById(R.id.room_accessible_floor_error);
        counterHeader = view.findViewById(R.id.label_counter_register);
        phoneHeader = view.findViewById(R.id.locale_has_intercom_header);
        biometryHeader = view.findViewById(R.id.locale_has_biometry_header);
        phoneError = view.findViewById(R.id.locale_has_intercom_error);
        biometryError = view.findViewById(R.id.locale_has_biometry_error);
        workRoomHeader = view.findViewById(R.id.work_room_header);
        workRoomError = view.findViewById(R.id.work_room_error);
//        TextInputLayout
        roomLocaleField = view.findViewById(R.id.room_location_field);
        roomDescField = view.findViewById(R.id.room_description_field);
        vertSignObsField = view.findViewById(R.id.visual_sign_obs_field);
        looseCarpetObsField = view.findViewById(R.id.carpet_obs_field);
        accessFloorObsField = view.findViewById(R.id.room_access_floor_obs_field);
        roomObsField = view.findViewById(R.id.room_obs_field);
        phoneHeightField = view.findViewById(R.id.intercom_height_field);
        phoneObsField = view.findViewById(R.id.locale_has_intercom_obs_field);
        biometryHeightField = view.findViewById(R.id.biometry_height_field);
        biometryObsField = view.findViewById(R.id.locale_has_biometry_obs_field);
        roomPhotoField = view.findViewById(R.id.room_photo_field);
//        TextInputLayout
        roomLocaleValue = view.findViewById(R.id.room_location_value);
        roomDescValue = view.findViewById(R.id.room_description_value);
        vertSignObsValue = view.findViewById(R.id.visual_sign_obs_value);
        looseCarpetObsValue = view.findViewById(R.id.carpet_obs_value);
        accessFloorObsValue = view.findViewById(R.id.room_access_floor_obs_value);
        roomObsValue = view.findViewById(R.id.room_obs_value);
        phoneHeightValue = view.findViewById(R.id.intercom_height_value);
        phoneObsValue = view.findViewById(R.id.locale_has_intercom_obs_value);
        biometryHeightValue = view.findViewById(R.id.biometry_height_value);
        biometryObsValue = view.findViewById(R.id.locale_has_biometry_obs_value);
        roomPhotoValue = view.findViewById(R.id.room_photo_value);
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
        addFountain = view.findViewById(R.id.room_add_fountain_button);
        addEquip = view.findViewById(R.id.room_add_equip_button);
        cancelRegister = view.findViewById(R.id.cancel_room);
        saveRegister = view.findViewById(R.id.save_room);
//        RadioGroup
        workRoomRadio = view.findViewById(R.id.work_room_radio);
        hasVertSingRadio = view.findViewById(R.id.room_has_visual_sign_radio);
        hasLooseCarpetRadio = view.findViewById(R.id.room_has_carpet_radio);
        hasAccessFloorRadio = view.findViewById(R.id.room_accessible_floor_radio);
        phoneRadio = view.findViewById(R.id.locale_has_intercom_radio);
        biometryRadio = view.findViewById(R.id.locale_has_biometry_radio);
//        FrameLayout
        childFrag = view.findViewById(R.id.room_child_fragment);
//        ConstLayout
        roomConst = view.findViewById(R.id.room_const_layout);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        roomModelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        FragmentManager
        manager = requireActivity().getSupportFragmentManager();
//        Listeners
        hasVertSingRadio.setOnCheckedChangeListener(this::radioListener);
        hasLooseCarpetRadio.setOnCheckedChangeListener(this::radioListener);
        hasAccessFloorRadio.setOnCheckedChangeListener(this::radioListener);
        phoneRadio.setOnCheckedChangeListener(this::radioListener);
        biometryRadio.setOnCheckedChangeListener(this::radioListener);
        workRoomRadio.setOnCheckedChangeListener(this::radioListener);
        addDoor.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addSwitch.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addWindow.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addTable.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addFreeSpace.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addBlackboard.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addCounter.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addRamp.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addStairs.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addFountain.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        addEquip.setOnClickListener(v -> buttonClickedListener(roomBundle, v));
        saveRegister.setOnClickListener(v -> buttonClickedListener(roomBundle, view));
        cancelRegister.setOnClickListener(v -> cancelClick());
        addObsFieldsToArray();
        allowObsScroll(roomScrollArray);
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

    private void setChildFragView(Bundle bundle) {
        switch (bundle.getInt(ROOM_TYPE)) {
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new LibraryFragment()).commit();
                break;
            case 3:
            case 4:
            case 9:
                phoneHeader.setVisibility(View.VISIBLE);
                biometryHeader.setVisibility(View.VISIBLE);
                phoneRadio.setVisibility(View.VISIBLE);
                biometryRadio.setVisibility(View.VISIBLE);
                break;
            case 5:
                counterHeader.setVisibility(View.VISIBLE);
                addCounter.setVisibility(View.VISIBLE);
                break;
            case 11:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new SecretariatFragment()).commit();
            case 12:
                workRoomHeader.setVisibility(View.VISIBLE);
                workRoomRadio.setVisibility(View.VISIBLE);
                roomDescField.setVisibility(View.VISIBLE);
                roomConst.setVisibility(View.GONE);
            default:
                break;
        }
    }

    private void addObsFieldsToArray() {
        roomScrollArray.add(roomDescValue);
        roomScrollArray.add(vertSignObsValue);
        roomScrollArray.add(looseCarpetObsValue);
        roomScrollArray.add(accessFloorObsValue);
        roomScrollArray.add(roomObsValue);
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
            case (R.id.room_add_fountain_button):
                buttonPressed = 10;
                break;
            case (R.id.room_add_equip_button):
                buttonPressed = 11;
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
                } else {
                    Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
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

    private void clearRoomFields() {
        roomLocaleValue.setText(null);
        roomDescValue.setText(null);
        hasVertSingRadio.clearCheck();
        hasLooseCarpetRadio.clearCheck();
        hasAccessFloorRadio.clearCheck();
        phoneRadio.clearCheck();
        biometryRadio.clearCheck();
    }

    private void clearRoomNoEmptyFieldsErrors() {
        workRoomError.setVisibility(View.GONE);
        roomLocaleField.setErrorEnabled(false);
        roomDescField.setErrorEnabled(false);
        phoneHeightField.setErrorEnabled(false);
        biometryHeightField.setErrorEnabled(false);
        vertSignError.setVisibility(View.GONE);
        looseCarpetError.setVisibility(View.GONE);
        accessFloorError.setVisibility(View.GONE);
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
            if (indexCheckRadio(workRoomRadio) == -1) {
                i++;
                workRoomError.setVisibility(View.VISIBLE);
            } else if (indexCheckRadio(workRoomRadio) == 0) {
                i += emptyFieldCont(bundle);
            }
        } else
            i += emptyFieldCont(bundle);

        return i == 0;
    }

    private int emptyFieldCont(Bundle bundle) {
        int i = 0;
        if (indexCheckRadio(hasVertSingRadio) == -1) {
            i++;
            vertSignError.setVisibility(View.VISIBLE);
        }
        if (indexCheckRadio(hasLooseCarpetRadio) == -1) {
            i++;
            looseCarpetError.setVisibility(View.VISIBLE);
        }
        if (indexCheckRadio(hasAccessFloorRadio) == -1) {
            i++;
            accessFloorError.setVisibility(View.VISIBLE);
        }

        if (bundle.getInt(ROOM_TYPE) == 3 || bundle.getInt(ROOM_TYPE) == 4 || bundle.getInt(ROOM_TYPE) == 9 ||
                bundle.getInt(ROOM_TYPE) == 11 || bundle.getInt(ROOM_TYPE) == 12) {
            if (indexCheckRadio(phoneRadio) == -1) {
                i++;
                phoneError.setVisibility(View.VISIBLE);
            } else if (indexCheckRadio(phoneRadio) == 1) {
                if (TextUtils.isEmpty(phoneHeightValue.getText())) {
                    i++;
                    phoneHeightField.setError(getString(R.string.req_field_error));
                }
            }
            if (indexCheckRadio(biometryRadio) == -1) {
                i++;
                biometryError.setVisibility(View.VISIBLE);
            } else if (indexCheckRadio(biometryRadio) == 1) {
                if (TextUtils.isEmpty(biometryHeightValue.getText())) {
                    i++;
                    biometryHeightField.setError(getString(R.string.req_field_error));
                }
            }
        }
        return i;
    }

    private void childFragCaller(int i, Bundle bundle) {
        Fragment fragment = null;
        bundle.putBoolean(FROM_ROOMS, true);
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
                fragment = RampStairsListFragment.newInstance();
                break;
            case 9:
                bundle.putInt(RAMP_OR_STAIRS, 2);
                bundle.putInt(AMBIENT_TYPE, 4);
                fragment = RampStairsListFragment.newInstance();
                break;
            case 10:
                fragment = WaterFountainListFragment.newInstance();
                break;
            case 11:
                fragment = EquipmentListFragment.newInstance();
                break;
        }
        if (fragment != null) {
            fragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.show_fragment_selected, fragment).addToBackStack(ROOM_OBJ_LIST).commit();
        } else
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
    }

    private RoomEntry newRoomEntry(Bundle bundle) {
        String roomLocale, roomDescription = null, vertSignObs = null, looseCarpetObs = null, accessFloorObs = null, roomObs = null, secPcrSpaceObs = null,
               intPhoneObs = null, bioClockObs = null, photos = null;
        Integer isWork = null, libDistShelves = null, libLongCorridor = null, libPcrManeuver = null, libHasPC = null, libAccessPC = null, secHasFixedSeat = null,
                secHasPcrSpace = null, hasIntPhone = null, hasBioClock = null, hasVertSing = null, hasLooseCarpet = null, accessFloor = null;
        Double secPcrWidth = null, secPcrDepth = null, intPhoneHeight = null, bioClockHeight = null;

        roomLocale = String.valueOf(roomLocaleValue.getText());
        if (bundle.getInt(ROOM_TYPE) == 12) {
            if (!TextUtils.isEmpty(roomDescValue.getText()))
                roomDescription = String.valueOf(roomDescValue.getText());
            isWork = indexCheckRadio(workRoomRadio);
            if (isWork == 0) {
                hasVertSing = indexCheckRadio(hasVertSingRadio);
                if (!TextUtils.isEmpty(vertSignObsValue.getText()))
                    vertSignObs = String.valueOf(vertSignObsValue.getText());
                hasLooseCarpet = indexCheckRadio(hasLooseCarpetRadio);
                if (!TextUtils.isEmpty(looseCarpetObsValue.getText()))
                    looseCarpetObs = String.valueOf(looseCarpetObsValue.getText());
                accessFloor = indexCheckRadio(hasAccessFloorRadio);
                if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
                    accessFloorObs = String.valueOf(accessFloorObsValue.getText());

                hasIntPhone = indexCheckRadio(phoneRadio);
                if (hasIntPhone == 1) {
                    if (!TextUtils.isEmpty(phoneHeightValue.getText()))
                        intPhoneHeight = Double.parseDouble(String.valueOf(phoneHeightValue.getText()));
                    if (!TextUtils.isEmpty(phoneObsValue.getText()))
                        intPhoneObs = String.valueOf(phoneObsValue.getText());
                }

                hasBioClock = indexCheckRadio(biometryRadio);
                if (hasBioClock == 1) {
                    if (!TextUtils.isEmpty(biometryHeightValue.getText()))
                        bioClockHeight = Double.parseDouble(String.valueOf(biometryHeightValue.getText()));
                    if (!TextUtils.isEmpty(biometryObsValue.getText()))
                        bioClockObs = String.valueOf(biometryObsValue.getText());
                }
            }
        } else {
            hasVertSing = indexCheckRadio(hasVertSingRadio);
            if (!TextUtils.isEmpty(vertSignObsValue.getText()))
                vertSignObs = String.valueOf(vertSignObsValue.getText());
            hasLooseCarpet = indexCheckRadio(hasLooseCarpetRadio);
            if (!TextUtils.isEmpty(looseCarpetObsValue.getText()))
                looseCarpetObs = String.valueOf(looseCarpetObsValue.getText());
            accessFloor = indexCheckRadio(hasAccessFloorRadio);
            if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
                accessFloorObs = String.valueOf(accessFloorObsValue.getText());

            if (bundle.getInt(ROOM_TYPE) == 2) {
                LibParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                libDistShelves = parcel.getRightDistShelves();
                libLongCorridor = parcel.getHasLongCorridors();
                if (libLongCorridor == 1)
                    libPcrManeuver = parcel.getHasManeuverArea();
                libHasPC = parcel.getHasComputers();
                if (libHasPC == 1)
                    libAccessPC = parcel.getHasAccessComputer();

            }
            else if (bundle.getInt(ROOM_TYPE) == 11) {
                SecParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                secHasFixedSeat = parcel.getHasFixedSeats();
                if (secHasFixedSeat == 1) {
                    secHasPcrSpace = parcel.getHasPCRSpace();
                    if (secHasPcrSpace == 1) {
                        secPcrWidth = parcel.getPcrSpaceWidth();
                        secPcrDepth = parcel.getPcrSpaceDepth();
                        secPcrSpaceObs = parcel.getPcrSpaceObs();
                    }
                }
            }

            if (bundle.getInt(ROOM_TYPE) == 3 || bundle.getInt(ROOM_TYPE) == 4 || bundle.getInt(ROOM_TYPE) == 9 ||
                    bundle.getInt(ROOM_TYPE) == 11) {
                hasIntPhone = indexCheckRadio(phoneRadio);
                if (hasIntPhone == 1) {
                    if (!TextUtils.isEmpty(phoneHeightValue.getText()))
                        intPhoneHeight = Double.parseDouble(String.valueOf(phoneHeightValue.getText()));
                    if (!TextUtils.isEmpty(phoneObsValue.getText()))
                        intPhoneObs = String.valueOf(phoneObsValue.getText());
                }

                hasBioClock = indexCheckRadio(biometryRadio);
                if (hasBioClock == 1) {
                    if (!TextUtils.isEmpty(biometryHeightValue.getText()))
                        bioClockHeight = Double.parseDouble(String.valueOf(biometryHeightValue.getText()));
                    if (!TextUtils.isEmpty(biometryObsValue.getText()))
                        bioClockObs = String.valueOf(biometryObsValue.getText());
                }

            }
        }


        if (!TextUtils.isEmpty(roomObsValue.getText()))
            roomObs = String.valueOf(roomObsValue.getText());
        if (!TextUtils.isEmpty(roomPhotoValue.getText()))
            photos = String.valueOf(roomPhotoValue.getText());

        return new RoomEntry(bundle.getInt(BLOCK_ID), bundle.getInt(ROOM_TYPE), roomLocale, roomDescription, isWork, hasVertSing, vertSignObs, hasLooseCarpet, looseCarpetObs,
                accessFloor, accessFloorObs, libDistShelves, libLongCorridor, libPcrManeuver, libHasPC, libAccessPC, secHasFixedSeat, secHasPcrSpace, secPcrWidth,
                secPcrDepth, secPcrSpaceObs, hasIntPhone, intPhoneHeight, intPhoneObs, hasBioClock, bioClockHeight, bioClockObs, photos, roomObs);
    }

    private void loadRoomData(RoomEntry roomEntry) {
        if (roomEntry.getRoomLocation() != null)
            roomLocaleValue.setText(roomEntry.getRoomLocation());
        if (roomEntry.getRoomDescription() != null)
            roomDescValue.setText(roomEntry.getRoomDescription());
        if (roomEntry.getRoomType() == 12) {
            if (roomEntry.getIsWorkRoom() != null && roomEntry.getIsWorkRoom() > -1) {
                checkRadioGroup(workRoomRadio, roomEntry.getIsWorkRoom());
                if (roomEntry.getIsWorkRoom() == 0) {
                    if (roomEntry.getRoomHasVertSing() != null && roomEntry.getRoomHasVertSing() != -1)
                        checkRadioGroup(hasVertSingRadio, roomEntry.getRoomHasVertSing());
                    if (roomEntry.getRoomVertSignObs() != null)
                        vertSignObsValue.setText(roomEntry.getRoomVertSignObs());
                    if (roomEntry.getRoomHasLooseCarpet() != null && roomEntry.getRoomHasLooseCarpet() != -1) {
                        checkRadioGroup(hasLooseCarpetRadio, roomEntry.getRoomHasLooseCarpet());
                        if (roomEntry.getRoomHasLooseCarpet() == 1 && roomEntry.getLooseCarpetObs() != null)
                            looseCarpetObsValue.setText(roomEntry.getLooseCarpetObs());
                    }
                    if (roomEntry.getRoomAccessFloor() != null && roomEntry.getRoomAccessFloor() != -1) {
                        checkRadioGroup(hasAccessFloorRadio, roomEntry.getRoomAccessFloor());
                        if (roomEntry.getRoomAccessFloor() == 0 && roomEntry.getAccessFloorObs() != null)
                            accessFloorObsValue.setText(roomEntry.getAccessFloorObs());
                    }

                    if (roomEntry.getHasIntercom() != null && roomEntry.getHasIntercom() != -1) {
                        checkRadioGroup(phoneRadio, roomEntry.getHasIntercom());
                        if (roomEntry.getIntercomHeight() != null)
                            phoneHeightValue.setText(String.valueOf(roomEntry.getIntercomHeight()));
                        if (roomEntry.getIntercomObs() != null)
                            phoneObsValue.setText(roomEntry.getIntercomObs());
                    }


                    if (roomEntry.getHasBioClock() != null && roomEntry.getHasBioClock() != -1) {
                        checkRadioGroup(biometryRadio, roomEntry.getHasBioClock());
                        if (roomEntry.getBioClockHeight() != null)
                            biometryHeightValue.setText(String.valueOf(roomEntry.getBioClockHeight()));
                        if (roomEntry.getBioClockObs() != null)
                            biometryObsValue.setText(roomEntry.getBioClockObs());
                    }
                }
            }
        } else {
            if (roomEntry.getRoomHasVertSing() != null && roomEntry.getRoomHasVertSing() != -1)
                checkRadioGroup(hasVertSingRadio, roomEntry.getRoomHasVertSing());
            if (roomEntry.getRoomVertSignObs() != null)
                vertSignObsValue.setText(roomEntry.getRoomVertSignObs());
            if (roomEntry.getRoomHasLooseCarpet() != null && roomEntry.getRoomHasLooseCarpet() != -1) {
                checkRadioGroup(hasLooseCarpetRadio, roomEntry.getRoomHasLooseCarpet());
                if (roomEntry.getRoomHasLooseCarpet() == 1 && roomEntry.getLooseCarpetObs() != null)
                    looseCarpetObsValue.setText(roomEntry.getLooseCarpetObs());
            }
            if (roomEntry.getRoomAccessFloor() != null && roomEntry.getRoomAccessFloor() != -1) {
                checkRadioGroup(hasAccessFloorRadio, roomEntry.getRoomAccessFloor());
                if (roomEntry.getRoomAccessFloor() == 0 && roomEntry.getAccessFloorObs() != null)
                    accessFloorObsValue.setText(roomEntry.getAccessFloorObs());
            }

            if (roomEntry.getHasIntercom() != null && roomEntry.getHasIntercom() != -1) {
                checkRadioGroup(phoneRadio, roomEntry.getHasIntercom());
                if (roomEntry.getIntercomHeight() != null)
                    phoneHeightValue.setText(String.valueOf(roomEntry.getIntercomHeight()));
                if (roomEntry.getIntercomObs() != null)
                    phoneObsValue.setText(roomEntry.getIntercomObs());
            }


            if (roomEntry.getHasBioClock() != null && roomEntry.getHasBioClock() != -1) {
                checkRadioGroup(biometryRadio, roomEntry.getHasBioClock());
                if (roomEntry.getBioClockHeight() != null)
                    biometryHeightValue.setText(String.valueOf(roomEntry.getBioClockHeight()));
                if (roomEntry.getBioClockObs() != null)
                    biometryObsValue.setText(roomEntry.getBioClockObs());
            }

            if (roomEntry.getRoomType() == 2 || roomEntry.getRoomType() == 11)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, roomBundle);
        }

        if (roomEntry.getRoomObs() != null)
            roomObsValue.setText(roomEntry.getRoomObs());
        if (roomEntry.getRoomPhoto() != null)
            roomPhotoValue.setText(roomEntry.getRoomPhoto());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexCheckRadio(radio);
        if (radio == workRoomRadio) {
            if (index == 0) {
                roomConst.setVisibility(View.VISIBLE);
                counterHeader.setVisibility(View.VISIBLE);
                addCounter.setVisibility(View.VISIBLE);
                phoneHeader.setVisibility(View.VISIBLE);
                biometryHeader.setVisibility(View.VISIBLE);
                phoneRadio.setVisibility(View.VISIBLE);
                biometryRadio.setVisibility(View.VISIBLE);
            } else {
                roomConst.setVisibility(View.GONE);
            }
        }
        else if (radio == hasVertSingRadio) {
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
}