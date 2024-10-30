package com.example.enishop.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {
    val Context.datastore by preferencesDataStore("settings")
    val DARK_MODE_KEY = booleanPreferencesKey("DARK_MODE_KEY")


    fun isDarkModeActivated(context: Context): Flow<Boolean> {
        return context.datastore.data.map { pref ->
            pref[DARK_MODE_KEY] ?: false
        }

    }
   suspend fun setDarkMode(context: Context, isActivated : Boolean){
        context.datastore.edit {pref ->
            Log.i("DATASTORE", "setDarkMode: $isActivated")
            pref[DARK_MODE_KEY] = isActivated
        }
    }
}