package com.example.testapplicationwithconcatadapter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.example.testapplicationwithconcatadapter.R
import com.example.testapplicationwithconcatadapter.databinding.FragmentMainBinding
import com.example.testapplicationwithconcatadapter.ui.model.Featured
import com.example.testapplicationwithconcatadapter.ui.model.FeaturedItemAdapter
import com.example.testapplicationwithconcatadapter.ui.model.Regular
import com.example.testapplicationwithconcatadapter.ui.model.RegularItemAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@OptIn(InternalCoroutinesApi::class)
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private val featuredItemAdapter = FeaturedItemAdapter(this::onItemSelected)
    private val regularItemAdapter = RegularItemAdapter(this::onItemSelected)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val concatAdapter = ConcatAdapter(
            featuredItemAdapter,
            regularItemAdapter
        )

        _binding?.recyclerView?.adapter = concatAdapter

        lifecycleScope.launchWhenResumed {
            viewModel.itemList.collect {
                it.filterIsInstance<Featured>().takeIf { it.isNotEmpty() }?.let { it ->
                    Timber.d("featured item list $it")
                    featuredItemAdapter.submitList(it)
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.itemList.collect { itemList ->
                itemList.filterIsInstance<Regular>().takeIf { it.isNotEmpty() }?.let {
                    Timber.d("regular item list $it")
                    regularItemAdapter.submitList(it)
                }
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private fun onItemSelected(id: String) {
        viewModel.itemSelected(id)
    }

}