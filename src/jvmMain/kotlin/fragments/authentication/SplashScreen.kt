package fragments.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import rogo.iot.module.platform.ILogR
import rogo.iot.module.rogocore.basesdk.define.IoTErrorCode
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.SmartSdkConnectCallback
import rogo.iot.module.rogocore.sdk.callback.SuccessStatusCallback
import rogo.iot.module.rogoplatformmultios.CommonIoTPlatform
import rogo.iot.module.rogoplatformmultios.DesktopIoTPlatform

@Composable
fun splashScreen(statusCallback: SuccessStatusCallback) {
    val TAG = "SplashScreen"
    DesktopIoTPlatform(object : CommonIoTPlatform.ConstructorPlatformCallback {
        override fun onSuccess(p0: CommonIoTPlatform?) {
            try {
                SmartSdk.isForceProduction = true
                SmartSdk().initV2(
                    p0,
                    "e4b75a6b23fc4f30bd5fab35436c6a90",
                    "964e2c974f001a0468bf2734ce88e96652afff328886"
                )
                SmartSdk.connectService(object : SmartSdkConnectCallback {
                    override fun onConnected(p0: Boolean) {
                        if (p0) {
                            statusCallback.onSuccess()
                        } else {
                            statusCallback.onFailure(IoTErrorCode.NOTSUPPORT, "SERVICE_IS_NOT_CONNECTED")
                        }
                    }

                    override fun onDisconnected() {
                        statusCallback.onFailure(IoTErrorCode.NOTSUPPORT, "SERVICE_IS_NOT_CONNECTED")
                    }
                })
            } catch (e: Exception){
                ILogR.D(TAG, "ON_CONNECT_SERVICE", e.message)
            }
        }
    })
}