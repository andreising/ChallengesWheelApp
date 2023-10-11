package com.andreisingeleytsev.challengeswheelapp.ui.screens.onboarding_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreisingeleytsev.challengeswheelapp.R
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Green
import com.andreisingeleytsev.challengeswheelapp.ui.theme.OnboardCard1Color
import com.andreisingeleytsev.challengeswheelapp.ui.theme.OnboardCard2Color
import com.andreisingeleytsev.challengeswheelapp.ui.theme.OnboardCard3Color
import com.andreisingeleytsev.challengeswheelapp.ui.theme.OnboardCard4Color
import com.andreisingeleytsev.challengeswheelapp.ui.theme.OnboardCard5Color
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Orange
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Pink
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Pink2
import com.andreisingeleytsev.challengeswheelapp.ui.theme.Yellow
import com.andreisingeleytsev.challengeswheelapp.ui.utils.Fonts

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onEndOnboard: () -> Unit) {
    val pagerState = rememberPagerState()
    val modifier = Modifier.fillMaxSize()
    Box(modifier = if (pagerState.currentPage > 3) modifier.clickable {
        onEndOnboard.invoke()
    } else modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.main_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                pageCount = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp),
                userScrollEnabled = true,
                state = pagerState
            ) {

                when (it) {
                    0 -> {
                        OnboardScreen1()
                    }

                    1 -> {
                        OnboardScreen2()
                    }

                    2 -> {
                        OnboardScreen3()
                    }

                    3 -> {
                        OnboardScreen4()
                    }

                    4 -> {
                        OnboardScreen5()
                    }

                    else -> {

                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            BoxWithConstraints(
                Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            ) {
                val dividerWidth = maxWidth / 5 - 2.dp
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    if (pagerState.currentPage > -1) Image(
                        painter = painterResource(id = R.drawable.pager_bar_1),
                        contentDescription = null,
                        modifier = Modifier.width(dividerWidth),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    if (pagerState.currentPage > 0) Image(
                        painter = painterResource(id = R.drawable.pager_bar_2),
                        contentDescription = null,
                        modifier = Modifier.width(dividerWidth),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    if (pagerState.currentPage > 1) Image(
                        painter = painterResource(id = R.drawable.pager_bar_3),
                        contentDescription = null,
                        modifier = Modifier.width(dividerWidth),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    if (pagerState.currentPage > 2) Image(
                        painter = painterResource(id = R.drawable.pager_bar_4),
                        contentDescription = null,
                        modifier = Modifier.width(dividerWidth),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    if (pagerState.currentPage > 3) Image(
                        painter = painterResource(id = R.drawable.pager_bar_5),
                        contentDescription = null,
                        modifier = Modifier.width(dividerWidth),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            if (pagerState.currentPage > 3) Text(
                text = stringResource(R.string.tap_to_continue), style = TextStyle(
                    color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                )
            )
        }

    }
}


@Composable
fun OnboardScreen1() {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(33.dp), colors = CardDefaults.cardColors(
                containerColor = OnboardCard1Color
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onboard_img_1),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = stringResource(R.string.welcome_to_the) + "\n" + stringResource(R.string.ultimate_game_of) + "\n" + stringResource(
                        R.string.fun_spontaneity
                    ) + "\n" + stringResource(R.string.and_a_touch_of) + "\n" + stringResource(R.string.madness),
                    style = TextStyle(
                        color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Pink
                    ), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(9.dp)
                ) {
                    Text(
                        text = stringResource(R.string.tired_of_the_same) + "\n" + stringResource(R.string.old_same_old),
                        style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                        ),
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun OnboardScreen2() {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(33.dp), colors = CardDefaults.cardColors(
                containerColor = OnboardCard2Color
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.dive_into_a_whirlwind_of) + "\n" + stringResource(
                        R.string.challenges_curated_by
                    ) + "\n" + stringResource(
                        R.string.none_other_than
                    ),
                    style = TextStyle(
                        color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Pink2
                    ), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(9.dp)
                ) {
                    Text(
                        text = stringResource(R.string.our_ultra_cool_ai), style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                        ), modifier = Modifier.padding(12.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.onboard_img_2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}


@Composable
fun OnboardScreen3() {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(33.dp), colors = CardDefaults.cardColors(
                containerColor = OnboardCard3Color
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onboard_img_3),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Orange
                    ), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(9.dp)
                ) {
                    Text(
                        text = stringResource(R.string.that_s_right_not_your) + "\n" + stringResource(
                            R.string.regular_boring
                        ) + "\n" + stringResource(R.string.grown_up_stuff),
                        style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                        ),
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.but_ai_crafted_dates_and) + "\n" + stringResource(
                        R.string.questions_that_are_as
                    ) + "\n" + stringResource(R.string.wild_and_unpredictable_as_you),
                    style = TextStyle(
                        color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}


@Composable
fun OnboardScreen4() {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(33.dp), colors = CardDefaults.cardColors(
                containerColor = OnboardCard4Color
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.ready_to_challenge_the) + "\n" + stringResource(R.string.norm) + "\n" + stringResource(
                        R.string.ready_to_flex
                    ) + "\n" + stringResource(R.string.your_wits),
                    style = TextStyle(
                        color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.onboard_img_4),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Yellow
                    ), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(9.dp)
                ) {
                    Text(
                        text = stringResource(R.string.ready_to_go) + "\n" + stringResource(R.string.a_little_crazy),
                        style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                        ),
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun OnboardScreen5() {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(33.dp), colors = CardDefaults.cardColors(
                containerColor = OnboardCard5Color
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onboard_img_4),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = stringResource(R.string.let_s_do_this), style = TextStyle(
                        color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                    ), modifier = Modifier.padding(start = 10.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Green
                    ), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(9.dp)
                ) {
                    Text(
                        text = stringResource(R.string.but_remember_no) + "\n" + stringResource(R.string.rules_just_our_ai_s) + "\n" + stringResource(
                            R.string.whims
                        ),
                        style = TextStyle(
                            color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                        ),
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.dive_in_and_let_the) + "\n" + stringResource(R.string.games_begin),
                    style = TextStyle(
                        color = Color.White, fontSize = 22.sp, fontFamily = Fonts.font
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}