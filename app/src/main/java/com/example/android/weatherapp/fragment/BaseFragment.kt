package com.example.android.weatherapp.fragment

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.weatherapp.utilities.MAX_PROGRESS
import com.example.android.weatherapp.utilities.MIN_PROGRESS

open class BaseFragment : Fragment() {

    fun checkError(errorOccurred: Boolean, progressBar: ProgressBar, errorView: TextView,
                 errorString: String) {
        if (errorOccurred) {
            hideProgressBar(progressBar)
            showError(errorView, errorString)
        } else {
            showProgressBar(progressBar)
            hideError(errorView)
        }
    }

    fun checkLoading(isLoaded: Boolean, progressBar: ProgressBar) {
        if (isLoaded) {
            hideProgressBar(progressBar)
        } else {
            showProgressBar(progressBar)
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