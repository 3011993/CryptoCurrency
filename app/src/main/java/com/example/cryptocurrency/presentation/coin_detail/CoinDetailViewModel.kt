package com.example.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.common.Resources
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.domain.usecases.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor
    (
    private val getCoinUseCase: GetCoinUseCase,
    private val repo: CoinRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())

    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        viewModelScope.launch {
            repo.getCoinById(coinId).collect { result ->
                when (result) {
                    is Resources.Success -> {
                        _state.value = CoinDetailState(coin = result.data)
                    }

                    is Resources.Loading -> {
                        _state.value = CoinDetailState(isLoading = true)
                    }

                    is Resources.Error -> {
                        _state.value = CoinDetailState(
                            error = result.message ?: "An Unexpected error occurred"
                        )
                    }

                }
            }
        }

    }


//    private fun getCoin(coinId: String) {
//        getCoinUseCase(coinId).onEach { result ->
//            when (result) {
//                is Resources.Success -> {
//                    _state.value = CoinDetailState(coin = result.data)
//                }
//
//                is Resources.Loading -> {
//                    _state.value = CoinDetailState(isLoading = true)
//                }
//
//                is Resources.Error -> {
//                    _state.value = CoinDetailState(
//                        error = result.message ?: "An Unexpected error occurred"
//                    )
//                }
//
//            }
//        }.launchIn(viewModelScope)
//
//    }
}