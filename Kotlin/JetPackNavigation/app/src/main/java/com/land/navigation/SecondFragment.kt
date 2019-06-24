package com.land.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private lateinit var arg1: String
    private lateinit var arg2: String

    companion object {

        fun newInstance() = SecondFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.second_fragment, container, false)
        arguments?.let {
            arg1 = it["arg1"] as String
            arg2 = it["arg2"] as String
        }
        val textView = view.findViewById<TextView>(R.id.tv_main)
        textView.text = "$arg1 : $arg2"
        return view
    }


}