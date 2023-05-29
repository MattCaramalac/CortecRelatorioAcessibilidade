package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class SidewalkSlopeRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
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
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        SidewalkSlopeEntry slopeEntry = slopeList.get(position);
        holder.textInfoOne.setText(slopeEntry.getSlopeLocation());
        holder.textInfoTwo.setText(slopeNumber(getItemCount()-position));
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

    @Override
    public int getItemCount() {
        return slopeList.size();
    }

    private String slopeNumber(int i) {
        return "Rebaixamento nº"+i;

    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteSidewalkSlope(slopeList.get(selectedItems.keyAt(i)).getSideSlopeID());
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
