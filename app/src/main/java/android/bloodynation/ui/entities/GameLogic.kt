package android.bloodynation.ui.entities

import android.bloodynation.ui.entities.database.*
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameLogic() : ViewModel() {

    inner class DatabaseManager(_context: Context){

        private val influenceDao: InfluenceDao
        private val questionDao: QuestionDao
        private val crossRefDao: QuestionsInfluencesCrossRefDao
        init {
            DictatorDatabase.getInstance(_context).also {
                influenceDao = it.influenceDao()
                questionDao = it.questionDao()
                crossRefDao = it.questionsInfluencesCrossRef()
            }
        }

        fun clearAll(){
            influenceDao.deleteAll()
            questionDao.deleteAll()
        }

        fun uploadQuestions(){
            for (i in questions)
                crossRefDao.insert(i)
        }
        fun uploadInfluences(){
            for (i in questions)
                for (j in i.inflns)
                    influenceDao.insertAll(j)
        }

    }




    // subordinate parts of kingdom
    private val fractions = ArrayList<Fraction>(4)
    private val fractionsLiveData = MutableLiveData<ArrayList<Fraction>>()

    private var questions = ArrayList<QuestionsWithInfluences>()

    private var score = 0
    private val scoreLiveData = MutableLiveData<Int>().apply {
        value = score
    }

    private var curQuestion = QuestionsWithInfluences(Question(question = "Начать игру?"))
    private val curQuestionLiveData: MutableLiveData<QuestionsWithInfluences> by lazy {
        MutableLiveData<QuestionsWithInfluences>().apply {
            value = curQuestion
        }
    }
    private var alive = false
    private val aliveLiveData = MutableLiveData<Boolean>().apply{
        value = alive
    }


    private val min = 0
    private val max = 12

    private val startMin = 4
    private val startMax = 7


    fun getDatabaseManager(context: Context): DatabaseManager {
        return DatabaseManager(context)
    }

    fun getAliveLiveData():LiveData<Boolean>{
        return aliveLiveData
    }

    fun getCurQuestionLiveData(): LiveData<QuestionsWithInfluences> {
        return curQuestionLiveData
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

    private fun updateQuestion(q: QuestionsWithInfluences) {
        curQuestion = q
        curQuestionLiveData.value = curQuestion
    }

    fun initGame() {
        nullifyScore()
        fillFractions()
        fillQuestions()
        updateQuestion(QuestionsWithInfluences(Question("Начать игру?")))
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
        questions.add(QuestionsWithInfluences(Question("Дать пизды церкви?")))
        questions[0].registerInfluence(
            Influence(
                "Народ",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[0].registerInfluence(
            Influence(
                "Армия",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[0].registerInfluence(
            Influence(
                "Элита",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[0].registerInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions.add(QuestionsWithInfluences(Question("Дать пизды элите?")))
        questions[1].registerInfluence(
            Influence(
                "Народ",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[1].registerInfluence(
            Influence(
                "Армия",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[1].registerInfluence(
            Influence(
                "Элита",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[1].registerInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions.add(QuestionsWithInfluences(Question("Поднять налог?")))
        questions[2].registerInfluence(
            Influence(
                "Народ",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[2].registerInfluence(
            Influence(
                "Армия",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[2].registerInfluence(
            Influence(
                "Элита",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[2].registerInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions.add(QuestionsWithInfluences(Question("Провести праздник города?")))
        questions[3].registerInfluence(
            Influence(
                "Народ",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[3].registerInfluence(
            Influence(
                "Армия",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[3].registerInfluence(
            Influence(
                "Элита",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
        questions[3].registerInfluence(
            Influence(
                "Церковь",
                Random.nextInt(-2, 3),
                Random.nextInt(-2, 3)
            )
        )
    }

    fun nextRound(answer: Boolean) {
        executeAnswer(curQuestion, answer)
        fractionsLiveData.value = fractions
        updateQuestion(getQuestion())
        addScore(1)
        alive = isAlive()
        aliveLiveData.value = alive
    }

    private fun isAlive(): Boolean {
        for (i in fractions) {
            if (i.currentAttitude <= 0) {
                return false
            }
        }
        return true
    }

    private fun getQuestion(): QuestionsWithInfluences {
        return questions.random()
    }

    private fun executeAnswer(question: QuestionsWithInfluences, answer: Boolean) {
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