package com.andreisingeleytsev.challengeswheelapp.ui.screens.game_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.challengeswheelapp.R
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Golden
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Green
import com.andreisingeleytsev.challengeswheelapp.ui.theme.MainOrange
import com.andreisingeleytsev.challengeswheelapp.ui.theme.MainPurple
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Orange
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Pink
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Pink2
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Yellow
import com.andreisingeleytsev.challengeswheelapp.ui.utils.Fonts
import com.andreisingeleytsev.challengeswheelapp.ui.utils.UIEvents


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    viewModel: GameScreenViewModel = hiltViewModel()
) {
    var rotate by remember {
        mutableStateOf(0f)
    }
    val animateRotate: Float by animateFloatAsState(
        targetValue = rotate, label = "", animationSpec = tween(
            durationMillis = 3000,
            easing = LinearEasing
        )
    )
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event) {
                is UIEvents.Roll -> {
                    rotate+=900F
                }

                else -> {}
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()
        .background(if (viewModel.id.value == 1) MainPurple
        else MainOrange), contentAlignment = Alignment.BottomCenter) {
        Image(
            painter = painterResource(id = if (viewModel.id.value == 1) R.drawable.game_bg_1
            else R.drawable.game_bg_2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = if (viewModel.id.value == 1) R.string.trivia_time
                else R.string.quest_time), style = TextStyle(
                    color = Golden,
                    fontSize = 32.sp,
                    fontFamily = Fonts.font,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(id = if (viewModel.id.value == 1) R.string.quick
                else R.string.phys_chall), style = TextStyle(
                    color = Color.White,
                    fontSize = 22.sp,
                    fontFamily = Fonts.font
                )
            )
            Image(
                painter = painterResource(id = if (viewModel.id.value == 1) R.drawable.game_wheel_1
                else R.drawable.game_wheel_2),
                modifier = Modifier
                    .padding(horizontal = 37.dp)
                    .fillMaxWidth()
                    .rotate(animateRotate)
                ,
                contentDescription = null,
                contentScale = ContentScale.FillWidth)
            Box(modifier = Modifier.offset(y = -40.dp), contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = if (viewModel.id.value == 1) R.drawable.indicator_1
                    else R.drawable.indicator_2),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.your_turn), style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = Fonts.font
                    ), modifier = Modifier.offset(y = 20.dp)
                )
            }
            Box(modifier = Modifier.offset(y = -40.dp), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.round_table),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.round) + " " + viewModel.rounded.value.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = Fonts.font
                    )
                )
            }
            Button(
                onClick = {
                          viewModel.onEvent(GameScreenEvent.OnSpin)
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewModel.id.value == 1) Color.Red
                    else MainPurple
                ), contentPadding = PaddingValues(horizontal = 32.dp, vertical = 0.dp)
            ) {
                Text(text = stringResource(R.string.spin), style = TextStyle(
                    color = Color.White,
                    fontSize = 43.sp,
                    fontFamily = Fonts.font,
                    fontWeight = FontWeight.ExtraBold
                ))
            }
        }
        if (viewModel.isOpened.value) QuestionField()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QuestionField(viewModel: GameScreenViewModel = hiltViewModel()) {
    Card(
        shape = RoundedCornerShape(topEnd = 47.dp, topStart = 47.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (viewModel.id.value == 1) MainPurple
            else MainOrange
        ), modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(40.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(border = BorderStroke(3.dp, Color.White), colors = CardDefaults.cardColors(
                containerColor = MainPurple
            )) {
                Text(
                    text = viewModel.question.value, modifier = Modifier.padding(24.dp), style = TextStyle(
                        color = Color.White,
                    fontSize = 23.sp,
                        fontFamily = Fonts.font
                    )
                )
            }

            if (!viewModel.answer.value.isNullOrEmpty()) Text(
                text = viewModel.answer.value!!,
                modifier = Modifier.padding(24.dp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 23.sp,
                    fontFamily = Fonts.font
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.icon_timer),
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = viewModel.time.value.toString(),
                modifier = Modifier.padding(24.dp), style = TextStyle(
                    color = Color.White,
                    fontSize = 65.sp,
                    fontFamily = Fonts.font,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                             viewModel.onEvent(GameScreenEvent.OnPass)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ), contentPadding = PaddingValues(0.dp), modifier = Modifier.width(120.dp)
            ) {
                Text(text = stringResource(R.string.pass), style = TextStyle(
                    color = if (viewModel.id.value == 1) MainPurple
                    else MainOrange,
                    fontSize = 28.sp,
                    fontFamily = Fonts.font,
                    fontWeight = FontWeight.Bold
                ) )
            }
            val verticalGradientBrush = Brush.verticalGradient(
                colors = listOf(
                    Pink,
                    Pink2,
                    Orange,
                    Yellow,
                    Green
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                viewModel.onEvent(GameScreenEvent.OnCorrect)
            }, contentPadding = PaddingValues(0.dp),
                modifier = Modifier.width(126.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                )
            ) {
                Box(
                    modifier = Modifier
                        .background(verticalGradientBrush)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.correct), style = TextStyle(
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = Fonts.font,
                    fontWeight = FontWeight.Bold
                )
                )
                }
            }
        }
        }
    }
}
