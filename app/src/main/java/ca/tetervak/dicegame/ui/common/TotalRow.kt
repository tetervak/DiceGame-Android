package ca.tetervak.dicegame.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.dicegame.R

@Composable
fun TotalRow(@StringRes labelRes: Int, total: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(labelRes),
            fontSize = 24.sp
        )
        Text(
            text = total.toString(), fontSize = 24.sp, color = colorResource(R.color.green_500)
        )
    }
}

@Preview
@Composable
fun TotalRowPreview(){
    TotalRow(labelRes = R.string.roll_total_label, total = 16)
}