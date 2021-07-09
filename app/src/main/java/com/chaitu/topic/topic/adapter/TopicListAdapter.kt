package com.chaitu.topic.topic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chaitu.topic.BR
import com.chaitu.topic.databinding.ItemTopicListBinding
import com.chaitu.topic.topic.data.model.Topic
import javax.inject.Inject

/**
 * Class that displays list of topics using [ListAdapter]
 *
 * @author Chaitanya
 */
class TopicListAdapter @Inject constructor() :
    ListAdapter<Topic, TopicListAdapter.ViewHolder>(DiffCallback()) {

    var listener: ((Topic) -> Unit)? = null

    private class DiffCallback : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Topic, newItem: Topic) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTopicListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic)
        holder.binding.root.setOnClickListener { listener?.invoke(topic) }
    }

    inner class ViewHolder(val binding: ItemTopicListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(topic: Topic) {
            binding.apply {
                setVariable(BR.topic, topic)
                executePendingBindings()
            }
        }
    }
}