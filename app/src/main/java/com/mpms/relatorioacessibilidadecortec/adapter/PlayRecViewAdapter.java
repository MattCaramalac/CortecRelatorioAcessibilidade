package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;

import java.util.List;

public class PlayRecViewAdapter extends RecyclerView.Adapter<PlayRecViewAdapter.PlayViewHolder> {

    private List<PlaygroundEntry> playList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public PlayRecViewAdapter(List<PlaygroundEntry> playList, Context context, OnEntryClickListener entryClickListener) {
        this.playList = playList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new PlayRecViewAdapter.PlayViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayViewHolder holder, int position) {
       PlaygroundEntry playground = playList.get(position);
        if (playground != null) {
            holder.playLocale.setText(playgroundLocation(playground));
            holder.playNumber.setText(playgroundNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return playList.size();
    }

    public String playgroundLocation(PlaygroundEntry play) {
        return play.getPlayLocation();
    }

    public String playgroundNumber (int i) {
        return "Playground nº" + i;
    }


    public static class PlayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView playLocale;
        public TextView playNumber;

        public PlayViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            playLocale = itemView.findViewById(R.id.item_info_one);
            playNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
