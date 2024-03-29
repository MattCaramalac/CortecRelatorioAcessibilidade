package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class RestroomRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
    private List<RestroomEntry> restroomList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public RestroomRecViewAdapter(List<RestroomEntry> restroomList, Context context, OnEntryClickListener entryClickListener) {
        this.restroomList = restroomList;
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
        RestroomEntry restroomEntry = restroomList.get(position);
        if (restroomList.get(position) != null) {
            holder.textInfoOne.setText(restLocationType(restroomEntry));
            holder.textInfoTwo.setText(restNumber(getItemCount()-position));
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
        return restroomList.size();
    }

    public String restLocationType(RestroomEntry restroom) {
        StringBuilder restTyping = new StringBuilder();
        String restType = "";
        switch (restroom.getRestType()) {
            case 0:
                restTyping.append("Acessível Independente ");
                break;
            case 1:
                restTyping.append("Coletivo Acessível ");
                break;
            case 2:
                restTyping.append("Coletivo Não Acessível ");
                break;
            case 3:
                restTyping.append("Infantil ");
                break;
        }
        if (restroom.getRestGender() != null) {
            switch (restroom.getRestGender()) {
                case 0:
                    restTyping.append("Masculino");
                    break;
                case 1:
                    restTyping.append("Feminino");
                    break;
                case 2:
                    restTyping.append("Unissex");
                    break;
                default:
                    break;
            }
        }
        return restTyping.toString() + " - " + restroom.getRestLocation() ;
    }

    public String restNumber(int i) {
        return "Sanitário nº" +i;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneRestroomEntry(restroomList.get(selectedItems.keyAt(i)).getRestroomID());
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
