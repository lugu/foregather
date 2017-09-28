package foregather.eu

import sri.universal.apis.AppRegistry

object MobileApp extends JSApp {

  def main(args:Array[String]) = {
    AppRegistry.registerComponent("foregather", () => components.root)
  }
}
