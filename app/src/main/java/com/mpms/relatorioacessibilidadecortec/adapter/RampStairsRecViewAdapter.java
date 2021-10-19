package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsEntry;

import java.util.List;

public class RampStairsRecViewAdapter extends RecyclerView.Adapter<RampStairsRecViewAdapter.RampStairsViewHolder> {

    private List<RampStairsEntry> rampStairsList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public RampStairsRecViewAdapter(List<RampStairsEntry> rampStairsList, Context context, OnEntryClickListener entryClickListener) {
        this.rampStairsList = rampStairsList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public RampStairsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new RampStairsRecViewAdapter.RampStairsViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RampStairsViewHolder holder, int position) {
        RampStairsEntry rampStairs = rampStairsList.get(position);
        if (rampStairs != null) {
            holder.rampStairsLocale.setText(rampStairsLocale(rampStairs));
            holder.rampStairsNumber.setText(rampStairsNumber(rampStairs, getItemCount()-position));
        }

    }

    @Override
    public int getItemCount() {
        return rampStairsList.size();
    }

    private String rampStairsLocale(RampStairsEntry rampStairs) {
        return rampStairs.getRampStairsLocation();
    }

    private String rampStairsNumber(RampStairsEntry rampStairs, int i){
        if (rampStairs.getRampStairsIdentifier() == 7)
            return "Escada nº"+i;
         else
             return "Rampa nº"+i;
    }

    public static class RampStairsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView rampStairsLocale;
        public TextView rampStairsNumber;

        public RampStairsViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            rampStairsLocale = itemView.findViewById(R.id.item_info_one);
            rampStairsNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
