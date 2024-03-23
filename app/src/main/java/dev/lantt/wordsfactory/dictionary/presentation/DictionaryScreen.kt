package dev.lantt.wordsfactory.dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.ui.shared.InputTextFieldWithAction
import dev.lantt.wordsfactory.core.presentation.ui.shared.PrimaryButton
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingSmall
import dev.lantt.wordsfactory.core.presentation.util.noRippleClickable

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var searchText by remember { mutableStateOf("") }

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
            value = searchText,
            onValueChange = { searchText = it },
            trailingIcon = ImageVector.vectorResource(id = R.drawable.ic_search),
            trailingIconDescription = stringResource(id = R.string.search),
            onTrailingIconClick = {
                // TODO
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

//        Spacer(modifier = Modifier.weight(1f))

//        DictionaryPlaceholderContent(
//            modifier = Modifier.fillMaxWidth()
//        )

//        Spacer(modifier = Modifier.weight(1f))

        DictionaryWordContent()

        Spacer(modifier = Modifier.weight(1f))

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