package com.mpms.relatorioacessibilidadecortec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class MainActivity extends AppCompatActivity {

    private ViewModelEntry viewModelEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       viewModelEntry = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(ViewModelEntry.class);

        FloatingActionButton fab = findViewById(R.id.fab_register);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));


    }
}