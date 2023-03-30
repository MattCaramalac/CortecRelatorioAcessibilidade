package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class SchoolAreasRegisterActivity extends AppCompatActivity implements TagInterface {

    boolean hasExtArea = true;
    boolean hasSupArea = true;

    int extID = 0;
    int supID = 0;

    MaterialButton saveQuitButton, externalAreaButton, blocksButton, supportAreaButton, circulationButton;

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
            areasBundle.putInt(BLOCK_ID, extID);
            callInspectionActivity();
        });

        supportAreaButton.setOnClickListener(v -> {
            if (!hasSupArea) {
                BlockSpaceEntry newArea = newAreas(areasBundle, 2);
                ViewModelEntry.insertBlockSpace(newArea);
            }
            areasBundle.putBoolean(SUP_AREA_REG, true);
            areasBundle.putInt(BLOCK_ID, supID);
            callInspectionActivity();
        });

        circulationButton.setOnClickListener(v -> {
//            Toast.makeText(this, "Em fase de Desenvolvimento", Toast.LENGTH_SHORT).show();
            areasBundle.putBoolean(CIRCULATION, true);
            callInspectionActivity();
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        areasBundle.putBoolean(EXT_AREA_REG, false);
        areasBundle.putBoolean(SUP_AREA_REG, false);
        areasBundle.putBoolean(CIRCULATION, false);
    }

    private void instantiateBlockViews() {
        saveQuitButton = findViewById(R.id.save_and_quit);
        externalAreaButton = findViewById(R.id.add_external_areas);
        blocksButton = findViewById(R.id.add_blocks);
        supportAreaButton = findViewById(R.id.add_support_area);
        circulationButton = findViewById(R.id.add_circulation);

        modelEntry = new ViewModelEntry(getApplication());
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
