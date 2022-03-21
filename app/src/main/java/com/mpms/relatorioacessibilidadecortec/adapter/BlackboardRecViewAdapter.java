package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;

import java.util.List;

public class BlackboardRecViewAdapter extends RecyclerView.Adapter<BlackboardRecViewAdapter.BlackboardViewHolder> {

    private List<BlackboardEntry> boardList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public BlackboardRecViewAdapter(List<BlackboardEntry> boardList, Context context, OnEntryClickListener entryClickListener) {
        this.boardList = boardList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public BlackboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new BlackboardRecViewAdapter.BlackboardViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BlackboardViewHolder holder, int position) {
       BlackboardEntry entry = boardList.get(position);
        if (entry != null) {
            holder.boardLocation.setText(boardLocale(entry));
            holder.boardNumber.setText(boardNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    private String boardLocale(BlackboardEntry entry) {
        return entry.getBoardLocation();
    }

    private String boardNumber(int i) {
        return "Lousa nº" + i;
    }


    public static class BlackboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView boardLocation;
        public TextView boardNumber;

        public BlackboardViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            boardLocation = itemView.findViewById(R.id.item_info_one);
            boardNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
