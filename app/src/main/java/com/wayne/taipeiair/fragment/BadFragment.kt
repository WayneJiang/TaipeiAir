package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wayne.taipeiair.databinding.FragmentBadBinding

class BadFragment : Fragment() {
    private lateinit var mFragmentBadBinding: FragmentBadBinding

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
        }
    }
}