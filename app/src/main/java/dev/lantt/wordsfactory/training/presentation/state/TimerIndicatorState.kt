package dev.lantt.wordsfactory.training.presentation.state

sealed interface TimerIndicatorState {

    data object FiveSeconds : TimerIndicatorState

    data object FourSeconds : TimerIndicatorState

    data object ThreeSeconds : TimerIndicatorState

    data object TwoSeconds : TimerIndicatorState

    data object OneSecond : TimerIndicatorState

    data object GoState : TimerIndicatorState

}