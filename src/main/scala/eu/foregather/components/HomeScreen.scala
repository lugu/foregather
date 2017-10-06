package eu.foregather.components

import sri.macros.{OptDefault => NoValue, OptionalParam => U}
import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal
import eu.foregather.components.QuizScreen.State
import eu.foregather.components.QuizScreen.Params

import scala.scalajs.js

import eu.foregather.model.QCM
import eu.foregather.model.User
import eu.foregather.model.DataSet

class HomeScreen extends NavigationScreenComponentNoPS {
  import HomeScreen._

  def textElement(t: String) = View(style = GlobalStyles.textBlock)(
    Text(style = GlobalStyles.defaultTextStyle)(t)
  )

  def render() = {
    View(style = styles.container)(
        View(style = styles.top)(
            View(style = styles.progress)(textElement("Progress")),
            View(style = styles.rightBlock)(
                View(style = styles.character)(textElement("Character")),
                View(style = styles.score)(textElement("Score"))
            )
        ),
        View(style = styles.bottom)(
            View(style = styles.history)(textElement("history")),
            TouchableHighlight(
              style = styles.run,
              onPress = () => navigation.navigate[QuizScreen](new Params {
                override val user: User = new User("Joan", 100)
              }))(textElement("Start Quiz !"))
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
      backgroundColor := GlobalStyles.blueGris
    )
    val history = style(
      flex := 1,
      backgroundColor := GlobalStyles.blanc
    )
    val rightBlock = style(
      flex := 1
    )
    val character = style(
      flex := 1,
      backgroundColor := GlobalStyles.blanc
    )
    val score = style(
      flex := 1,
      backgroundColor := GlobalStyles.blueSobre
    )
    val run = style(
      flex := 1,
      backgroundColor := GlobalStyles.blueProfond
    )
  }
}
