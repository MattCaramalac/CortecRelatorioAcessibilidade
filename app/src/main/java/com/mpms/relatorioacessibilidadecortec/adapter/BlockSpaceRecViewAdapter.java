package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.BlockSpaceEntry;

import java.util.List;

public class BlockSpaceRecViewAdapter extends RecyclerView.Adapter<BlockSpaceRecViewAdapter.BlockSpaceViewHolder>{

    private List<BlockSpaceEntry> blockSpaceList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public BlockSpaceRecViewAdapter(List<BlockSpaceEntry> blockSpaceList, Context context, OnEntryClickListener entryClickListener) {
        this.blockSpaceList = blockSpaceList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public BlockSpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new BlockSpaceRecViewAdapter.BlockSpaceViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockSpaceViewHolder holder, int position) {
        BlockSpaceEntry blockSpace = blockSpaceList.get(position);
        if (blockSpace != null) {
            holder.blockSpaceLocale.setText(blockSpaceLocale(blockSpace));
        }
    }

    @Override
    public int getItemCount() {
        return blockSpaceList.size();
    }

    private String blockSpaceLocale (BlockSpaceEntry blockSpace) {
        if (blockSpace.getBlockSpaceType() == 0)
            return "Bloco nº" + blockSpace.getBlockSpaceNumber();
        else
            return "Espaço de Apoio nº" + blockSpace.getBlockSpaceNumber();
    }

    public static class BlockSpaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView blockSpaceLocale;
        public TextView blockSpaceNumber;

        public BlockSpaceViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            blockSpaceLocale = itemView.findViewById(R.id.item_info_one);
            blockSpaceNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { entryClickListener.OnEntryClick(getAdapterPosition()); }
    }
}
