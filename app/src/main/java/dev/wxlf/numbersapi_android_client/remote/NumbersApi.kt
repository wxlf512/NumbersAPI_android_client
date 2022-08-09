package dev.wxlf.numbersapi_android_client.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("./random/trivia")
    fun getRandomTriviaFact(): Single<String>

    @GET("/{number}/trivia")
    fun getTriviaFact(@Path("number") number: Int): Single<String>
}