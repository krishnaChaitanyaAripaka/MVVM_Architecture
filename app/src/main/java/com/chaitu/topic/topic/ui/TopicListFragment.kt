package com.chaitu.topic.topic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaitu.topic.databinding.FragmentTopicListBinding
import com.chaitu.topic.topic.adapter.TopicListAdapter
import com.chaitu.topic.topic.data.Resource
import com.chaitu.topic.topic.data.model.Topic
import com.chaitu.topic.topic.extensions.show
import com.chaitu.topic.topic.extensions.showToast
import com.chaitu.topic.topic.viewmodel.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * [Fragment] which shows the list ot topics and acts as a default destination in the navigation.
 *
 * @author Chaitanya
 */
@AndroidEntryPoint
class TopicListFragment : Fragment() {

    private var _binding: FragmentTopicListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var listAdapter: TopicListAdapter

    private val viewModel: TopicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchTopics()

        observeViewModel()

        binding.run {
            initRecyclerView()
        }
    }

    private fun observeViewModel() {
        viewModel.topicList.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Failure -> {
                    showProgressBar(false)
                    requireContext().showToast(response.exceptionMessage)
                }
                is Resource.Loading -> {
                    showProgressBar(true)
                }
                is Resource.Success -> {
                    showProgressBar(false)
                    listAdapter.submitList(response.body)
                }
            }
        })
    }

    private fun fetchTopics() {
        viewModel.fetchTopics()
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.show(show)
    }

    private fun FragmentTopicListBinding.initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = listAdapter
        }

        listAdapter.listener = { topic ->
            navigateToTopicDetails(topic)
        }
    }

    private fun navigateToTopicDetails(topic: Topic) {
        findNavController().navigate(
            TopicListFragmentDirections.actionTopicListFragmentToTopicDetailFragment(
                topic
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}