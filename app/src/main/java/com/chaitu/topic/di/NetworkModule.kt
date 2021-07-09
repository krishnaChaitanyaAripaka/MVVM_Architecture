package com.chaitu.topic.di

import com.chaitu.topic.topic.data.api.TopicApi
import com.chaitu.topic.topic.data.repo.TopicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Singleton class that provides dependencies to achieve Dependency Inversion principle
 *
 * @author Chaitanya
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val baseUrl = "http://demo0850988.mockable.io/"

    @Singleton
    @Provides
    fun provideBaseUrl() = baseUrl

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create()
    ).baseUrl(baseUrl).build()

    @Singleton
    @Provides
    fun provideTopicService(retrofit: Retrofit): TopicApi = retrofit.create(TopicApi::class.java)

    @Singleton
    @Provides
    fun provideTopicRepo(topicApi: TopicApi) = TopicRepository(topicApi)
}