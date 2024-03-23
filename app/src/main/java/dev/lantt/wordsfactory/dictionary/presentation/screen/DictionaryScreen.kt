package dev.lantt.wordsfactory.dictionary.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.shared.InputTextFieldWithAction
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingLarge
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
    val query by viewModel.query.collectAsStateWithLifecycle()
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
            value = query,
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
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(PaddingLarge)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    color = PrimaryColor
                )
            }
            is DictionaryUiState.Error -> {
                Log.e("DictionaryScreen", (uiState as DictionaryUiState.Error).message.toString())
            }
            is DictionaryUiState.Success -> {
                DictionaryWordContent(
                    word = (uiState as DictionaryUiState.Success).word
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