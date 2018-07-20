package com.example.android.basicnotifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MessageHeardReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val conversationId = intent.getIntExtra("conversation_id", -1)
        Log.d("BasicNotifications", "MessageHeardReceiver conversation_id=$conversationId")
    }
}
