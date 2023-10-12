package com.example.cryptocurrency.presentation.coin_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resources
import com.example.cryptocurrency.data.repository.CoinRepositoryImpl
import com.example.cryptocurrency.domain.repository.CoinRepository
import com.example.cryptocurrency.domain.usecases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) :
    ViewModel() {

    private val _state = mutableStateOf(CoinListState())

    val state: State<CoinListState> = _state

    init {
        getCoins()

    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().collect() { result ->
                when (result) {
                    is Resources.Success -> {
                        _state.value = CoinListState(coins = result.data ?: emptyList())
                    }

                    is Resources.Loading -> {
                        _state.value = CoinListState(isLoading = true)
                    }

                    is Resources.Error -> {
                        _state.value = CoinListState(
                            error = result.message ?: "An Unexpected error occurred"
                        )
                    }
                }
            }
        }

    }

}
