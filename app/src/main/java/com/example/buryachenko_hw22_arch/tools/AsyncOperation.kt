package com.example.buryachenko_hw22_arch.tools

import com.example.buryachenko_hw22_arch.data.Post
import com.example.buryachenko_hw22_arch.domain.PostModel
import java.lang.Exception

fun interface CancellableOperation {
    fun cancel()
}

class AsyncOperation<T>(
    private val operation: () -> T,
    private val mainHandler: android.os.Handler,
    private val threadCreation: (Runnable) -> Thread
) {
    fun postOnMainThread(callback: (T) -> Unit): CancellableOperation {
        val activeThread = threadCreation {
            try {
                val result = operation()
                if(!Thread.currentThread().isInterrupted) {
                    mainHandler.post {
                        callback(result)
                    }
                }
            } catch (ignore: Exception) {}
        }

        return CancellableOperation {
            activeThread.interrupt()
        }
    }

    fun <R> map(transformation: (T) -> R): AsyncOperation<R> {
        return AsyncOperation(
            operation = { transformation(operation()) },
            mainHandler = mainHandler,
            threadCreation = threadCreation
        )
    }
}
