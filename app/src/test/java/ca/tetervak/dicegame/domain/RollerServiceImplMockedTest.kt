package ca.tetervak.dicegame.domain

import ca.tetervak.dicegame.data.impl.RollerServiceImpl
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock


class RollerServiceImplMockedTest{

    // Mocking the Random to produce 1,2,3,4,5,6,1,2,3,4,5,6,1,2, etc.
    private var count = 0
    private val mockRandom: Random = mock<Random> {
        on { nextInt(1, 7) } doAnswer {
            1 + (count++) % 6
        }
    }
    private val rollerService: RollerServiceImpl = RollerServiceImpl(mockRandom)

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
}