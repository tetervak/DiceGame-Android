package ca.tetervak.dicegame.domain

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RollDataTest{

    companion object {
        @JvmStatic
        @BeforeAll
        fun startAll() {
            println("--- Starting RollData Tests ---")
        }

        @JvmStatic
        @AfterAll
        fun endAll() {
            println("--- End of RollData Tests ---")
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


    @Test
    fun getTotal() {
        println("test getTotal()")
        val values = listOf(4,3,5)
        println("values = $values")
        val total = values.sum()
        println("total = $total")
        val rollData = RollData(values)
        println("rollData = $rollData")
        assertEquals(total, rollData.total)
    }

    @Test
    fun getNumberOfDice() {
        println("test numberOfDice()")
        val values = listOf(4,3,5,6)
        val numberOfDice = values.size
        println("numberOfDice = $numberOfDice")
        val rollData = RollData(values)
        println("rollData = $rollData")
        assertEquals(numberOfDice, rollData.numberOfDice)
    }
}