package fragments.authentication

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
import ui.theme.CORE_COLOR
import ui.theme.DARK_BLUE
import ui.theme.HeadlineLarge
import ui.theme.Roboto
import ui.theme.RogoSpace
import ui.theme.TitleLarge

@Composable
fun authenticationScreen(onAuthenticationSuccess: () -> Unit) {
    var currentScreen by remember {
        mutableStateOf("signIn")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(currentScreen) {
            "signIn" -> signInScreen(
                onSignInSuccess = {
                    onAuthenticationSuccess.invoke()
                }
            )

            "signUp" -> signUpScreen(
                onSignInClick = {
                    currentScreen = "signIn"
                }
            )
        }
    }
}