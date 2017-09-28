package foregather.eu.components

import sri.navigation._
import sri.universal.components._


class ScreenWithCustomRightButton extends NavigationScreenComponentNoPS {
  def render() = {
    View(style = GlobalStyles.wholeContainer)(
      TextC("Header Right element example")
    )
  }
}

