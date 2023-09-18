package ca.tetervak.dicegame.data.service

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import kotlin.random.Random

class RollerServiceImplTest {

    companion object {

        @JvmStatic
        var rollerService = RollerServiceImpl(Random(2))

        @JvmStatic
        @BeforeAll
        fun startAll() {
            println("--- Starting RollerServiceImpl Tests ---")
        }

        @JvmStatic
        @AfterAll
        fun endAll() {
            println("--- End of RollerServiceImpl Tests ---")
        }
    }

    @BeforeEach
    fun setUp() {
        println("--- testing case ---")
    }

    @AfterEach
    fun tearDown() {
        println("--- ------- ---- ---")
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 5])
    fun getRollData(numberOfDice: Int) {
        println("test getNumberOfDice($numberOfDice)")
        for(rollCount in 1..3){
            val roll = rollerService.getRollData(numberOfDice)
            println("roll $rollCount of $numberOfDice dice = $roll")
            assertEquals(numberOfDice, roll.numberOfDice)
            val values = roll.values
            for(value in values){
                assert(value > 0)
                assert(value <= 6)
            }
        }
    }

    @RepeatedTest(5)
    fun getRollData_repeated(info: RepetitionInfo){
        val numberOfDice = 3
        println("test getNumberOfDice($numberOfDice) repetition ${info.currentRepetition} of ${info.totalRepetitions}")
        val rollData = rollerService.getRollData(numberOfDice)
        println("rollData = $rollData")
        assertEquals(numberOfDice, rollData.numberOfDice)
        val values = rollData.values
        for(value in values){
            assert(value > 0)
            assert(value <= 6)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun getRollData_IllegalArg(numberOfDice: Int){
        println("test throwing exception for getRollData($numberOfDice)")
        assertThrows<IllegalArgumentException> {
            val rollData = rollerService.getRollData(numberOfDice)
            println("rollData = $rollData")
        }
    }
}