package org.cocoapods.podst_fundacja

import android.animation.AnimatorSet
import android.content.res.Configuration
import android.graphics.Color
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.*
import com.google.android.gms.ads.AdRequest

import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import kotlinx.android.synthetic.main.menu_main.*
import android.graphics.drawable.AnimatedVectorDrawable
import android.animation.ObjectAnimator
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.util.Log
import org.cocoapods.podst_fundacja.adapters.PagerAdapter
import org.cocoapods.podst_fundacja.supports.DrawerListener


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mSectionsPagerAdapter: PagerAdapter? = null
    private var open = true
    private var lastSlidePos = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // prepare UI
        setUI()

        // set up buttons actions and animations
        setUpListeners()
    }

    private fun setUI() {
        setContentView(R.layout.menu_main)

        // making a transparent navigation bar and status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT

        // Set up the ViewPager with the sections adapter.
        mSectionsPagerAdapter = PagerAdapter(supportFragmentManager)
        view_pager.adapter = mSectionsPagerAdapter

        // prepare Ads
        placeAd()

        // listening button is pressed in menu
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun placeAd() {
        var navigationBarHeight = 0
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0 && resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE)
            navigationBarHeight = resources.getDimensionPixelSize(resourceId)
        val p = ad_view.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(0, 0, 0, navigationBarHeight)
        ad_view.requestLayout()

        val adRequest = AdRequest.Builder().build()
        ad_view.loadAd(adRequest)
    }

    private fun setUpListeners() {
        menu_button_layout.setOnClickListener {
            nav_drawer.openDrawer(Gravity.END)
        }

        nav_drawer.addDrawerListener(object : DrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                animateTranslation(slideOffset)
            }

            override fun onDrawerStateChanged(newState: Int) {
                animateButton(newState)
            }
        })
    }

    private fun animateButton(newState: Int) {
        val openLast = open
        open = nav_drawer.isDrawerOpen(Gravity.END)
        if (newState == DrawerLayout.STATE_IDLE && open != openLast) {
            val mAnimation: AnimatedVectorDrawable
            if (!open) {
                mAnimation = getDrawable(R.drawable.avd_close_menu)
                        as AnimatedVectorDrawable
            } else {
                mAnimation = getDrawable(R.drawable.avd_open_menu)
                        as AnimatedVectorDrawable
            }
            menu_button.setImageDrawable(mAnimation)
            mAnimation.start()
        }
    }

    private fun animateTranslation(slideOffset: Float) {
        val mover = ObjectAnimator.ofFloat(menu_button_layout,
                "translationX", lastSlidePos, -slideOffset * resources.getDimension(R.dimen.menu_size))
        mover.duration = 20

        lastSlidePos = -slideOffset * resources.getDimension(R.dimen.menu_size)

        val animatorSet = AnimatorSet()
        animatorSet.play(mover)
        animatorSet.start()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        nav_drawer.closeDrawer(Gravity.END)
        Log.d("Menu item", "is: " + item.order)

        return true
    }

    override fun onBackPressed() {
        if (nav_drawer.isDrawerOpen(GravityCompat.START)) {
            nav_drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
