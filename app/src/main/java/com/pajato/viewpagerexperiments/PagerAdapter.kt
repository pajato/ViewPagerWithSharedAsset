package com.pajato.viewpagerexperiments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> FirstFragment.newInstance(0, "Fragment Index 0")
            1 -> FirstFragment.newInstance(1, "Fragment Index 1")
            2 -> FirstFragment.newInstance(2, "Fragment Index 2")
            else -> null
        }
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    companion object {
        const val NUM_ITEMS = 3
    }
}