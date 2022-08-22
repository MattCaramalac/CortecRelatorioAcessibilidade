package com.mpms.relatorioacessibilidadecortec.activities;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.AdmEquipListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.PlaygroundListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomRegisterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener, TagInterface {

    Bundle inspectionBundle = new Bundle();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    ViewModelEntry modelEntry;
    Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        modelEntry = new ViewModelEntry(getApplication());
        inspectionBundle = getIntent().getBundleExtra(AREAS_REG_BUNDLE);

        if (inspectionBundle.getInt(BLOCK_ID) == 0) {
            int areaType = 0;
            if (inspectionBundle.getBoolean(EXT_AREA_REG))
                areaType = 1;
            else if (inspectionBundle.getBoolean(SUP_AREA_REG))
                areaType = 2;
            modelEntry.getAreaFromSchool(inspectionBundle.getInt(SchoolRegisterActivity.SCHOOL_ID), areaType)
                    .observe(this, area -> inspectionBundle.putInt(BLOCK_ID, area.getBlockSpaceID()));
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
                    break;
                case 4:
                    displayRestroomListFragment();
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
                    break;
                case 3:
                    displayPlaygroundListFragment();
                    break;
                case 4:
                    displayRestroomListFragment();
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
        RestListFragment restListFragment = RestListFragment.newInstance();
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

    public boolean registerFragmentOnScreen() {
        int i = 1;
        frag = getSupportFragmentManager().findFragmentById(R.id.show_fragment_selected);
        if (frag == null)
            i = 0;
        else {
            if (frag instanceof ExternalAccessListFragment || frag instanceof ParkingLotListFragment || frag instanceof PlaygroundListFragment
                    || frag instanceof RestListFragment || frag instanceof RoomRegisterListFragment || frag instanceof SidewalkListFragment
                    || frag instanceof WaterFountainListFragment )
                i = 0;
        }
        return i == 0;
    }
}




