package com.sea.common.listener

import android.view.View
import android.view.ViewGroup

interface OnItemClickListener {

    fun onItemClick(parent: ViewGroup, view: View, position: Int)

}