package ca.tetervak.dicegame.data.repository

import ca.tetervak.dicegame.data.service.RollerService
import ca.tetervak.dicegame.domain.RollData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreviewRepository @Inject constructor(
    private val rollerService: RollerService
): RollDataRepository {
    override suspend fun getRollData(numberOfDice: Int): RollData =
        withContext(Dispatchers.IO){
            rollerService.getRollData(numberOfDice)
        }

}