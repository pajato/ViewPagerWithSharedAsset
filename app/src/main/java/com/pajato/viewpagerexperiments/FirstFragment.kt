package com.pajato.viewpagerexperiments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_layout.view.*

class FirstFragment : Fragment() {
    var title: String? = null
    var page: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt(PAGE_KEY)
        title = arguments?.getString(TITLE_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_layout, container, false)
        val label = view.fragment_text
        view.tag = page
        label.text = title
        return view
    }

    companion object {
        private const val PAGE_KEY = "page"
        private const val TITLE_KEY = "title"

        fun newInstance(page: Int, title: String): FirstFragment {
            val f = FirstFragment()
            val args = Bundle()
            args.putInt(PAGE_KEY, page)
            args.putString(TITLE_KEY, title)
            f.arguments = args
            return f
        }
    }

}