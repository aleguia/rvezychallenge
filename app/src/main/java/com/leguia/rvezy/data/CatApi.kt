package com.leguia.rvezy.data

import retrofit2.http.GET
import com.leguia.rvezy.data.CatResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Query

interface CatApi {
    @GET("images/search")
    suspend fun getCatList(
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 1,
        @Query("order") asc: String = "Desc"
    ): List<CatResponse>
}