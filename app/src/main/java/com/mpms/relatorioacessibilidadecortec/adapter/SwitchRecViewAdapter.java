package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;

import java.util.List;

public class SwitchRecViewAdapter extends RecyclerView.Adapter<SwitchRecViewAdapter.SwitchViewHolder> {

    private List<SwitchEntry> switchList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public SwitchRecViewAdapter(List<SwitchEntry> switchList, Context context, OnEntryClickListener entryClickListener) {
        this.switchList = switchList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public SwitchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new SwitchRecViewAdapter.SwitchViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SwitchViewHolder holder, int position) {
        SwitchEntry switchEntry = switchList.get(position);
        if (switchEntry != null) {
            holder.switchLocale.setText(switchLocation(switchEntry));
            holder.switchNumber.setText(switchNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return switchList.size();
    }

    public String switchLocation(SwitchEntry switchEntry) {
        return switchEntry.getSwitchLocation();
    }

    public String switchNumber (int i) {
        return "Interruptor nº" + i;
    }


    public static class SwitchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView switchLocale;
        public TextView switchNumber;

        public SwitchViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            switchLocale = itemView.findViewById(R.id.item_info_one);
            switchNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
