package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<SchoolEntry> schoolEntryList;
    private Context context;

    public RecyclerViewAdapter(List<SchoolEntry> schoolEntryList, Context context) {
        this.schoolEntryList = schoolEntryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entries_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SchoolEntry schoolEntry = schoolEntryList.get(position);
        holder.nameSchool.setText(schoolEntry.getSchoolName());
        holder.nameCity.setText(schoolEntry.getNameCity());

    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(schoolEntryList).size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameSchool;
        public TextView nameCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSchool = itemView.findViewById(R.id.schoolNameLayout);
            nameCity = itemView.findViewById(R.id.cityNameLayout);
        }
    }
}
