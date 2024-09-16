package fragments.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.IoTGroup
import ui.theme.GRAY
import ui.theme.HeadlineMedium
import ui.theme.Roboto
import ui.theme.Roboto_Bold
import ui.theme.RogoSpace

@Composable
fun groupScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Danh sách nhóm phòng",
            fontSize = HeadlineMedium.sp,
            color = Color.Black,
            fontFamily = Roboto
        )
        Spacer(modifier = Modifier.size(34.dp))

    }
}
@Composable
fun getGroupList(onUpdate: (IoTGroup) -> Unit) {
    var groupList by remember {
        mutableStateOf(SmartSdk.groupHandler().all.toMutableStateList())
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(groupList) {
                groupItem(it)
            }
        }
    }
}

@Composable
fun groupItem(ioTGroup: IoTGroup) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(GRAY, RoundedCornerShape(8.dp))
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = null,
                modifier = Modifier.size(27.dp)
            )

            RogoSpace(16)

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = ioTGroup.label,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontFamily = Roboto_Bold
                )

                RogoSpace(2)

                Text(
                    text = ioTGroup.desc,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = Roboto
                )
            }
        }
    }
    RogoSpace(12)
}