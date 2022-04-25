package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.AdmEquipEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.DeleteInterface;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class AdmEquipRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements DeleteInterface, ViewHolderInterface {

    private ListClickListener listener;
    private final List<AdmEquipEntry> admEquipList;
    private Context context;
    private final OnEntryClickListener entryClickListener;

    public AdmEquipRecViewAdapter(List<AdmEquipEntry> admEquipList, Context context, OnEntryClickListener entryClickListener) {
        this.admEquipList = admEquipList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        AdmEquipEntry admEquip = admEquipList.get(position);
        if (admEquip != null) {
            holder.textInfoOne.setText(admEquip.getAdmEquipLocation());
            holder.textInfoTwo.setText(admEquipNumber(getItemCount() - position));
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

    }

    @Override
    public int getItemCount() {
        return admEquipList.size();
    }

    public String admEquipNumber(int i) {
        return "Equipamento nº" + i;
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
        int listSize = admEquipList.size();
        for (int i = 0; i < listSize; i++) {
            ListViewHolder holder = (ListViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            holder.background.setBackgroundColor(Color.rgb(255, 255, 255));
            notifyItemChanged(i);
        }
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneAdmEquip(admEquipList.get(selectedItems.keyAt(i)).getAdmEquipID());
        }

        for (int i = 0; i < listSize; i++) {
            notifyItemRemoved(selectedItems.keyAt(i));
        }
    }
}
