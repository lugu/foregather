package eu.foregather.components

import sri.universal.styles.InlineStyleSheetUniversal

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

  val wholeContainer = style(flex := 1, padding := 20)

  val defaultHeader = colorStyle(blueFonce)

  def colorStyle(c: String) = style(backgroundColor := c)

  val defaultCardStyle = style(backgroundColor := "rgb(243, 241, 241)")

}
