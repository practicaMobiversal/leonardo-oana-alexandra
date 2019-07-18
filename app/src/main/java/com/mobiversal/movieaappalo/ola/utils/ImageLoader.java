package com.mobiversal.movieaappalo.ola.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoader {

    public static void loadImageUrl(ImageView imageView, String url, Context context) {
        Glide.with(context).load(url).into(imageView);

    }
}
