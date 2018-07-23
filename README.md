# Android Auto Development
- Class: https://www.udacity.com/course/android-auto-development--ud875C
- Developer guide: https://developer.android.com/training/auto/


### 1. Requirements
* Android 5.0(API 21) or higher can support Android Auto
    * "CarExtender" is needed.
    * It is recommended to use "~~Compat" libraries for compatibility on lower APIs.

* Android Auto simulators for test on mobile
    * Messaging: ~/Library/Android/sdk/extras/google/simulators/messaging-simulator.apk
    * Audio: ~/Library/Android/sdk/extras/google/simulators/media-browser-simulator.apk


### 2. Configuration for Android Auto
* 2.1. AndroidManifest.xml
>   * Support sign in Application tag
>   * Commit: https://github.com/ndukwon/learning_Android_Auto/commit/badf79631c7f97b5545a4aa935a88e03eaaad70e
>   ~~~xml
>   <meta-data android:name="com.google.android.gms.car.application"
>           android:resource="@xml/automotive_app_desc"/>
>   ~~~


* 2.2. Define "automotive_app_desc.xml"
>   * Define which type of service on Android Auto
>       * Now there are 2 types available.
>       1. Notification for messaging: read and reply
>       2. Audio playback
>   ~~~xml
>   <?xml version="1.0" encoding="utf-8"?>
>   <automotiveApp>
>       
>       <!-- Notification for Messaging -->
>       <uses name="notification" />
>       
>       <!-- Audio playback -->
>       <uses name="media"/>
>       
>   </automotiveApp>
>   ~~~

###3. Messaging Notification part
* 3.0. given conversation ID
* 3.1. an **Intent** to be sent when **TTS finished reading the message**
* 3.2. a **Receiver** to receive the TTS-finished intent.
* 3.3. an **Intent** to be sent when the **reply action is started**.
* 3.4. a **Receiver** to receive the reply-action intent.
* 3.5. a **RemoteInput** to type text by voice input
* 3.6. Packing each item below within **PendingIntent**
    * Conversation id + TTS-finished intent
    * Conversation id + reply-action intent
* 3.7. Packing all together within **CarExtender.UnreadConversation**
    * Conversation name
    * TTS-finished intent
    * reply-action intent
    * message(s)
* 3.8. Packing the UnreadConversation within **CarExtender**
* 3.9. Packing the CarExtender within **NotificationCompat.builder**
* 3.10. Notify the NotificationCompat by **NotificationManagerCompat**


###4. Audio part....
* 4.1. 



MessagingService
- Fragment >> Handler message >> Service >> Conversations
