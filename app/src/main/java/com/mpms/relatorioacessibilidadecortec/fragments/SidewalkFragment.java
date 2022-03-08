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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SidewalkSlopeListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;

public class SidewalkFragment extends Fragment {

    public static final String SIDEWALK_ID = "SIDEWALK_ID";

    TextInputLayout sidewalkLocationField, sidewalkStatusField, sidewalkWidthField, sidewalkTactileFloorStatusField, sidewalkObsField;
    TextInputEditText sidewalkLocationValue, sidewalkStatusValue, sidewalkWidthValue, sidewalkTactileFloorStatusValue, sidewalkObsValue;
    RadioGroup sidewalkConsStatusRadio, hasTactileFloor, statusTactileFloor, hasSlope;
    TextView tactileFloorLabel, slopeRegisterLabel, tactileFloorError, tactileFloorStatusError, sidewalkHasSlopeError;
    Button saveSidewalk, cancelSidewalk, addSlope;

    String sidewalkLocation, sidewalkStatus, sidewalkTactileFloorStatus, sidewalkObs;
    Integer sidewalkGoodStatus, hasTactFloor, tactileFloorStatus, sidewalkHasSlope;
    Double sidewalkWidth;

    ArrayList<TextInputEditText> sidewalkObsArray = new ArrayList<>();

    Bundle sidewalkData = new Bundle();

    int updateRegister = 0;
    int recentRegister = 0;
    int savedRegister = 0;
    int rowCounter = 0;

    ViewModelEntry modelEntry;
    ViewModelDialog modelDialog;

    public SidewalkFragment() {
        // Required empty public constructor
    }

    public static SidewalkFragment newInstance() {
        return new SidewalkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            sidewalkData.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
            sidewalkData.putInt(SIDEWALK_ID, this.getArguments().getInt(SIDEWALK_ID));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sidewalk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSidewalkFragmentViews(view);
        initialLayout();
        allowSidewalkObsScroll();

        hasTactileFloor.setOnCheckedChangeListener(this::hasSpecialFloorListener);
        hasSlope.setOnCheckedChangeListener(this::hasSlopeListener);

        if (sidewalkData.getInt(SIDEWALK_ID) > 0) {
            modelEntry.getSidewalkEntry(sidewalkData.getInt(SIDEWALK_ID))
                    .observe(getViewLifecycleOwner(), this::gatherSidewalkData);
            savedRegister = 1;
        }

        modelEntry.getLastSidewalkEntry().observe(getViewLifecycleOwner(), sidewalk -> {
            if (recentRegister == 1) {
                recentRegister = 0;
                sidewalkData.putInt(SIDEWALK_ID, sidewalk.getSidewalkID());
                openSidewalkSlopeFragment();
            }
        });

        modelEntry.getAllSidewalkSlopes(sidewalkData.getInt(SIDEWALK_ID))
                .observe(getViewLifecycleOwner(), slopeList -> {
                    if (slopeList != null && slopeList.size() > 0) {
                        rowCounter = slopeList.size();
                    }
        });

        addSlope.setOnClickListener(v -> {
            if (sidewalkData.getInt(SIDEWALK_ID) == 0) {
                if (updateRegister == 0) {
                    recentRegister++;
                    updateRegister++;
                    SidewalkEntry newSidewalkEntry = newSidewalk(sidewalkData);
                    ViewModelEntry.insertSidewalkEntry(newSidewalkEntry);
                } else if (updateRegister > 0) {
                    updateRegister++;
                    openSidewalkSlopeFragment();
                } else {
                    recentRegister = 0;
                    updateRegister = 0;
                    sidewalkData.putInt(SIDEWALK_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.register_error_try_again), Toast.LENGTH_SHORT).show();
                }
            } else if (sidewalkData.getInt(SIDEWALK_ID) > 0) {
                if (updateRegister >= 0) {
                    updateRegister++;
                    openSidewalkSlopeFragment();
                } else {
                    recentRegister = 0;
                    updateRegister = 0;
                    sidewalkData.putInt(SIDEWALK_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.register_error_try_again), Toast.LENGTH_SHORT).show();
                }
            } else {
                updateRegister = 0;
                recentRegister = 0;
                sidewalkData.putInt(SIDEWALK_ID, 0);
                Toast.makeText(getContext(), getString(R.string.register_error_try_again), Toast.LENGTH_SHORT).show();
            }
        });

        cancelSidewalk.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());


        saveSidewalk.setOnClickListener(v -> {
            if (checkEmptySidewalkFields()) {
                if (sidewalkData.getInt(SIDEWALK_ID) == 0) {
                    if (getCheckedSidewalkRadioButton(hasSlope) == 1 && rowCounter == 0) {
                        Toast.makeText(getContext(), "Por favor, adicione rebaixamentos para esta calçada", Toast.LENGTH_LONG).show();
                    } else if (getCheckedSidewalkRadioButton(hasSlope) == 0 && rowCounter > 0) {
//                        TODO - Deletar os slopes na hora de salvar. Chamar dialog - toast temporário.
                        Toast.makeText(getContext(), "A calçada possui rebaixamentos. Marque a opção correta ou delete os rebaixamentos", Toast.LENGTH_LONG).show();
                    } else {
                        if (updateRegister == 0) {
                            SidewalkEntry newSidewalkEntry = newSidewalk(sidewalkData);
                            ViewModelEntry.insertSidewalkEntry(newSidewalkEntry);
                            Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                            resetInitialLayout();
                        } else if (updateRegister > 0) {
                            SidewalkEntry upSidewalkEntry = newSidewalk(sidewalkData);
                            upSidewalkEntry.setSidewalkID(sidewalkData.getInt(SIDEWALK_ID));
                            ViewModelEntry.updateSidewalk(upSidewalkEntry);
                            Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                            resetInitialLayout();
                        } else {
                            updateRegister = 0;
                            recentRegister = 0;
                            savedRegister = 0;
                            rowCounter = 0;
                            sidewalkData.putInt(SIDEWALK_ID, 0);
                            Toast.makeText(getContext(), "Ocorreu um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (sidewalkData.getInt(SIDEWALK_ID) > 0) {
                    if (getCheckedSidewalkRadioButton(hasSlope) == 1 && rowCounter == 0) {
                        Toast.makeText(getContext(), "Por favor, adicione rebaixamentos para esta calçada", Toast.LENGTH_LONG).show();
                    } else if (getCheckedSidewalkRadioButton(hasSlope) == 0 && rowCounter > 0) {
//                        TODO - Deletar os slopes na hora de salvar. Chamar dialog - toast temporário.
                        Toast.makeText(getContext(), "A calçada possui rebaixamentos. Marque a opção correta ou delete os rebaixamentos", Toast.LENGTH_LONG).show();
                    } else {
                        if (updateRegister >= 0) {
                            SidewalkEntry upSidewalkEntry = newSidewalk(sidewalkData);
                            upSidewalkEntry.setSidewalkID(sidewalkData.getInt(SIDEWALK_ID));
                            ViewModelEntry.updateSidewalk(upSidewalkEntry);
                            Toast.makeText(getContext(), "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                            if (savedRegister == 1)
                                requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.SIDEWALK_LIST, 0);
                                else
                                resetInitialLayout();
                        } else {
                            updateRegister = 0;
                            recentRegister = 0;
                            rowCounter = 0;
                            savedRegister = 0;
                            sidewalkData.putInt(SIDEWALK_ID, 0);
                            Toast.makeText(getContext(), "Ocorreu um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    updateRegister = 0;
                    recentRegister = 0;
                    rowCounter = 0;
                    savedRegister = 0;
                    sidewalkData.putInt(SIDEWALK_ID, 0);
                    Toast.makeText(getContext(), "Ocorreu um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addObsFieldsToArray() {
        sidewalkObsArray.add(sidewalkStatusValue);
        sidewalkObsArray.add(sidewalkTactileFloorStatusValue);
        sidewalkObsArray.add(sidewalkObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSidewalkObsScroll() {
        addObsFieldsToArray();
        for (TextInputEditText obsScroll :sidewalkObsArray) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }

    private void instantiateSidewalkFragmentViews(View view) {
        sidewalkLocationField = view.findViewById(R.id.sidewalk_location_field);
        sidewalkStatusField = view.findViewById(R.id.sidewalk_status_field);
        sidewalkWidthField = view.findViewById(R.id.sidewalk_width_field);
        sidewalkTactileFloorStatusField = view.findViewById(R.id.sidewalk_special_floor_obs_field);
        sidewalkObsField = view.findViewById(R.id.sidewalk_obs_field);

        sidewalkLocationValue = view.findViewById(R.id.sidewalk_location_value);
        sidewalkStatusValue = view.findViewById(R.id.sidewalk_status_value);
        sidewalkWidthValue = view.findViewById(R.id.sidewalk_width_value);
        sidewalkTactileFloorStatusValue = view.findViewById(R.id.sidewalk_special_floor_obs_value);
        sidewalkObsValue = view.findViewById(R.id.sidewalk_obs_value);

        sidewalkConsStatusRadio = view.findViewById(R.id.radio_sidewalk_conservation);
        hasTactileFloor = view.findViewById(R.id.radio_sidewalk_special_floor);
        statusTactileFloor = view.findViewById(R.id.radio_status_special_floor);
        hasSlope = view.findViewById(R.id.radio_sidewalk_slope);

        slopeRegisterLabel = view.findViewById(R.id.label_sidewalk_slope_register);
        tactileFloorLabel = view.findViewById(R.id.label_status_special_floor);
        tactileFloorError = view.findViewById(R.id.has_tactile_floor_error);
        tactileFloorStatusError = view.findViewById(R.id.tactile_floor_status_error);
        sidewalkHasSlopeError = view.findViewById(R.id.sidewalk_has_slope_error);

        saveSidewalk = view.findViewById(R.id.save_sidewalk);
        cancelSidewalk = view.findViewById(R.id.cancel_sidewalk);
        addSlope = view.findViewById(R.id.add_sidewalk_slope);

        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);
    }

    private void gatherSidewalkData(SidewalkEntry sidewalk) {
        sidewalkLocationValue.setText(sidewalk.getSidewalkLocation());
        sidewalkConsStatusRadio.check(sidewalkConsStatusRadio.getChildAt(sidewalk.getSidewalkConservationStatus()).getId());
        sidewalkStatusValue.setText(sidewalk.getSidewalkConservationObs());
        sidewalkWidthValue.setText(String.valueOf(sidewalk.getWidthSidewalk()));
        hasTactileFloor.check(hasTactileFloor.getChildAt(sidewalk.getSidewalkHasTactileFloor()).getId());
        if (sidewalk.getSidewalkHasTactileFloor() == 1) {
            statusTactileFloor.check(statusTactileFloor.getChildAt(sidewalk.getTactileFloorConservationStatus()).getId());
            sidewalkTactileFloorStatusValue.setText(sidewalk.getTactileFloorObs());
        }
        hasSlope.check(hasSlope.getChildAt(sidewalk.getSidewalkHasSlope()).getId());
        sidewalkObsValue.setText(sidewalk.getSidewalkObs());
    }

    private void initialLayout() {
        tactileFloorLabel.setVisibility(View.GONE);
        statusTactileFloor.setVisibility(View.GONE);
        sidewalkTactileFloorStatusField.setVisibility(View.GONE);
        slopeRegisterLabel.setVisibility(View.GONE);
        addSlope.setVisibility(View.GONE);
    }

    private void hasSpecialFloorListener(RadioGroup radioGroup, int checkedID) {
        int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedID));

        if (index == 1) {
            tactileFloorLabel.setVisibility(View.VISIBLE);
            statusTactileFloor.setVisibility(View.VISIBLE);
            sidewalkTactileFloorStatusField.setVisibility(View.VISIBLE);
        } else {
            statusTactileFloor.clearCheck();
            sidewalkTactileFloorStatusValue.setText(null);
            tactileFloorLabel.setVisibility(View.GONE);
            statusTactileFloor.setVisibility(View.GONE);
            sidewalkTactileFloorStatusField.setVisibility(View.GONE);
        }
    }

    private void hasSlopeListener(RadioGroup radioGroup, int checkedID) {
        int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedID));
        if (index == 1) {
            slopeRegisterLabel.setVisibility(View.VISIBLE);
            addSlope.setVisibility(View.VISIBLE);
        } else {
            slopeRegisterLabel.setVisibility(View.GONE);
            addSlope.setVisibility(View.GONE);
        }
    }

    private int getCheckedSidewalkRadioButton(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkEmptySidewalkFields() {
        clearSidewalkEmptyFieldErrors();
        int i = 0;
        if (TextUtils.isEmpty(sidewalkLocationValue.getText())) {
            i++;
            sidewalkLocationField.setError(getString(R.string.blank_field_error));
        }
        if (getCheckedSidewalkRadioButton(sidewalkConsStatusRadio) == 0) {
            if (TextUtils.isEmpty(sidewalkStatusValue.getText())) {
                i++;
                sidewalkStatusField.setError(getString(R.string.blank_field_error));
            }
        }
        if (TextUtils.isEmpty(sidewalkWidthValue.getText())) {
            i++;
            sidewalkWidthField.setError(getString(R.string.blank_field_error));
        }
        if (getCheckedSidewalkRadioButton(hasTactileFloor) == -1) {
            tactileFloorError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedSidewalkRadioButton(hasTactileFloor) == 1) {
            if (getCheckedSidewalkRadioButton(statusTactileFloor) == -1) {
                tactileFloorStatusError.setVisibility(View.VISIBLE);
                i++;
            } else if (getCheckedSidewalkRadioButton(statusTactileFloor) == 0) {
                if (TextUtils.isEmpty(sidewalkTactileFloorStatusValue.getText())) {
                    sidewalkTactileFloorStatusField.setError(getString(R.string.blank_field_error));
                    i++;
                }
            }
        }
        if (getCheckedSidewalkRadioButton(hasSlope) == -1) {
            sidewalkHasSlopeError.setVisibility(View.VISIBLE);
            i++;
        }
        return i == 0;
    }

    private void clearSidewalkEmptyFieldErrors() {
        sidewalkLocationField.setErrorEnabled(false);
        sidewalkStatusField.setErrorEnabled(false);
        sidewalkWidthField.setErrorEnabled(false);
        tactileFloorError.setVisibility(View.GONE);
        tactileFloorStatusError.setVisibility(View.GONE);
        sidewalkTactileFloorStatusField.setErrorEnabled(false);
        sidewalkHasSlopeError.setVisibility(View.GONE);
    }

    private void openSidewalkSlopeFragment() {
        SidewalkSlopeListFragment slopeFragment = SidewalkSlopeListFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        slopeFragment.setArguments(sidewalkData);
        fragmentTransaction.replace(R.id.show_fragment_selected, slopeFragment).addToBackStack(null).commit();

    }

    private SidewalkEntry newSidewalk(Bundle bundle) {
        sidewalkLocation = String.valueOf(sidewalkLocationValue.getText());
        sidewalkGoodStatus = getCheckedSidewalkRadioButton(sidewalkConsStatusRadio);
        sidewalkStatus = String.valueOf(sidewalkStatusValue.getText());
        if (!TextUtils.isEmpty(sidewalkWidthValue.getText()))
            sidewalkWidth = Double.valueOf(String.valueOf(sidewalkWidthValue.getText()));
        hasTactFloor = getCheckedSidewalkRadioButton(hasTactileFloor);
        if (hasTactFloor == 1) {
            tactileFloorStatus = getCheckedSidewalkRadioButton(statusTactileFloor);
            sidewalkTactileFloorStatus = String.valueOf(sidewalkTactileFloorStatusValue.getText());
        }
        sidewalkHasSlope = getCheckedSidewalkRadioButton(hasSlope);
        sidewalkObs = String.valueOf(sidewalkObsValue.getText());

        return new SidewalkEntry(bundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER),sidewalkLocation,sidewalkGoodStatus,sidewalkStatus,sidewalkWidth,
                hasTactFloor,tactileFloorStatus,sidewalkTactileFloorStatus,sidewalkHasSlope, sidewalkObs);
    }

    private void resetInitialLayout() {
        updateRegister = 0;
        recentRegister = 0;
        rowCounter = 0;
        sidewalkData.remove(SIDEWALK_ID);
        sidewalkLocationValue.setText(null);
        sidewalkStatusValue.setText(null);
        sidewalkWidthValue.setText(null);
        sidewalkTactileFloorStatusValue.setText(null);
        sidewalkObsValue.setText(null);
        hasTactileFloor.clearCheck();
        statusTactileFloor.clearCheck();
        hasSlope.clearCheck();
        tactileFloorLabel.setVisibility(View.GONE);
        statusTactileFloor.setVisibility(View.GONE);
        sidewalkTactileFloorStatusField.setVisibility(View.GONE);
        slopeRegisterLabel.setVisibility(View.GONE);
        addSlope.setVisibility(View.GONE);
    }
}