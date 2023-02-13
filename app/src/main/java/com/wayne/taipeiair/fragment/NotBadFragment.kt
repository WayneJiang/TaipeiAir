package com.wayne.taipeiair.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wayne.taipeiair.databinding.FragmentNotBadBinding

class NotBadFragment : Fragment() {
    private lateinit var mFragmentNotBadBinding: FragmentNotBadBinding

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
        }
    }
}