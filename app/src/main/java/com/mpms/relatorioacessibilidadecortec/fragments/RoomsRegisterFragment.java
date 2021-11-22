package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddCounterDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddDoorDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddFreeSpaceDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddSwitchDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddTableDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddWindowDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.CafeteriaFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ClassroomFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.LibraryFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SecretariatFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

import java.util.ArrayList;
import java.util.Objects;

import static com.mpms.relatorioacessibilidadecortec.fragments.RoomRegisterListFragment.ROOM_TYPE;

public class RoomsRegisterFragment extends Fragment {

//    TODO - Implementar o método de recuperação de dados já cadastrados para a atualização

    public static final String ROOM_ID_VALUE = "ROOM_ID_VALUE";

    TextInputLayout roomLocationField, obsVisualSignField, obsTactileSignField, obsLooseCarpetField, roomObsField;
    TextInputEditText roomLocationValue, obsVisualSignValue, obsTactileSignValue, obsLooseCarpetValue, roomObsValue;
    TextView visualSignError, tactileSignError, carpetError;
    MaterialButton cancelRoomRegister, saveRoomRegister, doorRegister, switchRegister, windowRegister, tableRegister, freeSpaceRegister;
    RadioGroup hasVisualSignRadio, hasTactileSignRadio, hasLooseCarpetRadio;

    Integer hasVisSign, hasTactSign, hasLooseCarpet, libShelvesDistOK, libPcrManeuverOK, libAccessPcOK, secFixedSeat, secHasPcrSpace;
    Double classBoardHeight, secWidthPcrSpace, secDepthPcrSpace;
    String roomLocation, obsVisSign, obsTactSign, obsLooseCarpet, secObsPCRSpace, obsRoom;
    public int update = 0;
    public int recentRoomID = 0;
    private int buttonChoice = -1;

    FragmentManager manager;
    Bundle roomRegBundle = new Bundle();

    ViewModelFragments modelFragments;
    ViewModelEntry modelEntry;

    ArrayList<TextInputEditText> roomObsArray = new ArrayList<>();

    public RoomsRegisterFragment() {
        // Required empty public constructor
    }

    public static RoomsRegisterFragment newInstance() {
        return new RoomsRegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rooms_register, container, false);
        setHeaderText(rootView);

        if (this.getArguments() != null) {
            roomRegBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
            roomRegBundle.putInt(ROOM_TYPE, this.getArguments().getInt(ROOM_TYPE));
        }

        switch (roomRegBundle.getInt(ROOM_TYPE)) {
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new LibraryFragment()).commit();
                break;
            case 10:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new CafeteriaFragment()).commit();
                break;
            case 11:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new ClassroomFragment()).commit();
                break;
            case 15:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new SecretariatFragment()).commit();
                break;
            default:
                break;
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRoomViews(view);
        allowRoomObsScroll();

        modelFragments.getCounterClick().observe(getViewLifecycleOwner(), counter -> {
            if (Objects.equals(modelFragments.counterClick.getValue(), 1)) {
                buttonChoice = 5;
                saveUpdateDialogClick();
                modelFragments.setCounterClick(0);
            }
        });

//        Este método só funciona para salas recem-criadas. Não será util para atualização de salas.
//        TODO - Alterar o método de seleção de sala alterada
        modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom -> {
            if (update == 0 && buttonChoice != -1) {
//                recentRoomID = lastRoom.getRoomID();
                roomRegBundle.putInt(ROOM_ID_VALUE, lastRoom.getRoomID());
                update++;
            }

            switch (buttonChoice) {
                case 0:
                    addDoorDialog();
                    break;
                case 1:
                    addSwitchDialog();
                    break;
                case 2:
                    addWindowDialog();
                    break;
                case 3:
                    addTableDialog();
                    break;
                case 4:
                    addFreeSpaceDialog();
                    break;
                case 5:
                    addCounterDialog();
                    break;
                default:
                    break;
            }
            buttonChoice = -1;
        });

        modelFragments.getRoomBundle().observe(getViewLifecycleOwner(), roomBundle -> {
            if (roomBundle != null) {
                roomBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, roomRegBundle.getInt(SchoolRegisterActivity.SCHOOL_ID));
                if (update == 0) {
                    RoomEntry newEntry = newRoomEntry(roomBundle);
                    ViewModelEntry.insertRoomEntry(newEntry);
                    Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();

                } else {
                    RoomEntry updateEntry = newRoomEntry(roomBundle);
                    updateEntry.setRoomID(recentRoomID);
                    ViewModelEntry.updateRoom(updateEntry);
                    Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                }
                clearRoomFields();
                modelFragments.setRoomBundle(null);
                update = 0;
            }
        });

        doorRegister.setOnClickListener(v -> {
            buttonChoice = 0;
            saveUpdateDialogClick();
        });

        switchRegister.setOnClickListener(v -> {
            buttonChoice = 1;
            saveUpdateDialogClick();
        });

        windowRegister.setOnClickListener(v -> {
            buttonChoice = 2;
            saveUpdateDialogClick();
        });

        tableRegister.setOnClickListener(v -> {
            buttonChoice = 3;
            saveUpdateDialogClick();
        });

        freeSpaceRegister.setOnClickListener(v -> {
            buttonChoice = 4;
            saveUpdateDialogClick();
        });

        cancelRoomRegister.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveRoomRegister.setOnClickListener(v -> saveRoomEntry());
    }

    private void instantiateRoomViews(View view) {
//        TextInputLayout
        roomLocationField = view.findViewById(R.id.room_location_field);
        obsVisualSignField = view.findViewById(R.id.visual_sign_obs_field);
        obsTactileSignField = view.findViewById(R.id.tactile_sign_obs_field);
        obsLooseCarpetField = view.findViewById(R.id.carpet_obs_field);
        roomObsField = view.findViewById(R.id.room_obs_field);
//        TextInputEditText
        roomLocationValue = view.findViewById(R.id.room_location_value);
        obsVisualSignValue = view.findViewById(R.id.visual_sign_obs_value);
        obsTactileSignValue = view.findViewById(R.id.tactile_sign_obs_value);
        obsLooseCarpetValue = view.findViewById(R.id.carpet_obs_value);
        roomObsValue = view.findViewById(R.id.room_obs_value);
//        TextView
        visualSignError = view.findViewById(R.id.visual_sign_error);
        tactileSignError = view.findViewById(R.id.tactile_sign_error);
        carpetError = view.findViewById(R.id.carpet_error);
//        MaterialButton
        cancelRoomRegister = view.findViewById(R.id.cancel_room);
        saveRoomRegister = view.findViewById(R.id.save_room);
        doorRegister = view.findViewById(R.id.room_add_door_button);
        switchRegister = view.findViewById(R.id.room_add_switch_button);
        windowRegister = view.findViewById(R.id.room_add_window_button);
        tableRegister = view.findViewById(R.id.room_add_tables_button);
        freeSpaceRegister = view.findViewById(R.id.room_add_free_space_button);
//        RadioGroup
        hasVisualSignRadio = view.findViewById(R.id.room_has_visual_sign_radio);
        hasTactileSignRadio = view.findViewById(R.id.room_has_tactile_sign_radio);
        hasLooseCarpetRadio = view.findViewById(R.id.room_has_carpet_radio);
//        ViewModel
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        FragmentManager
        manager = getChildFragmentManager();

    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addObsFieldsToArray() {
        roomObsArray.add(obsVisualSignValue);
        roomObsArray.add(obsTactileSignValue);
        roomObsArray.add(obsLooseCarpetValue);
        roomObsArray.add(roomObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowRoomObsScroll() {
        addObsFieldsToArray();
        for (TextInputEditText obsScroll : roomObsArray) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }

    public void saveRoomEntry() {
        int chosenOption = roomRegBundle.getInt(ROOM_TYPE);
        if (chosenOption == 3 || chosenOption == 10 || chosenOption == 11 || chosenOption == 15) {
            if (update >= 0) {
                if (checkEmptyRoomFields())
                    modelFragments.setSaveAttemptRooms(1);
                else
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Houve um Erro. Favor, recomeçar cadastro", Toast.LENGTH_SHORT).show();
                clearRoomFields();
            }
        } else if (chosenOption > 0) {
            if (update == 0) {
                if (checkEmptyRoomFields()) {
                    RoomEntry newEntry = newRoomEntry(roomRegBundle);
                    ViewModelEntry.insertRoomEntry(newEntry);
                    clearRoomFields();
                    Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else if (update > 0) {
                RoomEntry newEntry = newRoomEntry(roomRegBundle);
                newEntry.setRoomID(roomRegBundle.getInt(ROOM_ID_VALUE));
                ViewModelEntry.updateRoom(newEntry);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                clearRoomFields();
            } else {
                Toast.makeText(getContext(), "Houve um Erro. Favor, recomeçar cadastro", Toast.LENGTH_SHORT).show();
                clearRoomFields();
            }
        }

    }

    public void saveUpdateDialogClick() {
        if (update == 0) {
            RoomEntry newEntry = newRoomEntry(roomRegBundle);
            ViewModelEntry.insertRoomEntry(newEntry);
        } else if (update > 0) {
//            TODO - Clicar nos botões para adicionar dados da sala NÃO deve atualizar o cadastro da sala até que a mesma seja atualizada no botão salvar
            RoomEntry newEntry = newRoomEntry(roomRegBundle);
            newEntry.setRoomID(roomRegBundle.getInt(ROOM_ID_VALUE));
            ViewModelEntry.updateRoom(newEntry);
        } else {
            Toast.makeText(getContext(), "Houve um Erro. Favor, recomeçar cadastro", Toast.LENGTH_SHORT).show();
            clearRoomFields();
        }

    }

    public void setHeaderText(View v) {
        int chosenOption = roomRegBundle.getInt(ROOM_TYPE);
        TextView headerText = v.findViewById(R.id.room_register_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }

    public int getCheckedRadioButton(RadioGroup radioGroup) {
        return radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
    }

    public void clearRoomFields() {
        roomLocationValue.setText(null);
        hasTactileSignRadio.clearCheck();
        hasVisualSignRadio.clearCheck();
        hasLooseCarpetRadio.clearCheck();
        obsTactileSignValue.setText(null);
        obsVisualSignValue.setText(null);
        obsLooseCarpetValue.setText(null);
        roomObsValue.setText(null);
    }

    public boolean checkEmptyRoomFields() {
        clearRoomErrors();
        int i = 0;
        if (TextUtils.isEmpty(roomLocationValue.getText())) {
            i++;
            roomLocationField.setError(getString(R.string.blank_field_error));
        }
        if (hasVisualSignRadio.getCheckedRadioButtonId() == -1) {
            i++;
            visualSignError.setVisibility(View.VISIBLE);
        }
        if (hasTactileSignRadio.getCheckedRadioButtonId() == -1) {
            i++;
            tactileSignError.setVisibility(View.VISIBLE);
        }
        if (hasLooseCarpetRadio.getCheckedRadioButtonId() == -1) {
            i++;
            carpetError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void clearRoomErrors() {
        roomLocationField.setErrorEnabled(false);
        visualSignError.setVisibility(View.GONE);
        tactileSignError.setVisibility(View.GONE);
        carpetError.setVisibility(View.GONE);
    }

    public RoomEntry newRoomEntry(Bundle bundle) {
        int chosenOption = roomRegBundle.getInt(ROOM_TYPE);

        roomLocation = String.valueOf(roomLocationValue.getText());
        hasVisSign = getCheckedRadioButton(hasVisualSignRadio);
        if (!TextUtils.isEmpty(obsVisualSignValue.getText()))
            obsVisSign = String.valueOf(obsVisualSignValue.getText());
        hasTactSign = getCheckedRadioButton(hasTactileSignRadio);
        if (!TextUtils.isEmpty(obsTactileSignValue.getText()))
            obsTactSign = String.valueOf(obsTactileSignValue.getText());
        hasLooseCarpet = getCheckedRadioButton(hasLooseCarpetRadio);
        if (!TextUtils.isEmpty(obsLooseCarpetValue.getText()))
            obsLooseCarpet = String.valueOf(obsVisualSignValue.getText());
        if (!TextUtils.isEmpty(roomObsValue.getText()))
            obsRoom = String.valueOf(roomObsValue.getText());

        switch (chosenOption) {
            case 3:
                libShelvesDistOK = bundle.getInt(LibraryFragment.DISTANCE_SHELVES, 0);
                libPcrManeuverOK = bundle.getInt(LibraryFragment.MANEUVER_PCR, 0);
                libAccessPcOK = bundle.getInt(LibraryFragment.COMPUTER_ACCESSIBLE, 0);
                break;
            case 11:
//                TODO - Alterar para cadastro de quadros negros depois, será removido
                classBoardHeight = bundle.getDouble(ClassroomFragment.BOARD_HEIGHT, 0.0);
                break;
            case 15:
                secFixedSeat = bundle.getInt(SecretariatFragment.HAS_FIXED_SEATS, 0);
                secHasPcrSpace = bundle.getInt(SecretariatFragment.HAS_PCR_SPACE, 0);
                secWidthPcrSpace = bundle.getDouble(SecretariatFragment.PCR_WIDTH, 0.0);
                secDepthPcrSpace = bundle.getDouble(SecretariatFragment.PCR_DEPTH, 0.0);
                secObsPCRSpace = bundle.getString(SecretariatFragment.PCR_OBS, null);
                break;
            default:
                break;
        }

        return new RoomEntry(roomRegBundle.getInt(SchoolRegisterActivity.SCHOOL_ID), chosenOption, roomLocation, hasVisSign,
                obsVisSign, hasTactSign, obsTactSign, hasLooseCarpet, obsLooseCarpet, libShelvesDistOK, libPcrManeuverOK,
                libAccessPcOK, secFixedSeat, secHasPcrSpace, secWidthPcrSpace, secDepthPcrSpace, secObsPCRSpace, obsRoom);
    }

    private void addDoorDialog() {
        AddDoorDialog.displayDoorDialog(requireActivity().getSupportFragmentManager(), roomRegBundle);
    }

    private void addSwitchDialog() {
        AddSwitchDialog.displaySwitchDialog(requireActivity().getSupportFragmentManager(), roomRegBundle);
    }

    private void addWindowDialog() {
        AddWindowDialog.displayWindowDialog(requireActivity().getSupportFragmentManager(), roomRegBundle);
    }

    private void addFreeSpaceDialog() {
        AddFreeSpaceDialog.displayFreeSpaceDialog(requireActivity().getSupportFragmentManager(), roomRegBundle);
    }

    private void addTableDialog() {
        AddTableDialog.addTableDialog(requireActivity().getSupportFragmentManager(), roomRegBundle);
    }

    private void addCounterDialog() {
        AddCounterDialog.displayCounterDialog(requireActivity().getSupportFragmentManager(), roomRegBundle);
    }

}