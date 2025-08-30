package com.example.fitnessapp.workouts

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RestTimer : ViewModel(){
    private var defaultDuration : Long = 60L
    private val _remainingTime = MutableStateFlow<Long>(defaultDuration)
    val remainingTime = _remainingTime.asStateFlow()
    val timerList = listOf(10L, 60L, 120L, 180L, 240L, 300L)
    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()


    val restTimesList = mutableListOf<Long>()

    private var timerJob: Job? = null

    fun startTimer(durationInSeconds: Long? = null) {
        val duration = durationInSeconds ?: defaultDuration

        // Add to the List for UI in StopWatch
        restTimesList.add(duration)

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