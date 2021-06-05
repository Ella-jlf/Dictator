package android.bloodynation.ui.entities.database

import androidx.room.*

@Dao
interface QuestionsInfluencesCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: QuestionsWithInfluences)


    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestions(): List<QuestionsWithInfluences>
}