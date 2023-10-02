package ca.tetervak.dicegame.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RollDataApi {

    @GET("roll-dice")
    suspend fun getRollData(@Query("numberOfDice") numberOfDice: Int
    ): RemoteRollData
}