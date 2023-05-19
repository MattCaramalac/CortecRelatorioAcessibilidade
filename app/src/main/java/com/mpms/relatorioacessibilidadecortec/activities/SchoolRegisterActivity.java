package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.SchoolRegisterFragmentOne;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.SchoolRegisterFragmentThree;
import com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments.SchoolRegisterFragmentTwo;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class SchoolRegisterActivity extends AppCompatActivity implements TagInterface {

    Button saveUpdateCloseButton, saveUpdateContinueButton, returnButton;
    ScrollView scroll;
    Fragment frag;
    FragmentManager manager;

    public static Bundle schoolBundle = new Bundle();
    Bundle schoolRegFragment = new Bundle();

    ViewModelFragments modelFragments;
    SchoolRegisterFragmentOne fragmentOne;
    SchoolRegisterFragmentTwo fragmentTwo;
    SchoolRegisterFragmentThree fragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_school_register);

        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST))
            schoolBundle.putInt(SCHOOL_ID, getIntent().getIntExtra(MainActivity.UPDATE_REQUEST, 0));
        else
            schoolBundle.putInt(SCHOOL_ID, 0);

        instantiateSchoolRegisterActivity();

        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (firstSchFragOnScreen())
                    cancelClick();
                else {
                    setEnabled(false);
                    SchoolRegisterActivity.super.onBackPressed();
                }
                setEnabled(true);
            }
        });

        modelFragments.getDataFromFragToActivity().observe(this, schoolReg -> {
            if (schoolReg.getBoolean(CLOSE_FRAGMENT)) {
                modelFragments.setSaveUpdateSchoolReg(null);
                finish();
            }
            else {
                if (schoolReg.getBoolean(OPEN_FRAG_TWO)){
                    schoolBundle.putBoolean(RECENT_ENTRY, true);
                    if (schoolReg.getInt(SCHOOL_ID) != 0)
                        schoolBundle.putInt(SCHOOL_ID, schoolReg.getInt(SCHOOL_ID));
                    modelFragments.setSaveUpdateSchoolReg(null);
                    setSecondFragment(schoolBundle);
                    manager.beginTransaction().replace(R.id.show_register_school_fragment, fragmentTwo).addToBackStack(null).commit();
                    scroll.smoothScrollTo(0,0); //dá scroll depois pelo menos, porém muito "rápido"
                } else if (schoolReg.getBoolean(OPEN_FRAG_THREE)) {
                    modelFragments.setSaveUpdateSchoolReg(null);
                    setThirdFragment(schoolBundle);
                    manager.beginTransaction().replace(R.id.show_register_school_fragment, fragmentThree).addToBackStack(null).commit();
                    scroll.smoothScrollTo(0,0);
                } else if (schoolReg.getBoolean(NEXT_ACTIVITY)) {
                    modelFragments.setSaveUpdateSchoolReg(null);
                    Intent blockSpaceIntent = new Intent(this, SchoolAreasRegisterActivity.class);
                    blockSpaceIntent.putExtra(SCHOOL_BUNDLE, schoolBundle);
                    startActivity(blockSpaceIntent);
                }
            }
        });
    }

    private boolean firstSchFragOnScreen() {
        frag = getSupportFragmentManager().findFragmentById(R.id.show_register_school_fragment);
        if (frag == null)
            return false;
        else {
            return frag instanceof SchoolRegisterFragmentOne;
        }
    }

    private void cancelClick() {
        if (schoolBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.delSchoolWithID(schoolBundle.getInt(SCHOOL_ID));
                schoolBundle = null;
                finish();
            });
            FragmentManager manager = this.getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            finish();
    }

    public static void provideSchoolID(Bundle bundle, Bundle bundleFrag) {
        if (bundle != null) {
            bundleFrag.putInt(SCHOOL_ID, bundle.getInt(SCHOOL_ID));
        }
    }

    private void instantiateSchoolRegisterActivity() {
//        MaterialButtons
        saveUpdateCloseButton = findViewById(R.id.save_update_close_register);
        saveUpdateContinueButton = findViewById(R.id.save_update_continue_register);
        returnButton = findViewById(R.id.return_school_button);
//        ScrollView
        scroll = findViewById(R.id.register_scroll_view);
//        Set Activity Visuals
        setButtonConfiguration();
        setFirstFragment(schoolBundle);
//        FragmentManager
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.show_register_school_fragment, fragmentOne).commit();
        scroll.scrollTo(0,0);
//        Listener
        saveUpdateCloseButton.setOnClickListener(this::buttonListener);
        saveUpdateContinueButton.setOnClickListener(this::buttonListener);
        returnButton.setOnClickListener(this::buttonListener);
//        ViewModel
        modelFragments = new ViewModelProvider(this).get(ViewModelFragments.class);
    }

    private void setFirstFragment(Bundle bundle) {
        fragmentOne = SchoolRegisterFragmentOne.newInstance(bundle);
    }

    private void setSecondFragment(Bundle bundle) {
        fragmentTwo = SchoolRegisterFragmentTwo.newInstance(bundle);
    }

    private void setThirdFragment(Bundle bundle) {
        fragmentThree = SchoolRegisterFragmentThree.newInstance(bundle);
    }

    private void setButtonConfiguration() {
        if (schoolBundle.getInt(SCHOOL_ID) != 0) {
            saveUpdateCloseButton.setText(R.string.label_update_close_button);
            saveUpdateContinueButton.setText(R.string.label_update_continue_button);
        } else {
            saveUpdateCloseButton.setText(R.string.label_save_close_button);
            saveUpdateContinueButton.setText(R.string.label_save_continue_button);
        }

    }

    private void buttonListener(View view) {
        if (view == saveUpdateCloseButton) {
            if (schoolBundle.getInt(SCHOOL_ID) != 0) {
                schoolRegFragment.putBoolean(UPDATE_CLOSE, true);
                schoolRegFragment.putBoolean(UPDATE_CONTINUE, false);
            } else {
                schoolRegFragment.putBoolean(SAVE_CLOSE, true);
                schoolRegFragment.putBoolean(SAVE_CONTINUE, false);
            }
            modelFragments.setSaveUpdateSchoolReg(schoolRegFragment);
        }
        else if (view == saveUpdateContinueButton) {
            if (schoolBundle.getInt(SCHOOL_ID) != 0) {
                schoolRegFragment.putBoolean(UPDATE_CLOSE, false);
                schoolRegFragment.putBoolean(UPDATE_CONTINUE, true);
            } else {
                schoolRegFragment.putBoolean(SAVE_CLOSE, false);
                schoolRegFragment.putBoolean(SAVE_CONTINUE, true);
            }
            modelFragments.setSaveUpdateSchoolReg(schoolRegFragment);
        }
        else {
            if (firstSchFragOnScreen())
                cancelClick();
            else
                this.getSupportFragmentManager().popBackStackImmediate();
        }
    }

}