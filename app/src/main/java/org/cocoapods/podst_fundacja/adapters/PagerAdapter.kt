package org.cocoapods.podst_fundacja.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import org.cocoapods.podst_fundacja.fragments.MainFragment
import org.cocoapods.podst_fundacja.fragments.SecondFragment
import org.cocoapods.podst_fundacja.fragments.ThirdFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    private val fragments = listOf(MainFragment(), SecondFragment(), ThirdFragment())

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        return fragments[position]
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}