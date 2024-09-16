package fragments.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rogo.iot.module.rogocore.sdk.SmartSdk
import ui.theme.BLUE
import ui.theme.BodyLarge
import ui.theme.CORE_COLOR
import ui.theme.DARK_BLUE
import ui.theme.HeadlineLarge
import ui.theme.HeadlineMedium
import ui.theme.LabelMedium
import ui.theme.Roboto
import ui.theme.Roboto_Bold
import ui.theme.RogoButton
import ui.theme.RogoOutlinedTextField
import ui.theme.RogoSpace
import ui.theme.TitleLarge

@Composable
fun signUpScreen(onSignInClick: () -> Unit) {
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
            text = "Sign up",
            fontSize = HeadlineMedium.sp,
            color = Color.Black,
            fontFamily = Roboto
        )

        Spacer(modifier = Modifier.size(34.dp))

        RogoOutlinedTextField(
            hint = "Type your email address"
        ) {

        }

        RogoSpace(24)

        Row (
            modifier = Modifier.width(600.dp).height(60.dp)
        ) {
            RogoOutlinedTextField(
                modifier = Modifier.weight(0.5f),
                hint = "Type verification code"
            ) {

            }

        }
        RogoSpace(24)

        RogoOutlinedTextField(
            hint = "Type your password"
        ) {

        }

        RogoSpace(24)

        RogoOutlinedTextField(
            hint = "Type your password again"
        ) {

        }

        RogoSpace(24)

        Row (
            modifier = Modifier.width(600.dp).height(60.dp)
        ) {
            RogoOutlinedTextField(
                modifier = Modifier.weight(0.45f),
                hint = "Type your phone number"
            ) {

            }

            Spacer(modifier = Modifier.weight(0.1f))

            RogoOutlinedTextField(
                modifier = Modifier.weight(0.45f),
                hint = "Type your password"
            ) {

            }
        }

        RogoSpace(20)
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

        RogoSpace(34)

        RogoButton(
            text = "Sign up",
            backgroundColor = BLUE,
            textColor = Color.White,
            isUppercase = true,
            cornerRadius = 100,
            onClick = {

            })

        RogoSpace(20)

        RogoButton(
            text = "Sign in",
            backgroundColor = Color.White,
            textColor = BLUE,
            cornerRadius = 100,
            isUppercase = true,
            onClick = {
                onSignInClick.invoke()
            })


    }
}

fun signUp() {

}