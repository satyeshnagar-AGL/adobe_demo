package com.example.adobedemo


import com.adobe.marketing.mobile.Edge;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.edge.consent.Consent;

import android.app.Application;
import com.adobe.marketing.mobile.Identity
import com.adobe.marketing.mobile.Lifecycle

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileCore.setApplication(this)


        try {
            Lifecycle.registerExtension();
            Edge.registerExtension();
            Consent.registerExtension(); // register Consent Identity.registerExtension();
            Identity.registerExtension();
            MobileCore.start {
                // Configure with App ID when SDK starts successfully
                MobileCore.configureWithAppID("64acf8b07350/bdc68007b75d/launch-319526f6bf93-development")
            }
        } catch (e: Exception) {
            // Log the exception
            e.printStackTrace() // or use any logging framework you prefer
        }


    }
}