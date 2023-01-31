package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.SchoolRegisterFragmentOne;
import com.mpms.relatorioacessibilidadecortec.fragments.SchoolRegisterFragmentThree;
import com.mpms.relatorioacessibilidadecortec.fragments.SchoolRegisterFragmentTwo;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class SchoolRegisterActivity extends AppCompatActivity {

    public static final String SCHOOL_ID = "SCHOOL_ID";
    public static final String SAVE_CLOSE = "SAVE_CLOSE";
    public static final String SAVE_CONTINUE = "SAVE_CONTINUE";
    public static final String UPDATE_CLOSE = "UPDATE_CLOSE";
    public static final String UPDATE_CONTINUE = "UPDATE_CONTINUE";
    public static final String CLOSE_FRAGMENT = "CLOSE_FRAGMENT";
    public static final String OPEN_FRAG_TWO = "OPEN_FRAG_TWO";
    public static final String OPEN_FRAG_THREE = "OPEN_FRAG_THREE";
    public static final String NEXT_ACTIVITY = "NEXT_ACTIVITY";
    public static final String SCHOOL_BUNDLE = "SCHOOL_BUNDLE";

    Button saveUpdateCloseButton, saveUpdateContinueButton;
    ScrollView scroll;
    FragmentManager manager;

    public static Bundle schoolRegBundle = new Bundle();
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
            schoolRegBundle.putInt(SCHOOL_ID, getIntent().getIntExtra(MainActivity.UPDATE_REQUEST, 0));
        else
            schoolRegBundle.putInt(SCHOOL_ID, 0);

        instantiateSchoolRegisterActivity();


        modelFragments.getDataFromFragToActivity().observe(this, schoolReg -> {
            if (schoolReg.getBoolean(CLOSE_FRAGMENT)) {
                modelFragments.setSaveUpdateSchoolReg(null);
                finish();
            }
            else {
                if (schoolReg.getBoolean(OPEN_FRAG_TWO)){
                    if (schoolReg.getInt(SCHOOL_ID) != 0)
                        schoolRegBundle.putInt(SCHOOL_ID, schoolReg.getInt(SCHOOL_ID));
                    modelFragments.setSaveUpdateSchoolReg(null);
                    setSecondFragment(schoolRegBundle);
                    manager.beginTransaction().replace(R.id.show_register_school_fragment, fragmentTwo).addToBackStack(null).commit();
                    scroll.smoothScrollTo(0,0); //dá scroll depois pelo menos, porém muito "rápido"
                } else if (schoolReg.getBoolean(OPEN_FRAG_THREE)) {
                    modelFragments.setSaveUpdateSchoolReg(null);
                    setThirdFragment(schoolRegBundle);
                    manager.beginTransaction().replace(R.id.show_register_school_fragment, fragmentThree).addToBackStack(null).commit();
                    scroll.smoothScrollTo(0,0);
                } else if (schoolReg.getBoolean(NEXT_ACTIVITY)) {
                    modelFragments.setSaveUpdateSchoolReg(null);
                    Intent blockSpaceIntent = new Intent(this, SchoolAreasRegisterActivity.class);
                    blockSpaceIntent.putExtra(SCHOOL_BUNDLE, schoolRegBundle);
                    startActivity(blockSpaceIntent);
                }
            }
        });
    }

    public static void provideSchoolID(Bundle bundle, Bundle bundleFrag) {
        if (bundle != null) {
            bundleFrag.putInt(SCHOOL_ID, bundle.getInt(SCHOOL_ID));
        }
    }

    private void instantiateSchoolRegisterActivity() {
        saveUpdateCloseButton = findViewById(R.id.save_update_close_register);
        saveUpdateContinueButton = findViewById(R.id.save_update_continue_register);
        scroll = findViewById(R.id.register_scroll_view);

        setButtonConfiguration();

        setFirstFragment(schoolRegBundle);

        manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.show_register_school_fragment, fragmentOne).commit();
        scroll.scrollTo(0,0);


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
        if (schoolRegBundle.getInt(SCHOOL_ID) != 0) {
            saveUpdateCloseButton.setText(R.string.label_update_close_button);
            saveUpdateContinueButton.setText(R.string.label_update_continue_button);
        } else {
            saveUpdateCloseButton.setText(R.string.label_save_close_button);
            saveUpdateContinueButton.setText(R.string.label_save_continue_button);
        }
        saveUpdateCloseButton.setOnClickListener(this::setSaveUpdateCloseButtonsLogic);
        saveUpdateContinueButton.setOnClickListener(this::setSaveUpdateContinueButtonsLogic);
    }

    private void setSaveUpdateCloseButtonsLogic(View view) {
        if (schoolRegBundle.getInt(SCHOOL_ID) != 0) {
            schoolRegFragment.putBoolean(UPDATE_CLOSE, true);
            schoolRegFragment.putBoolean(UPDATE_CONTINUE, false);
        } else {
            schoolRegFragment.putBoolean(SAVE_CLOSE, true);
            schoolRegFragment.putBoolean(SAVE_CONTINUE, false);
        }
        modelFragments.setSaveUpdateSchoolReg(schoolRegFragment);
    }

    private void setSaveUpdateContinueButtonsLogic(View view) {
        if (schoolRegBundle.getInt(SCHOOL_ID) != 0) {
            schoolRegFragment.putBoolean(UPDATE_CLOSE, false);
            schoolRegFragment.putBoolean(UPDATE_CONTINUE, true);
        } else {
            schoolRegFragment.putBoolean(SAVE_CLOSE, false);
            schoolRegFragment.putBoolean(SAVE_CONTINUE, true);
        }
        modelFragments.setSaveUpdateSchoolReg(schoolRegFragment);
    }
}