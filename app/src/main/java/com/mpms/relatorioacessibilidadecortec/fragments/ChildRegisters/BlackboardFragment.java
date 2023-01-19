package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class BlackboardFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout boardLocationField, boardInfHeightField, boardObsField;
    TextInputEditText boardLocationValue, boardInfHeightValue, boardObsValue;
    MaterialButton saveBoard, cancelBoard;

    Bundle boardBundle;

    ViewModelEntry modelEntry;


    public BlackboardFragment() {
        // Required empty public constructor
    }

    public static BlackboardFragment newInstance() {
        return new BlackboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            boardBundle = new Bundle(this.getArguments());
        else
            boardBundle = new Bundle();
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blackboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateBoardViews(view);

        if (boardBundle.getInt(BOARD_ID) > 0)
            modelEntry.getOneBlackboard(boardBundle.getInt(BOARD_ID)).observe(getViewLifecycleOwner(), this::loadBoardData);

        saveBoard.setOnClickListener(v -> {
            if (boardNoEmptyFields()) {
                BlackboardEntry newBoard = newBoard(boardBundle);
                if (boardBundle.getInt(BOARD_ID) > 0) {
                    newBoard.setBoardID(boardBundle.getInt(BOARD_ID));
                    ViewModelEntry.updateBlackboard(newBoard);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (boardBundle.getInt(BOARD_ID) == 0) {
                    ViewModelEntry.insertBlackboard(newBoard);
                    clearBoardFields();
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else {
                    boardBundle.putInt(BOARD_ID,0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBoard.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateBoardViews(View view) {
//        TextInputLayout
        boardLocationField = view.findViewById(R.id.board_location_field);
        boardInfHeightField = view.findViewById(R.id.board_inf_height_field);
        boardObsField = view.findViewById(R.id.blackboard_obs_field);
//        TextInputEditText
        boardLocationValue = view.findViewById(R.id.board_location_value);
        boardInfHeightValue = view.findViewById(R.id.board_inf_height_value);
        boardObsValue = view.findViewById(R.id.blackboard_obs_value);
//        MaterialButton
        saveBoard = view.findViewById(R.id.save_board);
        cancelBoard = view.findViewById(R.id.cancel_board);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        allowObsScroll(boardObsValue);
    }

    private void loadBoardData(BlackboardEntry entry) {
        boardLocationValue.setText(entry.getBoardLocation());
        boardInfHeightValue.setText(String.valueOf(entry.getInfBorderHeight()));
        if (entry.getBoardObs() != null)
            boardObsValue.setText(entry.getBoardObs());
    }

    private boolean boardNoEmptyFields() {
        clearBoardEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(boardLocationValue.getText())) {
            i++;
            boardLocationField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(boardInfHeightValue.getText())) {
            i++;
            boardInfHeightField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }

    private void clearBoardEmptyFieldsErrors() {
        boardLocationField.setErrorEnabled(false);
        boardInfHeightField.setErrorEnabled(false);
    }

    private void clearBoardFields() {
        boardLocationValue.setText(null);
        boardInfHeightValue.setText(null);
        boardObsValue.setText(null);
    }

    private BlackboardEntry newBoard(Bundle bundle) {
        String boardLocale, boardObs = null;
        double boardInfHeight;

        boardLocale = String.valueOf(boardLocationValue.getText());
        boardInfHeight = Double.parseDouble(String.valueOf(boardInfHeightValue.getText()));
        if (!TextUtils.isEmpty(boardObsValue.getText()))
            boardObs = String.valueOf(boardObsValue.getText());

        return new BlackboardEntry(bundle.getInt(AMBIENT_ID), boardLocale, boardInfHeight, boardObs);
    }
}