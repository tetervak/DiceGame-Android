package ca.tetervak.dicegame.data.service

import ca.tetervak.dicegame.domain.RollData
import kotlin.random.Random

class RollerServiceImpl(
    private val random: Random = Random.Default
): RollerService {

    override fun getRollData(numberOfDice: Int): RollData {
        if(numberOfDice > 0){
            val list: List<Int> =
                buildList(capacity = numberOfDice) {
                    repeat(numberOfDice){
                        add(random.nextInt(from = 1, until = 7))
                    }
                }
            return RollData(list)
        } else {
            throw IllegalArgumentException("Illegal nnumberOfDice = $numberOfDice")
        }
    }
}