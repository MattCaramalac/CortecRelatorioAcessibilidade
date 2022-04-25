package com.mpms.relatorioacessibilidadecortec.util;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;

public interface MainViewHolderInterface {

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

    void toggleSelection(MainListViewHolder holder, int position);
    void cancelSelection(RecyclerView recyclerView);
    void setListener(ListClickListener listener);
}
