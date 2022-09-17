package com.leguia.rvezy.data

import com.leguia.rvezy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().build()
        request = request.newBuilder()
            .addHeader("x-api-key", BuildConfig.CAT_API_KEY)
            .url(url)
            .build()
        return chain.proceed(request)
    }
}