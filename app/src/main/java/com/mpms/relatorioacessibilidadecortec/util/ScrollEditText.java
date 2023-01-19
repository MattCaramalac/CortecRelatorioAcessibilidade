package com.mpms.relatorioacessibilidadecortec.util;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public interface ScrollEditText {

    ArrayList<TextInputEditText> eText = new ArrayList<>();

    default boolean scrollingField(View v, MotionEvent event) {
        if (v.hasFocus()) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    default void allowObsScroll(ArrayList<TextInputEditText> eText) {
        for (TextInputEditText edit : eText) {
            edit.setOnTouchListener(this::scrollingField);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    default void allowObsScroll(TextInputEditText eText) {
        eText.setOnTouchListener(this::scrollingField);
    }
}
