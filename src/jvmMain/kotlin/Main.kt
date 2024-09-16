// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import fragments.authentication.authenticationScreen
import fragments.authentication.signInScreen
import fragments.authentication.signUpScreen
import fragments.authentication.splashScreen
import fragments.dashboard.dashboardScreen
import fragments.location.locationItem
import fragments.location.locationScreen
import rogo.iot.module.platform.ILogR
import rogo.iot.module.rogocore.sdk.callback.SuccessStatusCallback
import ui.theme.CORE_COLOR
import ui.theme.DARK_BLUE
import ui.theme.HeadlineLarge
import ui.theme.Roboto
import ui.theme.RogoSpace
import ui.theme.TitleLarge


@Composable
@Preview
fun MainApplication() {
    val TAG = "MainApplication"
    var currentScreen by remember { mutableStateOf("dashboard") }
    MaterialTheme {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .background(CORE_COLOR)
                    .padding(56.dp, 72.dp)
                    .fillMaxHeight()
            ) {
                Column {
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
                            color = DARK_BLUE,
                            fontFamily = Roboto
                        )
                    }

                    RogoSpace(72)

                    Text(
                        text = "Excellence of Rogo \nSolutions - All-in-one IoT \nPlatform",
                        fontSize = HeadlineLarge.sp,
                        color = DARK_BLUE,
                        fontFamily = Roboto,
                    )
                }
                Text(
                    text = "© 2023 - 2024\n" +
                            "https://rogo.com.vn\n" +
                            "All Rights Reserved",
                    fontSize = 8.sp,
                    color = DARK_BLUE,
                    fontFamily = Roboto,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxSize()
            ) {
                when(currentScreen) {
                    "authentication" -> authenticationScreen {
                        currentScreen = "location"
                    }

                    "splash" -> splashScreen(object : SuccessStatusCallback {
                        override fun onSuccess() {
                            ILogR.D(TAG, "onConnectServiceSuccess")
                            currentScreen = "location"
                        }

                        override fun onFailure(p0: Int, p1: String?) {
                            ILogR.D(TAG, "onConnectServiceFailure", p0, p1)
                            currentScreen = "authentication"
                        }
                    })

                    "location" -> locationScreen()

                    "dashboard" -> dashboardScreen()
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainApplication()
    }
}
