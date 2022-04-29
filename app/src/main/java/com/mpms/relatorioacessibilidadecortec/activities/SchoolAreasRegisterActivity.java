package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import static com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity.SCHOOL_ID;

public class SchoolAreasRegisterActivity extends AppCompatActivity {

    public static final String AREAS_REG_BUNDLE = "AREAS_REG_BUNDLE";
    public static final String EXT_AREA_REG = "EXT_AREA_REG";
    public static final String SUP_AREA_REG = "SUP_AREA_REG";
    public static final String EXT_ID = "EXT_ID";
    public static final String SUP_ID = "SUP_ID";

    boolean hasExtArea = true;
    boolean hasSupArea = true;

    int extID = 0;
    int supID = 0;

    MaterialButton saveQuitButton, externalAreaButton, blocksButton, supportAreaButton;

    ViewModelEntry modelEntry;

    Bundle areasBundle = new Bundle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_area_register);
        instantiateBlockViews();
        areasBundle = getIntent().getBundleExtra(SchoolRegisterActivity.SCHOOL_BUNDLE);

        modelEntry.getAreaFromSchool(areasBundle.getInt(SCHOOL_ID), 1).observe(this, extArea -> {
            if (extArea != null)
                extID = extArea.getBlockSpaceID();
                else
                hasExtArea = false;
        });

        modelEntry.getAreaFromSchool(areasBundle.getInt(SCHOOL_ID), 2).observe(this, supArea -> {
            if (supArea != null)
                supID = supArea.getBlockSpaceID();
            else
                hasSupArea = false;
        });


        saveQuitButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        blocksButton.setOnClickListener(v -> {
            Intent blockSpaceIntent = new Intent(SchoolAreasRegisterActivity.this, BlockRegisterActivity.class);
            blockSpaceIntent.putExtra(AREAS_REG_BUNDLE, areasBundle);
            startActivity(blockSpaceIntent);
        });

        externalAreaButton.setOnClickListener(v -> {
            if (!hasExtArea) {
                BlockSpaceEntry newArea = newAreas(areasBundle, 1);
                ViewModelEntry.insertBlockSpace(newArea);
            }
            areasBundle.putBoolean(EXT_AREA_REG, true);
            areasBundle.putInt(BlockRegisterActivity.BLOCK_ID, extID);
            callInspectionActivity();
        });

        supportAreaButton.setOnClickListener(v -> {
            if (!hasSupArea) {
                BlockSpaceEntry newArea = newAreas(areasBundle, 2);
                ViewModelEntry.insertBlockSpace(newArea);
            }
            areasBundle.putBoolean(SUP_AREA_REG, true);
            areasBundle.putInt(BlockRegisterActivity.BLOCK_ID, supID);
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

        modelEntry =  new ViewModelEntry(getApplication());
    }

    private void callInspectionActivity() {
        Intent inspectionIntent = new Intent(SchoolAreasRegisterActivity.this, InspectionActivity.class);
        inspectionIntent.putExtra(AREAS_REG_BUNDLE, areasBundle);
        startActivity(inspectionIntent);
    }

    private BlockSpaceEntry newAreas(Bundle bundle, int areaType) {
        return new BlockSpaceEntry(bundle.getInt(SCHOOL_ID), areaType, 1);
    }
}
