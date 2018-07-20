package com.example.android.basicnotifications

import android.app.Activity
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.RemoteInput
import android.view.View

/**
 * The entry point to the BasicNotification sample.
 */
class MainActivity : Activity() {

    companion object {
        /**
         * A numeric value that identifies the notification that we'll be sending.
         * This value needs to be unique within this app, but it doesn't need to be
         * unique system-wide.
         */
        val NOTIFICATION_ID = 1
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_layout)

    }

    /**
     * Send a sample notification using the NotificationCompat API.
     */
    fun sendNotification(view: View) {

        // BEGIN_INCLUDE(build_action)
        /** Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a [android.app.PendingIntent] so that the
         * notification service can fire it on our behalf.
         */
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        val thisConversationId = 42
        val msgHeardIntent = Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction("com.example.android.basicnotifications.MY_ACTION_MESSAGE_HEARD")
                .putExtra("conversation_id", thisConversationId)
                .setPackage("com.example.android.basicnotifications")       // This can avoid intercepting broadcast by other apps

        val msgHeardPendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                thisConversationId,
                msgHeardIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val msgReplyIntent = Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction("com.example.android.basicnotifications.MY_ACTION_MESSAGE_REPLY")
                .putExtra("conversation_id", thisConversationId)
                .setPackage("com.example.android.basicnotifications")       // This can avoid intercepting broadcast by other apps

        val msgReplyPendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                thisConversationId,
                msgReplyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val remoteInput = RemoteInput.Builder("voice_reply_key")
                .setLabel("Prompt text")
                .build()

        val conversationName = "Duky"
        val unreadConversation = NotificationCompat.CarExtender.UnreadConversation.Builder(conversationName)
                .setReadPendingIntent(msgHeardPendingIntent)
                .setReplyAction(msgReplyPendingIntent, remoteInput)

        unreadConversation.addMessage("Hello there, how are you?")
                .setLatestTimestamp(System.currentTimeMillis())

        // END_INCLUDE(build_action)



        // BEGIN_INCLUDE (build_notification)
        /**
         * Use NotificationCompat.Builder to set up our notification.
         */
        val builder = NotificationCompat.Builder(this)

        /** Set the icon that will appear in the notification bar. This icon also appears
         * in the lower right hand corner of the notification itself.
         *
         * Important note: although you can use any drawable as the small icon, Android
         * design guidelines state that the icon should be simple and monochrome. Full-color
         * bitmaps or busy images don't render well on smaller screens and can end up
         * confusing the user.
         */
        builder.setSmallIcon(R.drawable.ic_stat_notification)

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent)

        // Set the notification to auto-cancel. This means that the notification will disappear
        // after the user taps it, rather than remaining until it's explicitly dismissed.
        builder.setAutoCancel(true)

        val builderForCarExtender = NotificationCompat.Builder(this)
        builderForCarExtender.extend(
            NotificationCompat.CarExtender().setUnreadConversation(unreadConversation.build())
        )

        /**
         * Build the notification's appearance.
         * Set the large icon, which appears on the left of the notification. In this
         * sample we'll set the large icon to be the same as our app icon. The app icon is a
         * reasonable default if you don't have anything more compelling to use as an icon.
         */
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))

        /**
         * Set the text of the notification. This sample sets the three most commononly used
         * text areas:
         * 1. The content title, which appears in large type at the top of the notification
         * 2. The content text, which appears in smaller text below the title
         * 3. The subtext, which appears under the text on newer devices. Devices running
         * versions of Android prior to 4.2 will ignore this field, so don't use it for
         * anything vital!
         */
        builder.setContentTitle("BasicNotifications Sample")
        builder.setContentText("Time to learn about notifications!")
        builder.setSubText("Tap to view documentation about notifications.")

        // END_INCLUDE (build_notification)



        // BEGIN_INCLUDE(send_notification)
        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         */
//        val notificationManager = getSystemService(
//                Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(NOTIFICATION_ID, builder.build())

        // An standard Manager(android.app.NotificationManager under API 10) may not know Car extensions
        val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
        notificationManagerCompat.notify(NOTIFICATION_ID, builderForCarExtender.build())
        // END_INCLUDE(send_notification)
    }
}
