package eu.foregather.model

import Difficulty._
import Answers.Answers

object DataSet {
    def default: QCM = QCM("Default question", List("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy)
    def test: Set[QCM] = Set(
        QCM("Question 1", List("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy),
        QCM("Question 2", List("Answer A", "Answer B", "Answer C", "Answer D"), 1, Easy),
        QCM("Question 3", List("Answer A", "Answer B", "Answer C", "Answer D"), 2, Easy),
        QCM("Question 4", List("Answer A", "Answer B", "Answer C", "Answer D"), 3, Easy))

    def next: QCM = {
        import scala.util.Random
        val rnd = new Random
        test.toVector(rnd.nextInt(test.size))
    }
}
