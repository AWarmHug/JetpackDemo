package com.bingo.startup

import android.content.Context
import androidx.startup.Initializer
import com.bingo.startup.library.LibraryA
import com.bingo.startup.library.LibraryD

class LibraryDInitializer : Initializer<LibraryD> {
    override fun create(context: Context): LibraryD {
        LibraryD.init(context)
        return LibraryD()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}