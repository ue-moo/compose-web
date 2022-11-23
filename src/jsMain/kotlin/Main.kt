import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    var count: Int by mutableStateOf(0)

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)
        Layout {
            Button(attrs = {
                onClick { count -= 1 }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$count")
            }

            Div(
                attrs = {
                    classes(TextStyleSheet.text)
                },
            ) {
                Text("スタイルあてたやつ")
            }

            Button(attrs = {
                onClick { count += 1 }
            }) {
                Text("+")
            }

            BouncedButton("https://qiita.com/b4tchkn/items/f7ff79afd00a73df0b1d#%E3%81%A7%E3%81%8D%E3%81%9F%E3%83%9D%E3%83%BC%E3%83%88%E3%83%95%E3%82%A9%E3%83%AA%E3%82%AA%E3%82%92%E5%85%AC%E9%96%8B%E3%81%99%E3%82%8B", "できてる")
        }

        Footer(
            attrs = {
                classes(AppStyleSheet.footer, TextStyleSheet.text)
            },
        ) {
            Text("Footer")
        }
    }
}

object TextStyleSheet : StyleSheet(AppStyleSheet) {
    val text by style {
        property(
            "font-family",
            "Lato, Noto Sans JP, system-ui,-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Droid Sans,Helvetica Neue,Arial,sans-serif",
        )
    }
}

@Composable
fun BouncedButton(href: String, title: String) {
    A(
        attrs = {
            classes(AppStyleSheet.bouncedButton, AppStyleSheet.bouncedButtonSpan)
        },
        href = href,
    ) {
        Div(attrs = {
            classes(TextStyleSheet.text)
        }) {
            Text(title)
        }
    }
}

@Composable
private fun Layout(
    content: @Composable () -> Unit,
) {
    Div(
        attrs = {
            classes(AppStyleSheet.conterContainer)
        },
    ) {
        content()
    }
}

object AppStyleSheet : StyleSheet() {
    val conterContainer by style {
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        flexDirection(FlexDirection.Column)
        alignItems(AlignItems.Center)
        width(100.vw)
        height(100.vh)
        background("#F1F1F1")
    }

    val footer by style {
        display(DisplayStyle.Flex)
        position(Position.Fixed)
        bottom(0.px)
        justifyContent(JustifyContent.Center)
        paddingBottom(16.px)
        alignItems(AlignItems.Center)
        width(100.vw)
    }

    val bouncedButton by style {
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        height(44.px)
        width(240.px)
        boxSizing("border-box")
        color(Color("#000000"))
        letterSpacing(0.1.em)
        textDecoration("none")
        position(Position.Relative)
    }

    val bouncedButtonSpan by style {
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        height(44.px)
        width(240.px)
        background("#FFFFFF")
        border {
            width = 1.px
            style = LineStyle.Solid
            color = Color("#000000")
        }
        boxSizing("border-box")
        top((-6).px)
        left((-6).px)
        property("transition-duration", "0.2s")
        property("box-shadow", "0px 5px 12px #CCCCCC, -6px -6px 12px #FFF")
        hover(self) style {
            left((-1).px)
            top((-1).px)
            property("box-shadow", "0px 0px 4px #000000, -2px -2px 4px #FFF")
        }
    }
}

