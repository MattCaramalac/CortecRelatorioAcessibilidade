package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class ExtAccRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private List<ExternalAccess> extAccessList;
    private Context context;
    private OnEntryClickListener entryClickListener;
    private ListClickListener listener;

    public ExtAccRecViewAdapter(List<ExternalAccess> extAccessList, Context context, OnEntryClickListener entryClickListener) {
        this.extAccessList = extAccessList;
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
        ExternalAccess extAccess = extAccessList.get(position);
        if (extAccess.getEntranceType() != null) {
            holder.textInfoOne.setText(entranceType(extAccess));
            holder.textInfoTwo.setText(extAccessNumber(getItemCount()-position));
            if(selectedItems.get(position))
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
        return extAccessList.size();
    }

    public String entranceType(ExternalAccess ext) {
        if (ext.getEntranceType() == 0)
            return "Entrada Social - " + ext.getAccessLocation();
        else
            return "Entrada de Veículos - " + ext.getAccessLocation();
    }

    public String extAccessNumber(int i) {
        return "Entrada Externa nº "+i;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneExternalAccess(extAccessList.get(selectedItems.keyAt(i)).getExternalAccessID());
        }

        for (int i = 0; i < listSize; i++) {
            notifyItemRemoved(selectedItems.keyAt(i));
        }
    }

}


