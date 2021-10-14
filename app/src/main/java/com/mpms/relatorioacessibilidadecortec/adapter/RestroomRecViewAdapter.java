package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;

import java.util.List;

public class RestroomRecViewAdapter extends RecyclerView.Adapter<RestroomRecViewAdapter.RestViewHolder> {

    private List<RestroomEntry> restroomList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public RestroomRecViewAdapter(List<RestroomEntry> restroomList, Context context, OnEntryClickListener entryClickListener) {
        this.restroomList = restroomList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public RestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new RestroomRecViewAdapter.RestViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RestViewHolder holder, int position) {
        RestroomEntry restroomEntry = restroomList.get(position);
        if (restroomList.get(position) != null) {
            holder.restroomLocation.setText(restLocationType(restroomEntry));
            holder.restroomNumber.setText(restNumber(position));
        }
    }

    @Override
    public int getItemCount() {
        return restroomList.size();
    }

    public String restLocationType(RestroomEntry restroom) {
        String restType = "";
        switch (restroom.getRestroomType()) {
            case 0:
                restType = "- Masculino";
                break;
            case 1:
                restType = "- Feminino";
                break;
            case 2:
                restType = "- Familiar";
                break;
            default:
                break;
        }
        return restroom.getRestroomLocation()+ " " + restType;
    }

    public String restNumber(int i) {
        return "Sanitário nº " + (i+1);
    }

    public static class RestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView restroomLocation;
        public TextView restroomNumber;

        public RestViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            restroomLocation = itemView.findViewById(R.id.item_info_one);
            restroomNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
