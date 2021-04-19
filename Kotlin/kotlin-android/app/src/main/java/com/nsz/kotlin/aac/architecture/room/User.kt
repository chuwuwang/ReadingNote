package com.nsz.kotlin.aac.architecture.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @PrimaryKey var uId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "first_name") var firstName: String = "",
    @ColumnInfo(name = "last_name") var lastName: String = ""
)