package eu.foregather.components

import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal

import scala.scalajs.js.Object
import scala.scalajs.js

import eu.foregather.model.QCM
import eu.foregather.model.Profile
import eu.foregather.model.Circuit
import eu.foregather.model.CorrectAnswer
import eu.foregather.model.WrongAnswer
import eu.foregather.data.DataSet


class QuizScreen
    extends NavigationScreenComponent[QuizScreen.Params, QuizScreen.State] {
  import QuizScreen._

  initialState(State(qcm = DataSet.next, Circuit.initialState))

  def textElement(t: String) = View(style = GlobalStyles.textBlock)(
    Text(style = GlobalStyles.defaultTextStyle)(t)
  )

  def chooseAnswer(i: Int) = () => {
    if (state.qcm.correctAnswer == i) setState((s: State) => 
      State(DataSet.next, Circuit.actionHandler(CorrectAnswer, s.profile)))
    else setState((s: State) => 
      State(DataSet.next, Circuit.actionHandler(WrongAnswer, s.profile)))
  }

  def answerElement(i: Int) = 
        TouchableHighlight(style = styles.answer, onPress = chooseAnswer(i))(textElement(state.qcm.answers(i)))

  def render() = {
    View(style = styles.container)(
      View(style = styles.top)(textElement(state.profile.score.toString)),
      View(style = styles.middle)(textElement(state.qcm.question)),
      View(style = styles.bottom)(
        answerElement(0),answerElement(1),answerElement(2),answerElement(3)
      )
    )
  }
}

object QuizScreen {
  trait Params extends Object {
  }

  case class State(qcm: QCM, profile: Profile)

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

