package com.app.newsapplication.views.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app.newsapplication.R
import com.app.newsapplication.views.MainActivity
import com.app.newsapplication.views.NewsViewModel
import kotlinx.android.synthetic.main.fragment_detailed.*

class DetailedFragment : Fragment(R.layout.fragment_detailed) {

    lateinit var viewModel: NewsViewModel
    val args: DetailedFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val newsItem = args?.newsresponseitem
        webView.apply {
            webViewClient = WebViewClient()
            newsItem?.typeAttributes?.url?.let { loadUrl(it) }
        }
    }
}