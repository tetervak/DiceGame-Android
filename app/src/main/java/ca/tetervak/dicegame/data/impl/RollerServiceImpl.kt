package ca.tetervak.dicegame.data.impl

import ca.tetervak.dicegame.data.RollerService
import ca.tetervak.dicegame.domain.RollData
import javax.inject.Inject
import kotlin.random.Random


class RollerServiceImpl(
    private val random: Random
) : RollerService {

    @Inject
    constructor() : this(Random.Default)

    override fun getRollData(numberOfDice: Int): RollData {
        if (numberOfDice > 0) {
            val list: List<Int> =
                buildList(capacity = numberOfDice) {
                    repeat(numberOfDice) {
                        add(random.nextInt(from = 1, until = 7))
                    }
                }
            return RollData(list)
        } else {
            throw IllegalArgumentException("Illegal numberOfDice = $numberOfDice")
        }
    }
}