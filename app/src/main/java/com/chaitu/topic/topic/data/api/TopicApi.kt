package com.chaitu.topic.topic.data.api

import com.chaitu.topic.topic.data.model.Topic
import retrofit2.Response
import retrofit2.http.GET

/**
 * Class that contains list of Topic Api's
 *
 * @author Chaitanya
 */
interface TopicApi {
    @GET("topics")
    suspend fun getTopics(): Response<MutableList<Topic>>
}