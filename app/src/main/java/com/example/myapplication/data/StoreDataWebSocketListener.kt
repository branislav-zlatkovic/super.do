package com.example.myapplication.data

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class StoreDataWebSocketListener : WebSocketListener() {

    interface DataCallback {
        fun onDataReceived(data: String)
        fun onSocketClosing()
        fun onSocketFailure()
    }

    var callback: DataCallback? = null

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        callback?.onSocketClosing()
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        callback?.onSocketClosing()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        callback?.onSocketFailure()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        callback?.onDataReceived(text)
    }
}