package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
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
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestEntranceUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.RestAccessColParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestAccessibleFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestCollectiveFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class RestFragment extends Fragment implements TagInterface, ScrollEditText {

    RadioGroup isCollectiveRadio;
    TextView restEntranceTypeError;
    MaterialButton cancelRest, continueRest;

    Bundle restBundle;

    ViewModelEntry modelEntry;

    public RestFragment() {
        // Required empty public constructor
    }

    public static RestFragment newInstance() {
        return new RestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            restBundle = new Bundle(this.getArguments());
        else
            restBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (restBundle.getBoolean(RECENT_ENTRY))
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
        return inflater.inflate(R.layout.fragment_restroom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRestViews(view);

        if (restBundle.getInt(REST_ID) > 0) {
            restBundle.putBoolean(RECENT_ENTRY, true);
            modelEntry.getOneRestroomEntry(restBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadRestData);
        }

//        TODO - continuar com a lógica de recebimento de dados para criar/dar update quando necessário
        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(ADD_ITEM_REQUEST)) {

            } else if (bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                if (bundle.getInt(REST_ID) > 0) {
                    RestEntranceUpdate rEntUpdate = restEntUpdate(bundle);
                    ViewModelEntry.updateRestroomData(rEntUpdate);
                    callNextRestFragment(bundle);
                } else if (bundle.getInt(REST_ID) == 0) {
                    RestroomEntry rest = createRestEntry(bundle);
                    ViewModelEntry.insertRestroomEntry(rest);
                    restBundle.putBoolean(RECENT_ENTRY, true);
                } else {
                    restBundle.putInt(REST_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        continueRest.setOnClickListener(v -> {
            if (checkRestEmptyFields())
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, restBundle);
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!restBundle.getBoolean(RECENT_ENTRY)) {
            modelEntry.getLastRestroomEntry().observe(getViewLifecycleOwner(), rest -> {
                if (restBundle.getBoolean(RECENT_ENTRY)) {
                    restBundle.putInt(REST_ID, rest.getRestroomID());
                    callNextRestFragment(restBundle);
                }
            });
        }
    }

    private void instantiateRestViews(View view) {
//        TextView
        restEntranceTypeError = view.findViewById(R.id.exclusive_entrance_error);
//        RadioGroup
        isCollectiveRadio = view.findViewById(R.id.exclusive_entrance_radio);
//        MaterialButton
        cancelRest = view.findViewById(R.id.cancel_rest_register);
        continueRest = view.findViewById(R.id.continue_rest_register);

//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        cancelRest.setOnClickListener(v -> cancelClick());
        isCollectiveRadio.setOnCheckedChangeListener(this::restTypeListener);
    }

    private void loadRestData(RestroomEntry entry) {
        isCollectiveRadio.check(isCollectiveRadio.getChildAt(entry.getIsCollective()).getId());
    }

    private void cancelClick() {
        if (restBundle.getBoolean(RECENT_ENTRY) && restBundle.getInt(REST_ID) > 0) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteOneRestroomEntry(restBundle.getInt(REST_ID));
                restBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(REST_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(REST_LIST, 0);
    }

    private int getRadioCheckIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void restTypeListener(RadioGroup radio, int checkID) {
        int index = getRadioCheckIndex(radio);

        if (index == 0) {
            getChildFragmentManager().beginTransaction().replace(R.id.rest_type_frame, RestAccessibleFragment.newInstance(restBundle)).commit();
            continueRest.setText(getString(R.string.label_button_proceed_register));
        }
        else
            getChildFragmentManager().beginTransaction().replace(R.id.rest_type_frame, new RestCollectiveFragment()).commit();
    }

    private boolean checkRestEmptyFields() {
        clearErrors();

        if (getRadioCheckIndex(isCollectiveRadio) == -1) {
            restEntranceTypeError.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    private void clearErrors() {
        restEntranceTypeError.setVisibility(View.GONE);
    }

    private void callNextRestFragment(Bundle bundle) {
        Fragment fragment;
        if (getRadioCheckIndex(isCollectiveRadio) == 0) {
            bundle.putBoolean(FROM_REST, true);
            fragment = DoorFragment.newInstance();
        } else
            fragment = RestUrinalFragment.newInstance();
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();

    }

    private RestroomEntry createRestEntry(Bundle bundle) {
        int isCollective, restType, restAntiDrift, restDrain, restSwitch;
        Integer accessRoute = null, intRest = null, hasEntranceDoor = null, doorSill = null;
        Double hSwitch = null, doorWidth = null;
        String rLocation = null, routeObs = null, intObs = null, driftObs = null, drainObs = null, switchObs = null, doorSillObs = null;

        isCollective = getRadioCheckIndex(isCollectiveRadio);

        RestAccessColParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        restType = parcel.getRestType();
        if (parcel.getRestLocation() != null)
            rLocation = parcel.getRestLocation();
        if (parcel.getAccessRoute() != null)
            accessRoute = parcel.getAccessRoute();
        if (parcel.getAccessRouteObs() != null)
            routeObs = parcel.getAccessRouteObs();
        if (parcel.getIntRestroom() != null)
            intRest = parcel.getIntRestroom();
        if (parcel.getIntRestObs() != null)
            intObs = parcel.getIntRestObs();
        restAntiDrift = parcel.getAntiDriftFloor();
        if (parcel.getAntiDriftFloorObs() != null)
            driftObs = parcel.getAntiDriftFloorObs();
        restDrain = parcel.getRestDrain();
        if (parcel.getRestDrainObs() != null)
            drainObs = parcel.getRestDrainObs();
        restSwitch = parcel.getRestSwitch();
        if (parcel.getSwitchHeight() != null)
            hSwitch = parcel.getSwitchHeight();
        if (parcel.getSwitchObs() != null)
            switchObs = parcel.getSwitchObs();

        if (parcel.getHasEntranceDoor() != null)
            hasEntranceDoor = parcel.getHasEntranceDoor();
        if (parcel.getDoorWidth() != null)
            doorWidth = parcel.getDoorWidth();
        if (parcel.getDoorSillType() != null)
            doorSill = parcel.getDoorSillType();
        if (parcel.getDoorSillObs() != null)
            doorSillObs = parcel.getDoorSillObs();


        return new RestroomEntry(restBundle.getInt(BLOCK_ID), isCollective, restType, rLocation, hasEntranceDoor, doorWidth, doorSill, doorSillObs, accessRoute, routeObs, intRest, intObs,
                restAntiDrift, driftObs, restDrain, drainObs, restSwitch, hSwitch, switchObs, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null);
    }

    private RestEntranceUpdate restEntUpdate(Bundle bundle) {
        int isCollective, restType, restAntiDrift, restDrain, restSwitch;
        Integer accessRoute = null, intRest = null, hasEntranceDoor = null, doorSill = null;
        Double hSwitch = null, doorWidth = null;
        String rLocation = null, routeObs = null, intObs = null, driftObs = null, drainObs = null, switchObs = null, doorSillObs = null;

        isCollective = getRadioCheckIndex(isCollectiveRadio);

        RestAccessColParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        restType = parcel.getRestType();
        if (parcel.getRestLocation() != null)
            rLocation = parcel.getRestLocation();
        if (parcel.getAccessRoute() != null)
            accessRoute = parcel.getAccessRoute();
        if (parcel.getAccessRouteObs() != null)
            routeObs = parcel.getAccessRouteObs();
        if (parcel.getIntRestroom() != null)
            intRest = parcel.getIntRestroom();
        if (parcel.getIntRestObs() != null)
            intObs = parcel.getIntRestObs();
        restAntiDrift = parcel.getAntiDriftFloor();
        if (parcel.getAntiDriftFloorObs() != null)
            driftObs = parcel.getAntiDriftFloorObs();
        restDrain = parcel.getRestDrain();
        if (parcel.getRestDrainObs() != null)
            drainObs = parcel.getRestDrainObs();
        restSwitch = parcel.getRestSwitch();
        if (parcel.getSwitchHeight() != null)
            hSwitch = parcel.getSwitchHeight();
        if (parcel.getSwitchObs() != null)
            switchObs = parcel.getSwitchObs();

        if (parcel.getHasEntranceDoor() != null)
            hasEntranceDoor = parcel.getHasEntranceDoor();
        if (parcel.getDoorWidth() != null)
            doorWidth = parcel.getDoorWidth();
        if (parcel.getDoorSillType() != null)
            doorSill = parcel.getDoorSillType();
        if (parcel.getDoorSillObs() != null)
            doorSillObs = parcel.getDoorSillObs();


        return new RestEntranceUpdate(restBundle.getInt(REST_ID), isCollective, restType, rLocation, hasEntranceDoor, doorWidth, doorSill, doorSillObs, accessRoute, routeObs, intRest, intObs,
                restAntiDrift, driftObs, restDrain, drainObs, restSwitch, hSwitch, switchObs);
    }
}