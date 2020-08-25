package com.example.android.weatherapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.weatherapp.R
import com.example.android.weatherapp.adapter.HomeScreenAdapter
import com.example.android.weatherapp.databinding.FragmentHomeScreenBinding
import com.example.android.weatherapp.utilities.MAX_PROGRESS
import com.example.android.weatherapp.utilities.MIN_PROGRESS
import com.example.android.weatherapp.viewmodel.HomeScreenViewModel

class HomeScreenFragment : BaseFragment() {

    private val viewModel: HomeScreenViewModel by lazy {
        ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false)
        val adapter = HomeScreenAdapter()
        setOnDataFetchedObserver(binding, adapter)
        setOnLoadingObserver(binding)
        setOnErrorObserver(binding)
        return binding.root
    }

    private fun setOnDataFetchedObserver(binding: FragmentHomeScreenBinding, adapter: HomeScreenAdapter) {
        viewModel.eventDataFetched.observe(viewLifecycleOwner, Observer {
            binding.homeScreenReyclerview.adapter = adapter
            adapter.data = viewModel.forecastData
        })
    }

    private fun setOnLoadingObserver(binding: FragmentHomeScreenBinding) {
        viewModel.eventLoading.observe(viewLifecycleOwner, Observer { hasLoaded ->
            checkLoading(hasLoaded, binding.progressBar)
        })
    }

    private fun setOnErrorObserver(binding: FragmentHomeScreenBinding) {
        viewModel.eventError.observe(viewLifecycleOwner, Observer { errorOccurred ->
             checkError(errorOccurred, binding.progressBar, binding.error, viewModel.errorString)
        })
    }

}