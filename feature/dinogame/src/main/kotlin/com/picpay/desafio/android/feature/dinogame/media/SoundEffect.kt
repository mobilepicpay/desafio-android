package com.picpay.desafio.android.feature.dinogame.media

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.picpay.desafio.android.feature.dinogame.R

class SoundEffect(context: Context){
    private var soundPool: SoundPool
    private var jumpSound : Int
    private var gameEndSound : Int
    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        soundPool =
            SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build()
        jumpSound = soundPool.load(context, R.raw.sound,1)
         gameEndSound = soundPool.load(context, R.raw.ending,1)
    }
    fun playJumpSound(){
        soundPool.play(jumpSound,1F,1F,0,0,1F)
    }
    fun playGameEndSound(){
        soundPool.play(gameEndSound,1F,1F,0,0,1F)
    }
}