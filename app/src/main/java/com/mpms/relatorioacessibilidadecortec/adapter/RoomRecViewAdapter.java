package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;

import java.util.List;

public class RoomRecViewAdapter extends RecyclerView.Adapter<RoomRecViewAdapter.RoomViewHolder> {

    private List<RoomEntry> roomList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public RoomRecViewAdapter(List<RoomEntry> roomList, Context context, OnEntryClickListener entryClickListener) {
        this.roomList = roomList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.generic_item_entry_layout, viewGroup, false);
        return new RoomRecViewAdapter.RoomViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RoomEntry roomEntry = roomList.get(position);
        if (roomEntry != null) {
            holder.roomLocale.setText(roomLocation(roomEntry));
            holder.roomNumber.setText(roomNumeral(roomEntry, getItemCount()-position));
        }
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    private String roomLocation(RoomEntry roomEntry) {
        if (roomEntry.getRoomType() == 3)
            return "Biblioteca - "+ roomEntry.getRoomLocation();
        else if (roomEntry.getRoomType() == 5)
            return "Coordenação - "+ roomEntry.getRoomLocation();
        else if (roomEntry.getRoomType() == 6)
            return "Diretoria - "+ roomEntry.getRoomLocation();
        else if (roomEntry.getRoomType() == 10)
            return "Refeitório - "+ roomEntry.getRoomLocation();
        else if (roomEntry.getRoomType() == 11)
            return "Sala de Aula - "+ roomEntry.getRoomLocation();
        else if (roomEntry.getRoomType() == 12)
            return "Sala de Tecnologia - "+ roomEntry.getRoomLocation();
        else if (roomEntry.getRoomType() == 13)
            return "Sala de Recursos - "+ roomEntry.getRoomLocation();
        else if (roomEntry.getRoomType() == 14)
            return "Sala dos Professores - "+ roomEntry.getRoomLocation();
        else
            return "Secretaria - "+ roomEntry.getRoomLocation();
    }

    private String roomNumeral(RoomEntry roomEntry, int i) {
        if (roomEntry.getRoomType() == 3)
            return "Biblioteca nº"+i;
        else if (roomEntry.getRoomType() == 5)
            return "Coordenação nº"+i;
        else if (roomEntry.getRoomType() == 6)
            return "Diretoria nº"+i;
        else if (roomEntry.getRoomType() == 10)
            return "Refeitório nº"+i;
        else if (roomEntry.getRoomType() == 11)
            return "Sala de Aula nº"+i;
        else if (roomEntry.getRoomType() == 12)
            return "Sala de Tecnologia nº"+i;
        else if (roomEntry.getRoomType() == 13)
            return "Sala de Recursos nº"+i;
        else if (roomEntry.getRoomType() == 14)
            return "Sala dos Professores nº"+i;
        else
            return "Secretaria nº"+i;
    }


    public static class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView roomLocale;
        public TextView roomNumber;

        public RoomViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            roomLocale = itemView.findViewById(R.id.item_info_one);
            roomNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { entryClickListener.OnEntryClick(getAdapterPosition()); }
    }
}
