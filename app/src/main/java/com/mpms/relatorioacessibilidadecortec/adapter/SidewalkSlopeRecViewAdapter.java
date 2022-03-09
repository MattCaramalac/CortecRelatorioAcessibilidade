package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;

import java.util.List;

public class SidewalkSlopeRecViewAdapter extends RecyclerView.Adapter<SidewalkSlopeRecViewAdapter.SideSlopeViewHolder> {

    private List<SidewalkSlopeEntry> slopeList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public SidewalkSlopeRecViewAdapter(List<SidewalkSlopeEntry> slopeList, Context context, OnEntryClickListener entryClickListener) {
        this.slopeList = slopeList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public SideSlopeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new SidewalkSlopeRecViewAdapter.SideSlopeViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SideSlopeViewHolder holder, int position) {
        SidewalkSlopeEntry slopeEntry = slopeList.get(position);
        holder.slopeLocation.setText(slopeEntry.getSlopeLocation());
        holder.slopeNumber.setText(slopeNumber(getItemCount()-position));
    }

    @Override
    public int getItemCount() {
        return slopeList.size();
    }

    private String slopeNumber(int i) {
        return "Rebaixamento nº"+i;

    }

    public static class SideSlopeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView slopeLocation;
        public TextView slopeNumber;

        public SideSlopeViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            this.slopeLocation = itemView.findViewById(R.id.item_info_one);
            this.slopeNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
