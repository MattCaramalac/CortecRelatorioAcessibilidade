package com.mpms.relatorioacessibilidadecortec.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.OtherSpacesFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RampStairsFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestroomFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainFragment;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener {

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
        ExternalAccessFragment externalAccessFragment = ExternalAccessFragment.newInstance();
        externalAccessFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, externalAccessFragment).addToBackStack(null).commit();
    }

    public void closeExternalAccessFragment() {
        ExternalAccessFragment externalAccessFragment = (ExternalAccessFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (externalAccessFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(externalAccessFragment).commit();
        }
    }

    public void displayRestroomFragment() {
        RestroomFragment restroomFragment = RestroomFragment.newInstance();
        restroomFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restroomFragment).addToBackStack(null).commit();
    }

    public void displayWaterFountainFragment() {
        WaterFountainFragment waterFountainFragment = WaterFountainFragment.newInstance();
        waterFountainFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, waterFountainFragment).commit();

    }

    public void displaySidewalkFragment() {
        SidewalkFragment sidewalkFragment = SidewalkFragment.newInstance();
        sidewalkFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkFragment).addToBackStack(null).commit();
    }

    public void closeSidewalkFragment() {
        SidewalkFragment sidewalkFragment = (SidewalkFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (sidewalkFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(sidewalkFragment).commit();
        }
    }

    public void displayRoomsRegisterFragment(int chosenItem) {
        RoomsRegisterFragment roomsRegisterFragment = RoomsRegisterFragment.newInstance(chosenItem);
        roomsRegisterFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, roomsRegisterFragment).addToBackStack(null).commit();
    }

    public void closeRoomsRegisterFragment() {
        RoomsRegisterFragment roomsRegisterFragment = (RoomsRegisterFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (roomsRegisterFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(roomsRegisterFragment).commit();
        }
    }

    public void displayParkingLotFragment() {
        ParkingLotFragment parkingLotFragment = ParkingLotFragment.newInstance();
        parkingLotFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotFragment).addToBackStack(null).commit();
    }

    public void closeParkingLotFragment() {
        ParkingLotFragment parkingLotFragment = (ParkingLotFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (parkingLotFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(parkingLotFragment).commit();
        }
    }

    public void displayOtherSpacesFragment() {
        OtherSpacesFragment otherSpacesFragment = OtherSpacesFragment.newInstance();
        otherSpacesFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, otherSpacesFragment).addToBackStack(null).commit();
    }

    public void closeOtherSpacesFragment() {
        OtherSpacesFragment otherSpaces = (OtherSpacesFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (otherSpaces != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(otherSpaces).commit();
        }
    }

    public void displayStairsRampFragment(int chosenItem) {
        RampStairsFragment rampStairsFragment = RampStairsFragment.newInstance(chosenItem);
        fragmentSchoolID.putInt(ALLOW_UPDATE, 0);
        rampStairsFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, rampStairsFragment).addToBackStack(null).commit();
    }

    public void closeStairsRampFragment() {
        RampStairsFragment rampStairsFragment = (RampStairsFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (rampStairsFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(rampStairsFragment).commit();
        }
    }
}




