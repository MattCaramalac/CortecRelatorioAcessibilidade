package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
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
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.List;
import java.util.Objects;

public class BlockRegisterActivity extends AppCompatActivity implements OnEntryClickListener, TagInterface {

    private ViewModelEntry modelEntry;
    private ViewModelFragments modelFragments;
    private RecyclerView recyclerView;
    private BlockSpaceRecViewAdapter blockSpaceAdapter;
    private ActionMode actionMode;

    private int delClick = 0;

    Bundle blockRegister = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_register);
        blockRegister = getIntent().getBundleExtra(AREAS_REG_BUNDLE);

        instantiateViews();
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
            int size = blockSpace.size();
            blockRegister.putInt(BLOCK_NUMBER, size);
            modelFragments.setDataFromActivityToFrag(blockRegister);

            blockSpaceAdapter = new BlockSpaceRecViewAdapter(blockSpace, BlockRegisterActivity.this, this);
            recyclerView.setAdapter(blockSpaceAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(BlockRegisterActivity.this, R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            blockSpaceAdapter.setListener(new ListClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (actionMode == null)
                        OnEntryClick(position);
                    else
                        enableActionMode(blockSpace);
                }

                @Override
                public void onItemLongClick(int position) {
                    enableActionMode(blockSpace);
                }
            });
        });
    }

    private <T> void enableActionMode(List<T> entries) {
        if (actionMode == null) {
            AppCompatActivity activity = this;
            actionMode = activity.startSupportActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return true;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.delete_button) {
                        delClick = 1;
                        blockSpaceAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        blockSpaceAdapter.cancelSelection(recyclerView, entries, blockSpaceAdapter);
                    blockSpaceAdapter.selectedItems.clear();
                    blockSpaceAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = blockSpaceAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        BlockSpaceEntry blockSpace = modelEntry.allBlockSpaces.getValue().get(position);

        Intent registerComponents = new Intent(BlockRegisterActivity.this, InspectionActivity.class);
        blockRegister.putInt(BLOCK_ID, blockSpace.getBlockSpaceID());
        registerComponents.putExtra(SchoolAreasRegisterActivity.AREAS_REG_BUNDLE, blockRegister);
        startActivity(registerComponents);
    }
}