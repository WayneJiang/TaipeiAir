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
import com.wayne.taipeiair.databinding.FragmentNotBadBinding
import com.wayne.taipeiair.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotBadFragment : Fragment() {
    private lateinit var mFragmentNotBadBinding: FragmentNotBadBinding

    private val mMainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private var mCityAdapter = CityAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFragmentNotBadBinding = FragmentNotBadBinding.inflate(inflater, container, false)
        return mFragmentNotBadBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragmentNotBadBinding.apply {
            mMainViewModel.categoryFilterLiveData.observe(viewLifecycleOwner) {
                mCityAdapter.value = it

                mMainViewModel.notBadLiveData.observe(viewLifecycleOwner) {
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