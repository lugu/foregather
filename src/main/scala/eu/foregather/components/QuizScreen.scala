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

  def textElement(t: String) = View(style = GlobalStyles.textBlock)(
    Text(style = GlobalStyles.defaultTextStyle)(t)
  )

  def answerElement(i: Int) = 
        TouchableHighlight( style = styles.answer,
          onPress = () => navigation.navigate[HomeScreen])(textElement(params.get.qcm.answers(i)))

  def render() = {
    View(style = styles.container)(
      View(style = styles.top)(textElement("Progress")),
      View(style = styles.middle)(textElement(params.get.qcm.question)),
      View(style = styles.bottom)(
        answerElement(0),answerElement(1),answerElement(2),answerElement(3)
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

