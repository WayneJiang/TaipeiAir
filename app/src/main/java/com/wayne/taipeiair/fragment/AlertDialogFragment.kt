package com.wayne.taipeiair.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wayne.taipeiair.R

class AlertDialogFragment : AppCompatDialogFragment() {
    companion object {
        const val KEY_REQUEST = "KEY_REQUEST"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(
            requireContext()
        ).apply {
            setTitle(getString(R.string.app_name))
            setMessage(getString(R.string.connection_error))
            setPositiveButton(getString(R.string.retry)) { _, _ ->
                setFragmentResult(KEY_REQUEST, bundleOf())
            }
        }.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}