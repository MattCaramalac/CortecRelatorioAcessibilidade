package com.mpms.relatorioacessibilidadecortec.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class BlockRegisterFragment extends Fragment implements TagInterface {

    MaterialButton newBlock, saveQuit;

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;

    Bundle blockBundle = new Bundle();

    int blockClick = 0;

    public BlockRegisterFragment() {
        // Required empty public constructor
    }

    public static BlockRegisterFragment newInstance() {
        return new BlockRegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        modelFragments.getDataFromActivityToFrag().observe(getViewLifecycleOwner(), bundle -> blockBundle.putInt(SCHOOL_ID, bundle.getInt(SCHOOL_ID)));

        newBlock.setOnClickListener(v -> {
            blockClick++;
            blockBundle.putInt(BLOCK_OR_SPACE, 0);
            modelEntry.getLastBlockSpace(blockBundle.getInt(SCHOOL_ID), 0).observe(getViewLifecycleOwner(), block -> {
                if (block == null && blockClick > 0) {
                    blockBundle.putInt(NEXT_ENTRY, 1);
                    BlockSpaceEntry newEntry = newBlock(blockBundle);
                    ViewModelEntry.insertBlockSpace(newEntry);
                    blockClick--;
                } else if (block != null && blockClick > 0) {
                    blockBundle.putInt(NEXT_ENTRY, block.getBlockSpaceNumber() + 1);
                    BlockSpaceEntry newEntry = newBlock(blockBundle);
                    ViewModelEntry.insertBlockSpace(newEntry);
                    blockClick--;
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
        saveQuit = view.findViewById(R.id.save_quit_block_register);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
    }

    private BlockSpaceEntry newBlock(Bundle bundle) {
        return new BlockSpaceEntry(bundle.getInt(SCHOOL_ID), bundle.getInt(BLOCK_OR_SPACE), bundle.getInt(NEXT_ENTRY));
    }

}