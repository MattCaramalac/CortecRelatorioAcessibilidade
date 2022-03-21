package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;

import java.util.List;

public class DoorLockRecViewAdapter extends RecyclerView.Adapter<DoorLockRecViewAdapter.DoorLockViewHolder> {

    private List<DoorLockEntry> lockList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public DoorLockRecViewAdapter(List<DoorLockEntry> lockList, Context context, OnEntryClickListener entryClickListener) {
        this.lockList = lockList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public DoorLockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new DoorLockRecViewAdapter.DoorLockViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DoorLockViewHolder holder, int position) {
        DoorLockEntry lockEntry = lockList.get(position);
        if (lockEntry != null) {
            holder.lockType.setText(lockLocale(lockEntry));
            holder.lockNumber.setText(lockNumber(getItemCount() - position));
        }
    }

    @Override
    public int getItemCount() {
        return lockList.size();
    }

    private String lockLocale(DoorLockEntry lockEntry) {
        switch (lockEntry.getLockType()) {
            case 2:
                return lockEntry.getLockDesc();
            case 1:
                return "Trava Deslizante";
            case 0:
                return "Porta Cadeado";
            default:
                return "";
        }
    }

    private String lockNumber(int i) {
        return "Dispositivo nº" + i;
    }


    public static class DoorLockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView lockType;
        public TextView lockNumber;

        public DoorLockViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            lockType = itemView.findViewById(R.id.item_info_one);
            lockNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
