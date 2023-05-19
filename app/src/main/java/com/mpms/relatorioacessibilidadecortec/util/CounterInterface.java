package com.mpms.relatorioacessibilidadecortec.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.mpms.relatorioacessibilidadecortec.R;

public interface CounterInterface {

    default void setCounter(Context context, TextView view, int number) {
        view.setActivated(true);
        view.setTextColor(ContextCompat.getColorStateList(context, R.color.countercolor));
        view.setTypeface(null, Typeface.BOLD);
        view.setText(context.getString(R.string.counter_qnt, number));
    }

    default void clearCounter(Context context, TextView view) {
        view.setActivated(false);
        view.setTextColor(ContextCompat.getColorStateList(context, R.color.countercolor));
        view.setTypeface(null, Typeface.NORMAL);
        view.setText(context.getString(R.string.counter_qnt, 0));
    }
}
