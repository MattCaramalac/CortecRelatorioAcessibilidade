package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import java.util.List;

public class WindowRecViewAdapter extends RecyclerView.Adapter<WindowRecViewAdapter.WindowViewHolder> {

    private List<WindowEntry> windowList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public WindowRecViewAdapter(List<WindowEntry> windowList, Context context, OnEntryClickListener entryClickListener) {
        this.windowList = windowList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public WindowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new WindowRecViewAdapter.WindowViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WindowViewHolder holder, int position) {
        WindowEntry windowEntry = windowList.get(position);
        if (windowEntry != null) {
            holder.windowLocale.setText(windowLocation(windowEntry));
            holder.windowNumber.setText(windowNumber(getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return windowList.size();
    }

    private String windowLocation(WindowEntry windowEntry) {
        return windowEntry.getWindowLocation();
    }

    private String windowNumber(int i) {
        return "Janela nº" + i;
    }


    public static class WindowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView windowLocale;
        public TextView windowNumber;

        public WindowViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            windowLocale = itemView.findViewById(R.id.item_info_one);
            windowNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
