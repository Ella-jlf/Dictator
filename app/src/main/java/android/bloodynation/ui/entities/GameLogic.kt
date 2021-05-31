package android.bloodynation.ui.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameLogic : ViewModel(){
    private val fractions = ArrayList<Fraction>(4)
    val fractionsLiveData  = MutableLiveData<ArrayList<Fraction>>()
    private val min = 0
    private val max = 12
    private val startMin = 4
    private val startMax = 7
    private val questions = ArrayList<Question>()
    var curQuestion = Question("Начать игру?")


    fun initGame() {
        fillFractions()
        fillQuestions()
        curQuestion = Question("Начать игру?")
    }

    private fun fillFractions() {
        fractions.clear()
        fractions.add(Fraction("Народ", Random.nextInt(startMin, startMax)))
        fractions.add(Fraction("Армия", Random.nextInt(startMin, startMax)))
        fractions.add(Fraction("Элита", Random.nextInt(startMin, startMax)))
        fractions.add(Fraction("Церковь", Random.nextInt(startMin, startMax)))
        fractionsLiveData.value = fractions
    }

    private fun fillQuestions() {
        questions.clear()
        questions.add(Question("Дать пизды церкви?"))
        questions[0].addInfluence(Influence("Народ", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[0].addInfluence(Influence("Армия", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[0].addInfluence(Influence("Элита", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[0].addInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions.add(Question("Дать пизды элите?"))
        questions[1].addInfluence(Influence("Народ", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[1].addInfluence(Influence("Армия", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[1].addInfluence(Influence("Элита", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[1].addInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions.add(Question("Поднять налог?"))
        questions[2].addInfluence(Influence("Народ", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[2].addInfluence(Influence("Армия", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[2].addInfluence(Influence("Элита", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[2].addInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions.add(Question("Провести праздник города?"))
        questions[3].addInfluence(Influence("Народ", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[3].addInfluence(Influence("Армия", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[3].addInfluence(Influence("Элита", Random.nextInt(-2, 3), Random.nextInt(-2, 3)))
        questions[3].addInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
    }

    fun nextRound(answer:Boolean) {
        executeAnswer(curQuestion,answer)
        fractionsLiveData.value = fractions
        if (isAlive()){
            curQuestion = getQuestion()
        }
    }

    private fun isAlive(): Boolean {
        for (i in fractions) {
            if (i.currentAttitude <= 0){
                initGame()
                return false}
        }
        return true
    }

    private fun getQuestion(): Question {
        return questions.random()
    }

    private fun executeAnswer(question: Question, answer: Boolean) {
        if (answer) {
            for (i in fractions) {
                for (j in question.inflns) {
                    if (i.name == j.fraction_name) {
                        i.currentAttitude += j.yes
                    }
                }
            }
        } else {
            for (i in fractions) {
                for (j in question.inflns) {
                    if (i.name == j.fraction_name) {
                        i.currentAttitude += j.no
                    }
                }
            }

        }
    }

}