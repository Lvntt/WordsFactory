package dev.lantt.wordsfactory.core.presentation.navigation

import dev.lantt.wordsfactory.R

object BottomNavItems {
    val items = listOf(
        BottomNavItem(
            labelId = R.string.dictionary,
            iconId = R.drawable.ic_dictionary,
            route = WordsFactoryDestinations.DICTIONARY,
            isAccent = true
        ),
        BottomNavItem(
            labelId = R.string.training,
            iconId = R.drawable.ic_training,
            route = WordsFactoryDestinations.TRAINING
        ),
        BottomNavItem(
            labelId = R.string.video,
            iconId = R.drawable.ic_video,
            route = WordsFactoryDestinations.VIDEO
        )
    )
}