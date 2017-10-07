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
  // import java.time.LocalDate
  // import java.time.temporal.ChronoUnit
  // val epoch = LocalDate.ofEpochDay(0);
  def is = 0 // ChronoUnit.DAYS.between(epoch, LocalDate.now())
}

case class History(activities: List[Activity]) {
  def updateWith(d: Long, a: Int) = History(Activity(d, a) :: activities)
}
case class Activity(day: Long, quizNb: Int)
case class Profile(name: String, score: Int, history: History)

trait Action
case object CorrectAnswer extends Action
case object WrongAnswer extends Action

trait ProfileWatcher {
  def onProfileChange(p: Profile)
}

object Circuit {
  var watcher: List[ProfileWatcher] = List()
  var profile = Profile("Tom", 0, History(List()))
  def initialState = profile
  val actionHandler = (action: Action, model: Profile) => action match {
    case (CorrectAnswer) => setState(model.copy(score = model.score + 100, history = model.history.updateWith(Today.is, 1)))
    case (WrongAnswer) => setState(model.copy(score = model.score, history = model.history.updateWith(Today.is, 1)))
  }
  def setState(p: Profile) = {
    profile = p
    watcher.foreach { w => w.onProfileChange(p) }
  }
  def registerWatcher(w: ProfileWatcher) = { watcher = w :: watcher }
  def unregisterWatcher(w: ProfileWatcher) = { watcher = watcher.filterNot(a => a == w) }
}
