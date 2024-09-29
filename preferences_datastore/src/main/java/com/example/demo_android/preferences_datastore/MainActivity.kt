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
import com.example.demo_android.core.ui.theme.Demo_AndroidTheme

class MainActivity : ComponentActivity() {
    private val settings1: Settings1 = Settings1.getInstance(this)
    private val settings2: Settings2 = Settings2.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Demo_AndroidTheme {
                val settings1Boolean: Boolean by settings1.boolean.getFlow().collectAsState(initial = settings1.boolean.get())
                val settings1Int: Int by settings1.int.getFlow().collectAsState(initial = settings1.int.get())
                val settings1Long: Long by settings1.long.getFlow().collectAsState(initial = settings1.long.get())
                val settings1Float: Float by settings1.float.getFlow().collectAsState(initial = settings1.float.get())
                val settings1Double: Double by settings1.double.getFlow().collectAsState(initial = settings1.double.get())
                val settings1String: String by settings1.string.getFlow().collectAsState(initial = settings1.string.get())
                val settings1StringSet: Set<String> by settings1.stringSet.getFlow().collectAsState(initial = settings1.stringSet.get())

                val settings2Boolean: Boolean by settings2.boolean.getFlow().collectAsState(initial = settings2.boolean.get())
                val settings2Int: Int by settings2.int.getFlow().collectAsState(initial = settings2.int.get())
                val settings2Long: Long by settings2.long.getFlow().collectAsState(initial = settings2.long.get())
                val settings2Float: Float by settings2.float.getFlow().collectAsState(initial = settings2.float.get())
                val settings2Double: Double by settings2.double.getFlow().collectAsState(initial = settings2.double.get())
                val settings2String: String by settings2.string.getFlow().collectAsState(initial = settings2.string.get())
                val settings2StringSet: Set<String> by settings2.stringSet.getFlow().collectAsState(initial = settings2.stringSet.get())

                Row(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Button(onClick = { settings1.boolean.put(!settings1Boolean) }) {
                            Text(text = "${settings1Boolean}")
                        }
                        Button(onClick = { settings1.int.put(settings1Int + 1) }) {
                            Text(text = "${settings1Int}")
                        }
                        Button(onClick = { settings1.long.put(settings1Long + 10L) }) {
                            Text(text = "${settings1Long}")
                        }
                        Button(onClick = { settings1.float.put(settings1Float + 0.1f) }) {
                            Text(text = "${settings1Float}")
                        }
                        Button(onClick = { settings1.double.put(settings1Double + 0.01) }) {
                            Text(text = "${settings1Double}")
                        }
                        Button(onClick = { settings1.string.put(settings1String + "A") }) {
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
                                settings1.stringSet.put(newValue)
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
                        Button(onClick = { settings2.boolean.put(!settings2Boolean) }) {
                            Text(text = "${settings2Boolean}")
                        }
                        Button(onClick = { settings2.int.put(settings2Int + 1) }) {
                            Text(text = "${settings2Int}")
                        }
                        Button(onClick = { settings2.long.put(settings2Long + 10L) }) {
                            Text(text = "${settings2Long}")
                        }
                        Button(onClick = { settings2.float.put(settings2Float + 0.1f) }) {
                            Text(text = "${settings2Float}")
                        }
                        Button(onClick = { settings2.double.put(settings2Double + 0.01) }) {
                            Text(text = "${settings2Double}")
                        }
                        Button(onClick = { settings2.string.put(settings2String + "A") }) {
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
                                settings2.stringSet.put(newValue)
                            },
                        ) {
                            Text(text = "${settings2StringSet}")
                        }
                    }
                }
            }
        }
    }
}
