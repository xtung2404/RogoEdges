package fragments.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fragments.group.createGroupScreen
import fragments.group.deleteGroupScreen
import fragments.group.groupScreen
import fragments.group.updateGroup
import fragments.group.updateGroupScreen
import items.RogoFunction
import ui.theme.BLUE
import ui.theme.Roboto
import ui.theme.RogoSpace

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
                .background(BLUE, shape = RoundedCornerShape(0, 8, 8, 0))
        ) {
            getListFunction(onFunctionClick = {
                currentScreen = it
            })
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(28.dp, 36.dp)
        ) {
            when(currentScreen) {
                RogoFunction.SHOWGROUP.id -> groupScreen()
                RogoFunction.ADDGROUP.id -> createGroupScreen(onNavBack = {

                })
                RogoFunction.UPDATEGROUP.id -> updateGroupScreen(onNavBack = {

                })
                RogoFunction.DELETEGROUP.id -> deleteGroupScreen()
            }
        }
    }
}
@Composable
fun getListFunction(onFunctionClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(RogoFunction.getGroupFunctions()) {
            functionItem(it, onClick = {
                onFunctionClick.invoke(it)
            })
        }
    }
}

@Composable
fun functionItem(rogoFunction: RogoFunction, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(rogoFunction.id)
            }
    ) {
        Text(
            text = rogoFunction.id,
            fontFamily = Roboto,
            color = Color.White,
            fontSize = 14.sp)
        RogoSpace(8)
    }
}