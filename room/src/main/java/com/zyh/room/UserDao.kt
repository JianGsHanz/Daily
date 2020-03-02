package com.zyh.room

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *Time:2020/3/2
 *Author:zyh
 *Description:数据访问对象,实现具体的增删改查
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //有冲突是直接替换
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: User)

    @Update
    suspend fun updateUser(vararg user:User) //此方法根据Id来更新

    @Query("update user set user_name = :userName,user_sex = :userSex where userId = :oldId")
    suspend fun update(oldId:Int = 0,userName: String = "",userSex: String = "")

    @Query("delete from user where userId = :id")
    suspend fun deleteById(id: Int)

    @Query("delete from user")
    suspend fun deleteAll()

    @Query("select * from user where userId =:id or user_name = :userAny or user_sex = :userAny")
    fun queryByField(id: Int,userAny: String) : LiveData<MutableList<User>>

    @Query("select * from user")
    fun queryAll(): LiveData<MutableList<User>> //LiveData默认异步,无须加挂起suspend
}