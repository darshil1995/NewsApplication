package com.app.newsapplication.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapplication.R
import com.app.newsapplication.adapter.NewsResponseAdapter
import com.app.newsapplication.adapter.NewsResponseTypeAdapter
import com.app.newsapplication.util.Constants.Companion.PAGE_SIZE
import com.app.newsapplication.util.Constants.Companion.TOTAL_RESULTS
import com.app.newsapplication.util.Resource
import com.app.newsapplication.views.MainActivity
import com.app.newsapplication.views.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.delay

class NewsFragment : Fragment(R.layout.fragment_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsResponseAdapter: NewsResponseAdapter
    lateinit var newsResponseTypeAdapter: NewsResponseTypeAdapter
    var isFilter = false
    val TAG = "NEWSFRAGMENT"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initViews()

        newsResponseAdapter.setOnItemClickListener { newsResponseItem ->
            val bundle = Bundle().apply {
                putSerializable("newsresponseitem", newsResponseItem)
            }
            Navigation.findNavController(view).navigate(
                R.id.action_newsFragment_to_detailedFragment3,
                bundle
            )
        }

        newsResponseTypeAdapter.setOnItemClickListener { item ->
            isFilter = true
            val list = viewModel.newsResponse?.toList()?.filter { newsResponseItem ->
                newsResponseItem.type.equals(item?.type)
            }
            newsResponseAdapter.differ.submitList(list)
            (activity as MainActivity).title=item?.type?.uppercase()
        }

        viewModel.news.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->

                        Log.e(TAG, "Response: ${response.data}")
                        newsResponseAdapter.differ.submitList(newsResponse.toList())

                        //For Types Filter
                        viewModel.newsTypeList?.forEach { newsResponseItem ->
                            Log.i(TAG, "Type : " + newsResponseItem.type)
                            newsResponseTypeAdapter.differ.submitList(viewModel.newsTypeList)
                        }

                        val totalPages =
                            TOTAL_RESULTS / PAGE_SIZE + 2 //Add 2 because the division returns Integer and secondly the last page empty
                        isLastPage = viewModel.newsPage == totalPages
                        if (isLastPage) {
                            rvNews.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error:  $message")
                        Toast.makeText(activity, "An Error Occured: $message", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })

    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBegining = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBegining &&
                    isTotalMoreThanVisible && isScrolling

            if (shouldPaginate && !isFilter) {
                viewModel.getNews(viewModel.category)
                isScrolling = false
            }
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }


    private fun initViews() {
        newsResponseAdapter = NewsResponseAdapter()
        rvNews.apply {
            adapter = newsResponseAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.scrollListener)
        }

        newsResponseTypeAdapter = NewsResponseTypeAdapter()
        rvTypes.apply {
            adapter = newsResponseTypeAdapter
            layoutManager = LinearLayoutManager(activity).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        btnAllNews.apply {
            setOnClickListener {
                isFilter = false
                (activity as MainActivity).title="ALL NEWS"
                viewModel.getNews(viewModel.category)
            }
        }
    }
}