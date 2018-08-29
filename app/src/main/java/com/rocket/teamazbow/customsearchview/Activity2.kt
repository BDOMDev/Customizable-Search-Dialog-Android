package com.rocket.teamazbow.customsearchview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.SearchManager
import android.content.Intent

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val intent = intent

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchDialogManager.SEARCH)
            val suggestions = SearchDialogManager(this)
            suggestions.saveSearchQuery(query)
        }

    }
}
