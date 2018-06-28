package org.cocoapods.podst_fundacja.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.cocoapods.podst_fundacja.R
import org.cocoapods.podst_fundacja.controllers.TimeOfDay

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        rootView.main_background.setImageResource(TimeOfDay.checkTimeOfDay(1))

        return rootView
    }
}