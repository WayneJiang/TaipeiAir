package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.wayne.taipeiair.R
import com.wayne.taipeiair.databinding.DialogFragmentFilterBinding
import com.wayne.taipeiair.databinding.LayoutChipBinding
import com.wayne.taipeiair.viewmodel.MainViewModel

class FilterDialogFragment : BottomSheetDialogFragment() {
    companion object {
        internal const val KEY_FILTER = "KEY_FILTER"

        internal const val FILTER_YEAR_MONTH = "FILTER_YEAR_MONTH"
        internal const val FILTER_CATEGORY = "FILTER_CATEGORY"
    }

    private lateinit var mDialogFragmentFilterBinding: DialogFragmentFilterBinding

    private val mMainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mDialogFragmentFilterBinding =
            DialogFragmentFilterBinding.inflate(inflater, container, false)
        return mDialogFragmentFilterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isCancelable = false

        mDialogFragmentFilterBinding.apply {
            btnConfirm.setOnClickListener {
                updateFilter()

                dismiss()
            }

            requireArguments().apply {
                groupChip.apply {
                    when (getString(KEY_FILTER)) {
                        FILTER_YEAR_MONTH -> {
                            isSingleSelection = false

                            mMainViewModel.queryYearMonthsInRecordAsync()
                                .observe(viewLifecycleOwner) {
                                    removeAllViews()

                                    it.forEach { item ->
                                        addView(
                                            LayoutChipBinding.inflate(
                                                LayoutInflater.from(
                                                    requireContext()
                                                )
                                            ).root.apply {
                                                id = ViewGroup.generateViewId()
                                                text = item

                                                layoutParams =
                                                    ViewGroup.LayoutParams(
                                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                                    )

                                                mMainViewModel.yearMonthFilterLiveData.observe(
                                                    viewLifecycleOwner
                                                ) { selected ->
                                                    isChecked = selected.contains(item)
                                                }
                                            }
                                        )
                                    }
                                }
                        }
                        FILTER_CATEGORY -> {
                            isSingleSelection = true

                            mMainViewModel.categoryFilterLiveData.observe(viewLifecycleOwner) {
                                removeAllViews()

                                for (chip in 1..7) {
                                    addView(
                                        LayoutChipBinding.inflate(
                                            LayoutInflater.from(
                                                requireContext()
                                            )
                                        ).root.apply {
                                            id = ViewGroup.generateViewId()
                                            text =
                                                when (chip) {
                                                    1 -> {
                                                        getString(R.string.value_1)
                                                    }
                                                    2 -> {
                                                        getString(R.string.value_2)
                                                    }
                                                    3 -> {
                                                        getString(R.string.value_3)
                                                    }
                                                    4 -> {
                                                        getString(R.string.value_4)
                                                    }
                                                    5 -> {
                                                        getString(R.string.value_5)
                                                    }
                                                    6 -> {
                                                        getString(R.string.value_6)
                                                    }
                                                    else -> {
                                                        getString(R.string.value_7)
                                                    }
                                                }

                                            isChecked = (chip == it)

                                            layoutParams =
                                                ViewGroup.LayoutParams(
                                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                                )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            ?.apply {
                layoutParams.height = (resources.displayMetrics.heightPixels * 0.5f).toInt()

                BottomSheetBehavior.from(this).apply {
                    peekHeight = (resources.displayMetrics.heightPixels * 0.5f).toInt()
                    isDraggable = false
                }
            }
    }

    private fun updateFilter() =
        mDialogFragmentFilterBinding.apply {
            requireArguments().apply {
                when (getString(KEY_FILTER)) {
                    FILTER_YEAR_MONTH -> {
                        groupChip.apply {
                            val selected = mutableListOf<String>()
                            checkedChipIds.forEach {
                                selected.add(groupChip.findViewById<Chip>(it).text.toString())
                            }

                            mMainViewModel.yearMonthFilterLiveData.postValue(selected)
                        }
                    }
                    FILTER_CATEGORY -> {
                        mMainViewModel.categoryFilterLiveData.postValue(
                            when (groupChip.findViewById<Chip>(groupChip.checkedChipId).text) {
                                getString(R.string.value_1) -> {
                                    1
                                }
                                getString(R.string.value_2) -> {
                                    2
                                }
                                getString(R.string.value_3) -> {
                                    3
                                }
                                getString(R.string.value_4) -> {
                                    4
                                }
                                getString(R.string.value_5) -> {
                                    5
                                }
                                getString(R.string.value_6) -> {
                                    6
                                }
                                else -> {
                                    7
                                }
                            }
                        )
                    }
                }
            }
        }
}