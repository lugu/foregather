package eu.foregather.data

import eu.foregather.model.Difficulty._
import eu.foregather.model.Quiz
import scala.util.Random

trait EnglishFrenchList {
  val words: List[String]

  def size = words.size / 2
  def english(i: Int): String = words(2 * i)
  def french(i: Int): String = words(2 * i + 1)
  def randomIndex(rnd: Random) = rnd.nextInt(size)
  def randomEnglish(rnd: Random): String = english(randomIndex(rnd))
  def randomFrench(rnd: Random): String = french(randomIndex(rnd))

  def randomWords(not: Int, rnd: Random)(nb: Int, pick: (Int) => String): List[String] = {
    def rndInt(): Int = {
      val i = rnd.nextInt(size)
      if (i == not) rndInt() else i
    }
    (1 to nb).map(i => pick(rndInt())).toList
  }

  def makeQuiz(rnd: Random, pickL1: (Int) => String, pickL2: (Int) => String): Quiz = {
    val index = rnd.nextInt(size)
    val question = pickL1(index)
    val answers: List[String] = randomWords(index, rnd)(3, pickL2)
    val correctAnswer = rnd.nextInt(4)
    val randomAnswers: List[String] = answers.take(correctAnswer) ::: List(pickL2(index)) ::: answers.drop(correctAnswer)
    return Quiz(question, randomAnswers, correctAnswer, Easy)
  }

  def quiz(rnd: Random): Quiz = {
    if (rnd.nextBoolean()) makeQuiz(rnd, french, english)
    else makeQuiz(rnd, english, french)
  }
}
