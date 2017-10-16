package eu.foregather.components

import sri.universal.styles.InlineStyleSheetUniversal
import sri.platform.SriPlatform


object GlobalStyles extends InlineStyleSheetUniversal {

  import dsl._

  val bleuClaire   = "#a7b5da"
  val blueLumineux = "#9aa6f0"
  val blueSobre    = "#9290bc"
  val blueGris     = "#8c8698"
  val blueFonce    = "#44556b"
  val blueProfond  = "#365580"
  val blanc        = "#ffffff"
  val noir         = "#000000"
  def colorStyle(c: String) = style(backgroundColor := c)

  val defaultFontFamily = if (SriPlatform.isIOS) "HelveticaNeue-Thin" else "sans-serif-thin"
  val defaultTextStyle = style(
      fontFamily := defaultFontFamily,
      fontSize := 20
  )

  val defaultHeader = style( backgroundColor := blueFonce)

  val textBlock = style(
      alignItems := "center",
      justifyContent := "center",
      flex := 1)

}

