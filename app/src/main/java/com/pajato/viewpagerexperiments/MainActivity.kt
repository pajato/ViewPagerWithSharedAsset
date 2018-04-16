package com.pajato.viewpagerexperiments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Attach the adapter and onPageChangeListener.
        pager.adapter = PagerAdapter(supportFragmentManager)
        pager.addOnPageChangeListener(ImageFader(pager, image, background_image))
    }

}
