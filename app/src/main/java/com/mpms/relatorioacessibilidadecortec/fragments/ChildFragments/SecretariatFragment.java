package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SecParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class SecretariatFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout pcrSpaceWidthField, pcrSpaceDepthField, pcrSpaceObsField;
    TextInputEditText pcrSpaceWidthValue, pcrSpaceDepthValue, pcrSpaceObsValue;
    RadioGroup hasFixedSeatsRadio, hasPCRSpaceRadio;
    TextView fixedSeatsError, PCRSpaceHeader, PCRSpaceError;

    ViewModelEntry modelEntry;

    public SecretariatFragment() {
        // Required empty public constructor
    }

    public static SecretariatFragment newInstance(int dropdownChoice) {
        return new SecretariatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secretariat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        secretariatInstantiateView(view);

        getParentFragmentManager().setFragmentResultListener(LOAD_CHILD_DATA, this, (key, bundle) ->
                modelEntry.getRoomEntry(bundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadSecretariatData));

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (!bundle.getBoolean(ADD_ITEM_REQUEST)) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, checkEmptySecretariatFields());
            }
            gatherSecData(bundle);
            getParentFragmentManager().setFragmentResult(InspectionActivity.CHILD_DATA_LISTENER, bundle);
        });

        getParentFragmentManager().setFragmentResultListener(CLEAR_CHILD_DATA, this, (key, bundle) -> clearSecretariatFields());


    }

    public void secretariatInstantiateView(View view) {
//        TextInputLayout
        pcrSpaceWidthField = view.findViewById(R.id.PCR_space_width_field);
        pcrSpaceDepthField = view.findViewById(R.id.PCR_space_depth_field);
        pcrSpaceObsField = view.findViewById(R.id.PCR_space_obs_field);
//        TextInputEditText
        pcrSpaceWidthValue = view.findViewById(R.id.PCR_space_width_value);
        pcrSpaceDepthValue = view.findViewById(R.id.PCR_space_depth_value);
        pcrSpaceObsValue = view.findViewById(R.id.PCR_space_obs_value);
//        TextView
        fixedSeatsError = view.findViewById(R.id.fixed_seats_error);
        PCRSpaceHeader = view.findViewById(R.id.label_has_PCR_space);
        PCRSpaceError = view.findViewById(R.id.PCR_space_error);
//        RadioGroup
        hasFixedSeatsRadio = view.findViewById(R.id.has_fixed_seats_radio);
        hasPCRSpaceRadio = view.findViewById(R.id.has_PCR_space_radio);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        hasFixedSeatsRadio.setOnCheckedChangeListener(this::radioListener);
        hasPCRSpaceRadio.setOnCheckedChangeListener(this::radioListener);
        allowObsScroll(pcrSpaceObsValue);
    }

    public void gatherSecData(Bundle bundle) {
        int hasFixedSeats;
        Integer hasPCR = null;
        Double pcrWidth = null, pcrDepth = null;
        String pcrObs = null;

        hasFixedSeats = indexRadio(hasFixedSeatsRadio);
        if (hasFixedSeats == 1) {
            hasPCR = indexRadio(hasPCRSpaceRadio);
            if (hasPCR == 1) {
                pcrWidth = Double.parseDouble(String.valueOf(pcrSpaceWidthValue.getText()));
                pcrDepth = Double.parseDouble(String.valueOf(pcrSpaceDepthValue.getText()));
                pcrObs = String.valueOf(pcrSpaceObsValue.getText());
            }
        }

        SecParcel parcel = new SecParcel(hasFixedSeats, hasPCR, pcrWidth, pcrDepth, pcrObs);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    public void loadSecretariatData(RoomEntry room) {
        if (room.getSecHasFixedSeats() != null && room.getSecHasFixedSeats() > -1) {
            checkRadioGroup(hasFixedSeatsRadio, room.getSecHasFixedSeats());
            if (room.getSecHasFixedSeats() == 1) {
                if (room.getSecHasPcrSpace() != null && room.getSecHasPcrSpace() > -1) {
                   checkRadioGroup(hasPCRSpaceRadio, room.getSecHasPcrSpace());
                    if (room.getSecHasPcrSpace() == 1) {
                        if (room.getSecPcrSpaceWidth() != null)
                            pcrSpaceWidthValue.setText(String.valueOf(room.getSecPcrSpaceWidth()));
                        if (room.getSecPcrSpaceDepth() != null)
                            pcrSpaceDepthValue.setText(String.valueOf(room.getSecPcrSpaceDepth()));
                        if (room.getSecPCRSpaceObs() != null) {
                            pcrSpaceObsValue.setText(room.getSecPCRSpaceObs());
                        }
                    }
                }
            }
        }
    }

    public boolean checkEmptySecretariatFields() {
        clearSecretariatErrors();
        int error = 0;
        if (indexRadio(hasFixedSeatsRadio) == -1) {
            error++;
            fixedSeatsError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasFixedSeatsRadio) == 1) {
            if (indexRadio(hasPCRSpaceRadio) == -1) {
                error++;
                PCRSpaceError.setVisibility(View.VISIBLE);
            } else if (indexRadio(hasPCRSpaceRadio) == 1) {
                if (TextUtils.isEmpty(pcrSpaceWidthValue.getText())){
                    error++;
                    pcrSpaceWidthField.setError(getText(R.string.req_field_error));
                }
                if (TextUtils.isEmpty(pcrSpaceDepthValue.getText())) {
                    pcrSpaceDepthField.setError(getText(R.string.req_field_error));
                    error++;
                }
            }
        }
        return error == 0;
    }

    public void clearSecretariatErrors() {
        fixedSeatsError.setVisibility(View.GONE);
        PCRSpaceError.setVisibility(View.GONE);
        pcrSpaceWidthField.setErrorEnabled(false);
        pcrSpaceDepthField.setErrorEnabled(false);
    }

    public void clearSecretariatFields() {
        hasFixedSeatsRadio.clearCheck();
        hasPCRSpaceRadio.clearCheck();
        pcrSpaceWidthValue.setText(null);
        pcrSpaceDepthValue.setText(null);
        pcrSpaceObsValue.setText(null);
        PCRSpaceHeader.setVisibility(View.GONE);
        hasPCRSpaceRadio.setVisibility(View.GONE);
        pcrSpaceWidthField.setVisibility(View.GONE);
        pcrSpaceDepthField.setVisibility(View.GONE);
        pcrSpaceObsField.setVisibility(View.GONE);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        if (radio == hasFixedSeatsRadio) {
            if (index == 1) {
                PCRSpaceHeader.setVisibility(View.VISIBLE);
                hasPCRSpaceRadio.setVisibility(View.VISIBLE);
                pcrSpaceObsField.setVisibility(View.VISIBLE);
            } else {
                hasPCRSpaceRadio.clearCheck();
                pcrSpaceWidthValue.setText(null);
                pcrSpaceDepthValue.setText(null);
                pcrSpaceObsValue.setText(null);
                PCRSpaceHeader.setVisibility(View.GONE);
                hasPCRSpaceRadio.setVisibility(View.GONE);
                pcrSpaceWidthField.setVisibility(View.GONE);
                pcrSpaceDepthField.setVisibility(View.GONE);
                pcrSpaceObsField.setVisibility(View.GONE);
            }
        } else {
            if (index == 1) {
                pcrSpaceWidthField.setVisibility(View.VISIBLE);
                pcrSpaceDepthField.setVisibility(View.VISIBLE);
            } else {
                pcrSpaceWidthValue.setText(null);
                pcrSpaceDepthValue.setText(null);
                pcrSpaceWidthField.setVisibility(View.GONE);
                pcrSpaceDepthField.setVisibility(View.GONE);
            }
        }
    }
}