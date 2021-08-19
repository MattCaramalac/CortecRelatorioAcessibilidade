package com.mpms.relatorioacessibilidadecortec.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mpms.relatorioacessibilidadecortec.fragments.OtherSpacesFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RampFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestroomFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.StairsFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainFragment;

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
            //criar o fragmento para os banheiros/sanitários -> case 1
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
                displayStairsFragment();
                break;
            case 8:
                displayParkingLotFragment();
                break;
            case 9:
                displayRampFragment();
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
        fragmentTransaction.replace(R.id.show_fragment_selected,externalAccessFragment).addToBackStack(null).commit();
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
        fragmentTransaction.replace(R.id.show_fragment_selected,restroomFragment).addToBackStack(null).commit();
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

    public void displayStairsFragment() {
        StairsFragment stairsFragment = StairsFragment.newInstance();
        stairsFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, stairsFragment).addToBackStack(null).commit();
    }

    public void closeStairsFragment() {
        StairsFragment stairsFragment = (StairsFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (stairsFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(stairsFragment).commit();
        }
    }

    public void displayRampFragment() {
        RampFragment ramp = RampFragment.newInstance();
        ramp.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, ramp).addToBackStack(null).commit();
    }

    public void closeRampFragment() {
        StairsFragment stairsFragment = (StairsFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (stairsFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(stairsFragment).commit();
        }
    }

    public void displayOtherSpacesFragment() {
        OtherSpacesFragment otherSpacesFragment = OtherSpacesFragment.newInstance();
        otherSpacesFragment.setArguments(fragmentSchoolID);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, otherSpacesFragment).addToBackStack(null).commit();
        }
    }

