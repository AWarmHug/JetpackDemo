package com.bingo.startup

import android.content.Context
import androidx.startup.Initializer
import com.bingo.startup.library.LibraryA
import com.bingo.startup.library.LibraryB

class LibraryBInitializer : Initializer<LibraryB> {
    override fun create(context: Context): LibraryB {
        LibraryB.init(context)
        return LibraryB()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}