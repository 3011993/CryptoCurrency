package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.common.Resources
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) :
    CoinRepository {

    override suspend fun getCoins(): Flow<Resources<List<Coin>>> {
        return flow {
            try {
                emit(Resources.Loading<List<Coin>>())
                val coins = api.getCoins().map { it.toCoin() }
                emit(Resources.Success<List<Coin>>(coins))
            } catch (e: HttpException) {
                emit(
                    Resources.Error<List<Coin>>(
                        message = e.localizedMessage ?: "An Unexpected Error occurred"
                    )
                )

            } catch (e: IOException) {
                emit(Resources.Error<List<Coin>>("Couldn't reach to the server,Please Check your internet connection"))
            }

        }
    }

    override suspend fun getCoinById(coinId: String): Flow<Resources<CoinDetail>> {
        return flow {
            try {
                emit(Resources.Loading<CoinDetail>())
                val coin = api.getCoinById(coinId).toCoinDetail()
                emit(Resources.Success<CoinDetail>(coin))
            } catch (e: HttpException) {
                emit(
                    Resources.Error<CoinDetail>(
                        message = e.localizedMessage ?: "An Unexpected Error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(Resources.Error<CoinDetail>(message = "Couldn't reach to the server,Please Check your internet connection"))
            }

        }
    }
}
