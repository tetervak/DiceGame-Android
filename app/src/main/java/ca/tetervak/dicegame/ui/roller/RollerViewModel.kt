package ca.tetervak.dicegame.ui.roller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.dicegame.data.module.PreferenceRepository
import ca.tetervak.dicegame.data.RollerService
import ca.tetervak.dicegame.domain.RollData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val rollerService: RollerService,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<RollerUiState> = MutableStateFlow(INIT_STATE)
    val uiState: StateFlow<RollerUiState> = _uiState

    fun onRoll() {
        val rollData: RollData = rollerService.getRollData(uiState.value.numberOfDice)
        _uiState.value = RollerUiState.Rolled(
            rollData = rollData, date = Date()
        )
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceRepository.getNumberOfDiceFlow().collect { numberOfDice ->
                _uiState.value = RollerUiState.NotRolled(numberOfDice)
            }
        }
    }

    fun onReset() {
        _uiState.value = INIT_STATE
    }

    fun onChangeOfNumberOfDice(newNumberOfDice: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceRepository.saveNumberOfDice(newNumberOfDice)
        }
    }

    companion object {
        private val INIT_STATE = RollerUiState.NotRolled(numberOfDice = 3)
    }

}