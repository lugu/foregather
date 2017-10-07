package eu.foregather.data

import scala.util.Random

import eu.foregather.model.Difficulty._
import eu.foregather.model.Quiz

object DataSet {
    def default: Quiz = Quiz("Default question", List("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy)
    def test: Set[Quiz] = Set(
        Quiz("Question 1", List("Answer A", "Answer B", "Answer C", "Answer D"), 0, Easy),
        Quiz("Question 2", List("Answer A", "Answer B", "Answer C", "Answer D"), 1, Easy),
        Quiz("Question 3", List("Answer A", "Answer B", "Answer C", "Answer D"), 2, Easy),
        Quiz("Question 4", List("Answer A", "Answer B", "Answer C", "Answer D"), 3, Easy))

    val rnd = new Random
    def next: Quiz = {
        
        if (rnd.nextBoolean()) WordListLogistic.quiz(rnd)
        else WordListBusinessEnglish.quiz(rnd)
    }
}
