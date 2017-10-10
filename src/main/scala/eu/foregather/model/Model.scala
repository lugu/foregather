package eu.foregather.model

object Difficulty extends Enumeration {
    type Difficulty = Value
    val Easy, Medium, Hard = Value
}

import Difficulty._

case class Quiz(
    question: String,
    answers: List[String],
    correctAnswer: Int,
    difficulty: Difficulty) {
    def withoutAnswer = Quiz(question, answers, -1, difficulty)
}

case object Today {
  import scala.scalajs.js.Date
  val msPerDay = 1000 * 60 * 60 * 24
  val epoch = new Date(1970, 1, 1, 0, 0, 0, 0)
  def now = new Date()
  def daysSinceEpoch = ((now.getTime() - epoch.getTime()) / msPerDay).floor.toInt
  def is: Int = daysSinceEpoch
}

case class History(activities: List[Activity]) {
  def updateWith(d: Int, q: Int) = {
    activities.find((a: Activity) => a.day == d) match {
      case Some(a) => History(activities.map((a: Activity) =>
          if (a.day == d) Activity(d, a.quizNb + q) else a))
      case None => History(Activity(d, q) :: activities)
    }
  }
  def since(since: Int) = History(activities.filter(since < _.day))
  def sorted = History(activities.sortBy(_.day))
}

case class Activity(day: Int, quizNb: Int) {
}

case class Profile(name: String, score: Int, history: History)

trait Action
case object CorrectAnswer extends Action
case object WrongAnswer extends Action

trait ProfileWatcher {
  def onProfileChange(p: Profile)
}

object Circuit {
  var watcher: List[ProfileWatcher] = List()

  var profile = Profile("Unknown", 0, History(List()))
  def initialState = profile

  val actionHandler = (action: Action, model: Profile) => {
    val history = model.history.updateWith(Today.is, 1)
    val score = action match {
      case (CorrectAnswer) => model.score + 100
      case (WrongAnswer) => model.score
    }
    setState(model.copy(score = score + 100, history = history))
  }

  def setState(p: Profile) = {
    profile = p
    watcher.foreach { w => w.onProfileChange(p) }
    ProfileStorage.store(p)
  }

  def registerWatcher(w: ProfileWatcher) = { watcher = w :: watcher }
  def unregisterWatcher(w: ProfileWatcher) = { watcher = watcher.filterNot(a => a == w) }

  ProfileStorage.retrive((p: Profile) => setState(p))
}

