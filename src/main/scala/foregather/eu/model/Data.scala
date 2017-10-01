package foregather.eu.model

import Difficulty._
import Answers.Answers

object DataSet {
    def default: QCM = QCM("Default question", new Answers("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy)
    def test: Set[QCM] = Set(
        QCM("Question 1", new Answers("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy),
        QCM("Question 2", new Answers("Answer A", "Answer B", "Answer C", "Answer D"), 1, Easy),
        QCM("Question 3", new Answers("Answer A", "Answer B", "Answer C", "Answer D"), 2, Easy),
        QCM("Question 4", new Answers("Answer A", "Answer B", "Answer C", "Answer D"), 3, Easy))

    def next: QCM = {
        import scala.util.Random
        val rnd = new Random
        test.toVector(rnd.nextInt(test.size))
    }
}
