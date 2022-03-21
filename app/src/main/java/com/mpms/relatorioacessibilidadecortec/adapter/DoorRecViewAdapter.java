package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;

import java.util.List;

public class DoorRecViewAdapter extends RecyclerView.Adapter<DoorRecViewAdapter.DoorViewHolder> {

    private List<DoorEntry> doorList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public DoorRecViewAdapter(List<DoorEntry> doorList, Context context, OnEntryClickListener entryClickListener) {
        this.doorList = doorList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public DoorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new DoorRecViewAdapter.DoorViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DoorViewHolder holder, int position) {
        DoorEntry doorEntry = doorList.get(position);
        if (doorEntry != null) {
            holder.doorLocation.setText(doorLocale(doorEntry));
            holder.doorNumber.setText(doorNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return doorList.size();
    }

    private String doorLocale(DoorEntry door) {
        return door.getDoorLocation();
    }

    private String doorNumber(int i) {
        return "Porta nº" + i;
    }


    public static class DoorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView doorLocation;
        public TextView doorNumber;

        public DoorViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            doorLocation = itemView.findViewById(R.id.item_info_one);
            doorNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
