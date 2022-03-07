package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.AdmEquipEntry;

import java.util.List;

public class AdmEquipRecViewAdapter extends RecyclerView.Adapter<AdmEquipRecViewAdapter.AdmEquipViewHolder> {

    private List<AdmEquipEntry> admEquipList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public AdmEquipRecViewAdapter(List<AdmEquipEntry> admEquipList, Context context, OnEntryClickListener entryClickListener) {
        this.admEquipList = admEquipList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public AdmEquipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new AdmEquipRecViewAdapter.AdmEquipViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdmEquipViewHolder holder, int position) {
        AdmEquipEntry admEquip = admEquipList.get(position);
        if (admEquip != null) {
            holder.admEquipLocale.setText(admEquipLocation(admEquip));
            holder.admEquipNumber.setText(admEquipNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return admEquipList.size();
    }

    public String admEquipLocation(AdmEquipEntry admEquip) {
        return admEquip.getAdmEquipLocation();
    }

    public String admEquipNumber (int i) {
        return "Equipamento nº" + i;
    }


    public static class AdmEquipViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView admEquipLocale;
        public TextView admEquipNumber;

        public AdmEquipViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            admEquipLocale = itemView.findViewById(R.id.item_info_one);
            admEquipNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
