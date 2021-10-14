package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;

import java.util.List;

public class WaterRecViewAdapter extends RecyclerView.Adapter<WaterRecViewAdapter.WaterViewHolder> {

    private List<WaterFountainEntry> waterList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public WaterRecViewAdapter(List<WaterFountainEntry> waterList, Context context, OnEntryClickListener entryClickListener) {
        this.waterList = waterList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public WaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new WaterRecViewAdapter.WaterViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterViewHolder holder, int position) {
        WaterFountainEntry waterFountain = waterList.get(position);
        holder.waterType.setText(fountainType(waterFountain.getTypeWaterFountain()));
        holder.waterNumber.setText(fountainNumber(position));
    }

    @Override
    public int getItemCount() {
        return waterList.size();
    }

    private String fountainType(int i) {
        switch (i) {
            case 0:
                return "Bebedouro - Tipo Bica";
            case 1:
                return "Bebedouro - Outro Tipo";
            default:
                return "";
        }
    }

    private String fountainNumber(int i) {
        return "Bebedouro nº" + (i + 1);
    }

    public static class WaterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView waterType;
        public TextView waterNumber;

        public WaterViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            waterType = itemView.findViewById(R.id.item_info_one);
            waterNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
