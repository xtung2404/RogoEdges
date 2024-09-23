package fragments.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fragments.gatewayManufacturer.gatewayManufacturerScreen
import fragments.group.createGroupScreen
import fragments.group.deleteGroupScreen
import fragments.group.groupScreen
import fragments.group.updateGroup
import fragments.group.updateGroupScreen
import fragments.overview.overviewScreen
import items.RogoFunction
import ui.theme.BLUE
import ui.theme.BLUE_PURPLE
import ui.theme.DARK_BLUE
import ui.theme.DARK_GRAY
import ui.theme.GRAY
import ui.theme.RED
import ui.theme.Roboto
import ui.theme.RogoSpace
import ui.theme.TitleLarge
import ui.theme.WHITE
import ui.theme.backgroundColor
import ui.theme.lineColor

@Composable
fun dashboardScreen() {
    var currentScreen by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(0.2f)
                .padding(16.dp)
                .background(WHITE)
        ) {
            RogoSpace(48)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(27.dp),
                    tint = DARK_BLUE
                )

                RogoSpace(7)

                Text(
                    text = "ROGO solutions",
                    fontSize = TitleLarge.sp,
                    color = BLUE_PURPLE,
                    fontFamily = Roboto
                )
            }

            RogoSpace(24)

            getListFunction(
                modifier = Modifier.weight(1f),
                onFunctionClick = {
                    currentScreen = it
            })

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = RED
                )

                RogoSpace(7)

                Text(
                    text = "Log out",
                    fontSize = 14.sp,
                    color = RED,
                    fontFamily = Roboto
                )
            }

            RogoSpace(24)

        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
                .background(color = backgroundColor)
                .padding(horizontal = 40.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(0.dp, 48.dp, 40.dp, 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = RED
                    )

                    RogoSpace(16)

                    Column {
                        Text(
                            text = "Admin",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = Roboto
                        )

                        Text(
                            text = "Admin",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontFamily = Roboto
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(lineColor))

            RogoSpace(24)

            when(currentScreen) {
                RogoFunction.OVERVIEW.id -> overviewScreen()
                RogoFunction.GATEWAY_MANUFACTURER.id -> gatewayManufacturerScreen()
                RogoFunction.TESTING_WIFI_AND_BLE.id -> updateGroupScreen(onNavBack = {

                })
            }
        }
    }
}
@Composable
fun getListFunction(modifier: Modifier, onFunctionClick: (String) -> Unit) {
    LazyColumn(
        modifier = modifier
    ) {
        items(RogoFunction.getFunctions()) {
            functionItem(it, onClick = {
                onFunctionClick.invoke(it)
            })
        }
    }
}

@Composable
fun functionItem(rogoFunction: RogoFunction, onClick: (String) -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(rogoFunction.id)
            }.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = DARK_GRAY
        )

        RogoSpace(8)

        Text(
            text = rogoFunction.id,
            fontFamily = Roboto,
            color = DARK_GRAY ,
            fontSize = 14.sp,
            style = TextStyle(lineHeight = 24.sp)
        )
        RogoSpace(8)
    }
}