package com.rijatru.development.nativappstest.presentation.views.utils;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Bitmap;
import android.text.Editable;
import android.widget.ImageView;

import com.rijatru.development.nativappstest.presentation.views.AutoCompleteFormTextField;
import com.rijatru.development.nativappstest.presentation.views.adapters.TextWatcherAdapter;
import com.squareup.picasso.Picasso;

public class Binding {

    @BindingAdapter(value = "valueAttrChanged")
    public static void setListener(AutoCompleteFormTextField textField, final InverseBindingListener listener) {
        if (listener != null) {
            textField.getEditText().addTextChangedListener(new TextWatcherAdapter() {
                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onChange();
                }
            });
        }
    }

    @BindingAdapter("value")
    public static void setValue(AutoCompleteFormTextField textField, String value) {
        if (value == null) {
            value = "";
        }
        if (!textField.getValue().equals(value)) {
            textField.setValue(value);
        }
    }

    @InverseBindingAdapter(attribute = "value")
    public static String getValue(AutoCompleteFormTextField textField) {
        return textField.getValue();
    }

    @BindingAdapter({"app_photo"})
    public static void setImageUrl(ImageView imageView, final String url) {
        Picasso.get().load(url)
                .config(Bitmap.Config.RGB_565)
                .fit()
                .centerCrop()
                .into(imageView);
    }
}
