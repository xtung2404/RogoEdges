package fragments.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
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
import com.sun.net.httpserver.Authenticator.Success
import rogo.iot.module.platform.ILogR
import rogo.iot.module.rogocloudapi.auth.callback.AuthRequestCallback
import rogo.iot.module.rogocore.sdk.SmartSdk
import ui.theme.BLUE
import ui.theme.HeadlineMedium
import ui.theme.LabelMedium
import ui.theme.Roboto
import ui.theme.Roboto_Bold
import ui.theme.RogoButton
import ui.theme.RogoOutlinedTextField

@Composable
fun signInScreen(onSignInSuccess: () -> Unit) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isConfirmed by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign in",
            fontSize = HeadlineMedium.sp,
            color = Color.Black,
            fontFamily = Roboto
        )
        Spacer(modifier = Modifier.size(34.dp))

        RogoOutlinedTextField(
            hint = "Type your email address",
            onValueChange = {
                email = it
            }
        )
        Spacer(modifier = Modifier.size(24.dp))

        RogoOutlinedTextField(
            hint = "Type your password",
            onValueChange = {
                password = it
            }
        )

        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.width(600.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isConfirmed,
                onCheckedChange = {
                    isConfirmed = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black
                )
            )
            Text(
                text = "Agree to Terms of Use, Legal Statement, Privacy Policy",
                fontSize = LabelMedium.sp,
                color = Color.Black,
                fontFamily = Roboto_Bold
            )
        }
        Spacer(modifier = Modifier.size(34.dp))

        RogoButton(
            text = "Sign in",
            backgroundColor = BLUE,
            textColor = Color.White,
            isUppercase = true,
            cornerRadius = 100,
            onClick = {
                signIn(email, password, onSignInSuccess)
            })
    }
}

fun signIn(email: String ,password: String, onSignInSuccess: () -> Unit) {
    SmartSdk.signIn(null, "tungrogo24@gmail.com", null, "123456", object : AuthRequestCallback {
        override fun onSuccess() {
            onSignInSuccess.invoke()
            ILogR.D("SignInFragment", "onSuccess")
        }

        override fun onFailure(p0: Int, p1: String?) {
            ILogR.D("SignInFragment", "onFailure", p0, p1)
        }

    })
}