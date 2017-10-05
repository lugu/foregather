package eu.foregather

import sri.universal.apis.AppRegistry

object MobileApp extends App {
  AppRegistry.registerComponent("foregather", () => components.root)
}
