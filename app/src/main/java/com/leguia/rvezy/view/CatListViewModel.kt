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
class CatListViewModel @Inject constructor(private val networkService: CatApi) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _catList = MutableLiveData<List<CatResponse>>()
    val catList: LiveData<List<CatResponse>>
        get() = _catList

    init {
        networkService.getCatList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ catListResponse ->

                _catList.value = catListResponse

            }, { error ->

                println("an error ocurred: ${error}")

            }, {
                println("COMPLETES!")
            })
    }
}