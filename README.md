# Android Auto Development
- Class: https://www.udacity.com/course/android-auto-development--ud875C
- Developer guide: https://developer.android.com/training/auto/

### 1. Requirements
- Higher or Android 5.0(API 21) can support Android Auto


### 2. Android Auto support for messaging
>   * 2.1. AndroidManifest.xml
>       * Support sign in Application tag
>   ~~~xml
>   <meta-data android:name="com.google.android.gms.car.application"
>           android:resource="@xml/automotive_app_desc"/>
>   ~~~
>   * 2.2. Define "automotive_app_desc.xml"
>   
- Android Auto simulators for test on mobile
  - Messaging: ~/Library/Android/sdk/extras/google/simulators/messaging-simulator.apk
  - Audio: ~/Library/Android/sdk/extras/google/simulators/media-browser-simulator.apk


MessagingService
- Fragment >> Handler message >> Service >> Conversations
