package com.picpay.desafio.android.feature.dinogame.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.dinogame.other.AssetPath
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.DOUBT_FACTOR
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.dinoPos
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.minDistanceBetween
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadLength
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadPosition
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.xVelocity
import com.picpay.desafio.android.feature.dinogame.util.Converter.convertDpToPixels
import com.picpay.desafio.android.ui.theme.picPayGreen

val doubt_factor = convertDpToPixels(DOUBT_FACTOR.value)

class CactusState(val cactusList: ArrayList<Cactus> = ArrayList()) {

    fun init() {
        var startx = roadLength

        for (i in 0..2) {
            val cactus = Cactus(startx)
            cactusList.add(cactus)
            startx += (minDistanceBetween + (0..minDistanceBetween.value.toInt()).random().dp)
        }
    }

    fun move(score: MutableState<Int>, birdState: BirdState) {
        for (cactus in cactusList) {
            if (!birdState.isMoving || cactus.xpos < roadLength)
                cactus.xpos -= xVelocity
            if (!cactus.croosDino && cactus.xpos < dinoPos) {
                score.value++
                cactus.croosDino = true
            }
            if (cactus.xpos < (-50).dp) {
                reallocate(cactus)
                cactus.croosDino = false
            }
        }
    }

    private fun reallocate(cactus: Cactus) {
        if (cactusList.last().xpos < roadLength)
            cactus.xpos =
                roadLength + (minDistanceBetween + (0..minDistanceBetween.value.toInt()).random().dp)
        else
            cactus.xpos =
                cactusList.last().xpos + (minDistanceBetween + (0..minDistanceBetween.value.toInt()).random().dp)
    }


    fun destroy() {
        while (cactusList.isNotEmpty())
            cactusList.removeAt(0)
    }


    fun draw(drawScope: DrawScope, birdState: BirdState) {
        for (cactus in cactusList) {
            if (!birdState.isMoving || (cactus.xpos < roadLength)) {
                drawScope.apply {
                    withTransform({
                        val cactusPos = convertDpToPixels(cactus.xpos.value)
                        translate(
                            left = cactusPos,
                            top = convertDpToPixels(roadPosition.value) - AssetPath().CactusPath()
                                .getBounds().height
                        )
                    }) {
                        drawPath(
                            path = AssetPath().CactusPath(),
                            style = Fill,
                            color = picPayGreen
                        )
                    }
                }
            }
        }
    }

    class Cactus(var xpos: Dp, var croosDino: Boolean = false) {
        fun getRect(): Rect {
            val xposInFloat = convertDpToPixels(xpos.value)
            return Rect(
                left = xposInFloat,
                top = convertDpToPixels(roadPosition.value) - AssetPath().CactusPath()
                    .getBounds().height,
                right = xposInFloat + AssetPath().CactusPath().getBounds().width,
                bottom = convertDpToPixels(roadPosition.value)
            )
        }
    }
}