package com.example.adobedemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adobe.marketing.mobile.AdobeCallback
import com.adobe.marketing.mobile.Edge
import com.adobe.marketing.mobile.ExperienceEvent
import com.adobe.marketing.mobile.edge.consent.Consent

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val xdmData = mutableMapOf<String, Any>()
        val customXdmData = mutableMapOf<String, Any>()

        xdmData["_maruti"] = mutableMapOf<String, Any>().apply {
            this["userInfo"] = mutableMapOf<String, Any>().apply {
                this["authenticatedState"] = "<authenticated/unauthenticated>"
            }
            this["identities"] = mutableMapOf<String, Any>().apply {
                this["ecid"] = "<adobe-ecid>" // Need to discuss??
                this["hashedphoneSHA256"] = "1234567890"
            }
            xdmData["eventType"] = "web.webPageDetails.pageViews"

            val experienceEvent = ExperienceEvent.Builder()
                .setXdmSchema(xdmData)
                .build()

            Edge.sendEvent(experienceEvent, null)

        }


        var click:Button =findViewById(R.id.click)
        var retrive:Button =findViewById(R.id.retrive)



        retrive.setOnClickListener {
            Consent.getConsents(object : AdobeCallback<Map<String, Any>> {
                override fun call(currentConsents: Map<String, Any>) {
                    Log.d("TAGAAAAA", "call: "+currentConsents.toString())
                    // handle currentConsents
                }
            })
        }

        click.setOnClickListener {
            val collectConsents = mutableMapOf<String, Any>().apply {
                this["idSpecific"] = mutableMapOf<String, Any>().apply {
                    this["ECID"] = mutableMapOf<String, Any>().apply {
                        this["60685851533969143787454588399590351399"] = mutableMapOf<String, Any>().apply {
                            this["marketing"] = mutableMapOf<String, Any>().apply {
                                this["push"] = mutableMapOf<String, Any>().apply {
                                    this["val"] = "y" // y- Yes (opt-in), n- No (opt-out), p- Pending verification, u- Unknown, dy- Default of Yes (opt-in), dn- Default of No (opt-out), LI- Legitimate Interest, CT- Contract, CP- Compliance with a Legal Obligation, VI- Vital Interest of the Individual, PI- Public Interest
                                }
                            }
                        }
                    }
                }
            }

            val consents = mutableMapOf<String, Any>().apply {
                this["consents"] = collectConsents
            }

            Consent.update(consents)

        }

    }
}