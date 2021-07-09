package com.chaitu.topic.topic.extensions

import android.content.Context
import android.widget.Toast

/**
 * Class that contains extension functions to show any alerts, dialogs, toast on any screen
 *
 * @author Chaitanya
 */
fun Context.showToast(message: String) =
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()