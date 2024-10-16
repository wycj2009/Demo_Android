package com.example.demo_android.dagger2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.setLastAppLunchTime()

        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "${this@MainActivity::class.simpleName}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${viewModel.getInjectedFieldsInfo()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "lastAppLunchTime: ${viewModel.getLastAppLunchTime()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, MathArithmeticLectureActivity::class.java))
                    }
                ) {
                    Text(text = "MathArithmeticLectureActivity")
                }
                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, EnglishWordLectureActivity::class.java))
                    }
                ) {
                    Text(text = "EnglishWordLectureActivity")
                }
                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, EnglishGrammarLectureActivity::class.java))
                    }
                ) {
                    Text(text = "EnglishGrammarLectureActivity")
                }
                Spacer(modifier = Modifier.weight(1.0f))
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(10.dp))
                // TODO: Add Graph UI
            }
        }
    }
}
