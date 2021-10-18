package com.mpms.relatorioacessibilidadecortec.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.OtherSpacesListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestroomListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainListFragment;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener {

    public static final String PARKING_LIST = "PARKING_LIST";
    public static final String EXTERNAL_LIST = "EXTERNAL_LIST";
    public static final String REST_LIST = "REST_LIST";
    public static final String WATER_LIST = "WATER_LIST";
    public static final String OTHERS_LIST = "OTHERS_LIST";
    public static final String SIDEWALK_LIST = "SIDEWALK_LIST";
    public static final String ALLOW_UPDATE = "ALLOW_UPDATE";
    Bundle fragmentSchoolID = new Bundle();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        fragmentSchoolID = getIntent().getBundleExtra(SchoolRegisterActivity.SCHOOL_BUNDLE);
    }


    @Override
    public void onDropdownChoice(int choice) {
        switch (choice) {
            case 0:
                displayExtAccessListFragment();
                break;
            case 1:
                displayRestroomListFragment();
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
            case 16:
                displayOtherSpacesListFragment();
                break;
            default:
                displayRoomsRegisterListFragment(choice);
                break;
        }

    }

    public void displayExtAccessListFragment() {
        ExternalAccessListFragment extAccessList = ExternalAccessListFragment.newInstance();
        extAccessList.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, extAccessList).addToBackStack(EXTERNAL_LIST).commit();
    }

    public void displayRestroomListFragment() {
        RestroomListFragment restListFragment = RestroomListFragment.newInstance();
        restListFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restListFragment).addToBackStack(REST_LIST).commit();
    }

    public void displayFountainListFragment() {
        WaterFountainListFragment waterListFrag = WaterFountainListFragment.newInstance();
        waterListFrag.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, waterListFrag).addToBackStack(WATER_LIST).commit();

    }

    public void displaySidewalkListFragment() {
        SidewalkListFragment sidewalkList = SidewalkListFragment.newInstance();
        sidewalkList.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkList).addToBackStack(SIDEWALK_LIST).commit();
    }

    public void displayRoomsRegisterListFragment(int chosenItem) {
        RoomsRegisterFragment roomsRegisterFragment = RoomsRegisterFragment.newInstance(chosenItem);
        roomsRegisterFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, roomsRegisterFragment).addToBackStack(null).commit();
    }

    public void displayParkingLotListFragment() {
        ParkingLotListFragment parkingList = ParkingLotListFragment.newInstance();
        parkingList.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, parkingList).addToBackStack(PARKING_LIST).commit();
    }

    public void displayOtherSpacesListFragment() {
        OtherSpacesListFragment otherListFrag = OtherSpacesListFragment.newInstance();
        otherListFrag.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, otherListFrag).addToBackStack(OTHERS_LIST).commit();
    }

    public void displayStairsRampListFragment(int chosenItem) {
        RampStairsFragment rampStairsFragment = RampStairsFragment.newInstance(chosenItem);
        fragmentSchoolID.putInt(ALLOW_UPDATE, 0);
        rampStairsFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, rampStairsFragment).addToBackStack(null).commit();
    }
}




