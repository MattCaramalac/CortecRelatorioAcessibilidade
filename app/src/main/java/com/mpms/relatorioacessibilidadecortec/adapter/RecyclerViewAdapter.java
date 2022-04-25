package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.DeleteInterface;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.MainViewHolderInterface;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MainViewHolderInterface.MainListViewHolder> implements DeleteInterface, MainViewHolderInterface {

    private ListClickListener listener;
    private List<SchoolEntry> schoolEntryList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public RecyclerViewAdapter(List<SchoolEntry> schoolEntryList, Context context, OnEntryClickListener entryClickListener) {
        this.schoolEntryList = schoolEntryList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }


    @NonNull
    @Override
    public MainViewHolderInterface.MainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entries_layout, parent, false);
        return new MainViewHolderInterface.MainListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolderInterface.MainListViewHolder holder, int position) {
        SchoolEntry schoolEntry = schoolEntryList.get(position);
        holder.textInfoOne.setText(schoolEntry.getSchoolName());
        holder.textInfoTwo.setText(schoolEntry.getNameCity());
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
        return Objects.requireNonNull(schoolEntryList).size();
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneSchool(schoolEntryList.get(selectedItems.keyAt(i)));
        }
    }

    @Override
    public void toggleSelection(MainListViewHolder holder, int position) {
        if (selectedItems.get(position))
            selectedItems.delete(position);
        else
            selectedItems.put(position, true);
        notifyItemChanged(position);
    }

    @Override
    public void cancelSelection(RecyclerView recyclerView) {
        int listSize = schoolEntryList.size();
        for (int i = 0; i < listSize; i++) {
            MainListViewHolder holder = (MainListViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            holder.background.setBackgroundColor(Color.rgb(255, 255, 255));
            notifyItemChanged(i);
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
