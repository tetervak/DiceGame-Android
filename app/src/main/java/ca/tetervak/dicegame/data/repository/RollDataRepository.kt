package ca.tetervak.dicegame.data.repository

import ca.tetervak.dicegame.domain.RollData

interface RollDataRepository {
    suspend fun getRollData(numberOfDice: Int): RollData
}