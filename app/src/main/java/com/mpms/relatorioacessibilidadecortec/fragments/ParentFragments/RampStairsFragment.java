package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class RampStairsFragment extends Fragment implements TagInterface {

    private int recentEntry = 0;
    private int updateEntry = 0;

    TextInputLayout rampStairsLocField, photoField;
    TextInputEditText rampStairsLocValue, photoValue;
    TextView registerHeader;
    Button cancelRampStairs, proceedRegister;

    String rampStairsLocation;
    Bundle rampStairsBundle;

    ViewModelFragments modelFragments;
    ViewModelEntry modelEntry;

    public RampStairsFragment() {
        // Required empty public constructor
    }

    public static RampStairsFragment newInstance() {
        return new RampStairsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            rampStairsBundle = new Bundle(this.getArguments());
        else
            rampStairsBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ramp_stairs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRampStairsViews(view);
        setRampStairsTemplate(rampStairsBundle.getInt(RAMP_OR_STAIRS));

//      Usado quando um novo cadastro é realizado, colocando o ID no bundle e chamando o próximo fragmento
        modelEntry.getLastRampStairsEntry().observe(getViewLifecycleOwner(), rampStairs -> {
            if (recentEntry == 1) {
                recentEntry = 0;
                int rampStairsID = rampStairs.getRampStairsID();
                rampStairsBundle.putInt(RAMP_STAIRS_ID, rampStairsID);
                openFlightFragment();
            }
        });

//      Usado quando uma entrada deve ser atualizada,
        modelEntry.getRampStairsEntry(rampStairsBundle.getInt(RAMP_STAIRS_ID)).observe(getViewLifecycleOwner(), update -> {
            if (updateEntry == 1) {
                updateEntry = 0;
                openFlightFragment();
            }
        });

//      Preenchimento dos campos da tela
        if (rampStairsBundle.getInt(RAMP_STAIRS_ID) > 0)
            modelEntry.getRampStairsEntry(rampStairsBundle.getInt(RAMP_STAIRS_ID))
                    .observe(getViewLifecycleOwner(), this::loadRampStairsData);

        proceedRegister.setOnClickListener(v -> {
            if (checkRampStairsFields()) {
                if (rampStairsBundle.getInt(RAMP_STAIRS_ID) == 0) {
                    rampStairsBundle.putInt(InspectionActivity.ALLOW_UPDATE, 1);
                    RampStairsEntry newEntry = newRampOrStaircase(rampStairsBundle);
                    ViewModelEntry.insertRampStairs(newEntry);
                    recentEntry = 1;
                    rampStairsBundle.putBoolean(RECENT_ENTRY, true);
                } else if (rampStairsBundle.getInt(RAMP_STAIRS_ID) > 0) {
//                    Testar para verificar se não causa novas entradas
                    rampStairsBundle.putInt(InspectionActivity.ALLOW_UPDATE, 1);
                    RampStairsEntry upEntry = newRampOrStaircase(rampStairsBundle);
                    upEntry.setRampStairsID(rampStairsBundle.getInt(RAMP_STAIRS_ID));
                    ViewModelEntry.updateRampStairs(upEntry);
                    updateEntry = 1;
                } else {
                    errorEscape();
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

    }

    private void instantiateRampStairsViews(View view) {
        rampStairsLocField = view.findViewById(R.id.ramp_stairs_location_field);
        photoField = view.findViewById(R.id.ramp_stairs_photo_field);

        rampStairsLocValue = view.findViewById(R.id.ramp_stairs_location_value);
        photoValue = view.findViewById(R.id.ramp_stairs_photo_value);

        registerHeader = view.findViewById(R.id.ramp_stairs_header);

        cancelRampStairs = view.findViewById(R.id.cancel_ramp_stairs);
        proceedRegister = view.findViewById(R.id.save_ramp_stairs);

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        cancelRampStairs.setOnClickListener(this::cancelClick);
    }

    private void cancelClick(View v) {
        if (rampStairsBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteOneRampStairs(rampStairsBundle.getInt(RAMP_STAIRS_ID));
                rampStairsBundle = null;
                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    public void setRampStairsTemplate(int pickedOption) {
        switch (pickedOption) {
            case 1:
                rampStairsLocField.setHint(getString(R.string.hint_staircase_location));
                registerHeader.setText(getText(R.string.label_staircase_register_header));
                break;
            case 2:
                rampStairsLocField.setHint(getString(R.string.hint_ramp_location));
                registerHeader.setText(getText(R.string.label_ramp_register_header));
                break;
            default:
                errorEscape();
                break;
        }
    }

    private void openFlightFragment() {
        RampStairsFlightListFrag flightListFrag = RampStairsFlightListFrag.newInstance();
        flightListFrag.setArguments(rampStairsBundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, flightListFrag).addToBackStack(null).commit();
    }

    public RampStairsEntry newRampOrStaircase(Bundle bundle) {
        Integer extID = null, parkID = null, roomID = null, circID = null;
        String photo = null;
        rampStairsLocation = String.valueOf(rampStairsLocValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        if (bundle.getInt(AMBIENT_TYPE) == 1)
            extID = bundle.getInt(AMBIENT_ID);
        else if (bundle.getInt(AMBIENT_TYPE) == 3)
            parkID = bundle.getInt(PARKING_ID);
        else if (bundle.getInt(AMBIENT_TYPE) == 4)
            roomID = bundle.getInt(AMBIENT_ID);
        else if (bundle.getInt(CIRC_ID) != 0)
            circID = bundle.getInt(CIRC_ID);

        return new RampStairsEntry(extID, parkID, roomID, circID, bundle.getInt(RAMP_OR_STAIRS), rampStairsLocation, photo);
    }

    public boolean checkRampStairsFields() {
        clearRampStairsFieldError();
        int error = 0;
        if (TextUtils.isEmpty(rampStairsLocValue.getText())) {
            error++;
            rampStairsLocField.setError(getString(R.string.req_field_error));
        }
        return error == 0;
    }

    public void clearRampStairsFieldError() {
        rampStairsLocField.setErrorEnabled(false);
    }

    public void loadRampStairsData(RampStairsEntry rampStairs) {
        if (rampStairs.getRampStairsLocation() != null)
            rampStairsLocValue.setText(rampStairs.getRampStairsLocation());
        if (rampStairs.getRampStairsPhoto() != null)
            photoValue.setText(rampStairs.getRampStairsPhoto());
    }

    public void errorEscape() {
        Toast.makeText(getContext(), "Houve um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
        requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }


}