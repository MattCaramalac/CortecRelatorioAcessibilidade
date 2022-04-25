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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RecyclerViewAdapter;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnEntryClickListener {

    public static final String UPDATE_REQUEST = "UPDATE_REQUEST";
    private ViewModelEntry viewModelEntry;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ActionMode actionMode;

    int delClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewEntries);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModelEntry = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(ViewModelEntry.class);

        viewModelEntry.getAllEntries().observe(this, schoolEntries -> {

            recyclerViewAdapter = new RecyclerViewAdapter(schoolEntries,MainActivity.this, this);
            recyclerView.setAdapter(recyclerViewAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(MainActivity.this, R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            recyclerViewAdapter.setListener(new ListClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (actionMode == null)
                        OnEntryClick(position);
                    else
                        enableActionMode();
                }

                @Override
                public void onItemLongClick(int position) {
                    enableActionMode();
                }
            });
        });

        FloatingActionButton fab = findViewById(R.id.fab_register);
        fab.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            startActivity(new Intent(MainActivity.this, SchoolRegisterActivity.class));
        });
    }

    private void enableActionMode() {
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
                        recyclerViewAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        recyclerViewAdapter.cancelSelection(recyclerView);
                    recyclerViewAdapter.selectedItems.clear();
                    recyclerViewAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = recyclerViewAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        SchoolEntry schoolEntry = viewModelEntry.getAllEntries().getValue().get(position);
        Intent updateIntent = new Intent(MainActivity.this, SchoolRegisterActivity.class);
        updateIntent.putExtra(UPDATE_REQUEST, schoolEntry.getCadID());
        if (actionMode != null)
            actionMode.finish();
        startActivity(updateIntent);
    }
}