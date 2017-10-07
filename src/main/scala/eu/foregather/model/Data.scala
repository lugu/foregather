package eu.foregather.data

import scala.util.Random

import eu.foregather.model.Difficulty._
import eu.foregather.model.QCM

object DataSet {
    def default: QCM = QCM("Default question", List("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy)
    def test: Set[QCM] = Set(
        QCM("Question 1", List("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy),
        QCM("Question 2", List("Answer A", "Answer B", "Answer C", "Answer D"), 1, Easy),
        QCM("Question 3", List("Answer A", "Answer B", "Answer C", "Answer D"), 2, Easy),
        QCM("Question 4", List("Answer A", "Answer B", "Answer C", "Answer D"), 3, Easy))

    val rnd = new Random
    def next: QCM = {
        
        if (rnd.nextBoolean()) WordListLogistic.quiz(rnd)
        else WordListBusinessEnglish.quiz(rnd)
    }
}
