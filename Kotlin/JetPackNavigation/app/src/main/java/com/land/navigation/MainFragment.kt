package com.land.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class MainFragment : Fragment() {

    companion object {

        fun newInstance() = MainFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        view.findViewById<Button>(R.id.btn_setting).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_settings_activity)
        }

        // 参数跳转
        view.findViewById<Button>(R.id.btn_second).setOnClickListener {
            val bundle = bundleOf("arg1" to "很高兴遇见你", "arg2" to "hello world")
            Navigation.findNavController(view).navigate(R.id.action_second_fragment, bundle)
        }
        return view
    }


}