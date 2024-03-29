package com.mpms.relatorioacessibilidadecortec.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.ViewHolderInterface;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderInterface.MainListViewHolder> implements ViewHolderInterface {

    private ListClickListener listener;
    private List<SchoolEntry> schoolEntryList;
    private Context context;
    private OnEntryClickListener entryClickListener;
    private OnPopupClickListener popupListener;

    public RecyclerViewAdapter(List<SchoolEntry> schoolEntryList, Context context, OnEntryClickListener entryClickListener, OnPopupClickListener popupListener) {
        this.schoolEntryList = schoolEntryList;
        this.context = context;
        this.entryClickListener = entryClickListener;
        this.popupListener = popupListener;
    }


    @NonNull
    @Override
    public ViewHolderInterface.MainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entries_layout, parent, false);
        return new ViewHolderInterface.MainListViewHolder(view, entryClickListener, popupListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInterface.MainListViewHolder holder, int position) {
        SchoolEntry schoolEntry = schoolEntryList.get(position);
        holder.textInfoOne.setText(schoolEntry.getSchoolName());
        holder.textInfoTwo.setText(schoolEntry.getNameCity());
        if (schoolEntry.getReportSent() == 1)
            holder.check.setVisibility(View.VISIBLE);

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

        this.beforeDraw(holder.textInfoOne, () -> {
            Paint textPaint = holder.textInfoOne.getPaint();
            String text = holder.textInfoOne.getText().toString();
            int itemWidth = Math.round(textPaint.measureText(text));

            int viewWidth = holder.scrollText.getWidth();

            if (itemWidth > viewWidth) {
                holder.obScroller = new ObjectScroller(holder.textInfoOne, itemWidth,viewWidth);
                holder.obScroller.startObjectScroll();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(schoolEntryList).size();
    }

    @Override
    public void deleteItemList() {
        int listSize = selectedItems.size();
        for (int i = 0; i < listSize; i++) {
            ViewModelEntry.deleteOneSchoolEntry(schoolEntryList.get(selectedItems.keyAt(i)));
        }
    }

    @Override
    public void setListener(ListClickListener listener) {
        this.listener = listener;
    }

    public void beforeDraw(View view, Runnable runnable) {
        ViewTreeObserver.OnPreDrawListener preDraw = new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                runnable.run();
                return true;
            }
        };
        view.getViewTreeObserver().addOnPreDrawListener(preDraw);
    }

}
