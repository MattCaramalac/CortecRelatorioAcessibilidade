package com.mpms.relatorioacessibilidadecortec.util;

import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;

import java.util.List;

public interface ViewHolderInterface {

    SparseBooleanArray selectedItems = new SparseBooleanArray();
    int delClick = 0;

    class MainListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public LinearLayout background;
        public TextView textInfoOne;
        public TextView textInfoTwo;

        public MainListViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            background = itemView.findViewById(R.id.main_background);
            textInfoOne = itemView.findViewById(R.id.schoolNameLayout);
            textInfoTwo = itemView.findViewById(R.id.cityNameLayout);
            this.entryClickListener = entryClickListener;
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public LinearLayout background;
        public TextView textInfoOne;
        public TextView textInfoTwo;

        public ListViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            background = itemView.findViewById(R.id.item_holder);
            textInfoOne = itemView.findViewById(R.id.item_info_one);
            textInfoTwo = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }



    default <G extends RecyclerView.ViewHolder> void toggleSelection(RecyclerView.Adapter<G> adapter, int position) {
        if (selectedItems.get(position))
            selectedItems.delete(position);
        else
            selectedItems.put(position, true);
        adapter.notifyItemChanged(position);

    }
    default <T, G extends RecyclerView.ViewHolder> void cancelSelection(RecyclerView recyclerView, List<T> entryList, RecyclerView.Adapter<G> adapter) {
        int listSize = entryList.size();
        for (int i = 0; i < listSize; i++) {
            ViewHolderInterface.MainListViewHolder holder = (ViewHolderInterface.MainListViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            holder.background.setBackgroundColor(Color.rgb(255, 255, 255));
            adapter.notifyItemChanged(i);
        }
    }

    void setListener(ListClickListener listener);
    void deleteItemList();
}
