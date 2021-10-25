package com.example.myapplication.view.listings

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DataRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ItemsListingsViewModel: ViewModel() {

    private val dataRepository = DataRepository()

    private val compositeDisposable = CompositeDisposable()

    fun startReceiveData() {
        dataRepository.startReceivingData().subscribe(
            {

            }, {

            }
        ).addTo(compositeDisposable)
    }

    fun stopReceivingData() {
        dataRepository.startReceivingData()
    }

    override fun onCleared() {
        super.onCleared()
        dataRepository.stopReceivingData()
        compositeDisposable.dispose()
    }
}