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

import static com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity.BLOCK_SPACE_REGISTER;

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
    Bundle blockSpaceBundle = new Bundle();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        blockSpaceBundle.putInt(BLOCK_SPACE_REGISTER, getIntent().getIntExtra(BLOCK_SPACE_REGISTER, 0));
    }


    @Override
    public void onDropdownChoice(int choice) {
        switch (choice) {
            case 0:
                displayExtAccessListFragment();
                break;
            case 1:
//                displayRestroomListFragment();
                break;
            case 2:
                displayFountainListFragment();
                break;
            case 4:
                displaySidewalkListFragment();
                break;
            case 7:
            case 9:
                displayStairsRampListFragment(choice);
                break;
            case 8:
                displayParkingLotListFragment();
                break;
            case 17:
                displayAdmEquipListFragment();
                break;
            case 18:
                displayPlaygroundListFragment();
                break;
            default:
                displayRoomsRegisterListFragment(choice);
                break;
        }

    }

    public void displayExtAccessListFragment() {
        ExternalAccessListFragment extAccessList = ExternalAccessListFragment.newInstance();
        extAccessList.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, extAccessList).addToBackStack(EXTERNAL_LIST).commit();
    }

    public void displayRestroomListFragment() {
        RestroomListFragment restListFragment = RestroomListFragment.newInstance();
        restListFragment.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restListFragment).addToBackStack(REST_LIST).commit();
    }

    public void displayFountainListFragment() {
        WaterFountainListFragment waterListFrag = WaterFountainListFragment.newInstance();
        waterListFrag.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, waterListFrag).addToBackStack(WATER_LIST).commit();

    }

    public void displaySidewalkListFragment() {
        SidewalkListFragment sidewalkList = SidewalkListFragment.newInstance();
        sidewalkList.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkList).addToBackStack(SIDEWALK_LIST).commit();
    }

    public void displayRoomsRegisterListFragment(int chosenItem) {
        RoomRegisterListFragment roomList = RoomRegisterListFragment.newInstance(chosenItem);
        roomList.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, roomList).addToBackStack(ROOM_LIST).commit();
    }

    public void displayParkingLotListFragment() {
        ParkingLotListFragment parkingList = ParkingLotListFragment.newInstance();
        parkingList.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, parkingList).addToBackStack(PARKING_LIST).commit();
    }

    public void displayStairsRampListFragment(int chosenItem) {
        RampStairsListFragment rampStairsList = RampStairsListFragment.newInstance(chosenItem);
        blockSpaceBundle.putInt(ALLOW_UPDATE, 0);
        rampStairsList.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, rampStairsList).addToBackStack(RAMP_STAIRS_LIST).commit();
    }

    public void displayAdmEquipListFragment() {
        AdmEquipListFragment admEquipList = AdmEquipListFragment.newInstance();
        admEquipList.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, admEquipList).addToBackStack(ADM_EQUIP_LIST).commit();
    }

    public void displayPlaygroundListFragment() {
        PlaygroundListFragment playListFragment = PlaygroundListFragment.newInstance();
        playListFragment.setArguments(blockSpaceBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, playListFragment).addToBackStack(PLAYGROUND_LIST).commit();
    }
}




