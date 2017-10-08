package eu.foregather.model

import sri.universal.apis.AsyncStorage
import scala.scalajs.js
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global


object ProfileStorage {

  def historyFromString(s: String) = {
    History(s.split(",").flatMap(activityFromString(_)).toList)
  }

  def activityFromString(s: String): Option[Activity] = try {
    s.split("-").map(_.toInt) match {
      case Array(a, b) => Some(Activity(a, b))
        case _ => {
          println("Failed to unmarshall activity: " + s)
          None
        }
    } 
  } catch {
    case e: java.lang.NumberFormatException => {
      println("Failed to unmarshall activity: " + s)
      None
    }
  }

  def scoreFromString(s: String): Int = {
    try {
      s.toInt
    } catch {
      case e: java.lang.NumberFormatException => {
        println("Failed to parse score: " + s)
        0
      }
    }
  }

  val nameKey = "1/Name"
  val scoreKey = "1/Score"
  val historyKey = "1/History"
  val keys = js.Array(nameKey, scoreKey, historyKey)

  def retrive(f: Profile => Unit) = {
    AsyncStorage.multiGet(keys).toFuture onComplete {
      case Success(results) => {

        def getOrElse(i: Int, o: String): String = results.toSeq.lift(i) match {
          case Some(a) => a.toList match {
            case List(k, v) => v
            case _ => o
          }
          case None => o
        }

        val name = getOrElse(0, "No name")
        val score = scoreFromString(getOrElse(1, "0"))
        val history = historyFromString(getOrElse(2, ""))
        f(Profile(name, score, history))
      }
      case Failure(t) => println("Failed to fetch preferences: " + t.getMessage)
    }
  }

  def nameString(p: Profile) = p.name
  def scoreString(p: Profile) = p.score.toString
  def historyString(p: Profile) = {
    p.history.activities.map{
      case (a: Activity) =>a.day.toString + "-" + a.quizNb.toString 
    }.mkString(",")
  }

  def store(p: Profile) = {
    AsyncStorage.multiSet(js.Array(
        js.Array(nameKey, nameString(p)),
        js.Array(scoreKey, scoreString(p)),
        js.Array(historyKey, historyString(p))
    ))
  }
}
