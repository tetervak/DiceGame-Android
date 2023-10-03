package ca.tetervak.dicegame.ui.roller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.dicegame.domain.GetRollDataUseCase
import ca.tetervak.dicegame.domain.RollData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val getRollDataUseCase: GetRollDataUseCase
) : ViewModel() {

    private val _uiState: MutableState<RollerUiState> =
        mutableStateOf(RollerUiState.NotRolled)
    val uiState: State<RollerUiState> = _uiState

    val numberOfDice: StateFlow<Int> = MutableStateFlow(2)

    fun onRoll() = viewModelScope.launch {

        _uiState.value = RollerUiState.Loading

        //fake loading delay, 2 seconds
        delay(2000)

        try{
            val rollData: RollData = getRollDataUseCase(numberOfDice.value)
            _uiState.value =
                RollerUiState.Rolled(rollData = rollData, date = Date())
        } catch (e: IOException){
            _uiState.value = RollerUiState.Error
            e.printStackTrace()
        }

    }

    fun onReset() = viewModelScope.launch {
        _uiState.value = RollerUiState.NotRolled
    }

}