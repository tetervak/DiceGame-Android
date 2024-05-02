package ca.tetervak.dicegame.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.dicegame.R
import ca.tetervak.dicegame.ui.roller.NotRolledBody
import ca.tetervak.dicegame.ui.roller.RolledBody
import ca.tetervak.dicegame.ui.roller.RollerTopAppBar
import ca.tetervak.dicegame.ui.roller.RollerUiState
import ca.tetervak.dicegame.ui.roller.RollerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRootScreen(
    viewModel: RollerViewModel = viewModel()
) {
    val uiState: RollerUiState = viewModel.uiState.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            RollerTopAppBar(
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::onRoll) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = stringResource(R.string.roll_dice)
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding).padding(top = 32.dp)
        ) {

            NumberOfDiceInput(
                numberOfDice = uiState.numberOfDice,
                onChange = viewModel::onChangeOfNumberOfDice
            )

            when (uiState) {
                is RollerUiState.Rolled -> RolledBody(
                    rollData = uiState.rollData,
                    date = uiState.date,
                    onRoll = viewModel::onRoll,
                    onReset = viewModel::onReset
                )

                is RollerUiState.NotRolled -> NotRolledBody(
                    numberOfDice = uiState.numberOfDice,
                    onRoll = viewModel::onRoll
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberOfDiceInput(
    numberOfDice: Int,
    onChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectOptions = stringArrayResource(id = R.array.choices_of_numbers_of_dice)
    var selectExpanded: Boolean by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectOptions[numberOfDice - 1]) }

    ExposedDropdownMenuBox(
        expanded = selectExpanded,
        onExpandedChange = { selectExpanded = it },
        modifier = modifier
    ) {
        CompositionLocalProvider(LocalTextInputService provides null) {
            // the CompositionLocalProvider is to go around a library bug, popping keyboard
            OutlinedTextField(
                readOnly = true,
                value = selectedText,
                onValueChange = { },
                label = {
                    Text(
                        text = stringResource(R.string.number_of_dice),
                        fontSize = 14.sp
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = selectExpanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.purple_500)
                ),
                modifier = Modifier.menuAnchor()
            )
        }
        ExposedDropdownMenu(
            expanded = selectExpanded,
            onDismissRequest = {
                selectExpanded = false
            }
        ) {
            selectOptions.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = option
                        selectExpanded = false
                        onChange(selectOptions.indexOf(option) + 1)
                    },
                    text = {
                        Text(text = option, fontSize = 20.sp)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun RollerScreenPreviewNotRolled() {
    AppRootScreen(RollerViewModel())
}

@Preview
@Composable
fun RollerScreenPreviewRolled() {
    val viewModel = RollerViewModel()
    viewModel.onRoll()
    AppRootScreen(viewModel)
}
