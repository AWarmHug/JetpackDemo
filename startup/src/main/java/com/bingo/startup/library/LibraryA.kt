package com.bingo.startup.library

import android.content.Context
import android.util.Log
import android.widget.Toast


class LibraryA {
    companion object{
        fun init(context: Context){
            Log.d("LibraryA", "init: LibraryA")
        }
    }
}