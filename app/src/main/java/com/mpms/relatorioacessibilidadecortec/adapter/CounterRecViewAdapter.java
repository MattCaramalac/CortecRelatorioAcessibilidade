package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;

import java.util.List;

public class CounterRecViewAdapter extends RecyclerView.Adapter<CounterRecViewAdapter.CounterViewHolder> {

    private List<CounterEntry> counterList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public CounterRecViewAdapter(List<CounterEntry> counterList, Context context, OnEntryClickListener entryClickListener) {
        this.counterList = counterList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public CounterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new CounterRecViewAdapter.CounterViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterViewHolder holder, int position) {
        CounterEntry entry = counterList.get(position);
        if (entry != null) {
            holder.counterLocation.setText(counterLocale(entry));
            holder.counterNumber.setText(counterNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return counterList.size();
    }

    private String counterLocale(CounterEntry entry) {
        return entry.getCounterLocation();
    }

    private String counterNumber(int i) {
        return "Balcão nº"+i;
    }


    public static class CounterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView counterLocation;
        public TextView counterNumber;

        public CounterViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            counterLocation = itemView.findViewById(R.id.item_info_one);
            counterNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
