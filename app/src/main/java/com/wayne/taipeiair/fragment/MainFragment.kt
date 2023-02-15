package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.wayne.taipeiair.R
import com.wayne.taipeiair.databinding.FragmentMainBinding
import com.wayne.taipeiair.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var mFragmentMainBinding: FragmentMainBinding

    private val mMainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

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
            viewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    mMainViewModel.filterCity(newText)
                    return true
                }
            })

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
                findNavController().navigate(
                    R.id.action_to_filter, bundleOf(
                        FilterDialogFragment.KEY_FILTER to FilterDialogFragment.FILTER_YEAR_MONTH
                    )
                )
            }

            btnCategory.setOnClickListener {
                findNavController().navigate(
                    R.id.action_to_filter, bundleOf(
                        FilterDialogFragment.KEY_FILTER to FilterDialogFragment.FILTER_CATEGORY
                    )
                )
            }

            mMainViewModel.yearMonthFilterLiveData.observe(viewLifecycleOwner) {
                mMainViewModel.queryCitiesByYearMonth(it)
            }

            mMainViewModel.goodLiveData.observe(viewLifecycleOwner) {
                layoutTab.getTabAt(0)?.orCreateBadge?.apply {
                    horizontalOffset = -20
                    number = it.size
                }
            }

            mMainViewModel.notBadLiveData.observe(viewLifecycleOwner) {
                layoutTab.getTabAt(1)?.orCreateBadge?.apply {
                    horizontalOffset = -20
                    number = it.size
                }
            }

            mMainViewModel.badLiveData.observe(viewLifecycleOwner) {
                layoutTab.getTabAt(2)?.orCreateBadge?.apply {
                    horizontalOffset = -20
                    number = it.size
                }
            }
        }
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
}