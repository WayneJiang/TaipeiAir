package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wayne.taipeiair.CityAdapter
import com.wayne.taipeiair.databinding.FragmentGoodBinding
import com.wayne.taipeiair.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GoodFragment : Fragment() {
    private lateinit var mFragmentGoodBinding: FragmentGoodBinding

    private val mMainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private var mCityAdapter = CityAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFragmentGoodBinding = FragmentGoodBinding.inflate(inflater, container, false)
        return mFragmentGoodBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragmentGoodBinding.apply {
            mMainViewModel.categoryFilterLiveData.observe(viewLifecycleOwner) {
                mCityAdapter.value = it

                mMainViewModel.goodLiveData.observe(viewLifecycleOwner) {
                    mCityAdapter.apply {
                        submitList(emptyList())

                        lifecycleScope.launch {
                            delay(100)
                            submitList(it)
                        }
                    }
                }
            }

            viewRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext())

                adapter = mCityAdapter
            }
        }
    }
}