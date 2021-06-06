package android.bloodynation.ui.entities.database

import androidx.room.*

@Entity(
    tableName = "questions",
)
class Question(
    @PrimaryKey(autoGenerate = true)
    var questionId: Int = 0,
    @ColumnInfo(name = "question")
    var question: String? = null
) {
    constructor(_question: String) : this(0, _question)
}
