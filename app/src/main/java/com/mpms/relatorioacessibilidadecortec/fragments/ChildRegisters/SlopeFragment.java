package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.InclinationParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;


public class SlopeFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout slopeLocalField, slopeObsField, slopePhotoField;
    TextInputEditText slopeLocalValue, slopeObsValue, slopePhotoValue;
    MaterialButton cancelSlope, saveSlope;

    Bundle slopeBundle;

    ViewModelEntry modelEntry;


    public SlopeFragment() {
        // Required empty public constructor
    }

    public static SlopeFragment newInstance() {
        return new SlopeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            slopeBundle = new Bundle(getArguments());
        else
            slopeBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slope, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSlopeViews(view);

        if (slopeBundle.getInt(SLOPE_ID) > 0) {
            modelEntry.getOneSlope(slopeBundle.getInt(SLOPE_ID)).observe(getViewLifecycleOwner(), this::loadSlopeData);
            getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, slopeBundle);
        }

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyField() && bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                SlopeEntry slope = newSlope(bundle);
                if (bundle.getInt(SLOPE_ID) > 0) {
                    slope.setSlopeID(bundle.getInt(SLOPE_ID));
                    modelEntry.updateSlope(slope);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (bundle.getInt(SLOPE_ID) == 0) {
                    modelEntry.insertSlope(slope);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        saveSlope.setOnClickListener(v -> getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, slopeBundle));

        cancelSlope.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateSlopeViews(View view) {
//        TextInputLayout
        slopeLocalField = view.findViewById(R.id.slope_placement_field);
        slopeObsField = view.findViewById(R.id.slope_obs_field);
        slopePhotoField = view.findViewById(R.id.slope_photo_field);
//        TextInputEditText
        slopeLocalValue = view.findViewById(R.id.slope_placement_value);
        slopeObsValue = view.findViewById(R.id.slope_obs_value);
        slopePhotoValue = view.findViewById(R.id.slope_photo_value);
//        MaterialButton
        cancelSlope = view.findViewById(R.id.cancel_slope);
        saveSlope = view.findViewById(R.id.save_slope);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean noEmptyField() {
        int i = 0;
        slopeLocalField.setErrorEnabled(false);
        if (TextUtils.isEmpty(slopeLocalValue.getText())) {
            i++;
            slopeLocalField.setError(getString(R.string.req_field_error));
        }
        return i == 0;
    }

    private void loadSlopeData(SlopeEntry slope) {
        if (slope.getSlopeLocation() != null)
            slopeLocalValue.setText(slope.getSlopeLocation());
        if (slope.getSlopeObs() != null)
            slopeObsValue.setText(slope.getSlopeObs());
        if (slope.getSlopePhoto() != null)
            slopePhotoValue.setText(slope.getSlopePhoto());
    }

    private SlopeEntry newSlope(Bundle bundle) {
        String slopeLoc = null, obs = null, photo = null;
        int hasRamp = 0;
        double slopeHeight = 0;
        Double angle1 = null, angle2 = null, angle3 = null, angle4 = null;
        Integer circ = null, room = null, qnt = null;

        if (bundle.getInt(CIRC_ID) > 0)
            circ = bundle.getInt(CIRC_ID);
        else if (bundle.getInt(AMBIENT_ID) > 0)
            room = bundle.getInt(AMBIENT_ID);

        if (!TextUtils.isEmpty(slopeLocalValue.getText()))
            slopeLoc = String.valueOf(slopeLocalValue.getText());

        InclinationParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        if (parcel != null) {
            slopeHeight = parcel.getInclHeight();
            hasRamp = parcel.getHasInclSlope();
            if (hasRamp == 1) {
                qnt = parcel.getInclQnt();
                switch (qnt) {
                    case 4:
                        angle4 = parcel.getInclMeasure4();
                    case 3:
                        angle3 = parcel.getInclMeasure3();
                    case 2:
                        angle2 = parcel.getInclMeasure2();
                    case 1:
                        angle1 = parcel.getInclMeasure1();
                        break;
                    default:
                        break;
                }
            }
        }

        if (!TextUtils.isEmpty(slopeObsValue.getText()))
            obs = String.valueOf(slopeObsValue.getText());
        if (!TextUtils.isEmpty(slopePhotoValue.getText()))
            photo = String.valueOf(slopePhotoValue.getText());

        return new SlopeEntry(circ,room,slopeLoc, slopeHeight, hasRamp, qnt, angle1, angle2, angle3, angle4, obs, photo);
    }

    private void clearFields() {
        slopeLocalValue.setText(null);
        slopeObsValue.setText(null);
        slopePhotoValue.setText(null);
        getChildFragmentManager().setFragmentResult(CLEAR_CHILD_DATA, slopeBundle);
    }

}