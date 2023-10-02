package ca.tetervak.dicegame.data.remote

data class RemoteRollData(
    val values: List<Int>,
    val total: Int,
    val numberOfDice: Int
)
