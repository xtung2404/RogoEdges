package fragments.gatewayManufacturer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.GRAY
import ui.theme.RED
import ui.theme.Roboto
import ui.theme.RogoImageTextButton
import ui.theme.RogoSpace
import ui.theme.backgroundColor

@Composable
fun gatewayManufacturerScreen() {
    var search by remember {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Gateway List",
            fontSize = 24.sp,
            fontFamily = Roboto,
            color = Color.Black
        )

        RogoSpace(24)

        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.width(362.dp)) {
                OutlinedTextField(
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = Roboto
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp).padding(0.dp),
                            tint = Color.Black
                        )
                    },
                    placeholder = {
                        Text(
                            "Search",
                            style = TextStyle(
                                color = GRAY),
                            fontFamily = Roboto,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(0.dp)
                        )
                    },
                    modifier = Modifier
                        .background(backgroundColor)
                        .fillMaxWidth())
            }
            
            Spacer(Modifier.weight(1f))
            
            RogoImageTextButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(36.dp)
                    .padding(0.dp),
                text = "Edit gateway",
                textColor = Color.Black,
                isUppercase = true,
                fontFamily = Roboto,
                textSize = 14,
                cornerRadius = 8,
                textAlign = TextAlign.Left,
                backgroundColor = backgroundColor) {

            }

            RogoSpace(8)

            RogoImageTextButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(36.dp)
                    .padding(0.dp),
                text = "Reset mesh",
                textColor = RED,
                isUppercase = true,
                fontFamily = Roboto,
                textSize = 14,
                cornerRadius = 8,
                textAlign = TextAlign.Left,
                backgroundColor = backgroundColor) {

            }
        }

        RogoSpace(24)


    }
}