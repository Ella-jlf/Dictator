package android.bloodynation.ui.entities

import android.bloodynation.ui.entities.database.DictatorDatabase
import android.bloodynation.ui.entities.database.Influence
import android.bloodynation.ui.entities.database.Question
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameLogic() : ViewModel() {
    private val fractions = ArrayList<Fraction>(4)
    private val fractionsLiveData = MutableLiveData<ArrayList<Fraction>>()
    private val min = 0
    private val max = 12
    private var score = 0
    private val scoreLiveData = MutableLiveData<Int>().apply {
        value = score
    }
    private val startMin = 4
    private val startMax = 7
    private var questions = ArrayList<Question>()
    var curQuestion = Question(question = "Начать игру?")

    //TODO:delete this
    fun uploadToDb(context: Context) {
        val questionDao = DictatorDatabase.getInstance(context).questionDao()
        viewModelScope.launch(Dispatchers.IO) {
            questionDao.deleteAll()
            questionDao.insertAll(questions[0], questions[1], questions[2])
        }

    }

    // TODO: delete this
    fun checkDb(context: Context) {
        val questionDao = DictatorDatabase.getInstance(context).questionDao()
        viewModelScope.launch(Dispatchers.IO) {
            questions = questionDao.getAll() as ArrayList<Question>
        }
    }


    fun getRawFractions(): ArrayList<Fraction> {
        return fractions
    }

    fun getLiveDataFractions(): LiveData<ArrayList<Fraction>> {
        return fractionsLiveData
    }

    private fun addScore(count: Int) {
        score += count
        scoreLiveData.value = score
    }

    fun getScoreLiveData(): LiveData<Int> {
        return scoreLiveData
    }

    private fun nullifyScore() {
        score = 0
        scoreLiveData.value = score
    }

    fun initGame() {
        nullifyScore()
        fillFractions()
        fillQuestions()
        curQuestion = Question(question = "Начать игру?")
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

    //returns false if died
    fun nextRound(answer: Boolean): Boolean {
        executeAnswer(curQuestion, answer)
        fractionsLiveData.value = fractions
        curQuestion = getQuestion()
        addScore(1)
        return isAlive()
    }

    private fun isAlive(): Boolean {
        for (i in fractions) {
            if (i.currentAttitude <= 0) {
                return false
            }
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
                    if (i.name == j.fractionName) {
                        i.currentAttitude += j.yes
                        if (i.currentAttitude > max)
                            i.currentAttitude = max
                    }
                }
            }
        } else {
            for (i in fractions) {
                for (j in question.inflns) {
                    if (i.name == j.fractionName) {
                        i.currentAttitude += j.no
                        if (i.currentAttitude > max)
                            i.currentAttitude = max
                    }
                }
            }

        }
    }

}