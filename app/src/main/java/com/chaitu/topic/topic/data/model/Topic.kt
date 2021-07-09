package com.chaitu.topic.topic.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Model class for topics
 *
 * @author Chaitanya
 */
@Parcelize
data class Topic(val name: String, val desc: String) : Parcelable
