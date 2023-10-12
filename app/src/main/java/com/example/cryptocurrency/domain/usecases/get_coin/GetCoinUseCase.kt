package com.example.cryptocurrency.domain.usecases.get_coin

import com.example.cryptocurrency.common.Resources
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.data.repository.CoinRepositoryImpl
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke(coinId: String) :Flow<Resources<CoinDetail>> = repository.getCoinById(coinId)
}
