package foregather.eu.components

import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal

import scala.scalajs.js.Object
import scala.scalajs.js

import foregather.eu.model.QCM
import foregather.eu.model.DataSet


class QuizScreen
    extends NavigationScreenComponentP[QuizScreen.Params] {
  import QuizScreen._
  def render() = {
    View(style = styles.container)(
      View(style = styles.top)(TextC("Progress")),
      View(style = styles.middle)(TextC(params.get.qcm.question)),
      View(style = styles.bottom)(
        View(style = styles.columnAnswers)(
          TouchableHighlight( style = styles.answer,
            onPress = () => navigation.navigate[HomeScreen])(params.get.qcm.answers._1),
          TouchableHighlight( style = styles.answer,
            onPress = () => navigation.navigate[HomeScreen])(params.get.qcm.answers._3)
        ),
        View(style = styles.columnAnswers)(
          TouchableHighlight( style = styles.answer,
            onPress = () => navigation.navigate[HomeScreen])(params.get.qcm.answers._2),
          TouchableHighlight( style = styles.answer,
            onPress = () => navigation.navigate[HomeScreen])(params.get.qcm.answers._4)
        )
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
      flex := 1
    )
    val middle = style(
      flex := 1
    )
    val bottom = style(
      flex := 2,
      flexDirection := "row"
    )
    val columnAnswers = style(
      flex := 1,
      justifyContent := "space-around",
      alignItems := "center"
    )
    val answer = style(
      flex := 1,
      backgroundColor := "skyblue"
    )
  }
}

