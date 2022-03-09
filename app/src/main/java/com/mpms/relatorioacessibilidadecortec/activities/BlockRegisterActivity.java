package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.BlockSpaceRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

import static com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity.SCHOOL_ID;

public class BlockRegisterActivity extends AppCompatActivity implements OnEntryClickListener {

    public static final String BLOCK_SPACE_REGISTER = "BLOCK_SPACE_REGISTER";

    private ViewModelEntry modelEntry;
    private ViewModelFragments modelFragments;
    private RecyclerView recyclerView;
    private BlockSpaceRecViewAdapter blockSpaceAdapter;

    Bundle blockRegister = new Bundle();

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_register);
        blockRegister = getIntent().getBundleExtra(SchoolRegisterActivity.SCHOOL_BUNDLE);

        instantiateViews();

        modelFragments.setDataFromActivityToFrag(blockRegister);
    }


    private void instantiateViews() {
//        RecyclerView
        recyclerView = findViewById(R.id.show_block_spaces_registered);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(BlockRegisterActivity.this.getApplication()).create(ViewModelEntry.class);
        modelFragments = new ViewModelProvider(this).get(ViewModelFragments.class);
//        RecyclerView Setting
        modelEntry.getBlockSpaceFromSchool(blockRegister.getInt(SCHOOL_ID)).observe(this, blockSpace -> {
            blockSpaceAdapter = new BlockSpaceRecViewAdapter(blockSpace, BlockRegisterActivity.this, this);
            recyclerView.setAdapter(blockSpaceAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(BlockRegisterActivity.this, R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);
        });
    }

    @Override
    public void OnEntryClick(int position) {
        BlockSpaceEntry blockSpace = modelEntry.allBlockSpaces.getValue().get(position);

        Intent registerComponents = new Intent(BlockRegisterActivity.this, InspectionActivity.class);
        registerComponents.putExtra(BLOCK_SPACE_REGISTER, blockSpace.getBlockSpaceID());
        startActivity(registerComponents);
    }
}