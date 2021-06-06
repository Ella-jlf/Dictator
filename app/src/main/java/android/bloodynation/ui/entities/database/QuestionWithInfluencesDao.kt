package android.bloodynation.ui.entities.database

import androidx.room.*



@Dao
interface QuestionWithInfluencesDao {

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionsWithInfluences(): List<QuestionWithInfluences>

    @Transaction
    fun insert(questionsWithInfluences: ArrayList<QuestionWithInfluences>){
        for (i in questionsWithInfluences){
            val id = insert(i.questionInstance)

            for (j in i.inflns){
                j.setParentQuestionId(id)
                insert(j)
            }
        }
    }

    @Transaction
    fun insert(questionWithInfluences:QuestionWithInfluences){
            val id = insert(questionWithInfluences.questionInstance)

            for (j in questionWithInfluences.inflns){
                j.setParentQuestionId(id)
                insert(j)
            }
        }



    @Insert
    fun insert(question: Question):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(influence: Influence)



    @Query("DELETE FROM questions")
    fun deleteQuestions()
    @Query("DELETE FROM influences")
    fun deleteInfluences()
}