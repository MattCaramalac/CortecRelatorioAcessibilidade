package com.mpms.relatorioacessibilidadecortec.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.CirculationListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.ExternalAccessListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.ParkingLotListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.PlaygroundListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.PoolListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.RestListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.RoomRegisterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.SidewalkListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.WaterFountainListFragment;
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener, TagInterface {

    ExecutorService service;
    Handler handler;

    InspectionViewModel dataView;

    public static int endRegister = 0;
    Bundle inspectionBundle = new Bundle();
    ViewModelEntry modelEntry;
    Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        endRegister = 0;
        setContentView(R.layout.activity_inspection);
        modelEntry = new ViewModelEntry(getApplication());
        dataView = new ViewModelProvider(this).get(InspectionViewModel.class);
        inspectionBundle = getIntent().getBundleExtra(AREAS_REG_BUNDLE);
        service = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        if (inspectionBundle.getBoolean(CIRCULATION)) {
            dataView.setVisible(false);
            dataView.setHeaderTitle(getString(R.string.header_circulation_register));
            CirculationListFragment cList = CirculationListFragment.newInstance();
            cList.setArguments(inspectionBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, cList).addToBackStack(CIRC_LIST).commit();
        } else if (inspectionBundle.getInt(BLOCK_ID) == 0) {
            dataView.setVisible(true);
            int areaType = 0;
            if (inspectionBundle.getBoolean(EXT_AREA_REG))
                areaType = 1;
            else if (inspectionBundle.getBoolean(SUP_AREA_REG))
                areaType = 2;
            modelEntry.getAreaFromSchool(inspectionBundle.getInt(SCHOOL_ID), areaType)
                    .observe(this, area -> inspectionBundle.putInt(BLOCK_ID, area.getBlockSpaceID()));
        } else {
            dataView.setVisible(true);
        }

        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (registerFragmentOnScreen())
                    finish();
                else {
                    setEnabled(false);
                    InspectionActivity.super.onBackPressed();
                }
                setEnabled(true);
            }
        });

        getSupportFragmentManager().setFragmentResultListener(CLOSE_ACTIVITY, this, (key, bundle) -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        InspectionMemorial newFrag = InspectionMemorial.newInstance();
        newFrag.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.chooseItemMemorial, newFrag).commit();
    }

    @Override
    protected void onDestroy() {
        dataView.setHeaderTitle(null);
        dataView.setVisible(false);
        super.onDestroy();
    }

    @Override
    public void onDropdownChoice(int choice) {
        inspectionBundle.putBoolean(FROM_ROOMS, false);
        if (inspectionBundle.getBoolean(EXT_AREA_REG)) {
            switch (choice) {
                case 0:
                    displayExtAccessListFragment();
                    break;
                case 1:
                    displaySidewalkListFragment();
                    break;
                case 2:
                    displayParkingLotListFragment();
                    break;
                case 3:
                    displayRoomsRegisterListFragment(choice);
                    inspectionBundle.putBoolean(FROM_ROOMS, true);
                    break;
            }
        } else if (inspectionBundle.getBoolean(SUP_AREA_REG)) {
            switch (choice) {
                case 0:
                    displayFountainListFragment();
                    break;
                case 1:
                    displayParkingLotListFragment();
                    break;
                case 2:
                    displayRoomsRegisterListFragment(choice);
                    inspectionBundle.putBoolean(FROM_ROOMS, true);
                    break;
                case 3:
                    displayPlaygroundListFragment();
                    break;
                case 4:
                    displayRestroomListFragment();
                    break;
                case 5:
                    displayPoolListFragment();
                    break;
            }
        } else {
            switch (choice) {
                case 0:
                case 10:
                    displayRestroomListFragment();
                    break;
                case 1:
                    displayFountainListFragment();
                    break;
                default:
                    displayRoomsRegisterListFragment(choice);
                    inspectionBundle.putBoolean(FROM_ROOMS, true);
                    break;
            }
        }
        inspectionBundle.putInt(CHOICE, choice);
        getSupportFragmentManager().setFragmentResult(HEADER_TEXT, inspectionBundle);
    }

    public void displayExtAccessListFragment() {

        ExternalAccessListFragment extAccessList = ExternalAccessListFragment.newInstance();
        extAccessList.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, extAccessList).addToBackStack(EXTERNAL_LIST).commit();
    }

    public void displayRestroomListFragment() {
        RestListFragment restListFragment = RestListFragment.newInstance();
        restListFragment.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, restListFragment).addToBackStack(REST_LIST).commit();
    }

    public void displayFountainListFragment() {
        WaterFountainListFragment waterListFrag = WaterFountainListFragment.newInstance();
        waterListFrag.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, waterListFrag).addToBackStack(WATER_LIST).commit();

    }

    public void displaySidewalkListFragment() {
        SidewalkListFragment sidewalkList = SidewalkListFragment.newInstance();
        sidewalkList.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, sidewalkList).addToBackStack(SIDEWALK_LIST).commit();
    }

    public void displayRoomsRegisterListFragment(int chosenItem) {
        RoomRegisterListFragment roomList = RoomRegisterListFragment.newInstance(chosenItem);
        roomList.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, roomList).addToBackStack(ROOM_LIST).commit();
    }

    public void displayParkingLotListFragment() {
        ParkingLotListFragment parkingList = ParkingLotListFragment.newInstance();
        parkingList.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, parkingList).addToBackStack(PARKING_LIST).commit();
    }

    public void displayPlaygroundListFragment() {
        PlaygroundListFragment playListFragment = PlaygroundListFragment.newInstance();
        playListFragment.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, playListFragment).addToBackStack(PLAYGROUND_LIST).commit();
    }

    public void displayPoolListFragment() {
        PoolListFragment poolList = PoolListFragment.newInstance();
        poolList.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, poolList).addToBackStack(POOL_LIST).commit();
    }

    public boolean registerFragmentOnScreen() {
        frag = getSupportFragmentManager().findFragmentById(R.id.show_fragment_selected);
        if (frag == null)
            return true;
        else {
            return frag instanceof ExternalAccessListFragment || frag instanceof ParkingLotListFragment || frag instanceof PlaygroundListFragment
                    || frag instanceof RestListFragment || frag instanceof RoomRegisterListFragment || frag instanceof SidewalkListFragment
                    || (frag instanceof WaterFountainListFragment && !inspectionBundle.getBoolean(FROM_ROOMS));
        }
    }
}




