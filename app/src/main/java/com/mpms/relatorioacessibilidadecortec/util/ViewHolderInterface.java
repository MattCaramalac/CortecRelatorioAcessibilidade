package com.mpms.relatorioacessibilidadecortec.util;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;

public interface ViewHolderInterface {

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

    void toggleSelection(ListViewHolder holder, int position);
    void cancelSelection(RecyclerView recyclerView);
}
