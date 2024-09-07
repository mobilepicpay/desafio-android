package com.picpay.desafio.android.feature.dinogame.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.cloudHeight
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.minDistanceBetween
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadLength
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.xVelocity
import com.picpay.desafio.android.feature.dinogame.other.AssetPath
import com.picpay.desafio.android.feature.dinogame.util.Converter

class CloudState(val cloudList : ArrayList<Cloud> = ArrayList()) {
    fun init(){
        var startx = roadLength
        for( i in 0..1){
           val cloud = Cloud(startx)
            cloudList.add(cloud)
           startx += minDistanceBetween+ (0..minDistanceBetween.value.toInt()).random().dp
        }
    }

    fun move(){
        for(cloud in cloudList){
            cloud.xpos -= xVelocity
            if(cloud.xpos< (-10).dp)
                cloud.xpos = roadLength
        }
    }
    fun draw(drawScope: DrawScope){
        for(cloud in cloudList){
            val cloudXpos = Converter.convertDpToPixels(cloud.xpos.value)
            drawScope.apply {
                withTransform({
                    translate(
                        left = cloudXpos,
                        top = Converter.convertDpToPixels(cloudHeight.value)
                    )
                }) {
                    drawPath(
                        path = AssetPath().CloudPath(),
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}
class Cloud(var xpos : Dp)