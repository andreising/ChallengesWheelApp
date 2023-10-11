package com.andreisingeleytsev.challengeswheelapp.ui.screens.game_screen

import android.app.Application
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.challengeswheelapp.ui.utils.UIEvents
import com.andreisingeleytsev.challengeswheelapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val app: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {
    private val _uiEvent = Channel<UIEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: GameScreenEvent) {
        when (event) {
            is GameScreenEvent.OnSpin -> {
                sendUIEvent(UIEvents.Roll)
                viewModelScope.launch {
                    delay(3500)
                    isOpened.value = true
                    timer.start()
                }
            }

            is GameScreenEvent.OnPass -> {
                prepareToNewRound()
            }

            is GameScreenEvent.OnCorrect -> {
                prepareToNewRound()
                rounded.value++
            }
        }
    }

    private fun sendUIEvent(event: UIEvents) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    val rounded = mutableStateOf(
        1
    )
    val question = mutableStateOf(
        ""
    )
    val answer = mutableStateOf<String?>(
        null
    )
    val isOpened = mutableStateOf(false)
    val time = mutableStateOf(
        15
    )
    private val timer = object : CountDownTimer(15000, 1000) {
        override fun onTick(p0: Long) {
            time.value--
        }

        override fun onFinish() {
            viewModelScope.launch {
                isOpened.value = false
                time.value = 15
            }
            viewModelScope.launch {
                generateUIItems()
            }
        }
    }
    val id = mutableStateOf(
        savedStateHandle.get<String>("isQuestions")!!.toInt()
    )
    private val arrayID = if (id.value == 1) R.array.questions
    else R.array.challenges

    init {
        generateUIItems()
    }

    private fun generateUIItems() {
        val list = app.resources.getStringArray(arrayID)
        val pair = getText(list.random())
        question.value = pair.first
        answer.value = pair.second
    }

    private fun getText(string: String): Pair<String, String> {
        var result: Pair<String, String>?
        string.split("|").also {
            result = Pair(it[0], it[1])
        }
        return result!!
    }

    private fun prepareToNewRound() {
        time.value = 15
        isOpened.value = false
        timer.cancel()
        viewModelScope.launch {
            generateUIItems()
        }
    }
}