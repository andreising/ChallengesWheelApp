package com.andreisingeleytsev.challengeswheelapp.ui.screens.menu_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreisingeleytsev.challengeswheelapp.R
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Golden
import com.andreisingeleytsev.challengeswheelapp.ui.theme.MainOrange
import com.andreisingeleytsev.challengeswheelapp.ui.theme.MainPurple
import com.andreisingeleytsev.challengeswheelapp.ui.utils.Fonts
import com.andreisingeleytsev.challengeswheelapp.ui.utils.Routes


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MenuScreen(navHostController: NavHostController) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        val size1 = maxHeight / 3
        val size2 = maxHeight / 6
        val size3 = maxHeight / 3 * 2
        val state = remember {
            mutableStateOf(0)
        }
        val heightCard1 = remember {
            mutableStateOf(size1)
        }
        val animateHeightCard1: Dp by animateDpAsState(
            targetValue = heightCard1.value, label = "", animationSpec = tween(
                durationMillis = 200,
                easing = LinearEasing
            )
        )
        val heightCard2 = remember {
            mutableStateOf(size1)
        }
        val animateHeightCard2: Dp by animateDpAsState(
            targetValue = heightCard2.value, label = "", animationSpec = tween(
                durationMillis = 200,
                easing = LinearEasing
            )
        )
        val heightImg = remember {
            mutableStateOf(size1)
        }
        val animateHeightImg: Dp by animateDpAsState(
            targetValue = heightImg.value, label = "", animationSpec = tween(
                durationMillis = 200,
                easing = LinearEasing
            )
        )
        var direction by remember { mutableStateOf(-1) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(animateHeightImg),
                contentScale = ContentScale.FillWidth
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        , colors = CardDefaults.cardColors(
                        containerColor = MainPurple
                    ), shape = RoundedCornerShape(topEnd = 47.dp, topStart = 47.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                modifier = Modifier
                                    .width(60.dp)
                                    .pointerInput(true) {
                                        detectDragGestures(
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                val y = dragAmount.y
                                                when {
                                                    y > 0 -> {
                                                        // down
                                                        direction = 2
                                                    }

                                                    y < 0 -> {
                                                        // up
                                                        direction = 3
                                                    }
                                                }

                                            },
                                            onDragEnd = {
                                                when (direction) {
                                                    2 -> {
                                                        if (state.value == 1) {
                                                            heightCard1.value = size1
                                                            heightImg.value = size1
                                                            state.value = 0
                                                        }
                                                    }

                                                    3 -> {
                                                        if (state.value == 0) {
                                                            heightCard1.value = size3
                                                            heightImg.value = size2
                                                            state.value = 1
                                                        }
                                                    }
                                                }
                                            }
                                        )
                                    },
                                color = Color.White,
                                thickness = 3.dp
                            )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_two_fingers),
                                contentDescription = null
                            )
                            Column {
                                Text(
                                    text = stringResource(id = R.string.trivia_time), style = TextStyle(
                                        color = Golden,
                                        fontSize = 32.sp,
                                        fontFamily = Fonts.font,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                                Text(
                                    text = stringResource(id = R.string.quick), style = TextStyle(
                                        color = Color.White,
                                        fontSize = 22.sp,
                                        fontFamily = Fonts.font
                                    )
                                )
                            }
                            IconButton(onClick = {
                                when(state.value) {
                                    0 -> {
                                        heightCard1.value = size3
                                        heightImg.value = size2
                                        state.value = 1
                                    }
                                    1 -> {
                                        heightCard1.value = size1
                                        heightImg.value = size1
                                        state.value = 0
                                    }
                                    2 -> {
                                        heightCard1.value = size3
                                        heightCard2.value = size1
                                        heightImg.value = size2
                                        state.value = 1
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = if (state.value == 1) Icons.Default.KeyboardArrowDown
                                    else Icons.Default.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        if (state.value == 1) {
                            Button(
                                onClick = {
                                          navHostController.navigate(Routes.GAME_SCREEN + "/1")
                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red
                                ),
                                contentPadding = PaddingValues(horizontal = 30.dp),
                                shape = RoundedCornerShape(22.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.play), style = TextStyle(
                                        color = Golden,
                                        fontSize = 40.sp,
                                        fontFamily = Fonts.font,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Image(
                                painter = painterResource(id = R.drawable.wheel1),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .padding()
                        .fillMaxWidth()
                        .height(animateHeightCard2)
                        , colors = CardDefaults.cardColors(
                        containerColor = MainOrange
                    ), shape = RoundedCornerShape(topStart = 47.dp, topEnd = 47.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                modifier = Modifier
                                    .width(60.dp)
                                    .pointerInput(true) {
                                        detectDragGestures(
                                            onDrag = { change, dragAmount ->
                                                change.consume()

                                                val y = dragAmount.y
                                                when {
                                                    y > 0 -> {
                                                        // down
                                                        direction = 2
                                                    }

                                                    y < 0 -> {
                                                        // up
                                                        direction = 3
                                                    }
                                                }

                                            },
                                            onDragEnd = {
                                                when (direction) {
                                                    2 -> {
                                                        if (state.value == 2) {
                                                            heightCard2.value = size1
                                                            heightImg.value = size1
                                                            state.value = 0
                                                        }
                                                    }

                                                    3 -> {
                                                        if (state.value == 0) {
                                                            heightCard2.value = size3
                                                            heightImg.value = size2
                                                            state.value = 2
                                                        }
                                                    }
                                                }
                                            }
                                        )
                                    },
                                color = Color.White,
                                thickness = 3.dp
                            )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_rainbow),
                                contentDescription = null
                            )
                            Column {
                                Text(
                                    text = stringResource(id = R.string.quest_time), style = TextStyle(
                                        color = Golden,
                                        fontSize = 32.sp,
                                        fontFamily = Fonts.font,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = stringResource(id = R.string.phys_chall), style = TextStyle(
                                        color = Color.White,
                                        fontSize = 22.sp,
                                        fontFamily = Fonts.font
                                    )
                                )
                            }
                            IconButton(onClick = {
                                when(state.value) {
                                    0 -> {
                                        heightCard2.value = size3
                                        heightImg.value = size2
                                        state.value = 2
                                    }
                                    1 -> {
                                        heightCard2.value = size3
                                        heightImg.value = size2
                                        heightCard1.value = size1
                                        state.value = 2
                                    }
                                    2 -> {
                                        heightCard2.value = size1
                                        heightImg.value = size1
                                        state.value = 0
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = if (state.value == 2) Icons.Default.KeyboardArrowDown
                                    else Icons.Default.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        if (state.value == 2) {
                            Button(
                                onClick = {
                                    navHostController.navigate(Routes.GAME_SCREEN + "/0")
                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = MainPurple
                                ),
                                contentPadding = PaddingValues(horizontal = 30.dp),
                                shape = RoundedCornerShape(22.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.play), style = TextStyle(
                                        color = Golden,
                                        fontSize = 40.sp,
                                        fontFamily = Fonts.font,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Image(
                                painter = painterResource(id = R.drawable.wheel2),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
            }
        }
    }
}