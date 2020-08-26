package com.example.android.weatherapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.weatherapp.R
import com.example.android.weatherapp.adapter.HomeScreenAdapter
import com.example.android.weatherapp.databinding.FragmentHomeScreenBinding
import com.example.android.weatherapp.utilities.DEFAULT_LOCATION
import com.example.android.weatherapp.viewmodel.HomeScreenViewModel
import com.example.android.weatherapp.viewmodel.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_home_screen.*

class HomeScreenFragment : BaseFragment() {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false)
        setViewModel()
        //TODO: Rename it and discuss it
        activity?.baseContext?.let {context ->
            setAdapter(context, binding)
        }
        setOnLoadingObserver(binding)
        setOnErrorObserver(binding)
        setSearchButton(binding)
        return binding.root
    }

    private fun setSearchButton(binding: FragmentHomeScreenBinding) {
        binding.searchButton.setOnClickListener {
            val city = binding.cityText.text.toString()
            viewModel.fetchDataFromRepository(city)
        }
    }

    private fun setAdapter(activityContext: Context, binding: FragmentHomeScreenBinding) {
        val adapter = HomeScreenAdapter(activityContext)
        binding.homeScreenReyclerview.adapter = adapter
        setOnDataFetchedObserver(adapter)
    }

    private fun setViewModel() {
        viewModelFactory = HomeViewModelFactory(DEFAULT_LOCATION)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeScreenViewModel::class.java)
    }

    private fun setOnDataFetchedObserver(adapter: HomeScreenAdapter) {
        viewModel.eventDataFetched.observe(viewLifecycleOwner, Observer { dataFetched ->
            if(dataFetched) {
                adapter.updateData(viewModel.forecastData)
                viewModel.onDataFetched()
            }
        })
    }

    private fun setOnLoadingObserver(binding: FragmentHomeScreenBinding) {
        viewModel.eventLoading.observe(viewLifecycleOwner, Observer { hasLoaded ->
            checkLoadingStatus(
                hasLoaded, binding.progressBar, binding.cityText, binding.searchButton,
                home_screen_reyclerview)
        })
    }

    private fun setOnErrorObserver(binding: FragmentHomeScreenBinding) {
        viewModel.eventError.observe(viewLifecycleOwner, Observer { errorOccurred ->
            checkErrorStatus(
                errorOccurred, binding.progressBar, binding.error,
                viewModel.errorString, binding.cityText, binding.searchButton,
                binding.homeScreenReyclerview
            )
        })
    }

}