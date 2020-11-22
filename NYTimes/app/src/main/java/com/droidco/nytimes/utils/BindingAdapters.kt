package com.droidco.nytimes.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.droidco.nytimes.init.GlideApp


@BindingAdapter(value = ["app:imageUrl", "app:imagePlaceHolder"], requireAll = true)
fun loadUrl(view: ImageView, url: String?, placeHolder: Drawable) {

    val factory =
        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    GlideApp.with(view.context)
        .load(url)
        .transition(withCrossFade(factory))
        .placeholder(placeHolder)
        .fitCenter()
        .apply(requestOptions)
        .into(view)
}

@BindingAdapter("app:setText")
fun setText(textView: TextView, text: String?) {
    if (Utility.isStringValid(text)) {
        textView.visibility = View.VISIBLE
        textView.text = text
    } else {
        textView.visibility = View.GONE
    }
}

@BindingAdapter("app:setHtmlText")
fun setHtmlText(textView: TextView, text: String) {

    if (Utility.isStringValid(text)) {
        textView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.GONE
        return
    }
    textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
}