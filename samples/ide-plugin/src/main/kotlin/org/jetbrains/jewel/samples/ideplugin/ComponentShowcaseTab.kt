package org.jetbrains.jewel.samples.ideplugin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ui.JBColor
import com.intellij.util.ui.JBUI
import icons.JewelIcons
import org.jetbrains.jewel.bridge.LocalComponent
import org.jetbrains.jewel.bridge.toComposeColor
import org.jetbrains.jewel.foundation.lazy.tree.buildTree
import org.jetbrains.jewel.foundation.modifier.onActivated
import org.jetbrains.jewel.foundation.modifier.trackActivation
import org.jetbrains.jewel.foundation.modifier.trackComponentActivation
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.foundation.theme.LocalContentColor
import org.jetbrains.jewel.intui.markdown.bridge.ProvideMarkdownStyling
import org.jetbrains.jewel.markdown.extensions.Markdown
import org.jetbrains.jewel.ui.Orientation
import org.jetbrains.jewel.ui.Outline
import org.jetbrains.jewel.ui.component.CheckboxRow
import org.jetbrains.jewel.ui.component.CircularProgressIndicator
import org.jetbrains.jewel.ui.component.CircularProgressIndicatorBig
import org.jetbrains.jewel.ui.component.DefaultButton
import org.jetbrains.jewel.ui.component.Divider
import org.jetbrains.jewel.ui.component.Dropdown
import org.jetbrains.jewel.ui.component.Icon
import org.jetbrains.jewel.ui.component.IconButton
import org.jetbrains.jewel.ui.component.LazyTree
import org.jetbrains.jewel.ui.component.OutlinedButton
import org.jetbrains.jewel.ui.component.RadioButtonRow
import org.jetbrains.jewel.ui.component.Slider
import org.jetbrains.jewel.ui.component.Text
import org.jetbrains.jewel.ui.component.TextField
import org.jetbrains.jewel.ui.component.Tooltip
import org.jetbrains.jewel.ui.component.Typography
import org.jetbrains.jewel.ui.component.separator

@Composable
internal fun ComponentShowcaseTab() {
    val bgColor by remember(JBColor.PanelBackground.rgb) { mutableStateOf(JBColor.PanelBackground.toComposeColor()) }

    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier.trackComponentActivation(LocalComponent.current)
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ColumnOne()
        ColumnTwo()
    }
}

@Composable
private fun RowScope.ColumnOne() {
    Column(
        Modifier.trackActivation().weight(1f),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var activated by remember { mutableStateOf(false) }
        Text(
            "Here is a selection of our finest components(activated: $activated):",
            Modifier.onActivated {
                activated = it
            },
            style = Typography.h3TextStyle(),
        )

        var selectedItem by remember { mutableIntStateOf(-1) }
        Dropdown(
            menuContent = {
                selectableItem(selectedItem == 0, onClick = { selectedItem = 0 }) {
                    Text("Hello")
                }

                separator()

                selectableItem(selectedItem == 1, onClick = { selectedItem = 1 }) {
                    Text("World")
                }
            },
        ) {
            Text("Selected item $selectedItem")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var clicks1 by remember { mutableStateOf(0) }
            OutlinedButton({ clicks1++ }) {
                Text("Outlined: $clicks1")
            }
            OutlinedButton({ }, enabled = false) {
                Text("Outlined")
            }

            var clicks2 by remember { mutableStateOf(0) }
            DefaultButton({ clicks2++ }) {
                Text("Default: $clicks2")
            }
            DefaultButton({ }, enabled = false) {
                Text("Default")
            }
        }

        var textFieldValue by remember { mutableStateOf("") }
        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            modifier = Modifier.width(200.dp),
            placeholder = { Text("Write something...") },
        )

        var checked by remember { mutableStateOf(false) }
        var validated by remember { mutableStateOf(false) }
        val outline = if (validated) Outline.Error else Outline.None

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            CheckboxRow(
                checked = checked,
                onCheckedChange = { checked = it },
                outline = outline,
            ) {
                Text("Hello, I am a themed checkbox")
            }

            CheckboxRow(checked = validated, onCheckedChange = { validated = it }) {
                Text("Show validation")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            var index by remember { mutableStateOf(0) }
            RadioButtonRow(
                selected = index == 0,
                onClick = { index = 0 },
                outline = outline,
            ) {
                Text("I am number one")
            }
            RadioButtonRow(
                selected = index == 1,
                onClick = { index = 1 },
                outline = outline,
            ) {
                Text("Sad second")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                "actions/close.svg",
                iconClass = AllIcons::class.java,
                modifier = Modifier.border(1.dp, Color.Magenta),
                contentDescription = "An icon",
            )
            Icon(
                "icons/github.svg",
                iconClass = JewelIcons::class.java,
                modifier = Modifier.border(1.dp, Color.Magenta),
                contentDescription = "An icon",
            )

            IconButton(onClick = { }) {
                Icon("actions/close.svg", contentDescription = "An icon", AllIcons::class.java)
            }
            IconButton(onClick = { }) {
                Icon("actions/addList.svg", contentDescription = "An icon", AllIcons::class.java)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text("Circular progress small:")
            CircularProgressIndicator()
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text("Circular progress big:")
            CircularProgressIndicatorBig()
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Tooltip(tooltip = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon("general/showInfos.svg", contentDescription = null, AllIcons::class.java)

                    Text("This is a tooltip")
                }
            }) {
                Text(
                    modifier = Modifier.border(1.dp, JewelTheme.globalColors.borders.normal).padding(12.dp, 8.dp),
                    text = "Hover Me!",
                )
            }
        }

        var sliderValue by remember { mutableStateOf(.15f) }
        Slider(sliderValue, { sliderValue = it }, steps = 5)
    }
}

@Composable
private fun RowScope.ColumnTwo() {
    Column(
        Modifier.trackActivation().weight(1f),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        MarkdownExample()

        Divider(Orientation.Horizontal)

        var activated by remember { mutableStateOf(false) }
        Text(
            "activated: $activated",
            Modifier.onActivated {
                activated = it
            },
        )

        val tree = remember {
            buildTree {
                addNode("root 1") {
                    addLeaf("leaf 1")
                    addLeaf("leaf 2")
                }
                addNode("root 2") {
                    addLeaf("leaf 1")
                    addNode("node 1") {
                        addLeaf("leaf 1")
                        addLeaf("leaf 2")
                    }
                }
                addNode("root 3") {
                    addLeaf("leaf 1")
                    addLeaf("leaf 2")
                }
            }
        }
        LazyTree(
            tree = tree,
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            onElementClick = {},
            onElementDoubleClick = {},
        ) { element ->
            Box(Modifier.fillMaxWidth()) {
                Text(element.data, Modifier.padding(2.dp))
            }
        }
    }
}

@Composable
private fun MarkdownExample() {
    var enabled by remember { mutableStateOf(true) }
    CheckboxRow("Enabled", enabled, { enabled = it })

    val contentColor = if (enabled) JewelTheme.globalColors.text.normal else JewelTheme.globalColors.text.disabled
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        ProvideMarkdownStyling {
            Markdown(
                """
                |Hi! This is an example of **Markdown** rendering. We support the [CommonMark specs](https://commonmark.org/)
                |out of the box, but `you` can also have _extensions_.
                |
                |For example:
                | * Images
                | * Tables
                | * And more — I am running out of random things to say 😆
                |
                |```kotlin
                |fun hello() = "World"
                |```
                """.trimMargin(),
                Modifier.fillMaxWidth()
                    .background(JBUI.CurrentTheme.Banner.INFO_BACKGROUND.toComposeColor(), RoundedCornerShape(8.dp))
                    .border(
                        1.dp,
                        JBUI.CurrentTheme.Banner.INFO_BORDER_COLOR.toComposeColor(),
                        RoundedCornerShape(8.dp),
                    )
                    .padding(8.dp),
                enabled = enabled,
                onUrlClick = { url -> BrowserUtil.open(url) },
            )
        }
    }
}
