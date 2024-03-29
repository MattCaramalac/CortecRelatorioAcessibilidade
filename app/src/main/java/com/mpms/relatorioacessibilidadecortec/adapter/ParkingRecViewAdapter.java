package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class ParkingRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
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
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ParkingLotEntry parkingEntry = parkingList.get(position);
        if (parkingList.get(position) != null) {
            holder.textInfoOne.setText(lotType(parkingEntry.getTypeParkingLot()));
            holder.textInfoTwo.setText(lotNumber(getItemCount()-position));
            if (selectedItems.get(position))
                holder.background.setBackgroundColor(Color.rgb(158, 235, 247));
            else
                holder.background.setBackgroundColor(Color.rgb(255, 255, 255));

            holder.itemView.setOnClickListener(v -> {
                if (selectedItems.size() > 0) {
                    toggleSelection(this, position);
                }
                listener.onItemClick(position);
            });

            holder.itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    toggleSelection(this, position);
                    listener.onItemLongClick(position);
                }
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return parkingList.size();
    }

    private String lotType(int i) {
        switch (i) {
            case 1:
                return "Estacionamento Externo";
            case 2:
                return "Estacionamento Interno";
            case 3:
                return "Estacionamento na Calçada";
            default:
                return "";
        }
    }

    private String lotNumber(int i) {
        return "Estacionamento nº"+i;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneParkingLot(parkingList.get(selectedItems.keyAt(i)).getParkingID());
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
