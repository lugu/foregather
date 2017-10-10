package eu.foregather.components

import sri.core._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal

import scala.scalajs.js

import eu.foregather.model.History
import eu.foregather.model.Activity
import eu.foregather.model.Circuit
import eu.foregather.model.Profile
import eu.foregather.model.Today
import eu.foregather.model.ProfileWatcher

class HistoryChart extends ComponentS[HistoryChart.State] {
  import HistoryChart._

  initialState(State(Circuit.initialState.history))

  val watcher = new ProfileWatcher {
    def onProfileChange(p: Profile) = setState((s: State) => State(p.history))
  }

  override def componentWillMount() = {
    Circuit.registerWatcher(watcher)
      setState((s: State) => State(Circuit.initialState.history))
  }

  override def componentWillUnmount() = {
    Circuit.unregisterWatcher(watcher)
  }

  def normalizeQuizNb(i: Int) = if (i < 0) 0 else if (i > 100) 100 else i
  def bar(i: Int): ReactNode = {
    import dsl._
    View(style = Styles.barContainer)(
      View(style = style(flex := (100 - i)))(),
      View(style = style(flex := i, backgroundColor := GlobalStyles.blueFonce))())
  }

  def last30days(h: History) = {
    val since = Today.is - 30
    (for (i <- 1 to 30) yield Activity(since + i, 0)).foldLeft(
        h.since(since).sorted
        )((h: History, a: Activity) => h.updateWith(a.day, a.quizNb))
  }
  def columnSpace: ReactNode = View(style = Styles.barSpace)()
  def column(a: Activity): ReactNode = bar(normalizeQuizNb(a.quizNb))
  def columns(h: History): List[ReactNode] =
    h.activities.flatMap((a: Activity) => List(column(a), columnSpace))

  def render() = {
    val c = columnSpace :: columns(last30days(state.history))
    View(style = Styles.historyChart)(c: _*)
  }

  def textElement(t: String) = View(style = GlobalStyles.textBlock)(
      Text(style = GlobalStyles.defaultTextStyle)(t))
}

object HistoryChart extends InlineStyleSheetUniversal {

  case class State(history: History)

  def apply(key: String = null, ref: js.Function1[HistoryChart, Unit] = null) =
    CreateElementNoProps[HistoryChart](key = key, ref = ref)

  object Styles extends InlineStyleSheetUniversal {

    import dsl._

    val bar = style(
      flex := 1,
      backgroundColor := GlobalStyles.noir
    )

    val barSpace = style(
      flex := 1
    )
    val barContainer = style(
      flex := 2,
      flexDirection := "column"
    )

    val historyChart = style(
      flex := 1,
      flexDirection := "row",
      justifyContent := "space-around",
      alignItems := "center",
      flexWrap := "wrap",
      backgroundColor := GlobalStyles.blanc
    )
  }
}
