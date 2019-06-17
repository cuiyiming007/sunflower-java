package com.cym.sunflower.adapters;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("isGone")
    public static void bindIsGone(View view, Boolean isGone) {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }
}
