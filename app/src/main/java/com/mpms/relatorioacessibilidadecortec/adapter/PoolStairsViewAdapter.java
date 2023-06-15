package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PoolStairsViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
    private List<PoolStairsEntry> poolStairsList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public PoolStairsViewAdapter(List<PoolStairsEntry> poolStairsList, Context context, OnEntryClickListener entryClickListener) {
        this.poolStairsList = poolStairsList;
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
        PoolStairsEntry stairs = poolStairsList.get(position);
        if (stairs != null) {
            holder.textInfoOne.setText(poolStairsLocation(stairs));
            holder.textInfoTwo.setText(poolStairsNumber(getItemCount()-position));
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

    public String poolStairsLocation(PoolStairsEntry stairs) {
        return stairs.getStairsLocation();
    }

    public String poolStairsNumber (int i) {
        return "Escada nº" + i;
    }

    @Override
    public int getItemCount() {
        return poolStairsList.size();
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deletePoolStairs(poolStairsList.get(selectedItems.keyAt(i)).getPoolID());
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
