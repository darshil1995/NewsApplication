package com.app.newsapplication.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.newsapplication.R
import com.app.newsapplication.db.NewsResponseItemDatabase
import com.app.newsapplication.repository.NewsRepository


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing the database and ViewModel
        val newsRepository = NewsRepository(NewsResponseItemDatabase(this))
        val viewModelProviderFactory = NewViewModelProviderFactory(application,newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
    }
}