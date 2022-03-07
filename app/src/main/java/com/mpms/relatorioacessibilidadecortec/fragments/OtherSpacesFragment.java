package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class OtherSpacesFragment extends Fragment {

    TextInputEditText otherSpacesNameValue, otherSpacesDescriptionValue;
    TextInputLayout otherSpacesNameField, otherSpacesDescriptionField;
    Button saveOtherSpaces, cancelOtherSpaces;
    RadioGroup isAccessible;
    TextView isAccessibleError;

    Bundle otherBundle = new Bundle();

    ViewModelEntry modelEntry;

    public OtherSpacesFragment() {
        // Required empty public constructor
    }

    public static OtherSpacesFragment newInstance() {
        return new OtherSpacesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            otherBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
            otherBundle.putInt(OtherSpacesListFragment.OTHER_SPACE_ID, this.getArguments().getInt(OtherSpacesListFragment.OTHER_SPACE_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_spaces, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateOtherSpaceViews(view);

        otherSpacesDescriptionValue.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        });

        if (otherBundle.getInt(OtherSpacesListFragment.OTHER_SPACE_ID) > 0) {
            modelEntry.getOneOtherSpace(otherBundle.getInt(OtherSpacesListFragment.OTHER_SPACE_ID)).
                    observe(getViewLifecycleOwner(), this::gatherOtherSpacesData);
        }

        cancelOtherSpaces.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .popBackStack(InspectionActivity.OTHERS_LIST, 0));

        saveOtherSpaces.setOnClickListener(v -> {
            if(checkEmptyFields()) {
                OtherSpaces newSpace = createOtherSpace();
                if (otherBundle.getInt(OtherSpacesListFragment.OTHER_SPACE_ID) > 0) {
                    newSpace.setOtherSpacesID(otherBundle.getInt(OtherSpacesListFragment.OTHER_SPACE_ID));
                    ViewModelEntry.updateOtherSpace(newSpace);
                    Toast.makeText(getContext(), "Cadastro atualizado com Sucesso", Toast.LENGTH_SHORT).show();
                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    manager.popBackStack(InspectionActivity.OTHERS_LIST, 0);
                } else {
                    ViewModelEntry.insertOtherSpace(newSpace);
                    clearOtherSpaceFields();
                    Toast.makeText(getContext(), "Cadastro efetuado com Sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void instantiateOtherSpaceViews(View view) {
//        TextInputEditText
        otherSpacesNameValue = view.findViewById(R.id.other_spaces_name_value);
        otherSpacesDescriptionValue = view.findViewById(R.id.other_spaces_description_value);
//        TextInputLayout
        otherSpacesNameField = view.findViewById(R.id.other_spaces_name_field);
        otherSpacesDescriptionField = view.findViewById(R.id.other_spaces_description_field);
//        Buttons
        saveOtherSpaces = view.findViewById(R.id.save_other_spaces_button);
        cancelOtherSpaces = view.findViewById(R.id.cancel_other_spaces_button);
//        RadioGroup
        isAccessible = view.findViewById(R.id.accordance_with_accessibility_radio);
//        TextView
        isAccessibleError = view.findViewById(R.id.other_spaces_radio_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void gatherOtherSpacesData(OtherSpaces other) {
        otherSpacesNameValue.setText(other.getOtherSpaceName());
        otherSpacesDescriptionValue.setText(other.getOtherSpaceDescription());
        isAccessible.check(isAccessible.getChildAt(other.getIsAccessible()).getId());
    }

    public boolean checkEmptyFields() {
        clearOtherSpacesErrors();
        int error = 0;
        if (TextUtils.isEmpty(otherSpacesNameValue.getText())) {
            otherSpacesNameField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(otherSpacesDescriptionValue.getText())) {
            otherSpacesDescriptionField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (isAccessible.getCheckedRadioButtonId() == -1) {
            isAccessibleError.setVisibility(View.VISIBLE);
            error++;
        }
        return error == 0;
    }

    public void clearOtherSpacesErrors() {
        otherSpacesNameField.setError(null);
        otherSpacesDescriptionField.setError(null);
        isAccessibleError.setVisibility(View.GONE);
    }

    public OtherSpaces createOtherSpace() {
        return new OtherSpaces(otherBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER),
                Objects.requireNonNull(otherSpacesNameValue.getText()).toString(),
                Objects.requireNonNull(otherSpacesDescriptionValue.getText()).toString(),
                checkedRadio(isAccessible));
    }

    public int checkedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearOtherSpaceFields() {
        otherSpacesNameValue.setText(null);
        otherSpacesDescriptionValue.setText(null);
        isAccessible.clearCheck();
    }
}