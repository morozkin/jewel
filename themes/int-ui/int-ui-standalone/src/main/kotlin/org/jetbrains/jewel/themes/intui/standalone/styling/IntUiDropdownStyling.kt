package org.jetbrains.jewel.themes.intui.standalone.styling

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.jetbrains.jewel.DropdownState
import org.jetbrains.jewel.IntelliJTheme
import org.jetbrains.jewel.styling.DropdownColors
import org.jetbrains.jewel.styling.DropdownIcons
import org.jetbrains.jewel.styling.DropdownMetrics
import org.jetbrains.jewel.styling.DropdownStyle
import org.jetbrains.jewel.styling.MenuStyle
import org.jetbrains.jewel.styling.ResourcePainterProvider
import org.jetbrains.jewel.styling.StatefulPainterProvider
import org.jetbrains.jewel.themes.intui.core.palette.IntUiDarkPalette
import org.jetbrains.jewel.themes.intui.core.palette.IntUiLightPalette

@Stable
data class IntUiDropdownStyle(
    override val colors: IntUiDropdownColors,
    override val metrics: IntUiDropdownMetrics,
    override val icons: IntUiDropdownIcons,
    override val textStyle: TextStyle,
    override val menuStyle: MenuStyle
) : DropdownStyle {

    companion object {

        @Composable
        fun light(
            colors: IntUiDropdownColors = IntUiDropdownColors.light(),
            metrics: IntUiDropdownMetrics = IntUiDropdownMetrics(),
            icons: IntUiDropdownIcons = IntUiDropdownIcons(),
            textStyle: TextStyle = IntelliJTheme.defaultTextStyle,
            menuStyle: MenuStyle = IntUiMenuStyle.light()
        ) = IntUiDropdownStyle(colors, metrics, icons, textStyle, menuStyle)

        @Composable
        fun dark(
            colors: IntUiDropdownColors = IntUiDropdownColors.dark(),
            metrics: IntUiDropdownMetrics = IntUiDropdownMetrics(),
            icons: IntUiDropdownIcons = IntUiDropdownIcons(),
            textStyle: TextStyle = IntelliJTheme.defaultTextStyle,
            menuStyle: MenuStyle = IntUiMenuStyle.dark()
        ) = IntUiDropdownStyle(colors, metrics, icons, textStyle, menuStyle)
    }
}

@Immutable
data class IntUiDropdownColors(
    override val background: Color,
    override val backgroundDisabled: Color,
    override val backgroundFocused: Color,
    override val backgroundPressed: Color,
    override val backgroundHovered: Color,
    override val backgroundWarning: Color,
    override val backgroundError: Color,
    override val content: Color,
    override val contentDisabled: Color,
    override val contentFocused: Color,
    override val contentPressed: Color,
    override val contentHovered: Color,
    override val contentWarning: Color,
    override val contentError: Color,
    override val border: Color,
    override val borderDisabled: Color,
    override val borderFocused: Color,
    override val borderPressed: Color,
    override val borderHovered: Color,
    override val borderWarning: Color,
    override val borderError: Color,
    override val iconTint: Color,
    override val iconTintDisabled: Color,
    override val iconTintFocused: Color,
    override val iconTintPressed: Color,
    override val iconTintHovered: Color,
    override val iconTintWarning: Color,
    override val iconTintError: Color
) : DropdownColors {

    companion object {
        // TODO error and warning colors are probably wrong (and no distinction between focused
        //  and unfocused is currently modeled, but it exists in Swing)

        @Composable
        fun light(
            background: Color = IntUiLightPalette.grey(14),
            backgroundDisabled: Color = IntUiLightPalette.grey(13),
            backgroundFocused: Color = background,
            backgroundPressed: Color = background,
            backgroundHovered: Color = background,
            backgroundWarning: Color = background,
            backgroundError: Color = background,
            content: Color = IntUiLightPalette.grey(1),
            contentDisabled: Color = IntUiLightPalette.grey(8),
            contentFocused: Color = content,
            contentPressed: Color = content,
            contentHovered: Color = content,
            contentWarning: Color = content,
            contentError: Color = content,
            border: Color = IntUiLightPalette.grey(9),
            borderDisabled: Color = IntUiLightPalette.grey(11),
            borderFocused: Color = IntUiLightPalette.blue(4),
            borderPressed: Color = border,
            borderHovered: Color = border,
            borderWarning: Color = IntUiLightPalette.yellow(4),
            borderError: Color = IntUiLightPalette.red(4),
            iconTint: Color = IntUiLightPalette.grey(7),
            iconTintDisabled: Color = IntUiLightPalette.grey(9),
            iconTintFocused: Color = iconTint,
            iconTintPressed: Color = iconTint,
            iconTintHovered: Color = iconTint,
            iconTintWarning: Color = iconTint,
            iconTintError: Color = iconTint
        ) = IntUiDropdownColors(
            background,
            backgroundDisabled,
            backgroundFocused,
            backgroundPressed,
            backgroundHovered,
            backgroundWarning,
            backgroundError,
            content,
            contentDisabled,
            contentFocused,
            contentPressed,
            contentHovered,
            contentWarning,
            contentError,
            border,
            borderDisabled,
            borderFocused,
            borderPressed,
            borderHovered,
            borderWarning,
            borderError,
            iconTint,
            iconTintDisabled,
            iconTintFocused,
            iconTintPressed,
            iconTintHovered,
            iconTintWarning,
            iconTintError
        )

        @Composable
        fun dark(
            background: Color = IntUiDarkPalette.grey(2),
            backgroundDisabled: Color = background,
            backgroundFocused: Color = background,
            backgroundPressed: Color = background,
            backgroundHovered: Color = background,
            backgroundWarning: Color = background,
            backgroundError: Color = background,
            content: Color = IntUiDarkPalette.grey(12),
            contentDisabled: Color = IntUiDarkPalette.grey(7),
            contentFocused: Color = content,
            contentPressed: Color = content,
            contentHovered: Color = content,
            contentWarning: Color = content,
            contentError: Color = content,
            border: Color = IntUiDarkPalette.grey(5),
            borderDisabled: Color = IntUiDarkPalette.grey(11),
            borderFocused: Color = IntUiDarkPalette.blue(6),
            borderPressed: Color = border,
            borderHovered: Color = border,
            borderWarning: Color = IntUiDarkPalette.yellow(4),
            borderError: Color = IntUiDarkPalette.red(4),
            iconTint: Color = IntUiDarkPalette.grey(10),
            iconTintDisabled: Color = IntUiDarkPalette.grey(6),
            iconTintFocused: Color = iconTint,
            iconTintPressed: Color = iconTint,
            iconTintHovered: Color = iconTint,
            iconTintWarning: Color = iconTint,
            iconTintError: Color = iconTint
        ) = IntUiDropdownColors(
            background,
            backgroundDisabled,
            backgroundFocused,
            backgroundPressed,
            backgroundHovered,
            backgroundWarning,
            backgroundError,
            content,
            contentDisabled,
            contentFocused,
            contentPressed,
            contentHovered,
            contentWarning,
            contentError,
            border,
            borderDisabled,
            borderFocused,
            borderPressed,
            borderHovered,
            borderWarning,
            borderError,
            iconTint,
            iconTintDisabled,
            iconTintFocused,
            iconTintPressed,
            iconTintHovered,
            iconTintWarning,
            iconTintError
        )
    }
}

@Stable
data class IntUiDropdownMetrics(
    override val minSize: DpSize = DpSize(108.dp, 28.dp),
    override val cornerSize: CornerSize = CornerSize(4.dp),
    override val contentPadding: PaddingValues = PaddingValues(start = 9.dp, end = 8.dp),
    override val borderWidth: Dp = 1.dp
) : DropdownMetrics

@Immutable
data class IntUiDropdownIcons(
    override val chevronDown: StatefulPainterProvider<DropdownState> = ChevronDown()
) : DropdownIcons {

    @Immutable
    data class ChevronDown(
        override val normal: String = "icons/intui/chevronDown.svg",
        override val disabled: String = normal,
        override val focused: String = normal,
        override val pressed: String = normal,
        override val hovered: String = normal
    ) : ResourcePainterProvider<DropdownState>()
}
