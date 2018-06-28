package org.cocoapods.podst_fundacja.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.fragment_second.view.*
import org.cocoapods.podst_fundacja.R
import org.cocoapods.podst_fundacja.controllers.TimeOfDay


class SecondFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_second, container, false)
        rootView.pulse_image.startAnnimation()

        rootView.main_background.setImageResource(TimeOfDay.checkTimeOfDay(2))

        loadAd(rootView)



        return rootView
    }

    private fun loadAd(rootView : View) {
        val interstitialAd = InterstitialAd(context)
        interstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        val adRequest = AdRequest.Builder().build()
        interstitialAd.loadAd(adRequest)

        rootView.big_ad_button.isClickable = false
        rootView.big_ad_button.setOnClickListener {
            when {
                interstitialAd.isLoaded -> {
                    interstitialAd.show()
                    rootView.big_ad_button.isClickable = false
                }
                else -> {interstitialAd.loadAd(adRequest)
                    rootView.big_ad_button.isClickable = false
                }
            }
        }
        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                interstitialAd.loadAd(adRequest)
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                rootView.info.setText(R.string.connection_ok)
                rootView.big_ad_button.isClickable = true
            }

            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                rootView.info.setText(R.string.no_connection)
                rootView.big_ad_button.isClickable = true
            }
        }
    }

}
