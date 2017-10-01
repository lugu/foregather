package foregather.eu.model

object Difficulty extends Enumeration {
    type Difficulty = Value
    val Easy, Medium, Hard = Value
}

object Answers {
    type Answers = Tuple4[String, String, String, String]
}

import Difficulty._
import Answers.Answers


case class QCM(
    question: String,
    answers: Answers,
    correctAnswer: Int,
    difficulty: Difficulty) {
    def withoutAnswer = QCM(question, answers, -1, difficulty)
}

class User(val name: String, private var score: Int) {
    def level: Int = score / 1000
    def play(qcm: QCM) = {
        QuizUI.run(qcm.withoutAnswer, (answer: Int) => {
            if (qcm.correctAnswer == answer) {
                score += 100
            }
            qcm.correctAnswer 
        });
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
