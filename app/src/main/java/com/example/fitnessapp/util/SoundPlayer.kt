package com.example.fitnessapp.util

import android.media.MediaPlayer
import android.content.Context
import com.example.fitnessapp.R

object SoundPlayer{
    fun playPop(context: Context){
        val popSound = MediaPlayer.create(context, R.raw.bubblepop)
        popSound.setOnCompletionListener {
            it.release()
        }
        popSound.start()
    }
}