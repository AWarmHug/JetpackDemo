package com.bingo.startup

import android.content.Context
import androidx.startup.Initializer
import com.bingo.startup.library.LibraryA

class LibraryAInitializer : Initializer<LibraryA> {
    override fun create(context: Context): LibraryA {
        LibraryA.init(context)
        return LibraryA()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}