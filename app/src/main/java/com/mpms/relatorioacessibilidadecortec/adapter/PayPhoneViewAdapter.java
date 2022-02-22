package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;

import java.util.List;

public class PayPhoneViewAdapter extends RecyclerView.Adapter<PayPhoneViewAdapter.PayPhoneViewHolder>{

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
    public PayPhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_item_entry_layout, parent, false);
        return new PayPhoneViewAdapter.PayPhoneViewHolder(view, entryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PayPhoneViewHolder holder, int position) {
        PayPhoneEntry payPhoneEntry = payphoneList.get(position);
        if (payPhoneEntry != null) {
            holder.payPhoneLocation.setText(payPhoneText(payPhoneEntry));
            holder.payPhoneNumber.setText(payphoneNumber(getItemCount()-position));
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


    public static class PayPhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public TextView payPhoneLocation;
        public TextView payPhoneNumber;

        public PayPhoneViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            payPhoneLocation = itemView.findViewById(R.id.item_info_one);
            payPhoneNumber = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }
}
