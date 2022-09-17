package com.leguia.rvezy.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leguia.rvezy.data.CatApi
import com.leguia.rvezy.data.CatResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val networkService: CatApi) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _catList = MutableLiveData<List<CatResponse>>()
    val catSelected = MutableLiveData<CatResponse>()
    val catList: LiveData<List<CatResponse>>
        get() = _catList

    init {
        val disposable = networkService.getCatList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ catListResponse ->
                _catList.postValue(catListResponse)

            }, { error ->

                println("an error ocurred: ${error}")

            }, {
                println("COMPLETES!")
            })

    }

}