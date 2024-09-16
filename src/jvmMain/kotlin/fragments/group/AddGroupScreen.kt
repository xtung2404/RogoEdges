package fragments.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fragments.location.createLocation
import fragments.location.updateLocation
import rogo.iot.module.platform.ILogR
import rogo.iot.module.platform.callback.RequestCallback
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.IoTGroup
import ui.theme.Roboto
import ui.theme.RogoBackButton
import ui.theme.RogoOutlinedTextField
import ui.theme.RogoSpace

@Composable
fun createGroupScreen(ioTGroup: IoTGroup?, onNavBack: () -> Unit) {
    var label by remember {
        mutableStateOf(ioTGroup?.label?: "")
    }
    var ioTGroupType by remember {
        mutableStateOf(ioTGroup?.desc?: "")
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RogoBackButton {
            onNavBack.invoke()
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column (

            ) {
                Text(
                    text = "Tên nhóm phòng",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = Roboto
                )

                RogoSpace(8)

                RogoOutlinedTextField(
                    text = label,
                    hint = "Phòng khách"
                ) {
                    label = it
                }

                RogoSpace(20)

                Text(
                    text = "Kiểu nhóm",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = Roboto
                )

                RogoSpace(8)

                RogoOutlinedTextField(
                    text = ioTGroupType,
                    hint = "Nhà riêng, Chung cư"
                ) {
                    ioTGroupType = it
                }

                RogoSpace(34)

                IconButton(
                    modifier = Modifier
                        .size(27.dp)
                        .background(color = Color.Blue, RoundedCornerShape(8.dp))
                        .align(Alignment.End),
                    onClick = {
                        if (ioTGroup == null) {
                            createGroup(
                                label, ioTGroupType
                            )
                        } else {
                            updateLocation(
                                ioTGroup.uuid,
                                label,
                                ioTGroupType) {
                                onNavBack.invoke()
                            }
                        }

                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

fun createGroup(label: String, desc: String) {
    SmartSdk.groupHandler().createGroup(
        label,
        desc,
        object : RequestCallback<IoTGroup> {
            override fun onSuccess(p0: IoTGroup?) {
//                onResult.invoke()
            }

            override fun onFailure(p0: Int, p1: String?) {
                ILogR.D("CREATE_GROUP", "OnFailure", p0, p1)
            }

        }
    )
}

fun updateGroup(groupId: String, label: String, desc: String) {
    SmartSdk.groupHandler().updateGroup(
        groupId,
        label,
        desc,
        object : RequestCallback<IoTGroup> {
            override fun onSuccess(p0: IoTGroup?) {
                ILogR.D("CREATE_GROUP", "OnSuccess")
            }

            override fun onFailure(p0: Int, p1: String?) {
                ILogR.D("CREATE_GROUP", "OnFailure", p0, p1)
            }

        }
    )
}