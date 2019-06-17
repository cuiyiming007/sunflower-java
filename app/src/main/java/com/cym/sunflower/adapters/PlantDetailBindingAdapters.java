package com.cym.sunflower.adapters;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.cym.sunflower.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;

public class PlantDetailBindingAdapters {

    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }

    @BindingAdapter("isGone")
    public static void bindIsGone(FloatingActionButton view, Boolean isGone) {
        if (isGone == null || isGone) {
            view.hide();
        } else {
            view.show();
        }
    }

    @BindingAdapter("renderHtml")
    public static void bindRenderHtml(TextView view, String description) {
        if (description != null) {
            view.setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            view.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            view.setText("");
        }
    }

    @BindingAdapter("wateringText")
    public static void bindWateringText(TextView textView, int wateringInterval) {
        Resources resources = textView.getContext().getResources();
        String quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
                wateringInterval, wateringInterval);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        int length = builder.length();
        builder.append(resources.getString(R.string.watering_needs_prefix))
                .setSpan(new StyleSpan(Typeface.BOLD), length, builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.append(" ");
        length = builder.length();
        builder.append(quantityString)
                .setSpan(new StyleSpan(Typeface.ITALIC), length, builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }
}
