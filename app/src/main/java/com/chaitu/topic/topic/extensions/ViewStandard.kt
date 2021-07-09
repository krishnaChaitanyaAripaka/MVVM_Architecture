package com.chaitu.topic.topic.extensions

import android.view.View

/**
 * Class that contains extension functions for UI elements
 *
 * @author Chaitanya
 */
fun View.show(show: Boolean) {
    if (show) visible() else gone()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

