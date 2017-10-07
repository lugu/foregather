package eu.foregather.model

object Difficulty extends Enumeration {
    type Difficulty = Value
    val Easy, Medium, Hard = Value
}

import Difficulty._

case class Quiz(qcms: Set[QCM])

case class QCM(
    question: String,
    answers: List[String],
    correctAnswer: Int,
    difficulty: Difficulty) {
    def withoutAnswer = QCM(question, answers, -1, difficulty)
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

object Circuit {
  def initialState = Profile("Tom", 0, History(List()))
  val actionHandler = (action: Action, model: Profile) => action match {
    case (CorrectAnswer) => model.copy(score = model.score + 100, history = model.history.updateWith(Today.is, 1))
    case (WrongAnswer) => model.copy(score = model.score + 100, history = model.history.updateWith(Today.is, 1))
  }
}

object QuizUI {
    def run(qcm: QCM, getAnswer: (Int) => Int) = {
        println("should display the UI here")
        println("fake user response to be 1")
        val correctAnswer = getAnswer(1)
        println("should have answer " + correctAnswer)
    }
}
