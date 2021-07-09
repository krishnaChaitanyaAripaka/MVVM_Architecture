package com.chaitu.topic.topic.data

/**
 * State Handler that handles the UI state w.r.t the response fetched
 *
 * @author Chaitanya
 */
sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val body: T) : Resource<T>()
    data class Failure(val exceptionMessage: String) : Resource<Nothing>()
}
