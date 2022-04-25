package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.DeleteInterface;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class RoomRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements DeleteInterface, ViewHolderInterface {

    private ListClickListener listener;
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
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.generic_item_entry_layout, viewGroup, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        RoomEntry roomEntry = roomList.get(position);
        if (roomEntry != null) {
            holder.textInfoOne.setText(roomLocation(roomEntry));
            holder.textInfoTwo.setText(roomNumeral(roomEntry, getItemCount()-position));
            if (selectedItems.get(position))
                holder.background.setBackgroundColor(Color.rgb(158, 235, 247));
            else
                holder.background.setBackgroundColor(Color.rgb(255, 255, 255));

            holder.itemView.setOnClickListener(v -> {
                if (selectedItems.size() > 0) {
                    toggleSelection(holder, position);
                }
                listener.onItemClick(position);
            });

            holder.itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    toggleSelection(holder, position);
                    listener.onItemLongClick(position);
                }
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    private String roomLocation(RoomEntry roomEntry) {
        switch (roomEntry.getRoomType()) {
            case 3:
                return "Biblioteca - "+ roomEntry.getRoomLocation();
            case 5:
                return "Coordenação - "+ roomEntry.getRoomLocation();
            case 6:
                return "Diretoria - "+ roomEntry.getRoomLocation();
            case 10:
                return "Refeitório - "+ roomEntry.getRoomLocation();
            case 11:
                return "Sala de Aula - "+ roomEntry.getRoomLocation();
            case 12:
                return "Sala de Tecnologia - "+ roomEntry.getRoomLocation();
            case 13:
                return "Sala de Recursos - "+ roomEntry.getRoomLocation();
            case 14:
                return "Sala dos Professores - "+ roomEntry.getRoomLocation();
            case 15:
                return "Secretaria - "+ roomEntry.getRoomLocation();
            case 16:
                return roomEntry.getRoomLocation();
            default:
                return "";
        }
    }

    private String roomNumeral(RoomEntry roomEntry, int i) {
        switch (roomEntry.getRoomType()) {
            case 3:
                return "Biblioteca nº"+i;
            case 5:
                return "Coordenação nº"+i;
            case 6:
                return "Diretoria nº"+i;
            case 10:
                return "Refeitório nº"+i;
            case 11:
                return "Sala de Aula nº"+i;
            case 12:
                return "Sala de Tecnologia nº"+i;
            case 13:
                return "Sala de Recursos nº"+i;
            case 14:
                return "Sala dos Professores nº"+i;
            case 15:
                return "Secretaria nº"+i;
            case 16:
                return "Ambiente nº"+i;
            default:
                return "";
        }
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteRoom(roomList.get(selectedItems.keyAt(i)).getRoomID());
        }
    }

    @Override
    public void toggleSelection(ListViewHolder holder, int position) {
        if (selectedItems.get(position))
            selectedItems.delete(position);
        else
            selectedItems.put(position, true);
        notifyItemChanged(position);
    }

    @Override
    public void cancelSelection(RecyclerView recyclerView) {
        int listSize = roomList.size();
        for (int i = 0; i < listSize; i++) {
            ListViewHolder holder = (ListViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            holder.background.setBackgroundColor(Color.rgb(255, 255, 255));
            notifyItemChanged(i);
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
