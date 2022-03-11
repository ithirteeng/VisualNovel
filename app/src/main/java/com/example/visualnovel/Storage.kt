package com.example.visualnovel

import android.content.Context
import android.content.SharedPreferences

class Storage {
    companion object {
        const val STORAGE_NAME = "storage name"
    }

    private var settings: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var context: Context? = null

    fun init(mContext: Context) {
        context = mContext
        settings = context?.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        editor = settings?.edit()
    }

    fun makeSavedPreference(mContext: Context, key: String, nickname: String) {
        init(mContext)
        editor?.putString(key, nickname)
        editor?.commit()
    }

    fun takeSharedPreference(mContext: Context, key: String): String? {
        init(mContext)
        return settings?.getString(key, null)
    }

    fun clearSharedPreferences(mContext: Context) {
        init(mContext)
        editor?.clear()
    }
}