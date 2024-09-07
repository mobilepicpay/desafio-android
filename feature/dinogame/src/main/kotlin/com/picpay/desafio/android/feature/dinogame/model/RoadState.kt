package com.picpay.desafio.android.feature.dinogame.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.discreteRoadThickness
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.distanceBetweenRoad
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadLength
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadPosition
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadThickness
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.xVelocity
import com.picpay.desafio.android.feature.dinogame.util.Converter.convertDpToPixels

data class RoadState(var xpos : Dp = 0.dp) {

   fun move() {
       xpos -= xVelocity
       if (xpos < -roadLength)
           xpos = 0.dp
   }

    fun drawRoad(drawScope: DrawScope) {
        drawScope.apply{
            withTransform({
                translate(
                    left = convertDpToPixels(xpos.value),
                    top = 0f
                )
            }) {
                drawLine(
                    color = Color.DarkGray,
                    start = Offset(0f, convertDpToPixels(roadPosition.value)),
                    end = Offset(
                        2 * convertDpToPixels(roadLength.value),
                        convertDpToPixels(roadPosition.value)
                    ),
                    strokeWidth = convertDpToPixels(roadThickness.value),
                )
                drawLine(
                    color = Color.DarkGray,
                    start = Offset(x = 0f, y = convertDpToPixels(roadPosition.value)
                            + convertDpToPixels(distanceBetweenRoad.value)),
                    end = Offset(
                        x = 2 * convertDpToPixels(roadLength.value), y = convertDpToPixels(
                            roadPosition.value
                        ) + 30
                    ),
                    strokeWidth = convertDpToPixels(discreteRoadThickness.value),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 25f)
                )
            }
        }
    }
}