package com.leguia.rvezy.view

import androidx.arch.core.util.Function
import androidx.lifecycle.*
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.leguia.rvezy.data.CatApi
import com.leguia.rvezy.data.CatPagingSource
import com.leguia.rvezy.data.CatResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val pagingService: CatPagingSource) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _catList = MutableLiveData<PagingData<CatResponse>>()
    val catSelected = MutableLiveData<CatResponse>()
    val catList: LiveData<PagingData<CatResponse>>
        get() = pager
    private val pager = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { pagingService }
    ).liveData
    .cachedIn(viewModelScope)

//    init {
//        val disposable = pager
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { _catList.postValue(it) },
//                { error ->  println("an error ocurred: ${error}") },
//                { println("COMPLETES!")}
//            )
//
//    }

}