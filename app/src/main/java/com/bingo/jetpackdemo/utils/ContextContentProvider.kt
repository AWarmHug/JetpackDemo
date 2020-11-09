package com.bingo.jetpackdemo.utils

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.net.Uri

class ContextContentProvider : ContentProvider() {

    companion object {
        @JvmField
        var appContext: Context? = null
    }

    override fun onCreate(): Boolean {
        appContext = context
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ) = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

}