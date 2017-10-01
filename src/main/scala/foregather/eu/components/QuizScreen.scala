package foregather.eu.components

import sri.navigation._
import sri.universal.components._

import scala.scalajs.js.Object

import foregather.eu.model.QCM
import foregather.eu.model.DataSet


class QuizScreen
    extends NavigationScreenComponentP[QuizScreen.Params] {
  import QuizScreen._
  def render() = {
    View(style = GlobalStyles.wholeContainer)(
      TextC(props.navigation.state.params.map(_.qcm.question).getOrElse("Question not found").toString),
      Button(title = "Answer",
             onPress = () =>
               setParams(new Params {
                 override val qcm: QCM = DataSet.default
               }))
    )
  }
}

object QuizScreen {
  trait Params extends Object {
    val qcm: QCM
  }
}

