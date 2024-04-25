package dev.lantt.wordsfactory.core.presentation.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.lantt.wordsfactory.core.presentation.ui.theme.CornerRadiusLarge
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkGray
import dev.lantt.wordsfactory.core.presentation.ui.theme.InkWhite
import dev.lantt.wordsfactory.core.presentation.ui.theme.ParagraphMedium
import dev.lantt.wordsfactory.core.presentation.ui.theme.PrimaryColor
import dev.lantt.wordsfactory.core.presentation.util.NoRippleInteractionSource

@Composable
fun BottomNavigationBar(
    buttons: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val bottomNavigationBarShape = RoundedCornerShape(
        topStart = CornerRadiusLarge,
        topEnd = CornerRadiusLarge
    )

    NavigationBar(
        modifier = modifier
            .clip(shape = bottomNavigationBarShape)
            .border(
                width = 1.dp,
                color = InkGray,
                shape = bottomNavigationBarShape
            ),
        containerColor = InkWhite,
        contentColor = InkGray
    ) {
        buttons.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                modifier = if (item.isAccent) {
                    Modifier.padding(top = 6.dp)
                } else {
                    Modifier
                },
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.iconId),
                        contentDescription = stringResource(id = item.labelId),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.labelId),
                        style = ParagraphMedium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryColor,
                    selectedTextColor = PrimaryColor,
                    indicatorColor = InkWhite,
                    unselectedIconColor = InkGray,
                    unselectedTextColor = InkGray
                ),
                interactionSource = NoRippleInteractionSource()
            )
        }
    }
}