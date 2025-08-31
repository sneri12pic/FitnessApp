package com.example.fitnessapp.workouts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RestTimer : ViewModel(){

    val timerList = listOf(1L, 10L, 60L, 120L, 180L, 240L, 300L)
    var formattedTime by mutableStateOf("00:00")
    val restTimesList = mutableListOf<String>()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()
    private var defaultDuration : Long = 60L
    private val _remainingTime = MutableStateFlow<Long>(defaultDuration)
    val remainingTime = _remainingTime.asStateFlow()
    private var timerJob: Job? = null

    fun startTimer(durationInSeconds: Long? = null) {
        val duration = durationInSeconds ?: defaultDuration


        stopTimer()
        _remainingTime.value = duration
        _isRunning.value = true

        timerJob = viewModelScope.launch {
            while (_remainingTime.value > 0 && isActive) {
                delay(1000)
                _remainingTime.value--
            }
            _remainingTime.value = 0
            _isRunning.value = false

            // Add to the List for UI in StopWatch with Formatted Time
            formattedTime = formatTime(duration)
            restTimesList.add(formattedTime)
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    fun stopTimer() {
        timerJob?.cancel()
        _remainingTime.value = 0
        _isRunning.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    fun formatTime(remainingTime: Long): String {
        val minutes = (remainingTime % 3600) / 60
        val seconds = remainingTime % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

}