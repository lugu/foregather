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
    View(style = GlobalStyles.wholeContainer)(
      TextC("Question: " + params.get.qcm.question),
      View(style = styles.container)(
        getBlock(() => navigation.navigate[HomeScreen],
          params.get.qcm.answers._1),
        getBlock(() => navigation.navigate[HomeScreen],
          params.get.qcm.answers._2),
        getBlock(() => navigation.navigate[HomeScreen],
          params.get.qcm.answers._3),
        getBlock(() => navigation.navigate[HomeScreen],
          params.get.qcm.answers._4)
        ))
  }
  @inline
  def getBlock(onPress: () => _, title: String) =
    TouchableOpacity(
      style = styles.block,
      onPress = onPress,
      activeOpacity = 0.8
    )(
      Text(style = styles.blockText)(title)
    )
}

object QuizScreen {
  trait Params extends Object {
    val qcm: QCM
  }

  object styles extends InlineStyleSheetUniversal {

    import dsl._

    val container = style(
      flex := 1,
      padding := 20,
      flexDirection.row,
      flexWrap.wrap
    )

    val block = style(
      width := "42%",
      height := 120,
      backgroundColor := "white",
      borderColor := "#eee",
      borderRadius := 2,
      margin := 10,
      paddingHorizontal := 3,
      shadowColor := "grey",
      shadowOpacity := 0.5,
      shadowRadius := 2,
      shadowOffset := js.Dynamic.literal(height = 1, width = 0),
      elevation := 3,
      borderWidth := 1,
      justifyContent.center,
      alignItems.center
    )

    val blockText = style(fontWeight := "500", fontSize := 14)
  }
}

