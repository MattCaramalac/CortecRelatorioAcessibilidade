package com.mpms.relatorioacessibilidadecortec.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.OnPopupClickListener;

import java.util.List;

public interface ViewHolderInterface {

    SparseBooleanArray selectedItems = new SparseBooleanArray();
    int delClick = 0;

    class MainListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, TagInterface, PopupMenu.OnMenuItemClickListener {

        public OnEntryClickListener entryClickListener;
        public OnPopupClickListener popupListener;
        public LinearLayout background;
        public HorizontalScrollView scrollText;
        public TextView textInfoOne;
        public TextView textInfoTwo;
        public ImageView check;
        public ImageButton options;
//        public AnimationScroller scroller;
        public ObjectScroller obScroller;

        public MainListViewHolder(@NonNull View itemView, OnEntryClickListener entryClickListener, OnPopupClickListener popupListener) {
            super(itemView);

            background = itemView.findViewById(R.id.main_background);
            scrollText = itemView.findViewById(R.id.scroll_school_name);
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

    class ObjectScroller {
        ObjectAnimator animator;
        TextView scrollText;
        long duration = 7500;
        long delay = 2500;

        public ObjectScroller(TextView scroll, int itemWidth, int viewWidth) {
            this.scrollText = scroll;
            animator = ObjectAnimator.ofFloat(scrollText, "translationX", viewWidth-(itemWidth + 50));
            animator.setStartDelay(delay);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(duration);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setRepeatCount(ValueAnimator.INFINITE);
        }

        public void startObjectScroll() {
            animator.addListener(new DelayAnimation(delay));
            animator.start();
        }
    }

    class DelayAnimation implements Animator.AnimatorListener {
        private long delayMillis;

        public DelayAnimation(long delayMillis) {
            this.delayMillis = delayMillis;
        }

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            animation.pause();
            new Handler().postDelayed(animation::resume, delayMillis);
        }
    }
}
