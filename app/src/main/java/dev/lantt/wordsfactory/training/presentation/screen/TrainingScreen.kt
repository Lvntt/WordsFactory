package dev.lantt.wordsfactory.training.presentation.screen

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH4
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingLarge
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor
import dev.lantt.wordsfactory.core.presentation.util.hasNotificationPermission
import dev.lantt.wordsfactory.training.presentation.components.NotificationPermissionDialog
import dev.lantt.wordsfactory.training.presentation.components.StartTrainingTimerIndicator
import dev.lantt.wordsfactory.training.presentation.event.TrainingEvent
import dev.lantt.wordsfactory.training.presentation.state.TrainingState
import dev.lantt.wordsfactory.training.presentation.viewmodel.TrainingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrainingScreen(
    onNavigateToTestQuestion: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrainingViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val notificationsPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            viewModel.onStartTimer()
        }
    )

    val permissionDialogState by viewModel.permissionDialogState.collectAsStateWithLifecycle()
    val trainingState by viewModel.trainingState.collectAsStateWithLifecycle()
    val savedDictionaryWords by viewModel.savedDictionaryWords.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.trainingEvents.collect { event ->
            when (event) {
                TrainingEvent.TrainingStarted -> onNavigateToTestQuestion()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = PaddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        if (savedDictionaryWords.isEmpty()) {
            val dictionaryWordsTitle = buildAnnotatedString {
                append(stringResource(id = R.string.dictionaryNoWordsFirst))
                withStyle(style = SpanStyle(color = PrimaryColor)) {
                    append(" ${stringResource(id = R.string.noWords)} ")
                }
                append(stringResource(id = R.string.dictionaryNoWordsLast))
            }

            Text(
                text = dictionaryWordsTitle,
                style = HeadingH4,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        } else {
            val dictionaryWordsTitle = buildAnnotatedString {
                append(stringResource(id = R.string.dictionaryWordsTitleFirst))
                withStyle(style = SpanStyle(color = PrimaryColor)) {
                    append(" ${savedDictionaryWords.size} ")
                }
                append(stringResource(id = R.string.dictionaryWordsTitleLast))
            }

            Text(
                text = dictionaryWordsTitle,
                style = HeadingH4,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(PaddingLarge))

        Text(
            text = stringResource(id = R.string.trainingQuestion),
            style = HeadingH4,
            color = Color.Black
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            if (trainingState == TrainingState.TimerCountdown) {
                StartTrainingTimerIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    onEndTimer = viewModel::onStartTraining
                )
            } else {
                PrimaryButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = PaddingMedium),
                    onClick = {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                            !hasNotificationPermission(context) &&
                            permissionDialogState.shouldShow
                        ) {
                            viewModel.onShowDialog()
                        } else {
                            viewModel.onStartTimer()
                        }
                    },
                    text = stringResource(id = R.string.start)
                )
            }
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && permissionDialogState.isShown) {
        NotificationPermissionDialog(
            onDismiss = {
                viewModel.onDismissDialog()
                viewModel.onStartTimer()
            },
            onOk = {
                viewModel.onDismissDialog()
                notificationsPermissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        )
    }
}