package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wayne.taipeiair.CityAdapter
import com.wayne.taipeiair.databinding.FragmentBadBinding
import com.wayne.taipeiair.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BadFragment : Fragment() {
    private lateinit var mFragmentBadBinding: FragmentBadBinding

    private val mMainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private var mCityAdapter = CityAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFragmentBadBinding = FragmentBadBinding.inflate(inflater, container, false)
        return mFragmentBadBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragmentBadBinding.apply {
            mMainViewModel.categoryFilterLiveData.observe(viewLifecycleOwner) {
                mCityAdapter.value = it

                mMainViewModel.badLiveData.observe(viewLifecycleOwner) {
                    mCityAdapter.apply {
                        submitList(emptyList())

                        lifecycleScope.launch {
                            delay(100)
                            submitList(it)
                        }

                        tvNoData.visibility =
                            if (it.isEmpty()) {
                                View.VISIBLE
                            } else {
                                View.GONE
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