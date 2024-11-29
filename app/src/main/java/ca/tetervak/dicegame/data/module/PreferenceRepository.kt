package ca.tetervak.dicegame.data.module

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    fun getNumberOfDiceFlow(): Flow<Int>

    suspend fun saveNumberOfDice(numberOfDice: Int)
}