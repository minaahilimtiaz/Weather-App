package com.example.android.weatherapp.fragment

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.utilities.MAX_PROGRESS
import com.example.android.weatherapp.utilities.MIN_PROGRESS

open class BaseFragment : Fragment() {

    fun checkErrorStatus(errorOccurred: Boolean, progressBar: ProgressBar, errorView: TextView,
        errorString: String, cityText: TextView, searchButton: Button,
        recyclerView: RecyclerView) {
        if (errorOccurred) {
            hideProgressBar(progressBar)
            showError(errorView, errorString)
            hideSearchViews(cityText, searchButton)
            hideRecyclerView(recyclerView)
        } else {
            showProgressBar(progressBar)
            hideError(errorView)
        }
    }

    fun checkLoadingStatus(
        isLoaded: Boolean, progressBar: ProgressBar, cityText: TextView,
        searchButton: Button, recyclerView: RecyclerView
    ) {
        if (isLoaded) {
            hideProgressBar(progressBar)
            showSearchViews(cityText, searchButton)
            showRecyclerView(recyclerView)
        } else {
            showProgressBar(progressBar)
            hideSearchViews(cityText, searchButton)
            hideRecyclerView(recyclerView)
        }
    }

    private fun hideRecyclerView(recyclerView: RecyclerView) {
        recyclerView.visibility = View.GONE
    }

    private fun showRecyclerView(recyclerView: RecyclerView) {
        recyclerView.visibility = View.VISIBLE
    }

    private fun hideSearchViews(cityText: TextView, searchButton: Button) {
        cityText.apply {
            visibility = View.GONE
        }

        searchButton.apply {
            visibility = View.GONE
        }

    }

    private fun showSearchViews(cityText: TextView, searchButton: Button) {
        cityText.apply {
            visibility = View.VISIBLE
        }

        searchButton.apply {
            visibility = View.VISIBLE
        }

    }

    private fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.apply {
            progress = MAX_PROGRESS
            visibility = View.INVISIBLE
        }
    }

    private fun showProgressBar(progressBar: ProgressBar) {
        progressBar.apply {
            progress = MIN_PROGRESS
            visibility = View.VISIBLE
        }
    }

    private fun showError(errorView: TextView, errorString: String) {
        errorView.apply {
            visibility = View.VISIBLE
            text = errorString
        }
    }

    private fun hideError(errorView: TextView) {
        errorView.apply {
            visibility = View.INVISIBLE
        }
    }
}