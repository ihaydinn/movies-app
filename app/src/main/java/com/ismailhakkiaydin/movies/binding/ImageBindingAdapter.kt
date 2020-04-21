package com.ismailhakkiaydin.movies.binding


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ismailhakkiaydin.movies.util.Constant


@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(Constant.IMAGE_BASE_URL + Constant.IMAGE_W500 + url)
        .into(imageView)
}


