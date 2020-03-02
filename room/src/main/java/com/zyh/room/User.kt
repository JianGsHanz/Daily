package com.zyh.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Time:2020/3/2
 *Author:zyh
 *Description:Room表结构-实体类与数据库表column进行映射
 */
@Entity(tableName = "user")
data class User (
    @ColumnInfo(name = "user_name")
    var userName: String = "",
    @ColumnInfo(name = "user_sex")
    var userSex: String = "",
    @ColumnInfo(name = "user_age")
    var userAge: Int,
    @ColumnInfo(name = "user_image")
    var userImage: String,
    @PrimaryKey(autoGenerate = true)
    var userId:Int = 0
)