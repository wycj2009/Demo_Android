package com.example.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.Demo_AndroidTheme
import javax.inject.Inject

class EnglishWordLectureActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: EnglishWordLectureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.englishLectureComponent().create().inject(this)
        super.onCreate(savedInstanceState)

        viewModel.setLastEnglishWordLectureEntryTime()

        setContent {
            Demo_AndroidTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "${this@EnglishWordLectureActivity::class.simpleName}",
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
                    Text(
                        text = "lastEnglishWordLectureEntryTime: ${viewModel.getLastEnglishWordLectureEntryTime()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${viewModel.getWords()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.weight(1.0f))
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                    Spacer(modifier = Modifier.height(10.dp))
                    // TODO: Add Graph UI
                }
            }
        }
    }
}
