package foregather.eu.components

import sri.macros.{OptDefault => NoValue, OptionalParam => U}
import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal
import foregather.eu.components.QuizScreen.Params

import scala.scalajs.js

import foregather.eu.model.QCM
import foregather.eu.model.DataSet

class HomeScreen extends NavigationScreenComponentNoPS {
  import HomeScreen._
  def render() = {
    View(style = styles.container)(
        View(style = styles.top)(
            View(style = styles.progress)(),
            View(style = styles.rightBlock)(
                View(style = styles.character)(),
                View(style = styles.score)()
            )
        ),
        View(style = styles.bottom)(
            View(style = styles.history)(),
            TouchableHighlight(
              style = styles.run,
              onPress = () => navigation.navigate[QuizScreen]( new Params { override val qcm: QCM = DataSet.next })
              )("Quiz")
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
      flex := 1
    )
    val history = style(
      flex := 1
    )
    val rightBlock = style(
      flex := 1
    )
    val character = style(
      flex := 1
    )
    val score = style(
      flex := 1
    )
    val run = style(
      flex := 1
    )
  }
}
