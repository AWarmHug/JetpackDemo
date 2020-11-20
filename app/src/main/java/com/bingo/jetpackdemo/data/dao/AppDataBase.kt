package com.bingo.jetpackdemo.data.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bingo.jetpackdemo.utils.ContextContentProvider.Companion.appContext

@Database(entities = [SearchContent::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(): AppDataBase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(
                        appContext!!.applicationContext,
                        AppDataBase::class.java,
                        "app.db"
                    )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AppDataBase
        }
    }


    abstract fun searchContentDao(): SearchContentDao


}