package ca.tetervak.dicegame.data.repository

import ca.tetervak.dicegame.data.remote.RemoteRollData
import ca.tetervak.dicegame.data.remote.RollDataApi
import ca.tetervak.dicegame.domain.RollData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RollDataRepositoryImpl @Inject constructor(
    private val rollDataApi: RollDataApi
): RollDataRepository {
    override suspend fun getRollData(numberOfDice: Int): RollData =
        withContext(Dispatchers.IO){
            //fake loading delay, 2 seconds
            //delay(2000)

           rollDataApi.getRollData(numberOfDice).toRollData()
        }
}

fun RemoteRollData.toRollData():RollData = RollData(this.values)
