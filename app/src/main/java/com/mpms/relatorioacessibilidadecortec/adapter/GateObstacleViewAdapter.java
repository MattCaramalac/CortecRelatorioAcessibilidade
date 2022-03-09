package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;

import java.util.List;

public class GateObstacleViewAdapter extends RecyclerView.Adapter<GateObstacleViewAdapter.GateObstacleViewHolder> {

    private List<GateObsEntry> gateObsList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public GateObstacleViewAdapter(List<GateObsEntry> gateObsList, Context context, OnEntryClickListener entryClickListener) {
        this.gateObsList = gateObsList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public GateObstacleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new GateObstacleViewAdapter.GateObstacleViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GateObstacleViewHolder holder, int position) {
        GateObsEntry gateObsEntry = gateObsList.get(position);
        if (gateObsEntry != null) {
            holder.gateObsType.setText(gateObsName(gateObsEntry));
            holder.gateObsNumber.setText(gateObsNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return gateObsList.size();
    }

    private String gateObsName(GateObsEntry gateObsEntry) {
        switch (gateObsEntry.getAccessType()) {
            case 0:
                return "Cancela";
            case 1:
                return "Portão de Acesso";
            default:
                return "";
        }
    }

    private String gateObsNumber(int i) {
        return "Obstáculo nº"+i;
    }

    public static class GateObstacleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView gateObsType;
        public TextView gateObsNumber;

        public GateObstacleViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            gateObsType = itemView.findViewById(R.id.item_info_one);
            gateObsNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
