package com.example.adobedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.adobe.marketing.mobile.Assurance
import com.adobe.marketing.mobile.Edge
import com.adobe.marketing.mobile.EdgeCallback
import com.adobe.marketing.mobile.EdgeEventHandle
import com.adobe.marketing.mobile.ExperienceEvent
import com.adobe.marketing.mobile.Identity
import com.adobe.marketing.mobile.Lifecycle
import com.adobe.marketing.mobile.LoggingMode
import com.adobe.marketing.mobile.Messaging
import com.adobe.marketing.mobile.MobileCore
import com.adobe.marketing.mobile.Signal
import com.adobe.marketing.mobile.UserProfile
import com.adobe.marketing.mobile.edge.consent.Consent
import com.adobe.marketing.mobile.edge.identity.AuthenticatedState
import com.adobe.marketing.mobile.edge.identity.IdentityItem
import com.adobe.marketing.mobile.edge.identity.IdentityMap
import java.util.Arrays


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        MobileCore.setApplication(application); MobileCore.setLogLevel(LoggingMode.DEBUG)


        val extensions = Arrays.asList(
            Assurance.EXTENSION,
            Messaging.EXTENSION,
            com.adobe.marketing.mobile.edge.identity.Identity.EXTENSION,
            Identity.EXTENSION,
            Consent.EXTENSION,
            Edge.EXTENSION,
            UserProfile.EXTENSION,
            Lifecycle.EXTENSION,
            Signal.EXTENSION
        )


        MobileCore.registerExtensions(
            extensions
        ) {
            MobileCore.configureWithAppID("64acf8b07350/bdc68007b75d/launch-319526f6bf93-development")
        }


        Identity.getExperienceCloudId { s ->
            Log.d(
                "LOG_TAG",
                "ECID pulled by using getExperienceCloudId : $s"
            )
        }

        // send fcm token
        MobileCore.setPushIdentifier("abcdefghijklmnop123");



        val myButton: Button = findViewById(R.id.btnLogin)
        val logout: Button = findViewById(R.id.btnLogout)
        // Set a click listener
        myButton.setOnClickListener {
            val collectorIdentity = IdentityItem("1234567890", AuthenticatedState.AUTHENTICATED, true)
            val identityMap = IdentityMap()
            identityMap.addItem(collectorIdentity, "number")
            com.adobe.marketing.mobile.edge.identity.Identity.updateIdentities(identityMap);

            val xdmData = mutableMapOf<String, Any>(
                "eventType" to "mobile.user.login"
            )

            val experienceEvent = ExperienceEvent.Builder()
                .setXdmSchema(xdmData)
                .build()
            Edge.sendEvent(experienceEvent, null);

            startActivity(Intent(this,MainActivity2::class.java))
        }

        logout.setOnClickListener {
            val xdmData = mutableMapOf<String, Any>(
                "eventType" to "mobile.user.logout"
            )

            val experienceEvent = ExperienceEvent.Builder()
                .setXdmSchema(xdmData)
                .build()
            Edge.sendEvent(experienceEvent,null);

        }
    }

    override fun onPause() {
        super.onPause()
        MobileCore.lifecyclePause();
    }
     override fun onResume() {
        super.onResume()
        MobileCore.setApplication(application)
        MobileCore.lifecycleStart(null)
    }
}