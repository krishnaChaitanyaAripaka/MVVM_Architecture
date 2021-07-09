package com.chaitu.topic.topic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitu.topic.topic.data.Resource
import com.chaitu.topic.topic.data.model.Topic
import com.chaitu.topic.topic.data.repo.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class to perform operations and provide data from [TopicRepository] to UI layer
 *
 * @author Chaitanya
 */
@HiltViewModel
class TopicViewModel @Inject constructor(private val topicsRepo: TopicRepository) : ViewModel() {
    private var _topicList = MutableLiveData<Resource<MutableList<Topic>?>>()
    val topicList: LiveData<Resource<MutableList<Topic>?>>
        get() = _topicList

    fun fetchTopics() {
        viewModelScope.launch(Dispatchers.IO) {
            _topicList.postValue(Resource.Loading)
            _topicList.postValue(topicsRepo.getTopics())
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}