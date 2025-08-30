package com.example.fitnessapp.workouts


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.*

class StopWatch {
    var formattedTime by mutableStateOf("00:00:00")
    var formattedEndTimeList = mutableStateListOf<String>()
    var counterEndTime = 0

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isActive = false

    private var timeMillis = 0L
    private var lastTimeStamp = 0L
    private var job: Job? = null

    fun start(){
        if(isActive) return
        counterEndTime++
        lastTimeStamp = System.currentTimeMillis()
        isActive = true
        job = CoroutineScope(Dispatchers.Main).launch {
            formattedTime = formatTime(timeMillis)

            lastTimeStamp = System.currentTimeMillis()
            this@StopWatch.isActive = true
            while(isActive){
                val now = System.currentTimeMillis()
                timeMillis += now - lastTimeStamp
                lastTimeStamp = now
                formattedTime = formatTime(timeMillis)
                delay(50L) // UI-friendly update interval
            }
        }
    }

    fun pause() {
        if (!isActive) return

        // Update the elapsed time before stopping
        val now = System.currentTimeMillis()
        timeMillis += now - lastTimeStamp
        isActive = false
        job?.cancel()
    }
    fun reset() {
        job?.cancel()
        timeMillis = 0L
        lastTimeStamp = 0L

        // save the set time before resetting
        formattedEndTimeList.add(formattedTime)
        counterEndTime = formattedEndTimeList.size

        formattedTime = "00:00:00"
        isActive = false
    }

    private fun formatTime(timeMillis: Long) : String{
        val minutes = (timeMillis / 1000) / 60
        val seconds = (timeMillis / 1000) % 60
        val millis = (timeMillis % 1000) / 10
        return String.format("%02d:%02d:%02d", minutes, seconds, millis)

    }

}