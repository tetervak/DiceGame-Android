package ca.tetervak.dicegame.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.dicegame.R

@Composable
fun DiceValuesRow(list: List<Int>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.wrapContentWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (value in list) {
            Text(
                text = value.toString(),
                color = colorResource(R.color.deep_purple_500),
                fontSize = 56.sp
            )
        }
    }
}

@Preview
@Composable
fun DiceValuesRowPreview(){
    DiceValuesRow(list = listOf(3,2,5,6))
}