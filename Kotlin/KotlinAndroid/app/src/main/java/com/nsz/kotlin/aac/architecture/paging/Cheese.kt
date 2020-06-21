package com.nsz.kotlin.aac.architecture.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cheese(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String = ""
)