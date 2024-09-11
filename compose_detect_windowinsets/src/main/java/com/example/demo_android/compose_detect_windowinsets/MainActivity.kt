package com.example.demo_android.compose_detect_windowinsets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo_android.core.ui.theme.Demo_AndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()
//        window.setDecorFitsSystemWindows(false)

        setContent {
            Demo_AndroidTheme {
                val windowInsetsInfo: WindowInsetsInfo by rememberWindowInsetsInfoState()
                val localFocusManager: FocusManager = LocalFocusManager.current

                LaunchedEffect(windowInsetsInfo.isImeVisible) {
                    if (!windowInsetsInfo.isImeVisible) {
                        localFocusManager.clearFocus()
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = """
                            screenHeight=${windowInsetsInfo.screenHeight}
                            systemBarTopHeight=${windowInsetsInfo.systemBarTopHeight}
                            systemBarBottomHeight=${windowInsetsInfo.systemBarBottomHeight}
                            totalImeHeight=${windowInsetsInfo.totalImeHeight}
                            onlyImeHeight=${windowInsetsInfo.onlyImeHeight}
                            isImeVisible=${windowInsetsInfo.isImeVisible}
                        """.trimIndent(),
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {}
                    )
                }
            }
        }
    }
}
