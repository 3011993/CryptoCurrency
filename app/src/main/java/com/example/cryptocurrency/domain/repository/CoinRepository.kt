package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.common.Resources
import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins () : Flow<Resources<List<Coin>>>

    suspend fun getCoinById (coinId : String) : Flow<Resources<CoinDetail>>

}