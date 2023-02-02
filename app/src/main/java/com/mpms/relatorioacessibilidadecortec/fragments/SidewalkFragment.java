package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntryOne;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SideMeasureParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SideMeasureFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class SidewalkFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout sideLocationField;
    TextInputEditText sideLocationValue;
    RadioGroup streetPavementRadio, hasSidewalkRadio;
    TextView streetPavementError, hasSidewalkError;
    MaterialButton saveProceedSidewalk, cancelSidewalk;

    int slopeMeasureQnt = 1;

    Bundle sideBundle;

    int savedRegister = 0;

    ViewModelEntry modelEntry;

    public SidewalkFragment() {
        // Required empty public constructor
    }

    public static SidewalkFragment newInstance() {
        return new SidewalkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            sideBundle = new Bundle(this.getArguments());
        else
            sideBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (sideBundle.getBoolean(RECENT_ENTRY))
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sidewalk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSidewalkFragmentViews(view);


        if (sideBundle.getInt(AMBIENT_ID) > 0) {
            modelEntry.getSidewalkEntry(sideBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadSidewalkData);
            savedRegister = 1;
        }

        saveProceedSidewalk.setOnClickListener(v -> {
            if (getCheckedSidewalkRadioButton(hasSidewalkRadio) == 1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, sideBundle);
            else if (checkEmptySidewalkFields()) {
                if (sideBundle.getInt(AMBIENT_ID) > 0) {
                    ViewModelEntry.updateSidewalkOne(updateSidewalkOne(sideBundle));
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                }
                else {
                    ViewModelEntry.insertSidewalkEntry(newSidewalk(sideBundle));
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearSidewalkFields();
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE) && checkEmptySidewalkFields()) {
                if (sideBundle.getInt(AMBIENT_ID) > 0) {
                    ViewModelEntry.updateSidewalkOne(updateSidewalkOne(sideBundle));
                    callNextSidewalkFrag(sideBundle);
                } else if (sideBundle.getInt(AMBIENT_ID) == 0) {
                    sideBundle.putBoolean(RECENT_ENTRY, true);
                    ViewModelEntry.insertSidewalkEntry(newSidewalk(sideBundle));
                } else {
                    sideBundle.putInt(AMBIENT_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (!sideBundle.getBoolean(RECENT_ENTRY)) {
//            modelEntry.getLastSidewalkEntry().observe(getViewLifecycleOwner(), side -> {
//                if (sideBundle.getBoolean(RECENT_ENTRY))
//                    callNextSidewalkFrag(side);
//            });
//        }
    }

    private void cancelClick() {
        if (sideBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteSidewalk(sideBundle.getInt(AMBIENT_ID));
                sideBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(SIDEWALK_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(SIDEWALK_LIST, 0);
    }

    private void callNextSidewalkFrag(SidewalkEntry side) {
        int sID = side.getSidewalkID();
        sideBundle.putInt(AMBIENT_ID, sID);
        SidewalkFragmentTwo sideTwo = SidewalkFragmentTwo.newInstance();
        sideTwo.setArguments(sideBundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, sideTwo).addToBackStack(null).commit();
    }

    private void callNextSidewalkFrag(Bundle bundle) {
        SidewalkFragmentTwo sideTwo = SidewalkFragmentTwo.newInstance();
        sideTwo.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, sideTwo).addToBackStack(null).commit();
    }

    private void instantiateSidewalkFragmentViews(View view) {
//        TextInputLayout
        sideLocationField = view.findViewById(R.id.sidewalk_location_field);
//        TextInputEditText
        sideLocationValue = view.findViewById(R.id.sidewalk_location_value);
//        RadioGroup
        streetPavementRadio = view.findViewById(R.id.street_pavement_radio);
        hasSidewalkRadio = view.findViewById(R.id.has_sidewalk_radio);
//        TextView
        streetPavementError = view.findViewById(R.id.street_pavement_error);
        hasSidewalkError = view.findViewById(R.id.has_sidewalk_error);
//        MaterialButton
        saveProceedSidewalk = view.findViewById(R.id.save_proceed_sidewalk);
        cancelSidewalk = view.findViewById(R.id.cancel_sidewalk);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        hasSidewalkRadio.setOnCheckedChangeListener(this::sidewalkRadioListener);
        cancelSidewalk.setOnClickListener(v -> cancelClick());
    }


    private void loadSidewalkData(SidewalkEntry sidewalk) {
        if (sidewalk.getSidewalkLocation() != null)
            sideLocationValue.setText(sidewalk.getSidewalkLocation());
        if (sidewalk.getStreetPavement() != null && sidewalk.getStreetPavement() > -1)
            streetPavementRadio.check(streetPavementRadio.getChildAt(sidewalk.getStreetPavement()).getId());
        if (sidewalk.getHasSidewalk() != null && sidewalk.getHasSidewalk() > -1) {
            hasSidewalkRadio.check(hasSidewalkRadio.getChildAt(sidewalk.getHasSidewalk()).getId());
            if (sidewalk.getHasSidewalk() == 1)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, sideBundle);
        }

    }

    private void sidewalkRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasSidewalkRadio) {
            if (index == 1)
                getChildFragmentManager().beginTransaction().replace(R.id.has_sidewalk_fragment, new SideMeasureFragment()).commit();
            else
                removeSideMeasureFrag();
        }
    }

    private void removeSideMeasureFrag() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.has_sidewalk_fragment);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private int getCheckedSidewalkRadioButton(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkEmptySidewalkFields() {
        clearSidewalkEmptyFieldErrors();
        int i = 0;

        if (TextUtils.isEmpty(sideLocationValue.getText())) {
            i++;
            sideLocationField.setError(getString(R.string.req_field_error));
        }

        if (getCheckedSidewalkRadioButton(streetPavementRadio) == -1) {
            i++;
            streetPavementError.setVisibility(View.VISIBLE);
        }

        if (getCheckedSidewalkRadioButton(hasSidewalkRadio) == -1) {
            i++;
            hasSidewalkError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void clearSidewalkEmptyFieldErrors() {
        sideLocationField.setErrorEnabled(false);
        streetPavementError.setVisibility(View.GONE);
        hasSidewalkError.setVisibility(View.GONE);
    }

    private SidewalkEntry newSidewalk(Bundle bundle) {
        String sideLocale = null, sideMeasureObs = null, tactFloorObs = null;
        Integer streetPavement = null, hasSide = null, hasTactFloor = null, tactFloorColor = null;
        Double sideWidth = null, sideFSpaceWidth = null, sideSlope1 = null, sideSlope2 = null, sideSlope3 = null, sideSlope4 = null, sideSlope5 = null,
                sideSlope6 = null, tacTileDirWidth = null, tacTileAlertWidth = null;

        SideMeasureParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        if (!TextUtils.isEmpty(sideLocationValue.getText()))
            sideLocale = String.valueOf(sideLocationValue.getText());
        if (getCheckedSidewalkRadioButton(streetPavementRadio) != -1)
            streetPavement = getCheckedSidewalkRadioButton(streetPavementRadio);
        if (getCheckedSidewalkRadioButton(hasSidewalkRadio) != -1)
           hasSide = getCheckedSidewalkRadioButton(streetPavementRadio);

        if (parcel.getSidewalkWidth() != null)
            sideWidth = parcel.getSidewalkWidth();
        if (parcel.getSideFreeSpaceWidth() != null)
            sideFSpaceWidth = parcel.getSideFreeSpaceWidth();
        if (parcel.getSideMeasureObs() != null)
            sideMeasureObs = parcel.getSideMeasureObs();

        slopeMeasureQnt = parcel.getSlopeMeasureQnt();
        switch (slopeMeasureQnt) {
            case 6:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope6 = parcel.getSideTransSlope6();
            case 5:
                if (parcel.getSideTransSlope5() != null)
                    sideSlope5 = parcel.getSideTransSlope5();
            case 4:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope4 = parcel.getSideTransSlope4();
            case 3:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope3 = parcel.getSideTransSlope3();
            case 2:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope2 = parcel.getSideTransSlope2();
            case 1:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope1 = parcel.getSideTransSlope1();
                break;
            default:
                break;
        }

        if (parcel.getHasSpecialFloor() != null) {
            hasTactFloor = parcel.getHasSpecialFloor();
            if (hasTactFloor == 1) {
                if (parcel.getSpecialFloorRightColor() != null)
                    tactFloorColor = parcel.getSpecialFloorRightColor();
                if (parcel.getSpecialTileDirectionWidth() != null)
                    tacTileDirWidth = parcel.getSpecialTileDirectionWidth();
                if (parcel.getSpecialTileAlertWidth() != null)
                    tacTileAlertWidth = parcel.getSpecialTileAlertWidth();
            }
        }

        if (parcel.getSpecialFloorObs() != null)
            tactFloorObs = parcel.getSpecialFloorObs();

        return new SidewalkEntry(bundle.getInt(BLOCK_ID), sideLocale, streetPavement, sideWidth, sideFSpaceWidth, sideMeasureObs, slopeMeasureQnt,
                sideSlope1, sideSlope2, sideSlope3, sideSlope4, sideSlope5, sideSlope6, hasTactFloor, tactFloorColor, tacTileDirWidth,
                tacTileAlertWidth, tactFloorObs, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, hasSide);
    }

    private SidewalkEntryOne updateSidewalkOne(Bundle bundle) {
        String sideLocale = null, sideMeasureObs = null, tactFloorObs = null;
        Integer streetPavement = null, hasSide = null, hasTactFloor = null, tactFloorColor = null;
        Double sideWidth = null, sideFSpaceWidth = null, sideSlope1 = null, sideSlope2 = null, sideSlope3 = null, sideSlope4 = null, sideSlope5 = null,
                sideSlope6 = null, tacTileDirWidth = null, tacTileAlertWidth = null;

        SideMeasureParcel parcel = Parcels.unwrap(bundle.getParcelable("PARCEL"));

        if (!TextUtils.isEmpty(sideLocationValue.getText()))
            sideLocale = String.valueOf(sideLocationValue.getText());
        if (getCheckedSidewalkRadioButton(streetPavementRadio) != -1)
            streetPavement = getCheckedSidewalkRadioButton(streetPavementRadio);
        if (getCheckedSidewalkRadioButton(hasSidewalkRadio) != -1)
            hasSide = getCheckedSidewalkRadioButton(streetPavementRadio);

        if (parcel.getSidewalkWidth() != null)
            sideWidth = parcel.getSidewalkWidth();
        if (parcel.getSideFreeSpaceWidth() != null)
            sideFSpaceWidth = parcel.getSideFreeSpaceWidth();
        if (parcel.getSideMeasureObs() != null)
            sideMeasureObs = parcel.getSideMeasureObs();

        slopeMeasureQnt = parcel.getSlopeMeasureQnt();
        switch (slopeMeasureQnt) {
            case 6:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope6 = parcel.getSideTransSlope6();
            case 5:
                if (parcel.getSideTransSlope5() != null)
                    sideSlope5 = parcel.getSideTransSlope5();
            case 4:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope4 = parcel.getSideTransSlope4();
            case 3:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope3 = parcel.getSideTransSlope3();
            case 2:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope2 = parcel.getSideTransSlope2();
            case 1:
                if (parcel.getSideTransSlope6() != null)
                    sideSlope1 = parcel.getSideTransSlope1();
                break;
            default:
                break;
        }

        if (parcel.getHasSpecialFloor() != null) {
            hasTactFloor = parcel.getHasSpecialFloor();
            if (hasTactFloor == 1) {
                if (parcel.getSpecialFloorRightColor() != null)
                    tactFloorColor = parcel.getSpecialFloorRightColor();
                if (parcel.getSpecialTileDirectionWidth() != null)
                    tacTileDirWidth = parcel.getSpecialTileDirectionWidth();
                if (parcel.getSpecialTileAlertWidth() != null)
                    tacTileAlertWidth = parcel.getSpecialTileAlertWidth();
            }
        }

        if (parcel.getSpecialFloorObs() != null)
            tactFloorObs = parcel.getSpecialFloorObs();

        return new SidewalkEntryOne(bundle.getInt(AMBIENT_ID), sideLocale, streetPavement, sideWidth, sideFSpaceWidth, sideMeasureObs, slopeMeasureQnt,
                sideSlope1, sideSlope2, sideSlope3, sideSlope4, sideSlope5, sideSlope6, hasTactFloor, tactFloorColor, tacTileDirWidth,
                tacTileAlertWidth, tactFloorObs, hasSide);

    }

    private void clearSidewalkFields() {
        sideLocationValue.setText(null);
        streetPavementRadio.clearCheck();
        removeSideMeasureFrag();
        hasSidewalkRadio.clearCheck();

    }
}