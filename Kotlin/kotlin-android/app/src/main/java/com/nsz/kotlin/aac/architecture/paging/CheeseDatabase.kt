package com.nsz.kotlin.aac.architecture.paging

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nsz.kotlin.ux.common.CommonLog

@Database(
    entities = [Cheese::class],
    version = 1
)
abstract class CheeseDatabase : RoomDatabase() {

    abstract fun cheeseDao(): CheeseDao

    companion object {

        private var instance: CheeseDatabase ? = null

        @Synchronized
        fun get(context: Context): CheeseDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, CheeseDatabase::class.java, "cheese").addCallback(
                    object : Callback() {

                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CommonLog.e("onCreate CheeseDatabase")
                        }

                    }
                ).build()
            }
            return instance!!
        }

        private fun initDb(context: Context) {

        }

    }

}