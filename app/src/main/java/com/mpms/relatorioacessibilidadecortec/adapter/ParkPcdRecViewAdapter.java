package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPCDEntry;

import java.util.List;

public class ParkPcdRecViewAdapter extends RecyclerView.Adapter<ParkPcdRecViewAdapter.PcdViewHolder> {

    private List<ParkingLotPCDEntry> pcdList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public ParkPcdRecViewAdapter(List<ParkingLotPCDEntry> pcdList, Context context, OnEntryClickListener entryClickListener) {
        this.pcdList = pcdList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public PcdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ParkPcdRecViewAdapter.PcdViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PcdViewHolder holder, int position) {
        ParkingLotPCDEntry pcdEntry = pcdList.get(position);
        if (pcdList.get(position) != null) {
            holder.pcdVacancyNumber.setText(pcdNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return pcdList.size();
    }

    private String pcdNumber(int i) {
        return "Vaga PCD nº"+i;
    }

    public static class PcdViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView pcdVacancyNumber;

        public PcdViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            pcdVacancyNumber = itemView.findViewById(R.id.item_info_one);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
