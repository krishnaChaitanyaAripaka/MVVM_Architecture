package com.chaitu.topic.topic.data.repo

import com.chaitu.topic.topic.data.Resource
import com.chaitu.topic.topic.data.api.TopicApi
import com.chaitu.topic.topic.data.model.Topic

/**
 * Class that performs network operation to fetch the response from [TopicApi] Network layer
 *
 * @author Chaitanya
 */
class TopicRepository(private val topicApi: TopicApi) {
    suspend fun getTopics(): Resource<MutableList<Topic>?> {
        var response = topicApi.getTopics()
        return if (response.isSuccessful) {
            Resource.Success(response.body())
        } else {
            Resource.Failure(response.message())
        }
    }
}