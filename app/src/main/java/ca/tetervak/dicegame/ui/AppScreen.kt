package ca.tetervak.dicegame.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.dicegame.ui.roller.RollerScreen
import ca.tetervak.dicegame.ui.roller.RollerViewModel

@Composable
fun AppScreen(){
    // an application with navigation will have additional
    // logic between AppScreen and RollerScreen

    // this ViewModel belongs to the MainActivity
    val viewModel: RollerViewModel = viewModel()

    RollerScreen(viewModel)
}