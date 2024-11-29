package ca.tetervak.dicegame.data.impl

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import ca.tetervak.dicegame.data.module.PreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): PreferenceRepository {

    private companion object {
        val NUMBER_OF_DICE = intPreferencesKey("number_of_dice")
        const val TAG = "UserSettingsRepo"
    }

    private val numberOfDice: Flow<Int> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[NUMBER_OF_DICE] ?: 3
        }

    override fun getNumberOfDiceFlow(): Flow<Int> = numberOfDice

    override suspend fun saveNumberOfDice(numberOfDice: Int) {
        dataStore.edit { preferences ->
            preferences[NUMBER_OF_DICE] = numberOfDice
        }
    }
}