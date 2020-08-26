package com.example.android.weatherapp.fragment

import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.utilities.MAX_PROGRESS
import com.example.android.weatherapp.utilities.MIN_PROGRESS

open class BaseFragment : Fragment() {

    fun checkErrorStatus(errorOccurred: Boolean, progressBar: ProgressBar,
        errorString: String, recyclerView: RecyclerView) {
        if (errorOccurred) {
            hideProgressBar(progressBar)
            createAlertOnError(errorString)
            hideRecyclerView(recyclerView)
        } else {
            showProgressBar(progressBar)
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
            hideRecyclerView(recyclerView)
        }
    }

    private fun hideRecyclerView(recyclerView: RecyclerView) {
        recyclerView.visibility = View.GONE
    }

    private fun showRecyclerView(recyclerView: RecyclerView) {
        recyclerView.visibility = View.VISIBLE
    }


    private fun showSearchViews(cityText: TextView, searchButton: Button) {
        cityText.visibility = View.VISIBLE
        searchButton.visibility = View.VISIBLE
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

    private fun createAlertOnError(errorMessage: String) {
        val alertBuilder = AlertDialog.Builder(activity)
        alertBuilder.setMessage(errorMessage)
            .setPositiveButton(R.string.positive_button) { dialogClicked, buttonClicked ->
            }.show()
    }

}