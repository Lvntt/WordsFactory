package dev.lantt.wordsfactory.training.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusSmall
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkDark
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PaddingRegular
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphLarge
import dev.lantt.wordsfactory.core.presentation.ui.theme.TestOptionSelectedBackground
import dev.lantt.wordsfactory.core.presentation.ui.theme.TestOptionSelectedBorder
import dev.lantt.wordsfactory.core.presentation.ui.theme.TestOptionUnselectedBackground
import dev.lantt.wordsfactory.core.presentation.ui.theme.TestOptionUnselectedBorder
import dev.lantt.wordsfactory.core.presentation.util.noRippleClickable

@Composable
fun TestOption(
    optionOrder: String,
    optionText: String,
    onChooseOption: (String) -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) TestOptionSelectedBorder
        else TestOptionUnselectedBorder
    val backgroundColor = if (isSelected) TestOptionSelectedBackground
        else TestOptionUnselectedBackground
    val borderWidth = if (isSelected) 2.dp else 1.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(CornerRadiusSmall)
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(CornerRadiusSmall)
            )
            .noRippleClickable {
                onChooseOption(optionText)
            }
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = PaddingRegular,
                vertical = PaddingMedium
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(PaddingMedium)
        ) {
            Text(
                text = optionOrder,
                style = ParagraphLarge,
                color = InkDark
            )

            Text(
                text = optionText,
                style = ParagraphLarge,
                color = InkDark,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}