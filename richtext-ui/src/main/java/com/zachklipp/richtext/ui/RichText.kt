@file:Suppress("RemoveEmptyParenthesesFromAnnotationEntry")

package com.zachklipp.richtext.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.DensityAmbient

/**
 * Draws some rich text. Entry point to the rich text library.
 */
@Composable
fun RichText(
  modifier: Modifier = Modifier.None,
  style: RichTextStyle? = null,
  children: @Composable() RichTextScope.() -> Unit
) {
  with(RichTextScope) {
    // Nested RichTexts should not continue list leveling from the parent.
    RestartListLevel {
      println("RichTextStyle: $style")
      WithStyle(style) {
        val resolvedStyle = currentRichTextStyle.resolveDefaults()
        val blockSpacing = with(DensityAmbient.current) {
          resolvedStyle.paragraphSpacing!!.toIntPx()
        }

        Column(modifier = modifier) {
          children()
        }
      }
    }
  }
}
