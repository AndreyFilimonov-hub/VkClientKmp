package com.filimonov.vkclientkmp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.filimonov.vkclientkmp.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "onboard_launch")

class OnboardingRepositoryImpl(private val context: Context) :
    OnboardingRepository {

    private val completedKey = booleanPreferencesKey("onboarding_completed")

    override suspend fun isOnboardingCompleted(): Boolean {
        return context.dataStore.data.first()[completedKey] ?: false
    }

    override suspend fun setOnboardingCompleted() {
        context.dataStore.edit { preferences ->
            preferences[completedKey] = true
        }
    }
}