package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;

import java.util.List;

public class ParkingRecViewAdapter extends RecyclerView.Adapter<ParkingRecViewAdapter.ParkingViewHolder> {

    private List<ParkingLotEntry> parkingList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public ParkingRecViewAdapter(List<ParkingLotEntry> parkingList, Context context, OnEntryClickListener entryClickListener) {
        this.parkingList = parkingList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ParkingRecViewAdapter.ParkingViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        ParkingLotEntry parkingEntry = parkingList.get(position);
        if (parkingList.get(position) != null) {
            holder.parkingLotType.setText(lotType(parkingEntry.getTypeParkingLot()));
            holder.parkingLotNumber.setText(lotNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return parkingList.size();
    }

    private String lotType(int i) {
        switch (i) {
            case 0:
                return "Estacionamento Interno";
            case 1:
                return "Estacionamento Externo";
            default:
                return "";
        }
    }

    private String lotNumber(int i) {
        return "Estacionamento nº"+i;
    }

    public static class ParkingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView parkingLotType;
        public TextView parkingLotNumber;

        public ParkingViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            parkingLotType = itemView.findViewById(R.id.item_info_one);
            parkingLotNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
