package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.wayne.taipeiair.R
import com.wayne.taipeiair.databinding.DialogFragmentFilterBinding

class FilterDialogFragment : BottomSheetDialogFragment() {
    companion object {
        internal const val KEY_FILTER = "KEY_FILTER"

        internal const val FILTER_YEAR_MONTH = "FILTER_YEAR_MONTH"
        internal const val FILTER_CATEGORY = "FILTER_CATEGORY"
    }

    private lateinit var mDialogFragmentFilterBinding: DialogFragmentFilterBinding

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
            requireArguments().apply {
                when (getString(KEY_FILTER)) {
                    FILTER_YEAR_MONTH -> {
                    }
                    FILTER_CATEGORY -> {
                        val chips = mutableListOf<Int>()
                        groupChip.apply {
                            var generatedId = ViewGroup.generateViewId()
                            addView(
                                Chip(requireContext()).apply {
                                    id = generatedId
                                    text = getString(R.string.value_1)
                                    isChecked = true
                                    isCheckable = true
                                    isClickable = true
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                }
                            )
                            chips.add(generatedId)

                            generatedId = ViewGroup.generateViewId()
                            addView(
                                Chip(requireContext()).apply {
                                    id = generatedId
                                    isCheckable = true
                                    isClickable = true
                                    text = getString(R.string.value_2)
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                }
                            )
                            chips.add(generatedId)

                            generatedId = ViewGroup.generateViewId()
                            addView(
                                Chip(requireContext()).apply {
                                    id = generatedId
                                    isCheckable = true
                                    isClickable = true
                                    text = getString(R.string.value_3)
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                }
                            )
                            chips.add(generatedId)

                            generatedId = ViewGroup.generateViewId()
                            addView(
                                Chip(requireContext()).apply {
                                    id = generatedId
                                    isCheckable = true
                                    isClickable = true
                                    text = getString(R.string.value_4)
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                }
                            )
                            chips.add(generatedId)

                            generatedId = ViewGroup.generateViewId()
                            addView(
                                Chip(requireContext()).apply {
                                    id = generatedId
                                    isCheckable = true
                                    isClickable = true
                                    text = getString(R.string.value_5)
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                }
                            )
                            chips.add(generatedId)

                            generatedId = ViewGroup.generateViewId()
                            addView(
                                Chip(requireContext()).apply {
                                    id = generatedId
                                    isCheckable = true
                                    isClickable = true
                                    text = getString(R.string.value_6)
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                }
                            )
                            chips.add(generatedId)

                            generatedId = ViewGroup.generateViewId()
                            addView(
                                Chip(requireContext()).apply {
                                    id = generatedId
                                    isCheckable = true
                                    isClickable = true
                                    text = getString(R.string.value_7)
                                    layoutParams = ViewGroup.LayoutParams(
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

    private fun setResult() =
        requireArguments().apply {

        }
}