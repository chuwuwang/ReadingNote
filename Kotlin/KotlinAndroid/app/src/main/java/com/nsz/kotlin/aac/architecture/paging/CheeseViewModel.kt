package com.nsz.kotlin.aac.architecture.paging

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.nsz.kotlin.ux.common.executors.ioThread

class CheeseViewModel(app: Application) : AndroidViewModel(app) {

    private val cheeseDao = CheeseDatabase.get(app).cheeseDao()
    private val config: PagedList.Config = Config(pageSize = 60)

    // normal
    // private val factory = cheeseDao.allCheesesByName()
    // val allCheeseList = LivePagedListBuilder(factory, config).build()

    // for ktx
    val allCheeseList = cheeseDao.allCheesesByName().toLiveData(config)

    fun insert(text: String) = ioThread {
        val cheese = Cheese(name = text)
        cheeseDao.insert(cheese)
    }

    fun remove(cheese: Cheese) = ioThread {
        cheeseDao.delete(cheese)
    }

}