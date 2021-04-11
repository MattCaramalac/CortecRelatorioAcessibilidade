package com.mpms.relatorioacessibilidadecortec.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class MainActivity extends AppCompatActivity {

//    private static final int MAIN_ACTIVITY_REQ_CODE = 1;
    private ViewModelEntry viewModelEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       viewModelEntry = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(ViewModelEntry.class);

       viewModelEntry.getAllEntries().observe(this, schoolEntries -> {

       });

//        contactViewModel.getAllContacts().observe(this, contacts -> {
//
//            recyclerViewAdapter = new RecyclerViewAdapter(contacts, MainActivity.this, this);
//            recyclerView.setAdapter(recyclerViewAdapter);
//
//        });

        FloatingActionButton fab = findViewById(R.id.fab_register);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));

//        {
//            startActivityForResult(new Intent(MainActivity.this, RegisterActivity.class), MAIN_ACTIVITY_REQ_CODE);
//                });








    }
}