package com.chaitu.topic.topic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.chaitu.topic.databinding.FragmentTopicDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * [Fragment] that displays the topic details.
 *
 * @author Chaitanya
 */
@AndroidEntryPoint
class TopicDetailsFragment : Fragment() {

    private var _binding: FragmentTopicDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: TopicDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTopicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            name.text = args.topic.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}