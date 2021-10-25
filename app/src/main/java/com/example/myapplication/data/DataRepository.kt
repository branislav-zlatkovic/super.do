package com.example.myapplication.data

import com.example.myapplication.domain.error.WebSocketFailedException
import com.example.myapplication.domain.model.StoreItem
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import timber.log.Timber
import java.util.concurrent.TimeUnit

class DataRepository {

    companion object {
        const val CLOSED_NORMAL = 1000
    }

    private var webSocket: WebSocket? = null
    private var webSocketListener: StoreDataWebSocketListener = StoreDataWebSocketListener()

    private var socketOkHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    private val gson = Gson()

    val dataList: MutableList<StoreItem> = mutableListOf()

    private fun startWebSocket() {
        this.webSocket = socketOkHttpClient.newWebSocket(
            Request.Builder().url("ws://superdo-groceries.herokuapp.com/receive").build(),
            webSocketListener
        )
    }

    fun startReceivingData(): Observable<List<StoreItem>> {
        return Observable.create { emitter ->
            webSocketListener.callback = object : StoreDataWebSocketListener.DataCallback {
                override fun onDataReceived(data: String) {
                    val item = gson.fromJson(data, StoreItem::class.java)
                    dataList.add(0, item)
                    emitter.onNext(dataList.toMutableList())
                }

                override fun onSocketClosing() {
                    Timber.e("Socket closing")
                    emitter.onComplete()
                }

                override fun onSocketFailure() {
                    Timber.e("Socket failure")
                    emitter.onError(WebSocketFailedException())
                }
            }
            startWebSocket()
        }
    }

    fun stopReceivingData() {
        webSocketListener.callback = null
//        webSocket?.close(CLOSED_NORMAL, null)
        webSocket?.cancel()
        webSocket = null
    }

    fun isReceivingData() = webSocket != null
}