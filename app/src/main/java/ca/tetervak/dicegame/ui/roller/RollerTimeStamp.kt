package ca.tetervak.dicegame.ui.roller

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ca.tetervak.dicegame.util.formatDateAndTime
import java.util.Date

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