package com.example.demo_android.compose_detect_windowinsets

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import kotlin.math.max

@SuppressLint("InternalInsetResource")
@Composable
fun rememberWindowInsetsInfoState(): State<WindowInsetsInfo> {
    val windowInsetsInfoState: MutableState<WindowInsetsInfo> = remember { mutableStateOf(WindowInsetsInfo()) }
    val rootView: View = LocalView.current.rootView
    val systemBarTopHeight: Int = rootView.resources.run {
        val id: Int = getIdentifier("status_bar_height", "dimen", "android")
        rootView.resources.getDimensionPixelSize(id)
    }
    val systemBarBottomHeight: Int = rootView.resources.run {
        val id: Int = getIdentifier("navigation_bar_height", "dimen", "android")
        rootView.resources.getDimensionPixelSize(id)
    }

    DisposableEffect(rootView) {
        val measurementView = View(rootView.context)
        val popupWindow = PopupWindow(measurementView, 0, WindowManager.LayoutParams.MATCH_PARENT).apply {
            softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
        }
        val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val screenHeight: Int = rootView.height
            val totalImeHeight: Int = screenHeight - Rect().apply { measurementView.getWindowVisibleDisplayFrame(this) }.bottom
            val onlyImeHeight: Int = max(0, totalImeHeight - systemBarBottomHeight)

            windowInsetsInfoState.value = windowInsetsInfoState.value.copy(
                screenHeight = screenHeight,
                systemBarTopHeight = systemBarTopHeight,
                systemBarBottomHeight = systemBarBottomHeight,
                totalImeHeight = totalImeHeight,
                onlyImeHeight = onlyImeHeight,
                isImeVisible = onlyImeHeight > 0,
            )
        }

        popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, 0, 0)
        measurementView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)

        return@DisposableEffect onDispose {
            measurementView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
            popupWindow.dismiss()
        }
    }

    return windowInsetsInfoState
}

/**
 * @property screenHeight Screen Height (inclusive [systemBarTopHeight] and [systemBarBottomHeight]) (px)
 * @property systemBarTopHeight Status Bar Height (px)
 * @property systemBarBottomHeight Navigation Bar Height (px)
 * @property totalImeHeight Keyboard Height (inclusive [systemBarBottomHeight]) (px)
 * @property onlyImeHeight Keyboard Height (exclusive [systemBarBottomHeight]) (px)
 */
data class WindowInsetsInfo(
    val screenHeight: Int = 0,
    val systemBarTopHeight: Int = 0,
    val systemBarBottomHeight: Int = 0,
    val totalImeHeight: Int = 0,
    val onlyImeHeight: Int = 0,
    val isImeVisible: Boolean = false,
)
