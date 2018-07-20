package com.example.android.basicnotifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.RemoteInput
import android.util.Log

class MessageReplyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val conversationId = intent.getIntExtra("conversation_id", -1)

        val replyText = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("voice_reply_key")

        Log.d("BasicNotifications", "Found voice reply [$replyText] from conversation")
    }
}
