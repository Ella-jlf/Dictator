package android.bloodynation.ui.entities.database

import androidx.room.*

@Dao
interface QuestionDao {


    @Query("SELECT * FROM questions")
    fun getAll(): List<Question>

    @Query("SELECT * FROM questions WHERE questionId IN (:questionIds)")
    fun loadAllByIds(questionIds: IntArray): List<Question>

    @Insert
    fun insertAll(vararg question: Question)

    @Query("DELETE FROM questions")
    fun deleteAll()

    @Delete
    fun delete(question: Question)

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionsWithInfluences(): List<QuestionsWithInfluences>

}