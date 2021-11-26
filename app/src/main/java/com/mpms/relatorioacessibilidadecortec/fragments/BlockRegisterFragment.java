package com.mpms.relatorioacessibilidadecortec.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class BlockRegisterFragment extends Fragment {

    public static final String BLOCK_OR_SPACE = "BLOCK_OR_SPACE";
    public static final String NEXT_ENTRY = "NEXT_ENTRY";

    MaterialButton newBlock, newSpace, saveQuit;
    ViewModelEntry modelEntry;

    Bundle blockBundle = new Bundle();

    public BlockRegisterFragment() {
        // Required empty public constructor
    }

    public static BlockRegisterFragment newInstance() {
        return new BlockRegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            blockBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_block_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateBlockRegisterViews(view);

        newBlock.setOnClickListener(v -> {
            blockBundle.putInt(BLOCK_OR_SPACE, 0);
            modelEntry.getLastBlockSpace(0).observe(getViewLifecycleOwner(), block -> {
                if (block == null) {
                    blockBundle.putInt(NEXT_ENTRY, 1);
                    BlockSpaceEntry newEntry = newBlock(blockBundle);
                    ViewModelEntry.insertBlockSpace(newEntry);
                } else {
                    blockBundle.putInt(NEXT_ENTRY, block.getBlockSpaceNumber() + 1);
                    BlockSpaceEntry newEntry = newBlock(blockBundle);
                    ViewModelEntry.insertBlockSpace(newEntry);
                }
            });
        });

        newSpace.setOnClickListener(v -> {
            blockBundle.putInt(BLOCK_OR_SPACE, 1);
            modelEntry.getLastBlockSpace(1).observe(getViewLifecycleOwner(), block -> {
                if (block == null) {
                    blockBundle.putInt(NEXT_ENTRY, 1);
                    BlockSpaceEntry newEntry = newBlock(blockBundle);
                    ViewModelEntry.insertBlockSpace(newEntry);
                } else {
                    blockBundle.putInt(NEXT_ENTRY, block.getBlockSpaceNumber() + 1);
                    BlockSpaceEntry newEntry = newBlock(blockBundle);
                    ViewModelEntry.insertBlockSpace(newEntry);
                }
            });
        });

        saveQuit.setOnClickListener(v -> {
            blockBundle = null;
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    private void instantiateBlockRegisterViews(View view) {
//        MaterialButtons
        newBlock = view.findViewById(R.id.add_new_block_button);
        newSpace = view.findViewById(R.id.add_new_space_button);
        saveQuit = view.findViewById(R.id.save_quit_block_register);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private BlockSpaceEntry newBlock(Bundle bundle) {
        return new BlockSpaceEntry(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID), bundle.getInt(BLOCK_OR_SPACE), bundle.getInt(NEXT_ENTRY));
    }

}