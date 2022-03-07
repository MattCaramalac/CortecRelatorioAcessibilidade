package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.PlayRecViewAdapter;
import com.mpms.relatorioacessibilidadecortec.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class PlaygroundListFragment extends Fragment implements OnEntryClickListener {

    public static final String NEW_PLAYGROUND_ENTRY = "NEW_PLAYGROUND_ENTRY";

    MaterialButton closePlayList, addPlayground, continuePlayground;

    TextView registerHeader;

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private PlayRecViewAdapter playAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Bundle playBundle = new Bundle();

    public PlaygroundListFragment() {
        // Required empty public constructor
    }

    public static PlaygroundListFragment newInstance() {
        return new PlaygroundListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            playBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_items_entries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateAdmEquipListViews(view);

        modelEntry.getAllPlaygroundsPerBlock(playBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER))
                .observe(getViewLifecycleOwner(), playList -> {
                    playAdapter = new PlayRecViewAdapter(playList, requireActivity(), this);
                    recyclerView.setAdapter(playAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                    dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity(), R.drawable.abc_list_divider_material)));
                    recyclerView.addItemDecoration(dividerItemDecoration);
                });

        addPlayground.setOnClickListener(v -> openPlayFragment());

        closePlayList.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    @Override
    public void OnEntryClick(int position) {
        PlaygroundEntry playEntry =  modelEntry.allPlaygrounds.getValue().get(position);
        playBundle.putInt(PlaygroundFragment.PLAY_ID, playEntry.getPlaygroundID());
        openPlayFragment();
    }

    private void openPlayFragment() {
        PlaygroundFragment playFragment = PlaygroundFragment.newInstance();
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (playBundle.getInt(PlaygroundFragment.PLAY_ID) > 0) {
            playFragment.setArguments(playBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, playFragment).addToBackStack(null).commit();
        }
        else {
            playBundle.putInt(PlaygroundFragment.PLAY_ID, 0);
            playFragment.setArguments(playBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, playFragment).addToBackStack(NEW_PLAYGROUND_ENTRY).commit();
        }
    }

    private void instantiateAdmEquipListViews(View v) {
//        MaterialButton
        closePlayList = v.findViewById(R.id.cancel_child_items_entries);
        addPlayground = v.findViewById(R.id.add_child_items_entries);
        continuePlayground = v.findViewById(R.id.continue_child_items_entries);
        continuePlayground.setVisibility(View.GONE);
//        RecyclerView
        recyclerView = v.findViewById(R.id.child_items_entries_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        TextView
        registerHeader = v.findViewById(R.id.identifier_header);
        registerHeader.setText(R.string.header_playground_register);
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
    }
}
