package com.mpms.relatorioacessibilidadecortec.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.SchoolRegisterFragmentOne;
import com.mpms.relatorioacessibilidadecortec.fragments.SchoolRegisterFragmentTwo;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class NewSchoolRegisterActivity extends AppCompatActivity {

    public static final String SAVE_CLOSE = "SAVE_CLOSE";
    public static final String SAVE_CONTINUE = "SAVE_CONTINUE";
    public static final String UPDATE_CLOSE = "UPDATE_CLOSE";
    public static final String UPDATE_CONTINUE = "UPDATE_CONTINUE";
    public static final String CLOSE_FRAGMENT = "CLOSE_FRAGMENT";
    public static final String OPEN_FRAG_TWO = "OPEN_FRAG_TWO";
    public static final String OPEN_FRAG_THREE = "OPEN_FRAG_THREE";
    public static final String END_SCHOOL_REG = "END_SCHOOL_REG";

    Button saveUpdateCloseButton, saveUpdateContinueButton;
    FrameLayout registerFragment;
    FragmentManager manager;

    Bundle schoolRegBundle = new Bundle();
    Bundle schoolRegFragment = new Bundle();

    ViewModelFragments modelFragments;
    SchoolRegisterFragmentOne fragmentOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_school_register);

        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST)){
            schoolRegBundle.putInt(MainActivity.UPDATE_REQUEST, getIntent().getIntExtra(MainActivity.UPDATE_REQUEST, -1));
        }

        instantiateSchoolRegisterActivity();
        setButtonConfiguration();

        modelFragments.getDataFromFragToActivity().observe(this, schoolReg -> {
            if (schoolReg.getBoolean(CLOSE_FRAGMENT)) {
                modelFragments.setSaveUpdateSchoolReg(null);
                finish();
            }
            else {
                if (schoolReg.getBoolean(OPEN_FRAG_TWO)){
                    modelFragments.setSaveUpdateSchoolReg(null);
                    manager.beginTransaction().replace(R.id.show_register_school_fragment, new SchoolRegisterFragmentTwo()).commit();
                } else if (schoolReg.getBoolean(OPEN_FRAG_THREE)) {
                    modelFragments.setSaveUpdateSchoolReg(null);
                } else if (schoolReg.getBoolean(END_SCHOOL_REG)) {
                    modelFragments.setSaveUpdateSchoolReg(null);
                }
            }
        });
    }

    private void instantiateSchoolRegisterActivity() {
        saveUpdateCloseButton = findViewById(R.id.save_update_close_register);
        saveUpdateContinueButton = findViewById(R.id.save_update_continue_register);

        registerFragment = findViewById(R.id.show_register_school_fragment);

        setFirstFragment(schoolRegBundle);

        manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.show_register_school_fragment, fragmentOne).commit();

        modelFragments = new ViewModelProvider(this).get(ViewModelFragments.class);
    }

    private void setFirstFragment(Bundle bundle) {
        fragmentOne = SchoolRegisterFragmentOne.newInstance(bundle);
    }

    private void setButtonConfiguration() {
        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST)) {
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
        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST)) {
            schoolRegFragment.putBoolean(UPDATE_CLOSE, true);
        } else {
            schoolRegFragment.putBoolean(SAVE_CLOSE, true);
        }
        modelFragments.setSaveUpdateSchoolReg(schoolRegFragment);
    }

    private void setSaveUpdateContinueButtonsLogic(View view) {
        if (getIntent().hasExtra(MainActivity.UPDATE_REQUEST)) {
            schoolRegFragment.putBoolean(UPDATE_CONTINUE, true);
        } else {
            schoolRegFragment.putBoolean(SAVE_CONTINUE, true);
        }
        modelFragments.setSaveUpdateSchoolReg(schoolRegFragment);
    }
}