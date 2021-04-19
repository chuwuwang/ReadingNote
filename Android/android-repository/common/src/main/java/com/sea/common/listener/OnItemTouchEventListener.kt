package com.sea.common.listener

interface OnItemTouchEventListener {

    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemRemove(position: Int)

}