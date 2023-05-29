package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class TableRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
    private List<TableEntry> tableList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public TableRecViewAdapter(List<TableEntry> tableList, Context context, OnEntryClickListener entryClickListener) {
        this.tableList = tableList;
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
        TableEntry tableEntry = tableList.get(position);
        if (tableEntry != null) {
            holder.textInfoOne.setText(tableTypeText(tableEntry, getItemCount() - position));
            holder.textInfoTwo.setText(tableNumber(tableEntry, getItemCount() - position));
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
        return tableList.size();
    }

    public String tableTypeText(TableEntry table, int i) {
        String tableType = null;
        if (table.getTableDesc() != null)
            tableType = table.getTableDesc();
        if (table.getRoomType() == 6) {
            if (table.getTableType() == 1 && tableType != null)
                return "Mesa do Aluno nº" + i + " - " + tableType;
            else if (table.getTableType() == 1)
                return "Mesa do Aluno nº" + i;
            else if (table.getTableType() == 0 && tableType != null)
                return "Mesa do Professor nº" + i + " - " + tableType;
            else
                return "Mesa do Professor nº" + i;
        } else {
            if (tableType != null)
                return "Cadastro de Mesa nº" + i + " - " + tableType;
            else
                return "Cadastro de Mesa nº" + i;
        }
    }

    public String tableNumber(TableEntry tableEntry, int i) {
        if (tableEntry.getRoomType() == 6)
            return "Mesa nº" + i;
        else
            return "";
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteTable(tableList.get(selectedItems.keyAt(i)).getTableID());
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
