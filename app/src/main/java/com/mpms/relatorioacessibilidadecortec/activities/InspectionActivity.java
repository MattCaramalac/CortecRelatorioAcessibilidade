package com.mpms.relatorioacessibilidadecortec.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.StairsRampFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainFragment;

import java.util.Locale;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener {

    public int schoolEntryID;
    public static final String SCHOOL_ID_VALUE = "SCHOOL_ID_VALUE";
    Bundle fragmentSchoolID = new Bundle();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        schoolEntryID = getIntent().getIntExtra(RegisterActivity.MEMORIAL_ITEM_ENTRY,0);
        fragmentSchoolID.putInt(SCHOOL_ID_VALUE, schoolEntryID);
        }



    @Override
    public void onDropdownChoice(int choice) {
        switch (choice) {
            case 0:
                displayExternalAccessFragment(choice);
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
            default:
                displayRoomsRegisterFragment(choice);
                break;
        }

    }

    public void displayExternalAccessFragment(int chosenItem) {
        ExternalAccessFragment externalAccessFragment = ExternalAccessFragment.newInstance(chosenItem);
        externalAccessFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected,externalAccessFragment).addToBackStack(null).commit();
    }

    public void closeExternalAccessFragment() {
        ExternalAccessFragment externalAccessFragment = (ExternalAccessFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (externalAccessFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(externalAccessFragment).commit();
        }
    }

    public void displayWaterFountainFragment() {
        WaterFountainFragment waterFountainFragment = WaterFountainFragment.newInstance();
        waterFountainFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected,waterFountainFragment).commit();

    }

    public void displaySidewalkFragment() {
        SidewalkFragment sidewalkFragment = SidewalkFragment.newInstance();
        sidewalkFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected,sidewalkFragment).addToBackStack(null).commit();
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

    public void displayStairsRampFragment(int chosenItem) {
        StairsRampFragment stairsRampFragment = StairsRampFragment.newInstance(chosenItem);
        stairsRampFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, stairsRampFragment).addToBackStack(null).commit();
    }

    public void closeStairsRampFragment() {
        StairsRampFragment stairsRampFragment = (StairsRampFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (stairsRampFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(stairsRampFragment).commit();
        }
    }

}