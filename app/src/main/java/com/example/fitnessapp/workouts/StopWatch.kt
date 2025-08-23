package com.example.fitnessapp.workouts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.fitnessapp.util.SoundPlayer
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class StopWatch {
    var formattedTime by mutableStateOf("00:00")

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isActive = false

    private var timeMillis = 0L
    private var lastTimeStamp = 0L
    private var job: Job? = null

    fun start(){
        if(isActive) return

        lastTimeStamp = System.currentTimeMillis()
        isActive = true
        job = CoroutineScope(Dispatchers.Main).launch {
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
        formattedTime = "00:00"
        isActive = false
    }

    private fun formatTime(timeMillis: Long) : String{
        val minutes = (timeMillis / 1000) / 60
        val seconds = (timeMillis / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

}