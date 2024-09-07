package com.picpay.desafio.android.feature.dinogame

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.feature.dinogame.model.CloudState
import com.picpay.desafio.android.feature.dinogame.model.GameOver
import com.picpay.desafio.android.feature.dinogame.model.Score
import com.picpay.desafio.android.feature.dinogame.media.SoundEffect
import com.picpay.desafio.android.feature.dinogame.other.Constants.Companion.roadLength
import com.picpay.desafio.android.feature.dinogame.model.BirdState
import com.picpay.desafio.android.feature.dinogame.model.CactusState
import com.picpay.desafio.android.feature.dinogame.model.DinoState
import com.picpay.desafio.android.feature.dinogame.model.RoadState
import com.picpay.desafio.android.feature.dinogame.model.doubt_factor
import com.picpay.desafio.android.feature.dinogame.util.deviceDensity
import org.koin.compose.koinInject

@Composable
fun StartGameLoop(
    navToContacts: () -> Unit,
    networkMonitor: NetworkMonitor = koinInject()
) {

    val isOnline by networkMonitor.isOnline.collectAsState(initial = false)

    if (isOnline) navToContacts()

    val density = LocalDensity.current
    deviceDensity = density.density


    val context = LocalContext.current

    val soundEffect = remember { SoundEffect(context) }
    val roadState = remember { RoadState() }
    val cactusState = remember { CactusState() }
    val dinoState = remember { DinoState() }
    val scoreState = remember { mutableIntStateOf(0) }
    val highestScoreState = remember { mutableIntStateOf(0) }
    val cloudState = remember { CloudState() }
    val birdState = remember { BirdState() }

    val state = rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 500)
        )
    )

    if (birdState.targetScore == scoreState.intValue)
        birdState.isMoving = true

    if (cactusState.cactusList.isEmpty())
        cactusState.init()
    if (cloudState.cloudList.isEmpty())
        cloudState.init()

    var gameEnd by remember { mutableStateOf(false) }
    val tick = state.value
    val interaction = remember { MutableInteractionSource() }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
//            .background(color = Color.Black)
            .clickable(
                interactionSource = interaction,
                indication = null
            ) {
                if (!gameEnd) {
                    dinoState.jump()
                    soundEffect.playJumpSound()
                } else {
                    gameEnd = false
                    cactusState.destroy()
                    birdState.destroy()
                    if (highestScoreState.intValue < scoreState.intValue) {
                        highestScoreState.intValue = scoreState.intValue
                    }
                    birdState.isMoving = false
                    birdState.targetScore = 3
                    scoreState.intValue = 0
                }
            }) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            dinoState.draw(this, tick, gameEnd)
            cactusState.draw(this, birdState)
            cloudState.draw(this)
            roadState.drawRoad(this)
            if (birdState.isMoving)
                birdState.draw(this)
        }
        Score(scoreState.intValue, highestScoreState.intValue)

        if (birdState.xpos < 0.dp) {
            birdState.isMoving = false
            birdState.xpos = roadLength
            birdState.increaseTargetedScore()
        }

        val dinoRect = dinoState.getRect()
        val birdRect = birdState.getRect()
        for (cactus in cactusState.cactusList) {
            if (checkCollision(
                    dinoRect.deflate(doubt_factor),
                    cactus.getRect().deflate(doubt_factor)
                ) || checkCollision(
                    dinoRect.deflate(
                        doubt_factor
                    ), birdRect.deflate(doubt_factor)
                )
            ) {
                if (!gameEnd) {
                    soundEffect.playGameEndSound()
                    if (scoreState.intValue > highestScoreState.intValue)
                        highestScoreState.intValue = scoreState.intValue
                }
                gameEnd = true

            }
        }
        GameOver(
            modifier = Modifier
                .wrapContentSize(Alignment.TopCenter)
                .padding(top = 150.dp), gameEnd
        )
    }
    if (!gameEnd) {
        cactusState.move(scoreState, birdState)
        dinoState.init()
        cloudState.move()
        roadState.move()
        if (birdState.isMoving)
            birdState.move(scoreState)
    }

}

fun checkCollision(dinoRect: Rect, obstacleRect: Rect): Boolean {
    if (dinoRect.overlaps(obstacleRect)) return true
    else return false
}

