package com.mpms.relatorioacessibilidadecortec.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mpms.relatorioacessibilidadecortec.fragments.CoordinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.LibraryFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment;
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
                displayExternalAccessFragment();
                break;
            case 1:
                displayWaterFountainFragment();
                break;
            case 2:
                displayLibraryFragment();
                break;
            case 3:
                displaySidewalkFragment();
                break;
            case 4:
                displayCoordinationFragment();
                break;
        }

    }

    public void displayExternalAccessFragment() {
        ExternalAccessFragment externalAccessFragment = ExternalAccessFragment.newInstance();
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

    public void displayLibraryFragment() {
        LibraryFragment libraryFragment = LibraryFragment.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected,libraryFragment).addToBackStack(null).commit();
    }

    public void closeLibraryFragment() {
        LibraryFragment libraryFragment = (LibraryFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (libraryFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(libraryFragment).commit();
        }
    }

    public void displaySidewalkFragment() {
        SidewalkFragment sidewalkFragment = SidewalkFragment.newInstance();
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

    public void displayCoordinationFragment() {
        CoordinationFragment coordinationFragment = CoordinationFragment.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected,coordinationFragment).addToBackStack(null).commit();
    }

    public void closeCoordinationFragment() {
        CoordinationFragment coordinationFragment = (CoordinationFragment) fragmentManager.findFragmentById(R.id.show_fragment_selected);
        if (coordinationFragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(coordinationFragment).commit();
        }
    }
}