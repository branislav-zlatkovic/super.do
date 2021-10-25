package com.example.myapplication.view.listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentItemListingsBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ItemsListingsFragment : Fragment() {

    private var _binding: FragmentItemListingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ItemsListingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)
            .get(ItemsListingsViewModel::class.java)
        _binding = FragmentItemListingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startReceiveData()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_open_item_details)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}