
package com.example.demo_android.gemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = "" // https://aistudio.google.com
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var prompt: String by remember { mutableStateOf("미국의 수도는?") }
            var content: String by remember { mutableStateOf("content") }

            Box(modifier = Modifier.fillMaxSize()) {
                TextField(
                    modifier = Modifier.align(Alignment.TopCenter),
                    value = prompt,
                    onValueChange = {
                        prompt = it
                    },
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = content,
                )
                Button(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = {
                        runBlocking {
                            content = generateContent(prompt)
                        }
                    },
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }

    suspend fun generateContent(prompt: String): String {
        return try {
            val response: GenerateContentResponse = generativeModel.generateContent(prompt)
            response.text ?: "응답을 생성하지 못했습니다."
        } catch (e: Exception) {
            "오류 발생: ${e.message}"
        }
    }
}
