package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;

import java.util.List;

public class FreeSpaceRecViewAdapter extends RecyclerView.Adapter<FreeSpaceRecViewAdapter.FreeSpaceViewHolder> {

    private List<FreeSpaceEntry> freeList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public FreeSpaceRecViewAdapter(List<FreeSpaceEntry> freeList, Context context, OnEntryClickListener entryClickListener) {
        this.freeList = freeList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public FreeSpaceRecViewAdapter.FreeSpaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new FreeSpaceRecViewAdapter.FreeSpaceViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FreeSpaceRecViewAdapter.FreeSpaceViewHolder holder, int position) {
        FreeSpaceEntry entry = freeList.get(position);
        if (entry != null) {
            holder.fSpaceLocation.setText(fSpaceLocale(entry));
            holder.fSpaceNumber.setText(fSpaceNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return freeList.size();
    }

    private String fSpaceLocale(FreeSpaceEntry entry) {
        return entry.getFreeSpaceLocation();
    }

    private String fSpaceNumber(int i) {
        return "Faixa Livre nº" + i;
    }


    public static class FreeSpaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView fSpaceLocation;
        public TextView fSpaceNumber;

        public FreeSpaceViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            fSpaceLocation = itemView.findViewById(R.id.item_info_one);
            fSpaceNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
