package com.mpms.relatorioacessibilidadecortec.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.OtherSpacesListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestroomListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainListFragment;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener {

    public static final String EXTERNAL_LIST = "EXTERNAL_LIST";
    public static final String REST_LIST = "REST_LIST";
    public static final String WATER_LIST = "WATER_LIST";
    public static final String OTHERS_LIST = "OTHERS_LIST";
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
                displayExternalAccessFragment();
                break;
            case 1:
                displayRestroomFragment();
                break;
            case 2:
                displayWaterFountainFragment();
                break;
            case 4:
                displaySidewalkFragment();
                break;
            case 7:
            case 9:
                displayStairsRampFragment(choice);
                break;
            case 8:
                displayParkingLotFragment();
                break;
            case 16:
                displayOtherSpacesFragment();
                break;
            default:
                displayRoomsRegisterFragment(choice);
                break;
        }

    }

    public void displayExternalAccessFragment() {
        ExternalAccessListFragment extAccessList = ExternalAccessListFragment.newInstance();
        extAccessList.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, extAccessList).addToBackStack(EXTERNAL_LIST).commit();
    }

    public void displayRestroomFragment() {
        RestroomListFragment restListFragment = RestroomListFragment.newInstance();
        restListFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restListFragment).addToBackStack(REST_LIST).commit();
    }

    public void displayWaterFountainFragment() {
        WaterFountainListFragment waterListFrag = WaterFountainListFragment.newInstance();
        waterListFrag.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, waterListFrag).addToBackStack(WATER_LIST).commit();

    }

    public void displaySidewalkFragment() {
        SidewalkFragment sidewalkFragment = SidewalkFragment.newInstance();
        sidewalkFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkFragment).addToBackStack(null).commit();
    }

    public void displayRoomsRegisterFragment(int chosenItem) {
        RoomsRegisterFragment roomsRegisterFragment = RoomsRegisterFragment.newInstance(chosenItem);
        roomsRegisterFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, roomsRegisterFragment).addToBackStack(null).commit();
    }

    public void displayParkingLotFragment() {
        ParkingLotFragment parkingLotFragment = ParkingLotFragment.newInstance();
        parkingLotFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotFragment).addToBackStack(null).commit();
    }

    public void displayOtherSpacesFragment() {
        OtherSpacesListFragment otherListFrag = OtherSpacesListFragment.newInstance();
        otherListFrag.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, otherListFrag).addToBackStack(OTHERS_LIST).commit();
    }

    public void displayStairsRampFragment(int chosenItem) {
        RampStairsFragment rampStairsFragment = RampStairsFragment.newInstance(chosenItem);
        fragmentSchoolID.putInt(ALLOW_UPDATE, 0);
        rampStairsFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, rampStairsFragment).addToBackStack(null).commit();
    }
}




