package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RecyclerViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnEntryClickListener {

    public static final String UPDATE_REQUEST = "UPDATE_REQUEST";
    private ViewModelEntry viewModelEntry;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<SchoolEntry> schoolEntryList;

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
        });

        FloatingActionButton fab = findViewById(R.id.fab_register);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SchoolRegisterActivity.class)));
    }

    @Override
    public void OnEntryClick(int position) {
        SchoolEntry schoolEntry = viewModelEntry.getAllEntries().getValue().get(position);

        Intent updateIntent = new Intent(MainActivity.this, SchoolRegisterActivity.class);
        updateIntent.putExtra(UPDATE_REQUEST, schoolEntry.getCadID());
        startActivity(updateIntent);
    }
}