package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;

import java.util.List;

public class SidewalkRecViewAdapter extends RecyclerView.Adapter<SidewalkRecViewAdapter.WalkViewHolder> {

    private List<SidewalkEntry> walkList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public SidewalkRecViewAdapter(List<SidewalkEntry> walkList, Context context, OnEntryClickListener entryClickListener) {
        this.walkList = walkList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public WalkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new SidewalkRecViewAdapter.WalkViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WalkViewHolder holder, int position) {
        SidewalkEntry walkEntry = walkList.get(position);
        holder.walkLocation.setText(sidewalkLocation(walkEntry));
        holder.walkNumber.setText(sidewalkNumber(getItemCount()-position));
    }

    @Override
    public int getItemCount() {
        return walkList.size();
    }

    private String sidewalkLocation(SidewalkEntry entry) {
        return "Calçada " + entry.getSidewalkLocation();
    }

    private String sidewalkNumber(int i) {
        return "Calçada nº" + i;
    }

    public static class WalkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView walkLocation;
        public TextView walkNumber;

        public WalkViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);

            walkLocation = itemView.findViewById(R.id.item_info_one);
            walkNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
