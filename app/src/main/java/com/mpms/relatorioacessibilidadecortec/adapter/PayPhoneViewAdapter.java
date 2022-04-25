package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.DeleteInterface;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;

public class PayPhoneViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.ListViewHolder> implements DeleteInterface, ViewHolderInterface {

    private ListClickListener listener;
    private List<PayPhoneEntry> payphoneList;
    private Context context;
    private OnEntryClickListener entryClickListener;

    public PayPhoneViewAdapter(List<PayPhoneEntry> payphoneList, Context context, OnEntryClickListener entryClickListener) {
        this.payphoneList = payphoneList;
        this.context = context;
        this.entryClickListener = entryClickListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new ViewHolderInterface.ListViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        PayPhoneEntry payPhoneEntry = payphoneList.get(position);
        if (payPhoneEntry != null) {
            holder.textInfoOne.setText(payPhoneText(payPhoneEntry));
            holder.textInfoTwo.setText(payphoneNumber(getItemCount()-position));
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
        return payphoneList.size();
    }

    private String payPhoneText(PayPhoneEntry payphone) {
        return "Localizaçao: " + payphone.getPhoneRefPoint();
    }

    private String payphoneNumber(int i) {
        return "Telefone nº"+i;
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deletePayPhoneEntry(payphoneList.get(selectedItems.keyAt(i)).getPayPhoneID());
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
        int listSize = payphoneList.size();
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
