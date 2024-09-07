package com.picpay.desafio.android.feature.dinogame.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import com.picpay.desafio.android.feature.dinogame.other.AssetPath
import com.picpay.desafio.android.feature.dinogame.other.Constants
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.birdHeight
import com.picpay.desafio.android.feature.dinogame.util.Converter

class BirdState(var xpos :Dp = Constants.roadLength, var crossDino :Boolean = false, var targetScore : Int = 3) {
    var isMoving = false

    fun move(score : MutableState<Int>){
            xpos -= Constants.xVelocity
        if (!crossDino && xpos < Constants.dinoPos) {
            score.value++
            crossDino = true
        }
        }

    fun destroy(){
       xpos = Constants.roadLength
    }

    fun increaseTargetedScore(){
        targetScore = (targetScore+3..targetScore+6).random()
    }

    fun draw(drawScope: DrawScope){
            val birdXpos = Converter.convertDpToPixels(xpos.value)
            drawScope.apply {
                withTransform({
                    translate(
                        left = birdXpos,
                        top = Converter.convertDpToPixels(birdHeight.value) - AssetPath().BirdPath().getBounds().height
                    )
                }) {
                    drawPath(
                        path = AssetPath().BirdPath(),
                        color = Color.LightGray
                    )
                }
            }
        }


    fun getRect() : Rect {
        val resource = AssetPath().BirdPath()
        return  Rect(
            left = Converter.convertDpToPixels(xpos.value),
            top =  Converter.convertDpToPixels(birdHeight.value) - resource.getBounds().height,
            right = Converter.convertDpToPixels(xpos.value) + resource.getBounds().width,
            bottom = Converter.convertDpToPixels(birdHeight.value)
        )
    }
    }