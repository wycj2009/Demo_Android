package com.example.demo_android.preferences_datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private val settings1DataStore: Settings1DataStore = Settings1DataStore.getInstance(this)
    private val settings2DataStore: Settings2DataStore = Settings2DataStore.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settings1Boolean: Boolean by settings1DataStore.boolean.getFlow().collectAsState(initial = settings1DataStore.boolean.get())
            val settings1Int: Int by settings1DataStore.int.getFlow().collectAsState(initial = settings1DataStore.int.get())
            val settings1Long: Long by settings1DataStore.long.getFlow().collectAsState(initial = settings1DataStore.long.get())
            val settings1Float: Float by settings1DataStore.float.getFlow().collectAsState(initial = settings1DataStore.float.get())
            val settings1Double: Double by settings1DataStore.double.getFlow().collectAsState(initial = settings1DataStore.double.get())
            val settings1String: String by settings1DataStore.string.getFlow().collectAsState(initial = settings1DataStore.string.get())
            val settings1StringSet: Set<String> by settings1DataStore.stringSet.getFlow().collectAsState(initial = settings1DataStore.stringSet.get())

            val settings2Boolean: Boolean by settings2DataStore.boolean.getFlow().collectAsState(initial = settings2DataStore.boolean.get())
            val settings2Int: Int by settings2DataStore.int.getFlow().collectAsState(initial = settings2DataStore.int.get())
            val settings2Long: Long by settings2DataStore.long.getFlow().collectAsState(initial = settings2DataStore.long.get())
            val settings2Float: Float by settings2DataStore.float.getFlow().collectAsState(initial = settings2DataStore.float.get())
            val settings2Double: Double by settings2DataStore.double.getFlow().collectAsState(initial = settings2DataStore.double.get())
            val settings2String: String by settings2DataStore.string.getFlow().collectAsState(initial = settings2DataStore.string.get())
            val settings2StringSet: Set<String> by settings2DataStore.stringSet.getFlow().collectAsState(initial = settings2DataStore.stringSet.get())

            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { settings1DataStore.boolean.put(!settings1Boolean) }) {
                        Text(text = "${settings1Boolean}")
                    }
                    Button(onClick = { settings1DataStore.int.put(settings1Int + 1) }) {
                        Text(text = "${settings1Int}")
                    }
                    Button(onClick = { settings1DataStore.long.put(settings1Long + 10L) }) {
                        Text(text = "${settings1Long}")
                    }
                    Button(onClick = { settings1DataStore.float.put(settings1Float + 0.1f) }) {
                        Text(text = "${settings1Float}")
                    }
                    Button(onClick = { settings1DataStore.double.put(settings1Double + 0.01) }) {
                        Text(text = "${settings1Double}")
                    }
                    Button(onClick = { settings1DataStore.string.put(settings1String + "A") }) {
                        Text(text = "${settings1String}")
                    }
                    Button(
                        onClick = {
                            var newValue: Set<String> = settings1StringSet.let {
                                it + setOf(('A'.code + it.size).toChar().toString())
                            }
                            if (newValue.size > 10) {
                                newValue = setOf("A")
                            }
                            settings1DataStore.stringSet.put(newValue)
                        },
                    ) {
                        Text(text = "${settings1StringSet}")
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { settings2DataStore.boolean.put(!settings2Boolean) }) {
                        Text(text = "${settings2Boolean}")
                    }
                    Button(onClick = { settings2DataStore.int.put(settings2Int + 1) }) {
                        Text(text = "${settings2Int}")
                    }
                    Button(onClick = { settings2DataStore.long.put(settings2Long + 10L) }) {
                        Text(text = "${settings2Long}")
                    }
                    Button(onClick = { settings2DataStore.float.put(settings2Float + 0.1f) }) {
                        Text(text = "${settings2Float}")
                    }
                    Button(onClick = { settings2DataStore.double.put(settings2Double + 0.01) }) {
                        Text(text = "${settings2Double}")
                    }
                    Button(onClick = { settings2DataStore.string.put(settings2String + "A") }) {
                        Text(text = "${settings2String}")
                    }
                    Button(
                        onClick = {
                            var newValue: Set<String> = settings2StringSet.let {
                                it + setOf(('A'.code + it.size).toChar().toString())
                            }
                            if (newValue.size > 10) {
                                newValue = setOf("A")
                            }
                            settings2DataStore.stringSet.put(newValue)
                        },
                    ) {
                        Text(text = "${settings2StringSet}")
                    }
                }
            }
        }
    }
}
