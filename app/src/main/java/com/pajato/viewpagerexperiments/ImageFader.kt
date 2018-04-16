package com.pajato.viewpagerexperiments

import android.support.v4.view.ViewPager
import android.widget.ImageView

/**
 * @param pager The View Pager being listened to.
 * @param image The ImageView that will be displayed when the viewpager is idle.
 * @param background_image A second ImageView that is used for fading between fragment layout images.
 */
internal class ImageFader(private val pager: ViewPager, private val image: ImageView,
                          private val background_image: ImageView) : ViewPager.OnPageChangeListener {
    private var checkDirection: Boolean = true
    private var scrollStarting: Boolean = false
    private var currentItem: Int = 0
    private var swipingLeft: Boolean = false

    /** Track when scrolling and when we settle, update the current item. */
    override fun onPageScrollStateChanged(state: Int) {
        if (!scrollStarting && state == ViewPager.SCROLL_STATE_DRAGGING) {
            scrollStarting = true
            checkDirection = true
        } else {
            scrollStarting = false
        }

        if (state == ViewPager.SCROLL_STATE_SETTLING) {
            currentItem = pager.currentItem
            image.setImageResource(getImageResource(currentItem))
        }
    }

    /** Fade between the image and the background image during scrolls. */
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        // Determine the direction. This should only be done once during any scroll.
        if (checkDirection) {
            swipingLeft = thresholdOffset > positionOffset
            val id = getBackgroundImageResourceId(currentItem, swipingLeft)
            background_image.setImageResource(id)
            checkDirection = false
        }

        // Fade one image out and one image in. The way we fade images changes depending on our direction.
        var imageAlpha = (positionOffset - 0.25f) * 2
        imageAlpha = if (imageAlpha > 1.0f) 1.0f else imageAlpha
        imageAlpha = if (imageAlpha < 0.0f) 0.0f else imageAlpha
        if (swipingLeft) {
            image.alpha = 1.0f - imageAlpha
            background_image.alpha = imageAlpha
        } else {
            image.alpha = imageAlpha
            background_image.alpha = 1 - imageAlpha
        }
    }

    /** A helper method to establish which resource id should be used as our "background" view. */
    private fun getBackgroundImageResourceId(currentItem: Int, swipingLeft: Boolean): Int {
        val index = if (swipingLeft) {
            if (currentItem + 1 <= PagerAdapter.NUM_ITEMS - 1) {
                currentItem + 1
            } else {
                PagerAdapter.NUM_ITEMS - 1
            }
        } else {
            if (currentItem - 1 >= 0) {
                currentItem - 1
            } else {
                0
            }
        }
        return getImageResource(index)
    }

    /** A helper method to establish which resource id should be used as the primary view. */
    private fun getImageResource(index: Int): Int {
        return when (index) {
            0 -> R.drawable.ic_filter_1_black_24dp
            1 -> R.drawable.ic_filter_2_black_24dp
            2 -> R.drawable.ic_filter_3_black_24dp
            else -> -1
        }
    }


    /// Unused but is required to be implemented by ViewPager.OnPageChangeListener.
    override fun onPageSelected(position: Int) {}

    // const vals must be inside a companion object to make them "static".
    companion object {
        const val thresholdOffset: Float = 0.5f
    }
}