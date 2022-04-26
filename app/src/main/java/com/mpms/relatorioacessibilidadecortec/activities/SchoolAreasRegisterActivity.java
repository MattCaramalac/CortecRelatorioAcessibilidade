package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;

public class SchoolAreasRegisterActivity extends AppCompatActivity {

    public static final String AREAS_REG_BUNDLE = "AREAS_REG_BUNDLE";
    public static final String EXT_AREA_REG = "EXT_AREA_REG";
    public static final String SUP_AREA_REG = "SUP_AREA_REG";

    MaterialButton saveQuitButton, externalAreaButton, blocksButton, supportAreaButton;

    Bundle areasBundle = new Bundle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_area_register);
        instantiateBlockViews();
        areasBundle = getIntent().getBundleExtra(SchoolRegisterActivity.SCHOOL_BUNDLE);

        saveQuitButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        externalAreaButton.setOnClickListener(v -> {
            areasBundle.putBoolean(EXT_AREA_REG, true);
            callInspectionActivity();
        });

        blocksButton.setOnClickListener(v -> {
            Intent blockSpaceIntent = new Intent(SchoolAreasRegisterActivity.this, BlockRegisterActivity.class);
            blockSpaceIntent.putExtra(AREAS_REG_BUNDLE, areasBundle);
            startActivity(blockSpaceIntent);
        });

        supportAreaButton.setOnClickListener(v -> {
            areasBundle.putBoolean(SUP_AREA_REG, true);
            callInspectionActivity();
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        areasBundle.putBoolean(EXT_AREA_REG, false);
        areasBundle.putBoolean(SUP_AREA_REG, false);
    }

    private void instantiateBlockViews() {
        saveQuitButton = findViewById(R.id.save_and_quit);
        externalAreaButton = findViewById(R.id.add_external_areas);
        blocksButton = findViewById(R.id.add_blocks);
        supportAreaButton = findViewById(R.id.add_support_area);
    }

    private void callInspectionActivity() {
        Intent inspectionIntent = new Intent(SchoolAreasRegisterActivity.this, InspectionActivity.class);
        inspectionIntent.putExtra(AREAS_REG_BUNDLE, areasBundle);
        startActivity(inspectionIntent);
    }
}
