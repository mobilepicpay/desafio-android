package com.picpay.desafio.android.feature.dinogame.model

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Score(score: Int,highestScore : Int) {
    Row(Modifier.offset(150.dp, 20.dp)) {
        Text(text = "HI SCORE: $highestScore", color = Color.White,fontSize = 20.sp)
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "SCORE : $score", color = Color.White, fontSize = 20.sp)
    }
}