package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class ParkPcdRecViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
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
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ParkingLotPCDEntry pcdEntry = pcdList.get(position);
        if (pcdEntry != null) {
            holder.textInfoOne.setText(pcdLocale(pcdEntry.getPcdVacancyLocal()));
            holder.textInfoTwo.setText(pcdNumber(getItemCount()-position));
            if (selectedItems.get(position))
                holder.background.setBackgroundColor(Color.rgb(158, 235, 247));
            else
                holder.background.setBackgroundColor(Color.rgb(255, 255, 255));

            holder.itemView.setOnClickListener(v -> {
                if (selectedItems.size() > 0) {
                    toggleSelection(this, position);
                }
                listener.onItemClick(position);
            });

            holder.itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    toggleSelection(this, position);
                    listener.onItemLongClick(position);
                }
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return pcdList.size();
    }

    private String pcdNumber(int i) {
        return "Vaga PCD nº"+i;
    }

    private String pcdLocale(String locale) {
        return "Vaga - " + locale;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deletePdmrParkingLot(pcdList.get(selectedItems.keyAt(i)).getParkPcdID());
        }
    }
    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }
}
