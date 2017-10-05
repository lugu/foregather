package eu.foregather

import sri.universal.apis.AppRegistry

object MobileApp extends App {

  override def main(args:Array[String]) = {
    AppRegistry.registerComponent("foregather", () => components.root)
  }
}
