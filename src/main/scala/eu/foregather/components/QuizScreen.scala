package eu.foregather.components

import sri.navigation._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal

import scala.scalajs.js.Object
import scala.scalajs.js

import eu.foregather.model.Quiz
import eu.foregather.model.Profile
import eu.foregather.model.Circuit
import eu.foregather.model.ProfileWatcher
import eu.foregather.model.CorrectAnswer
import eu.foregather.model.WrongAnswer
import eu.foregather.data.DataSet


class QuizScreen
    extends NavigationScreenComponent[QuizScreen.Params, QuizScreen.State] {
  import QuizScreen._

  initialState(State(quiz = DataSet.next, Circuit.initialState, false))

  val watcher = new ProfileWatcher {
    def onProfileChange(p: Profile) = setState((s: State) => State(s.quiz, p, s.done))
  }

  override def componentWillMount() = {
    Circuit.registerWatcher(watcher)
    setState((s: State) => State(s.quiz, Circuit.initialState, s.done))
  }

  override def componentWillUnmount() = {
    Circuit.unregisterWatcher(watcher)
  }

  def textElement(t: String) = View(style = GlobalStyles.textBlock)(
    Text(style = GlobalStyles.defaultTextStyle)(t)
  )

  def chooseAnswer(i: Int) = () => {
    // 1. update the button style
    setState((s: State) => State(s.quiz, s.profile, true))

    // 2. update the score
    if (state.quiz.correctAnswer == i) Circuit.actionHandler(CorrectAnswer, state.profile)
    else  Circuit.actionHandler(WrongAnswer, state.profile)

    // 3. move to the next quiz
    import scala.scalajs.js.timers._
    setTimeout(1000) {
      setState((s: State) => State(DataSet.next, s.profile, false))
    }
  }

  def answerStyle(i: Int) = if (state.quiz.correctAnswer == i) styles.correctAnswer
    else styles.incorrectAnswer

  def answerElement(i: Int) =
    if (state.done)
        TouchableHighlight(style = answerStyle(i))(textElement(state.quiz.answers(i)))
    else
        TouchableHighlight(style = styles.answer, onPress = chooseAnswer(i))(textElement(state.quiz.answers(i)))

  def render() = {
    View(style = styles.container)(
      View(style = styles.top)(textElement(state.profile.score.toString)),
      View(style = styles.middle)(textElement(state.quiz.question)),
      View(style = styles.bottom)(
        answerElement(0),answerElement(1),answerElement(2),answerElement(3)
      )
    )
  }
}

object QuizScreen {
  trait Params extends Object {
  }

  case class State(quiz: Quiz, profile: Profile, done: Boolean)

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
    val incorrectAnswer = style(
      backgroundColor := "red",
      margin := 20,
      width := 125,
      height := 125,
      alignItems := "center",
      justifyContent := "center"
    )
    val correctAnswer = style(
      backgroundColor := "green",
      margin := 20,
      width := 125,
      height := 125,
      alignItems := "center",
      justifyContent := "center"
    )
  }
}

