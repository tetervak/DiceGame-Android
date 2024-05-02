package ca.tetervak.dicegame.ui.roller

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.dicegame.R
import ca.tetervak.dicegame.domain.RollData
import ca.tetervak.dicegame.util.formatDateAndTime
import java.util.Date

@Composable
fun RolledBody(
    rollData: RollData,
    date: Date,
    onRoll: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 40.dp)
    ) {
        val list: List<Int> = rollData.values
        DiceImagesRow(list)
        DiceValuesRow(list)
        if (rollData.numberOfDice > 1){
            TotalRow(labelRes = R.string.roll_total_label, total = rollData.total)
        }
        RollerTimeStamp(date)
        Button(
            onClick = onRoll, modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = stringResource(R.string.roll_button_label, rollData.numberOfDice))
        }
        Button(
            onClick = onReset, modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.reset_button_label))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RolledBodyPreview() {
    RolledBody(
        rollData = RollData(listOf(1, 2, 3)),
        date = Date(),
        onRoll = {},
        onReset = {}
    )
}

@Composable
fun TotalRow(@StringRes labelRes: Int, total: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(labelRes),
            fontSize = 24.sp
        )
        Text(
            text = total.toString(),
            fontSize = 24.sp,
            color = colorResource(R.color.green_500)
        )
    }
}

@Preview
@Composable
fun TotalRowPreview(){
    TotalRow(labelRes = R.string.roll_total_label, total = 16)
}

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

@Composable
fun DiceImagesRow(list: List<Int>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.wrapContentWidth(),
    ) {
        for (value in list) {
            DiceImage(value)
        }
    }
}

@Composable
private fun DiceImage(value: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(diceImageResourceId(value)),
        contentDescription = value.toString(),
        modifier = modifier.size(80.dp)
    )
}

private fun diceImageResourceId(value: Int) = when (value) {
    1 -> R.drawable.dice_1
    2 -> R.drawable.dice_2
    3 -> R.drawable.dice_3
    4 -> R.drawable.dice_4
    5 -> R.drawable.dice_5
    else -> R.drawable.dice_6
}

@Preview
@Composable
fun DiceImagesRowPreview(){
    DiceImagesRow(list = listOf(3, 4, 2, 5))
}

@Composable
fun RollerTimeStamp(date: Date, modifier: Modifier = Modifier) {
    Text(
        text = formatDateAndTime(date),
        fontSize = 18.sp,
        color = Color.Gray,
        modifier = modifier
    )
}

@Preview
@Composable
fun RollerTimeStampPreview(){
    RollerTimeStamp(date = Date())
}