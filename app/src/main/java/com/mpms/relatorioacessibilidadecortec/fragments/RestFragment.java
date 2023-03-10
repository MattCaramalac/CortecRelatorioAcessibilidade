package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessEntranceUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestColFirstUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.RestAccessColParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestAccessibleFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestCollectiveFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.FreeSpaceListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.RestBoxListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class RestFragment extends Fragment implements TagInterface, ScrollEditText {

    MultiLineRadioGroup restTypeMultiRadio;
    TextView restEntranceTypeError;
    MaterialButton cancelRest, continueRest;

    Bundle restBundle;

    ViewModelEntry modelEntry;

    int hasDoor = -1;

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

    //    todo - quando trocar a opção do tipo de sanitário, "limpar" o resto do cadastro
//    todo 2 - o cadastro está carregando 2 vezes
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRestViews(view);

        if (restBundle.getInt(REST_ID) > 0) {
            modelEntry.getOneRestroomEntry(restBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadRestData);
        }

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(ADD_ITEM_REQUEST)) {
                if (bundle.getInt(REST_ID) > 0) {
                    restBundle.putBoolean(RECENT_ENTRY, true);
                    if (restTypeMultiRadio.getCheckedRadioButtonIndex() > 0) {
                        RestColFirstUpdate rEntUpdate = restColEntUpdate(bundle);
                        ViewModelEntry.updateRestroomData(rEntUpdate);
                    } else {
                        RestAccessEntranceUpdate accUpdate = restAccessEntUpdate(bundle);
                        ViewModelEntry.updateAccessRestData(accUpdate);
                    }
                    callNextChildFragment(bundle);
                } else if (bundle.getInt(REST_ID) == 0) {
                    restBundle.putBoolean(RECENT_ENTRY, true);
                    RestroomEntry rest = createRestEntry(bundle);
                    ViewModelEntry.insertRestroomEntry(rest);
                } else {
                    restBundle.putInt(REST_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            } else if (bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                if (bundle.getInt(REST_ID) > 0) {
                    restBundle.putBoolean(RECENT_ENTRY, true);
                    if (restTypeMultiRadio.getCheckedRadioButtonIndex() > 0) {
                        RestColFirstUpdate rEntUpdate = restColEntUpdate(bundle);
                        ViewModelEntry.updateRestroomData(rEntUpdate);
                    } else {
                        RestAccessEntranceUpdate accUpdate = restAccessEntUpdate(bundle);
                        ViewModelEntry.updateAccessRestData(accUpdate);
                    }
                    callNextRestFragment(bundle);
                } else if (bundle.getInt(REST_ID) == 0) {
                    restBundle.putBoolean(RECENT_ENTRY, true);
                    RestroomEntry rest = createRestEntry(bundle);
                    ViewModelEntry.insertRestroomEntry(rest);
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
                    if (restBundle.getBoolean(ADD_ITEM_REQUEST))
                        callNextChildFragment(restBundle);
                    else
                        callNextRestFragment(restBundle);
                }
            });
        }
        restBundle.putBoolean(ADD_ITEM_REQUEST, false);
        restBundle.putBoolean(CHILD_DATA_COMPLETE, false);
        restBundle.putBoolean(BOX_ENTRY, false);
    }

    private void instantiateRestViews(View view) {
//        TextView
        restEntranceTypeError = view.findViewById(R.id.exclusive_entrance_error);
//        RadioGroup
        restTypeMultiRadio = view.findViewById(R.id.rest_type_multiradio);
//        MaterialButton
        cancelRest = view.findViewById(R.id.cancel_rest_register);
        continueRest = view.findViewById(R.id.continue_rest_register);

//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        cancelRest.setOnClickListener(v -> cancelClick());
        restTypeMultiRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (v, r) -> restTypeListener(restTypeMultiRadio));
    }

    private void loadRestData(RestroomEntry entry) {
        restTypeMultiRadio.checkAt(entry.getIsCollective());
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

    private void restTypeListener(MultiLineRadioGroup multi) {
        int index = multi.getCheckedRadioButtonIndex();

        if (index == 0) {
            getChildFragmentManager().beginTransaction().replace(R.id.rest_type_frame, RestAccessibleFragment.newInstance(restBundle)).commit();
            continueRest.setText(getString(R.string.label_button_proceed_register));
        } else
            getChildFragmentManager().beginTransaction().replace(R.id.rest_type_frame, RestCollectiveFragment.newInstance(restBundle, index)).commit();
    }

    private boolean checkRestEmptyFields() {
        clearErrors();

        if (restTypeMultiRadio.getCheckedRadioButtonIndex() == -1) {
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
        if (restTypeMultiRadio.getCheckedRadioButtonIndex() == 0) {
            bundle.putBoolean(FROM_REST, true);
            fragment = DoorFragment.newInstance();
        } else if (restTypeMultiRadio.getCheckedRadioButtonIndex() == 1 && hasDoor == 1) {
            bundle.putBoolean(FROM_COLLECTIVE, true);
            fragment = DoorFragment.newInstance();
        } else if (restTypeMultiRadio.getCheckedRadioButtonIndex() == 1)
            fragment = RestSinkColFragment.newInstance();
        else if (restTypeMultiRadio.getCheckedRadioButtonIndex() == 2)
            fragment = RestUrinalFragment.newInstance();
        else {
            fragment = RestToiletFragment.newInstance();
        }
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();

    }

    private void callNextChildFragment(Bundle bundle) {
        Fragment fragment;
        if (bundle.getBoolean(BOX_ENTRY)) {
            fragment = new RestBoxListFragment();
            fragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction().
                    replace(R.id.show_fragment_selected, fragment).addToBackStack(BOX_LIST).commit();
        } else {
            bundle.putBoolean(FROM_REST, true);
            fragment = new FreeSpaceListFragment();
            fragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction().
                    replace(R.id.show_fragment_selected, fragment).addToBackStack(FREE_LIST).commit();
        }

    }

    private RestroomEntry createRestEntry(Bundle bundle) {
        int restType;
        Integer restGender = null, restAntiDrift = null, restDrain = null, restSwitch = null, accessRoute = null, intRest = null, hasWindow = null, winQnt = null, doorSill = null;
        Double hSwitch = null, winHeight1 = null, winHeight2 = null, winHeight3 = null, notAccLength = null, notAccWidth = null, notAccEntWidth = null;
        String rLocation = null, routeObs = null, intObs = null, driftObs = null, drainObs = null, switchObs = null, winType1 = null, winType2 = null,
                winType3 = null, winObs = null, nAccEntObs = null;

        restType = restTypeMultiRadio.getCheckedRadioButtonIndex();
        restBundle.putInt(REST_TYPE, restType);

        RestAccessColParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        if (parcel.getRestGender() != null)
            restGender = parcel.getRestGender();
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
        if (parcel.getAntiDriftFloor() != null)
            restAntiDrift = parcel.getAntiDriftFloor();
        if (parcel.getAntiDriftFloorObs() != null)
            driftObs = parcel.getAntiDriftFloorObs();
        if (parcel.getRestDrain() != null)
            restDrain = parcel.getRestDrain();
        if (parcel.getRestDrainObs() != null)
            drainObs = parcel.getRestDrainObs();
        if (parcel.getRestSwitch() != null)
            restSwitch = parcel.getRestSwitch();
        if (parcel.getSwitchHeight() != null)
            hSwitch = parcel.getSwitchHeight();
        if (parcel.getSwitchObs() != null)
            switchObs = parcel.getSwitchObs();

        if (parcel.getHasWindow() != null) {
            hasWindow = parcel.getHasWindow();
            if (hasWindow == 1) {
                if (parcel.getWinQnt() != null) {
                    winQnt = parcel.getWinQnt();
                    switch (winQnt) {
                        case 3:
                            if (parcel.getWinComType3() != null && parcel.getWinComType3().length() > 0)
                                winType3 = parcel.getWinComType3();
                            if (parcel.getWinComHeight3() != null)
                                winHeight3 = parcel.getWinComHeight3();
                        case 2:
                            if (parcel.getWinComType2() != null && parcel.getWinComType2().length() > 0)
                                winType2 = parcel.getWinComType2();
                            if (parcel.getWinComHeight2() != null)
                                winHeight2 = parcel.getWinComHeight2();
                        case 1:
                            if (parcel.getWinComType1() != null && parcel.getWinComType1().length() > 0)
                                winType1 = parcel.getWinComType1();
                            if (parcel.getWinComHeight1() != null)
                                winHeight1 = parcel.getWinComHeight1();
                            break;
                    }
                }
            }
        }
        if (parcel.getWinObs() != null)
            winObs = parcel.getWinObs();

        if (parcel.getCollectiveHasDoor() != null) {
            hasDoor = parcel.getCollectiveHasDoor();

        }
        if (parcel.getNotAccessLength() != null)
            notAccLength = parcel.getNotAccessLength();
        if (parcel.getNotAccessWidth() != null)
            notAccWidth = parcel.getNotAccessWidth();
        if (parcel.getNotAccEntranceWidth() != null)
            notAccEntWidth = parcel.getNotAccEntranceWidth();
        if (parcel.getNotAccEntranceSill() != null)
            doorSill = parcel.getNotAccEntranceSill();
        if (parcel.getNotAccEntranceObs() != null)
            nAccEntObs = parcel.getNotAccEntranceObs();


        return new RestroomEntry(restBundle.getInt(BLOCK_ID), restType, restGender, rLocation, hasDoor, notAccEntWidth, doorSill,
                nAccEntObs, accessRoute, routeObs, intRest, intObs, restAntiDrift, driftObs, restDrain, drainObs, restSwitch, hSwitch, switchObs,
                notAccLength, notAccWidth, null, null, null, null, null, null, null,
                null, null,null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null,
                hasWindow, winQnt, winType1, winHeight1, winType2, winHeight2, winType3, winHeight3, winObs,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null);
    }

    private RestColFirstUpdate restColEntUpdate(Bundle bundle) {
        int restType;
        Integer hasDoor = null, restGender = null, restAntiDrift = null, restDrain = null, restSwitch = null, accessRoute = null, intRest = null, hasWindow = null,
                winQnt = null, doorSill = null;
        Double hSwitch = null, winHeight1 = null, winHeight2 = null, winHeight3 = null, notAccLength = null, notAccWidth = null, notAccEntWidth = null;
        String rLocation = null, routeObs = null, intObs = null, driftObs = null, drainObs = null, switchObs = null, winType1 = null, winType2 = null,
                winType3 = null, winObs = null, nAccEntObs = null;

        restType = restTypeMultiRadio.getCheckedRadioButtonIndex();
        restBundle.putInt(REST_TYPE, restType);

        RestAccessColParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        if (parcel.getRestGender() != null)
            restGender = parcel.getRestGender();
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
        if (parcel.getAntiDriftFloor() != null)
            restAntiDrift = parcel.getAntiDriftFloor();
        if (parcel.getAntiDriftFloorObs() != null)
            driftObs = parcel.getAntiDriftFloorObs();
        if (parcel.getRestDrain() != null)
            restDrain = parcel.getRestDrain();
        if (parcel.getRestDrainObs() != null)
            drainObs = parcel.getRestDrainObs();
        if (parcel.getRestSwitch() != null)
            restSwitch = parcel.getRestSwitch();
        if (parcel.getSwitchHeight() != null)
            hSwitch = parcel.getSwitchHeight();
        if (parcel.getSwitchObs() != null)
            switchObs = parcel.getSwitchObs();

        if (parcel.getHasWindow() != null) {
            hasWindow = parcel.getHasWindow();
            if (hasWindow == 1) {
                if (parcel.getWinQnt() != null) {
                    winQnt = parcel.getWinQnt();
                    switch (winQnt) {
                        case 3:
                            if (parcel.getWinComType3() != null && parcel.getWinComType3().length() > 0)
                                winType3 = parcel.getWinComType3();
                            if (parcel.getWinComHeight3() != null)
                                winHeight3 = parcel.getWinComHeight3();
                        case 2:
                            if (parcel.getWinComType2() != null && parcel.getWinComType2().length() > 0)
                                winType2 = parcel.getWinComType3();
                            if (parcel.getWinComHeight2() != null)
                                winHeight2 = parcel.getWinComHeight3();
                        case 1:
                            if (parcel.getWinComType1() != null && parcel.getWinComType1().length() > 0)
                                winType1 = parcel.getWinComType1();
                            if (parcel.getWinComHeight1() != null)
                                winHeight1 = parcel.getWinComHeight1();
                            break;
                    }
                }
            }
        }
        if (parcel.getWinObs() != null)
            winObs = parcel.getWinObs();

        if (parcel.getCollectiveHasDoor() != null)
            hasDoor = parcel.getCollectiveHasDoor();
        if (parcel.getNotAccessLength() != null)
            notAccLength = parcel.getNotAccessLength();
        if (parcel.getNotAccessWidth() != null)
            notAccWidth = parcel.getNotAccessWidth();
        if (parcel.getNotAccEntranceWidth() != null)
            notAccEntWidth = parcel.getNotAccEntranceWidth();
        if (parcel.getNotAccEntranceSill() != null)
            doorSill = parcel.getNotAccEntranceSill();
        if (parcel.getNotAccEntranceObs() != null)
            nAccEntObs = parcel.getNotAccEntranceObs();


        return new RestColFirstUpdate(restBundle.getInt(REST_ID), restType, restGender, rLocation, hasDoor, notAccEntWidth, doorSill, nAccEntObs, accessRoute, routeObs, intRest,
                intObs, restAntiDrift, driftObs, restDrain, drainObs, restSwitch, hSwitch, switchObs, notAccLength, notAccWidth, hasWindow, winQnt, winType1, winHeight1,
                winType2, winHeight2, winType3, winHeight3, winObs);
    }

    private RestAccessEntranceUpdate restAccessEntUpdate(Bundle bundle) {
        int restType;
        Integer restGender = null, restAntiDrift = null, restDrain = null, restSwitch = null, accessRoute = null, intRest = null, hasWindow = null;
        Double hSwitch = null;
        String rLocation = null, routeObs = null, intObs = null, driftObs = null, drainObs = null, switchObs = null;

        restType = restTypeMultiRadio.getCheckedRadioButtonIndex();
        restBundle.putInt(REST_TYPE, restType);

        RestAccessColParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        if (parcel.getRestGender() != null)
            restGender = parcel.getRestGender();
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
        if (parcel.getAntiDriftFloor() != null)
            restAntiDrift = parcel.getAntiDriftFloor();
        if (parcel.getAntiDriftFloorObs() != null)
            driftObs = parcel.getAntiDriftFloorObs();
        if (parcel.getRestDrain() != null)
            restDrain = parcel.getRestDrain();
        if (parcel.getRestDrainObs() != null)
            drainObs = parcel.getRestDrainObs();
        if (parcel.getRestSwitch() != null)
            restSwitch = parcel.getRestSwitch();
        if (parcel.getSwitchHeight() != null)
            hSwitch = parcel.getSwitchHeight();
        if (parcel.getSwitchObs() != null)
            switchObs = parcel.getSwitchObs();


        return new RestAccessEntranceUpdate(restBundle.getInt(REST_ID), restType, restGender, rLocation, accessRoute, routeObs, intRest, intObs,
                restAntiDrift, driftObs, restDrain, drainObs, restSwitch, hSwitch, switchObs);
    }
}