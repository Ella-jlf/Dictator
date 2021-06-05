package android.bloodynation.ui.entities.database

import androidx.room.Entity

@Entity(primaryKeys = ["questionId", "influenceId"],tableName = "questions_influences_cross_ref")
data class QuestionsInfluencesCrossRef(val questionId: Int, val influenceId: Int)
