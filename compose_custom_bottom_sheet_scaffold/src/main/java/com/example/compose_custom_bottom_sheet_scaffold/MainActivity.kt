package com.example.compose_custom_bottom_sheet_scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.theme.Demo_AndroidTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Demo_AndroidTheme {
                var isListScrolling: Boolean by remember { mutableStateOf(false) }
                val lazyListState: LazyListState = rememberLazyListState()

                CustomBottomSheetScaffold(
                    isListScrolling = { isListScrolling },
                    isListTop = { !lazyListState.canScrollBackward },
                    sheetContent = { nestedScrollConnection: NestedScrollConnection ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .nestedScroll(nestedScrollConnection)
                                    .pointerInput(Unit) {
                                        awaitPointerEventScope {
                                            while (true) {
                                                val pointerEvent: PointerEvent = awaitPointerEvent(pass = PointerEventPass.Main)
                                                when (pointerEvent.type) {
                                                    PointerEventType.Press -> {
                                                        isListScrolling = true
                                                    }
                                                    PointerEventType.Release -> {
                                                        isListScrolling = false
                                                    }
                                                }
                                            }
                                        }
                                    },
                                state = lazyListState,
                            ) {
                                items(100) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.DarkGray)
                                            .wrapContentSize(Alignment.Center),
                                        text = "${it}",
                                        color = Color.White,
                                        style = TextStyle(fontSize = 30.sp)
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                }
                            }
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun CustomBottomSheetScaffold(
    sheetCollapsedY: Dp = LocalDensity.current.run { (LocalConfiguration.current.screenHeightDp.dp * 0.8f) },
    sheetExpandedY: Dp = LocalDensity.current.run { (LocalConfiguration.current.screenHeightDp.dp * 0.2f) },
    sheetTopRadius: Dp = 16.dp,
    isListScrolling: () -> Boolean = { false },
    isListTop: () -> Boolean = { true },
    onSheetMove: (fraction: Float, y: Float, collapsedY: Float, expandedY: Float) -> Unit = { _, _, _, _ -> },
    containerContent: @Composable BoxScope.() -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        )
    },
    sheetContent: @Composable BoxScope.(NestedScrollConnection) -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
        )
    },
) {
    val sheetCollapsedYPx: Float = LocalDensity.current.run { sheetCollapsedY.toPx() }
    val sheetExpandedYPx: Float = LocalDensity.current.run { sheetExpandedY.toPx() }
    var sheetYPx: Float by remember { mutableFloatStateOf(sheetCollapsedYPx) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return if (sheetYPx == sheetExpandedYPx) Offset.Zero else available
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                return if (sheetYPx == sheetExpandedYPx) Velocity.Zero else available
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                return Velocity.Zero
            }
        }
    }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var settleJob: Job? by remember { mutableStateOf(null) }
    fun settle(): Job = coroutineScope.launch {
        animate(
            initialValue = sheetYPx,
            targetValue = if (sheetYPx - sheetExpandedYPx < sheetCollapsedYPx - sheetYPx) sheetExpandedYPx else sheetCollapsedYPx,
            initialVelocity = 0f,
            animationSpec = spring(),
        ) { value: Float, _ ->
            sheetYPx = value
            onSheetMove(1f - ((sheetYPx - sheetExpandedYPx) / (sheetCollapsedYPx - sheetExpandedYPx)), sheetYPx, sheetCollapsedYPx, sheetExpandedYPx)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        containerContent()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp - sheetExpandedY)
                .offset(y = LocalDensity.current.run { sheetYPx.toDp() })
                .clip(RoundedCornerShape(topStart = sheetTopRadius, topEnd = sheetTopRadius))
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val pointerEvent: PointerEvent = awaitPointerEvent(pass = PointerEventPass.Main)
                            when (pointerEvent.type) {
                                PointerEventType.Press -> {
                                    settleJob?.cancel()
                                }
                                PointerEventType.Move -> {
                                    if (!isListScrolling() || sheetYPx != sheetExpandedYPx || isListTop()) {
                                        val pointerOffsetYPx: Float = pointerEvent.changes.first().run { position.y - previousPosition.y }
                                        sheetYPx = (sheetYPx + pointerOffsetYPx).coerceIn(sheetExpandedYPx, sheetCollapsedYPx)
                                        onSheetMove(1f - ((sheetYPx - sheetExpandedYPx) / (sheetCollapsedYPx - sheetExpandedYPx)), sheetYPx, sheetCollapsedYPx, sheetExpandedYPx)
                                    }
                                }
                                PointerEventType.Release -> {
                                    settleJob = settle()
                                }
                            }
                        }
                    }
                },
        ) {
            sheetContent(nestedScrollConnection)
        }
    }
}
