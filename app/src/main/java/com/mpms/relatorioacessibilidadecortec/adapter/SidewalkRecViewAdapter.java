package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class SidewalkRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
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
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        SidewalkEntry walkEntry = walkList.get(position);
        if (walkEntry != null) {
            holder.textInfoOne.setText(sidewalkLocation(walkEntry));
            holder.textInfoTwo.setText(sidewalkNumber(getItemCount()-position));
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
        return walkList.size();
    }

    private String sidewalkLocation(SidewalkEntry entry) {
        return "Calçada " + entry.getSidewalkLocation();
    }

    private String sidewalkNumber(int i) {
        return "Calçada nº" + i;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteSidewalk(walkList.get(selectedItems.keyAt(i)).getSidewalkID());
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
