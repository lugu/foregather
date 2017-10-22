package eu.foregather.components

import sri.navigation._
import sri.universal.components._
import sri.universal.apis.Dimensions
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

  def textView(t: String) = View(style = GlobalStyles.textBlock)(
    Text(style = GlobalStyles.defaultTextStyle)(t)
  )

  def actionAnswer(i: Int) = () => {
    // 1. update the button style
    setState((s: State) => State(s.quiz, s.profile, true))

    // 2. update the score
    if (state.quiz.correctAnswer == i) Circuit.actionHandler(CorrectAnswer, state.profile)
    else  Circuit.actionHandler(WrongAnswer, state.profile)
  }

  def actionNext = () => {
    setState((s: State) => State(DataSet.next, s.profile, false))
  }

  def answerStyle(i: Int) = if (state.quiz.correctAnswer == i) styles.correctAnswer
    else styles.incorrectAnswer

  def answerView(i: Int) = {
    val txt = textView(state.quiz.answers(i))
    val st = if (state.done) answerStyle(i) else styles.answer
    val ac = if (state.done) actionNext else actionAnswer(i)
    TouchableHighlight(style = st, onPress = ac)(txt)
  }

  def screenViews = {
    View(styles.container)(
      View(style = styles.top)(textView(state.profile.score.toString)),
      View(style = styles.middle)(textView(state.quiz.question)),
      View(style = styles.bottom)(
        answerView(0),answerView(1),answerView(2),answerView(3)
        )
    )
  }

  def render() = if (!state.done) screenViews
    else TouchableOpacity(styles.container, onPress=actionNext)(screenViews)
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
      margin := 10,
      width := 125,
      height := 125,
      alignItems := "center",
      justifyContent := "center"
    )
    val incorrectAnswer = style(
      backgroundColor := "red",
      margin := 10,
      width := 125,
      height := 125,
      alignItems := "center",
      justifyContent := "center"
    )
    val correctAnswer = style(
      backgroundColor := "green",
      margin := 10,
      width := 125,
      height := 125,
      alignItems := "center",
      justifyContent := "center"
    )
  }
}

