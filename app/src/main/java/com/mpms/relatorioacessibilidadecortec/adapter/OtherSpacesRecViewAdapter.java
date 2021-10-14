package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;

import java.util.List;

public class OtherSpacesRecViewAdapter extends RecyclerView.Adapter<OtherSpacesRecViewAdapter.OtherViewHolder> {

    private List<OtherSpaces> othersList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public OtherSpacesRecViewAdapter(List<OtherSpaces> othersList, Context context, OnEntryClickListener entryClickListener) {
        this.othersList = othersList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public OtherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new OtherSpacesRecViewAdapter.OtherViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherViewHolder holder, int position) {
        OtherSpaces otherSpaces = othersList.get(position);
        holder.spaceName.setText(spaceName(otherSpaces));
        holder.spaceNumber.setText(spaceNumber(getItemCount()-position));
    }

    @Override
    public int getItemCount() {
        return othersList.size();
    }

    private String spaceName(OtherSpaces spaces) {
        return spaces.getOtherSpaceName();
    }

    private String spaceNumber(int i) {
        return "Ambiente nº"+(i);
    }

    public static class OtherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView spaceName;
        public TextView spaceNumber;

        public OtherViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            this.entryClickListener = entryClickListener;
            spaceName = itemView.findViewById(R.id.item_info_one);
            spaceNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
