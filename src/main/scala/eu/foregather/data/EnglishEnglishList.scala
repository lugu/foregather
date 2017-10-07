package eu.foregather.data

import eu.foregather.model.Difficulty._
import eu.foregather.model.QCM
import scala.util.Random

trait EnglishFrenchList {
  val words: List[String]

  def size = words.size / 2
  def english(i: Int): String = words(2 * i)
  def french(i: Int): String = words(2 * i + 1)
  def randomIndex(rnd: Random) = rnd.nextInt(size)
  def randomEnglish(rnd: Random): String = english(randomIndex(rnd))
  def randomFrench(rnd: Random): String = french(randomIndex(rnd))

  def quiz(rnd: Random): QCM = {

    val index = rnd.nextInt(size)
    if (rnd.nextBoolean()) {
      // french question
      val question = french(index)
      val answers: List[String] = randomEnglish(rnd) :: randomEnglish(rnd) :: randomEnglish(rnd) :: List()
      val correctAnswer = rnd.nextInt(4)
      val randomAnswers: List[String] = answers.take(correctAnswer) ::: List(french(index)) ::: answers.drop(correctAnswer)
      return QCM(question, randomAnswers, correctAnswer, Easy)
    } else {
      // english question
      val question = english(index)
      val answers: List[String] = randomFrench(rnd) :: randomFrench(rnd) :: randomFrench(rnd) :: List()
      val correctAnswer = rnd.nextInt(4)
      val randomAnswers: List[String] = answers.take(correctAnswer) ::: List(french(index)) ::: answers.drop(correctAnswer)
      return QCM(question, randomAnswers, correctAnswer, Easy)
    }
  }
}
