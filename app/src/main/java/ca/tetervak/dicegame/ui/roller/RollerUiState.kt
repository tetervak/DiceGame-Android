package ca.tetervak.dicegame.ui.roller

import ca.tetervak.dicegame.domain.RollData
import java.util.Date

sealed interface RollerUiState {

    data class Rolled(
        val rollData: RollData,
        val date: Date,
    ) : RollerUiState

    object NotRolled: RollerUiState
}