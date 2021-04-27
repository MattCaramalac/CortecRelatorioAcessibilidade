package com.mpms.relatorioacessibilidadecortec.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.fragments.CafeteriaFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RampFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.LibraryFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SecretaryFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.StairsFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainFragment;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener {

    private int dropdownChoice = -1;

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
    }

    @Override
    public void onDropdownChoice(int choice) {
        switch (choice) {
            case 0:
                displayExternalAccessFragment(choice);
                break;
            case 2:
                displayWaterFountainFragment(choice);
                break;
            case 4:
                displaySidewalkFragment(choice);
                break;
            case 7:
                displayStairsFragment(choice);
                break;
            case 8:
                displayParkingLotFragment(choice);
                break;
            case 9:
                displayRampFragment(choice);
                break;
            case 15:
                displaySecretaryFragment(choice);
                break;
            default:
                displayRoomsRegisterFragment(choice);
                break;
        }

    }

    public void displayExternalAccessFragment(int chosenItem) {
        ExternalAccessFragment externalAccessFragment = ExternalAccessFragment.newInstance(chosenItem);
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

    public void displayWaterFountainFragment(int chosenItem) {
        WaterFountainFragment waterFountainFragment = WaterFountainFragment.newInstance(chosenItem);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected,waterFountainFragment).addToBackStack(null).commit();
    }

    public void closeWaterFountainFragment() {
        WaterFountainFragment waterFountainFragment = (WaterFountainFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (waterFountainFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(waterFountainFragment).commit();
        }
    }

    public void displaySidewalkFragment(int chosenItem) {
        SidewalkFragment sidewalkFragment = SidewalkFragment.newInstance(chosenItem);
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

    public void displaySecretaryFragment(int chosenItem) {
        SecretaryFragment secretaryFragment = SecretaryFragment.newInstance(chosenItem);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, secretaryFragment).addToBackStack(null).commit();
    }

    public void closeSecretaryFragment() {
        SecretaryFragment secretaryFragment = (SecretaryFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (secretaryFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(secretaryFragment).commit();
        }
    }

    public void displayParkingLotFragment(int chosenItem) {
        ParkingLotFragment parkingLotFragment = ParkingLotFragment.newInstance(chosenItem);
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

    public void displayStairsFragment(int chosenItem) {
        StairsFragment stairsFragment = StairsFragment.newInstance(chosenItem);
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

    public void displayRampFragment(int chosenItem) {
        RampFragment rampFragment = RampFragment.newInstance(chosenItem);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, rampFragment).addToBackStack(null).commit();
    }

    public void closeRampFragment() {
        RampFragment rampFragment = (RampFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (rampFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(rampFragment).commit();
        }
    }

}