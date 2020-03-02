package com.zyh.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 *Time:2020/3/2
 *Author:zyh
 *Description: 提供直接访问底层数据库实现，我们应该从里面拿到具体的Dao,进行数据库操作。
 */
@Database(entities = [User::class],version = 3)
abstract class RDataBase : RoomDatabase(){

    companion object{
        val INSTANCE :RDataBase by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
            Room.databaseBuilder(App.instance,RDataBase::class.java,"mydb.db")
//                .allowMainThreadQueries() //允许在UI线程操作数据库
//                .fallbackToDestructiveMigration() //版本更新不保留数据
                .addMigrations(Migration1To2(1,2),Migration2To3(2,3),Migration1To3(1,3)) //升级
                .build()
        }

        class Migration1To2(startVersion: Int, endVersion: Int) :Migration(startVersion, endVersion){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table user add column user_age INTEGER not null default 1")
            }
        }

        class Migration2To3(startVersion: Int, endVersion: Int) :Migration(startVersion, endVersion){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table user add column user_image TEXT  not null default ''")
            }
        }

        //跨版本升级
        class Migration1To3(startVersion: Int, endVersion: Int) :Migration(startVersion, endVersion){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""create table user_temp(userId INTEGER primary key AUTOINCREMENT not null ,
                    user_name TEXT not null default '',user_sex TEXT not null default '',
                    user_age INTEGER NOT NULL DEFAULT 1,user_image TEXT not null default '')""")
                database.execSQL("""
                    insert into user_temp(userId,user_name,user_sex) select userId,user_name,user_sex from user
                """)
                database.execSQL("drop table user")
                database.execSQL("alter table user_temp rename to user")
            }
        }
    }


    abstract fun getUserDao():UserDao


}