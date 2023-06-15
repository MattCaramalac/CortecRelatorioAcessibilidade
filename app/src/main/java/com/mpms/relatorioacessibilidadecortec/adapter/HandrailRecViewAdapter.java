package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class HandrailRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
    private final List<RampStairsHandrailEntry> handrailList;
    private Context context;
    private final OnEntryClickListener entryClickListener;

    public HandrailRecViewAdapter(List<RampStairsHandrailEntry> handrailList, Context context, OnEntryClickListener entryClickListener) {
        this.handrailList = handrailList;
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
        RampStairsHandrailEntry entry = handrailList.get(position);
        if (entry != null) {
            holder.textInfoOne.setText(handPosition(entry));
            holder.textInfoTwo.setText(handrailType(entry));
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

    private String handPosition(RampStairsHandrailEntry entry) {
        switch (entry.getHandrailPlacement()) {
            case 0:
                return "Corrimão Superior Esquerdo";
            case 1:
                return "Corrimão Superior Direito";
            case 2:
                return "Corrimão Inferior Esquerdo";
            case 3:
                return "Corrimão Inferior Direito";
            default:
                return "";
        }
    }

    private String handrailType(RampStairsHandrailEntry entry) {
        return "Corrimão Lateral";
    }

    @Override
    public int getItemCount() {
        return handrailList.size();
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneRampStairsHandrail(handrailList.get(selectedItems.keyAt(i)).getHandrailID());
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
