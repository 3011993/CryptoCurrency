package com.example.cryptocurrency.data.remote

import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

const val GET_COINS_QUERY = "/v1/coins"
const val GET_COIN_Detail_QUERY = "/v1/coins/{coinId}"

interface CoinPaprikaApi {

    @GET(GET_COINS_QUERY)
    suspend fun getCoins(): List<CoinDto>

    //Todo check if we change the name parameter what will happened
    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}