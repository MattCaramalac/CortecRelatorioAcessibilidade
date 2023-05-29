package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlopeRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
    private List<SlopeEntry> slopeList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public SlopeRecViewAdapter(List<SlopeEntry> slopeList, Context context, OnEntryClickListener entryClickListener) {
        this.slopeList = slopeList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListViewHolder holder, int position) {
        SlopeEntry slope = slopeList.get(position);
        if (slope != null) {
            holder.textInfoOne.setText(slopeLocation(slope));
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
    }

    @Override
    public int getItemCount() {
        return slopeList.size();
    }

    public String slopeLocation(SlopeEntry slope) {
        return slope.getSlopeLocation();
    }

    public String slopeNumber(int i) {
        return "Desnível nº" + i;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++)
            ViewModelEntry.deleteSoleStep(slopeList.get(selectedItems.keyAt(i)).getSlopeID());
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
