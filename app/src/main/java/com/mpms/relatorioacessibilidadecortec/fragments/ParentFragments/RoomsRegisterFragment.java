package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

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
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

import java.util.ArrayList;

public class RoomsRegisterFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextView roomIdentifier, vertSignError, looseCarpetError, accessFloorError, phoneError, biometryError, phoneHeader, biometryHeader, workRoomHeader,
            workRoomError, tacSignError;
    TextInputLayout roomLocaleField, roomDescField, vertSignObsField, looseCarpetObsField, accessFloorObsField, roomObsField,
            phoneHeightField, phoneObsField, biometryHeightField, biometryObsField, roomPhotoField, tactSignHeightField, tactSignInclField, tactSignObsField;
    TextInputEditText roomLocaleValue, roomDescValue, vertSignObsValue, looseCarpetObsValue, accessFloorObsValue, roomObsValue,
            phoneHeightValue, phoneObsValue, biometryHeightValue, biometryObsValue, roomPhotoValue, tactSignHeightValue, tactSignInclValue, tactSignObsValue;
    MaterialButton cancelRegister, saveRegister;
    RadioGroup hasVertSingRadio, hasLooseCarpetRadio, hasAccessFloorRadio, phoneRadio, biometryRadio, workRoomRadio, tactSignRadio;
    FrameLayout childFrag;
    ConstraintLayout roomConst;

    ArrayList<TextInputEditText> roomScrollArray = new ArrayList<>();
    Bundle roomBundle;

    ViewModelEntry modelEntry;
    InspectionViewModel dataView;
    public static ViewModelFragments roomModelFragments;
    FragmentManager manager;

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

        dataView.setVisible(false);

        if (roomBundle.getInt(AMBIENT_ID) > 0)
            modelEntry.getRoomEntry(roomBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadRoomData);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, getViewLifecycleOwner(), (key, bundle) -> {
            if (roomNoEmptyFields(bundle) && bundle.getBoolean(CHILD_DATA_COMPLETE))
                saveUpdateRoomEntry(bundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!roomBundle.getBoolean(RECENT_ENTRY)) {
            modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), room -> {
                if (roomBundle.getBoolean(RECENT_ENTRY))
                    callNextFragment(room);
            });
        }
    }

    private void instantiateRoomViews(View view) {
//        TextView
        roomIdentifier = view.findViewById(R.id.room_register_header);
        roomIdentifier.setVisibility(View.GONE);
        vertSignError = view.findViewById(R.id.visual_sign_error);
        looseCarpetError = view.findViewById(R.id.carpet_error);
        accessFloorError = view.findViewById(R.id.room_accessible_floor_error);
        phoneHeader = view.findViewById(R.id.locale_has_intercom_header);
        biometryHeader = view.findViewById(R.id.locale_has_biometry_header);
        phoneError = view.findViewById(R.id.locale_has_intercom_error);
        biometryError = view.findViewById(R.id.locale_has_biometry_error);
        workRoomHeader = view.findViewById(R.id.work_room_header);
        workRoomError = view.findViewById(R.id.work_room_error);
        tacSignError = view.findViewById(R.id.room_tactile_sign_error);
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
        tactSignHeightField = view.findViewById(R.id.room_tact_sign_height_field);
        tactSignInclField = view.findViewById(R.id.room_tact_sign_incl_field);
        tactSignObsField = view.findViewById(R.id.tactile_sign_obs_field);
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
        tactSignHeightValue = view.findViewById(R.id.room_tact_sign_height_value);
        tactSignInclValue = view.findViewById(R.id.room_tact_sign_incl_value);
        tactSignObsValue = view.findViewById(R.id.tactile_sign_obs_value);
//        MaterialButton
        cancelRegister = view.findViewById(R.id.cancel_room);
        saveRegister = view.findViewById(R.id.continue_room);
//        RadioGroup
        workRoomRadio = view.findViewById(R.id.work_room_radio);
        hasVertSingRadio = view.findViewById(R.id.room_has_visual_sign_radio);
        hasLooseCarpetRadio = view.findViewById(R.id.room_has_carpet_radio);
        hasAccessFloorRadio = view.findViewById(R.id.room_accessible_floor_radio);
        phoneRadio = view.findViewById(R.id.locale_has_intercom_radio);
        biometryRadio = view.findViewById(R.id.locale_has_biometry_radio);
        tactSignRadio = view.findViewById(R.id.room_has_tactile_sign_radio);
//        FrameLayout
        childFrag = view.findViewById(R.id.room_child_fragment);
//        ConstLayout
        roomConst = view.findViewById(R.id.room_const_layout);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        roomModelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        dataView = new ViewModelProvider(requireActivity()).get(InspectionViewModel.class);
//        FragmentManager
        manager = requireActivity().getSupportFragmentManager();
//        Listeners
        hasVertSingRadio.setOnCheckedChangeListener(this::radioListener);
        hasLooseCarpetRadio.setOnCheckedChangeListener(this::radioListener);
        hasAccessFloorRadio.setOnCheckedChangeListener(this::radioListener);
        tactSignRadio.setOnCheckedChangeListener(this::radioListener);
        phoneRadio.setOnCheckedChangeListener(this::radioListener);
        biometryRadio.setOnCheckedChangeListener(this::radioListener);
        workRoomRadio.setOnCheckedChangeListener(this::radioListener);
        saveRegister.setOnClickListener(v -> saveContinueListener(roomBundle));
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
            case NUM_LIB:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new LibraryFragment()).commit();
                break;
            case NUM_SEC:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new SecretariatFragment()).commit();
            case NUM_COORD:
            case NUM_DIR:
            case NUM_TEACHER:
                phoneHeader.setVisibility(View.VISIBLE);
                biometryHeader.setVisibility(View.VISIBLE);
                phoneRadio.setVisibility(View.VISIBLE);
                biometryRadio.setVisibility(View.VISIBLE);
                break;
            case NUM_OTHER:
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
        if (bundle.getInt(AMBIENT_ID) == 0) {
            ViewModelEntry.insertRoomEntry(newRoom);
            if (bundle.getInt(ROOM_TYPE) != NUM_OTHER || (bundle.getInt(ROOM_TYPE) == NUM_OTHER && indexRadio(workRoomRadio) == 0))
                roomBundle.putBoolean(RECENT_ENTRY, true);
            else {
                Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        } else if (bundle.getInt(AMBIENT_ID) > 0) {
            newRoom.setRoomID(bundle.getInt(AMBIENT_ID));
            ViewModelEntry.updateRoom(newRoom);
            if (bundle.getInt(ROOM_TYPE) != NUM_OTHER || (bundle.getInt(ROOM_TYPE) == NUM_OTHER && indexRadio(workRoomRadio) == 0))
                callNextFragment(bundle);
            else {
                Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        } else {
            bundle.putInt(AMBIENT_ID, 0);
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveContinueListener(Bundle bundle) {
        if (bundle.getInt(ROOM_TYPE) == NUM_LIB || bundle.getInt(ROOM_TYPE) == NUM_SEC) {
            getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
        } else if (roomNoEmptyFields(bundle))
            saveUpdateRoomEntry(bundle);
        else
            Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();

    }

    private void callNextFragment(RoomEntry entry) {
        roomBundle.putInt(AMBIENT_ID, entry.getRoomID());
        RoomsRegisterFragmentTwo fragment = new RoomsRegisterFragmentTwo();
        fragment.setArguments(roomBundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
    }

    private void callNextFragment(Bundle bundle) {
        bundle.putBoolean(RECENT_ENTRY, true);
        RoomsRegisterFragmentTwo fragment = new RoomsRegisterFragmentTwo();
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
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
        tactSignHeightField.setErrorEnabled(false);
        tacSignError.setVisibility(View.GONE);
    }

    private boolean roomNoEmptyFields(Bundle bundle) {
        clearRoomNoEmptyFieldsErrors();
        int i = 0;
        if ((bundle.getInt(ROOM_TYPE) == NUM_OTHER || bundle.getInt(ROOM_TYPE) == NUM_CLASS) && TextUtils.isEmpty(roomLocaleValue.getText())) {
            i++;
            roomLocaleField.setError(getString(R.string.req_field_error));
        }
        if (bundle.getInt(ROOM_TYPE) == NUM_OTHER) {
            if (indexRadio(workRoomRadio) == -1) {
                i++;
                workRoomError.setVisibility(View.VISIBLE);
            } else if (indexRadio(workRoomRadio) == 0) {
                i += emptyFieldCont(bundle);
            }
        } else
            i += emptyFieldCont(bundle);

        return i == 0;
    }

    private int emptyFieldCont(Bundle bundle) {
        int i = 0;
        if (indexRadio(hasVertSingRadio) == -1) {
            i++;
            vertSignError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(tactSignRadio) == -1) {
            i++;
            tacSignError.setVisibility(View.VISIBLE);
        } else if (indexRadio(tactSignRadio) == 1) {
            if (TextUtils.isEmpty(tactSignHeightValue.getText())) {
                i++;
                tactSignHeightField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(hasLooseCarpetRadio) == -1) {
            i++;
            looseCarpetError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(hasAccessFloorRadio) == -1) {
            i++;
            accessFloorError.setVisibility(View.VISIBLE);
        }

        if (bundle.getInt(ROOM_TYPE) == NUM_COORD || bundle.getInt(ROOM_TYPE) == NUM_DIR || bundle.getInt(ROOM_TYPE) == NUM_TEACHER ||
                bundle.getInt(ROOM_TYPE) == NUM_SEC || bundle.getInt(ROOM_TYPE) == NUM_OTHER) {
            if (indexRadio(phoneRadio) == -1) {
                i++;
                phoneError.setVisibility(View.VISIBLE);
            } else if (indexRadio(phoneRadio) == 1) {
                if (TextUtils.isEmpty(phoneHeightValue.getText())) {
                    i++;
                    phoneHeightField.setError(getString(R.string.req_field_error));
                }
            }
            if (indexRadio(biometryRadio) == -1) {
                i++;
                biometryError.setVisibility(View.VISIBLE);
            } else if (indexRadio(biometryRadio) == 1) {
                if (TextUtils.isEmpty(biometryHeightValue.getText())) {
                    i++;
                    biometryHeightField.setError(getString(R.string.req_field_error));
                }
            }
        }
        return i;
    }

    private RoomEntry newRoomEntry(Bundle bundle) {
        String roomLocale = null, roomDescription = null, vertSignObs = null, looseCarpetObs = null, accessFloorObs = null, roomObs = null, secPcrSpaceObs = null,
                intPhoneObs = null, bioClockObs = null, photos = null, tactileSignObs = null;
        Integer isWork = null, libDistShelves = null, libLongCorridor = null, libPcrManeuver = null, libHasPC = null, libAccessPC = null, secHasFixedSeat = null,
                secHasPcrSpace = null, hasIntPhone = null, hasBioClock = null, hasVertSing = null, hasLooseCarpet = null, accessFloor = null, hasTactileSign = null;
        Double secPcrWidth = null, secPcrDepth = null, intPhoneHeight = null, bioClockHeight = null, tactHeight = null, tactIncl = null;

        if (!TextUtils.isEmpty(roomLocaleValue.getText()))
            roomLocale = String.valueOf(roomLocaleValue.getText());
        if (bundle.getInt(ROOM_TYPE) == NUM_OTHER) {
            if (!TextUtils.isEmpty(roomDescValue.getText()))
                roomDescription = String.valueOf(roomDescValue.getText());
            isWork = indexRadio(workRoomRadio);
            if (isWork == 0) {
                hasVertSing = indexRadio(hasVertSingRadio);
                if (!TextUtils.isEmpty(vertSignObsValue.getText()))
                    vertSignObs = String.valueOf(vertSignObsValue.getText());
                if (indexRadio(tactSignRadio) != -1) {
                    hasTactileSign = indexRadio(tactSignRadio);
                    if (hasTactileSign == 1)
                        if (!TextUtils.isEmpty(tactSignHeightValue.getText()))
                            tactHeight = Double.parseDouble(String.valueOf(tactSignHeightValue.getText()));
                    if (!TextUtils.isEmpty(tactSignInclValue.getText()))
                        tactIncl = Double.parseDouble(String.valueOf(tactSignInclValue.getText()));
                }
                if (!TextUtils.isEmpty(tactSignObsValue.getText()))
                    tactileSignObs = String.valueOf(tactSignObsValue.getText());
                hasLooseCarpet = indexRadio(hasLooseCarpetRadio);
                if (!TextUtils.isEmpty(looseCarpetObsValue.getText()))
                    looseCarpetObs = String.valueOf(looseCarpetObsValue.getText());
                accessFloor = indexRadio(hasAccessFloorRadio);
                if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
                    accessFloorObs = String.valueOf(accessFloorObsValue.getText());

                hasIntPhone = indexRadio(phoneRadio);
                if (hasIntPhone == 1) {
                    if (!TextUtils.isEmpty(phoneHeightValue.getText()))
                        intPhoneHeight = Double.parseDouble(String.valueOf(phoneHeightValue.getText()));
                    if (!TextUtils.isEmpty(phoneObsValue.getText()))
                        intPhoneObs = String.valueOf(phoneObsValue.getText());
                }

                hasBioClock = indexRadio(biometryRadio);
                if (hasBioClock == 1) {
                    if (!TextUtils.isEmpty(biometryHeightValue.getText()))
                        bioClockHeight = Double.parseDouble(String.valueOf(biometryHeightValue.getText()));
                    if (!TextUtils.isEmpty(biometryObsValue.getText()))
                        bioClockObs = String.valueOf(biometryObsValue.getText());
                }
            }
        } else {
            hasVertSing = indexRadio(hasVertSingRadio);
            if (!TextUtils.isEmpty(vertSignObsValue.getText()))
                vertSignObs = String.valueOf(vertSignObsValue.getText());
            if (indexRadio(tactSignRadio) != -1) {
                hasTactileSign = indexRadio(tactSignRadio);
                if (hasTactileSign == 1)
                    if (!TextUtils.isEmpty(tactSignHeightValue.getText()))
                        tactHeight = Double.parseDouble(String.valueOf(tactSignHeightValue.getText()));
                if (!TextUtils.isEmpty(tactSignInclValue.getText()))
                    tactIncl = Double.parseDouble(String.valueOf(tactSignInclValue.getText()));
            }
            if (!TextUtils.isEmpty(tactSignObsValue.getText()))
                tactileSignObs = String.valueOf(tactSignObsValue.getText());
            hasLooseCarpet = indexRadio(hasLooseCarpetRadio);
            if (!TextUtils.isEmpty(looseCarpetObsValue.getText()))
                looseCarpetObs = String.valueOf(looseCarpetObsValue.getText());
            accessFloor = indexRadio(hasAccessFloorRadio);
            if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
                accessFloorObs = String.valueOf(accessFloorObsValue.getText());

            if (bundle.getInt(ROOM_TYPE) == NUM_LIB) {
                LibParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                libDistShelves = parcel.getRightDistShelves();
                libLongCorridor = parcel.getHasLongCorridors();
                if (libLongCorridor == 1)
                    libPcrManeuver = parcel.getHasManeuverArea();
                libHasPC = parcel.getHasComputers();
                if (libHasPC == 1)
                    libAccessPC = parcel.getHasAccessComputer();

            } else if (bundle.getInt(ROOM_TYPE) == NUM_SEC) {
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

            if (bundle.getInt(ROOM_TYPE) == NUM_COORD || bundle.getInt(ROOM_TYPE) == NUM_DIR || bundle.getInt(ROOM_TYPE) == NUM_TEACHER ||
                    bundle.getInt(ROOM_TYPE) == NUM_SEC) {
                hasIntPhone = indexRadio(phoneRadio);
                if (hasIntPhone == 1) {
                    if (!TextUtils.isEmpty(phoneHeightValue.getText()))
                        intPhoneHeight = Double.parseDouble(String.valueOf(phoneHeightValue.getText()));
                    if (!TextUtils.isEmpty(phoneObsValue.getText()))
                        intPhoneObs = String.valueOf(phoneObsValue.getText());
                }

                hasBioClock = indexRadio(biometryRadio);
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
                secPcrDepth, secPcrSpaceObs, hasIntPhone, intPhoneHeight, intPhoneObs, hasBioClock, bioClockHeight, bioClockObs, photos, roomObs, hasTactileSign, tactHeight,
                tactIncl, tactileSignObs);
    }

    private void loadRoomData(RoomEntry roomEntry) {
        if (roomEntry.getRoomLocation() != null)
            roomLocaleValue.setText(roomEntry.getRoomLocation());
        if (roomEntry.getRoomDescription() != null)
            roomDescValue.setText(roomEntry.getRoomDescription());
        if (roomEntry.getRoomType() == NUM_OTHER) {
            if (roomEntry.getIsWorkRoom() != null && roomEntry.getIsWorkRoom() > -1) {
                checkRadioGroup(workRoomRadio, roomEntry.getIsWorkRoom());
                if (roomEntry.getIsWorkRoom() == 0) {
                    if (roomEntry.getRoomHasVertSing() != null && roomEntry.getRoomHasVertSing() != -1)
                        checkRadioGroup(hasVertSingRadio, roomEntry.getRoomHasVertSing());
                    if (roomEntry.getRoomVertSignObs() != null)
                        vertSignObsValue.setText(roomEntry.getRoomVertSignObs());
                    if (roomEntry.getHasTactSign() != null && roomEntry.getHasTactSign() > -1) {
                        checkRadioGroup(tactSignRadio, roomEntry.getHasTactSign());
                        if (roomEntry.getHasTactSign() == 1) {
                            if (roomEntry.getTactSignHeight() != null)
                                tactSignHeightValue.setText(String.valueOf(roomEntry.getTactSignHeight()));
                            if (roomEntry.getTactSignIncl() != null)
                                tactSignInclValue.setText(String.valueOf(roomEntry.getTactSignIncl()));
                        }
                    }
                    if (roomEntry.getTactSignObs() != null)
                        tactSignObsValue.setText(roomEntry.getTactSignObs());
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
            if (roomEntry.getHasTactSign() != null && roomEntry.getHasTactSign() > -1) {
                checkRadioGroup(tactSignRadio, roomEntry.getHasTactSign());
                if (roomEntry.getHasTactSign() == 1) {
                    if (roomEntry.getTactSignHeight() != null)
                        tactSignHeightValue.setText(String.valueOf(roomEntry.getTactSignHeight()));
                    if (roomEntry.getTactSignIncl() != null)
                        tactSignInclValue.setText(String.valueOf(roomEntry.getTactSignIncl()));
                }
            }
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

            if (roomEntry.getRoomType() == NUM_LIB || roomEntry.getRoomType() == NUM_SEC)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, roomBundle);
        }

        if (roomEntry.getRoomObs() != null)
            roomObsValue.setText(roomEntry.getRoomObs());
        if (roomEntry.getRoomPhoto() != null)
            roomPhotoValue.setText(roomEntry.getRoomPhoto());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        if (radio == workRoomRadio) {
            if (index == 0) {
                roomConst.setVisibility(View.VISIBLE);
                phoneHeader.setVisibility(View.VISIBLE);
                biometryHeader.setVisibility(View.VISIBLE);
                phoneRadio.setVisibility(View.VISIBLE);
                biometryRadio.setVisibility(View.VISIBLE);
                saveRegister.setText(getString(R.string.label_button_proceed_register));
            } else {
                roomConst.setVisibility(View.GONE);
                saveRegister.setText(getString(R.string.label_button_save));
            }
        } else if (radio == hasVertSingRadio) {
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