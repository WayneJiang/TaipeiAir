package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.wayne.taipeiair.CityAdapter
import com.wayne.taipeiair.databinding.FragmentGoodBinding
import com.wayne.taipeiair.repository.entity.CityEntity
import com.wayne.taipeiair.viewmodel.GoodViewModel

class GoodFragment : Fragment() {
    private lateinit var mFragmentGoodBinding: FragmentGoodBinding

    private var mCityAdapter = CityAdapter(1)

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
            GoodViewModel.goodLiveData.observe(viewLifecycleOwner) {
                mCityAdapter.submitList(it)
            }

            viewRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext())

                adapter =
                    mCityAdapter.apply {
                        submitList(emptyList())
                    }
            }
        }
    }
}