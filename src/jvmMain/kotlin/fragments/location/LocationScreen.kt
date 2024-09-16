package fragments.location

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
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
import rogo.iot.module.platform.ILogR
import rogo.iot.module.platform.callback.RequestCallback
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.IoTLocation
import ui.theme.GRAY
import ui.theme.HeadlineMedium
import ui.theme.Roboto
import ui.theme.Roboto_Bold
import ui.theme.RogoBackButton
import ui.theme.RogoOutlinedTextField
import ui.theme.RogoSpace

@Composable
fun locationScreen() {
    var currentScreen by remember {
        mutableStateOf("showLocation")
    }
    var currentLocation by remember {
        mutableStateOf<IoTLocation?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(56.dp, 72.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(currentScreen) {
            "showLocation" -> showLocationScreen(onCreateLocation = {
                currentScreen = "createLocation"
            }, onUpdateLocation = {
                currentLocation = it
            })
            "createLocation" -> createLocationScreen(onNavBack = {
                currentScreen = "showLocation"
            }, ioTLocation = currentLocation)
        }
    }
}

@Composable
fun showLocationScreen(onCreateLocation: () -> Unit, onUpdateLocation: (IoTLocation) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Danh sách địa điểm",
                fontSize = HeadlineMedium.sp,
                color = Color.Black,
                fontFamily = Roboto
            )

            Icon(
                modifier = Modifier
                    .size(27.dp)
                    .background(Color.Black, CircleShape)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        onCreateLocation.invoke()
                    },
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.size(34.dp))
        getLocationList {
            onUpdateLocation.invoke(it)
        }
    }
}
@Composable
fun getLocationList(onUpdate: (IoTLocation) -> Unit) {
    var locationList by remember {
        mutableStateOf(SmartSdk.locationHandler().all.toMutableStateList())
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(locationList) {
                locationItem(it, onUpdate = {
                    onUpdate.invoke(it)
                }, onDeleteFinish = {
                    locationList = SmartSdk.locationHandler().all.toMutableStateList()
                })
            }
        }
    }
}

@Composable
fun locationItem(ioTLocation: IoTLocation, onUpdate: (IoTLocation) -> Unit, onDeleteFinish: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                SmartSdk.setAppLocation(ioTLocation.uuid)
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(GRAY, RoundedCornerShape(8.dp))
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(27.dp)
            )

            RogoSpace(16)

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = ioTLocation.label,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontFamily = Roboto_Bold
                )

                RogoSpace(2)

                Text(
                    text = ioTLocation.desc,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = Roboto
                )
            }
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                modifier = Modifier
                    .size(27.dp)
                    .clickable {
                        onUpdate.invoke(ioTLocation)
                    }
            )

            RogoSpace(8)

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier
                    .size(27.dp)
                    .clickable {
                        deleteLocation(ioTLocation.uuid) {
                            onDeleteFinish.invoke()
                        }
                    }
            )
        }
    }
    RogoSpace(12)
}

@Composable
fun createLocationScreen(ioTLocation: IoTLocation?, onNavBack: () -> Unit) {
    var label by remember {
        mutableStateOf(ioTLocation?.label?: "")
    }
    var ioTLocationType by remember {
        mutableStateOf(ioTLocation?.desc?: "")
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
                    text = "Tên địa điểm",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = Roboto)

                RogoSpace(8)

                RogoOutlinedTextField(
                    text = label,
                    hint = "Nhà riêng, Chung cư"
                ) {
                    label = it
                }

                RogoSpace(20)

                Text(
                    text = "Kiểu địa điểm",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontFamily = Roboto)

                RogoSpace(8)

                RogoOutlinedTextField(
                    text = ioTLocationType,
                    hint = "Nhà riêng, Chung cư"
                ) {
                    ioTLocationType = it
                }

                RogoSpace(34)

                IconButton(
                    modifier = Modifier
                        .size(27.dp)
                        .background(color = Color.Blue, RoundedCornerShape(8.dp))
                        .align(Alignment.End),
                    onClick = {
                        if (ioTLocation == null) {
                            createLocation(label, ioTLocationType, onResult = {
                                onNavBack.invoke()
                            })
                        } else {
                            updateLocation(
                                ioTLocation.uuid,
                                label,
                                ioTLocationType) {
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

fun createLocation(label: String, locationType: String, onResult: () -> Unit) {
    SmartSdk.locationHandler().createLocation(
        label,
        locationType,
        object : RequestCallback<IoTLocation> {
            override fun onSuccess(p0: IoTLocation?) {
                onResult.invoke()
            }

            override fun onFailure(p0: Int, p1: String?) {
                ILogR.D("CreateLocation", "OnFailure: ", p0, p1)
            }
        }
    )
}

fun updateLocation(locationId: String, label: String, desc: String, onResult: () -> Unit) {
    SmartSdk.locationHandler().updateLocation(
        locationId,
        label,
        desc,
        object : RequestCallback<IoTLocation> {
            override fun onSuccess(p0: IoTLocation?) {
                onResult.invoke()
            }

            override fun onFailure(p0: Int, p1: String?) {
                ILogR.D("UpdateLocation", "OnFailure", p0, p1)
            }
        }
    )
}

fun deleteLocation(locationId: String, onResult: () -> Unit) {
    SmartSdk.locationHandler().delete(
        locationId,
        object : RequestCallback<Boolean> {
            override fun onSuccess(p0: Boolean?) {
                onResult.invoke()
            }

            override fun onFailure(p0: Int, p1: String?) {
                ILogR.D("DELETE_LOCATIOn", "OnFailure", p0, p1)
            }

        })
}

