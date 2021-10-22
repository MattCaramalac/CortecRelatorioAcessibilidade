package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;

import java.util.List;

public class ParkElderRecViewAdapter extends RecyclerView.Adapter<ParkElderRecViewAdapter.ElderViewHolder>{

    private List<ParkingLotElderlyEntry> elderList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public ParkElderRecViewAdapter(List<ParkingLotElderlyEntry> elderList, Context context,
                                   OnEntryClickListener entryClickListener) {
        this.elderList = elderList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public ElderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ParkElderRecViewAdapter.ElderViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ElderViewHolder holder, int position) {
        ParkingLotElderlyEntry elderEntry = elderList.get(position);
        if (elderList.get(position) != null) {
            holder.elderVacancyNumber.setText(elderNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return elderList.size();
    }

    private String elderNumber(int i) {
        return "Vaga Idosos nº"+i;
    }

    public static class ElderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView elderVacancyNumber;

        public ElderViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);

            elderVacancyNumber = itemView.findViewById(R.id.item_info_one);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
