package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;

import java.util.List;

public class TableRecViewAdapter extends RecyclerView.Adapter<TableRecViewAdapter.TableViewHolder> {

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
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new TableRecViewAdapter.TableViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        TableEntry tableEntry = tableList.get(position);
        if (tableEntry != null) {
            holder.tableType.setText(tableTypeText(tableEntry, getItemCount() - position));
            holder.tableNumber.setText(tableNumber(tableEntry, getItemCount() - position));
        }
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public String tableTypeText(TableEntry table, int i) {
        if (table.getRoomType() == 11) {
            if (table.getTableType() == 0)
                return "Mesa do Aluno nº" + i;
            else
                return "Mesa do Professor nº" + i;
        } else {
            return "Cadastro de Mesa nº" + i;
        }
    }

    public String tableNumber(TableEntry tableEntry, int i) {
        if (tableEntry.getRoomType() == 11)
            return "Mesa nº" + i;
        else
            return "";
    }


    public static class TableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView tableType;
        public TextView tableNumber;

        public TableViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            tableType = itemView.findViewById(R.id.item_info_one);
            tableNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
