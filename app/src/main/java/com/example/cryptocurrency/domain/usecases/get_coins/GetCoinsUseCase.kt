package com.example.cryptocurrency.domain.usecases.get_coins

import com.example.cryptocurrency.common.Resources
import com.example.cryptocurrency.data.repository.CoinRepositoryImpl
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke(): Flow<Resources<List<Coin>>> = repository.getCoins()
}