package com.example.demo_android.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db: AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
        val timeStampDao: TimeStampDao = db.timeStampDao()

        setContent {
            val timeStamps: List<TimeStamp> by timeStampDao.getAllFlow().collectAsState(initial = emptyList())

            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    text = buildString {
                        timeStamps.forEach { timeStamp: TimeStamp ->
                            appendLine(timeStamp)
                        }
                    },
                )
                Row(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                timeStampDao.insertAll(TimeStamp(timeValue = "${System.currentTimeMillis()}"))
                            }
                        }
                    ) {
                        Text(text = "Insert")
                    }
                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                timeStampDao.getAll().firstOrNull()?.id?.let {
                                    timeStampDao.deleteAll(it)
                                }
                            }
                        }
                    ) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}
