package com.bingo.startup

import android.content.Context
import androidx.startup.Initializer
import com.bingo.startup.library.LibraryA
import com.bingo.startup.library.LibraryC

class LibraryCInitializer : Initializer<LibraryC> {
    override fun create(context: Context): LibraryC {
        LibraryC.init(context)
        return LibraryC()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(LibraryBInitializer::class.java)
    }
}