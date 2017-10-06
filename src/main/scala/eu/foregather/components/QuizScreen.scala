package eu.foregather.components

import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal

import scala.scalajs.js.Object
import scala.scalajs.js

import eu.foregather.model.QCM
import eu.foregather.model.DataSet


class QuizScreen
    extends NavigationScreenComponentP[QuizScreen.Params] {
  import QuizScreen._
  def render() = {
    View(style = styles.container)(
      View(style = styles.top)(TextC("Progress")),
      View(style = styles.middle)(TextC(params.get.qcm.question)),
      View(style = styles.bottom)(
        TouchableHighlight( style = styles.answer,
          onPress = () => navigation.navigate[HomeScreen])(TextC(params.get.qcm.answers._1)),
        TouchableHighlight( style = styles.answer,
          onPress = () => navigation.navigate[HomeScreen])(TextC(params.get.qcm.answers._2)),
        TouchableHighlight( style = styles.answer,
          onPress = () => navigation.navigate[HomeScreen])(TextC(params.get.qcm.answers._3)),
        TouchableHighlight( style = styles.answer,
          onPress = () => navigation.navigate[HomeScreen])(TextC(params.get.qcm.answers._4))
      )
    )
  }
}

object QuizScreen {
  trait Params extends Object {
    val qcm: QCM
  }

  object styles extends InlineStyleSheetUniversal {

    import dsl._

    val container = style(
      flex := 1
    )
    val top = style(
      backgroundColor := GlobalStyles.blueGris,
      flex := 1
    )
    val middle = style(
      flex := 1
    )
    val bottom = style(
      flex := 4,
      flexDirection := "row",
      justifyContent := "space-between",
      alignItems := "center",
      flexWrap := "wrap"
    )
    val answer = style(
      backgroundColor := GlobalStyles.blueLumineux,
      margin := 20,
      width := 125,
      height := 125,
      alignItems := "center",
      justifyContent := "center"
    )
  }
}

