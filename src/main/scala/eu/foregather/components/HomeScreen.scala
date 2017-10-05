package eu.foregather.components

import sri.macros.{OptDefault => NoValue, OptionalParam => U}
import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal
import eu.foregather.components.QuizScreen.Params

import scala.scalajs.js

import eu.foregather.model.QCM
import eu.foregather.model.DataSet

class HomeScreen extends NavigationScreenComponentNoPS {
  import HomeScreen._
  def render() = {
    View(style = styles.container)(
        View(style = styles.top)(
            View(style = styles.progress)(TextC("Progress")),
            View(style = styles.rightBlock)(
                View(style = styles.character)(TextC("Character")),
                View(style = styles.score)(TextC("Score"))
            )
        ),
        View(style = styles.bottom)(
            View(style = styles.history)(TextC("history")),
            TouchableHighlight(
              style = styles.run,
              onPress = () => navigation.navigate[QuizScreen]( new Params { override val qcm: QCM = DataSet.next })
              )(TextC("Quiz"))
        )
    )
  }
}

object HomeScreen {

  object styles extends InlineStyleSheetUniversal {

    import dsl._

    val container = style(
      flex := 1
    )
    val top = style(
      flex := 1,
      flexDirection := "row"
    )
    val bottom = style(
      flex := 1
    )
    val progress = style(
      flex := 1,
      backgroundColor := "yellow"
    )
    val history = style(
      flex := 1,
      backgroundColor := "red"
    )
    val rightBlock = style(
      flex := 1
    )
    val character = style(
      flex := 1
    )
    val score = style(
      flex := 1,
      backgroundColor := "blue"
    )
    val run = style(
      flex := 1
    )
  }
}
