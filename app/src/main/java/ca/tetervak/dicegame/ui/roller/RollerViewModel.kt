package ca.tetervak.dicegame.ui.roller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.dicegame.domain.GetRollDataUseCase
import ca.tetervak.dicegame.domain.RollData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val getRollDataUseCase: GetRollDataUseCase
) : ViewModel() {

    private val _rollerUiState: MutableState<RollerUiState> =
        mutableStateOf(RollerUiState.NotRolled)
    val rollerUiState: State<RollerUiState> = _rollerUiState

    val numberOfDice: StateFlow<Int> = MutableStateFlow(3)

    fun onRoll() = viewModelScope.launch {
        val rollData: RollData = getRollDataUseCase(numberOfDice.value)

        _rollerUiState.value = RollerUiState.Rolled(
            rollData = rollData, date = Date()
        )
    }

    fun onReset() = viewModelScope.launch {
        _rollerUiState.value = RollerUiState.NotRolled
    }

}