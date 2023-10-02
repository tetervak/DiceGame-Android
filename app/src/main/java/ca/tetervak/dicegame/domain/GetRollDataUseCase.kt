package ca.tetervak.dicegame.domain

import ca.tetervak.dicegame.data.repository.RollDataRepository
import ca.tetervak.dicegame.data.service.RollerService
import javax.inject.Inject

class GetRollDataUseCase @Inject constructor(
    private val repository: RollDataRepository
) {
    suspend operator fun invoke(numberOfDice: Int) =
        repository.getRollData(numberOfDice)
}