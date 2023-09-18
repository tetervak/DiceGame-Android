package ca.tetervak.dicegame.domain

import ca.tetervak.dicegame.data.service.RollerService
import javax.inject.Inject

class GetRollDataUseCase @Inject constructor(
    private val rollerService: RollerService
) {
    operator fun invoke(numberOfDice: Int) = rollerService.getRollData(numberOfDice)
}