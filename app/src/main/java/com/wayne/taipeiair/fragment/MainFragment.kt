package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.wayne.taipeiair.R
import com.wayne.taipeiair.databinding.FragmentMainBinding
import com.wayne.taipeiair.repository.entity.CityEntity
import com.wayne.taipeiair.viewmodel.GoodViewModel
import com.wayne.taipeiair.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var mFragmentMainBinding: FragmentMainBinding

    private val mMainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mFragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragmentMainBinding.apply {
            viewPager.adapter =
                PageAdapter(requireActivity().supportFragmentManager, lifecycle)

            TabLayoutMediator(layoutTab, viewPager) { tab, position ->
                tab.text =
                    when (position) {
                        0 -> {
                            getString(R.string.good)
                        }
                        1 -> {
                            getString(R.string.not_bad)
                        }
                        else -> {
                            getString(R.string.bad)
                        }
                    }
            }.attach()

            btnYearMonth.setOnClickListener {

            }

            btnCategory.setOnClickListener {
                findNavController().navigate(
                    R.id.action_to_filter, bundleOf(
                        FilterDialogFragment.KEY_FILTER to FilterDialogFragment.FILTER_CATEGORY
                    )
                )
            }
        }

        queryData()
    }

    inner class PageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        private val fragments = arrayListOf(
            GoodFragment(),
            NotBadFragment(),
            BadFragment()
        )

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    private fun queryData() =
        mMainViewModel.queryCitiesByYearMonth("111年 10月").observe(viewLifecycleOwner) {
            GoodViewModel.goodLiveData.postValue(it)
        }
}