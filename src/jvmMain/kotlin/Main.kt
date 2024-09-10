// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import fragments.authenticationScreen
import fragments.signInScreen
import fragments.signUpScreen
import fragments.splashScreen
import rogo.iot.module.platform.ILogR
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.SmartSdkConnectCallback
import rogo.iot.module.rogocore.sdk.callback.SuccessStatusCallback
import rogo.iot.module.rogoplatformmultios.CommonIoTPlatform
import rogo.iot.module.rogoplatformmultios.DesktopIoTPlatform


@Composable
@Preview
fun MainApplication() {
    val TAG = "MainApplication"
    var currentScreen by remember { mutableStateOf("splash") }
    MaterialTheme {
        when(currentScreen) {
            "authentication" -> authenticationScreen()
            "splash" -> splashScreen(object : SuccessStatusCallback {
                override fun onSuccess() {
                    ILogR.D(TAG, "onConnectServiceSuccess")
                }

                override fun onFailure(p0: Int, p1: String?) {
                    ILogR.D(TAG, "onConnectServiceFailure", p0, p1)
                    currentScreen = "authentication"
                }
            })
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainApplication()
    }
}
