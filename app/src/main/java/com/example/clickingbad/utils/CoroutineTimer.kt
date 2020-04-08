package com.example.clickingbad.utils

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

inline fun startCoroutineTimer(
    delayMillis: Long = 0,
    repeatMillis: Long = 0,
    crossinline action: () -> Unit
) = GlobalScope.launch {

    delay(delayMillis)
    if (repeatMillis > 0) {
        while (true) {
            action()
            delay(repeatMillis)
        }
    } else {
        action()
    }

}
