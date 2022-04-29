package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.DeleteInterface;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class WaterRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements DeleteInterface, ViewHolderInterface {

    private ListClickListener listener;
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
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        WaterFountainEntry waterFountain = waterList.get(position);
        holder.textInfoOne.setText(fountainType(waterFountain.getFountainType()));
        holder.textInfoTwo.setText(fountainNumber(getItemCount()-position));
        if (selectedItems.get(position))
            holder.background.setBackgroundColor(Color.rgb(158, 235, 247));
        else
            holder.background.setBackgroundColor(Color.rgb(255, 255, 255));

        holder.itemView.setOnClickListener(v -> {
            if (selectedItems.size() > 0) {
                toggleSelection(holder, position);
            }
            listener.onItemClick(position);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                toggleSelection(holder, position);
                listener.onItemLongClick(position);
            }
            return true;
        });
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
        return "Bebedouro nº" +i;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneWaterFountain(waterList.get(selectedItems.keyAt(i)).getWaterFountainID());
        }
    }

    @Override
    public void toggleSelection(ListViewHolder holder, int position) {
        if (selectedItems.get(position))
            selectedItems.delete(position);
        else
            selectedItems.put(position, true);
        notifyItemChanged(position);
    }

    @Override
    public void cancelSelection(RecyclerView recyclerView) {
        int listSize = waterList.size();
        for (int i = 0; i < listSize; i++) {
            ListViewHolder holder = (ListViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            holder.background.setBackgroundColor(Color.rgb(255, 255, 255));
            notifyItemChanged(i);
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
