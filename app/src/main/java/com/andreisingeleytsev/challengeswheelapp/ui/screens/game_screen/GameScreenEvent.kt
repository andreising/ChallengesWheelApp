package com.andreisingeleytsev.challengeswheelapp.ui.screens.game_screen

sealed class GameScreenEvent{
    object OnSpin: GameScreenEvent()
    object OnPass: GameScreenEvent()
    object OnCorrect: GameScreenEvent()
}
