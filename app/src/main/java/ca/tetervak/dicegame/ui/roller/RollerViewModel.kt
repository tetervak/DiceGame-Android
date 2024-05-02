package ca.tetervak.dicegame.ui.roller

import androidx.lifecycle.ViewModel
import ca.tetervak.dicegame.domain.RollData
import ca.tetervak.dicegame.domain.RollerService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

class RollerViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<RollerUiState> =
        MutableStateFlow(RollerUiState.NotRolled(numberOfDice = 3))
    val uiState: StateFlow<RollerUiState> = _uiState

    private val rollerService: RollerService = RollerService()

    fun onRoll() {
        val rollData: RollData = rollerService.getRollData(uiState.value.numberOfDice)
        _uiState.value = RollerUiState.Rolled(
            rollData = rollData, date = Date()
        )
    }

    fun onReset() {
        _uiState.value = RollerUiState.NotRolled(uiState.value.numberOfDice)
    }

    fun onChangeOfNumberOfDice(newNumberOfDice: Int) {
        _uiState.value = RollerUiState.NotRolled(newNumberOfDice)
    }

}