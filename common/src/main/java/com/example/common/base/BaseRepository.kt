package com.example.common.base

import com.example.common.api.ApiException
import com.google.gson.JsonParseException
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.SocketTimeoutException


typealias Block<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit

open class BaseRepository {

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @param error 错误时执行
     * @return Job
     */
     fun launch(block: Block<Unit>, error: Error? = null, cancel: Cancel? = null): Job?{
        return GlobalScope.launch {
            try {
                block.invoke()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        onError(e)
                        error?.invoke(e)
                    }
                }
            }
        }
    }

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @return Deferred<T>
     */
    protected fun <T> async(block: Block<T>): Deferred<T> {
        return GlobalScope.async { block.invoke() }
    }

    /**
     * 取消协程
     * @param job 协程job
     */
    protected fun cancelJob(job: Job?) {
        if (job != null && job.isActive && !job.isCompleted && !job.isCancelled) {
            job.cancel()
        }
    }

    /**
     * 统一处理错误
     * @param e 异常
     */
    private fun onError(e: Exception) {
        when (e) {
            is ApiException -> {
                when (e.code) {

                }
            }
            is ConnectException -> {
                // 连接失败
            }
            is SocketTimeoutException -> {
                // 请求超时
            }
            is JsonParseException -> {
                // 数据解析错误
            }
            else -> {
                // 其他错误
                e.message?.let { }
            }
        }
    }

}