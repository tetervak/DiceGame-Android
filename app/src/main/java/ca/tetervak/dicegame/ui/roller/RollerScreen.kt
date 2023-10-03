package ca.tetervak.dicegame.ui.roller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import ca.tetervak.dicegame.R
import ca.tetervak.dicegame.ui.common.RollerTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RollerScreen(
    viewModel: RollerViewModel,
    modifier: Modifier = Modifier
) {

    val state: State<RollerUiState> = viewModel.uiState
    val rollerUiState: RollerUiState = state.value
    val numberOfDice: Int by viewModel.numberOfDice.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RollerTopAppBar(
                title = stringResource(R.string.nav_roller_title),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        when (rollerUiState) {
            is RollerUiState.Rolled -> RolledBody(
                rollData = rollerUiState.rollData,
                date = rollerUiState.date,
                numberOfDice = numberOfDice,
                onRoll = viewModel::onRoll,
                onReset = viewModel::onReset,
                modifier = modifier.padding(innerPadding)
            )
            is RollerUiState.NotRolled -> NotRolledBody(
                numberOfDice = numberOfDice,
                onRoll = viewModel::onRoll,
                modifier = modifier.padding(innerPadding)
            )
            is RollerUiState.Loading -> LoadingBody()
            is RollerUiState.Error -> ErrorBody(
                onRetry = viewModel::onRoll
            )
        }
    }
}