package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;


public class StairsFlightFragment extends Fragment {

    Button cancelFlight;


    public StairsFlightFragment() {
        // Required empty public constructor
    }

    public static StairsFlightFragment newInstance() {
        return new StairsFlightFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stairs_flight, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancelFlight = view.findViewById(R.id.cancel_flight);

//        Comando faz retornar a tela de cadastro da escadaria
//        TODO - Inserir dialog de perda de dados cadastrados ao tentar retornar para a tela inicial de cadastro
        cancelFlight.setOnClickListener(v-> Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStackImmediate());

    }
}