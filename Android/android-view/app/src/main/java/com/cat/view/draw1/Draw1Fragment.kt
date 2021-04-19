package com.cat.view.draw1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.cat.view.R

class Draw1Fragment : Fragment() {

    companion object {

        private const val TAG = "Draw1Fragment"

        fun newInstance(@LayoutRes layoutRes: Int): Draw1Fragment {
            val fragment = Draw1Fragment()
            val bundle = Bundle()
            bundle.putInt("layoutRes", layoutRes)
            fragment.arguments = bundle
            return fragment
        }

    }

    private val layoutResource: Int by lazy {
        val bundle = arguments
        if (bundle != null) {
            bundle.getInt("layoutRes")
        } else {
            Log.e(TAG, "arguments is null")
            R.layout.fragment_draw1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_draw1, container, false)
        val viewStub = view.findViewById<ViewStub>(R.id.view_stub)
        viewStub.layoutResource = layoutResource
        viewStub.inflate()
        return view
    }

}