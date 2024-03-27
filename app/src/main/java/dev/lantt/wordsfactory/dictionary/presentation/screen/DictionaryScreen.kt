package dev.lantt.wordsfactory.dictionary.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.shared.InputTextFieldWithAction
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.HeadingH5
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor
import dev.lantt.wordsfactory.core.presentation.util.noRippleClickable
import dev.lantt.wordsfactory.dictionary.presentation.components.DictionaryPlaceholderContent
import dev.lantt.wordsfactory.dictionary.presentation.components.DictionaryWordContent
import dev.lantt.wordsfactory.dictionary.presentation.state.DictionaryUiState
import dev.lantt.wordsfactory.dictionary.presentation.viewmodel.DictionaryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier,
    viewModel: DictionaryViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current
    val dictionaryState by viewModel.dictionaryState.collectAsStateWithLifecycle()
    val uiState by viewModel.dictionaryUiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InkWhite)
            .noRippleClickable {
                focusManager.clearFocus()
            }
    ) {
        InputTextFieldWithAction(
            modifier = Modifier.padding(
                top = PaddingRegular,
                start = PaddingMedium,
                end = PaddingMedium
            ),
            value = dictionaryState.query,
            onValueChange = viewModel::onQueryChange,
            trailingIcon = ImageVector.vectorResource(id = R.drawable.ic_search),
            trailingIconDescription = stringResource(id = R.string.search),
            onTrailingIconClick = {
                viewModel.onSearch()
                focusManager.clearFocus()
            }
        )

        if (uiState is DictionaryUiState.Success) {
            Spacer(modifier = Modifier.height(PaddingMedium))
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }

        when (uiState) {
            DictionaryUiState.Initial -> {
                DictionaryPlaceholderContent(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            DictionaryUiState.Loading -> {
                LottieLoading(
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
            is DictionaryUiState.Error -> {
                ErrorContent()
                Log.e("DictionaryScreen", (uiState as DictionaryUiState.Error).message.toString())
            }
            is DictionaryUiState.Success -> {
                DictionaryWordContent(
                    word = (uiState as DictionaryUiState.Success).word,
                    viewModel = viewModel
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (uiState is DictionaryUiState.Success) {
            PrimaryButton(
                modifier = Modifier.padding(
                    horizontal = PaddingRegular,
                    vertical = PaddingSmall
                ),
                onClick = { /*TODO*/ },
                text = stringResource(id = R.string.addToDictionary)
            )
        }
    }
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_wifi_off),
            contentDescription = null,
            tint = PrimaryColor
        )

        Text(
            text = stringResource(id = R.string.connectionError),
            style = HeadingH5,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LottieLoading(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_animation))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    LottieLoading()
}