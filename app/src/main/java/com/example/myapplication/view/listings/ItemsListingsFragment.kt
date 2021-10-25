package com.example.myapplication.view.listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentItemListingsBinding
import com.example.myapplication.view.common.UnifiedItemDecoration

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
        viewModel = ViewModelProvider(requireActivity())
            .get(ItemsListingsViewModel::class.java)
        _binding = FragmentItemListingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemListingsAdapter()
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findFirstVisibleItemPosition() in 0..1) {
                    binding.recyclerView.smoothScrollToPosition(0)
                }
            }
        })
        binding.recyclerView.addItemDecoration(
            UnifiedItemDecoration(requireContext(), R.dimen.store_items_spacing)
        )
        binding.recyclerView.adapter = adapter

        viewModel.itemsLiveData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}