package ca.tetervak.dicegame.data.service

import ca.tetervak.dicegame.domain.RollData


interface RollerService {
    fun getRollData(numberOfDice: Int): RollData
}