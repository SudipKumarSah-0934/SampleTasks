package com.example.tasks.services


import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService : AccessibilityService() {
    private  val TAG = "MyAccessibilityService"

    override fun onInterrupt() {
        Log.e(TAG, "onInterrupt: Something went wrong!")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.e(TAG, "onAccessibilityEvent: ")
        val packageName: String = event!!.packageName.toString()
        val packageManager = this.packageManager
        try {
            val applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            val applicationLabel: CharSequence = packageManager.getApplicationLabel(applicationInfo)
            Log.e(TAG, "app name is $applicationLabel")
            if (applicationLabel=="WhatsApp"){
            Toast.makeText(applicationContext,"WhatsApp launched",Toast.LENGTH_SHORT).show()}
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }
    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo()
        info.apply {
            // Set the type of events that this service wants to listen to. Others
            // won't be passed to this service.
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED

            // If you only want this service to work with specific applications, set their
            // package names here. Otherwise, when the service is activated, it will listen
            // to events from all applications.

            // Set the type of feedback your service will provide.
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            // Default services are invoked only if no package-specific ones are present
            // for the type of AccessibilityEvent generated. This service *is*
            // application-specific, so the flag isn't necessary. If this was a
            // general-purpose service, it would be worth considering setting the
            // DEFAULT flag.

            // flags = AccessibilityServiceInfo.DEFAULT;

            notificationTimeout = 100
        }

        this.serviceInfo = info
        Log.d(TAG, "onServiceConnected: ")
    }
}