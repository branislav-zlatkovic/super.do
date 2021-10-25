package com.example.myapplication.view.itemdetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentItemDetailsBinding
import com.google.android.material.transition.MaterialContainerTransform

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ItemDetailsFragment : Fragment() {

    private var _binding: FragmentItemDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemDetailsBinding.inflate(inflater, container, false)

        val args: ItemDetailsFragmentArgs by navArgs()
        val transitionName = args.sharedTransitionName
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 350
        }
        binding.imgBackground.setBackgroundColor(Color.parseColor(transitionName.split(":")[1]))
        binding.imgBackground.transitionName = transitionName

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}