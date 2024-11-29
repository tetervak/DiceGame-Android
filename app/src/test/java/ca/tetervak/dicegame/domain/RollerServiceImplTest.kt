package ca.tetervak.dicegame.domain

import ca.tetervak.dicegame.data.impl.RollerServiceImpl
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.random.Random

class RollerServiceImplTest {

    // specifying the seed makes the "random" sequence always the same
    private val random: Random = Random(seed = 10)
    private val rollerService: RollerServiceImpl = RollerServiceImpl(random)

    @Before
    fun setUp() {
        println("--- testing case ---")
    }

    @After
    fun tearDown() {
        println("--- ------- ---- ---")
    }

    @Test
    fun getRollData(){
        for(numberOfDice: Int in 1..4){
            for(repetition: Int in 1..5){
                println("test getNumberOfDice($numberOfDice) repetition $repetition")
                val rollData = rollerService.getRollData(numberOfDice)
                println("rollData = $rollData")
                assertEquals(numberOfDice, rollData.numberOfDice)
                val values = rollData.values
                for(value in values){
                    assertTrue(value > 0)
                    assertTrue(value <= 6)
                }
            }
        }
    }

    @Test
    fun getRollData_ZeroDice(){
        println("test throwing exception for getRollData(numberOfDice = 0)")
        assertThrows(IllegalArgumentException::class.java) {
            val rollData = rollerService.getRollData(numberOfDice = 0)
            println("rollData = $rollData")
        }
    }
}