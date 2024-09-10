package fragments

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rogo.iot.module.platform.ILogR
import rogo.iot.module.rogocore.basesdk.define.IoTErrorCode
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.SmartSdkConnectCallback
import rogo.iot.module.rogocore.sdk.callback.SuccessStatusCallback
import rogo.iot.module.rogoplatformmultios.CommonIoTPlatform
import rogo.iot.module.rogoplatformmultios.DesktopIoTPlatform
import ui.theme.RogoButton

@Composable
fun splashScreen(statusCallback: SuccessStatusCallback) {
    val TAG = "SplashScreen"
    var text by remember {
        mutableStateOf("ISCONNECTING")
    }
    DesktopIoTPlatform(object : CommonIoTPlatform.ConstructorPlatformCallback {
        override fun onSuccess(p0: CommonIoTPlatform?) {
            try {
                SmartSdk().initV2(p0)
                SmartSdk.connectService(object : SmartSdkConnectCallback {
                    override fun onConnected(p0: Boolean) {
                        if (p0) {
                            text = "SERVICE_IS_CONNECTED"
                            statusCallback.onSuccess()
                        } else {
                            text = "SERVICE_IS_NOT_CONNECTED"
                            statusCallback.onFailure(IoTErrorCode.NOTSUPPORT, "SERVICE_IS_NOT_CONNECTED")
                        }
                    }

                    override fun onDisconnected() {
                        statusCallback.onFailure(IoTErrorCode.NOTSUPPORT, "SERVICE_IS_NOT_CONNECTED")
                        text = "SERVICE_IS_NOT_CONNECTED"
                    }

                })
            } catch (e: Exception){
                ILogR.D(TAG, "ON_CONNECT_SERVICE", e.message)
                text = e.message.toString()
            }
        }
    })
}