package com.example.myapplication.view.listings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DataRepository
import com.example.myapplication.domain.model.StoreItem
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ItemsListingsViewModel: ViewModel() {

    private val dataRepository = DataRepository()

    private val compositeDisposable = CompositeDisposable()

    val itemsLiveData = MutableLiveData<List<StoreItem>>()

    fun startReceiveData() {
        dataRepository.startReceivingData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    itemsLiveData.value = it
                }, {
                    Timber.e(it, "Error receiving items!")
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