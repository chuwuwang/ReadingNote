package com.sea.common.http.interceptor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sea.common.R
import com.sea.common.databinding.HttpFragmentLogsResponseBindingImpl
import com.sea.common.http.interceptor.HttpEntity

class HttpLogsResponseFragment : Fragment() {

    companion object {

        fun create(entity: HttpEntity): HttpLogsResponseFragment {
            val fragment = HttpLogsResponseFragment()
            val bundle = Bundle()
            bundle.putSerializable("entity", entity);
            fragment.arguments = bundle
            return fragment
        }

    }

    private val entity by lazy {
        val bundle = arguments
        if (bundle != null) {
            bundle.getSerializable("entity") as HttpEntity
        } else {
            HttpEntity()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<HttpFragmentLogsResponseBindingImpl>(
            inflater,
            R.layout.http_fragment_logs_response,
            container,
            false
        )
        val view = binding.root
        binding.entity = entity
        return view
    }

}