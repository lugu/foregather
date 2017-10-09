package eu.foregather.components

import sri.core._
import sri.universal.components._
import sri.universal.styles.InlineStyleSheetUniversal

import scala.scalajs.js

import eu.foregather.model.History
import eu.foregather.model.Activity
import eu.foregather.model.Circuit
import eu.foregather.model.Profile
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

  def bar(i: Int) = View(style = Styles.bar)()
  def render() = {

    val columns = state.history.activities.map((a: Activity) => bar(a.quizNb))
    View(style = Styles.historyChart)(columns: _*)
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
