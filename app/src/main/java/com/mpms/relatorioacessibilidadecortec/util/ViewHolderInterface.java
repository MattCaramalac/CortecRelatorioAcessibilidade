package com.mpms.relatorioacessibilidadecortec.util;

import android.content.Intent;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.OnPopupClickListener;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.List;

public interface ViewHolderInterface {

    SparseBooleanArray selectedItems = new SparseBooleanArray();
    int delClick = 0;

    class MainListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, TagInterface, PopupMenu.OnMenuItemClickListener {

        public OnEntryClickListener entryClickListener;
        public OnPopupClickListener popupListener;
        public LinearLayout background;
        public TextView textInfoOne;
        public TextView textInfoTwo;
        public ImageView check;
        public ImageButton options;
        public AnimationScroller scroller;

        public MainListViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener, OnPopupClickListener popupListener) {
            super(itemView);

            background = itemView.findViewById(R.id.main_background);
            textInfoOne = itemView.findViewById(R.id.schoolNameLayout);
            textInfoTwo = itemView.findViewById(R.id.cityNameLayout);
            check = itemView.findViewById(R.id.report_generated_check);
            options = itemView.findViewById(R.id.register_options_button);
            options.setOnClickListener(this::imgClick);
            this.entryClickListener = entryClickListener;
            this.popupListener = popupListener;
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }

        public void imgClick (View v) {
            PopupMenu menu = new PopupMenu(v.getContext(), v);
            menu.inflate(R.menu.menu_main);
            menu.setOnMenuItemClickListener(this);
            menu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = getAdapterPosition();
            popupListener.onPopupClickOption(position, item);
            return false;
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public OnEntryClickListener entryClickListener;
        public LinearLayout background;
        public TextView textInfoOne;
        public TextView textInfoTwo;

        public ListViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener) {
            super(itemView);
            background = itemView.findViewById(R.id.item_holder);
            textInfoOne = itemView.findViewById(R.id.item_info_one);
            textInfoTwo = itemView.findViewById(R.id.item_info_two);
            this.entryClickListener = entryClickListener;
        }

        @Override
        public void onClick(View v) {
            entryClickListener.OnEntryClick(getAdapterPosition());
        }
    }

    default <G extends RecyclerView.ViewHolder> void toggleSelection(RecyclerView.Adapter<G> adapter, int position) {
        if (selectedItems.get(position))
            selectedItems.delete(position);
        else
            selectedItems.put(position, true);
        adapter.notifyItemChanged(position);

    }

    default <T, G extends RecyclerView.ViewHolder> void cancelSelection(RecyclerView recyclerView, List<T> entryList, RecyclerView.Adapter<G> adapter) {
        int listSize = entryList.size();
        for (int i = 0; i < listSize; i++) {
            ViewHolderInterface.MainListViewHolder holder = (ViewHolderInterface.MainListViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            holder.background.setBackgroundColor(Color.rgb(255, 255, 255));
            adapter.notifyItemChanged(i);
        }
    }

    void setListener(ListClickListener listener);
    void deleteItemList();

    public class AnimationScroller {
        Animation animation;
        TextView scrollText;
        long duration = 15000;

        //        TODO - Corrigir o tanto que o texto dá scroll até somente terminar o texto em questão
        public AnimationScroller(TextView scroll, int itemWidth, int viewWidth) {
            this.scrollText = scroll;
            this.animation = new TranslateAnimation(0, viewWidth-itemWidth,
                    0,  0);
            this.animation.setStartOffset(1000);
            this.animation.setInterpolator(new LinearInterpolator());
            this.animation.setDuration(this.duration);
            this.animation.setFillAfter(true);
            this.animation.setRepeatMode(Animation.RESTART);
            this.animation.setRepeatCount(Animation.INFINITE);
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public void setScrollText(String text) {
            this.scrollText.setText(text);
        }

        public void startScroll() {
            this.scrollText.setSelected(true);
            this.scrollText.startAnimation(this.animation);
        }

        public void setAnimationListener() {

            animation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }
                public void onAnimationEnd(Animation animation) {
                    // This callback function can be used to perform any task at the end of the Animation
                }
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }
}
