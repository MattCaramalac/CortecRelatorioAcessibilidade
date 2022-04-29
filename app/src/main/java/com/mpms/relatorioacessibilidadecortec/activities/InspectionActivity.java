package com.mpms.relatorioacessibilidadecortec.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.AdmEquipListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.PlaygroundListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestroomListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomRegisterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener {

    public static final String LOAD_CHILD_DATA = "LOAD_CHILD_DATA";
    public static final String GATHER_CHILD_DATA = "GATHER_CHILD_DATA";
    public static final String ADD_ITEM_REQUEST = "ADD_ITEM_REQUEST";
    public static final String CHILD_DATA_LISTENER = "CHILD_DATA_LISTENER";
    public static final String CHILD_DATA_COMPLETE = "CHILD_DATA_COMPLETE";
    public static final String CLEAR_CHILD_DATA = "CLEAR_CHILD_DATA";

    public static final String PARKING_LIST = "PARKING_LIST";
    public static final String EXTERNAL_LIST = "EXTERNAL_LIST";
    public static final String REST_LIST = "REST_LIST";
    public static final String WATER_LIST = "WATER_LIST";
    public static final String SIDEWALK_LIST = "SIDEWALK_LIST";
    public static final String ALLOW_UPDATE = "ALLOW_UPDATE";
    public static final String RAMP_STAIRS_LIST = "RAMP_STAIRS_LIST";
    public static final String ROOM_LIST = "ROOM_LIST";
    public static final String ADM_EQUIP_LIST = "ADM_EQUIP_LIST";
    public static final String PLAYGROUND_LIST = "PLAYGROUND_LIST";
    Bundle inspectionBundle = new Bundle();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    ViewModelEntry modelEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        modelEntry = new ViewModelEntry(getApplication());
        inspectionBundle = getIntent().getBundleExtra(SchoolAreasRegisterActivity.AREAS_REG_BUNDLE);

        if (inspectionBundle.getBoolean(SchoolAreasRegisterActivity.EXT_AREA_REG)
                && inspectionBundle.getInt(BlockRegisterActivity.BLOCK_ID) == 0) {
            modelEntry.getAreaFromSchool(inspectionBundle.getInt(SchoolRegisterActivity.SCHOOL_ID), 1)
                    .observe(this, extArea ->
                            inspectionBundle.putInt(BlockRegisterActivity.BLOCK_ID, extArea.getBlockSpaceID()));
        } else if (inspectionBundle.getBoolean(SchoolAreasRegisterActivity.SUP_AREA_REG)
                && inspectionBundle.getInt(BlockRegisterActivity.BLOCK_ID) == 0) {
            modelEntry.getAreaFromSchool(inspectionBundle.getInt(SchoolRegisterActivity.SCHOOL_ID), 2)
                    .observe(this, supArea ->
                            inspectionBundle.putInt(BlockRegisterActivity.BLOCK_ID, supArea.getBlockSpaceID()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        InspectionMemorial newFrag = InspectionMemorial.newInstance();
        newFrag.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.chooseItemMemorial, newFrag).commit();
    }

    @Override
    public void onDropdownChoice(int choice) {
        if (inspectionBundle.getBoolean(SchoolAreasRegisterActivity.EXT_AREA_REG)) {
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
                    break;
            }
        } else if (inspectionBundle.getBoolean(SchoolAreasRegisterActivity.SUP_AREA_REG)) {
            switch (choice) {
                case 0:
                    displayFountainListFragment();
                    break;
                case 1:
                    displayParkingLotListFragment();
                    break;
                case 2:
                    displayRoomsRegisterListFragment(choice);
                    break;
                case 3:
                    displayPlaygroundListFragment();
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
                    break;
            }
        }
    }

    public void displayExtAccessListFragment() {
        ExternalAccessListFragment extAccessList = ExternalAccessListFragment.newInstance();
        extAccessList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, extAccessList).addToBackStack(EXTERNAL_LIST).commit();
    }

    public void displayRestroomListFragment() {
        RestroomListFragment restListFragment = RestroomListFragment.newInstance();
        restListFragment.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restListFragment).addToBackStack(REST_LIST).commit();
    }

    public void displayFountainListFragment() {
        WaterFountainListFragment waterListFrag = WaterFountainListFragment.newInstance();
        waterListFrag.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, waterListFrag).addToBackStack(WATER_LIST).commit();

    }

    public void displaySidewalkListFragment() {
        SidewalkListFragment sidewalkList = SidewalkListFragment.newInstance();
        sidewalkList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkList).addToBackStack(SIDEWALK_LIST).commit();
    }

    public void displayRoomsRegisterListFragment(int chosenItem) {
        RoomRegisterListFragment roomList = RoomRegisterListFragment.newInstance(chosenItem);
        roomList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, roomList).addToBackStack(ROOM_LIST).commit();
    }

    public void displayParkingLotListFragment() {
        ParkingLotListFragment parkingList = ParkingLotListFragment.newInstance();
        parkingList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, parkingList).addToBackStack(PARKING_LIST).commit();
    }

    public void displayStairsRampListFragment(int chosenItem) {
        RampStairsListFragment rampStairsList = RampStairsListFragment.newInstance(chosenItem);
        inspectionBundle.putInt(ALLOW_UPDATE, 0);
        rampStairsList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, rampStairsList).addToBackStack(RAMP_STAIRS_LIST).commit();
    }

    public void displayAdmEquipListFragment() {
        AdmEquipListFragment admEquipList = AdmEquipListFragment.newInstance();
        admEquipList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, admEquipList).addToBackStack(ADM_EQUIP_LIST).commit();
    }

    public void displayPlaygroundListFragment() {
        PlaygroundListFragment playListFragment = PlaygroundListFragment.newInstance();
        playListFragment.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, playListFragment).addToBackStack(PLAYGROUND_LIST).commit();
    }
}




