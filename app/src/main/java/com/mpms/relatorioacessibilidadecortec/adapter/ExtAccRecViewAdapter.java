package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;

import java.util.List;

public class ExtAccRecViewAdapter extends RecyclerView.Adapter<ExtAccRecViewAdapter.ExtViewHolder> {

    private List<ExternalAccess> extAccessList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public ExtAccRecViewAdapter(List<ExternalAccess> extAccessList, Context context, OnEntryClickListener entryClickListener) {
        this.extAccessList = extAccessList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }


    @NonNull
    @Override
    public ExtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ext_access_entries_layout, parent, false);
        return new ExtAccRecViewAdapter.ExtViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtViewHolder holder, int position) {
        ExternalAccess extAccess = extAccessList.get(position);
        if (extAccess.getEntranceType() != null) {
            holder.extAccessType.setText(entranceType(extAccess.getEntranceType()));
            holder.extAccessNumber.setText(extAccessNumber(position));
        }
    }

    @Override
    public int getItemCount() {
        return extAccessList.size();
    }

    public String entranceType(int extType) {
        if (extType == 0)
            return "Entrada Social";
        else
            return "Entrada de Veículos";
    }

    public String extAccessNumber(int i) {
        return "Entrada Externa nº "+(i+1);
    }


    public static class ExtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView extAccessType;
        public TextView extAccessNumber;

        public ExtViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            extAccessType = itemView.findViewById(R.id.ext_access_type);
            extAccessNumber = itemView.findViewById(R.id.ext_access_number);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }


}


