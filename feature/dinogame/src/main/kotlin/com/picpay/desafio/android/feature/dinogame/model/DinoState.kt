package com.picpay.desafio.android.feature.dinogame.model

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.dinogame.other.AssetPath
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.dinoGravity
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.dinoPos
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.dinoUpVelocity
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadPosition
import com.picpay.desafio.android.feature.dinogame.util.Converter.convertDpToPixels

data class DinoState(var ypos: Dp = roadPosition, var velocity : Dp = 0.dp) {
    private var gravity = 0.dp

    fun init(){
        ypos += velocity
        velocity += gravity
        if(ypos> roadPosition){
            ypos = roadPosition
            velocity=0.dp
            gravity=0.dp
        }
    }

    fun jump(){
        Log.i("ashu","road = $roadPosition")
        Log.i("ashu", "ypos - $ypos")
        if(ypos == roadPosition){
            velocity= dinoUpVelocity
            gravity = dinoGravity
        }
    }

    fun draw(drawScope: DrawScope, tick : Float,gameEnd : Boolean){
        val resource : Path
        if (tick<= 0.5f)
            resource = AssetPath().DinoPath()
        else
            resource = AssetPath().DinoPath2()

        val rotateValue = when(gameEnd){
              false -> 0f
              else -> 180f
        }

        drawScope.apply {
            withTransform({
                translate(
                    left = convertDpToPixels(dinoPos.value),
                    top = convertDpToPixels(ypos.value) - resource.getBounds().height
                )
            }) {
                rotate(
                    degrees = rotateValue,
                    pivot = Offset(resource.getBounds().width / 2, resource.getBounds().height / 2)
                ) {
                    drawPath(
                        path = resource,
                        style = Fill,
                        color = if (!gameEnd) Color.White
                        else Color.Red
                    )
                }
            }
        }
    }

    fun getRect() : Rect{
        val resource = AssetPath().DinoPath()
        return  Rect(
            left = convertDpToPixels(dinoPos.value),
            top =  convertDpToPixels(ypos.value) - resource.getBounds().height,
            right = convertDpToPixels(dinoPos.value) + resource.getBounds().width,
            bottom = convertDpToPixels(ypos.value)
        )
    }
}